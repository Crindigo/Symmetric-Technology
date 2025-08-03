package symtech.common.metatileentities.multi.electric;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import symtech.api.metatileentity.multiblock.MetaTileEntityOrderedDT;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.client.renderer.textures.SymtechTextures;
import symtech.common.blocks.BlockSymtechMultiblockCasing;
import symtech.common.blocks.SymtechBlocks;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityHighTemperatureDistillationTower extends MetaTileEntityOrderedDT {

    public MetaTileEntityHighTemperatureDistillationTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, SymtechRecipeMaps.HIGH_TEMPERATURE_DISTILLATION);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHighTemperatureDistillationTower(metaTileEntityId);
    }

    @Override
    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("YSY", "YYY", "YYY")
                .aisle("XXX", "X#X", "XXX").setRepeatable(1, 11)
                .aisle("XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('Y', states(getCasingState())
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMaxGlobalLimited(1)))
                .where('X', states(getCasingState())
                        .or(metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.EXPORT_FLUIDS).stream()
                                .filter(mte->!(mte instanceof MetaTileEntityMultiFluidHatch))
                                .toArray(MetaTileEntity[]::new))
                                .setMinLayerLimited(1).setMaxLayerLimited(1))
                        .or(autoAbilities(true, false)))
                .where('#', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return SymtechTextures.SILICON_CARBIDE_CASING;
    }

    protected static IBlockState getCasingState() {
        return SymtechBlocks.MULTIBLOCK_CASING.getState(BlockSymtechMultiblockCasing.CasingType.SILICON_CARBIDE_CASING);
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return SymtechTextures.HTDT_OVERLAY;
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    public int getFluidOutputLimit() {
        return getOutputFluidInventory().getTanks();
    }
}
