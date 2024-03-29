package anthxny.logic_n_control.util.forge;

import anthxny.logic_n_control.LogicNControl;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.common.util.MavenVersionStringHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class UtilsImpl {
	public static String getVersion() {
		String versionString = "UNKNOWN";

		List<IModInfo> infoList = ModList.get().getModFileById(LogicNControl.ID).getMods();
		if (infoList.size() > 1) {
			LogicNControl.LOGGER.error("Multiple mods for ID: " + LogicNControl.ID);
		}
		for (IModInfo info : infoList) {
			if (info.getModId().equals(LogicNControl.ID)) {
				versionString = MavenVersionStringHelper.artifactVersionToString(info.getVersion());
				break;
			}
		}
		return versionString;
	}

	public static boolean isDevEnv() {
		return !FMLLoader.isProduction();
	}

	public static Path configDir() {
		return FMLPaths.CONFIGDIR.get();
	}

	public static void registerConfig(ModConfig.Type type, IConfigSpec<?> spec) {
		ModLoadingContext.get().registerConfig(type, spec);
	}

	public static Set<LiteralArgumentBuilder<CommandSourceStack>> commands = new HashSet<>();

	public static void registerCommand(LiteralArgumentBuilder<CommandSourceStack> command) {
		commands.add(command);
	}
}
