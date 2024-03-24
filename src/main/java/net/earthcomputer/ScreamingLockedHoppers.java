package net.earthcomputer;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ScreamingLockedHoppers implements ModInitializer {
	public static final SoundEvent SCREAMING_SOUND = SoundEvent.of(new Identifier("screaming-locked-hoppers", "hopper_screams"), 32);
	public static final long MIN_TIME_BETWEEN_SCREAMS = 40;

	@Override
	public void onInitialize() {
		Registry.register(Registries.SOUND_EVENT, new Identifier("screaming-locked-hoppers", "hopper_screams"), SCREAMING_SOUND);
	}
}