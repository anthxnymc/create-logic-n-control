package anthxny.logic_n_control.content.programmablegate;

import com.simibubi.create.content.redstone.diodes.BrassDiodeScrollSlot;
import com.simibubi.create.content.redstone.diodes.BrassDiodeScrollValueBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import static com.simibubi.create.content.redstone.diodes.BrassDiodeBlock.POWERING;

import java.util.List;

public class PGBlockEntity extends SmartBlockEntity
{
    protected int state;
    ScrollValueBehaviour maxState;

    public PGBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) { super(type, pos, state); }


    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        maxState = new BrassDiodeScrollValueBehaviour(Lang.translateDirect("logistics.redstone_interval"), this, new BrassDiodeScrollSlot()).between(2, 60 * 20 * 60);
        maxState.withFormatter(this::format);
        maxState.withCallback(this::onMaxDelayChanged);
        maxState.setValue(2);
        behaviours.add(maxState);
    }

    public float getProgress() {
        int max = Math.max(2, maxState.getValue());
        return Mth.clamp(state, 0, max) / (float) max;
    }

    public boolean isIdle() {
        return state == 0;
    }

    @Override
    public void tick() {
        super.tick();
        boolean powered = getBlockState().getValue(DiodeBlock.POWERED);
        boolean powering = getBlockState().getValue(POWERING);
        boolean atMax = state >= maxState.getValue();
        boolean atMin = state <= 0;
        updateState(powered, powering, atMax, atMin);
    }

    private void onMaxDelayChanged(int newMax) {
        state = Mth.clamp(state, 0, newMax);
        sendData();
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        state = compound.getInt("State");
        super.read(compound, clientPacket);
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putInt("State", state);
        super.write(compound, clientPacket);
    }

    private String format(int value) {
        if (value < 60)
            return value + "t";
        if (value < 20 * 60)
            return (value / 20) + "s";
        return (value / 20 / 60) + "m";
    }

//    @Override
//    public String getClipboardKey() {
//        return "Block";
//    }
//
//    @Override
//    public boolean readFromClipboard(CompoundTag tag, Player player, Direction side, boolean simulate) {
//        if (!tag.contains("Inverted"))
//            return false;
//        if (simulate)
//            return true;
//        BlockState blockState = getBlockState();
//        if (blockState.getValue(BrassDiodeBlock.INVERTED) != tag.getBoolean("Inverted"))
//            level.setBlockAndUpdate(worldPosition, blockState.cycle(BrassDiodeBlock.INVERTED));
//        return true;
//    }
//
//    @Override
//    public boolean writeToClipboard(CompoundTag tag, Direction side) {
//        tag.putBoolean("Inverted", getBlockState().getOptionalValue(BrassDiodeBlock.INVERTED).orElse(false));
//        return true;
//    }

    protected void updateState(boolean powered, boolean powering, boolean atMax, boolean atMin) {
        if (atMin && !powered)
            return;
        if (atMin || powered) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(POWERING, true));
            state = maxState.getValue();
            return;
        }

        if (state == 1) {
            if (powering && !level.isClientSide)
                level.setBlockAndUpdate(worldPosition, getBlockState().setValue(POWERING, false));
            if (!powered)
                state = 0;
            return;
        }

        if (!powered)
            state--;
    }
}
