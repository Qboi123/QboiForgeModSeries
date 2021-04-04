package com.qtech.forgemods.debugmenu.pages;

import com.qtech.forgemods.debugmenu.DebugPage;
import com.qtech.forgemods.debugmenu.QFMDebugMenu;

public class DefaultPage extends DebugPage {
    public DefaultPage() {
        super(QFMDebugMenu.getModId(), "default");
    }

    @Override
    public boolean hasRequiredComponents() {
        return true;
    }
}
