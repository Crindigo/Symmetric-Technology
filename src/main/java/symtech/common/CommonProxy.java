package symtech.common;

import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.modules.ModuleContainerRegistryEvent;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.MaterialRegistryEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.items.MetaItems;
import gregtech.modules.ModuleManager;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.File;

import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.GeckoLib;
import symtech.Symtech;
import symtech.api.blocks.VariantItemBlockFalling;
import symtech.api.fluids.SymtechGeneratedFluidHandler;
import symtech.api.unification.ore.SymtechOrePrefix;
import symtech.api.unification.ore.SymtechStoneTypes;
import symtech.common.blocks.SheetedFrameItemBlock;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechMetaBlocks;
import symtech.common.blocks.SymtechStoneVariantBlock;
import symtech.common.item.SymtechMetaItems;
import symtech.common.materials.SymtechMaterials;
import symtech.loaders.SymtechOreDictionaryLoader;
import symtech.loaders.recipes.SymtechRecipeLoader;
import symtech.modules.SymtechModules;

import java.util.Objects;
import java.util.function.Function;

import static symtech.common.blocks.SymtechMetaBlocks.SHEETED_FRAMES;

@Mod.EventBusSubscriber(modid = Symtech.MODID)
public class CommonProxy {

    public void preLoad() {
        GeckoLib.initialize();
        SymtechStoneTypes.init();
    }

