package anthxny.logic_n_control.forge;

import anthxny.logic_n_control.LogicNControl;
import anthxny.logic_n_control.config.ExampleConfig;
import anthxny.logic_n_control.util.forge.UtilsImpl;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

public abstract class Events {
	@Mod.EventBusSubscriber(modid = LogicNControl.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static abstract class ClientModBusEvents {
		@SubscribeEvent
		static void onLoadComplete(FMLLoadCompleteEvent event) {
			ModContainer container = ModList.get()
					.getModContainerById(LogicNControl.ID)
					.orElseThrow(() -> new IllegalStateException("Example Mod mod container missing on LoadComplete"));
			container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
					() -> new ConfigScreenHandler.ConfigScreenFactory(ExampleConfig::createConfigScreen));
		}
	}

	@SubscribeEvent
	static void registerCommands(RegisterCommandsEvent event) {
		for(var command : UtilsImpl.commands)
			event.getDispatcher().register(command);
	}
}
