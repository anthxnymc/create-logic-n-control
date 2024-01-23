package anthxny.logic_n_control.fabric;

import anthxny.logic_n_control.config.ExampleConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return ExampleConfig::createConfigScreen;
	}
}
