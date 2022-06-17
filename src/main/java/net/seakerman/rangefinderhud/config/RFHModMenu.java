package net.seakerman.rangefinderhud.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;

@Environment(EnvType.CLIENT)
public class RFHModMenu implements ModMenuApi
{
    /*@Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        try {
            RFHConfigScreen configGUI = new RFHConfigScreen();
            return parent -> configGUI.getConfigScreen(parent, MinecraftClient.getInstance().world != null);
        } catch (NoClassDefFoundError e) {
            //I definitely didn't catch an error, you saw nothing...
            LogManager.getLogger().warn("[Rangefinder HUD]: Mod settings cannot be edited in Mod Menu without cloth config");
            return null;
        }

    }*/
}
