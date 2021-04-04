package com.qtech.forgemods.core.modules.items.objects.type;

import com.qtech.forgemods.core.modules.ui.ModItemGroups;
import net.minecraft.item.Item;

/**
 * Game PC block class.
 *
 * @author Qboi123
 */
public class IngredientItem extends Item {
    public IngredientItem() {
        super(new Properties().group(ModItemGroups.INGREDIENTS));
    }
}
