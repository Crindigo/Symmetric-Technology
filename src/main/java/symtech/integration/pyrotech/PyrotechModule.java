package symtech.integration.pyrotech;

import gregtech.api.modules.GregTechModule;
import gregtech.integration.IntegrationSubmodule;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import symtech.Symtech;
import symtech.api.SymtechLog;
import symtech.common.metatileentities.multi.primitive.MetaTileEntityPrimitiveSmelter;
import symtech.modules.SymtechModules;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static symtech.api.util.SuSyUtility.susyId;
import static symtech.common.metatileentities.SuSyMetaTileEntities.PRIMITIVE_SMELTER;

@GregTechModule(
        moduleID = SymtechModules.MODULE_PYROTECH,
        containerID = Symtech.MODID,
        modDependencies = "pyrotech",
        name = "SuSy Pyrotech Integration",
        description = "SuSy Pyrotech Integration Module")
public class PyrotechModule extends IntegrationSubmodule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        SymtechLog.logger.info("Pyrotech found. Enabling integration...");
        PRIMITIVE_SMELTER = registerMetaTileEntity(14800, new MetaTileEntityPrimitiveSmelter(susyId("primitive_smelter")));
    }
}
