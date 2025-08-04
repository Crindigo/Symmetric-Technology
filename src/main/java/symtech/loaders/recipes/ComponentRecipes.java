package symtech.loaders.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static symtech.common.item.SymtechMetaItems.*;

public class ComponentRecipes {
    public static void init() {
        ModHandler.addShapedRecipe("symtech:steam_motor", MOTOR_STEAM.getStackForm(),
                "PSP", "GSG", "TST",
                'P', new UnificationEntry(plate, Bronze),
                'S', new UnificationEntry(stick, Bronze),
                'G', new UnificationEntry(gearSmall, Bronze),
                'T', PISTON_STEAM.getStackForm());

        ModHandler.addShapedRecipe("symtech:steam_conveyor", CONVEYOR_STEAM.getStackForm(),
                "PPP", "MGM", "PPP",
                'P', new UnificationEntry(plate, Rubber),
                'M', MOTOR_STEAM.getStackForm(),
                'G', new UnificationEntry(gearSmall, Bronze));

        ModHandler.addShapedRecipe("symtech:steam_piston", PISTON_STEAM.getStackForm(),
                "PPP", "TSS", "ThG",
                'P', new UnificationEntry(plate, Bronze),
                'T', new UnificationEntry(pipeTinyFluid, Bronze),
                'S', new UnificationEntry(stick, Bronze),
                'G', new UnificationEntry(gearSmall, Bronze));

        ModHandler.addShapedRecipe("symtech:steam_pump", PUMP_STEAM.getStackForm(),
                "STR", "dFw", "RMF",
                'S', new UnificationEntry(screw, Bronze),
                'T', new UnificationEntry(rotor, Bronze),
                'R', new UnificationEntry(ring, Iron),
                'F', new UnificationEntry(pipeTinyFluid, Bronze),
                'M', MOTOR_STEAM.getStackForm());

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Bronze, 2)
                .input(stick, Bronze, 3)
                .input(gearSmall, Bronze, 2)
                .input(PISTON_STEAM, 2)
                .circuitMeta(1)
                .output(MOTOR_STEAM)
                .duration(100)
                .EUt(30)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(MOTOR_STEAM, 2)
                .input(gearSmall, Bronze)
                .fluidInputs(Rubber.getFluid(144 * 6))
                .output(CONVEYOR_STEAM)
                .duration(100)
                .EUt(30)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(screw, Bronze)
                .input(rotor, Bronze)
                .input(ring, Iron, 2)
                .input(MOTOR_STEAM)
                .input(pipeTinyFluid, Bronze, 2)
                .circuitMeta(1)
                .output(PUMP_STEAM)
                .duration(100)
                .EUt(30)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Bronze, 3)
                .input(stick, Bronze, 2)
                .input(gearSmall, Bronze)
                .input(pipeTinyFluid, Bronze, 2)
                .circuitMeta(1)
                .output(PISTON_STEAM)
                .duration(100)
                .EUt(30)
                .buildAndRegister();
    }
}
