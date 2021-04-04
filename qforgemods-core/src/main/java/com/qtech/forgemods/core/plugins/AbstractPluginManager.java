package com.qtech.forgemods.core.plugins;

import com.google.common.annotations.Beta;
import com.qtech.forgemods.core.QFMCorePlugin;
import com.qtech.forgemods.core.QFMPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public abstract class AbstractPluginManager<T extends QFMPlugin<T>> {
    protected final List<ModPlugin<T>> plugins = new ArrayList<>();

    public abstract void registerPlugin(T plugin);

    /**
     * Get all QFM plugins.
     *
     * @return a list of QFM plugins.
     */
    public List<ModPlugin<T>> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }

    /**
     * Todo: make abstract when used.
     *
     * @param clazz the plugin class.
     */
    @Beta
    public void registerPlugin(Class<?> clazz) {

    }
}
