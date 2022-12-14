package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.recipes.SpecimenTubeRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class RecipeInit {
    public static final SpecialRecipeSerializer<SpecimenTubeRecipe> SPECIMEN_TUBE_RECIPE = new SpecialRecipeSerializer<>(SpecimenTubeRecipe::new);

    public static void init(){
        Registry.register(Registry.RECIPE_SERIALIZER,new Identifier(MOD_ID,"recipe_specimen_tube"),SPECIMEN_TUBE_RECIPE);
    }
}
