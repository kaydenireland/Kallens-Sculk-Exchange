package net.kallen.kse.datagen;


import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.item.kseItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class kseRecipeProvider extends RecipeProvider {
    public kseRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new kseRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }


    @Override
    protected void buildRecipes() {
        List<ItemLike> ECHO_SMELTABLES = List.of(kseBlocks.ECHO_ORE, kseBlocks.DEEPSLATE_ECHO_ORE);

        shaped(RecipeCategory.MISC, kseBlocks.ECHO_SHARD_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', Items.ECHO_SHARD)
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
                .save(output);

        shapeless(RecipeCategory.MISC, Items.ECHO_SHARD, 9)
                .requires(kseBlocks.ECHO_SHARD_BLOCK)
                .unlockedBy("has_echo_shard_block", has(kseBlocks.ECHO_SHARD_BLOCK)).save(output);

        shapeless(RecipeCategory.MISC, kseBlocks.SCULKED_COBBLESTONE)
                .requires(Blocks.COBBLESTONE)
                .requires(Blocks.SCULK_VEIN)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE))
                .unlockedBy("has_sculk_vein", has(Blocks.SCULK_VEIN))
                .save(output);

        shapeless(RecipeCategory.MISC, kseBlocks.SCULKED_DEEPSLATE)
                .requires(Blocks.COBBLED_DEEPSLATE)
                .requires(Blocks.SCULK_VEIN)
                .unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE))
                .unlockedBy("has_sculk_vein", has(Blocks.SCULK_VEIN))
                .save(output);

        oreSmelting(output, ECHO_SMELTABLES, RecipeCategory.MISC, Items.ECHO_SHARD, 0.25f, 200, "echo");
        oreBlasting(output, ECHO_SMELTABLES, RecipeCategory.MISC, Items.ECHO_SHARD, 0.25f, 100, "echo");

        spearBuilder(kseItems.AMETHYST_SPEAR, Items.AMETHYST_SHARD, Items.STICK);
        spearBuilder(kseItems.ECHO_SPEAR, Items.ECHO_SHARD, Items.STICK);

        // Bells

        shaped(RecipeCategory.BUILDING_BLOCKS, kseItems.IRON_BELL.get())
                .pattern(" A ")
                .pattern("A A")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(output);

        shaped(RecipeCategory.MISC, kseItems.AMETHYST_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', kseItems.IRON_BELL)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .unlockedBy("has_iron_bell", has(kseItems.IRON_BELL))
                .save(output);

        shaped(RecipeCategory.MISC, kseItems.ECHO_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.ECHO_SHARD)
                .define('B', kseItems.IRON_BELL)
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
                .unlockedBy("has_iron_bell", has(kseItems.IRON_BELL))
                .save(output);

        shaped(RecipeCategory.MISC, kseItems.GLOW_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.GLOWSTONE)
                .define('B', kseItems.IRON_BELL)
                .unlockedBy("has_glowstone", has(Blocks.GLOWSTONE))
                .unlockedBy("has_iron_bell", has(kseItems.IRON_BELL))
                .save(output);

    }

    protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, KallensSculkExpanse.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected void spearBuilder(ItemLike pSpear, ItemLike pMaterial, ItemLike pStick) {
        shaped(RecipeCategory.MISC, pSpear)
                .pattern("  T")
                .pattern(" / ")
                .pattern("/  ")
                .define('T', pMaterial)
                .define('/', pStick)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .unlockedBy(getHasName(pStick), has(pStick))
                .save(output);

        shaped(RecipeCategory.MISC, pSpear)
                .pattern("T  ")
                .pattern(" / ")
                .pattern("  /")
                .define('T', pMaterial)
                .define('/', pStick)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .unlockedBy(getHasName(pStick), has(pStick))
                .save(output, KallensSculkExpanse.MODID + ":" + getItemName(pSpear) + "reverse");

    }

}