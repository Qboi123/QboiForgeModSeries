package com.qtech.forgemods.actionmenu;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleSecurity;
import com.qtech.forgemods.core.keybinds.KeyBindingList;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ActionMenuModule extends Module {
    private final ClientSide clientSide;
    private final ServerSide serverSide;

    public ActionMenuModule() {
        if (QFMCore.isClientSide()) {
            this.clientSide = new ClientSide(this);
            this.serverSide = null;
        } else if (QFMCore.isServerSide()) {
            this.serverSide = new ServerSide(this);
            this.clientSide = null;
        } else {
            this.clientSide = null;
            this.serverSide = null;
        }
    }

    @Override
    public void onEnable() {
        if (QFMCore.isClientSide()) {
            MinecraftForge.EVENT_BUS.register(this.clientSide);
        } else if (QFMCore.isServerSide()) {
            MinecraftForge.EVENT_BUS.register(this.serverSide);
        }
    }

    @Override
    public void onDisable() {
        if (QFMCore.isClientSide()) {
            MinecraftForge.EVENT_BUS.unregister(this.clientSide);
        } else if (QFMCore.isServerSide()) {
            MinecraftForge.EVENT_BUS.unregister(this.serverSide);
        }
    }

    @Override
    public boolean canDisable() {
        return true;
    }

    @Override
    public String getName() {
        return "action_menu";
    }

    @Override
    public boolean isDefaultEnabled() {
        return true;
    }

    @Override
    public ModuleCompatibility getCompatibility() {
        return ModuleCompatibility.FULL;
    }

    @Override
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.SAFE;
    }

    public static class ClientSide extends Module.ClientSide {
        @Getter
        private final ActionMenuModule module;

        public ClientSide(ActionMenuModule module) {
            this.module = module;
        }

        @SubscribeEvent
        public void clientTick(TickEvent.ClientTickEvent event) {
            Minecraft mc = Minecraft.getInstance();
            if (KeyBindingList.ACTION_MENU.isPressed() && !(mc.currentScreen instanceof ActionMenuScreen)) {
                mc.displayGuiScreen(new ActionMenuScreen(mc.currentScreen, MainActionMenu.INSTANCE, 0));
            }
        }
    }

    public static class ServerSide extends Module.ServerSide {
        @Getter
        private final ActionMenuModule module;

        public ServerSide(ActionMenuModule module) {
            this.module = module;
        }
    }
}
