package symtech.integration.theoneprobe;

import gregtech.api.modules.GregTechModule;
import gregtech.api.util.Mods;
import gregtech.integration.IntegrationSubmodule;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import symtech.Symtech;
import symtech.integration.theoneprobe.provider.DelegatorInfoProvider;
import symtech.integration.theoneprobe.provider.EvaporationPoolInfoProvider;
import symtech.integration.theoneprobe.provider.StrandShaperInfoProvider;
import symtech.modules.SymtechModules;

@GregTechModule(
        moduleID = SymtechModules.MODULE_TOP,
        containerID = Symtech.MODID,
        modDependencies = Mods.Names.THE_ONE_PROBE,
        name = "SuSy TheOneProbe Integration",
        description = "SuSy TheOneProbe Integration Module")
public class TheOneProbeModule extends IntegrationSubmodule {

    @Override
    public void init(FMLInitializationEvent event) {
        getLogger().info("TheOneProbe found. Enabling SuSy top integration...");
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new EvaporationPoolInfoProvider());
        oneProbe.registerProvider(new DelegatorInfoProvider());
        oneProbe.registerProvider(new StrandShaperInfoProvider());
    }
}
