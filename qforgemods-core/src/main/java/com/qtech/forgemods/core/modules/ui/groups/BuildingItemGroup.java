package com.qtech.forgemods.core.modules.ui.groups;

import com.qtech.forgemods.core.QFMCore;
import com.qsoftware.modlib.common.BetterItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BuildingItemGroup extends BetterItemGroup {
    public BuildingItemGroup() {
        super(new ResourceLocation(QFMCore.modId, "building"), new ItemStack(Blocks.BOOKSHELF));
    }
}
