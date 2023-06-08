package net.seakerman.rangefinderhud.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.text.DecimalFormat;

import net.seakerman.rangefinderhud.RangefinderHUD;

@Environment(EnvType.CLIENT)
public class RangefinderHUDHud
{

    public static void draw(DrawContext context)
    {
        RenderSystem.enableBlend();
        drawInfo(context);

        MinecraftClient.getInstance().getProfiler().pop();
    }

    private static void drawInfo(DrawContext context)
    {
        int x = RangefinderHUD.config.x;
        int y = RangefinderHUD.config.y;
        int offset = RangefinderHUD.config.offset;


        int color1 = RangefinderHUD.config.color1;

        int color2 = RangefinderHUD.config.color2;

        //render the info
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(RangefinderHUD.config.precision);
        String distanceString;
        double reading = getRangefinderReading();
        if (reading < 2048)
        {
            distanceString = String.format(df.format(reading));
        }
        else
        {
            distanceString = "";
        }
        TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;
        if (fontRenderer != null)
        {
                context.drawText(fontRenderer, "--->||", x, y - offset, color1, true);
                context.drawText(fontRenderer, distanceString, x, y, color2, true);
        }
    }

    private static double getRangefinderReading()
    {
        float reading = 0;
        Vec3d eyePos = new Vec3d(0,0,0);
        Vec3d resultVector = new Vec3d(0,0,0);
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null)
        {
            eyePos = player.getEyePos();
        }
        if (getLookedAtBLockPos().length() > .0001)
        {
            resultVector = eyePos.subtract(getLookedAtBLockPos());
        }
        else
        {
            resultVector = new Vec3d(2048,0,0);
        }
        return resultVector.length();
    }
    private static Vec3d getLookedAtBLockPos()
    {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Vec3d hitPos = new Vec3d(0,0,0);
        if (player != null)
        {
            HitResult blockHit = player.raycast(2048, 0.0f, false);
            if (blockHit.getType() == HitResult.Type.BLOCK)
            {
                BlockPos blockHitPos = ((BlockHitResult) blockHit).getBlockPos();
                hitPos = new Vec3d(blockHitPos.getX(),blockHitPos.getY(),blockHitPos.getZ());
            }
            else
            {
                hitPos = new Vec3d(-.5,-.5,-.5);
            }
        }
        hitPos = hitPos.add(.5,.5,.5);
        return hitPos;
    }
}
