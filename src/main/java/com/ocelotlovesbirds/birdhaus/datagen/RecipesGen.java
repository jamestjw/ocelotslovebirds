package com.ocelotlovesbirds.birdhaus.datagen;

//Based on the Jorrit Tyberghein's RecipeGenerator and MyCrayFish's Furniture Mod.

import java.util.function.Consumer;

import com.ocelotlovesbirds.birdhaus.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

/**
 * The RecipesGenerator allows for the dynamic inclusion of recipes so hand-tailoring the .json is no longer necessary.
 */
public class RecipesGen extends RecipeProvider {

    public RecipesGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    /**
     * @param recipeConsumer The consumer of the recipe. Pass in the consumer from the build-crafting-recipes.
     * @param birdhouse      The birdhouse block being constructed. Basic for now but can be modified to allow for
     *                       material-specific birdhouses.
     * @param log            Ingredient for the birdhouse.
     * @param planks         Ingredient for the birdhouse.
     */
    private static void birdhouseRecipe(
            Consumer<FinishedRecipe> recipeConsumer, ItemLike birdhouse, ItemLike log, ItemLike planks) {
        //Note: To add more ingredients, overload this method with more ItemLikes, provide the item in the function call
        //and provide a pattern and define below. Unlocked by controls what unlocks the recipe for a player in survival
        // mode.
        ShapedRecipeBuilder.shaped(birdhouse)
                .pattern("LLL")
                .pattern(" P ")
                .pattern(" P ")
                .define('L', log)
                .define('P', planks)
                .unlockedBy("has_log", has(log))
                .unlockedBy("has_planks", has(planks))
                .group("birdhouse")
                .save(recipeConsumer);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        birdhouseRecipe(consumer, Registration.BIRDHOUSE_BLOCK.get(), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_WOOD);
    }
}
