package symtech.api.unification.ore;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import symtech.common.blocks.SymtechBlocks;
import symtech.common.blocks.SymtechStoneVariantBlock;
import symtech.common.materials.SymtechMaterials;

public class SymtechStoneTypes {
    public static StoneType GABBRO;
    public static StoneType GNEISS;
    public static StoneType GRAPHITE;
    public static StoneType LIMESTONE;
    public static StoneType MICA;
    public static StoneType PHYLLITE;
    public static StoneType QUARTZITE;
    public static StoneType SHALE;
    public static StoneType SLATE;
    public static StoneType SOAPSTONE;
    public static StoneType KIMBERLITE;

    public static StoneType ANORTHOSITE;

    public SymtechStoneTypes(){
    }

    public static void init(){
        GABBRO = new StoneType(12, "gabbro", SoundType.STONE, SymtechOrePrefix.oreGabbro, SymtechMaterials.Gabbro,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.GABBRO),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.GABBRO), false);
        GNEISS = new StoneType(13, "gneiss", SoundType.STONE, SymtechOrePrefix.oreGneiss, SymtechMaterials.Gneiss,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.GNEISS),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.GNEISS), false);
        LIMESTONE = new StoneType(14, "limestone", SoundType.STONE, SymtechOrePrefix.oreLimestone, SymtechMaterials.Limestone,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.LIMESTONE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.LIMESTONE), false);
        PHYLLITE = new StoneType(15, "phyllite", SoundType.STONE, SymtechOrePrefix.orePhyllite, SymtechMaterials.Phyllite,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.PHYLLITE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.PHYLLITE), false);
        QUARTZITE = new StoneType(16, "quartzite", SoundType.STONE, SymtechOrePrefix.oreQuartzite, Materials.Quartzite,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.QUARTZITE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.QUARTZITE), false);
        SHALE = new StoneType(17, "shale", SoundType.STONE, SymtechOrePrefix.oreShale, SymtechMaterials.Shale,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.SHALE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.SHALE), false);
        SLATE = new StoneType(18, "slate", SoundType.STONE, SymtechOrePrefix.oreSlate, SymtechMaterials.Slate,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.SLATE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.SLATE), false);
        SOAPSTONE = new StoneType(19, "soapstone", SoundType.STONE, SymtechOrePrefix.oreSoapstone, Materials.Soapstone,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.SOAPSTONE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.SOAPSTONE), false);
        KIMBERLITE = new StoneType(20, "kimberlite", SoundType.STONE, SymtechOrePrefix.oreKimberlite, SymtechMaterials.Kimberlite,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.KIMBERLITE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.KIMBERLITE), false);
        ANORTHOSITE = new StoneType(21, "anorthosite", SoundType.STONE, SymtechOrePrefix.oreAnorthosite, SymtechMaterials.Anorthosite,
                () -> gtStoneState(SymtechStoneVariantBlock.StoneType.ANORTHOSITE),
                state -> gtStonePredicate(state, SymtechStoneVariantBlock.StoneType.ANORTHOSITE), false);

        if (ConfigHolder.worldgen.allUniqueStoneTypes) {
            addSecondary(SymtechOrePrefix.oreGabbro, SymtechMaterials.Gabbro);
            addSecondary(SymtechOrePrefix.oreGneiss, SymtechMaterials.Gneiss);
            addSecondary(SymtechOrePrefix.oreLimestone, SymtechMaterials.Limestone);
            addSecondary(SymtechOrePrefix.orePhyllite, SymtechMaterials.Phyllite);
            addSecondary(SymtechOrePrefix.oreQuartzite, Materials.Quartzite);
            addSecondary(SymtechOrePrefix.oreShale, SymtechMaterials.Shale);
            addSecondary(SymtechOrePrefix.oreSlate, SymtechMaterials.Slate);
            addSecondary(SymtechOrePrefix.oreSoapstone, Materials.Soapstone);
            addSecondary(SymtechOrePrefix.oreKimberlite, SymtechMaterials.Kimberlite);
            addSecondary(SymtechOrePrefix.oreAnorthosite, SymtechMaterials.Anorthosite);
        }
    }

    private static void addSecondary(OrePrefix prefix, Material material) {
        prefix.addSecondaryMaterial(new MaterialStack(material, OrePrefix.dust.getMaterialAmount(material)));
    }

    private static IBlockState gtStoneState(SymtechStoneVariantBlock.StoneType stoneType) {
        return SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH).getState(stoneType);
    }

    private static boolean gtStonePredicate(IBlockState state, SymtechStoneVariantBlock.StoneType stoneType) {
        SymtechStoneVariantBlock block = SymtechBlocks.SYMTECH_STONE_BLOCKS.get(SymtechStoneVariantBlock.StoneVariant.SMOOTH);
        return state.getBlock() == block && block.getState(state) == stoneType;
    }
}
