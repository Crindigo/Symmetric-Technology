package symtech.loaders.recipes;

import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.MetaTileEntityLoader;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import symtech.common.STConfigHolder;
import symtech.common.blocks.*;
import symtech.common.blocks.BlockCoagulationTankWall.CoagulationTankWallType;
import symtech.common.materials.SymtechMaterials;
import symtech.common.metatileentities.SymtechMetaTileEntities;
import symtech.common.metatileentities.multi.electric.MetaTileEntityOreSorter;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN;
import static gregtech.common.blocks.MetaBlocks.METAL_CASING;
import static gregtech.common.blocks.MetaBlocks.STONE_BLOCKS;
import static gregtech.common.blocks.StoneVariantBlock.StoneType.CONCRETE_LIGHT;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static symtech.common.blocks.BlockSymtechMultiblockCasing.CasingType.SILICON_CARBIDE_CASING;
import static symtech.common.blocks.SymtechBlocks.*;
import static symtech.common.item.SymtechMetaItems.*;
import static symtech.common.materials.SymtechMaterials.*;
import static symtech.common.metatileentities.SymtechMetaTileEntities.*;

public class MachineRecipes {
    public static void init() {
        ModHandler.addShapedRecipe("symtech:reverberatory_furnace",
                REVERBERATORY_FURNACE.getStackForm(),
                "hPS", "GBT", "dPS",
                'P', new UnificationEntry(plate, WroughtIron),
                'S', new UnificationEntry(screw, WroughtIron),
                'G', new UnificationEntry(block, Glass),
                'B', METAL_CASING.getItemVariant(PRIMITIVE_BRICKS),
                'T', new UnificationEntry(stick, WroughtIron));


        initSteamMachines();
        initElectricMachines();
        initLogisticsMachines();
        initMultiblocks();
        initEnergyMachines();
    }

