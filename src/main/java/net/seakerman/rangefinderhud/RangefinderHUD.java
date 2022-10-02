package net.seakerman.rangefinderhud;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.seakerman.rangefinderhud.hud.ForgeGui;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RangefinderHUD.MOD_ID)
public class RangefinderHUD
{
    public static final String MOD_ID = "rangefinderhud";
    private static final Logger LOGGER = LogUtils.getLogger();
    public RangefinderHUD()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if (FMLEnvironment.dist.isClient()) {

            MinecraftForge.EVENT_BUS.register(new ForgeGui());
        }
    }
}
