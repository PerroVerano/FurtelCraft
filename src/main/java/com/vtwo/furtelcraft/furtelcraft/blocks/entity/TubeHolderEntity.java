package com.vtwo.furtelcraft.furtelcraft.blocks.entity;

import com.vtwo.furtelcraft.furtelcraft.blocks.TubeHolder;
import com.vtwo.furtelcraft.furtelcraft.init.FCBlocks;
import com.vtwo.furtelcraft.furtelcraft.interfaces.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.TubeHolderScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
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

import java.util.Objects;

import static com.vtwo.furtelcraft.furtelcraft.init.FCTags.TUBE_ITEM;

public class TubeHolderEntity extends BlockEntity implements ImplementedInventory, SidedInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    int tick = 0;
    static int level = 0;

    public TubeHolderEntity(BlockPos pos, BlockState state) {
        super(FCBlocks.TUBE_HOLDER_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, TubeHolderEntity entity) {
    }

    public void use(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!player.getMainHandStack().isEmpty() && player.getMainHandStack().isIn(TUBE_ITEM) && !player.isSneaking()) {
            if (inventory.get(0).isEmpty()) {
                inventory.set(0, player.getMainHandStack().split(1));
                level = 1;
            } else if (inventory.get(1).isEmpty()) {
                inventory.set(1, player.getMainHandStack().split(1));
                level = 2;
            } else if (inventory.get(2).isEmpty()) {
                inventory.set(2, player.getMainHandStack().split(1));
                level = 3;
            }
        } else {
            if (!inventory.get(2).isEmpty()) {
                player.giveItemStack(inventory.get(2));
                inventory.set(2, ItemStack.EMPTY);
                level = 2;
            } else if (!inventory.get(1).isEmpty()) {
                player.giveItemStack(inventory.get(1));
                inventory.set(1, ItemStack.EMPTY);
                level = 1;
            } else if (!inventory.get(0).isEmpty()) {
                player.giveItemStack(inventory.get(0));
                inventory.set(0, ItemStack.EMPTY);
                level = 0;
            } else {
                player.giveItemStack(ItemStack.EMPTY);
            }
        }
        this.updateListeners();
        world.setBlockState(this.getPos(), this.getCachedState().with(TubeHolder.LEVEL, level));
    }

    private void updateListeners() {
        this.markDirty();
        Objects.requireNonNull(this.getWorld()).updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putInt("LEVEL", this.getCachedState().get(TubeHolder.LEVEL));
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        level = nbt.getInt("LEVEL");
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
            return switch (index) {
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
        return new TubeHolderScreenHandler(syncId, inv, this, propertyDelegate);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }


}
