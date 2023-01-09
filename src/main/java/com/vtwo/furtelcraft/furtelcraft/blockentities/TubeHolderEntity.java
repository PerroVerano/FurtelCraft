package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.blocks.TubeHolder;
import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.interfaces.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.TubeHolderScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.vtwo.furtelcraft.furtelcraft.init.TagInit.TUBE_ITEM;

public class TubeHolderEntity extends BlockEntity implements ImplementedInventory, SidedInventory , NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);
    int tick = 0;

    public TubeHolderEntity(BlockPos pos, BlockState state) {
        super(BlockInit.TUBE_HOLDER_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public static void tick(World world,BlockPos blockPos,BlockState blockState,TubeHolderEntity entity) {
        setStatus(world, blockPos, blockState, entity);
    }

    public void use(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!player.getMainHandStack().isEmpty() && player.getMainHandStack().isIn(TUBE_ITEM) && !player.isSneaking()) {
            if (inventory.get(0).isEmpty()) {
                inventory.set(0,player.getMainHandStack().split(1));
            } else if (inventory.get(1).isEmpty()) {
                inventory.set(1,player.getMainHandStack().split(1));
            } else if (inventory.get(2).isEmpty()) {
                inventory.set(2,player.getMainHandStack().split(1));
            }
        } else {
            if (!inventory.get(2).isEmpty()) {
                player.giveItemStack(inventory.get(2));
                inventory.set(2,ItemStack.EMPTY);
            } else if (!inventory.get(1).isEmpty()) {
                player.giveItemStack(inventory.get(1));
                inventory.set(1,ItemStack.EMPTY);
            } else if (!inventory.get(0).isEmpty()) {
                player.giveItemStack(inventory.get(0));
                inventory.set(0,ItemStack.EMPTY);
            }
            else {
                player.giveItemStack(ItemStack.EMPTY);
            }
        }
        markDirty();
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

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index){
                case 0 -> TubeHolderEntity.this.tick;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                TubeHolderEntity.this.tick = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new TubeHolderScreenHandler(syncId,inv,this,propertyDelegate);
    }

    public static void setStatus(World world, BlockPos pos, BlockState state, TubeHolderEntity entity) {
        if (entity.inventory.get(0).isEmpty() && entity.inventory.get(1).isEmpty() && entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,0));
        } else if (!entity.inventory.get(0).isEmpty() && entity.inventory.get(1).isEmpty() && entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,1));
        } else if (!entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,2));
        } else if (!entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && !entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,3));
        } else if (entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,4));
        } else if (entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && !entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos, state.with(TubeHolder.LEVEL,5));
        } else if (entity.inventory.get(0).isEmpty() && entity.inventory.get(1).isEmpty() && !entity.inventory.get(2).isEmpty()) {
            world.setBlockState(pos,state.with(TubeHolder.LEVEL,6));
        }else {
            world.setBlockState(pos, state.with(TubeHolder.LEVEL,7));
        }
    }
}
