package anthxny.logic_n_control.content.programmablegate;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.TickableInstance;
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.foundation.utility.Color;

public class PGInstance extends BlockEntityInstance<PGBlockEntity> implements TickableInstance
{

    protected final ModelData indicator;

    protected int previousState;

    public PGInstance(MaterialManager materialManager, PGBlockEntity blockEntity) {
        super(materialManager, blockEntity);

        indicator = materialManager.defaultSolid()
                .material(Materials.TRANSFORMED)
                .getModel(AllPartialModels.FLEXPEATER_INDICATOR, blockState).createInstance();

        indicator.loadIdentity()
                .translate(getInstancePosition())
                .setColor(getColor());

        previousState = blockEntity.state;
    }

    @Override
    public void tick() {
        if (previousState == blockEntity.state) return;

        indicator.setColor(getColor());

        previousState = blockEntity.state;
    }

    @Override
    public void updateLight() {
        relight(pos, indicator);
    }

    @Override
    public void remove() {
        indicator.delete();
    }

    protected int getColor() {
        return Color.mixColors(0x2c0300, 0xcd0000, blockEntity.getProgress());
    }
}