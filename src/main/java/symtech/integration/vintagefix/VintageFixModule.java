package symtech.integration.vintagefix;

import gregtech.api.modules.GregTechModule;
import gregtech.integration.IntegrationSubmodule;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.embeddedt.vintagefix.dynamicresources.model.DynamicBakedModelProvider;
import org.embeddedt.vintagefix.event.DynamicModelBakeEvent;
import org.jetbrains.annotations.NotNull;
import symtech.Symtech;
import symtech.mixins.vintagefix.LampBakedModelAccessor;
import symtech.mixins.vintagefix.LampBakedModelAccessor.EntryAccessor;
import symtech.mixins.vintagefix.LampBakedModelAccessor.KeyAccessor;
import symtech.modules.SymtechModules;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@GregTechModule(
        moduleID = SymtechModules.MODULE_VINTAGEFIX,
        containerID = Symtech.MODID,
        modDependencies = "vintagefix",
        name = "Symtech VintageFix Integration",
        description = "Symtech VintageFix Integration Module")
public class VintageFixModule extends IntegrationSubmodule {

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return Collections.singletonList(VintageFixModule.class);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onDynModelBake(DynamicModelBakeEvent event) {
        if(!(event.location instanceof ModelResourceLocation)) return;

        for (Map.Entry<KeyAccessor, EntryAccessor> e : LampBakedModelAccessor.getEntries().entrySet()) {
            var entry = e.getValue();
            if (entry.getOriginalModel() == event.location) {
                if (entry.getCustomItemModel() != null) {
                    IBakedModel model = event.bakedModel;
                    if (model != null) {
                        // Directly provide existing model to prevent using CTM models
                        IBakedModel customModel = e.getKey().getModelType().createModel(model);
                        DynamicBakedModelProvider.instance.putObject(entry.getCustomItemModel(), customModel);
                    }
                }
            }
        }
    }
}
