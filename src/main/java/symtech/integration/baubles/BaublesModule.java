package symtech.integration.baubles;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import gregtech.api.modules.GregTechModule;
import gregtech.common.items.MetaItems;
import gregtech.integration.IntegrationSubmodule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import symtech.Symtech;
import symtech.api.SymtechLog;
import symtech.common.item.behavior.ArmorBaubleBehavior;
import symtech.modules.SymtechModules;

import java.util.Collections;
import java.util.List;

@GregTechModule(
        moduleID = SymtechModules.MODULE_BAUBLES,
        containerID = Symtech.MODID,
        modDependencies = "baubles",
        name = "Symtech Baubles Integration",
        description = "Symtech Baubles Integration Module")
public class BaublesModule extends IntegrationSubmodule {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        MetaItems.SEMIFLUID_JETPACK.addComponents(new ArmorBaubleBehavior(BaubleType.BODY));
        MetaItems.ELECTRIC_JETPACK.addComponents(new ArmorBaubleBehavior(BaubleType.BODY));
        MetaItems.ELECTRIC_JETPACK_ADVANCED.addComponents(new ArmorBaubleBehavior(BaubleType.BODY));
        MetaItems.NIGHTVISION_GOGGLES.addComponents(new ArmorBaubleBehavior(BaubleType.HEAD));
    }

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return Collections.singletonList(BaublesModule.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        SymtechLog.logger.info("Baubles found. Enabling integration...");
    }

    public static ItemStack getElytraBauble(@NotNull EntityLivingBase entity) {
        if (entity instanceof EntityPlayer player) {
            // The body slot is 5
            return BaublesApi.getBaublesHandler(player).getStackInSlot(5);
        }
        return ItemStack.EMPTY;
    }
}
