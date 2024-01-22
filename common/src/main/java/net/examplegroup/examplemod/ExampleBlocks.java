package net.examplegroup.examplemod;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.redstone.diodes.AbstractDiodeGenerator;
import com.simibubi.create.content.redstone.diodes.BrassDiodeBlock;
import com.simibubi.create.content.redstone.diodes.BrassDiodeGenerator;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.examplegroup.examplemod.content.programmablegate.ProgrammableGateBlockEntity;
import net.examplegroup.examplemod.content.programmablegate.ProgrammableGateRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.examplegroup.examplemod.content.programmablegate.ProgrammableGateInstance;

import static net.examplegroup.examplemod.ExampleMod.REGISTRATE;

public class ExampleBlocks {
	public static final BlockEntry<BrassDiodeBlock> PROGRAMMABLE_GATE =
			REGISTRATE.block("programmable_gate", BrassDiodeBlock::new)
					.initialProperties(() -> Blocks.REPEATER)
					.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
					.blockstate(new BrassDiodeGenerator()::generate)
					.addLayer(() -> RenderType::cutoutMipped)
					.item()
					.model(AbstractDiodeGenerator::diodeItemModel)
					.build()
					.register();

	public static final BlockEntityEntry<ProgrammableGateBlockEntity> PROGRAMMABLE_GATE_ENTITY = REGISTRATE
			.blockEntity("programmable_gate", ProgrammableGateBlockEntity::new)
			.instance(() -> ProgrammableGateInstance::new, false)
			.validBlocks(ExampleBlocks.PROGRAMMABLE_GATE)
			.renderer(() -> ProgrammableGateRenderer::new)
			.register();


	public static void init() {
		// load the class and register everything
		ExampleMod.LOGGER.info("Registering blocks for " + ExampleMod.NAME);
	}
}
