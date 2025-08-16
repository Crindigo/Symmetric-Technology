package symtech.integration.supercritical;

import gregtech.api.modules.GregTechModule;
import gregtech.integration.IntegrationSubmodule;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import symtech.Symtech;
import symtech.api.SymtechLog;
import symtech.common.metatileentities.multi.electric.MetaTileEntityNaturalDraftCoolingTower;
import symtech.modules.SymtechModules;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static symtech.api.util.SymtechUtility.symId;
import static symtech.common.metatileentities.SymtechMetaTileEntities.NATURAL_DRAFT_COOLING_TOWER;

@GregTechModule(
        moduleID = SymtechModules.MODULE_SUPERCRITICAL,
        containerID = Symtech.MODID,
        modDependencies = "supercritical",
        name = "Symtech Supercritical Integration",
        description = "Symtech Supercritical Integration Module")
public class SupercriticalModule extends IntegrationSubmodule {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        SymtechLog.logger.info("Supercritical found. Enabling integration...");
        NATURAL_DRAFT_COOLING_TOWER = registerMetaTileEntity(
                15042, new MetaTileEntityNaturalDraftCoolingTower(symId("natural_draft_cooling_tower")));
    }
}
