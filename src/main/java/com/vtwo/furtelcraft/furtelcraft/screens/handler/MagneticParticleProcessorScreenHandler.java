package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.FCScreens;
import com.vtwo.furtelcraft.furtelcraft.init.FCTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MagneticParticleProcessorScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    // 服务器想要客户端开启 screenHandler 时，客户端调用这个构造器。
    // 如有空的物品栏，客户端会调用其他构造器，screenHandler 将会自动
    // 在客户端将空白物品栏同步给物品栏。
    public MagneticParticleProcessorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,new SimpleInventory(4),new ArrayPropertyDelegate(2));
    }

    // 这个构造器是在服务器的 BlockEntity 中被调用的，无需先调用其他构造器，服务器知道容器的物品栏
    // 并直接将其作为参数传入。然后物品栏在客户端完成同步。
    public MagneticParticleProcessorScreenHandler(int synId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate){
        super(FCScreens.MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER, synId);
        checkSize(inventory, 4);//物品栏有4格
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkDataCount(propertyDelegate,1);
        // 玩家开启时，一些物品栏有自定义的逻辑
        inventory.onOpen(playerInventory.player);
        //方块物品栏————开始
        this.addSlot(new Slot(this.inventory,0,31,35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(FCTags.TUBE_ITEM);
            }
        });
        this.addSlot(new Slot(this.inventory,1,54,35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(FCTags.TUBE_ITEM);
            }
        });
        this.addSlot(new Slot(this.inventory,2,109,35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.inventory,3,151,59){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.BLAZE_POWDER;
            }
        });
        //方块物品栏————结束
        //玩家物品栏————固定句式————开始
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
        //玩家物品栏————固定句式————结束
        this.addProperties(propertyDelegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getTick(){
        return propertyDelegate.get(0);
    }

    public int getEnergy(){
        return propertyDelegate.get(1);
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
