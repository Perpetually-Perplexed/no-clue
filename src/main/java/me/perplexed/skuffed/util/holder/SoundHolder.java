package me.perplexed.skuffed.util.holder;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.perplexed.skuffed.SkuffedMod.ID;

public class SoundHolder {

    public static final SoundEvent MONEY_MUSIC = register("money_disc");

    private static SoundEvent register(String name) {
        var id = new Identifier(ID,name);
        return Registry.register(Registry.SOUND_EVENT,id,new SoundEvent(id));
    }
}
