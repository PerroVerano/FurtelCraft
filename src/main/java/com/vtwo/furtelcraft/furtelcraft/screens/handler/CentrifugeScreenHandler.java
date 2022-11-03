package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.ScreenInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CentrifugeScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public CentrifugeScreenHandler(int syncId,PlayerInventory playerInventory){
        this(syncId,playerInventory,new SimpleInventory(4));
    }

    public CentrifugeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenInit.CENTRIFUGE_SCREEN_HANDLER,syncId);
        checkSize(inventory,4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.addSlot(new Slot(this.inventory,0,79,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory,1,52,62){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory,2,106,52){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory,3,79,43){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        //玩家快捷栏
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + 玩家物品栏槽位
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
