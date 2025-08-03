package symtech.integration.bdsandm;

import funwayguy.bdsandm.core.BDSM;
import gregtech.api.cover.CoverRayTracer;
import gregtech.api.modules.GregTechModule;
import gregtech.common.items.tool.rotation.CustomBlockRotations;
import gregtech.common.items.tool.rotation.ICustomRotationBehavior;
import gregtech.integration.IntegrationSubmodule;
import net.minecraft.block.BlockDirectional;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import symtech.Symtech;
import symtech.api.SymtechLog;
import symtech.modules.SymtechModules;

@GregTechModule(
        moduleID = SymtechModules.MODULE_BDSAndM,
        containerID = Symtech.MODID,
        modDependencies = "bdsandm",
        name = "SuSy BDSAndM Integration",
        description = "SuSy BDSAndM Integration Module")
public class BDSAndMModule extends IntegrationSubmodule {

    public static final ICustomRotationBehavior BDSAndM_BARREL_BEHAVIOR = (state, world, pos, hitResult) -> {
        EnumFacing gridSide = CoverRayTracer.determineGridSideHit(hitResult);
        if (gridSide == null) return false;
        gridSide = gridSide.getOpposite(); // IDK what's happening here, blame the original author
        if (gridSide != state.getValue(BlockDirectional.FACING)) {
            world.setBlockState(pos, state.withProperty(BlockDirectional.FACING, gridSide));
            return true;
        }
        return false;
    };

    @Override
    public void init(FMLInitializationEvent event) {
        SymtechLog.logger.info("BDSAndM found. Enabling integration...");
        CustomBlockRotations.registerCustomRotation(BDSM.blockMetalBarrel, BDSAndM_BARREL_BEHAVIOR);
        CustomBlockRotations.registerCustomRotation(BDSM.blockWoodBarrel, BDSAndM_BARREL_BEHAVIOR);
        CustomBlockRotations.registerCustomRotation(BDSM.blockMetalCrate, BDSAndM_BARREL_BEHAVIOR);
        CustomBlockRotations.registerCustomRotation(BDSM.blockWoodCrate, BDSAndM_BARREL_BEHAVIOR);
    }
}
