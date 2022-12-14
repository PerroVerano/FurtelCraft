package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.recipes.SpecimenTubeRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;

public class RecipeInit {
    public static final SpecialRecipeSerializer<SpecimenTubeRecipe> SPECIMEN_TUBE_RECIPE = RecipeSerializer.register("recipe_specimen_tube",new SpecialRecipeSerializer<>(SpecimenTubeRecipe::new));

    public static void init(){

    }
}
