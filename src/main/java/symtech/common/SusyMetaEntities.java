package symtech.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import symtech.Symtech;
import symtech.client.renderer.handler.DroneRenderer;
import symtech.common.entities.EntityDrone;

public class SusyMetaEntities {

    public static void init() {
        EntityRegistry.registerModEntity(new ResourceLocation(Symtech.MODID, "drone"), EntityDrone.class, "Drone", 2, Symtech.instance, 64, 3, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDrone.class, DroneRenderer::new);
    }

}
