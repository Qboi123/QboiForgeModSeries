package com.qtech.forgemods.core.modules.items.objects.base;

import com.qtech.forgemods.core.common.interfaces.IHasDyeColor;
import net.minecraft.item.Item;

public abstract class DyeColorizedItem extends Item implements IHasDyeColor {
    public DyeColorizedItem(Properties properties) {
        super(properties);
    }
}
