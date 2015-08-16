package com.aaron1011.stuckarrows;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "stuckarrows", name = "StuckArrows", version = "1.0.0")
public class StuckArrows {

    @SidedProxy(clientSide = "com.aaron1011.stuckarrows.ClientProxy")
    private static ClientProxy clientProxy;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            clientProxy.replaceLayerArrow();
        }
    }

}
