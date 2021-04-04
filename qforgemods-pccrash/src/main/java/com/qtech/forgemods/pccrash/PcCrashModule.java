package com.qtech.forgemods.pccrash;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qtech.forgemods.core.Modules;
import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleManager;
import com.qtech.forgemods.core.common.ModuleSecurity;
import com.qtech.forgemods.core.util.ComputerUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PcCrashModule extends Module {
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
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.EXPERIMENTAL;
    }

    @Override
    public boolean canDisable() {
        return true;
    }

    @Override
    public @NotNull String getName() {
        return "pc_crash";
    }

    @Override
    public @NotNull ModuleCompatibility getCompatibility() {
        if (ModuleManager.getInstance().isUnsavedDisabled(Modules.PC_SHUTDOWN) || ModuleManager.getInstance().isUnsavedDisabled(Modules.CONFIRM_EXIT)) {
            return ModuleCompatibility.NONE;
        } else if (!ComputerUtils.supportsCrash()) {
            return ModuleCompatibility.NONE;
        } else if (QFMCore.isClientSide()) {
            return ModuleCompatibility.FULL;
        } else if (QFMCore.isServerSide()) {
            return ModuleCompatibility.PARTIAL;
        } else {
            return ModuleCompatibility.NONE;
        }
    }

    @Override
    public boolean isDefaultEnabled() {
        return false;
    }
}
