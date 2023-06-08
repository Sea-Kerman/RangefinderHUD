package net.seakerman.rangefinderhud.config;

import me.shedaniel.autoconfig.AutoConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RFHModMenu implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return screen -> AutoConfig.getConfigScreen(ModConfig.class, screen).get();

    }
}
