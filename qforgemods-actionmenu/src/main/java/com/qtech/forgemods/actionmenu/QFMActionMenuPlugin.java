package com.qtech.forgemods.actionmenu;

import com.qtech.forgemods.core.QFMCorePlugin;
import com.qtech.forgemods.core.QFMPlugin;

@SuppressWarnings("unused")
public interface QFMActionMenuPlugin extends QFMPlugin<QFMActionMenuPlugin> {
    int getActionMenuMinBuild();
    int getActionMenuMaxBuild();
}
