package symtech.loaders.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.loaders.recipe.handlers.OreRecipeHandler;
import net.minecraft.item.ItemStack;
import symtech.api.unification.ore.SymtechOrePrefix;

public class SymtechOreRecipeHandler {

    public static void init(){
        if (ConfigHolder.worldgen.allUniqueStoneTypes) {
            SymtechOrePrefix.oreGabbro.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreGneiss.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreLimestone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.orePhyllite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreQuartzite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreShale.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreSlate.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreSoapstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreKimberlite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            SymtechOrePrefix.oreAnorthosite.addProcessingHandler(PropertyKey.ORE, SymtechOreRecipeHandler::processDoubleOre);
        }
    }

    public static void processDoubleOre(OrePrefix orePrefix, Material material, OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.gem, byproductMaterial);
        if (byproductStack.isEmpty()) {
            byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
        }

        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material);
        Material smeltingMaterial = property.getDirectSmeltResult() == null ? material : property.getDirectSmeltResult();
        double amountOfCrushedOre = (double)property.getOreMultiplier();
        ItemStack ingotStack;
        if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        } else if (smeltingMaterial.hasProperty(PropertyKey.GEM)) {
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);
        } else {
            ingotStack = OreDictUnifier.get(OrePrefix.dust, smeltingMaterial);
        }

        int oreTypeMultiplier = orePrefix != OrePrefix.oreNetherrack && orePrefix != OrePrefix.oreEndstone ? 1 : 2;
        ingotStack.setCount(ingotStack.getCount() * property.getOreMultiplier() * oreTypeMultiplier);
        crushedStack.setCount(crushedStack.getCount() * property.getOreMultiplier());
        if (!crushedStack.isEmpty()) {
            RecipeBuilder<?> builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder) RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()).input(orePrefix, material)).duration(10)).EUt(16);
            if (material.hasProperty(PropertyKey.GEM) && !OreDictUnifier.get(OrePrefix.gem, material).isEmpty()) {
                builder.outputs(new ItemStack[]{GTUtility.copy((int)Math.ceil(amountOfCrushedOre) * oreTypeMultiplier, OreDictUnifier.get(OrePrefix.gem, material, crushedStack.getCount()))});
            } else {
                builder.outputs(new ItemStack[]{GTUtility.copy((int)Math.ceil(amountOfCrushedOre) * oreTypeMultiplier, crushedStack)});
            }

            builder.buildAndRegister();
            builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.MACERATOR_RECIPES.recipeBuilder()).input(orePrefix, material)).outputs(new ItemStack[]{GTUtility.copy((int)Math.round(amountOfCrushedOre) * 2 * oreTypeMultiplier, crushedStack)})).chancedOutput(byproductStack, 1400, 850)).duration(400);

            for(MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material.hasProperty(PropertyKey.DUST)) {
                    ItemStack dustStack = OreDictUnifier.getGem(secondaryMaterial);
                    builder.chancedOutput(dustStack, 6700, 800);
                }
            }

            builder.buildAndRegister();
        }

        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack, 0.5F);
        }

    }

    private static boolean doesMaterialUseNormalFurnace(Material material) {
        return !material.hasProperty(PropertyKey.BLAST);
    }

}
