package com.qtech.forgemods.core.plugins;

import com.google.common.annotations.Beta;
import com.qtech.forgemods.core.QFMCorePlugin;

@SuppressWarnings("unused")
public final class QFMCorePluginManager extends AbstractPluginManager<QFMCorePlugin> {
    private static final QFMCorePluginManager instance = new QFMCorePluginManager();

    public static QFMCorePluginManager get() {
        return instance;
    }

    private QFMCorePluginManager() {

    }

    @Override
    public void registerPlugin(QFMCorePlugin plugin) {
        this.plugins.add(new ModPlugin<>(plugin));
    }

    @Beta
    @Override
    public void registerPlugin(Class<?> clazz) {

    }
}
