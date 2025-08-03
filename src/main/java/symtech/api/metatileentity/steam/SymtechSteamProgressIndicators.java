package symtech.api.metatileentity.steam;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import symtech.api.gui.SymtechGuiTextures;

public class SymtechSteamProgressIndicators {

    public static final SymtechSteamProgressIndicator COMPRESS = new SymtechSteamProgressIndicator(GuiTextures.PROGRESS_BAR_COMPRESS_STEAM, ProgressWidget.MoveType.HORIZONTAL, 20, 15);
    public static final SymtechSteamProgressIndicator ARROW = new SymtechSteamProgressIndicator(GuiTextures.PROGRESS_BAR_ARROW_STEAM, ProgressWidget.MoveType.HORIZONTAL, 20, 15);
    public static final SymtechSteamProgressIndicator MIXER = new SymtechSteamProgressIndicator(SymtechGuiTextures.PROGRESS_BAR_MIXER_STEAM, ProgressWidget.MoveType.CIRCULAR, 20, 20);
    public static final SymtechSteamProgressIndicator EXTRACTION_STEAM = new SymtechSteamProgressIndicator(SymtechGuiTextures.PROGRESS_BAR_EXTRACTION_STEAM, ProgressWidget.MoveType.VERTICAL_DOWNWARDS, 20, 20);

}
