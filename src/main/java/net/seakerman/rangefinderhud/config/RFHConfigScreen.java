package net.seakerman.rangefinderhud.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static net.seakerman.rangefinderhud.RangefinderHUD.rangefinderHUDConfigData;
import static net.seakerman.rangefinderhud.RangefinderHUD.saveConfig;

public class RFHConfigScreen
{
    public Screen getConfigScreen(Screen parent, boolean isTransparent)
    {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Rangefinder HUD"))
                .setSavingRunnable(() ->
                {
                    saveConfig();
                });

        ConfigCategory category = builder.getOrCreateCategory(Text.of(""));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //On/Off
        category.addEntry(entryBuilder.startBooleanToggle(Text.of("On/Off"),rangefinderHUDConfigData.onOff)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Turns it on and off")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.onOff = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //X Position
        category.addEntry(entryBuilder.startIntField(Text.of("X Position"),rangefinderHUDConfigData.x)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 580 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.x = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Y Position
        category.addEntry(entryBuilder.startIntField(Text.of("Y Position"),rangefinderHUDConfigData.y)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 525 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.y = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Offset
        category.addEntry(entryBuilder.startIntField(Text.of("Offset"),rangefinderHUDConfigData.offset)
                .setDefaultValue(10) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Offset between arrow and reading")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.offset = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Precision
        category.addEntry(entryBuilder.startIntField(Text.of("Precision"),rangefinderHUDConfigData.precision)
                .setDefaultValue(2) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Number of digits after the decimal point")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.precision = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Color 1
        SubCategoryBuilder color1Settings = entryBuilder.startSubCategory(Text.of("Arrow Color")).setTooltip(Text.of("The color of the arrow"));
        color1Settings.add(entryBuilder.startIntField(Text.of("R"),rangefinderHUDConfigData.color1_red)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Arrow")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color1_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("G"),rangefinderHUDConfigData.color1_green)
                .setDefaultValue(200) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Arrow")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color1_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("B"),rangefinderHUDConfigData.color1_blue)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Arrow")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color1_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("A"),rangefinderHUDConfigData.color1_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Arrow")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color1_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color1Settings.build());

        //Color 2
        SubCategoryBuilder color2Settings = entryBuilder.startSubCategory(Text.of("Reading Color")).setTooltip(Text.of("The color of the reading"));
        color2Settings.add(entryBuilder.startIntField(Text.of("R"),rangefinderHUDConfigData.color2_red)
                .setDefaultValue(220) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Reading")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color2_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("G"),rangefinderHUDConfigData.color2_green)
                .setDefaultValue(200) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Reading")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color2_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("B"),rangefinderHUDConfigData.color2_blue)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Reading")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color2_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("A"),rangefinderHUDConfigData.color2_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Reading")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> rangefinderHUDConfigData.color2_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color2Settings.build());

        return builder.setTransparentBackground(isTransparent).build();
    }
}
