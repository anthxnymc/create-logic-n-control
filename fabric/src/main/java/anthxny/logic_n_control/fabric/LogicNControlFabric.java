package anthxny.logic_n_control.fabric;

import anthxny.logic_n_control.LogicNControl;
import anthxny.logic_n_control.config.ExampleConfig;
import net.fabricmc.api.ModInitializer;

#if PRE_CURRENT_MC_1_19_2
import net.minecraftforge.api.fml.event.config.ModConfigEvents;
#elif POST_CURRENT_MC_1_20_1
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
#endif

public class LogicNControlFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfigEvents.loading(LogicNControl.ID).register(ExampleConfig::onLoad);
        ModConfigEvents.reloading(LogicNControl.ID).register(ExampleConfig::onReload);
        LogicNControl.init();
        // on fabric, Registrates must be explicitly finalized and registered.
        LogicNControl.REGISTRATE.register();
    }
}
