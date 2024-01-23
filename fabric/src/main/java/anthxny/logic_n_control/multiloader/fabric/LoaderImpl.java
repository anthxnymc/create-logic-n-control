package anthxny.logic_n_control.multiloader.fabric;

import anthxny.logic_n_control.multiloader.Loader;
import net.fabricmc.loader.api.FabricLoader;

public class LoaderImpl {
	@org.jetbrains.annotations.ApiStatus.Internal
	public static Loader getCurrent() {
		return FabricLoader.getInstance().isModLoaded("quilt_loader") ? Loader.QUILT : Loader.FABRIC;
	}
}
