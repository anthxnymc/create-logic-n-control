package anthxny.logic_n_control.forge;

import anthxny.logic_n_control.LogicNControl;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LogicNControl.ID)
public class LogicNControlForge
{
    public LogicNControlForge() {
        // registrate must be given the mod event bus on forge before registration
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LogicNControl.REGISTRATE.registerEventListeners(eventBus);
        LogicNControl.init();

        eventBus.addListener(EventPriority.LOWEST, LogicNControlForge::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        // Add data generators here
        DataGenerator gen = event.getGenerator();
    }
}
