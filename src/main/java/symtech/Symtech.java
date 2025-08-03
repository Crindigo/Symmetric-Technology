package symtech;

import com.cleanroommc.modularui.factory.GuiManager;
import gregtech.GTInternalTags;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;
import symtech.api.capability.SuSyCapabilities;
import symtech.api.metatileentity.MetaTileEntityGuiFactory;
import symtech.api.sound.SusySounds;
import symtech.common.CommonProxy;
import symtech.common.SusyMetaEntities;
import symtech.common.blocks.SuSyBlocks;
import symtech.common.blocks.SuSyMetaBlocks;
import symtech.common.covers.SuSyCoverBehaviors;
import symtech.common.item.SuSyMetaItems;
import symtech.common.metatileentities.SuSyMetaTileEntities;

@Mod(name = Symtech.NAME, modid = Symtech.MODID, version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING + ";required-after:gcym")
public class Symtech {

    public static final String NAME = "Symmetric Technology";
    public static final String MODID = "symtech";

    @SidedProxy(modId = MODID, clientSide = "symtech.client.ClientProxy", serverSide = "symtech.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(Symtech.MODID)
    public static Symtech instance;

    @Mod.EventHandler
    public void onModConstruction(FMLConstructionEvent event) {
        //This is now a config option I think
        //GTValues.HT = true;

        // Groovyscript starts immediately!
        proxy.checkCanaryFile();
    }



    @Mod.EventHandler
    public void onPreInit(@NotNull FMLPreInitializationEvent event) {

        proxy.preLoad();

        SuSyMetaBlocks.init();
        SuSyMetaItems.initMetaItems();
        SuSyBlocks.init();

        SusySounds.registerSounds();

        GuiManager.registerFactory(MetaTileEntityGuiFactory.INSTANCE);

        SuSyMetaTileEntities.init();
        SuSyCapabilities.init();

        SusyMetaEntities.init();

        if (FMLLaunchHandler.side() == Side.CLIENT) {
            OBJLoader.INSTANCE.addDomain(MODID);
        }
    }

    @Mod.EventHandler
    public void onInit(@NotNull FMLInitializationEvent event) {
        proxy.load();
        SuSyCoverBehaviors.init();
    }
}
