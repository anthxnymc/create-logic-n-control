package anthxny.logic_n_control.fabric.datagen;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import anthxny.logic_n_control.LogicNControl;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.commons.logging.Log;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class LCDataGen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        // Ensure that all mods are present if they are needed for data gen
        //for (Mods mod : Mods.values())
        //    mod.assertForDataGen();

        Path railwaysResources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
        // fixme re-enable the existing file helper when porting lib's ResourcePackLoader.createPackForMod is fixed
        ExistingFileHelper helper = new ExistingFileHelper(
                Set.of(railwaysResources), Set.of("create"), false, null, null
        );
        FabricDataGenerator.Pack pack = gen.createPack();

        LogicNControl.REGISTRATE.setupDatagen(pack, helper);
        LogicNControl.gatherData(pack);
    }
}