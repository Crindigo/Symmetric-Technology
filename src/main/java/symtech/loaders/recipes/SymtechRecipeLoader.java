package symtech.loaders.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.category.RecipeCategories;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import net.minecraft.item.ItemStack;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechStoneVariantBlock;
import symtech.common.item.SymtechMetaItems;
import symtech.common.metatileentities.SymtechMetaTileEntities;
import symtech.loaders.SymtechMetaTileEntityLoader;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Brass;
import static gregtech.api.unification.material.Materials.Lead;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK;
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
        registerStoneRecipes();
        registerDrumRecipes();

        //GTRecipeHandler.removeAllRecipes(ELECTROLYZER_RECIPES);

        // make more loaders to categorize recipes and what is added

        //RES Example Recipe

        /*
        TagCompound tag = new TagCompound();

        tag.setString("defID", "rolling_stock/locomotives/k4_pacific.json");
        tag.setFloat("gauge", (float) Gauges.STANDARD);

        cam72cam.mod.item.ItemStack is = new cam72cam.mod.item.ItemStack(IRItems.ITEM_ROLLING_STOCK, 1);
        is.setTagCompound(tag);
        SymtechRecipeMaps.RAILROAD_ENGINEERING_STATION_RECIPES.recipeBuilder()
                .input(plate, Materials.Steel)
                .input(plate, Materials.Iron)
                .outputs(is.internal)
                .EUt(GTValues.VA[4])
                .duration(1000)
                .buildAndRegister();

        SymtechRecipeMaps.RAILROAD_ENGINEERING_STATION_RECIPES.recipeBuilder()
                .inputNBT(IRItems.ITEM_ROLLING_STOCK.internal, NBTMatcher.EQUAL_TO, NBTCondition.create(NBTTagType.STRING, "defID", "rolling_stock/locomotives/black_mesa_tram.json"))
                .outputs(is.internal)
                .EUt(GTValues.VA[4])
                .duration(4000)
                .buildAndRegister();


        SymtechRecipeMaps.DRONE_PAD.recipeBuilder()
                .input(ingot, Materials.Iron)
                .output(Items.BEEF, 16)
                .duration(10)
                .dimension(0)
                .EUt(2)
                .buildAndRegister();
        */
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

    private static void registerStoneRecipes(){
        EnumMap<SymtechStoneVariantBlock.StoneVariant, List<ItemStack>> symtechVariantListMap = new EnumMap<>(SymtechStoneVariantBlock.StoneVariant.class);
        for (SymtechStoneVariantBlock.StoneVariant shape : SymtechStoneVariantBlock.StoneVariant.values()) {
            SymtechStoneVariantBlock block = SymtechBlocks.SYMTECH_STONE_BLOCKS.get(shape);
            symtechVariantListMap.put(shape,
                    Arrays.stream(SymtechStoneVariantBlock.StoneType.values())
                            .map(block::getItemVariant)
                            .collect(Collectors.toList()));
        }
        List<ItemStack> stcobbles = symtechVariantListMap.get(SymtechStoneVariantBlock.StoneVariant.COBBLE);
        List<ItemStack> stsmooths = symtechVariantListMap.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH);

        EnumMap<StoneVariantBlock.StoneVariant, List<ItemStack>> variantListMap = new EnumMap<>(StoneVariantBlock.StoneVariant.class);
        for (StoneVariantBlock.StoneVariant shape : StoneVariantBlock.StoneVariant.values()) {
            StoneVariantBlock block = MetaBlocks.STONE_BLOCKS.get(shape);
            variantListMap.put(shape,
                    Arrays.stream(StoneVariantBlock.StoneType.values())
                            .map(block::getItemVariant)
                            .collect(Collectors.toList()));
        }

        List<ItemStack> cobbles = variantListMap.get(StoneVariantBlock.StoneVariant.COBBLE);
        List<ItemStack> smooths = variantListMap.get(StoneVariantBlock.StoneVariant.SMOOTH);

        registerSmoothRecipe(stcobbles, stsmooths);
        registerCobbleRecipe(stsmooths, stcobbles);
        registerCobbleSmashingRecipe(stsmooths, stcobbles);
        registerCobbleSmashingRecipe(smooths, cobbles);
        registerMacerationToStoneDustRecipe();
    }

    private static void registerCobbleRecipe(List<ItemStack> smoothStack, List<ItemStack> cobbleStack) {
        for (int i = 0; i < smoothStack.size(); i++) {
            FORMING_PRESS_RECIPES.recipeBuilder()
                    .inputs(smoothStack.get(i))
                    .outputs(cobbleStack.get(i))
                    .duration(12).EUt(4).buildAndRegister();
        }
    }

    private static void registerSmoothRecipe(List<ItemStack> roughStack, List<ItemStack> smoothStack) {
        for (int i = 0; i < roughStack.size(); i++) {
            ModHandler.addSmeltingRecipe(roughStack.get(i), smoothStack.get(i), 0.1f);

            EXTRUDER_RECIPES.recipeBuilder()
                    .inputs(roughStack.get(i))
                    .notConsumable(SHAPE_EXTRUDER_BLOCK.getStackForm())
                    .outputs(smoothStack.get(i))
                    .duration(24).EUt(8).buildAndRegister();
        }
    }


    private static void registerCobbleSmashingRecipe(List<ItemStack> smoothStack, List<ItemStack> cobbleStack) {
        for (int i = 0; i < smoothStack.size(); i++) {

            ModHandler.addShapedRecipe(smoothStack.get(i).getDisplayName() + "_hammer_smashing", cobbleStack.get(i), new Object[]{"hS", 'S', smoothStack.get(i)});
        }
    }

    private static void registerMacerationToStoneDustRecipe() {
        for (SymtechStoneVariantBlock.StoneType stoneType : SymtechStoneVariantBlock.StoneType.values()) {
            MACERATOR_RECIPES.recipeBuilder()
                    .inputs(SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(stoneType))
                    .output(dust, stoneType.getMaterial())
                    .category(RecipeCategories.MACERATOR_RECYCLING)
                    .buildAndRegister();
            MACERATOR_RECIPES.recipeBuilder()
                    .inputs(SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.COBBLE).getItemVariant(stoneType))
                    .output(dust, stoneType.getMaterial())
                    .category(RecipeCategories.MACERATOR_RECYCLING)
                    .buildAndRegister();
        }
    }
}
