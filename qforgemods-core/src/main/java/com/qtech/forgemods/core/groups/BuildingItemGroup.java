package com.qtech.forgemods.core.groups;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.common.BetterItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BuildingItemGroup extends BetterItemGroup {
    public BuildingItemGroup() {
        super(new ResourceLocation(QFMCore.modId, "building"), new ItemStack(Blocks.BOOKSHELF));
    }
}
