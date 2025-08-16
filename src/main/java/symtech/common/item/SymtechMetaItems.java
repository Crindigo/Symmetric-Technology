package symtech.common.item;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem.MetaValueItem;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.items.metaitem.MetaOreDictItem.OreDictValueItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.common.items.behaviors.TooltipBehavior;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumDyeColor;
import symtech.SymtechValues;
import symtech.common.item.behavior.PipeNetPainterBehavior;

import static gregtech.common.items.MetaItems.SPRAY_EMPTY;

public class SymtechMetaItems {

    private static StandardMetaItem metaItem;
    public static MetaOreDictItem oreDictItem;
    public static MetaValueItem CATALYST_BED_SUPPORT_GRID;
    public static MetaValueItem CONVEYOR_STEAM;
    public static MetaValueItem PUMP_STEAM;
    public static MetaValueItem AIR_VENT;
    public static MetaValueItem RESTRICTIVE_FILTER;
    public static MetaValueItem MOTOR_STEAM;
    public static MetaValueItem PISTON_STEAM;
    public static OreDictValueItem CARBON_MOLECULAR_SIEVE;

    public static void initMetaItems() {
        metaItem = new StandardMetaItem();
        metaItem.setRegistryName("symtech:meta_item");
        oreDictItem = new MetaOreDictItem((short) 0);
        oreDictItem.setRegistryName("symtech:ore_dict_item");
        CatalystItems.init();
        initOreDictItems();
    }

    public static void initSubItems() {
        initMetaItem();
        CatalystItems.initCatalysts();
    }

    private static void initMetaItem() {
        addExtraBehaviours();

        // initialize metaitems here
        CATALYST_BED_SUPPORT_GRID = metaItem.addItem(1, "catalyst_bed_support_grid");
        CONVEYOR_STEAM = metaItem.addItem(2, "conveyor.steam").addComponents(new TooltipBehavior((lines) -> {
            lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
            lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", new Object[]{4}));
        }));
        PUMP_STEAM = metaItem.addItem(3, "pump.steam").addComponents(new TooltipBehavior((lines) -> {
            lines.add(I18n.format("metaitem.electric.pump.tooltip"));
            lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", new Object[]{32}));
        }));
        AIR_VENT = metaItem.addItem(4, "air_vent").addComponents(new TooltipBehavior((lines) -> {
            lines.add(I18n.format("metaitem.air_vent.tooltip.1", 100));
        }));

        RESTRICTIVE_FILTER = metaItem.addItem(6, "restrictive_filter");

        MOTOR_STEAM = metaItem.addItem(7, "steam.motor");
        PISTON_STEAM = metaItem.addItem(8, "steam.piston");
    }

    private static void initOreDictItems() {
        CARBON_MOLECULAR_SIEVE = oreDictItem.addOreDictItem(100, "carbon_molecular_sieve",
                0x101010, MaterialIconSet.SHINY, OrePrefix.dust);
    }

    private static void addExtraBehaviours() {
        MetaItems.SPRAY_SOLVENT.addComponents(new PipeNetPainterBehavior(1024, SPRAY_EMPTY.getStackForm(), -1));
        for (int i = 0; i < EnumDyeColor.values().length; i++) {
            MetaItems.SPRAY_CAN_DYES[i].addComponents(new PipeNetPainterBehavior(512, SPRAY_EMPTY.getStackForm(), i));
        }
    }

    private static void addTieredOredictItem(OreDictValueItem[] items, int id, int RGB, OrePrefix prefix) {

        for (int i = 0; i < items.length; i++) {
            items[i] = oreDictItem.addOreDictItem(id + i, SymtechValues.TierMaterials[i + 1].toString(), RGB, MaterialIconSet.DULL, prefix, I18n.format("symtech.universal.catalysts.tooltip.tier", GTValues.V[i], GTValues.VN[i]));
        }

    }
}
