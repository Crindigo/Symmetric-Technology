package symtech.api.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import symtech.Symtech;

public class SymtechSounds {

    public static SoundEvent DRONE_TAKEOFF;

    public static void registerSounds() {
        DRONE_TAKEOFF = registerSound("entity.drone_takeoff");
    }

    private static SoundEvent registerSound(String soundNameIn) {
        ResourceLocation location = new ResourceLocation(Symtech.MODID, soundNameIn);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

}
