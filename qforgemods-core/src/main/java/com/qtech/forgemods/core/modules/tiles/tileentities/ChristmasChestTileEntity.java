package com.qtech.forgemods.core.modules.tiles.tileentities;

import com.qtech.forgemods.core.modules.tiles.ModTileEntities;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(
   value = Dist.CLIENT,
   _interface = IChestLid.class
)
public class ChristmasChestTileEntity extends ChestTileEntity {
   public ChristmasChestTileEntity() {
      super(ModTileEntities.CHRISTMAS_CHEST.get());
   }
}
