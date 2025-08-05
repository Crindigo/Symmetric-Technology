package symtech.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import symtech.Symtech;

@Config(modid = Symtech.MODID)
public class STConfigHolder {

    @Comment("Options for machines")
    public static MachineOptions machine = new MachineOptions();

    @Comment("Options for recipes")
    public static RecipeOptions recipe = new RecipeOptions();

    public static class MachineOptions {

        @Comment({"Max parallel for the Fermentation Vat multi.", "Default: 8"})
        @Config.RangeInt(min = 1, max = 64)
        public int fermentationVatMaxParallel = 8;
    }

    public static class RecipeOptions {

        @Comment({
                "Use alternate recipes for CEU steam macerator, extractor, compressor, forge hammer,",
                "and rock breaker (uses steam pistons, etc.)",
                "Default: false"
        })
        public boolean alternateCEUSteamMachines = false;
    }
}
