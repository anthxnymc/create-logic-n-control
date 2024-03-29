package anthxny.logic_n_control.multiloader.fabric;

import anthxny.logic_n_control.multiloader.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class EnvImpl {
	public static Env getCurrent() {
		return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? Env.CLIENT : Env.SERVER;
	}
}
