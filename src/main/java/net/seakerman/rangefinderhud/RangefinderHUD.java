package net.seakerman.rangefinderhud;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.seakerman.rangefinderhud.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class RangefinderHUD implements ClientModInitializer {
	public static ModConfig config;
	public static final Logger LOGGER = LoggerFactory.getLogger("RangefinderHUD");

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("RangefinderHUD started");
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		LOGGER.info("Initialized successfully.");
	}

}
