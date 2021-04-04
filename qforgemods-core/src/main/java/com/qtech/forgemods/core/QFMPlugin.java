package com.qtech.forgemods.core;

import com.qtech.forgemods.core.plugins.AbstractPluginManager;

import javax.annotation.Nullable;

public interface QFMPlugin<T extends QFMPlugin<T>> {
    @Nullable
    AbstractPluginManager<? extends QFMPlugin<?>> getPluginManager();

    default boolean hasPluginManager() {
        return getPluginManager() != null;
    }
}
