package symtech.loaders.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.stack.UnificationEntry;
import symtech.loaders.SymtechMetaTileEntityLoader;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static symtech.common.metatileentities.SymtechMetaTileEntities.*;

public class SymtechRecipeLoader {

    public static void init() {
        SymtechMetaTileEntityLoader.init();
        FridgeRecipes.init();
        CoagulationRecipes.init();
        VulcanizationRecipes.init();
        SymtechOreRecipeHandler.init();
        SymtechMaterialRecipeHandler.init();
        ComponentRecipes.init();
        MachineRecipes.init();
        QuarryRecipes.init();
        ElementRecipes.init();
        PSARecipes.init();
        StoneRecipes.init();
        registerDrumRecipes();
    }

    private static void registerDrumRecipes() {
        ModHandler.addShapedRecipe("drum_lead",
                LEAD_DRUM.getStackForm(),
                " h ", "PSP", "PSP",
                'P', new UnificationEntry(plate, Lead),
                'S', new UnificationEntry(stickLong, Lead));

        ModHandler.addShapedRecipe("drum_brass",
                BRASS_DRUM.getStackForm(),
                " h ", "PSP", "PSP",
                'P', new UnificationEntry(plate, Brass),
                'S', new UnificationEntry(stickLong, Brass));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(stickLong, Lead, 2).input(plate, Lead, 4)
                .outputs(LEAD_DRUM.getStackForm()).duration(200).circuitMeta(2).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(stickLong, Brass, 2).input(plate, Brass, 4)
                .outputs(BRASS_DRUM.getStackForm()).duration(200).circuitMeta(2).buildAndRegister();

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_lead",
                LEAD_DRUM.getStackForm(), LEAD_DRUM.getStackForm());

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_brass",
                BRASS_DRUM.getStackForm(), BRASS_DRUM.getStackForm());

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_pe",
                PE_CAN.getStackForm(), PE_CAN.getStackForm());

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_pp",
                PP_CAN.getStackForm(), PP_CAN.getStackForm());

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_ptfe",
                PTFE_CAN.getStackForm(), PTFE_CAN.getStackForm());

        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_uhmwpe",
                UHMWPE_CAN.getStackForm(), UHMWPE_CAN.getStackForm());
    }
}
