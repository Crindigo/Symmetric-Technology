package symtech.loaders;

import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechStoneVariantBlock;


public class SymtechOreDictionaryLoader {
    public static void init(){
        loadStoneOredict();
    }

    public static void loadStoneOredict(){

        for (SymtechStoneVariantBlock.StoneType type : SymtechStoneVariantBlock.StoneType.values()) {
            ItemStack smooth = SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(type);
            ItemStack cobble = SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.COBBLE).getItemVariant(type);
            OreDictUnifier.registerOre(smooth, type.getOrePrefix(), type.getMaterial());
        }

        for (StoneVariantBlock.StoneType type : StoneVariantBlock.StoneType.values()) {
            ItemStack smooth = MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(type);
            ItemStack cobble = MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.COBBLE).getItemVariant(type);
        }

        // For IR railbeds
        ItemStack concreteLightSmooth = MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH).getItemVariant(StoneVariantBlock.StoneType.CONCRETE_LIGHT);
        OreDictionary.registerOre("railBed", concreteLightSmooth);
    }
}
