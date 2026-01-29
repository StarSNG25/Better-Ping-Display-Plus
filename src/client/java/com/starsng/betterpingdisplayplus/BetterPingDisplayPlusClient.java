package com.starsng.betterpingdisplayplus;

import static com.starsng.betterpingdisplayplus.BetterPingDisplayPlus.LOGGER;
import static com.starsng.betterpingdisplayplus.BetterPingDisplayPlus.MOD_ID;

import com.starsng.betterpingdisplayplus.config.Config;
import java.io.File;
import java.nio.file.Path;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class BetterPingDisplayPlusClient implements ClientModInitializer
{
	public static BetterPingDisplayPlusClient INSTANCE;
	
	private static Path configFilePath;
	private static final String CONFIG_FILE_NAME = MOD_ID + ".json";
	
	private Config config = Config.fromDefault();
	
	@Override
	public void onInitializeClient()
	{
		INSTANCE = this;
		
		configFilePath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME);
		File configFile = configFilePath.toFile();
		
		if (configFile.exists())
			try
			{
				config = Config.fromFile(configFile);
				config.writeToFile(configFile);
			}
			catch (Exception ex)
			{
				LOGGER.error("Failed to load config file", ex);
			}
		else
			try
			{
				LOGGER.warn("Could not find config file, creating a default one");
				config.writeToFile(configFile);
			}
			catch (Exception ex)
			{
				LOGGER.error("Failed to write default config file", ex);
			}
		
		LOGGER.info(MOD_ID + " client initialized");
	}
	
	public static BetterPingDisplayPlusClient getInstance()
	{
		return INSTANCE;
	}
	
	public Config getConfig()
	{
		return config;
	}
	
	public Path getConfigFilePath()
	{
		return configFilePath;
	}
}
