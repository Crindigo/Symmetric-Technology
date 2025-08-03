package symtech.common.metatileentities.multi.electric;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import symtech.client.renderer.textures.SymtechTextures;
import symtech.common.blocks.BlockSinteringBrick;
import symtech.api.metatileentity.multiblock.SymtechPredicates;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.api.recipes.properties.SinterProperty;
import symtech.common.blocks.BlockSymtechMultiblockCasing;
import symtech.common.blocks.SymtechBlocks;

import javax.annotation.Nonnull;
import java.util.List;

public class MetaTileEntitySinteringOven extends RecipeMapMultiblockController {

    private boolean canUsePlasma;

    public MetaTileEntitySinteringOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, SymtechRecipeMaps.SINTERING_RECIPES);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySinteringOven(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("symtech.multiblock.sintering_oven.can_use_plasma",
                    new TextComponentTranslation(canUsePlasma ? "symtech.multiblocks.sintering_oven.use_plasma.affirmative" : "symtech.multiblocks.sintering_oven.use_plasma.negative")
                            .setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE))));
        }
        super.addDisplayText(textList);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart multiblockPart) {
        return SymtechTextures.ULV_STRUCTURAL_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return SymtechTextures.SINTERING_OVERLAY;
    }

    protected static IBlockState getCasingState() {
        return SymtechBlocks.MULTIBLOCK_CASING.getState(BlockSymtechMultiblockCasing.CasingType.ULV_STRUCTURAL_CASING);
    }

    @NotNull
    @Override
    public BlockPattern createStructurePattern() {
        // Different characters use common constraints. Copied from GCyM
        TraceabilityPredicate casingPredicate = states(getCasingState()).setMinGlobalLimited(33);

        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .aisle("     ", " BBB ", " B#B ", " BBB ", "     ")
                .aisle("FFFFF", "FBBBF", "FB#BF", " BBB ", "     ")
                .aisle("     ", " BBB ", " B#B ", " BBB ", "     ")
                .aisle("FFFFF", "FBBBF", "FB#BF", " BBB ", "     ")
                .aisle("     ", " BBB ", " B#B ", " BBB ", "     ")
                .aisle("FFFFF", "FBBBF", "FB#BF", " BBB ", "     ")
                .aisle("     ", " BBB ", " B#B ", " BBB ", "     ")
                .aisle("FFFFF", "FBBBF", "FB#BF", " BBB ", "     ")
                .aisle("     ", " BBB ", " B#B ", " BBB ", "     ")
                .aisle("DDDDD", "DDSDD", "DDDDD", "DDDDD", "DDDDD")
                .where('S', selfPredicate())
                .where('D', casingPredicate
                        .or(autoAbilities(true, true, false, true, true, false, false)))
                .where('C', casingPredicate
                        .or(autoAbilities(false, false, true, false, false, true, false)))
                .where('F', frames(Materials.Steel))
                .where('B', SymtechPredicates.sinteringBricks())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("SinteringBrickType");
        if (type instanceof BlockSinteringBrick.SinteringBrickType) {
            this.canUsePlasma = ((BlockSinteringBrick.SinteringBrickType) type).canResistPlasma;
        } else {
            this.canUsePlasma = false;
        }
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.canUsePlasma = false;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess) {
        return this.canUsePlasma || !(recipe.getProperty(SinterProperty.getInstance(), false));
    }

}
