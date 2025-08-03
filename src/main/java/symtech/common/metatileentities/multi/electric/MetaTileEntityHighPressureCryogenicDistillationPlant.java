package symtech.common.metatileentities.multi.electric;

import gregtech.api.capability.impl.DistillationTowerLogicHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import symtech.api.capability.impl.ExtendedDTLogicHandler;
import symtech.api.metatileentity.multiblock.MetaTileEntityOrderedDT;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.client.renderer.textures.SymtechTextures;
import symtech.common.blocks.BlockSymtechMultiblockCasing;
import symtech.common.blocks.SymtechBlocks;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityHighPressureCryogenicDistillationPlant extends MetaTileEntityOrderedDT {
    public MetaTileEntityHighPressureCryogenicDistillationPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, SymtechRecipeMaps.HIGH_PRESSURE_CRYOGENIC_DISTILLATION);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHighPressureCryogenicDistillationPlant(this.metaTileEntityId);
    }

    @Override
    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("CCC", "CCC", "CCC")
                .aisle("CSC", "CFC", "CCC")
                .aisle("XXX", "XFX", "XXX").setRepeatable(1, 16)
                .aisle("DDD", "DDD", "DDD")
                .where('S', this.selfPredicate())
                .where('C', states(getCasingState())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMaxGlobalLimited(1))
                        .or(autoAbilities(true, false).setExactLimit(1)))
                .where('F', states(SymtechBlocks.MULTIBLOCK_CASING.getState(BlockSymtechMultiblockCasing.CasingType.SIEVE_TRAY)))
                .where('X', states(getCasingState())
                        .or(metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.EXPORT_FLUIDS).stream()
                                .filter(mte -> !(mte instanceof MetaTileEntityMultiFluidHatch))
                                .toArray(MetaTileEntity[]::new))
                                .setMaxLayerLimited(1))
                        .or(metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.IMPORT_FLUIDS).stream()
                                .filter(mte -> !(mte instanceof MetaTileEntityMultiFluidHatch))
                                .toArray(MetaTileEntity[]::new))
                                .setMaxLayerLimited(4)))
                .where('D', states(getCasingState()))
                .where('#', air())
                .build();
    }

    @Override
    @NotNull
    public DistillationTowerLogicHandler createHandler() {
        return new ExtendedDTLogicHandler(this, 2, ignored -> 1);
    }


    protected static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.ALUMINIUM_FROSTPROOF);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.FROST_PROOF_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return SymtechTextures.HPCDT_OVERLAY;
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }
}
