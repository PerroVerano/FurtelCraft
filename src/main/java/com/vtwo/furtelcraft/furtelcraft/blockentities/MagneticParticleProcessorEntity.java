package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.inventory.ImplementedInventory;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.MagneticParticleProcessorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class MagneticParticleProcessorEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    //SCREEN--1
    public MagneticParticleProcessorEntity(BlockPos pos, BlockState state) {
        super(BlockInit.MAGNETIC_PARTICLE_PROCESSOR_ENTITY, pos, state);
    }

    //定义槽位数量SCREEN--2
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);

    //SCREEN--3
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // createMenu 会创建 ScreenHandler 自身
    // getDisplayName 会提供名称，名称通常显示在顶部
    // SCREEN--4
    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        // 因为我们的类实现 Inventory，所以将*这个*提供给 ScreenHandler
        // 一开始只有服务器拥有物品栏，然后在 ScreenHandler 中同步给客户端
        // SCREEN--5
        return new MagneticParticleProcessorScreenHandler(syncId,inv,this,propertyDelegate);
    }



    int tick = 0;
    int energy;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index){
                case 0 -> MagneticParticleProcessorEntity.this.tick;
                case 1 -> MagneticParticleProcessorEntity.this.energy;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index){
                case 0 -> MagneticParticleProcessorEntity.this.tick = value;
                case 1 -> MagneticParticleProcessorEntity.this.energy = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public static void tick(World world,BlockPos blockPos,BlockState blockState,MagneticParticleProcessorEntity entity){
        NbtCompound nbt;
        if (entity.inventory.get(3).isOf(Items.BLAZE_POWDER)){
            if (entity.energy < 12){
                entity.inventory.get(3).decrement(1);
                entity.energy++;
            }
        }
        if (entity.energy != 0 && !entity.inventory.get(0).isEmpty() && !entity.inventory.get(1).isEmpty() && entity.inventory.get(2).getCount() < entity.inventory.get(2).getMaxCount()){
            if (entity.inventory.get(0).isOf(ItemInit.SPLITTING_DNA_TUBE) && entity.inventory.get(1).isOf(ItemInit.BUFFER_TUBE) && entity.inventory.get(0).hasNbt()){
                entity.tick++;
                if (entity.tick == 20 * 10){
                    entity.tick = 0;
                    --entity.energy;
                    nbt = entity.inventory.get(0).getNbt();
                    entity.inventory.get(0).decrement(1);
                    entity.inventory.get(1).decrement(1);
                    if (entity.inventory.get(2).isEmpty()){
                        ItemStack result = ItemInit.COMBINE_DNA_TUBE.getDefaultStack();
                        result.setNbt(nbt);
                        entity.inventory.set(2,result);
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


    //将容器内的物品进行保存（nbt）
    // SCREEN--6
    @Override
    public void readNbt(NbtCompound nbt) {//注意顺序！否则会出现进度自动归零的bug
        super.readNbt(nbt);
        Inventories.readNbt(nbt,this.inventory);
        this.tick = nbt.getInt("trans");
        this.energy = nbt.getInt("energy");
    }

    //SCREEN--7
    @Override
    protected void writeNbt(NbtCompound nbt) {//注意顺序！否则会出现进度自动归零的bug
        super.writeNbt(nbt);
        nbt.putInt("trans",this.tick);
        Inventories.writeNbt(nbt,this.inventory);
        nbt.putInt("energy",this.energy);
    }
}
