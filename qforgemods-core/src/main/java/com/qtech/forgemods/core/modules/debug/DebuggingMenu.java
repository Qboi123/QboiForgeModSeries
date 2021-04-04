package com.qtech.forgemods.core.modules.debug;

import com.qtech.forgemods.core.modules.actionmenu.AbstractActionMenu;
import com.qtech.forgemods.core.modules.actionmenu.IActionMenuItem;
import com.qtech.forgemods.core.network.Network;
import com.qtech.forgemods.core.network.OreProfilePacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class DebuggingMenu extends AbstractActionMenu {
    public DebuggingMenu() {
        addItem(new IActionMenuItem() {
            @Override
            public void onActivate() {
                Network.channel.sendToServer(new OreProfilePacket(true));
            }

            @Override
            public ITextComponent getText() {
                return new StringTextComponent("Start Ore Profiler");
            }
        });
        addItem(new IActionMenuItem() {
            @Override
            public void onActivate() {
                Network.channel.sendToServer(new OreProfilePacket(false));
            }

            @Override
            public ITextComponent getText() {
                return new StringTextComponent("Stop Ore Profiler");
            }
        });
    }
}
