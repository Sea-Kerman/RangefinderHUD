package net.seakerman.rangefinderhud.hud;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;

import java.awt.*;
import java.text.DecimalFormat;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;

public class RangefinderHUDHud
{
    public static void draw(MatrixStack matrixStack)
    {


        RenderSystem.enableBlend();

        drawInfo(matrixStack);

        Minecraft.getInstance().getProfiler().pop();
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

    private double getRangefinderReading()
    {
        Vec3 eyePos = new Vec3(0,0,0);
        Vec3 resultVector = new Vec3(0,0,0);
        Player player = Minecraft.getInstance().player;
        Vec3 lookedAtBlockPos = getLookedAtBLockPos();
        if (player != null)
        {
            eyePos = new Vec3(player.getEyePosition().x,player.getEyePosition().y,player.getEyePosition().z);
        }
        if (lookedAtBlockPos.length() > .0001)
        {
            resultVector = eyePos.subtract(getLookedAtBLockPos());
        }
        else
        {
            resultVector = new Vec3(2048,0,0);
        }
        return resultVector.length();
    }
    private Vec3 getLookedAtBLockPos()
    {
        Player player = Minecraft.getInstance().player;
        Vec3 hitPos = new Vec3(0,0,0);
        if (player != null)
        {
            HitResult blockHit = player.pick(2048, 0.0f, false);
            if (blockHit.getType() == HitResult.Type.BLOCK)
            {
                BlockPos blockHitPos = ((BlockHitResult) blockHit).getBlockPos();
                hitPos = new Vec3(blockHitPos.getX(),blockHitPos.getY(),blockHitPos.getZ());
            }
            else
            {
                hitPos = new Vec3(-.5,-.5,-.5);
            }
        }
        hitPos = hitPos.add(.5,.5,.5);
        return hitPos;
    }
}
