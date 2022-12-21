package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.inventory.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.items.SpecimenTube;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.RackScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RackEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(15,ItemStack.EMPTY);
    int tick = 0;
    int potion = 0;
    int time = 0;
    public RackEntity(BlockPos pos, BlockState state) {
        super(BlockInit.RACK_ENTITY, pos, state);
    }

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
        return new RackScreenHandler(syncId,inv,this,propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,this.inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,this.inventory);
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> RackEntity.this.tick;
                case 1 -> RackEntity.this.potion;
                case 2 -> RackEntity.this.time;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> RackEntity.this.tick = value;
                case 1 -> RackEntity.this.potion = value;
                case 2 -> RackEntity.this.time = value;
            }
        }

        @Override
        public int size() {
            return 3;
        }
    };

    public static void tick(World world, BlockPos blockPos, BlockState blockState, RackEntity entity) {
        NbtCompound baseNbt;
        if (!entity.inventory.get(11).isEmpty() && !entity.inventory.get(12).isEmpty()) {
            if (entity.inventory.get(11).isOf(ItemInit.SPECIMEN_TUBE) && entity.inventory.get(11).hasNbt() && entity.inventory.get(12).isOf(ItemInit.PROTEINASE_K_TUBE)) {
                entity.time ++;
                baseNbt = entity.inventory.get(11).getNbt();
                assert baseNbt != null;
                baseNbt.putInt("time",entity.time);
                entity.inventory.get(11).setNbt(baseNbt);
                if (entity.time == 20 * 10) {
                    entity.time = 0;
                    ItemStack result = ItemInit.SPLITTING_DNA_TUBE.getDefaultStack();
                    result.setNbt(entity.inventory.get(11).getNbt());
                    result.removeSubNbt("time");
                    entity.inventory.get(12).decrement(1);
                    entity.inventory.set(11,ItemStack.EMPTY);
                    entity.inventory.set(11,result);
                }
            } else if (entity.inventory.get(11).isOf(ItemInit.COMBINE_DNA_TUBE) && entity.inventory.get(12).isOf(ItemInit.ABSOLUTE_ALCOHOL_TUBE) && entity.inventory.get(11).hasNbt()) {
                entity.time ++;
                baseNbt = entity.inventory.get(11).getNbt();
                assert baseNbt != null;
                baseNbt.putInt("time",entity.time);
                entity.inventory.get(11).setNbt(baseNbt);
                if (entity.time == 20 * 10) {
                    entity.time = 0;
                    ItemStack result = ItemInit.SEDIMENT_DNA_TUBE.getDefaultStack();
                    result.setNbt(entity.inventory.get(11).getNbt());
                    result.removeSubNbt("time");
                    entity.inventory.get(12).decrement(1);
                    entity.inventory.set(11,ItemStack.EMPTY);
                    entity.inventory.set(11,result);
                }
            }
        } else {
            entity.time = 0;
        }
    }
}
