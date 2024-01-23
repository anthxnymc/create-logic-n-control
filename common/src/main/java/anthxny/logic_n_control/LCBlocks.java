package anthxny.logic_n_control;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.redstone.diodes.AbstractDiodeGenerator;
import com.simibubi.create.content.redstone.diodes.BrassDiodeBlock;
import com.simibubi.create.content.redstone.diodes.BrassDiodeGenerator;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import anthxny.logic_n_control.content.programmablegate.PGBlockEntity;
import anthxny.logic_n_control.content.programmablegate.PGRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import anthxny.logic_n_control.content.programmablegate.PGInstance;

public class LCBlocks
{
	public static final BlockEntry<BrassDiodeBlock> PROGRAMMABLE_GATE =
			LogicNControl.REGISTRATE.block("programmable_gate", BrassDiodeBlock::new)
					.initialProperties(() -> Blocks.REPEATER)
					.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
					.blockstate(new BrassDiodeGenerator()::generate)
					.addLayer(() -> RenderType::cutoutMipped)
					.item()
					.model(AbstractDiodeGenerator::diodeItemModel)
					.build()
					.register();

	public static final BlockEntityEntry<PGBlockEntity> PROGRAMMABLE_GATE_ENTITY = LogicNControl.REGISTRATE
			.blockEntity("programmable_gate", PGBlockEntity::new)
			.instance(() -> PGInstance::new, false)
			.validBlocks(LCBlocks.PROGRAMMABLE_GATE)
			.renderer(() -> PGRenderer::new)
			.register();


	public static void init() {
		// load the class and register everything
		LogicNControl.LOGGER.info("Registering blocks for " + LogicNControl.NAME);
	}
}
