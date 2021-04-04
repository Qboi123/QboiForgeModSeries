package com.qtech.forgemods.actionmenu;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

@Deprecated
public class TestActionMenu extends AbstractActionMenu {
    public TestActionMenu() {
        this.addItem(new IActionMenuItem() {
            @Override
            public void onActivate() {
                QFMActionMenu.LOGGER.debug("Test message");
            };

            @Override
            public ITextComponent getText() {
                return new StringTextComponent("Print message.");
            }
        });
        this.addItem(new IActionMenuItem() {
            @Override
            public void onActivate() {
                QFMActionMenu.LOGGER.debug("Test message 2");
            };

            @Override
            public ITextComponent getText() {
                return new StringTextComponent("Print message version 2.");
            }
        });
    }
}
