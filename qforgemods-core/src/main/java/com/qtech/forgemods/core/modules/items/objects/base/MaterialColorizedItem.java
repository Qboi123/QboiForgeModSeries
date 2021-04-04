package com.qtech.forgemods.core.modules.items.objects.base;

import com.qtech.forgemods.core.common.interfaces.IHasMaterialColor;
import net.minecraft.item.Item;

public abstract class MaterialColorizedItem extends Item implements IHasMaterialColor {
    public MaterialColorizedItem(Properties properties) {
        super(properties);
    }
}