    /**
     * Recursively deletes a directory and all its contents.
     *
     * @param directory the directory to delete
     * @return true if the directory was successfully deleted, false otherwise
     */
    private boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        return directory.delete();
    }

    public void load() {
    }

    @SubscribeEvent
    public static void registerBlocks(@NotNull RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(SymtechBlocks.COOLING_COIL);
        registry.register(SymtechBlocks.SINTERING_BRICK);
        registry.register(SymtechBlocks.COAGULATION_TANK_WALL);
        for (SymtechStoneVariantBlock block : SymtechBlocks.SYMTECH_STONE_BLOCKS.values()) registry.register(block);
        registry.register(SymtechBlocks.ALTERNATOR_COIL);
        registry.register(SymtechBlocks.TURBINE_ROTOR);
        registry.register(SymtechBlocks.SEPARATOR_ROTOR);
        registry.register(SymtechBlocks.STRUCTURAL_BLOCK);
        registry.register(SymtechBlocks.STRUCTURAL_BLOCK_1);
        registry.register(SymtechBlocks.DRILL_HEAD);
        registry.register(SymtechBlocks.DRILL_BIT);
        registry.register(SymtechBlocks.DEPOSIT_BLOCK);
        registry.register(SymtechBlocks.RESOURCE_BLOCK);
        registry.register(SymtechBlocks.RESOURCE_BLOCK_1);
        registry.register(SymtechBlocks.MULTIBLOCK_TANK);
        registry.register(SymtechBlocks.EVAPORATION_BED);
        registry.register(SymtechBlocks.ELECTRODE_ASSEMBLY);
        registry.register(SymtechBlocks.MULTIBLOCK_CASING);
        registry.register(SymtechBlocks.SERPENTINE);
        registry.register(SymtechBlocks.HARDBLOCKS);
        registry.register(SymtechBlocks.HARDBLOCKS1);
        registry.register(SymtechBlocks.CUSTOMSHEETS);
        registry.register(SymtechBlocks.METALLURGY);
        registry.register(SymtechBlocks.METALLURGY_2);
        registry.register(SymtechBlocks.METALLURGY_ROLL);
        registry.register(SymtechBlocks.CONVEYOR_BELT);
        registry.register(SymtechBlocks.ROCKET_ASSEMBLER_CASING);
        registry.register(SymtechBlocks.REGOLITH);

        SHEETED_FRAMES.values().stream().distinct().forEach(registry::register);
    }

    @SubscribeEvent
    public static void registerItems(@NotNull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        SymtechMetaItems.initSubItems();

        registry.register(createItemBlock(SymtechBlocks.COOLING_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.SINTERING_BRICK, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.COAGULATION_TANK_WALL, VariantItemBlock::new));
        for (SymtechStoneVariantBlock block : SymtechBlocks.SYMTECH_STONE_BLOCKS.values())
            registry.register(createItemBlock(block, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.ALTERNATOR_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.DRILL_HEAD, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.DRILL_BIT, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.TURBINE_ROTOR, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.SEPARATOR_ROTOR, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.STRUCTURAL_BLOCK, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.STRUCTURAL_BLOCK_1, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.DEPOSIT_BLOCK, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.RESOURCE_BLOCK, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.RESOURCE_BLOCK_1, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.EVAPORATION_BED, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.MULTIBLOCK_TANK, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.ELECTRODE_ASSEMBLY, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.MULTIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.SERPENTINE, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.HARDBLOCKS, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.HARDBLOCKS1, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.CUSTOMSHEETS, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.METALLURGY, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.METALLURGY_2, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.METALLURGY_ROLL, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.CONVEYOR_BELT, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.ROCKET_ASSEMBLER_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(SymtechBlocks.REGOLITH, VariantItemBlockFalling::new));

        SHEETED_FRAMES.values()
                .stream().distinct()
                .map(block -> createItemBlock(block, SheetedFrameItemBlock::new))
                .forEach(registry::register);
    }

    @SubscribeEvent
    public static void createMaterialRegistry(MaterialRegistryEvent event) {
        GregTechAPI.materialManager.createRegistry(Symtech.MODID);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(@NotNull MaterialEvent event) {
        SymtechMaterials.init();
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void postRegisterMaterials(@NotNull PostMaterialEvent event) {
        MetaItems.addOrePrefix(SymtechOrePrefix.catalystPellet);
        MetaItems.addOrePrefix(SymtechOrePrefix.catalystBed);
        MetaItems.addOrePrefix(SymtechOrePrefix.flotated);
        MetaItems.addOrePrefix(SymtechOrePrefix.sifted);
        MetaItems.addOrePrefix(SymtechOrePrefix.concentrate);
        MetaItems.addOrePrefix(SymtechOrePrefix.fiber);
        MetaItems.addOrePrefix(SymtechOrePrefix.wetFiber);
        MetaItems.addOrePrefix(SymtechOrePrefix.thread);
        MetaItems.addOrePrefix(SymtechOrePrefix.dustWet);
        MetaItems.addOrePrefix(SymtechOrePrefix.electrode);

        Materials.Aluminium.addFlags("continuously_cast");
        SymtechGeneratedFluidHandler.init();

        //SymtechMaterials.removeFlags();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void itemToolTip(ItemTooltipEvent event) {
        handleCoilTooltips(event);
        addTooltip(event, "symtech.machine.steam_extractor", TooltipHelper.BLINKING_ORANGE + I18n.format("symtech.machine.steam_extractor_cannot_melt_items.warning"), 2);
    }

    private static void handleCoilTooltips(ItemTooltipEvent event) {
        Block block = Block.getBlockFromItem(event.getItemStack().getItem());
        if(block instanceof BlockWireCoil && TooltipHelper.isShiftDown()) {
            ItemStack itemStack = event.getItemStack();
            Item item = itemStack.getItem();
            BlockWireCoil wireCoilBlock = (BlockWireCoil)block;
            VariantItemBlock itemBlock = (VariantItemBlock)item;
            BlockWireCoil.CoilType coilType = (BlockWireCoil.CoilType)wireCoilBlock.getState(itemBlock.getBlockState(itemStack));
            event.getToolTip().add(I18n.format("tile.wire_coil.tooltip_evaporation", new Object[0]));
            event.getToolTip().add(I18n.format("tile.wire_coil.tooltip_energy_evaporating", new Object[]{coilType.getCoilTemperature()/1000}));
        }
    }

    // Since this function checks if the key is in the translation key, you can sometimes add tooltips to multiple items
    //   with a single call of the function. Useful for hitting both basic and high pressure steam machines, for example.
    private static void addTooltip(ItemTooltipEvent event, String key, String toolTip, int index) {
        if(event.getItemStack().getTranslationKey().contains(key)) {
            event.getToolTip().add(index, toolTip);
        }
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        SymtechOreDictionaryLoader.init();
        SymtechMetaBlocks.registerOreDict();
        SymtechRecipeLoader.init();
    }

    @SubscribeEvent
    public static void registerModuleContainer(ModuleContainerRegistryEvent event) {
        ModuleManager.getInstance().registerContainer(new SymtechModules());
    }


    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }
}
