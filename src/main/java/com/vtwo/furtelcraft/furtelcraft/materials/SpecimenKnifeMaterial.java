package com.vtwo.furtelcraft.furtelcraft.materials;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SpecimenKnifeMaterial implements ToolMaterial {
    public static final SpecimenKnifeMaterial INSTANCE = new SpecimenKnifeMaterial();
    @Override
    public int getDurability() {
        return 64;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
