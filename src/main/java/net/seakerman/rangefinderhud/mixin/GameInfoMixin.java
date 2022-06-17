package net.seakerman.rangefinderhud.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.seakerman.rangefinderhud.RangefinderHUD;
import net.seakerman.rangefinderhud.hud.RangefinderHUDHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;

@Environment(EnvType.CLIENT)
@Mixin(value = InGameHud.class)
public abstract class GameInfoMixin
{
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "render", at = @At("HEAD"))
	private void onDraw(MatrixStack matrixStack, float esp, CallbackInfo ci) {
		if (!this.client.options.debugEnabled && rangefinderHUDConfigData != null) {
			// Draw Game info on every GameHud render
			RangefinderHUDHud.draw(matrixStack);
		}
	}
}
