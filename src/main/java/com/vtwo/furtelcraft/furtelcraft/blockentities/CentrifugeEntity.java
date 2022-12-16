package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.inventory.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.CentrifugeScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
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
        return new CentrifugeScreenHandler(syncId,inv,this);
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
}
