package symtech.loaders.recipes;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.category.RecipeCategories;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import net.minecraft.item.ItemStack;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechStoneVariantBlock;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Concrete;
import static gregtech.api.unification.material.Materials.Rubber;
import static gregtech.api.unification.ore.OrePrefix.block;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT;
import static symtech.common.blocks.SymtechStoneVariantBlock.StoneType.INDUSTRIAL_CONCRETE;
import static symtech.common.blocks.SymtechStoneVariantBlock.StoneType.MILITARY_CONCRETE;

public class StoneRecipes
{
    public static void init() {
        EnumMap<SymtechStoneVariantBlock.StoneVariant, List<ItemStack>> symtechVariantListMap = new EnumMap<>(SymtechStoneVariantBlock.StoneVariant.class);
        for (SymtechStoneVariantBlock.StoneVariant shape : SymtechStoneVariantBlock.StoneVariant.values()) {
            SymtechStoneVariantBlock block = SymtechBlocks.SYMTECH_STONE_BLOCKS.get(shape);
            symtechVariantListMap.put(shape,
                    Arrays.stream(SymtechStoneVariantBlock.StoneType.values())
                            .map(block::getItemVariant)
                            .collect(Collectors.toList()));
        }
        var stcobbles = symtechVariantListMap.get(SymtechStoneVariantBlock.StoneVariant.COBBLE);
        var stsmooths = symtechVariantListMap.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH);
        var stbricks  = symtechVariantListMap.get(SymtechStoneVariantBlock.StoneVariant.BRICKS);

        EnumMap<StoneVariantBlock.StoneVariant, List<ItemStack>> variantListMap = new EnumMap<>(StoneVariantBlock.StoneVariant.class);
        for (StoneVariantBlock.StoneVariant shape : StoneVariantBlock.StoneVariant.values()) {
            StoneVariantBlock block = MetaBlocks.STONE_BLOCKS.get(shape);
            variantListMap.put(shape,
                    Arrays.stream(StoneVariantBlock.StoneType.values())
                            .map(block::getItemVariant)
                            .collect(Collectors.toList()));
        }

        var cobbles = variantListMap.get(StoneVariantBlock.StoneVariant.COBBLE);
        var smooths = variantListMap.get(StoneVariantBlock.StoneVariant.SMOOTH);

        registerSmoothRecipe(stcobbles, stsmooths);
        registerCobbleRecipe(stsmooths, stcobbles);
        registerCobbleSmashingRecipe(stsmooths, stcobbles);
        registerCobbleSmashingRecipe(smooths, cobbles);
        registerBricksRecipe(stsmooths, stbricks);
        registerMacerationToStoneDustRecipe();

        var indConcrete = stsmooths.get(INDUSTRIAL_CONCRETE.ordinal()).copy();
        indConcrete.setCount(4);
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .input("frameGtSteel", 1)
                .fluidInputs(Concrete.getFluid(576))
                .outputs(indConcrete)
                .duration(100)
                .EUt(VA[1])
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(block, Concrete)
                .fluidInputs(Rubber.getFluid(GTValues.L / 8))
                .outputs(stsmooths.get(MILITARY_CONCRETE.ordinal()))
                .duration(20)
                .EUt(2)
                .buildAndRegister();
    }

    private static void registerCobbleRecipe(List<ItemStack> smoothStack, List<ItemStack> cobbleStack) {
        for (int i = 0; i < smoothStack.size(); i++) {
            FORMING_PRESS_RECIPES.recipeBuilder()
                    .inputs(smoothStack.get(i))
                    .outputs(cobbleStack.get(i))
                    .duration(12).EUt(4).buildAndRegister();
        }
    }

    private static void registerSmoothRecipe(List<ItemStack> roughStack, List<ItemStack> smoothStack) {
        for (int i = 0; i < roughStack.size(); i++) {
            ModHandler.addSmeltingRecipe(roughStack.get(i), smoothStack.get(i), 0.1f);

            EXTRUDER_RECIPES.recipeBuilder()
                    .inputs(roughStack.get(i))
                    .notConsumable(SHAPE_EXTRUDER_BLOCK.getStackForm())
                    .outputs(smoothStack.get(i))
                    .duration(24).EUt(8).buildAndRegister();
        }
    }


    private static void registerCobbleSmashingRecipe(List<ItemStack> smoothStack, List<ItemStack> cobbleStack) {
        for (int i = 0; i < smoothStack.size(); i++) {

            ModHandler.addShapedRecipe(smoothStack.get(i).getDisplayName() + "_hammer_smashing", cobbleStack.get(i), "hS", 'S', smoothStack.get(i));
        }
    }

    private static void registerMacerationToStoneDustRecipe() {
        for (SymtechStoneVariantBlock.StoneType stoneType : SymtechStoneVariantBlock.StoneType.values()) {
            MACERATOR_RECIPES.recipeBuilder()
                    .inputs(SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(stoneType))
                    .output(dust, stoneType.getMaterial())
                    .category(RecipeCategories.MACERATOR_RECYCLING)
                    .buildAndRegister();
            MACERATOR_RECIPES.recipeBuilder()
                    .inputs(SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.COBBLE).getItemVariant(stoneType))
                    .output(dust, stoneType.getMaterial())
                    .category(RecipeCategories.MACERATOR_RECYCLING)
                    .buildAndRegister();
        }
    }

    private static void registerBricksRecipe(List<ItemStack> smooths, List<ItemStack> bricks) {
        for (int i = 0; i < smooths.size(); i++) {
            var four = bricks.get(i).copy();
            four.setCount(4);
            ModHandler.addShapedRecipe(
                    smooths.get(i).getDisplayName() + "_bricks", four,
                    "SS", "SS",
                    'S', smooths.get(i));

            EXTRUDER_RECIPES.recipeBuilder()
                    .inputs(smooths.get(i))
                    .notConsumable(SHAPE_EXTRUDER_INGOT.getStackForm())
                    .outputs(bricks.get(i))
                    .duration(24)
                    .EUt(8)
                    .buildAndRegister();
        }
    }
}
