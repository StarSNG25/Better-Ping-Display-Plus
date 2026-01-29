package com.starsng.betterpingdisplayplus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.starsng.betterpingdisplayplus.BetterPingDisplayPlusClient;
import com.starsng.betterpingdisplayplus.config.Config;
import com.vladmarica.betterpingdisplay.hud.CustomPlayerListHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;

@Mixin(CustomPlayerListHud.class)
public class CustomPlayerListHudMixin
{
	@ModifyVariable(method = "renderPingDisplay", at = @At("STORE"), ordinal = 0)
	private static String applyNullPingPlaceholder(String original, MinecraftClient minecraftClient, PlayerListHud playerListHud, DrawContext drawContext, int width, int x, int y, PlayerListEntry playerListEntry)
	{
		Config config = BetterPingDisplayPlusClient.getInstance().getConfig();
		
		return playerListEntry.getLatency() == 0
				? config.getNullPingPlaceholderColor() + config.getNullPingPlaceholder()
				: original;
	}
}
