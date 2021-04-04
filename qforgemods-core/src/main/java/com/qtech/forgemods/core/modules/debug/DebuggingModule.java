package com.qtech.forgemods.core.modules.debug;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleSecurity;
import com.qtech.forgemods.core.modules.actionmenu.MainActionMenu;
import com.qtech.forgemods.core.modules.actionmenu.MenuHandler;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

public class DebuggingModule extends Module {
    private static final DebuggingMenu debuggingMenu = new DebuggingMenu();

    public DebuggingModule() {

        MainActionMenu.registerHandler(new MenuHandler(new StringTextComponent("Debugging"), debuggingMenu));
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
}
