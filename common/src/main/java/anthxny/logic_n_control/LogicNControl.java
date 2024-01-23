package anthxny.logic_n_control;

import anthxny.logic_n_control.util.Utils;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import anthxny.logic_n_control.multiloader.Loader;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogicNControl
{
    public static final String ID = "logic_n_control";
    public static final String NAME = "Create: Logic 'n' Control";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final String VERSION = Utils.getVersion();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(LogicNControl.ID);
            //.useCreativeTab(() -> LCItems.itemGroup);

    public static void init() {
        LOGGER.info("{} v{} initializing! Create version: {} on platform: {}", NAME, VERSION, Create.VERSION, Loader.CURRENT);
        LCBlocks.init();
    }


    public static void gatherData(FabricDataGenerator.Pack gen) {

    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
