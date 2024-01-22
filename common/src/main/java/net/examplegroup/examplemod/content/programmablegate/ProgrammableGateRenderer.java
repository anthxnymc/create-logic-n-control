package net.examplegroup.examplemod.content.programmablegate;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.foundation.blockEntity.renderer.ColoredOverlayBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import com.simibubi.create.foundation.utility.Color;

public class ProgrammableGateRenderer extends ColoredOverlayBlockEntityRenderer<ProgrammableGateBlockEntity>
{
    public ProgrammableGateRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected int getColor(ProgrammableGateBlockEntity be, float partialTicks) {
        return Color.mixColors(0x2C0300, 0xCD0000, be.getProgress());
    }

    @Override
    protected SuperByteBuffer getOverlayBuffer(ProgrammableGateBlockEntity be) {
        return CachedBufferer.partial(AllPartialModels.FLEXPEATER_INDICATOR, be.getBlockState());
    }
}

