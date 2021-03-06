package com.qtech.forgemods.core.modules.tiles.blocks.machines.refinery;

import com.qtech.forgemods.core.modules.ui.ModMachineContainers;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.AbstractMachineBaseContainer;
import com.qsoftware.modlib.silentlib.inventory.SlotOutputOnly;
import com.qsoftware.modlib.silentlib.util.InventoryUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fluids.FluidStack;

public class RefineryContainer extends AbstractMachineBaseContainer<RefineryTileEntity> {
    public RefineryContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new RefineryTileEntity(), new IntArray(RefineryTileEntity.FIELDS_COUNT));
    }

    public RefineryContainer(int id, PlayerInventory playerInventory, RefineryTileEntity tileEntity, IIntArray fieldsIn) {
        super(ModMachineContainers.refinery, id, tileEntity, fieldsIn);

        this.addSlot(new Slot(this.tileEntity, 0, 8, 16));
        this.addSlot(new SlotOutputOnly(this.tileEntity, 1, 8, 59));
        this.addSlot(new Slot(this.tileEntity, 2, 134, 16));
        this.addSlot(new SlotOutputOnly(this.tileEntity, 3, 134, 59));

        InventoryUtils.createPlayerSlots(playerInventory, 8, 84).forEach(this::addSlot);

        this.addUpgradeSlots();
    }

    public int getProgress() {
        return fields.get(5);
    }

    public int getProcessTime() {
        return fields.get(6);
    }

    @SuppressWarnings("deprecation") // Use of Registry
    public FluidStack getFluidInTank(int tank) {
        int fluidId = this.fields.get(7 + 2 * tank);
        Fluid fluid = Registry.FLUID.getByValue(fluidId);
        int amount = this.fields.get(8 + 2 * tank);
        return new FluidStack(fluid, amount);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int inventorySize = this.tileEntity.getSizeInventory();
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 1 || index == 3) {
                if (!this.mergeItemStack(itemstack1, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= inventorySize) {
                if (com.qtech.forgemods.core.util.InventoryUtils.isEmptyFluidContainer(itemstack1) || com.qtech.forgemods.core.util.InventoryUtils.isFilledFluidContainer(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, inventorySize - 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerInventoryEnd) {
                    if (!this.mergeItemStack(itemstack1, playerInventoryEnd, playerHotbarEnd, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerHotbarEnd && !this.mergeItemStack(itemstack1, inventorySize, playerInventoryEnd, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
