package com.rashdanml.falconry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.rashdanml.falconry.common.setup.ClientSetup;
import com.rashdanml.falconry.common.setup.CommonSetup;
import com.rashdanml.falconry.common.setup.Registration;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Falconry.MODID)
public class Falconry {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "falconry";

    public Falconry() {

        // Register the deferred registry
        Registration.init();

        // Register the setup method for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register 'ModSetup::init' to be called at mod setup time (server and client)
        modbus.addListener(CommonSetup::init);
        // Register 'ClientSetup::init' to be called at mod setup time (client only)
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
    }
}
