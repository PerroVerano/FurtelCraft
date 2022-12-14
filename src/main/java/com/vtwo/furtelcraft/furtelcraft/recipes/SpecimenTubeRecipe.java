package com.vtwo.furtelcraft.furtelcraft.recipes;

import com.google.common.collect.Lists;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.init.RecipeInit;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpecimenTubeRecipe extends SpecialCraftingRecipe {
    public SpecimenTubeRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.getItem() == ItemInit.WOLF_MEAT) {
                    list.add(stack);
                }
                else if (stack.getItem() == ItemInit.WATER_TUBE) {
                    list.add(stack);
                }
            }
        }
        return list.size() == 2;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        boolean hasWolfMeat = false;
        boolean hasWaterTube = false;
        ItemStack stack = new ItemStack(ItemInit.SPECIMEN_TUBE);
        NbtCompound nbt = stack.getOrCreateSubNbt("Sequence");
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            count++;
            if (itemStack.getItem() == ItemInit.WOLF_MEAT) {
                hasWolfMeat = true;
            }
            else if (itemStack.getItem() == ItemInit.WATER_TUBE) {
                hasWaterTube = true;
            }
        }
        if (hasWolfMeat && hasWaterTube && count == 2) {
            nbt.putString("Sequence","WWF7");
            return stack;
        }
        return stack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        return super.getRemainder(inventory);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.SPECIMEN_TUBE_RECIPE;
    }
}
