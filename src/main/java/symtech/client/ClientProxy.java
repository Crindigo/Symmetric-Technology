package symtech.client;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.input.KeyBind;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import symtech.SymtechValues;
import symtech.Symtech;
import symtech.api.recipes.catalysts.CatalystGroup;
import symtech.api.recipes.catalysts.CatalystInfo;
import symtech.common.CommonProxy;
import symtech.common.SusyMetaEntities;
import symtech.common.blocks.SheetedFrameItemBlock;
import symtech.common.blocks.SuSyBlocks;
import symtech.common.blocks.SuSyMetaBlocks;
import symtech.common.item.SuSyMetaItems;
import symtech.common.item.behavior.PipeNetWalkerBehavior;
import symtech.loaders.SuSyFluidTooltipLoader;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Symtech.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static int titleRenderTimer = -1;
    private static final int TITLE_RENDER_LENGTH = 150;

    public void preLoad() {
        super.preLoad();
        SusyMetaEntities.initRenderers();
    }

    @Override
    public void load() {
        super.load();
        SuSyMetaBlocks.registerColors();
        SuSyFluidTooltipLoader.registerTooltips();
    }

    @SubscribeEvent
    public static void addMaterialFormulaHandler(@Nonnull ItemTooltipEvent event) {
        //ensure itemstack is a sheetedframe
        ItemStack itemStack = event.getItemStack();
        if (!(itemStack.getItem() instanceof SheetedFrameItemBlock)) return;

        UnificationEntry unificationEntry = OreDictUnifier.getUnificationEntry(itemStack);

        //ensure chemical composition does exist to be added
        if (unificationEntry != null && unificationEntry.material != null) {
            if (unificationEntry.material.getChemicalFormula() != null && !unificationEntry.material.getChemicalFormula().isEmpty())
                //pretty YELLOW is being auto-converted to a string
                event.getToolTip().add(TextFormatting.YELLOW + unificationEntry.material.getChemicalFormula());
        }
    }

    @SubscribeEvent
    public static void addPipelinerTooltip(@Nonnull ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<String> tooltips = event.getToolTip();

        if (stack.getItem() instanceof IGTTool tool
                && tool.getToolStats().getBehaviors().contains(PipeNetWalkerBehavior.INSTANCE)) {
            tooltips.add(I18n.format("item.susy.tool.tooltip.pipeliner",
                    GameSettings.getKeyDisplayString(KeyBind.TOOL_AOE_CHANGE.toMinecraft().getKeyCode())));
        }
    }

    @SubscribeEvent
    public static void addCatalystTooltipHandler(@Nonnull ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        // Handles Item tooltips
        Collection<String> tooltips = new ArrayList<>();

        if (itemStack.getItem() instanceof MetaOreDictItem oreDictItem) { // Test for OreDictItems
            Optional<String> oreDictName = OreDictUnifier.getOreDictionaryNames(itemStack).stream().findFirst();
            if (oreDictName.isPresent() && oreDictItem.OREDICT_TO_FORMULA.containsKey(oreDictName.get()) && !oreDictItem.OREDICT_TO_FORMULA.get(oreDictName.get()).isEmpty()) {
                tooltips.add(TextFormatting.YELLOW + oreDictItem.OREDICT_TO_FORMULA.get(oreDictName.get()));
            }
        }

        for (CatalystGroup group :
                CatalystGroup.getCatalystGroups()) {
            ItemStack is = itemStack.copy();
            is.setCount(1);
            CatalystInfo catalystInfo = group.getCatalystInfos().get(is);
            if (catalystInfo != null) {
                tooltips.add(TextFormatting.UNDERLINE + (TextFormatting.BLUE + I18n.format("susy.catalyst_group." + group.getName() + ".name")));
                if(catalystInfo.getTier() == CatalystInfo.NO_TIER){
                    tooltips.add(TextFormatting.RED + "Disclaimer: Catalyst bonuses for non-tiered catalysts have not yet been implemented.");
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.yield", catalystInfo.getYieldEfficiency()));
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.energy", catalystInfo.getEnergyEfficiency()));
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.speed", catalystInfo.getSpeedEfficiency()));
                } else {
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.tier", GTValues.V[catalystInfo.getTier()], GTValues.VNF[catalystInfo.getTier()]));
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.yield.tiered", catalystInfo.getYieldEfficiency()));
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.energy.tiered", catalystInfo.getEnergyEfficiency()));
                    tooltips.add(I18n.format("susy.universal.catalysts.tooltip.speed.tiered", catalystInfo.getSpeedEfficiency()));
                }
            }
        }

        event.getToolTip().addAll(tooltips);
    }

    @SubscribeEvent
    public static void registerModels(@NotNull ModelRegistryEvent event) {
        SuSyBlocks.registerItemModels();
        SuSyMetaBlocks.registerItemModels();
    }
}
