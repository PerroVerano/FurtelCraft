package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.ScreenInit;
import com.vtwo.furtelcraft.furtelcraft.init.TagInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class RackScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    
    public RackScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,new SimpleInventory(15),new ArrayPropertyDelegate(3));
    }

    public RackScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenInit.RACK_SCREEN_HANDLER,syncId);
        checkSize(inventory,15);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkDataCount(propertyDelegate,1);
        inventory.onOpen(playerInventory.player);
        this.addSlot(new Slot(this.inventory, 0, 221, 7) {

        });
        this.addSlot(new Slot(this.inventory, 1, 221, 25) {

        });
        this.addSlot(new Slot(this.inventory, 2, 221, 43) {

        });
        this.addSlot(new Slot(this.inventory, 3, 221, 61) {

        });
        this.addSlot(new Slot(this.inventory, 4, 203, 7) {

        });
        this.addSlot(new Slot(this.inventory, 5, 203, 25) {

        });
        this.addSlot(new Slot(this.inventory, 6, 203, 43) {

        });
        this.addSlot(new Slot(this.inventory, 7, 203, 61) {

        });
        this.addSlot(new Slot(this.inventory, 8, 57, 29) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(TagInit.TUBE_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, 9, 107, 24) {

        });
        this.addSlot(new Slot(this.inventory, 10, 57, 60) {

        });
        this.addSlot(new Slot(this.inventory, 11, 19, 44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(TagInit.TUBE_ITEM);
            }

            @Override
            public int getMaxItemCount(ItemStack stack) {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, 12, 19, 14) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(TagInit.TUBE_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, 13, 144, 53) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(TagInit.TUBE_ITEM);
            }
        });
        this.addSlot(new Slot(this.inventory, 14, 176, 23) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(TagInit.TUBE_ITEM);
            }
        });
        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 35 + 8 + j * 18, 84 + i * 18));
            }
        }
        //玩家快捷栏
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 35 + 8 + i * 18, 142));
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

    public int getPotion(){
        return propertyDelegate.get(1);
    }

    public int getTime() {
        return propertyDelegate.get(2);
    }
}
