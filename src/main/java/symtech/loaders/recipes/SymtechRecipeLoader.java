package symtech.loaders.recipes;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.ToolOreDict;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.common.item.SymtechMetaItems;
import symtech.loaders.SymtechMetaTileEntityLoader;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static symtech.api.recipes.SymtechRecipeMaps.BATCH_REACTOR_RECIPES;
import static symtech.common.item.SymtechMetaItems.MINERAL_WOOL;
import static symtech.common.materials.SymtechMaterials.*;
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

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(Lava.getFluid(1000))
                .notConsumable(stick, Steel, 1)
                .outputs(MINERAL_WOOL.getStackForm(16))
                .duration(500)
                .EUt(30)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, Indium, 3)
                .input(dustTiny, Silver)
                .output(dust, CryogenicSolder, 3)
                .duration(120)
                .EUt(GTValues.VA[2])
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.ELECTRIC_MOTOR_LV, 4)
                .input(rotor, Steel, 4)
                .input(stick, Steel, 4)
                .input(plate, Tin, 2)
                .input(Blocks.CHEST)
                .input("craftingToolButcheryKnife")
                .input(MetaItems.ROBOT_ARM_LV)
                .input("batteryLv")
                .fluidInputs(SolderingAlloy.getFluid(72))
                .output(SymtechMetaItems.DRONE_LV)
                .EUt(30)
                .duration(80)
                .buildAndRegister();

        BATCH_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Borax, 23)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, BoricAcid, 28)
                .fluidOutputs(SaltWater.getFluid(2000)) // 4 dustSalt + 5kL water
                .fluidOutputs(Water.getFluid(3000))
                .EUt(120)
                .duration(600)
                .buildAndRegister();

        BATCH_REACTOR_RECIPES.recipeBuilder()
                .input(dust, BoricAcid, 7)
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, BoronNitride, 2)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(120)
                .duration(150)
                .buildAndRegister();

        SymtechRecipeMaps.HOT_ISOSTATIC_PRESS.recipeBuilder()
                .input(dust, BoronNitride, 12)
                .output(SymtechMetaItems.NOZZLE_BORON_NITRIDE)
                .duration(600)
                .EUt(480)
                .buildAndRegister();
    }
}
