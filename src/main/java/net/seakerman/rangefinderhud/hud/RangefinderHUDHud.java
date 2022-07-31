package net.seakerman.rangefinderhud.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.text.DecimalFormat;

import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;

@Environment(EnvType.CLIENT)
public class RangefinderHUDHud
{

    public static void draw(MatrixStack matrixStack)
    {


        RenderSystem.enableBlend();

        drawInfo(matrixStack);

        MinecraftClient.getInstance().getProfiler().pop();
    }

    private static void drawInfo(MatrixStack matrixStack)
    {
        int x = rangefinderHUDConfigData.x;
        int y = rangefinderHUDConfigData.y;
        int offset = rangefinderHUDConfigData.offset;


        int color1 = getColorFromRGBA(new Color(rangefinderHUDConfigData.color1_red,
                rangefinderHUDConfigData.color1_green,
                rangefinderHUDConfigData.color1_blue,
                rangefinderHUDConfigData.color1_alpha));

        int color2 = getColorFromRGBA(new Color(rangefinderHUDConfigData.color2_red,
                rangefinderHUDConfigData.color2_green,
                rangefinderHUDConfigData.color2_blue,
                rangefinderHUDConfigData.color2_alpha));

        //render the info
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(rangefinderHUDConfigData.precision);
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
            if (rangefinderHUDConfigData.onOff)
            {
                fontRenderer.drawWithShadow(matrixStack, "--->||", x, y - offset, color1);
                fontRenderer.drawWithShadow(matrixStack, distanceString, x, y, color2);
            }
        }
    }

    private static int getColorFromRGBA(Color tempcolor)
    {
        return (tempcolor.getRGB() & 0x00ffffff) | (tempcolor.getAlpha() << 24);
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
