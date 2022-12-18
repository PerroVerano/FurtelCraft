package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.inventory.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.CentrifugeScreenHandler;
import com.vtwo.furtelcraft.furtelcraft.utils.NbtSequenceItemLists;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CentrifugeEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public CentrifugeEntity(BlockPos pos, BlockState state) {
        super(BlockInit.CENTRIFUGE_ENTITY, pos, state);
    }

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CentrifugeScreenHandler(syncId,inv,this,propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,this.inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt,this.inventory);
        super.writeNbt(nbt);
    }
    
    
    
    int tick = 0;
    
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> CentrifugeEntity.this.tick;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                CentrifugeEntity.this.tick = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public static void tick(World world, BlockPos blockPos, BlockState blockState,CentrifugeEntity entity) {
        if (!entity.inventory.get(3).isEmpty()) {
            ItemStack item = entity.inventory.get(3);
            if (item.isOf(ItemInit.SPECIMEN_TUBE) && item.hasNbt()) {
                entity.tick++;
                if (entity.tick == 20 * 4){
                    entity.inventory.set(0, item);
                }
                if (entity.tick == 20 * 8){
                    entity.inventory.set(0,ItemStack.EMPTY);
                    entity.inventory.set(1, item);
                }
                if (entity.tick == 20 * 12){
                    entity.inventory.set(1,ItemStack.EMPTY);
                    entity.inventory.set(2, item);
                }
                if (entity.tick == 20 * 16) {
                    entity.tick = 0;
                    entity.inventory.set(2,ItemStack.EMPTY);
                    entity.inventory.set(3,ItemStack.EMPTY);
                    ItemStack result = NbtSequenceItemLists.getSequenceNbtResult(item);
                    entity.inventory.set(3, result);
                }

            }
        }
        else {
            entity.tick = 0;
            for (int i = 0; i < 3; i++) {
                entity.inventory.set(i,ItemStack.EMPTY);
            }
        }
    }
}
