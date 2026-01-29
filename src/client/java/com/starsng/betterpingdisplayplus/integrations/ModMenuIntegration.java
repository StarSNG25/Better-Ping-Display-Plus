package com.starsng.betterpingdisplayplus.integrations;

import static com.starsng.betterpingdisplayplus.BetterPingDisplayPlus.LOGGER;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.util.NullScreenFactory;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory()
	{
		if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3"))
			return new YaclConfigScreenFactory();

		LOGGER.info("YACL is not installed, GUI config will not be available");
		return new NullScreenFactory<>();
	}
}
