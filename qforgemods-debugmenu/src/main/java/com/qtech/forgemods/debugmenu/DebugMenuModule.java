package com.qtech.forgemods.debugmenu;

import com.qtech.forgemods.actionmenu.MainActionMenu;
import com.qtech.forgemods.actionmenu.MenuHandler;
import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleSecurity;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Getter
public class DebugMenuModule extends Module {
    @OnlyIn(Dist.CLIENT)
    public final ClientSide clientSide;

    public DebugMenuModule() {
        if (QFMCore.isClientSide()) {
            clientSide = new ClientSide();
        } else {
            clientSide = null;
        }
    }

    @Override
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.SAFE;
    }

    @Override
    public void onEnable() {
        if (QFMCore.isClientSide()) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @Override
    public void onDisable() {
        if (QFMCore.isClientSide()) {
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }

    @SubscribeEvent
    public void onKeyReleased(InputEvent.KeyInputEvent event) {
        DebugMenu.onKeyReleased(event);
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event) {
        DebugMenu.renderGameOverlay(event);
    }

    @Override
    public boolean canDisable() {
        return true;
    }

    @Override
    public @NotNull String getName() {
        return "debug_menu";
    }

    @Override
    public @NotNull ModuleCompatibility getCompatibility() {
        if (QFMCore.isClientSide()) {
            return ModuleCompatibility.FULL;
        } else if (QFMCore.isServerSide()) {
            return ModuleCompatibility.PARTIAL;
        } else {
            return ModuleCompatibility.NONE;
        }
    }

    @Override
    public boolean isDefaultEnabled() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public static class ClientSide extends Module.ClientSide {
        private static final DebugMenuMenu debugMenuMenu = new DebugMenuMenu();

        public ClientSide() {
            MainActionMenu.registerHandler(new MenuHandler(new StringTextComponent("Debug Menu"), debugMenuMenu));
        }
    }
}
