package com.qtech.forgemods.core.modules.environment.ores;

import com.qtech.forgemods.core.config.AbstractOreConfig;

@Deprecated
public abstract class AbstractOreConfigs {
    public AbstractOreConfig getConfig(DefaultOre ore) {
//        return ore.getConfig().orElseThrow(() -> new IllegalArgumentException("Ore '" + ore.getName() + "' has no config."));
        return null;
    }
}
