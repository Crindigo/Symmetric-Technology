package symtech.loaders.recipes;

import gregtech.api.unification.ore.OrePrefix;
import symtech.common.item.SymtechMetaItems;

import static gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static symtech.api.recipes.SymtechRecipeMaps.PRESSURE_SWING_ADSORBER_RECIPES;

public class PSARecipes
{
    public static void init()
    {
        PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Carbon)
                .fluidInputs(Benzene.getFluid(1000))
                .outputs(SymtechMetaItems.CARBON_MOLECULAR_SIEVE.getItemStack())
                .duration(600)
                .EUt(60)
                .buildAndRegister();

        PRESSURE_SWING_ADSORBER_RECIPES.recipeBuilder()
                .notConsumable(SymtechMetaItems.CARBON_MOLECULAR_SIEVE.getItemStack())
                .fluidInputs(Air.getFluid(10000))
                .fluidOutputs(Nitrogen.getFluid(7800)) // 7808
                .fluidOutputs(Oxygen.getFluid(2000)) // 2095
                .duration(600)
                .EUt(16)
                .buildAndRegister();
    }
}
