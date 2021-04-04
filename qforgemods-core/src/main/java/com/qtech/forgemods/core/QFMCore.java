package com.qtech.forgemods.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qtech.forgemods.core.common.Module;
import com.qtech.forgemods.core.common.ModuleManager;
import com.qtech.forgemods.core.internal.QfmArgs;
import com.qtech.forgemods.core.modules.environment.ModEntities;
import com.qtech.forgemods.core.modules.items.ModItemsAlt;
import com.qtech.forgemods.core.modules.tiles.ModBlocksAlt;
import com.qtech.forgemods.core.modules.ui.ModContainers;
import com.qtech.forgemods.core.plugins.QFMCorePluginManager;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The main mod class.
 * Here's everything started.
 *
 * @author Qboi123
 * @see #QFMCore()
 * @see Mod
 * @see Mod.EventBusSubscriber
 */
@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@FieldsAreNonnullByDefault
@Mod(QFMCore.modId)
@Mod.EventBusSubscriber(modid = QFMCore.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class QFMCore implements QFMCorePlugin {
    /**
     * QForgeMod's Logger
     */
    public static final Logger LOGGER = LogManager.getLogger("QFM:Generic");

    /**
     * Unused.
     */
    @SuppressWarnings("unused")
    @Deprecated
    public static final Random RANDOM = new Random();
    private static final boolean MOD_TEST_PHASE = false;

    // Class static fields.
    @Getter @Nullable private static QFMCore instance;
    @Getter @Nullable private static IProxy proxy;
    @Getter @Nullable private static Initialization init;

    @Getter private static final boolean clientSide;
    @Getter private static final boolean serverSide;

    // Mod Data
    @Getter public static final String modId = "qfm_core";
    @Getter public static final String modName = "Qboi's Forge Mods: Core";
    @Getter public static final String nbtName = "QFMCore";
    @Getter public static final String modVersion;
    @Getter public static final QFMVersion version;
    @Getter private static final QfmArgs modArgs;

    @Getter private final ModuleManager moduleManager;

    @SuppressWarnings("ConstantConditions")
    private static final Supplier<Boolean> getClientSide = () -> {
        try {
            return Minecraft.getInstance() != null; // This is null when running runData.
        } catch (Throwable t) {
            return false;
        }
    };

    static {
        if (new File("/mnt/chromeos").exists()) {
            throw new UnsupportedOperationException("Tried to run QForgeMod on Chrome OS (Linux subsystem), this is unsupported.");
        }

        clientSide = getClientSide.get();

        boolean s;
        try {
            Class.forName("net.minecraft.server.MinecraftServer");
            s = true;
        } catch (ClassNotFoundException e) {
            s = false;
        }
        serverSide = s;

        // Create gson instance.
        Gson gson = new Gson();

        // Get stream.
        InputStream qfmArgsStream = QFMCore.class.getResourceAsStream("/META-INF/qfm_args.json");
        Objects.requireNonNull(qfmArgsStream, "Couldn't get QForgeMod Args file.");

        // Get data.
        InputStreamReader isr = new InputStreamReader(qfmArgsStream);
        JsonObject o = gson.fromJson(isr, JsonObject.class);

        modArgs = new QfmArgs(o);
        modVersion = modArgs.getVersion().getName();
        version = modArgs.getVersion().toVersionObject();
    }

    // Getters
    @Getter private final IEventBus modEventBus;
    private final QFMCorePluginManager pluginManager = QFMCorePluginManager.get();

    /**
     * The QForgeUtils constructor for mod-loading.
     *
     * @since 1.0-alpha1
     * @see Mod
     * @see QFMCore
     */
    public QFMCore() {

        // Constants.
        QFMCore.instance = this;
        QFMCore.proxy = DistExecutor.safeRunForDist(() -> SideProxy.Client::new, () -> SideProxy.Server::new);
        QFMCore.init = new Initialization(this);

        try {
            ModuleManager.getInstance().init();
        } catch (IOException e) {
            CrashReport report = new CrashReport("QFM Modules being initialized", e);
            CrashReportCategory reportCategory = report.makeCategory("Module details");
            try {
                ModuleManager manager = ModuleManager.getInstance();
                Module currentModule = manager.getCurrentModule();

                reportCategory.addDetail("Module Name", currentModule::getName);
                reportCategory.addDetail("Enabled", () -> manager.isEnabledInConfig(currentModule) ? "Yes" : "No");
            } catch (Throwable ignored) {

            }

            throw new ReportedException(report);
        }

        moduleManager = ModuleManager.getInstance();

        // Assign constants.
        Constants.logger = LOGGER;
        Constants.qforgemod = this;
        Constants.proxy = proxy;

        // Final fields.
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        this.modEventBus = modEventBus;

        // Register forge event bus listener(s).
        MinecraftForge.EVENT_BUS.register(this);

        // Register mod event bus listeners.
        modEventBus.addListener(QFMCore.init::clientSetup);
        modEventBus.addListener(QFMCore.init::serverSetup);
        modEventBus.addListener(QFMCore.init::commonSetup);
        modEventBus.addListener(QFMCore.init::loadComplete);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        // Register generic listeners.
        ModBlocksAlt.BLOCKS.register(modEventBus);
        ModBlocksAlt.ITEMS.register(modEventBus);
        ModItemsAlt.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModContainers.CONTAINER_TYPES.register(modEventBus);
//        ModTileEntities.TILE_ENTITY_TYPES.register(modEventBus);

        // Client-start.
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> QFMCore.init.clientStart());
    }

    public static File getDataFile() {
        return Initialization.getServer().func_240776_a_(new FolderName("qfm-data")).toFile();
    }

    public static Path getDataPath() {
        return Initialization.getServer().func_240776_a_(new FolderName("qfm-data"));
    }

    /**
     * Get the resource location based on QForgeMod's id.
     *
     * @param path the resource path.
     * @return a resource location.
     */
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(modId, path);
    }

    /**
     * Internal method.
     *
     * @return boolean.
     */
    @OnlyIn(Dist.CLIENT)
    private static boolean isDevState0() {
        return Minecraft.getInstance().getVersion().equals("MOD_DEV");
    }

    /**
     * Check if QForgeMod is currently a development build.
     *
     * @return the QFM dev-state..
     */
    public static boolean isModDev() {
        try {
            return isDevState0();
        } catch (NoSuchMethodError error) {
            return false;
        }
    }

    /**
     * Check if QForgeMod is currently a development build.
     *
     * @param def the default value if failed to detect development mode.
     * @return the QFM dev-state..
     */
    public static boolean isModDev(boolean def) {
        try {
            return isDevState0();
        } catch (NoSuchMethodError error) {
            return def;
        }
    }

    /**
     * Check test phase.
     *
     * @return true if QForgeMod is in test phase, false otherwise.
     */
    public static boolean isTestPhase() {
        return isModDev() || MOD_TEST_PHASE;
    }

    /**
     * @return true if QForgeMod is a dev test version, false otherwise.
     */
    public static boolean isDevtest() {
        return modArgs.getFlags().isDevTest();
    }

    /**
     * Event handler for server starting.
     *
     * @param event a {@link FMLServerStartingEvent} object.
     */
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        Objects.requireNonNull(init).serverStart(event);
    }

    /**
     * Event handler for Inter-Mod Communication enqueue. (IMC enqueue)
     *
     * @param event a {@link InterModEnqueueEvent} object.
     */
    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("qforgemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    /**
     * Event handler for Inter-Mod Communication process. (IMC process)
     *
     * @param event a {@link InterModProcessEvent} object.
     */
    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(InterModComms.IMCMessage::getMessageSupplier).
                collect(Collectors.toList()));
    }

    @Override
    @Nullable
    public QFMCorePluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public int getCoreMinBuild() {
        return 0;
    }

    @Override
    public int getCoreMaxBuild() {
        return Integer.MAX_VALUE;
    }
}
