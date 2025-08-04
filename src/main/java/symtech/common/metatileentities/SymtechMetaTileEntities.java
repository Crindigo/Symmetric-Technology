package symtech.common.metatileentities;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.impl.PropertyFluidFilter;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import gregtech.common.metatileentities.storage.MetaTileEntityCrate;
import gregtech.common.metatileentities.storage.MetaTileEntityDrum;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import symtech.api.SymtechLog;
import symtech.api.metatileentity.CatalystMachineMetaTileEntity;
import symtech.api.metatileentity.ContinuousMachineMetaTileEntity;
import symtech.api.metatileentity.PseudoMultiMachineMetaTileEntity;
import symtech.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity;
import symtech.api.metatileentity.steam.SymtechSteamProgressIndicator;
import symtech.api.metatileentity.steam.SymtechSteamProgressIndicators;
import symtech.api.recipes.SymtechRecipeMaps;
import symtech.api.util.SymtechUtility;
import symtech.client.renderer.textures.SymtechTextures;
import symtech.common.blocks.BlockTurbineRotor;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.metatileentities.logistics.MetaTileEntityBridge;
import symtech.common.metatileentities.logistics.MetaTileEntityExtender;
import symtech.common.metatileentities.multi.electric.*;
import symtech.common.metatileentities.multi.electric.strand.*;
import symtech.common.metatileentities.multi.primitive.MetaTileEntityCoagulationTank;
import symtech.common.metatileentities.multi.primitive.MetaTileEntityPrimitiveMudPump;
import symtech.common.metatileentities.multi.primitive.MetaTileEntityPrimitiveSmelter;
import symtech.common.metatileentities.multiblockpart.MetaTileEntityPrimitiveItemBus;
import symtech.common.metatileentities.multiblockpart.MetaTileEntityStrandBus;
import symtech.common.metatileentities.multiblockpart.SymtechMetaTileEntityDumpingHatch;
import symtech.common.metatileentities.multiblockpart.SymtechMetaTileEntityEnergyHatch;
import symtech.common.metatileentities.single.electric.*;
import symtech.common.metatileentities.single.steam.MetaTileEntitySteamLatexCollector;
import symtech.common.metatileentities.single.steam.SymtechSimpleSteamMetaTileEntity;
import symtech.common.metatileentities.storage.MetaTileEntityLockedCrate;
import symtech.common.metatileentities.storage.MetaTileEntityPlasticCan;

import java.util.ArrayList;
import java.util.function.Function;

import static gregtech.api.util.GTUtility.gregtechId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static symtech.api.util.SymtechUtility.symId;

public class SymtechMetaTileEntities {

    public static MetaTileEntityMagneticRefrigerator MAGNETIC_REFRIGERATOR;

    public static MetaTileEntityCoagulationTank COAGULATION_TANK;

    public static PseudoMultiMachineMetaTileEntity[] LATEX_COLLECTOR;
    public static PseudoMultiSteamMachineMetaTileEntity[] STEAM_LATEX_COLLECTOR;

    public static CatalystMachineMetaTileEntity[] VULCANIZING_PRESS;
    public static SymtechSimpleSteamMetaTileEntity[] STEAM_VULCANIZING_PRESS;

    public static CatalystMachineMetaTileEntity[] ROASTER;
    public static SymtechSimpleSteamMetaTileEntity[] STEAM_ROASTER;

    public static MetaTileEntitySinteringOven SINTERING_OVEN;

    public static SymtechSimpleSteamMetaTileEntity[] STEAM_MIXER;

    public static SymtechSimpleSteamMetaTileEntity[] STEAM_VACUUM_CHAMBER;
    public static SimpleMachineMetaTileEntity[] VACUUM_CHAMBER;

    public static MetaTileEntityDrum LEAD_DRUM;
    public static MetaTileEntityDrum BRASS_DRUM;
    public static MetaTileEntityPlasticCan PE_CAN;
    public static MetaTileEntityPlasticCan PP_CAN;
    public static MetaTileEntityPlasticCan PTFE_CAN;
    public static MetaTileEntityPlasticCan UHMWPE_CAN;

    public static MetaTileEntityCrate HERMETICALLY_SEALED_CRATE;

    //Machines for chem overhaul
    public static ContinuousMachineMetaTileEntity[] CONTINUOUS_STIRRED_TANK_REACTOR;
    public static ContinuousMachineMetaTileEntity[] FIXED_BED_REACTOR;
    public static ContinuousMachineMetaTileEntity[] TRICKLE_BED_REACTOR;
    public static ContinuousMachineMetaTileEntity[] BUBBLE_COLUMN_REACTOR;
    public static SimpleMachineMetaTileEntity[] BATCH_REACTOR;
    public static SimpleMachineMetaTileEntity[] CRYSTALLIZER;
    public static SimpleMachineMetaTileEntity[] DRYER;
    public static SimpleMachineMetaTileEntity[] ION_EXCHANGE_COLUMN;
    public static SimpleMachineMetaTileEntity[] ZONE_REFINER;
    public static SimpleMachineMetaTileEntity[] TUBE_FURNACE;
    public static MetaTileEntityFluidizedBedReactor FLUIDIZED_BED_REACTOR;
    public static MetaTileEntityPolymerizationTank POLYMERIZATION_TANK;
    public static MetaTileEntityElectrolyticCell ELECTROLYTIC_CELL;

    // Machines for Oil Overhaul
    public static MetaTileEntityCokingTower COKING_TOWER;
    public static MetaTileEntityVacuumDistillationTower VACUUM_DISTILLATION_TOWER;
    public static MetaTileEntityCatalyticReformer CATALYTIC_REFORMER;
    public static MetaTileEntitySmokeStack SMOKE_STACK;

