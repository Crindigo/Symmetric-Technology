package symtech.loaders.recipes;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.unification.material.Materials.*;
import static symtech.api.recipes.SymtechRecipeMaps.QUARRY_RECIPES;
import static symtech.common.materials.SymtechMaterials.*;

public class QuarryRecipes {
    public static void init() {
        // GABBRO, GNEISS, LIMESTONE, PHYLLITE, QUARTZITE, SHALE, SLATE, SOAPSTONE, KIMBERLITE, ANORTHOSITE
        makeInfinite(Gabbro, 60, 0);
        makeInfinite(Limestone, 60, 0);
        makeInfinite(Shale, 60, 0);

        // beneath (using nether ID)
        makeInfinite(Gneiss, 240, -1);
        makeInfinite(Phyllite, 240, -1);
        makeInfinite(Quartzite, 240, -1);
        makeInfinite(Slate,240, -1);
        makeInfinite(Soapstone, 240, -1);

        // nether
        makeInfinite(Kimberlite, 240, -1);
        makeInfinite(Netherrack, 240, -1);

        // intended for the moon?
        makeInfinite(Anorthosite, 960, 1);
        makeInfinite(Endstone, 960, 1);

        // Collections per dimension
        QUARRY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .dimension(0)
                .chancedOutput(OrePrefix.stone, Andesite, 10000 / 6, 0)
                .chancedOutput(OrePrefix.stone, Granite, 10000 / 6, 0)
                .chancedOutput(OrePrefix.stone, Diorite, 10000 / 6, 0)
                .chancedOutput(OrePrefix.stone, Gabbro, 10000 / 6, 0)
                .chancedOutput(OrePrefix.stone, Limestone, 10000 / 6, 0)
                .chancedOutput(OrePrefix.stone, Shale, 10000 / 6, 0)
                .duration(20)
                .EUt(60)
                .buildAndRegister();

        QUARRY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .dimension(0)
                .chancedOutput(OrePrefix.stone, Marble, 5000, 0)
                .chancedOutput(OrePrefix.stone, Basalt, 5000, 0)
                .duration(20)
                .EUt(240)
                .buildAndRegister();

        QUARRY_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .dimension(0)
                .chancedOutput(OrePrefix.stone, GraniteBlack, 5000, 0)
                .chancedOutput(OrePrefix.stone, GraniteRed, 5000, 0)
                .duration(20)
                .EUt(960)
                .buildAndRegister();

        QUARRY_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .dimension(-1)
                .chancedOutput(OrePrefix.stone, Kimberlite, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Gneiss, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Phyllite, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Quartzite, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Slate, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Soapstone, 10000 / 7, 0)
                .chancedOutput(OrePrefix.stone, Netherrack, 10000 / 7, 0)
                .duration(20)
                .EUt(240)
                .buildAndRegister();

        QUARRY_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .dimension(1)
                .chancedOutput(OrePrefix.stone, Anorthosite, 5000, 0)
                .chancedOutput(OrePrefix.stone, Endstone, 5000, 0)
                .duration(20)
                .EUt(960)
                .buildAndRegister();
    }

    private static void makeInfinite(Material material, int eut, int ...dimensions) {
        QUARRY_RECIPES.recipeBuilder()
                .notConsumable(OrePrefix.stone, material)
                .output(OrePrefix.stone, material)
                .duration(16)
                .EUt(eut)
                .dimension(dimensions)
                .buildAndRegister();
    }
}
