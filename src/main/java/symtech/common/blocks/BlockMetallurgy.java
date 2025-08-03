package symtech.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import symtech.api.blocks.VariantDirectionalRotatableBlock;

public class BlockMetallurgy extends VariantDirectionalRotatableBlock<BlockMetallurgy.BlockMetallurgyType> {
    public BlockMetallurgy() {
        super(Material.IRON);
        setTranslationKey("metallurgy");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(BlockMetallurgyType.HYDRAULIC_CYLINDER));
    }

    public enum BlockMetallurgyType implements IStringSerializable {
        HYDRAULIC_CYLINDER("hydraulic_cylinder");
        private String name;

        BlockMetallurgyType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
