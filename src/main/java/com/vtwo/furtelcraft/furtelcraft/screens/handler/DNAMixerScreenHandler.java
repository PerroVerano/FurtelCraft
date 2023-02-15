package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.FCScreens;
import com.vtwo.furtelcraft.furtelcraft.init.FCTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DNAMixerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public DNAMixerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,new SimpleInventory(4),new ArrayPropertyDelegate(1));
    }

    public DNAMixerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory,PropertyDelegate propertyDelegate) {
        super(FCScreens.DNA_MIXER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkDataCount(propertyDelegate,1);
        inventory.onOpen(playerInventory.player);
        this.addSlot(new Slot(this.inventory,0,44,22){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.inventory,1,44,52){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(FCTags.TUBE_ITEM);
            }
        });
        this.addSlot(new Slot(this.inventory,2,98,37){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory,3,120,37){
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

    public int getTick(){
        return propertyDelegate.get(0);
    }
}
