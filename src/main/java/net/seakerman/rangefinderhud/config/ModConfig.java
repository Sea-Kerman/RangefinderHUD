package net.seakerman.rangefinderhud.config;

import me.shedaniel.autoconfig.ConfigData;             // autoconfig is better for settings menu
import me.shedaniel.autoconfig.annotation.Config;      // easy-to-read code and less files
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "rangefinderhud")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean onOff = true;

    @ConfigEntry.Gui.Tooltip
    public int x = 50;
    @ConfigEntry.Gui.Tooltip
    public int y = 50;

    @ConfigEntry.Gui.Tooltip
    public int offset = 10;

    @ConfigEntry.Gui.Tooltip
    public int precision = 2;

    @ConfigEntry.ColorPicker(allowAlpha = true)   //color picker entry type
    public int color1 = 0xFF00C832;     //#ARGB format

    @ConfigEntry.ColorPicker(allowAlpha = true)
    public int color2 = 0xFFC8C8C8;

}