    private static void initSteamMachines() {
        if (STConfigHolder.recipe.alternateCEUSteamMachines) {
            ModHandler.removeRecipeByName("gregtech:steam_macerator_bronze");
            ModHandler.removeRecipeByName("gregtech:steam_extractor_bronze");
            ModHandler.removeRecipeByName("gregtech:steam_compressor_bronze");
            ModHandler.removeRecipeByName("gregtech:steam_hammer_bronze");
            ModHandler.removeRecipeByName("gregtech:steam_rock_breaker_bronze");
            ModHandler.removeRecipeByName("gregtech:steam_miner");

            // The only problem here is making a PBF for steel before a macerator in base CEu is a pain
            // due to the mortar giving 4x less clay dust. So, using wrought iron instead.
            ModHandler.addShapedRecipe("gregtech:steam_macerator_bronze",
                    STEAM_MACERATOR_BRONZE.getStackForm(),
                    "ZPZ", "PCP", "TPT",
                    'Z', new UnificationEntry(toolHeadBuzzSaw, WroughtIron),
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                    'T', PISTON_STEAM);

            ModHandler.addShapedRecipe("gregtech:steam_extractor_bronze",
                    STEAM_EXTRACTOR_BRONZE.getStackForm(),
                    "PPP", "TCG", "PPP",
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'T', PISTON_STEAM,
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                    'G', new UnificationEntry(block, Glass));

            ModHandler.addShapedRecipe("gregtech:steam_compressor_bronze",
                    STEAM_COMPRESSOR_BRONZE.getStackForm(),
                    "PPP", "TCT", "PPP",
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'T', PISTON_STEAM,
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

            ModHandler.addShapedRecipe("gregtech:steam_hammer_bronze",
                    STEAM_HAMMER_BRONZE.getStackForm(),
                    "PTP", "PCP", "PAP",
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'T', PISTON_STEAM,
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                    'A', OreDictNames.craftingAnvil);

            // Susy used 2 diamonds and 1 pipe on the bottom here. This seems inconsistent, so I
            // swapped it for 2 pipes and one steel drill head, so diamonds aren't required for steam.
            ModHandler.addShapedRecipe("gregtech:steam_rock_breaker_bronze",
                    STEAM_ROCK_BREAKER_BRONZE.getStackForm(),
                    "TPT", "PCP", "PDP",
                    'T', PISTON_STEAM,
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                    'D', new UnificationEntry(toolHeadDrill, Steel));

            // Susy did not change this one either, but adding motors makes it consistent with LV+
            // miners and a drill is better than diamonds.
            ModHandler.addShapedRecipe("gregtech:steam_miner",
                    STEAM_MINER.getStackForm(),
                    "MPM", "PCP", "GDG",
                    'M', MOTOR_STEAM,
                    'D', new UnificationEntry(toolHeadDrill, Steel),
                    'P', new UnificationEntry(pipeSmallFluid, Bronze),
                    'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                    'G', new UnificationEntry(gearSmall, Bronze));
        }

        // Latex Collector

        ModHandler.addShapedRecipe("gregtech:latex_extractor.bronze",
                STEAM_LATEX_COLLECTOR[0].getStackForm(),
                " D ", "GRG", "PCP",
                'D', new UnificationEntry(toolHeadDrill, Steel),
                'G', new UnificationEntry(block, Glass),
                'R', new UnificationEntry(rotor, Steel),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("gregtech:latex_extractor.steel",
                STEAM_LATEX_COLLECTOR[1].getStackForm(),
                "FPF", "SCS", "WWW",
                'F', new UnificationEntry(pipeSmallFluid, Steel),
                'P', PUMP_STEAM.getStackForm(),
                'S', new UnificationEntry(plate, Steel),
                'C', STEAM_LATEX_COLLECTOR[0].getStackForm(),
                'W', new UnificationEntry(plate, WroughtIron));

        // Mixer

        ModHandler.addShapedRecipe("gregtech:mixer.bronze",
                STEAM_MIXER[0].getStackForm(),
                "GRG", "GSG", "PCP",
                'G', new UnificationEntry(block, Glass),
                'R', new UnificationEntry(rotor, Bronze),
                'S', new UnificationEntry(stick, Bronze),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("gregtech:mixer.steel",
                STEAM_MIXER[1].getStackForm(),
                "PSP", "WCW", "PPP",
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'S', new UnificationEntry(plate, Steel),
                'W', new UnificationEntry(plate, WroughtIron),
                'C', STEAM_MIXER[0].getStackForm());


        // Roaster

        ModHandler.addShapedRecipe("gregtech:roaster.bronze",
                STEAM_ROASTER[0].getStackForm(),
                "PRP", "PBP", "PCP",
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'R', new UnificationEntry(rotor, Bronze),
                'B', new UnificationEntry(plate, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL));

        ModHandler.addShapedRecipe("gregtech:roaster.steel",
                STEAM_ROASTER[1].getStackForm(),
                "WWW", "SCS", "PPP",
                'W', new UnificationEntry(plate, WroughtIron),
                'S', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'C', STEAM_ROASTER[0].getStackForm());

        // Vacuum Chamber

        ModHandler.addShapedRecipe("gregtech:vacuum_chamber.bronze",
                STEAM_VACUUM_CHAMBER[0].getStackForm(),
                "PPP", "UCG", "PPP",
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'U', PUMP_STEAM.getStackForm(),
                'G', new UnificationEntry(block, Glass),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL));

        ModHandler.addShapedRecipe("gregtech:vacuum_chamber.steel",
                STEAM_VACUUM_CHAMBER[1].getStackForm(),
                "SPS", "PCP", "WPW",
                'S', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeSmallFluid, TinAlloy),
                'W', new UnificationEntry(plate, WroughtIron),
                'C', STEAM_VACUUM_CHAMBER[0].getStackForm());

        // Vulcanizing Press

        ModHandler.addShapedRecipe("gregtech:vulcanizing_press.bronze",
                STEAM_VULCANIZING_PRESS[0].getStackForm(),
                "STS", "PLP", "PCP",
                'S', new UnificationEntry(springSmall, Steel),
                'T', new UnificationEntry(stick, Steel),
                'P', new UnificationEntry(pipeSmallFluid, Bronze),
                'L', new UnificationEntry(plate, Steel),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL));

        ModHandler.addShapedRecipe("gregtech:vulcanizing_press.steel",
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

        // Bath Condenser
        ModHandler.addShapedRecipe("symtech:bath_condenser", BATH_CONDENSER[0].getStackForm(),
                "PPP", "PHP", "PPP",
                'P', new UnificationEntry(pipeSmallFluid, StainlessSteel),
                'H', CraftingComponent.HULL.getIngredient(GTValues.HV));

        // Phase Separator
        ModHandler.addShapedRecipe("symtech:phase_separator", PHASE_SEPARATOR[0].getStackForm(),
                "FDP", "PHP",
                'F', new UnificationEntry(frameGt, Steel),
                'D', STEEL_DRUM.getStackForm(),
                'P', new UnificationEntry(pipeSmallFluid, Steel),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV));
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


        // LV, MV, HV quadruple and nonuple input/output hatches.
        // Also add screwdriver recipes to convert between input and output.
    }

    private static void initMultiblocks() {

        // Fermentation Vat
        ModHandler.addShapedRecipe("symtech:fermentation_vat", FERMENTATION_VAT.getStackForm(),
                "CUC", "GHG", "CIC",
                'C', CraftingComponent.CABLE.getIngredient(GTValues.LV),
                'U', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'G', new UnificationEntry(block, Glass),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV));

        // Coagulation Tank
        ModHandler.addShapedRecipe("symtech:coagulation_tank", COAGULATION_TANK.getStackForm(),
                "PRP", "sTh", "PBP",
                'P', new UnificationEntry(plank, TreatedWood),
                'R', new UnificationEntry(rotor, Steel),
                'T', new UnificationEntry(pipeLargeFluid, TreatedWood),
                'B', new UnificationEntry(bolt, Steel));

        ModHandler.addShapedRecipe("symtech:coagulation_tank_wall",
                COAGULATION_TANK_WALL.getItemVariant(CoagulationTankWallType.WOODEN_COAGULATION_TANK_WALL),
                "PBP", "sFh", "PBP",
                'P', new UnificationEntry(plank, TreatedWood),
                'F', new UnificationEntry(frameGt, TreatedWood),
                'B', new UnificationEntry(bolt, Steel));

        // Polymerization tank
        ModHandler.addShapedRecipe("symtech:polymerization_tank", POLYMERIZATION_TANK.getStackForm(),
                "CMC", "PRP", "IHI",
                'C', CraftingComponent.CABLE.getIngredient(GTValues.LV),
                'M', CraftingComponent.MOTOR.getIngredient(GTValues.LV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'R', CraftingComponent.ROTOR.getIngredient(GTValues.LV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV));

        // Fluidized bed reactor
        ModHandler.addShapedRecipe("symtech:fluidized_bed_reactor", FLUIDIZED_BED_REACTOR.getStackForm(),
                "CPC", "LHL", "IMI",
                'C', CraftingComponent.CABLE.getIngredient(GTValues.HV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.HV),
                'L', new UnificationEntry(pipeLargeFluid, Polytetrafluoroethylene),
                'H', CraftingComponent.HULL.getIngredient(GTValues.HV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.HV),
                'M', CraftingComponent.MOTOR.getIngredient(GTValues.HV));

        // Sintering oven
        ModHandler.addShapedRecipe("symtech:brick_sintering_block",
                SINTERING_BRICK.getItemVariant(BlockSinteringBrick.SinteringBrickType.BRICK),
                " h ", "FCF", "   ",
                'F', new UnificationEntry(foil, Steel),
                'C', METAL_CASING.getItemVariant(PRIMITIVE_BRICKS));

        ModHandler.addShapedRecipe("symtech:sintering_oven", SINTERING_OVEN.getStackForm(),
                "MRW", "ICI", "PWP",
                'M', CraftingComponent.MOTOR.getIngredient(GTValues.LV),
                'R', CraftingComponent.ROTOR.getIngredient(GTValues.LV),
                'W', new UnificationEntry(wireGtQuadruple, Cupronickel),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'C', METAL_CASING.getItemVariant(PRIMITIVE_BRICKS),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV));

        // PSA
        ModHandler.addShapedRecipe("symtech:pressure_swing_adsorber", PRESSURE_SWING_ADSORBER.getStackForm(),
                "LML", "PHP", "ILI",
                'L', new UnificationEntry(pipeLargeFluid, Aluminium),
                'M', CraftingComponent.MOTOR.getIngredient(GTValues.MV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.MV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.MV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.MV));

        // Turbines

        ModHandler.addShapedRecipe("symtech:alternator_coil",
                ALTERNATOR_COIL.getItemVariant(BlockAlternatorCoil.AlternatorCoilType.COPPER),
                "hMd", "MTM", "ICI",
                'M', CraftingComponent.MOTOR.getIngredient(GTValues.LV),
                'T', new UnificationEntry(plate, Steel),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'C', new UnificationEntry(cableGtSingle, Tin));

        ModHandler.addShapedRecipe("symtech:steel_turbine_rotor",
                TURBINE_ROTOR.getItemVariant(BlockTurbineRotor.BlockTurbineRotorType.STEEL),
                "TST", "hLd", "TRT",
                'T', new UnificationEntry(plate, Steel),
                'S', new UnificationEntry(screw, Steel),
                'L', new UnificationEntry(stickLong, Steel),
                'R', new UnificationEntry(rotor, Steel));

        ModHandler.addShapedRecipe("symtech:steel_turbine_controller", BASIC_STEAM_TURBINE.getStackForm(),
                "TCT", "IHI", "CIC",
                'T', new UnificationEntry(plate, Steel),
                'C', new UnificationEntry(cableGtSingle, Tin),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV));

