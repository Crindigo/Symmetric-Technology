package symtech.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;

import java.util.WeakHashMap;

// My endless gratitude to the AdvancedRocketry team for the gravity code

public class GravityHandler {
    public static final float LIVING_OFFSET = 0.0755f;
    public static final float THROWABLE_OFFSET = 0.03f;
    public static final float OTHER_OFFSET = 0.04f;
    public static final float ARROW_OFFSET = 0.05f;

    private static WeakHashMap<Entity, Double> entityMap = new WeakHashMap<>();
    public static void applyGravity(Entity entity) {
        if (entity.hasNoGravity()) return;
        //Because working gravity on elytra-flying players can cause..... severe problems at lower gravity, it is my utter delight to announce to you elytra are now magic!
        //This totally isn't because Mojang decided for some godforsaken @#@#@#% reason to make ALL WAYS TO SET ELYTRA FLIGHT _protected_
        //With no set methods
        //So I cannot, without much more effort than it's worth, set elytra flight. Therefore, they're magic.
        if ((!(entity instanceof EntityPlayer) && !(entity instanceof EntityFlying)) || (!(entity instanceof EntityFlying) && !(((EntityPlayer) entity).capabilities.isFlying || ((EntityLivingBase) entity).isElytraFlying()))) {
            Double d;
            if (entityMap.containsKey(entity) && (d = entityMap.get(entity)) != null) {

                double multiplier = (isOtherEntity(entity) || entity instanceof EntityItem) ? OTHER_OFFSET * d : (entity instanceof EntityArrow) ? ARROW_OFFSET * d : (entity instanceof EntityThrowable) ? THROWABLE_OFFSET * d : LIVING_OFFSET * d;

                entity.motionY += multiplier;
            }
        }
    }

    public static boolean isOtherEntity(Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart || entity instanceof EntityFallingBlock || entity instanceof EntityTNTPrimed;
    }
}
