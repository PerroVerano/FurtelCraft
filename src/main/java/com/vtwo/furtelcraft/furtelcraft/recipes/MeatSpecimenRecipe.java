package com.vtwo.furtelcraft.furtelcraft.recipes;

import com.google.common.collect.Lists;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.init.RecipeInit;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;

import static com.vtwo.furtelcraft.furtelcraft.init.TagInit.IS_SPECIMEN_MEAT_ITEM;

public class MeatSpecimenRecipe extends SpecialCraftingRecipe {

    private static int knifeSlot = 0;
    private static int knifeDamage = 0;

    public MeatSpecimenRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.isOf(ItemInit.SPECIMEN_KNIFE) && stack.getDamage() < 64 && stack.getDamage() >= 0) {
                    list.add(stack);
                }
                else if (stack.isIn(IS_SPECIMEN_MEAT_ITEM)) {
                    list.add(stack);
                }
            }
        }
        return list.size() == 2;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        boolean hasWolfSpecimen = false;
        boolean hasFoxSpecimen = false;
        boolean hasEnderDragonSpecimen = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                count++;
                if (itemStack.isOf(ItemInit.WOLF_MEAT)) {
                    hasWolfSpecimen = true;
                } else if (itemStack.isOf(ItemInit.FOX_MEAT)) {
                    hasFoxSpecimen = true;
                } else if (itemStack.isOf(ItemInit.ENDER_DRAGON_MEAT)) {
                    hasEnderDragonSpecimen = true;
                } else if (itemStack.isOf(ItemInit.SPECIMEN_KNIFE)) {
                    knifeSlot = i;
                    knifeDamage = itemStack.getDamage();
                }
            }
        }
        if (count == 2 && hasWolfSpecimen) {
            return new ItemStack(ItemInit.WOLF_MEAT_SPECIMEN,2);
        } else if (count == 2 && hasFoxSpecimen) {
            return new ItemStack(ItemInit.FOX_MEAT_SPECIMEN,2);
        }else if (count == 2 && hasEnderDragonSpecimen) {
            return new ItemStack(ItemInit.ENDER_DRAGON_MEAT_SPECIMEN,4);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(9,ItemStack.EMPTY);
        ItemStack knife = new ItemStack(ItemInit.SPECIMEN_KNIFE);
        if (knifeDamage == 63) {
            list.set(knifeSlot,ItemStack.EMPTY);
        }
        else {
            knife.setDamage(knifeDamage + 1);
            list.set(knifeSlot,knife);
        }
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.MEAT_SPECIMEN_RECIPE;
    }
}
