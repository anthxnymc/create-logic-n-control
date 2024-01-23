package anthxny.logic_n_control.multiloader.forge;

import anthxny.logic_n_control.multiloader.Loader;
import org.jetbrains.annotations.ApiStatus;

public class LoaderImpl {
	@ApiStatus.Internal
	public static Loader getCurrent() {
		return Loader.FORGE;
	}
}