    public static MetaTileEntityFermentationVat FERMENTATION_VAT;

    public static SimpleMachineMetaTileEntity[] UV_LIGHT_BOX;

    public static SimpleMachineMetaTileEntity[] ION_IMPLANTER;
    public static SimpleMachineMetaTileEntity[] CVD;

    public static SimpleMachineMetaTileEntity[] WEAPONS_FACTORY;

    public static SimpleMachineMetaTileEntity[] FLUID_DECOMPRESSOR;

    public static SimpleMachineMetaTileEntity[] FLUID_COMPRESSOR;

    public static MetaTileEntityOreSorter ORE_SORTER;
    public static MetaTileEntityCondenser CONDENSER;
    public static MetaTileEntityNaturalDraftCoolingTower NATURAL_DRAFT_COOLING_TOWER;
    public static MetaTileEntitySymtechLargeTurbine BASIC_GAS_TURBINE;
    public static MetaTileEntitySymtechLargeTurbine BASIC_STEAM_TURBINE;
    public static MetaTileEntitySymtechLargeTurbine LOW_PRESSURE_ADVANCED_STEAM_TURBINE;
    public static MetaTileEntitySymtechLargeTurbine HIGH_PRESSURE_ADVANCED_STEAM_TURBINE;

    public static MetaTileEntityHeatExchanger HEAT_EXCHANGER;
    public static MetaTileEntityHeatRadiator HEAT_RADIATOR;
    public static MetaTileEntityLargeWeaponsFactory LARGE_WEAPONS_FACTORY;
    public static MetaTileEntityMagnetohydrodynamicGenerator MAGNETOHYDRODYNAMIC_GENERATOR;
    public static MetaTileEntityQuarry QUARRY;
    public static MetaTileEntityGravitySeparator GRAVITY_SEPARATOR;
    public static MetaTileEntityQuencher QUENCHER;
    public static MetaTileEntityMixerSettler MIXER_SETTLER;

    public static MetaTileEntityEnergyHatch[] NEW_ENERGY_OUTPUT_HATCH_4A = new MetaTileEntityEnergyHatch[3];
    public static MetaTileEntityEnergyHatch[] NEW_ENERGY_OUTPUT_HATCH_16A = new MetaTileEntityEnergyHatch[4];

    public static MetaTileEntityPrimitiveMudPump PRIMITIVE_MUD_PUMP;

    public static MetaTileEntityPressureSwingAdsorber PRESSURE_SWING_ADSORBER;
    public static MetaTileEntityReactionFurnace REACTION_FURNACE;
    public static MetaTileEntityDronePad DRONE_PAD;

    public static SymtechMetaTileEntityDumpingHatch DUMPING_HATCH;

    public static MetaTileEntityAdvancedArcFurnace ADVANCED_ARC_FURNACE;
    public static MetaTileEntityClarifier CLARIFIER;
    public static MetaTileEntityDumper DUMPER;
    public static MetaTileEntityEvaporationPool EVAPORATION_POOL;
    public static MetaTileEntityFlareStack FLARE_STACK;
    public static MetaTileEntityFrothFlotationTank FROTH_FLOTATION_TANK;
    public static MetaTileEntityMultiStageFlashDistiller MULTI_STAGE_FLASH_DISTILLER;

    public static MetaTileEntityLargeFluidPump LARGE_FLUID_PUMP;
    public static MetaTileEntityHighTemperatureDistillationTower HIGH_TEMPERATURE_DISTILLATION_TOWER;
    public static MetaTileEntityRotaryKiln ROTARY_KILN;
    public static MetaTileEntityHighPressureCryogenicDistillationPlant HIGH_PRESSURE_CRYOGENIC_DISTILLATION_PLANT;
    public static MetaTileEntityLowPressureCryogenicDistillationPlant LOW_PRESSURE_CRYOGENIC_DISTILLATION_PLANT;
    public static MetaTileEntitySingleColumnCryogenicDistillationPlant SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT;
    public static MetaTileEntityReverberatoryFurnace REVERBERATORY_FURNACE;
    public static MetaTileEntityBlender BLENDER;
    public static SimpleMachineMetaTileEntity[] PHASE_SEPARATOR;
    public static SimpleMachineMetaTileEntity[] BATH_CONDENSER;
    public static SimpleMachineMetaTileEntity[] ELECTROSTATIC_SEPARATOR;
    public static SimpleMachineMetaTileEntity[] TEXTILE_SPINNER;
    public static SimpleMachineMetaTileEntity[] POLISHING_MACHINE;

    public static MetaTileEntityPrimitiveSmelter PRIMITIVE_SMELTER;
    public static MetaTileEntityPrimitiveItemBus PRIMITIVE_ITEM_IMPORT;
    public static MetaTileEntityPrimitiveItemBus PRIMITIVE_ITEM_EXPORT;

    //Space Multis
    public static MetaTileEntityScrapRecycler SCRAP_RECYCLER;
    public static MetaTileEntitySieveDistillationTower SIEVE_DISTILLATION_TOWER;

    public static MetaTileEntityCurtainCoater CURTAIN_COATER;
    public static MetaTileEntityPreciseMillingMachine MILLING;

    public static MetaTileEntityBridge INV_BRIDGE;
    public static MetaTileEntityBridge TANK_BRIDGE;
    public static MetaTileEntityBridge INV_TANK_BRIDGE;
    public static MetaTileEntityBridge UNIVERSAL_BRIDGE;

