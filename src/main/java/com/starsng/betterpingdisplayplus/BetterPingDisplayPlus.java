package com.starsng.betterpingdisplayplus;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterPingDisplayPlus implements ModInitializer
{
	public static final String MOD_ID = "better-ping-display-plus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize()
	{
		LOGGER.info(MOD_ID + " main initialized");
	}
}
