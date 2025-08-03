package symtech.common.metatileentities.multi.electric;

import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.client.renderer.textures.SymtechTextures;
import symtech.common.blocks.BlockMetallurgy;
import symtech.common.blocks.BlockSymtechMultiblockCasing;
import symtech.common.blocks.SymtechBlocks;

import java.util.function.Supplier;

import static symtech.api.blocks.VariantDirectionalRotatableBlock.FACING;

public class MetaTileEntityHotIsostaticPress extends RecipeMapMultiblockController {
    public MetaTileEntityHotIsostaticPress(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, SymtechRecipeMaps.HOT_ISOSTATIC_PRESS);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("  SSS  ", "  SFS  ", "  SFS  ", "  SFS  ", "  SFS  ", "  SFS  ", "  SSS  ")
                .aisle(" SSSSS ", " SIIIS ", " SIIIS ", " SIIIS ", " SIIIS ", " SIIIS ", " SSSSS ")
                .aisle("SSSSSSS", "SIIIIIS", "SICCCIS", "SICCCIS", "SICCCIS", "SIIIIIS", "SSSSSSS")
                .aisle("SSSPSSS", "SIIHIIS", "SICXCIS", "SICXCIS", "SICXCIS", "SIIhIIS", "SSSPSSS")
                .aisle("SSSPSSS", "SIIIIIS", "SICXCIS", "SICXCIS", "SICXCIS", "SIIPIIS", "SSSPSSS")
                .aisle(" SSPSS ", " SIIIS ", " SIIIS ", " SIIIS ", " SIIIS ", " SIPIS ", " SSPSS ")
                .aisle("  SPS  ", "  SOS  ", "  SPS  ", "  SPS  ", "  SPS  ", "  SPS  ", "  SPS  ")
                .where(' ', any())
                .where('O', selfPredicate())
                .where('S', states(SymtechBlocks.MULTIBLOCK_CASING.getState(BlockSymtechMultiblockCasing.CasingType.SILICON_CARBIDE_CASING)).setMinGlobalLimited(27).or(autoAbilities()))
                .where('I', states(SymtechBlocks.MULTIBLOCK_CASING.getState(BlockSymtechMultiblockCasing.CasingType.SILICON_CARBIDE_CASING)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('C', states(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.NICHROME)))
                .where('X', air())
                .where('H', hydraulicOrientation(RelativeDirection.UP))
                .where('h', hydraulicOrientation(RelativeDirection.DOWN))
                .where('F', frames(getFrameMaterial()))
                .build();
    }

    protected TraceabilityPredicate hydraulicOrientation(RelativeDirection direction) {
        EnumFacing facing = getRelativeFacing(direction);

        Supplier<BlockInfo[]> supplier = () -> new BlockInfo[]{new BlockInfo(hydraulicState().withProperty(FACING, facing))};
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            if (!(state.getBlock() instanceof BlockMetallurgy)) return false;

            // auto-correct rotor orientation
            if (state != hydraulicState().withProperty(FACING, facing)) {
                getWorld().setBlockState(blockWorldState.getPos(), hydraulicState().withProperty(FACING, facing));
            }
            return true;
        }, supplier);
    }

    protected EnumFacing getRelativeFacing(RelativeDirection dir) {
        return dir.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped());
    }

    private IBlockState hydraulicState() {
        return SymtechBlocks.METALLURGY.getState(BlockMetallurgy.BlockMetallurgyType.HYDRAULIC_CYLINDER);
    }

    protected Material getFrameMaterial() {
        Material mat = GregTechAPI.materialManager.getMaterial("incoloy_nine_zero_eight");
        if (mat == null) mat = Materials.Invar;
        return mat;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return SymtechTextures.SILICON_CARBIDE_CASING;
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.FORMING_PRESS_OVERLAY;
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityHotIsostaticPress(metaTileEntityId);
    }
}