    public static MetaTileEntityExtender INV_EXTENDER;
    public static MetaTileEntityExtender TANK_EXTENDER;
    public static MetaTileEntityExtender INV_TANK_EXTENDER;
    public static MetaTileEntityExtender UNIVERSAL_EXTENDER;

    public static final MetaTileEntityMultiFluidHatch[] SYMTECH_QUADRUPLE_IMPORT_HATCH = new MetaTileEntityMultiFluidHatch[3]; // LV-HV
    public static final MetaTileEntityMultiFluidHatch[] SYMTECH_NONUPLE_IMPORT_HATCH = new MetaTileEntityMultiFluidHatch[3];   // LV-HV
    public static final MetaTileEntityMultiFluidHatch[] SYMTECH_QUADRUPLE_EXPORT_HATCH = new MetaTileEntityMultiFluidHatch[3]; // LV-HV
    public static final MetaTileEntityMultiFluidHatch[] SYMTECH_NONUPLE_EXPORT_HATCH = new MetaTileEntityMultiFluidHatch[3];   // LV-HV

    public static MetaTileEntityIncinerator[] INCINERATOR = new MetaTileEntityIncinerator[4];

    public static MetaTileEntityRTG[] RTG = new MetaTileEntityRTG[8];

    public static MetaTileEntityStrandBus IMPORT_STRAND;
    public static MetaTileEntityStrandBus EXPORT_STRAND;

    public static MetaTileEntityTurningZone TURNING_ZONE;
    public static MetaTileEntityStrandCooler STRAND_COOLER;
    public static MetaTileEntityRollingMill ROLLING_MILL;
    public static MetaTileEntityClusterMill CLUSTER_MILL;
    public static MetaTileEntityHotIsostaticPress HOT_ISOSTATIC_PRESS;
    public static MetaTileEntityFlyingShear FLYING_SHEAR;
    public static MetaTileEntityBilletMold BILLET_MOLD;
    public static MetaTileEntitySlabMold SLAB_MOLD;
    public static MetaTileEntityGasAtomizer GAS_ATOMIZER;
    public static MetaTileEntityArcFurnaceComplex ARC_FURNACE_COMPLEX;
    public static MetaTileEntityMetallurgicalConverter METALLURGICAL_CONVERTER;

    public static MetaTileEntityInjectionMolder INJECTION_MOLDER;

