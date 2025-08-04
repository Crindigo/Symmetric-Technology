package symtech.loaders.recipes;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockSteamCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.MetaTileEntityLoader;
import symtech.common.materials.SymtechMaterials;
import symtech.common.metatileentities.SymtechMetaTileEntities;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static symtech.common.item.SymtechMetaItems.PUMP_STEAM;
import static symtech.common.metatileentities.SymtechMetaTileEntities.*;

public class MachineRecipes {
    public static void init() {
        ModHandler.addShapedRecipe("symtech:reverberatory_furnace",
                REVERBERATORY_FURNACE.getStackForm(),
                "hPS", "GBT", "dPS",
                'P', new UnificationEntry(plate, WroughtIron),
                'S', new UnificationEntry(screw, WroughtIron),
                'G', new UnificationEntry(block, Glass),
                'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS),
                'T', new UnificationEntry(stick, WroughtIron));


        initSteamMachines();
        initElectricMachines();
        initLogisticsMachines();
        initMultiblocks();
    }

    private static void initSteamMachines() {
        // Latex Collector

        ModHandler.addShapedRecipe("symtech:latex_extractor.bronze",
                STEAM_LATEX_COLLECTOR[0].getStackForm(),
                " D ", "GRG", "PCP",
                'D', new UnificationEntry(toolHeadDrill, Steel),
                'G', new UnificationEntry(block, Glass),
                'R', new UnificationEntry(rotor, Steel),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("symtech:latex_extractor.steel",
                STEAM_LATEX_COLLECTOR[1].getStackForm(),
                "FPF", "SCS", "WWW",
                'F', new UnificationEntry(pipeSmallFluid, Steel),
                'P', PUMP_STEAM.getStackForm(),
                'S', new UnificationEntry(plate, Steel),
                'C', STEAM_LATEX_COLLECTOR[0].getStackForm(),
                'W', new UnificationEntry(plate, WroughtIron));

        // Mixer

        ModHandler.addShapedRecipe("symtech:mixer.bronze",
                STEAM_MIXER[0].getStackForm(),
                "GRG", "GSG", "PCP",
                'G', new UnificationEntry(block, Glass),
                'R', new UnificationEntry(rotor, Bronze),
                'S', new UnificationEntry(stick, Bronze),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("symtech:mixer.steel",
                STEAM_MIXER[1].getStackForm(),
                "PSP", "WCW", "PPP",
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'S', new UnificationEntry(plate, Steel),
                'W', new UnificationEntry(plate, WroughtIron),
                'C', STEAM_MIXER[0].getStackForm());


        // Roaster

        ModHandler.addShapedRecipe("symtech:roaster.bronze",
                STEAM_ROASTER[0].getStackForm(),
                "PRP", "PBP", "PCP",
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'R', new UnificationEntry(rotor, Bronze),
                'B', new UnificationEntry(plate, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL));

        ModHandler.addShapedRecipe("symtech:roaster.steel",
                STEAM_ROASTER[1].getStackForm(),
                "WWW", "SCS", "PPP",
                'W', new UnificationEntry(plate, WroughtIron),
                'S', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'C', STEAM_ROASTER[0].getStackForm());

        // Vacuum Chamber

        ModHandler.addShapedRecipe("symtech:vacuum_chamber.bronze",
                STEAM_VACUUM_CHAMBER[0].getStackForm(),
                "PPP", "UCG", "PPP",
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'U', PUMP_STEAM.getStackForm(),
                'G', new UnificationEntry(block, Glass),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("symtech:vacuum_chamber.steel",
                STEAM_VACUUM_CHAMBER[1].getStackForm(),
                "SPS", "PCP", "WPW",
                'S', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'W', new UnificationEntry(plate, WroughtIron),
                'C', STEAM_VACUUM_CHAMBER[0].getStackForm());

        // Vulcanizing Press

        ModHandler.addShapedRecipe("symtech:vulcanizing_press.bronze",
                STEAM_VULCANIZING_PRESS[0].getStackForm(),
                "STS", "PLP", "PCP",
                'S', new UnificationEntry(springSmall, Steel),
                'T', new UnificationEntry(stick, Steel),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'L', new UnificationEntry(plate, Steel),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL));

        ModHandler.addShapedRecipe("symtech:vulcanizing_press.steel",
                STEAM_VULCANIZING_PRESS[1].getStackForm(),
                "SSS", "PCP", "WWW",
                'S', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'W', new UnificationEntry(plate, WroughtIron),
                'C', STEAM_VULCANIZING_PRESS[0].getStackForm());
    }

    private static void initElectricMachines() {
        MetaTileEntityLoader.registerMachineRecipe(BATCH_REACTOR,
                "CPC", "EHE", "ICI",
                'C', CraftingComponent.CABLE,
                'P', CraftingComponent.PUMP,
                'E', CraftingComponent.PIPE_REACTOR,
                'I', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(BUBBLE_COLUMN_REACTOR,
                "ETE", "CPC", "IHI",
                'E', CraftingComponent.PIPE_REACTOR,
                'T', CraftingComponent.PIPE_LARGE,
                'C', CraftingComponent.CABLE,
                'P', CraftingComponent.PUMP,
                'I', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(CONTINUOUS_STIRRED_TANK_REACTOR,
                "ERE", "CMC", "IHI",
                'E', CraftingComponent.PIPE_REACTOR,
                'R', CraftingComponent.ROTOR,
                'C', CraftingComponent.CABLE,
                'M', CraftingComponent.MOTOR,
                'I', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(CRYSTALLIZER,
                "PGP", "EHE", "IUI",
                'P', CraftingComponent.PLATE,
                'G', CraftingComponent.GLASS,
                'E', CraftingComponent.PIPE_REACTOR,
                'H', CraftingComponent.HULL,
                'I', CraftingComponent.CIRCUIT,
                'U', CraftingComponent.PUMP);

        MetaTileEntityLoader.registerMachineRecipe(CVD,
                "UGG", "HSP", "ICC",
                'U', CraftingComponent.PUMP,
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL,
                'S', CraftingComponent.SPRING,
                'P', CraftingComponent.PIPE_LARGE,
                'I', CraftingComponent.CIRCUIT,
                'C', CraftingComponent.CABLE);

        MetaTileEntityLoader.registerMachineRecipe(DRYER,
                "CIC", "SHS", "CIC",
                'C', CraftingComponent.CABLE,
                'I', CraftingComponent.CIRCUIT,
                'S', CraftingComponent.SPRING,
                'H', CraftingComponent.HULL);

        CraftingComponent.Component electrodes = new CraftingComponent.Component(Stream.of(new Object[][] {
                { 0, new UnificationEntry(wireFine, RedAlloy) },
                { 1, new UnificationEntry(wireFine, Steel) },
                { 2, new UnificationEntry(wireFine, BlackSteel) }, // graphite electrode
                { 3, new UnificationEntry(wireFine, Platinum) },
                { 4, new UnificationEntry(wireFine, Palladium) }, // titanium
                { 5, new UnificationEntry(wireFine, TungstenSteel) }, // tungsten
                { 6, new UnificationEntry(wireFine, Osmiridium) },
                { 7, new UnificationEntry(wireFine, NiobiumTitanium) },
                { 8, new UnificationEntry(wireFine, Naquadah) },
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        MetaTileEntityLoader.registerMachineRecipe(ELECTROSTATIC_SEPARATOR,
                "WEW", "CHC", "WEW",
                'W', CraftingComponent.WIRE_QUAD,
                'E', electrodes,
                'C', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(FIXED_BED_REACTOR,
                " I ", "TPT", "CHC",
                'I', CraftingComponent.CIRCUIT,
                'T', CraftingComponent.PIPE_LARGE,
                'P', CraftingComponent.PUMP,
                'C', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(FLUID_COMPRESSOR,
                "GTG", "PHU", "ICI",
                'G', CraftingComponent.GLASS,
                'T', CraftingComponent.PISTON,
                'P', CraftingComponent.PIPE_LARGE,
                'H', CraftingComponent.HULL,
                'U', CraftingComponent.PUMP,
                'I', CraftingComponent.CIRCUIT,
                'C', CraftingComponent.CABLE);

        MetaTileEntityLoader.registerMachineRecipe(FLUID_DECOMPRESSOR,
                "GTG", "UHP", "ICI",
                'G', CraftingComponent.GLASS,
                'T', CraftingComponent.PISTON,
                'P', CraftingComponent.PIPE_LARGE,
                'H', CraftingComponent.HULL,
                'U', CraftingComponent.PUMP,
                'I', CraftingComponent.CIRCUIT,
                'C', CraftingComponent.CABLE);

        MetaTileEntityLoader.registerMachineRecipe(INCINERATOR,
                "IUI", "CHC", "SSS",
                'I', CraftingComponent.CIRCUIT,
                'U', CraftingComponent.PUMP,
                'C', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL,
                'S', CraftingComponent.SPRING);

        MetaTileEntityLoader.registerMachineRecipe(ION_EXCHANGE_COLUMN,
                " U ", "GPG", "IHI",
                'U', CraftingComponent.PUMP,
                'G', CraftingComponent.GLASS,
                'P', CraftingComponent.PIPE_LARGE,
                'I', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(ION_IMPLANTER,
                "IGI", "MHM", "CSC",
                'I', CraftingComponent.CIRCUIT,
                'G', CraftingComponent.GLASS,
                'M', CraftingComponent.STICK_MAGNETIC,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CABLE,
                'S', CraftingComponent.SPRING);

        MetaTileEntityLoader.registerMachineRecipe(LATEX_COLLECTOR,
                "IDI", "GPG", "CHC",
                'I', CraftingComponent.CIRCUIT,
                'D', new UnificationEntry(toolHeadDrill, Steel),
                'G', new UnificationEntry(block, Glass),
                'P', CraftingComponent.PUMP,
                'C', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL);

        MetaTileEntityLoader.registerMachineRecipe(POLISHING_MACHINE,
                "AIU", "GHG", "CMC",
                'A', CraftingComponent.ROBOT_ARM,
                'I', CraftingComponent.CIRCUIT,
                'U', CraftingComponent.PUMP,
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CABLE,
                'M', CraftingComponent.MOTOR);

        MetaTileEntityLoader.registerMachineRecipe(ROASTER,
                "IRI", "WHW", "CWC",
                'I', CraftingComponent.CIRCUIT,
                'R', CraftingComponent.ROTOR,
                'W', CraftingComponent.COIL_HEATING_DOUBLE,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CABLE);

        MetaTileEntityLoader.registerMachineRecipe(TEXTILE_SPINNER,
                "ICI", "UHM", "CMM",
                'I', CraftingComponent.CIRCUIT,
                'C', CraftingComponent.CABLE,
                'U', CraftingComponent.PUMP,
                'H', CraftingComponent.HULL,
                'M', CraftingComponent.MOTOR);

        MetaTileEntityLoader.registerMachineRecipe(TRICKLE_BED_REACTOR,
                "CUC", "PHP", "IUI",
                'C', CraftingComponent.CABLE,
                'U', CraftingComponent.PUMP,
                'P', CraftingComponent.PIPE_LARGE,
                'H', CraftingComponent.HULL,
                'I', CraftingComponent.CIRCUIT);

        MetaTileEntityLoader.registerMachineRecipe(TUBE_FURNACE,
                "IGG", "HSP", "CVC",
                'I', CraftingComponent.CIRCUIT,
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL,
                'S', CraftingComponent.SPRING,
                'P', CraftingComponent.PIPE_LARGE,
                'C', CraftingComponent.CABLE,
                'V', CraftingComponent.CONVEYOR);

        MetaTileEntityLoader.registerMachineRecipe(UV_LIGHT_BOX,
                "CEC", "IHI", "PPP",
                'C', CraftingComponent.CABLE,
                'E', CraftingComponent.EMITTER,
                'I', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL,
                'P', CraftingComponent.PLATE);

        MetaTileEntityLoader.registerMachineRecipe(VACUUM_CHAMBER,
                "PPP", "UHU", "CIC",
                'P', CraftingComponent.PLATE,
                'U', CraftingComponent.PUMP,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CABLE,
                'I', CraftingComponent.CIRCUIT);

        MetaTileEntityLoader.registerMachineRecipe(VULCANIZING_PRESS,
                "CPC", "WHW", "CIC",
                'C', CraftingComponent.CABLE,
                'P', CraftingComponent.PISTON,
                'W', CraftingComponent.COIL_HEATING_DOUBLE,
                'H', CraftingComponent.HULL,
                'I', CraftingComponent.CIRCUIT);

        MetaTileEntityLoader.registerMachineRecipe(WEAPONS_FACTORY,
                "IAI", "VHV", "CIC",
                'I', CraftingComponent.CIRCUIT,
                'A', CraftingComponent.ROBOT_ARM,
                'V', CraftingComponent.CONVEYOR,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CABLE);

        MetaTileEntityLoader.registerMachineRecipe(ZONE_REFINER,
                "SPS", "CHC", "IVI",
                'S', CraftingComponent.SPRING,
                'P', CraftingComponent.PIPE_LARGE,
                'C', CraftingComponent.CABLE_QUAD,
                'H', CraftingComponent.HULL,
                'I', CraftingComponent.CIRCUIT,
                'V', CraftingComponent.CONVEYOR);
    }

    private static void initLogisticsMachines() {

        ModHandler.addShapedRecipe("symtech:extender.inv", INV_EXTENDER.getStackForm(),
                " hP", " H ", "Pw ",
                'P', new UnificationEntry(pipeNormalItem, Nickel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:extender.tank", TANK_EXTENDER.getStackForm(),
                "Fh ", " H ", " wF",
                'F', new UnificationEntry(pipeNormalFluid, Steel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:extender.inv_tank", INV_TANK_EXTENDER.getStackForm(),
                "FhP", " H ", "PwF",
                'F', new UnificationEntry(pipeNormalFluid, Steel),
                'P', new UnificationEntry(pipeNormalItem, Nickel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:extender.universal", UNIVERSAL_EXTENDER.getStackForm(),
                "FRP", "IHG", "PSF",
                'F', new UnificationEntry(pipeNormalFluid, Aluminium),
                'R', CraftingComponent.ROTOR.getIngredient(GTValues.MV),
                'P', new UnificationEntry(pipeNormalItem, Electrum),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'G', new UnificationEntry(gear, Aluminium),
                'S', new UnificationEntry(spring, Aluminium),
                'H', MetaTileEntities.HULL[GTValues.MV].getStackForm());

        ModHandler.addShapedRecipe("symtech:bridge.inv", INV_BRIDGE.getStackForm(),
                "hP ", " H ", " Pw",
                'P', new UnificationEntry(pipeNormalItem, Nickel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:bridge.tank", TANK_BRIDGE.getStackForm(),
                "h  ", "FHF", "  w",
                'F', new UnificationEntry(pipeNormalFluid, Steel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:bridge.inv_tank", INV_TANK_BRIDGE.getStackForm(),
                "hP ", "FHF", " Pw",
                'F', new UnificationEntry(pipeNormalFluid, Steel),
                'P', new UnificationEntry(pipeNormalItem, Nickel),
                'H', MetaTileEntities.HULL[GTValues.LV].getStackForm());

        ModHandler.addShapedRecipe("symtech:bridge.universal", UNIVERSAL_BRIDGE.getStackForm(),
                "SPR", "FHF", "IPG",
                'F', new UnificationEntry(pipeNormalFluid, Aluminium),
                'R', CraftingComponent.ROTOR.getIngredient(GTValues.MV),
                'P', new UnificationEntry(pipeNormalItem, Electrum),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'G', new UnificationEntry(gear, Aluminium),
                'S', new UnificationEntry(spring, Aluminium),
                'H', MetaTileEntities.HULL[GTValues.MV].getStackForm());

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Nickel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(2)
                .output(INV_EXTENDER)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Steel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(2)
                .output(TANK_EXTENDER)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Nickel, 2)
                .input(pipeNormalFluid, Steel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(3)
                .output(INV_TANK_EXTENDER)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Electrum, 2)
                .input(pipeNormalFluid, Aluminium, 2)
                .input(rotor, Bronze)
                .input(gear, Aluminium)
                .input(spring, Aluminium)
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(MetaTileEntities.HULL[GTValues.MV])
                .circuitMeta(3)
                .output(UNIVERSAL_EXTENDER)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Nickel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(4)
                .output(INV_BRIDGE)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Steel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(4)
                .output(TANK_BRIDGE)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Nickel, 2)
                .input(pipeNormalFluid, Steel, 2)
                .input(MetaTileEntities.HULL[GTValues.LV])
                .circuitMeta(5)
                .output(INV_TANK_BRIDGE)
                .duration(200)
                .EUt(16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalItem, Electrum, 2)
                .input(pipeNormalFluid, Aluminium, 2)
                .input(rotor, Bronze)
                .input(gear, Aluminium)
                .input(spring, Aluminium)
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(MetaTileEntities.HULL[GTValues.MV])
                .circuitMeta(5)
                .output(UNIVERSAL_BRIDGE)
                .duration(200)
                .EUt(16)
                .buildAndRegister();
    }

    private static void initMultiblocks() {

        ModHandler.addShapedRecipe("symtech:fermentation_vat", FERMENTATION_VAT.getStackForm(),
                "CUC", "GHG", "CIC",
                'C', CraftingComponent.CABLE.getIngredient(GTValues.LV),
                'U', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'G', new UnificationEntry(block, Glass),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV));
    }
}
