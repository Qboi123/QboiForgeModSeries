package com.qtech.forgemods.actionmenu;

import com.google.common.annotations.Beta;
import com.qtech.forgemods.core.plugins.AbstractPluginManager;
import com.qtech.forgemods.core.plugins.ModPlugin;

@SuppressWarnings("UnstableApiUsage")
public class QFMActionMenuPluginManager extends AbstractPluginManager<QFMActionMenuPlugin> {
    private static final QFMActionMenuPluginManager instance = new QFMActionMenuPluginManager();

    public static QFMActionMenuPluginManager get() {
        return instance;
    }

    private QFMActionMenuPluginManager() {

    }

    @Override
    public void registerPlugin(QFMActionMenuPlugin plugin) {
        this.plugins.add(new ModPlugin<>(plugin));
    }

    @Beta
    @Override
    public void registerPlugin(Class<?> clazz) {

    }
}
