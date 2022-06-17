package net.seakerman.rangefinderhud;

import net.fabricmc.api.ClientModInitializer;
import net.seakerman.rangefinderhud.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RangefinderHUD implements ClientModInitializer {
	public static Config rangefinderHUDConfigData;
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("rangefinderhud");

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("RangefinderHUD started");
		rfh$loadConfig();
	}

	// config code based on bedrockify & actually unbreaking fabric config code
	// https://github.com/juancarloscp52/BedrockIfy/blob/1.17.x/src/main/java/me/juancarloscp52/bedrockify/Bedrockify.java
	// https://github.com/wutdahack/ActuallyUnbreakingFabric/blob/1.18.1/src/main/java/wutdahack/actuallyunbreaking/ActuallyUnbreaking.java
	public static void rfh$loadConfig() {
		File config = new File(FabricLoader.getInstance().getConfigDir().toFile(), "rangefinderhud.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (config.exists()) {
			try {
				FileReader fileReader = new FileReader(config);
				rangefinderHUDConfigData = gson.fromJson(fileReader, Config.class);
				fileReader.close();
				saveConfig();
			} catch (IOException e) {
				System.out.println("Config could not be loaded, using defaults");
			}
		} else {
			rangefinderHUDConfigData = new Config();
			saveConfig();
		}
	}

	public static void saveConfig() {
		File config = new File(FabricLoader.getInstance().getConfigDir().toFile(), "rangefinderhud.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (!config.getParentFile().exists()) {
			//noinspection ResultOfMethodCallIgnored
			config.getParentFile().mkdir();
		}
		try {
			FileWriter fileWriter = new FileWriter(config);
			fileWriter.write(gson.toJson(rangefinderHUDConfigData));
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Config file could not be saved");
		}
	}
}
