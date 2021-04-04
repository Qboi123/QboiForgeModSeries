package com.qtech.forgemods.core;

import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleManager;
import com.qtech.forgemods.core.modules.actionmenu.ActionMenuModule;
import com.qtech.forgemods.core.modules.client.ClientTweaksModule;
import com.qtech.forgemods.core.modules.debug.DebuggingModule;
import com.qtech.forgemods.core.modules.environment.WorldGenerationModule;
import com.qtech.forgemods.core.modules.environment.EntitiesModule;
import com.qtech.forgemods.core.modules.tiles.BlocksModule;
import com.qtech.forgemods.core.modules.confirmExit.ConfirmExitModule;
import com.qtech.forgemods.core.modules.debugMenu.DebugMenuModule;
import com.qtech.forgemods.core.modules.MainModule;
import com.qtech.forgemods.core.modules.items.ItemsModule;
import com.qtech.forgemods.core.modules.environment.ores.OresModule;
import com.qtech.forgemods.core.modules.pcCrash.PCCrashModule;
import com.qtech.forgemods.core.modules.pcShutdown.PCShutdownModule;
import com.qtech.forgemods.core.modules.tiles.TileEntitiesModule;
import com.qtech.forgemods.core.modules.updates.UpdatesModule;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@UtilityClass
public class Modules {
    public static final List<Module> MODULES = new ArrayList<>();
    public static final MainModule MAIN = new MainModule();
    public static final ClientTweaksModule CLIENT = new ClientTweaksModule();
    public static final BlocksModule BLOCKS = new BlocksModule();
    public static final ItemsModule ITEMS = new ItemsModule();
    public static final EntitiesModule ENTITIES = new EntitiesModule();
    public static final TileEntitiesModule TILE_ENTITIES = new TileEntitiesModule();
    public static final WorldGenerationModule BIOMES = new WorldGenerationModule();
    public static final OresModule ORES = new OresModule();
    public static final ConfirmExitModule CONFIRM_EXIT = new ConfirmExitModule();
    public static final PCShutdownModule PC_SHUTDOWN = new PCShutdownModule();
    public static final PCCrashModule PC_CRASH = new PCCrashModule();
    public static final DebuggingModule DEBUGGING = new DebuggingModule();

    public static void init(ModuleManager manager) {
        manager.register(MAIN);
        manager.register(BLOCKS);
        manager.register(ITEMS);
        manager.register(ENTITIES);
        manager.register(TILE_ENTITIES);
        manager.register(BIOMES);
        manager.register(ORES);
        manager.register(CONFIRM_EXIT);
        manager.register(PC_SHUTDOWN);
        manager.register(PC_CRASH);
        manager.register(UPDATES);
        if (QFMCore.isClientSide()) {
            manager.register(CLIENT);
        }
        if (QFMCore.isModDev(false)) {
            manager.register(DEBUGGING);
        }
        manager.register(DEBUG_MENU);
        manager.register(ACTION_MENU);
    }
}
