package net.seakerman.rangefinderhud.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.*;
import java.text.DecimalFormat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

//import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;

public class RangefinderHUDHud
{
    public static void draw(PoseStack matrixStack)
    {


        RenderSystem.enableBlend();

        drawInfo(matrixStack);

        Minecraft.getInstance().getProfiler().pop();
    }

    private static void drawInfo(PoseStack matrixStack)
    {
        /*
        GOTTA SET UP CONFIGS
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
        */

        int x = 580;
        int y = 525;
        int offset = 10;
        int color1 = getColorFromRGBA(new Color(0,200,50,255));
        int color2 = getColorFromRGBA(new Color(200,200,200,255));

        //render the info
        DecimalFormat df = new DecimalFormat();
        //df.setMaximumFractionDigits(rangefinderHUDConfigData.precision);
        df.setMaximumFractionDigits(2);
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
        Font fontRenderer = Minecraft.getInstance().font;
        if (fontRenderer != null)
        {
            //if (rangefinderHUDConfigData.onOff)
            if(true)
            {
                fontRenderer.drawShadow(matrixStack, "--->||", x, y - offset, color1);
                fontRenderer.drawShadow(matrixStack, distanceString, x, y, color2);

            }
        }
    }

    private static int getColorFromRGBA(Color tempcolor)
    {
        return (tempcolor.getRGB() & 0x00ffffff) | (tempcolor.getAlpha() << 24);
    }

    private static double getRangefinderReading()
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
    private static Vec3 getLookedAtBLockPos()
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
