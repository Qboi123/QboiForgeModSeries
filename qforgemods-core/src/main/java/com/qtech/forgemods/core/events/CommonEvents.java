package com.qtech.forgemods.core.events;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.capability.EnergyStorageImplBase;
import com.qtech.forgemods.core.modules.items.objects.energy.BatteryItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QFMCore.modId)
public class CommonEvents {
    @SubscribeEvent
    public static void onAttachItemCaps(AttachCapabilitiesEvent<Item> event) {
        if (event.getObject() instanceof BatteryItem) {
            event.addCapability(QFMCore.rl("energy"), new EnergyStorageImplBase(500_000, 10_000, 10_000));
        }
    }
}
