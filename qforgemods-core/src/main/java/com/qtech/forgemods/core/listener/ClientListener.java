package com.qtech.forgemods.core.listener;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.common.interfaces.IFOVUpdateItem;
import com.qtech.forgemods.core.network.Network;
import com.qtech.forgemods.core.network.OreProfilePacket;
import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Client listener
 *
 * @author (partial) CoFH - https://github.com/CoFH
 */
@Mod.EventBusSubscriber(modid = QFMCore.modId, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@UtilityClass
public class ClientListener {
    @SubscribeEvent
    public static void handleFOVUpdateEvent(FOVUpdateEvent event) {
        ItemStack stack = event.getEntity().getActiveItemStack();

        if (stack.getItem() instanceof IFOVUpdateItem) {
            event.setNewfov(event.getFov() - ((IFOVUpdateItem) stack.getItem()).getFOVMod(stack, event.getEntity()));
        }
    }

    @SubscribeEvent
    public static void handleClientChatEvent(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.startsWith("$")) {
            String[] s = message.split(" ");
            if (s[0].equals("$ore_profile")) {
                if (s.length == 2) {
                    String rl = s[1];
                    ResourceLocation resourceLocation = new ResourceLocation(rl);
                    Network.channel.sendToServer(new OreProfilePacket());
                }
            }
        }
    }
}