        ModHandler.addShapedRecipe("symtech:gas_turbine_controller", BASIC_GAS_TURBINE.getStackForm(),
                "TCT", "IHI", "CIC",
                'T', new UnificationEntry(plate, Steel),
                'C', new UnificationEntry(cableGtSingle, Copper),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.MV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.MV));

        ModHandler.addShapedRecipe("symtech:titanium_hp_turbine_controller",
                HIGH_PRESSURE_ADVANCED_STEAM_TURBINE.getStackForm(),
                "TPT", "IHI", "CIC",
                'T', new UnificationEntry(plate, Titanium),
                'P', new UnificationEntry(pipeSmallFluid, Titanium),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.EV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.EV),
                'C', new UnificationEntry(cableGtHex, Aluminium));

        ModHandler.addShapedRecipe("symtech:titanium_lp_turbine_controller",
                LOW_PRESSURE_ADVANCED_STEAM_TURBINE.getStackForm(),
                "TPT", "IHI", "CIC",
                'T', new UnificationEntry(plate, Titanium),
                'P', new UnificationEntry(pipeHugeFluid, Titanium),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.EV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.EV),
                'C', new UnificationEntry(cableGtHex, Aluminium));

        ModHandler.addShapedRecipe("symtech:hp_turbine_rotor",
                TURBINE_ROTOR.getItemVariant(BlockTurbineRotor.BlockTurbineRotorType.HIGH_PRESSURE),
                "TNT", "hSd", "TRT",
                'T', new UnificationEntry(plate, Titanium),
                'N', new UnificationEntry(ring, Nimonic105),
                'S', new UnificationEntry(stickLong, Nimonic105),
                'R', new UnificationEntry(rotor, Nimonic105));

        ModHandler.addShapedRecipe("symtech:lp_turbine_rotor",
                TURBINE_ROTOR.getItemVariant(BlockTurbineRotor.BlockTurbineRotorType.LOW_PRESSURE),
                "TNT", "hSd", "TRT",
                'T', new UnificationEntry(plate, Titanium),
                'N', new UnificationEntry(ring, Incoloy825),
                'S', new UnificationEntry(stickLong, Incoloy825),
                'R', new UnificationEntry(rotor, Incoloy825));

        ModHandler.addShapedRecipe("symtech:silicon_carbide_casing",
                MULTIBLOCK_CASING.getItemVariant(SILICON_CARBIDE_CASING, 2),
                "ThT", "TCT", "TwT",
                'T', new UnificationEntry(plate, SiliconCarbide),
                'C', METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN));

        ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, SiliconCarbide, 6)
                .inputs(METAL_CASING.getItemVariant(STAINLESS_CLEAN))
                .outputs(MULTIBLOCK_CASING.getItemVariant(SILICON_CARBIDE_CASING, 2))
                .EUt(16)
                .duration(50)
                .buildAndRegister();

        // Ore Sorter
        ModHandler.addShapedRecipe("symtech:ore_sorter", ORE_SORTER.getStackForm(),
                "AIA", "PHP", "AIA",
                'A', CraftingComponent.ROBOT_ARM.getIngredient(GTValues.LV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.MV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV));

        // Primitive Mud Pump
        ModHandler.addShapedRecipe("symtech:primitive_mud_pump", PRIMITIVE_MUD_PUMP.getStackForm(),
                "NPS", "RCd", "LFL",
                'N', new UnificationEntry(ring, Bronze),
                'P', new UnificationEntry(pipeNormalFluid, TreatedWood),
                'S', new UnificationEntry(screw, Bronze),
                'R', new UnificationEntry(rotor, Bronze),
                'C', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                'L', new ItemStack(Blocks.STONE_SLAB, 1, 4),
                'F', new UnificationEntry(pipeLargeFluid, Wood));

        // Condenser
        ModHandler.addShapedRecipe("symtech:condenser", CONDENSER.getStackForm(),
                " P ", "FCF", " P ",
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'F', new UnificationEntry(frameGt, Steel),
                'C', MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE));

        // Heat Exchanger
        ModHandler.addShapedRecipe("symtech:heat_exchanger", HEAT_EXCHANGER.getStackForm(),
                " F ", "PCP", " F ",
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'F', new UnificationEntry(frameGt, Steel),
                'C', MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE));

        // Steel Drill Head
        ModHandler.addShapedRecipe("symtech:steel_drill_head",
                DRILL_HEAD.getItemVariant(BlockDrillHead.DrillHeadType.STEEL),
                "PVP", "GCG", " G ",
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'V', CraftingComponent.CONVEYOR.getIngredient(GTValues.LV),
                'G', MetaItems.COMPONENT_GRINDER_DIAMOND,
                'C', METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID));

        // Heat Radiator
        ModHandler.addShapedRecipe("symtech:heat_radiator", HEAT_RADIATOR.getStackForm(),
                "FLF", "PHP", "FLF",
                'F', new UnificationEntry(frameGt, Steel),
                'L', new UnificationEntry(pipeLargeFluid, Steel),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV));

        ModHandler.addShapedRecipe("symtech:basic_serpentine",
                SERPENTINE.getItemVariant(BlockSerpentine.SerpentineType.BASIC, 6),
                " Tw", "PPP", "hT ",
                'T', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeTinyFluid, Copper));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Steel, 2)
                .input(pipeTinyFluid, Copper, 2)
                .circuitMeta(2)
                .outputs(SERPENTINE.getItemVariant(BlockSerpentine.SerpentineType.BASIC, 6))
                .duration(240)
                .EUt(GTValues.VA[1])
                .buildAndRegister();

        // Large Weapons Factory
        ModHandler.addShapedRecipe("symtech:large_weapons_factory", LARGE_WEAPONS_FACTORY.getStackForm(),
                "SAE", "VHV", "IAI",
                'S', CraftingComponent.SENSOR.getIngredient(GTValues.LV),
                'A', CraftingComponent.ROBOT_ARM.getIngredient(GTValues.LV),
                'E', CraftingComponent.EMITTER.getIngredient(GTValues.LV),
                'V', CraftingComponent.CONVEYOR.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV));

        // Gravity Separator
        ModHandler.addShapedRecipe("symtech:gravity_separator", GRAVITY_SEPARATOR.getStackForm(),
                "GIG", "VHV", "ICI",
                'G', MetaItems.COMPONENT_GRINDER_DIAMOND,
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.MV),
                'V', CraftingComponent.CONVEYOR.getIngredient(GTValues.MV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.MV),
                'C', CraftingComponent.CABLE.getIngredient(GTValues.MV));

        ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .input(MetaItems.ELECTRIC_MOTOR_HV, 2)
                .input(gear, StainlessSteel, 4)
                .input(rotor, StainlessSteel, 16)
                .input(plate, StainlessSteel, 16)
                .outputs(SEPARATOR_ROTOR.getItemVariant(BlockSeparatorRotor.BlockSeparatorRotorType.STEEL, 5))
                .duration(240)
                .EUt(GTValues.VA[1])
                .buildAndRegister();

        // Reaction Furnace
        ModHandler.addShapedRecipe("symtech:reaction_furnace", REACTION_FURNACE.getStackForm(),
                "CSC", "IHI", "PPP",
                'C', CraftingComponent.CABLE_QUAD.getIngredient(GTValues.MV),
                'S', CraftingComponent.SPRING.getIngredient(GTValues.MV),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.MV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.MV),
                'P', CraftingComponent.PLATE.getIngredient(GTValues.MV));

        // Advanced Arc Furnace
        ModHandler.addShapedRecipe("symtech:advanced_arc_furnace", ADVANCED_ARC_FURNACE.getStackForm(),
                "CEC", "IHI", "PTA",
                'C', new UnificationEntry(cableGtHex, Tin),
                'E', ELECTRODE_ASSEMBLY.getItemVariant(BlockElectrodeAssembly.ElectrodeAssemblyType.CARBON),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.LV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.LV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.LV),
                'T', CraftingComponent.PLATE.getIngredient(GTValues.LV),
                'A', CraftingComponent.ROBOT_ARM.getIngredient(GTValues.LV));

        // Arc Furnace Complex
        ModHandler.addShapedRecipe("symtech:arc_furnace_complex", ARC_FURNACE_COMPLEX.getStackForm(),
                "CEC", "IHI", "PTA",
                'C', new UnificationEntry(cableGtHex, Platinum),
                'E', ELECTRODE_ASSEMBLY.getItemVariant(BlockElectrodeAssembly.ElectrodeAssemblyType.CARBON),
                'I', CraftingComponent.CIRCUIT.getIngredient(GTValues.EV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.EV),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.EV),
                'T', CraftingComponent.PLATE.getIngredient(GTValues.EV),
                'A', CraftingComponent.ROBOT_ARM.getIngredient(GTValues.EV));

        // Electrode Assembly
        ModHandler.addShapedRecipe("symtech:electrode_assembly",
                ELECTRODE_ASSEMBLY.getItemVariant(BlockElectrodeAssembly.ElectrodeAssemblyType.CARBON),
                "TCT", "GFG", "TCT",
                'T', new UnificationEntry(plate, Steel),
                'C', new UnificationEntry(cableGtSingle, Copper),
                'G', new UnificationEntry(dust, Graphite),
                'F', new UnificationEntry(frameGt, Steel));

        // Evaporation Pool
        ModHandler.addShapedRecipe("symtech:evaporation_pool", EVAPORATION_POOL.getStackForm(),
                "SFS", "PHP", "SFS",
                'S', STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(CONCRETE_LIGHT),
                'F', new UnificationEntry(pipeHugeFluid, Aluminium),
                'P', CraftingComponent.PUMP.getIngredient(GTValues.MV),
                'H', CraftingComponent.HULL.getIngredient(GTValues.MV));

        // Evaporation Bed
        ModHandler.addShapedRecipe("symtech:evaporation_bed",
                EVAPORATION_BED.getItemVariant(BlockEvaporationBed.EvaporationBedType.DIRT, 8),
                "SDS", "DGD", "SDS",
                'S', Blocks.SAND,
                'D', new ItemStack(Blocks.DIRT, 1, 0),
                'G', Blocks.GRAVEL);

        // Clarifier

        // Clarifier Vat

        // MSFD

        // Smoke Stack

        // Flare Stack

        // Froth Flotation Tank

        // Froth Flotation Vat

        // Vacuum Distillation Tower

        // Quencher

        // Dumper

        // Coking Tower

        // Rotary Kiln

        // High Temp Distillation Tower

        // Mixer Settler

        // Catalytic Reformer

        // Single Column Cryogenic Distillation Plant

        // Low Pressure Cryogenic Distillation Plant

        // High Pressure Cryogenic Distillation Plant

        // Blender

        // Large Fluid Pump

        // Sieve Distillation Tower
    }

    private static void initEnergyMachines() {
        // 4A and 16A dynamos for LV, MV, HV. 16A for EV.
    }
}
