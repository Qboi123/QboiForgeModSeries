package com.qtech.forgemods.debugmenu;

import com.qtech.forgemods.actionmenu.AbstractActionMenu;
import com.qtech.forgemods.actionmenu.IActionMenuItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Locale;

public class DebugMenuMenu extends AbstractActionMenu {
    public DebugMenuMenu() {
        for (DebugMenu.PAGE page : DebugMenu.PAGE.values()) {
            addItem(new IActionMenuItem() {
                @Override
                public void onActivate() {
                    DebugMenu.DEBUG_PAGE = page;
                }

                @Override
                public ITextComponent getText() {
                    return new StringTextComponent(page.name().toLowerCase(Locale.ROOT));
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            });
        }
    }
}
