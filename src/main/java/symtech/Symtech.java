package symtech;

import com.cleanroommc.modularui.factory.GuiManager;
import gregtech.GTInternalTags;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;
import symtech.api.capability.SymtechCapabilities;
import symtech.api.metatileentity.MetaTileEntityGuiFactory;
import symtech.api.sound.SymtechSounds;
import symtech.common.CommonProxy;
import symtech.common.SymtechMetaEntities;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechMetaBlocks;
import symtech.common.covers.SymtechCoverBehaviors;
import symtech.common.item.SymtechMetaItems;
import symtech.common.metatileentities.SymtechMetaTileEntities;

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
    }



    @Mod.EventHandler
    public void onPreInit(@NotNull FMLPreInitializationEvent event) {

        proxy.preLoad();

        SymtechMetaBlocks.init();
        SymtechMetaItems.initMetaItems();
        SymtechBlocks.init();

        SymtechSounds.registerSounds();

        GuiManager.registerFactory(MetaTileEntityGuiFactory.INSTANCE);

        SymtechMetaTileEntities.init();
        SymtechCapabilities.init();

        SymtechMetaEntities.init();

        if (FMLLaunchHandler.side() == Side.CLIENT) {
            OBJLoader.INSTANCE.addDomain(MODID);
        }
    }

    @Mod.EventHandler
    public void onInit(@NotNull FMLInitializationEvent event) {
        proxy.load();
        SymtechCoverBehaviors.init();
    }
}