    public static void init() {
        MAGNETIC_REFRIGERATOR = registerMetaTileEntity(14500, new MetaTileEntityMagneticRefrigerator(symId("magnetic_refrigerator")));
        COAGULATION_TANK = registerMetaTileEntity(14501, new MetaTileEntityCoagulationTank(symId("coagulation_tank")));

        LATEX_COLLECTOR[0] = registerMetaTileEntity(14502, new MetaTileEntityLatexCollector(symId("latex_collector.lv"), 1));
        LATEX_COLLECTOR[1] = registerMetaTileEntity(14503, new MetaTileEntityLatexCollector(symId("latex_collector.mv"), 2));
        LATEX_COLLECTOR[2] = registerMetaTileEntity(14504, new MetaTileEntityLatexCollector(symId("latex_collector.hv"), 3));
        LATEX_COLLECTOR[3] = registerMetaTileEntity(14505, new MetaTileEntityLatexCollector(symId("latex_collector.ev"), 4));
        SIEVE_DISTILLATION_TOWER = registerMetaTileEntity(14506, new MetaTileEntitySieveDistillationTower(symId("sieve_distillation_tower")));

        PE_CAN = registerMetaTileEntity(14507, new MetaTileEntityPlasticCan(symId("drum.pe"), Materials.Polyethylene, 64_000));
        PP_CAN = registerMetaTileEntity(14508, new MetaTileEntityPlasticCan(symId("drum.pp"), new PropertyFluidFilter(444, true, true, false, false), 0xdfe39a, 128_000));
        PTFE_CAN = registerMetaTileEntity(14509, new MetaTileEntityPlasticCan(symId("drum.ptfe"), Materials.Polytetrafluoroethylene, 512_000));

        STEAM_LATEX_COLLECTOR[0] = registerMetaTileEntity(14510, new MetaTileEntitySteamLatexCollector(symId("latex_collector.bronze"), false));
        STEAM_LATEX_COLLECTOR[1] = registerMetaTileEntity(14511, new MetaTileEntitySteamLatexCollector(symId("latex_collector.steel"), true));

        UHMWPE_CAN = registerMetaTileEntity(14512, new MetaTileEntityPlasticCan(symId("drum.uhmwpe"), new PropertyFluidFilter(425, true, true, true, false), 0xc5e3de, 512_000)); // sadly I have to put it here

        SINTERING_OVEN = registerMetaTileEntity(14521, new MetaTileEntitySinteringOven(symId("sintering_oven")));

        HERMETICALLY_SEALED_CRATE = registerMetaTileEntity(14522, new MetaTileEntityCrate(symId("crate.pe"), Materials.Polyethylene, 54));

        registerSimpleSteamMTE(STEAM_VULCANIZING_PRESS, 14515, "vulcanizing_press", SymtechRecipeMaps.VULCANIZATION_RECIPES, SymtechSteamProgressIndicators.COMPRESS, SymtechTextures.VULCANIZING_PRESS_OVERLAY, true);
        registerCatalystMTE(VULCANIZING_PRESS, 3, 14517, "vulcanizing_press", SymtechRecipeMaps.VULCANIZATION_RECIPES, SymtechTextures.VULCANIZING_PRESS_OVERLAY, true);

        registerSimpleSteamMTE(STEAM_ROASTER, 14679, "roaster", SymtechRecipeMaps.ROASTER_RECIPES, SymtechSteamProgressIndicators.ARROW, SymtechTextures.ROASTER_OVERLAY, true);
        registerCatalystMTE(ROASTER, 12, 14523, "roaster", SymtechRecipeMaps.ROASTER_RECIPES, SymtechTextures.ROASTER_OVERLAY, true, SymtechUtility.bulkTankSizeFunction);

        registerSimpleSteamMTE(STEAM_MIXER, 14536, "mixer", RecipeMaps.MIXER_RECIPES, SymtechSteamProgressIndicators.MIXER, SymtechTextures.MIXER_OVERLAY_STEAM, false);

        registerSimpleSteamMTE(STEAM_VACUUM_CHAMBER, 14538, "vacuum_chamber", SymtechRecipeMaps.VACUUM_CHAMBER, SymtechSteamProgressIndicators.COMPRESS, Textures.GAS_COLLECTOR_OVERLAY, false);
        registerSimpleMTE(VACUUM_CHAMBER, 12, 14540, "vacuum_chamber", SymtechRecipeMaps.VACUUM_CHAMBER, Textures.GAS_COLLECTOR_OVERLAY, true);

        //chem overhaul
        registerContinuousMachineMTE(CONTINUOUS_STIRRED_TANK_REACTOR, 12, 14554, "continuous_stirred_tank_reactor", SymtechRecipeMaps.CSTR_RECIPES, SymtechTextures.CONTINUOUS_STIRRED_TANK_REACTOR_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);
        registerContinuousMachineMTE(FIXED_BED_REACTOR, 12, 14567, "fixed_bed_reactor", SymtechRecipeMaps.FIXED_BED_REACTOR_RECIPES, SymtechTextures.FIXED_BED_REACTOR_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);
        registerContinuousMachineMTE(TRICKLE_BED_REACTOR, 12, 14580, "trickle_bed_reactor", SymtechRecipeMaps.TRICKLE_BED_REACTOR_RECIPES, SymtechTextures.TRICKLE_BED_REACTOR_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);
        registerContinuousMachineMTE(BUBBLE_COLUMN_REACTOR, 12, 14606, "bubble_column_reactor", SymtechRecipeMaps.BUBBLE_COLUMN_REACTOR_RECIPES, SymtechTextures.BUBBLE_COLUMN_REACTOR_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);

        registerSimpleMTE(BATCH_REACTOR, 12, 14681, "batch_reactor", SymtechRecipeMaps.BATCH_REACTOR_RECIPES, SymtechTextures.BATCH_REACTOR_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);

        //max tier = 12 -> OpV [excludes ULv] -> 13 ids taken (add maxTier +1 to start ID to get next valid id)
        registerSimpleMTE(CRYSTALLIZER, 12, 14593, "crystallizer", SymtechRecipeMaps.CRYSTALLIZER_RECIPES, SymtechTextures.CRYSTALLIZER_OVERLAY, true, SymtechUtility.reactorTankSizeFunction);
        registerSimpleMTE(DRYER, 12, 14621, "dryer", SymtechRecipeMaps.DRYER_RECIPES, SymtechTextures.DRYER_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(ION_EXCHANGE_COLUMN, 12, 14694, "ion_exchange_column", SymtechRecipeMaps.ION_EXCHANGE_COLUMN_RECIPES, SymtechTextures.ION_EXCHANGE_COLUMN_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(ZONE_REFINER, 12, 14707, "zone_refiner", SymtechRecipeMaps.ZONE_REFINER_RECIPES, SymtechTextures.ZONE_REFINER_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(TUBE_FURNACE, 12, 14720, "tube_furnace", SymtechRecipeMaps.TUBE_FURNACE_RECIPES, SymtechTextures.TUBE_FURNACE_OVERLAY, true, GTUtility.defaultTankSizeFunction);

        FLUIDIZED_BED_REACTOR = registerMetaTileEntity(14619, new MetaTileEntityFluidizedBedReactor(symId("fluidized_bed_reactor")));
        POLYMERIZATION_TANK = registerMetaTileEntity(14620, new MetaTileEntityPolymerizationTank(symId("polymerization_tank")));
        ELECTROLYTIC_CELL = registerMetaTileEntity(14634, new MetaTileEntityElectrolyticCell(symId("electrolytic_cell")));
        GRAVITY_SEPARATOR = registerMetaTileEntity(15052, new MetaTileEntityGravitySeparator(symId("gravity_separator")));

        INV_BRIDGE = registerMetaTileEntity(14733, new MetaTileEntityBridge(symId("bridge.inv"), cap -> cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, SymtechTextures.INV_BRIDGE, Materials.Steel));
        TANK_BRIDGE = registerMetaTileEntity(14734, new MetaTileEntityBridge(symId("bridge.tank"), cap -> cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, SymtechTextures.TANK_BRIDGE, Materials.Steel));
        INV_TANK_BRIDGE = registerMetaTileEntity(14735, new MetaTileEntityBridge(symId("bridge.inv_tank"), cap -> cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, SymtechTextures.INV_TANK_BRIDGE, Materials.Steel));
        UNIVERSAL_BRIDGE = registerMetaTileEntity(14736, new MetaTileEntityBridge(symId("bridge.universal"), cap -> true, SymtechTextures.UNIVERSAL_BRIDGE, Materials.Aluminium));

        INV_EXTENDER = registerMetaTileEntity(14737, new MetaTileEntityExtender(symId("extender.inv"), cap -> cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, SymtechTextures.INV_EXTENDER, Materials.Steel));
        TANK_EXTENDER = registerMetaTileEntity(14738, new MetaTileEntityExtender(symId("extender.tank"), cap -> cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, SymtechTextures.TANK_EXTENDER, Materials.Steel));
        INV_TANK_EXTENDER = registerMetaTileEntity(14739, new MetaTileEntityExtender(symId("extender.inv_tank"), cap -> cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, SymtechTextures.INV_TANK_EXTENDER, Materials.Steel));
        UNIVERSAL_EXTENDER = registerMetaTileEntity(14740, new MetaTileEntityExtender(symId("extender.universal"), cap -> true, SymtechTextures.UNIVERSAL_EXTENDER, Materials.Aluminium));

        // 14800 Pyrotech Integration: Primitive Smelter
        PRIMITIVE_ITEM_IMPORT = registerMetaTileEntity(14801, new MetaTileEntityPrimitiveItemBus(symId("primitive_item_import"), false));
        PRIMITIVE_ITEM_EXPORT = registerMetaTileEntity(14802, new MetaTileEntityPrimitiveItemBus(symId("primitive_item_export"), true));

        //oil stuff
        COKING_TOWER = registerMetaTileEntity(14635, new MetaTileEntityCokingTower(symId("coking_tower")));
        VACUUM_DISTILLATION_TOWER = registerMetaTileEntity(14636, new MetaTileEntityVacuumDistillationTower(symId("vacuum_distillation_tower")));
        SMOKE_STACK = registerMetaTileEntity(14637, new MetaTileEntitySmokeStack(symId("smoke_stack")));
        CATALYTIC_REFORMER = registerMetaTileEntity(14638, new MetaTileEntityCatalyticReformer(symId("catalytic_reformer")));
        FERMENTATION_VAT = registerMetaTileEntity(14639, new MetaTileEntityFermentationVat(symId("fermentation_vat")));

        //circuit stuff
        registerSimpleMTE(UV_LIGHT_BOX, 12, 14640, "uv_light_box", SymtechRecipeMaps.UV_RECIPES, SymtechTextures.UV_LIGHT_BOX_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(CVD, 12, 14653, "cvd", SymtechRecipeMaps.CVD_RECIPES, SymtechTextures.CVD_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(ION_IMPLANTER, 12, 14666, "ion_implanter", SymtechRecipeMaps.ION_IMPLANTATION_RECIPES, SymtechTextures.ION_IMPLANTER_OVERLAY, true, GTUtility.defaultTankSizeFunction);

        CURTAIN_COATER = registerMetaTileEntity(14513, new MetaTileEntityCurtainCoater(symId("curtain_coater")));
        MILLING = registerMetaTileEntity(14514, new MetaTileEntityPreciseMillingMachine(symId("milling")));

        //thermodynamic stuff
        registerSimpleMTE(FLUID_COMPRESSOR, 12, 15000, "fluid_compressor", SymtechRecipeMaps.FLUID_COMPRESSOR_RECIPES, SymtechTextures.FLUID_COMPRESSOR_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(FLUID_DECOMPRESSOR, 12, 15013, "fluid_decompressor", SymtechRecipeMaps.FLUID_DECOMPRESSOR_RECIPES, SymtechTextures.FLUID_DECOMPRESSOR_OVERLAY, true, GTUtility.defaultTankSizeFunction);

        //war crimes
        registerSimpleMTE(WEAPONS_FACTORY, 12, 15026, "weapons_factory", SymtechRecipeMaps.WEAPONS_FACTORY_RECIPES, Textures.ASSEMBLER_OVERLAY, true, GTUtility.defaultTankSizeFunction);

        //mbd stuff
        ORE_SORTER = registerMetaTileEntity(15040, new MetaTileEntityOreSorter(symId("ore_sorter")));
        CONDENSER = registerMetaTileEntity(15041, new MetaTileEntityCondenser(symId("condenser")));
        NATURAL_DRAFT_COOLING_TOWER = registerMetaTileEntity(15042, new MetaTileEntityNaturalDraftCoolingTower(symId("natural_draft_cooling_tower")));
        HEAT_EXCHANGER = registerMetaTileEntity(15044, new MetaTileEntityHeatExchanger(symId("heat_exchanger")));
        HEAT_RADIATOR = registerMetaTileEntity(15045, new MetaTileEntityHeatRadiator(symId("heat_radiator")));
        LARGE_WEAPONS_FACTORY = registerMetaTileEntity(15046, new MetaTileEntityLargeWeaponsFactory(symId("large_weapons_factory")));
        MAGNETOHYDRODYNAMIC_GENERATOR = registerMetaTileEntity(15047, new MetaTileEntityMagnetohydrodynamicGenerator(symId("magnetohydrodynamic_generator")));
        QUENCHER = registerMetaTileEntity(15049, new MetaTileEntityQuencher(symId("quencher")));

        PRIMITIVE_MUD_PUMP = registerMetaTileEntity(15051, new MetaTileEntityPrimitiveMudPump(symId("primitive_mud_pump")));

        PRESSURE_SWING_ADSORBER = registerMetaTileEntity(15060, new MetaTileEntityPressureSwingAdsorber(symId("pressure_swing_adsorber")));
        REACTION_FURNACE = registerMetaTileEntity(15061, new MetaTileEntityReactionFurnace(symId("reaction_furnace")));

        DRONE_PAD = registerMetaTileEntity(15062, new MetaTileEntityDronePad(symId("drone_pad")));

        QUARRY = registerMetaTileEntity(15063, new MetaTileEntityQuarry(symId("quarry")));

        LEAD_DRUM = registerMetaTileEntity(14553, new MetaTileEntityDrum(symId("drum.lead"), Materials.Lead, 32000));
        BRASS_DRUM = registerMetaTileEntity(17010, new MetaTileEntityDrum(symId("drum.brass"), new PropertyFluidFilter(1280, true, false, true, false), false, Materials.Brass.getMaterialRGB(), 16000));

        NEW_ENERGY_OUTPUT_HATCH_4A[0] = registerMetaTileEntity(16000, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_4a.lv"), 1, 4, true));
        NEW_ENERGY_OUTPUT_HATCH_16A[0] = registerMetaTileEntity(16001, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_16a.lv"), 1, 16, true));
        NEW_ENERGY_OUTPUT_HATCH_4A[1] = registerMetaTileEntity(16002, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_4a.mv"), 2, 4, true));
        NEW_ENERGY_OUTPUT_HATCH_16A[1] = registerMetaTileEntity(16003, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_16a.mv"), 2, 16, true));
        NEW_ENERGY_OUTPUT_HATCH_4A[2] = registerMetaTileEntity(16004, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_4a.hv"), 3, 4, true));
        NEW_ENERGY_OUTPUT_HATCH_16A[2] = registerMetaTileEntity(16005, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_16a.hv"), 3, 16, true));
        NEW_ENERGY_OUTPUT_HATCH_16A[3] = registerMetaTileEntity(16006, new SymtechMetaTileEntityEnergyHatch(symId("energy_hatch.output_16a.ev"), 4, 16, true));

        INCINERATOR[0] = registerMetaTileEntity(16500, new MetaTileEntityIncinerator(symId("incinerator.lv"), 1, 20, 10));
        INCINERATOR[1] = registerMetaTileEntity(16501, new MetaTileEntityIncinerator(symId("incinerator.mv"), 2, 20, 20));
        INCINERATOR[2] = registerMetaTileEntity(16502, new MetaTileEntityIncinerator(symId("incinerator.hv"), 3, 20, 40));
        INCINERATOR[3] = registerMetaTileEntity(16503, new MetaTileEntityIncinerator(symId("incinerator.ev"), 4, 10, 40));

        // RTGs: 16504-16511
        RTG[0] = registerMetaTileEntity(16504, new MetaTileEntityRTG(symId("rtg.lv"), 1));
        RTG[1] = registerMetaTileEntity(16505, new MetaTileEntityRTG(symId("rtg.mv"), 2));

        // Strand casting: 16600-16610
        IMPORT_STRAND = registerMetaTileEntity(16600, new MetaTileEntityStrandBus(symId("strand_bus.import"), false));
        EXPORT_STRAND = registerMetaTileEntity(16601, new MetaTileEntityStrandBus(symId("strand_bus.export"), true));
        TURNING_ZONE = registerMetaTileEntity(16602, new MetaTileEntityTurningZone(symId("turning_zone")));
        STRAND_COOLER = registerMetaTileEntity(16603, new MetaTileEntityStrandCooler(symId("strand_cooler")));
        ROLLING_MILL = registerMetaTileEntity(16604, new MetaTileEntityRollingMill(symId("rolling_mill")));
        CLUSTER_MILL = registerMetaTileEntity(16605, new MetaTileEntityClusterMill(symId("cluster_mill")));
        HOT_ISOSTATIC_PRESS = registerMetaTileEntity(16606, new MetaTileEntityHotIsostaticPress(symId("hot_isostatic_press")));
        FLYING_SHEAR = registerMetaTileEntity(16607, new MetaTileEntityFlyingShear(symId("flying_shear")));
        SLAB_MOLD = registerMetaTileEntity(16608, new MetaTileEntitySlabMold(symId("slab_mold")));
        BILLET_MOLD = registerMetaTileEntity(16609, new MetaTileEntityBilletMold(symId("billet_mold")));
        GAS_ATOMIZER = registerMetaTileEntity(16610, new MetaTileEntityGasAtomizer(symId("gas_atomizer")));
        ARC_FURNACE_COMPLEX = registerMetaTileEntity(16611, new MetaTileEntityArcFurnaceComplex(symId("arc_furnace_complex")));
        METALLURGICAL_CONVERTER = registerMetaTileEntity(16612, new MetaTileEntityMetallurgicalConverter(symId("metallurgical_converter")));

        // Turbines: 17000-17010
        BASIC_STEAM_TURBINE = registerMetaTileEntity(17000, new MetaTileEntitySymtechLargeTurbine(symId("basic_steam_turbine"), SymtechRecipeMaps.LARGE_STEAM_TURBINE, 1, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING), SymtechBlocks.TURBINE_ROTOR.getState(BlockTurbineRotor.BlockTurbineRotorType.STEEL), Textures.SOLID_STEEL_CASING, SymtechTextures.LARGE_STEAM_TURBINE_OVERLAY));
        BASIC_GAS_TURBINE = registerMetaTileEntity(17001, new MetaTileEntitySymtechLargeTurbine(symId("basic_gas_turbine"), RecipeMaps.GAS_TURBINE_FUELS, 2, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING), SymtechBlocks.TURBINE_ROTOR.getState(BlockTurbineRotor.BlockTurbineRotorType.STEEL), Textures.SOLID_STEEL_CASING, SymtechTextures.LARGE_GAS_TURBINE_OVERLAY));

        ADVANCED_ARC_FURNACE = registerMetaTileEntity(17003, new MetaTileEntityAdvancedArcFurnace(symId("advanced_arc_furnace")));
        CLARIFIER = registerMetaTileEntity(17004, new MetaTileEntityClarifier(symId("clarifier")));
        DUMPER = registerMetaTileEntity(17005, new MetaTileEntityDumper(symId("dumper")));
        EVAPORATION_POOL = registerMetaTileEntity(17006, new MetaTileEntityEvaporationPool(symId("evaporation_pool")));
        FLARE_STACK = registerMetaTileEntity(17007, new MetaTileEntityFlareStack(symId("flare_stack")));
        FROTH_FLOTATION_TANK = registerMetaTileEntity(17008, new MetaTileEntityFrothFlotationTank(symId("froth_flotation_tank")));
        MULTI_STAGE_FLASH_DISTILLER = registerMetaTileEntity(17009, new MetaTileEntityMultiStageFlashDistiller(symId("multi_stage_flash_distiller")));

        //FREE ID: 17011
        HIGH_TEMPERATURE_DISTILLATION_TOWER = registerMetaTileEntity(17012, new MetaTileEntityHighTemperatureDistillationTower(symId("high_temperature_distillation_tower")));
        ROTARY_KILN = registerMetaTileEntity(17013, new MetaTileEntityRotaryKiln(symId("rotary_kiln")));
        HIGH_PRESSURE_CRYOGENIC_DISTILLATION_PLANT = registerMetaTileEntity(17014, new MetaTileEntityHighPressureCryogenicDistillationPlant(symId("high_pressure_cryogenic_distillation_plant")));
        LOW_PRESSURE_CRYOGENIC_DISTILLATION_PLANT = registerMetaTileEntity(17015, new MetaTileEntityLowPressureCryogenicDistillationPlant(symId("low_pressure_cryogenic_distillation_plant")));
        REVERBERATORY_FURNACE = registerMetaTileEntity(17016, new MetaTileEntityReverberatoryFurnace(symId("reverberatory_furnace")));
        SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT = registerMetaTileEntity(17017, new MetaTileEntitySingleColumnCryogenicDistillationPlant(symId("single_column_cryogenic_distillation_plant")));
        BLENDER = registerMetaTileEntity(17020, new MetaTileEntityBlender(symId("blender")));

        PHASE_SEPARATOR[0] = registerMetaTileEntity(17018, new MetaTileEntityPhaseSeparator(symId("phase_separator")));
        BATH_CONDENSER[0] = registerMetaTileEntity(17019, new MetaTileEntityBathCondenser(symId("bath_condenser")));

        LARGE_FLUID_PUMP = registerMetaTileEntity(17021, new MetaTileEntityLargeFluidPump(symId("large_fluid_pump")));

        for (int i = GTValues.LV; i <= GTValues.HV; i++) { // Quadruple/Nonuple hatches: 17022-17033
            int index = i - GTValues.LV;
            String tierName = GTValues.VN[i].toLowerCase();
            SYMTECH_QUADRUPLE_IMPORT_HATCH[index] = registerMetaTileEntity(17022 + index,
                    new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.import_4x." + tierName), i, 4, false));
            SYMTECH_NONUPLE_IMPORT_HATCH[index] = registerMetaTileEntity(17025 + index,
                    new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.import_9x." + tierName), i, 9, false));
            SYMTECH_QUADRUPLE_EXPORT_HATCH[index] = registerMetaTileEntity(17028 + index,
                    new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.export_4x." + tierName), i, 4, true));
            SYMTECH_NONUPLE_EXPORT_HATCH[index] = registerMetaTileEntity(17031 + index,
                    new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.export_9x." + tierName), i, 9, true));
        }

        registerSimpleMTE(ELECTROSTATIC_SEPARATOR, 12, 17035, "electrostatic_separator", SymtechRecipeMaps.ELECTROSTATIC_SEPARATOR, SymtechTextures.ELECTROSTATIC_SEPARATOR_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(POLISHING_MACHINE, 12, 17048, "polishing_machine", SymtechRecipeMaps.POLISHING_MACHINE, SymtechTextures.POLISHING_MACHINE_OVERLAY, true, GTUtility.defaultTankSizeFunction);
        registerSimpleMTE(TEXTILE_SPINNER, 12, 17061, "textile_spinner", SymtechRecipeMaps.SPINNING_RECIPES, SymtechTextures.TEXTILE_SPINNER_OVERLAY, true);
        ArrayList<Integer> ids = new ArrayList<>();
        for (int id = 14500; id < 15000; id++) {
            if (GregTechAPI.MTE_REGISTRY.getObjectById(id) == null) ids.add(id);
        }
        SymtechLog.logger.debug("Available ID(s) are: {}", ids);

        MIXER_SETTLER = registerMetaTileEntity(17100, new MetaTileEntityMixerSettler(symId("mixer_settler")));

        //Space machines
        SCRAP_RECYCLER = registerMetaTileEntity(18006, new MetaTileEntityScrapRecycler(symId("scrap_recycler")));

        //Advanced Steam Turbines
        LOW_PRESSURE_ADVANCED_STEAM_TURBINE = registerMetaTileEntity(18100, new MetaTileEntitySymtechLargeTurbine(symId("low_pressure_advanced_steam_turbine"), SymtechRecipeMaps.LOW_PRESSURE_ADVANCED_STEAM_TURBINE, 4, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_TURBINE_CASING), SymtechBlocks.TURBINE_ROTOR.getState(BlockTurbineRotor.BlockTurbineRotorType.LOW_PRESSURE), Textures.STABLE_TITANIUM_CASING, SymtechTextures.LOW_PRESSURE_ADVANCED_STEAM_TURBINE_OVERLAY));
        HIGH_PRESSURE_ADVANCED_STEAM_TURBINE = registerMetaTileEntity(18101, new MetaTileEntityHighPressureLargeTurbine(symId("high_pressure_advanced_steam_turbine")));

        INJECTION_MOLDER = registerMetaTileEntity(18110, new MetaTileEntityInjectionMolder(symId("injection_molder")));
    }

    private static void registerSimpleSteamMTE(SymtechSimpleSteamMetaTileEntity[] machines, int startId, String name, RecipeMap<?> recipeMap, SymtechSteamProgressIndicator progressIndicator, ICubeRenderer texture, boolean isBricked) {
        machines[0] = registerMetaTileEntity(startId, new SymtechSimpleSteamMetaTileEntity(symId(String.format("%s.bronze", name)), recipeMap, progressIndicator, texture, isBricked, false));
        machines[1] = registerMetaTileEntity(startId + 1, new SymtechSimpleSteamMetaTileEntity(symId(String.format("%s.steel", name)), recipeMap, progressIndicator, texture, isBricked, true));
    }

    private static void registerSimpleMTE(SimpleMachineMetaTileEntity[] machines, int maxTier, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing) {
        for (int i = 0; i <= maxTier; i++) {
            machines[i] = registerMetaTileEntity(startId + i, new SimpleMachineMetaTileEntity(symId(String.format("%s.%s", name, GTValues.VN[i + 1].toLowerCase())), map, texture, i + 1, hasFrontFacing));
        }
    }

    private static void registerSimpleMTE(SimpleMachineMetaTileEntity[] machines, int maxTier, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i <= maxTier; i++) {
            machines[i] = registerMetaTileEntity(startId + i, new SimpleMachineMetaTileEntity(symId(String.format("%s.%s", name, GTValues.VN[i + 1].toLowerCase())), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
        }
    }

    private static void registerContinuousMachineMTE(ContinuousMachineMetaTileEntity[] machines, int maxTier, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i <= maxTier; i++) {
            machines[i] = registerMetaTileEntity(startId + i, new ContinuousMachineMetaTileEntity(symId(String.format("%s.%s", name, GTValues.VN[i + 1].toLowerCase())), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
        }
    }

    private static void registerCatalystMTE(CatalystMachineMetaTileEntity[] machines, int maxTier, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i <= maxTier; i++) {
            machines[i] = registerMetaTileEntity(startId + i, new CatalystMachineMetaTileEntity(symId(String.format("%s.%s", name, GTValues.VN[i + 1].toLowerCase())), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
        }
    }

    private static void registerCatalystMTE(CatalystMachineMetaTileEntity[] machines, int maxTier, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing) {
        registerCatalystMTE(machines, maxTier, startId, name, map, texture, hasFrontFacing, GTUtility.defaultTankSizeFunction);
    }

    static {

        STEAM_LATEX_COLLECTOR = new PseudoMultiSteamMachineMetaTileEntity[2];
        STEAM_VULCANIZING_PRESS = new SymtechSimpleSteamMetaTileEntity[2];
        STEAM_ROASTER = new SymtechSimpleSteamMetaTileEntity[2];

        STEAM_MIXER = new SymtechSimpleSteamMetaTileEntity[2];
        STEAM_VACUUM_CHAMBER = new SymtechSimpleSteamMetaTileEntity[2];

        LATEX_COLLECTOR = new PseudoMultiMachineMetaTileEntity[GTValues.EV];
        VULCANIZING_PRESS = new CatalystMachineMetaTileEntity[GTValues.EV];
        ROASTER = new CatalystMachineMetaTileEntity[GTValues.OpV];
        VACUUM_CHAMBER = new SimpleMachineMetaTileEntity[GTValues.OpV];

        CONTINUOUS_STIRRED_TANK_REACTOR = new ContinuousMachineMetaTileEntity[GTValues.OpV];
        FIXED_BED_REACTOR = new ContinuousMachineMetaTileEntity[GTValues.OpV];
        TRICKLE_BED_REACTOR = new ContinuousMachineMetaTileEntity[GTValues.OpV];
        BUBBLE_COLUMN_REACTOR = new ContinuousMachineMetaTileEntity[GTValues.OpV];

        BATCH_REACTOR = new SimpleMachineMetaTileEntity[GTValues.OpV];

        CRYSTALLIZER = new SimpleMachineMetaTileEntity[GTValues.OpV];
        DRYER = new SimpleMachineMetaTileEntity[GTValues.OpV];
        ION_EXCHANGE_COLUMN = new SimpleMachineMetaTileEntity[GTValues.OpV];
        ZONE_REFINER = new SimpleMachineMetaTileEntity[GTValues.OpV];
        TUBE_FURNACE = new SimpleMachineMetaTileEntity[GTValues.OpV];

        UV_LIGHT_BOX = new SimpleMachineMetaTileEntity[GTValues.OpV];
        CVD = new SimpleMachineMetaTileEntity[GTValues.OpV];
        ION_IMPLANTER = new SimpleMachineMetaTileEntity[GTValues.OpV];

        FLUID_COMPRESSOR = new SimpleMachineMetaTileEntity[GTValues.OpV];
        FLUID_DECOMPRESSOR = new SimpleMachineMetaTileEntity[GTValues.OpV];
        WEAPONS_FACTORY = new SimpleMachineMetaTileEntity[GTValues.OpV];

        ELECTROSTATIC_SEPARATOR = new SimpleMachineMetaTileEntity[GTValues.OpV];
        TEXTILE_SPINNER = new SimpleMachineMetaTileEntity[GTValues.OpV];
        POLISHING_MACHINE = new SimpleMachineMetaTileEntity[GTValues.OpV];

        PHASE_SEPARATOR = new SimpleMachineMetaTileEntity[1];
        BATH_CONDENSER = new SimpleMachineMetaTileEntity[1];
    }
}
