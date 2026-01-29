package com.starsng.betterpingdisplayplus.integrations;

import static com.starsng.betterpingdisplayplus.BetterPingDisplayPlus.LOGGER;
import static net.minecraft.text.Text.translatable;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import com.starsng.betterpingdisplayplus.BetterPingDisplayPlusClient;
import com.starsng.betterpingdisplayplus.config.Config;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.CyclingListControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Formatting;

public class YaclConfigScreenFactory implements ConfigScreenFactory<Screen>
{
	@Override
	public Screen create(Screen parent)
	{
		BetterPingDisplayPlusClient betterPingDisplayPlusClient = BetterPingDisplayPlusClient.getInstance();
		Config config = betterPingDisplayPlusClient.getConfig();
		
		Option<String> nullPingPlaceholderOption = Option.<String>createBuilder()
				.name(translatable("better-ping-display-plus.settings.nullPingPlaceholder"))
				.description(OptionDescription.of(translatable("better-ping-display-plus.settings.nullPingPlaceholder.description")))
				.binding(Config.DEFAULT_NULL_PING_PLACEHOLDER, config::getNullPingPlaceholder, config::setNullPingPlaceholder)
				.controller(StringControllerBuilder::create)
				.build();
		
		Option<Formatting> nullPingPlaceholderColorOption = Option.<Formatting>createBuilder()
				.name(translatable("better-ping-display-plus.settings.nullPingPlaceholderColor"))
				.description(OptionDescription.of(translatable("better-ping-display-plus.settings.nullPingPlaceholderColor.description")))
				.binding(Config.DEFAULT_NULL_PING_PLACEHOLDER_COLOR, config::getNullPingPlaceholderColor, config::setNullPingPlaceholderColor)
				.controller(opt -> CyclingListControllerBuilder.create(opt)
						.values(Arrays.stream(Formatting.values())
								.filter(Formatting::isColor)
								.toList())
						.formatValue(v -> translatable(v + v.getName().toUpperCase(Locale.ROOT)))
				)
				.build();
		
		return YetAnotherConfigLib.createBuilder()
				.title(translatable("better-ping-display-plus.settings.title"))
				.category(ConfigCategory.createBuilder()
						.name(translatable("better-ping-display-plus.settings.title"))
						.option(nullPingPlaceholderOption)
						.option(nullPingPlaceholderColorOption)
						.build())
				.save(() ->
				{
					try
					{
						config.writeToFile(betterPingDisplayPlusClient.getConfigFilePath().toFile());
					}
					catch (IOException ex)
					{
						LOGGER.warn("Failed to write config file", ex);
					}
				})
				.build()
				.generateScreen(parent);
	}
}
