package com.qtech.forgemods.debugmenu;

import com.qtech.forgemods.debugmenu.pages.WindowPage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = QFMDebugMenu.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DebugPages {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DebugMenu.registerPage(new WindowPage(QFMDebugMenu.modId, "window"));
//        DebugScreen.registerPage(new PlayerPage1(QForgeMod.MOD_ID, "player1"));
    }
}
