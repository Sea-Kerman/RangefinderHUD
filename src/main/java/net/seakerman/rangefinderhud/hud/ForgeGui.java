package net.seakerman.rangefinderhud.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;



public class ForgeGui
{
    private Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderGameOverlayPost(RenderGuiOverlayEvent.Post event)
    {
        if(minecraft == null || minecraft.player == null || minecraft.player.level == null){
            return;
        }
        PoseStack matrixStack = event.getPoseStack();
            if (!this.minecraft.options.renderDebug /*&& rangefinderHUDConfigData != null*/) {
                // Draw Game info on every GameHud render
                RangefinderHUDHud.draw(matrixStack);
            }
    }
}
