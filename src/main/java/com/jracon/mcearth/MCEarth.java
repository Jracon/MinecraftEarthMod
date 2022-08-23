package com.jracon.mcearth;

import com.jracon.mcearth.setup.ClientSetup;
import com.jracon.mcearth.setup.ModSetup;
import com.jracon.mcearth.setup.Registration;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MCEarth.MOD_ID)
public class MCEarth {

    public static final String MOD_ID = "mcearth";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MCEarth() {

        ModSetup.setup();
        Registration.init();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
    }
}
