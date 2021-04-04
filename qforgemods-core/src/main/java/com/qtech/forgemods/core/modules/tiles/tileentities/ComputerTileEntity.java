package com.qtech.forgemods.core.modules.tiles.tileentities;

import com.qtech.forgemods.core.client.gui.settings.SettingsScreen;
import com.qtech.forgemods.core.pc.common.computerapi.Computer;
import com.qtech.forgemods.core.pc.common.device.PowerSwitch;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ComputerTileEntity extends TileEntity implements ITickableTileEntity {
    private PowerSwitch powerSwitch;

    public ComputerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {
        this.powerSwitch.tick();
    }
}
