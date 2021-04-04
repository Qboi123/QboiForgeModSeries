package com.qtech.forgemods.core.plugins;

import com.google.common.annotations.Beta;
import com.qtech.forgemods.core.QFMPlugin;

@Beta
public class ModPlugin<T extends QFMPlugin> {
    private final T plugin;

    public ModPlugin(T plugin) {
        this.plugin = plugin;
    }

    public T getPlugin() {
        return plugin;
    }
}
