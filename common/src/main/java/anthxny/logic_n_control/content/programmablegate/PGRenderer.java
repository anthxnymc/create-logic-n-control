package anthxny.logic_n_control.content.programmablegate;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.foundation.blockEntity.renderer.ColoredOverlayBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import com.simibubi.create.foundation.utility.Color;

public class PGRenderer extends ColoredOverlayBlockEntityRenderer<PGBlockEntity>
{
    public PGRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected int getColor(PGBlockEntity be, float partialTicks) {
        return Color.mixColors(0x2C0300, 0xCD0000, be.getProgress());
    }

    @Override
    protected SuperByteBuffer getOverlayBuffer(PGBlockEntity be) {
        return CachedBufferer.partial(AllPartialModels.FLEXPEATER_INDICATOR, be.getBlockState());
    }
}

