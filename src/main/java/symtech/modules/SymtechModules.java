package symtech.modules;

import gregtech.api.modules.IModuleContainer;
import gregtech.api.modules.ModuleContainer;
import symtech.Symtech;

@ModuleContainer
public class SymtechModules implements IModuleContainer {

    public static final String MODULE_CORE = "symtech_core";
    public static final String MODULE_BDSAndM = "symtech_bdsandm_integration";
    public static final String MODULE_BAUBLES = "symtech_baubles_integration";
    public static final String MODULE_TOP = "symtech_top_integration";
    public static final String MODULE_PYROTECH = "symtech_pyrotech_integration";
    public static final String MODULE_LITTLETILES = "symtech_littletiles_integration";

    @Override
    public String getID() {
        return Symtech.MODID;
    }
}
