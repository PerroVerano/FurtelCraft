package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.inventory.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.MagneticParticleProcessorScreenHandler;
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

public class MagneticParticleProcessorEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public MagneticParticleProcessorEntity(BlockPos pos, BlockState state) {
        super(BlockInit.MAGNETIC_PARTICLE_PROCESSOR_ENTITY, pos, state);
    }

    //定义槽位数量
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // createMenu 会创建 ScreenHandler 自身
    // getDisplayName 会提供名称，名称通常显示在顶部
    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        // 因为我们的类实现 Inventory，所以将*这个*提供给 ScreenHandler
        // 一开始只有服务器拥有物品栏，然后在 ScreenHandler 中同步给客户端
        return new MagneticParticleProcessorScreenHandler(syncId,inv,this);
    }

    //将容器内的物品进行保存（nbt）
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,this.inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,this.inventory);
    }
}
