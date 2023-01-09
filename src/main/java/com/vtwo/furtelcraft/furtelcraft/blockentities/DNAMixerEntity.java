package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.init.TagInit;
import com.vtwo.furtelcraft.furtelcraft.interfaces.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.DNAMixerScreenHandler;
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
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DNAMixerEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public DNAMixerEntity(BlockPos pos, BlockState state) {
        super(BlockInit.DNA_MIXER_ENTITY, pos, state);
    }

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DNAMixerScreenHandler(syncId,inv,this,propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tick",this.tick);
        Inventories.writeNbt(nbt,this.inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,this.inventory);
        this.tick = nbt.getInt("tick");
    }



    int tick = 0;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index){
                case 0 -> DNAMixerEntity.this.tick;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                DNAMixerEntity.this.tick = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public static void tick(World world, BlockPos blockPos, BlockState blockState,DNAMixerEntity entity) {
        if (!entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && entity.inventory.get(2).isEmpty()) {
            if (entity.inventory.get(0).isIn(TagInit.SPECIMEN_MEAT_ITEM) && entity.inventory.get(1).isOf(ItemInit.WATER_TUBE)) {
                entity.tick++;
                if (entity.tick == 20 * 36) {
                    entity.tick = 0;
                    ItemStack temp = entity.inventory.get(0).copy();
                    entity.inventory.get(0).decrement(1);
                    entity.inventory.get(1).decrement(1);
                    if (entity.inventory.get(2).isEmpty()) {
                        entity.inventory.set(2, NbtSequenceItemLists.getSequenceNbtItemStack(temp));
                    }
                    else {
                        entity.inventory.get(2).increment(1);
                    }
                }
            }
        }
        if (entity.inventory.get(0).isEmpty() || entity.inventory.get(1).isEmpty()){
            entity.tick = 0;
        }
    }
}
