package symtech.loaders.recipes;

import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static symtech.api.recipes.SymtechRecipeMaps.*;
import static symtech.common.materials.SymtechMaterials.*;

public class ElementRecipes
{
    public static void init() {
        // Bromine
        PUMPING_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidOutputs(Seawater.getFluid(1000))
                .duration(20)
                .biomes("ocean", "frozen_ocean", "deep_ocean")
                .EUt(16)
                .buildAndRegister();

        BUBBLE_COLUMN_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Seawater.getFluid(50))
                .fluidInputs(Chlorine.getFluid(25))
                .fluidOutputs(ChlorinatedSeawater.getFluid(50))
                .duration(5)
                .EUt(60)
                .buildAndRegister();

        VACUUM_CHAMBER.recipeBuilder()
                .fluidInputs(ChlorinatedSeawater.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .output(OrePrefix.dust, Salt)
                .fluidOutputs(Bromine.getFluid(40))
                .fluidOutputs(Water.getFluid(1000))
                .duration(100)
                .EUt(120)
                .buildAndRegister();

        // Iodine
        ORE_WASHER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(OrePrefix.crushedPurified, Saltpeter)
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(OrePrefix.dust, Saltpeter)
                .chancedOutput(OrePrefix.dust, SodiumIodate, 2500, 500)
                .duration(200)
                .EUt(120)
                .buildAndRegister();

        // Germanium
        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(OrePrefix.crushed, Sphalerite)
                .fluidInputs(SulfuricAcid.getFluid(100))
                .output(OrePrefix.crushedPurified, Sphalerite)
                .chancedOutput(OrePrefix.dust, Zinc, 5000, 500)
                .chancedOutput(OrePrefix.dust, Stone, 4000, 400)
                .chancedOutput(OrePrefix.dust, GermaniumDioxide, 1000, 100)
                .duration(200)
                .EUt(120)
                .buildAndRegister();

        TUBE_FURNACE_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, GermaniumDioxide, 3)
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(OrePrefix.dust, Germanium)
                .fluidOutputs(Steam.getFluid(2000))
                .duration(200)
                .EUt(120)
                .buildAndRegister();

        // Alternate rare earths. Europium uses Fusion, so don't add that to keep it gated.
        BATCH_REACTOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, RareEarth)
                .fluidInputs(SodiumHydroxideSolution.getFluid(3000))
                .output(OrePrefix.dust, RareEarthHydroxides)
                .output(OrePrefix.dust, SodiumHydroxide, 9)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .duration(300)
                .EUt(480)
                .buildAndRegister();

        // 3 HCl + RE(OH)3 -> RECl3 + 3 H2O
        BATCH_REACTOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, RareEarthHydroxides)
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .output(OrePrefix.dust, RareEarthChlorides)
                .fluidOutputs(Water.getFluid(3000))
                .duration(300)
                .EUt(480)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, RareEarthChlorides)
                .chancedOutput(OrePrefix.dustSmall, Praseodymium, 2500, 400)
                .chancedOutput(OrePrefix.dustSmall, Gadolinium, 2500, 400)
                .fluidOutputs(Chlorine.getFluid(3000))
                .duration(300)
                .EUt(480)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .input(OrePrefix.dust, SodiumHydroxide, 3)
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .duration(100)
                .EUt(7)
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .output(OrePrefix.dust, SodiumHydroxide, 3)
                .duration(100)
                .EUt(7)
                .buildAndRegister();
    }
}
