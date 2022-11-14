package com.awesomeninja.assorted_additions.recipe;

import com.awesomeninja.assorted_additions.AssortedAdditions;
import com.awesomeninja.assorted_additions.block.ModBlocks;
import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

public class CyanRoseConversionRecipe extends SingleItemRecipe {
    
    public CyanRoseConversionRecipe(ResourceLocation p_44418_, String p_44419_, Ingredient p_44420_, ItemStack p_44421_) {
        super(Type.INSTANCE, Serializer.INSTANCE, p_44418_, p_44419_, p_44420_, p_44421_);
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return this.ingredient.test(p_44002_.getItem(0));
    }
    
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CYAN_ROSE.get());
    }

    public static class Type implements RecipeType<CyanRoseConversionRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "cyan_rose_converting";
    }

    public static class Serializer implements RecipeSerializer<CyanRoseConversionRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(AssortedAdditions.MODID, Type.ID);
  
        public CyanRoseConversionRecipe fromJson(ResourceLocation p_44449_, JsonObject p_44450_) {
           String s = GsonHelper.getAsString(p_44450_, "group", "");
           Ingredient ingredient;
           if (GsonHelper.isArrayNode(p_44450_, "ingredient")) {
              ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(p_44450_, "ingredient"));
           } else {
              ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44450_, "ingredient"));
           }
  
           String s1 = GsonHelper.getAsString(p_44450_, "result");
           int i = GsonHelper.getAsInt(p_44450_, "count");
           ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
           return new CyanRoseConversionRecipe(p_44449_, s, ingredient, itemstack);
        }
  
        public CyanRoseConversionRecipe fromNetwork(ResourceLocation p_44452_, FriendlyByteBuf p_44453_) {
           String s = p_44453_.readUtf();
           Ingredient ingredient = Ingredient.fromNetwork(p_44453_);
           ItemStack itemstack = p_44453_.readItem();
           return new CyanRoseConversionRecipe(p_44452_, s, ingredient, itemstack);
        }
  
        public void toNetwork(FriendlyByteBuf p_44440_, CyanRoseConversionRecipe p_44441_) {
           p_44440_.writeUtf(p_44441_.group);
           p_44441_.ingredient.toNetwork(p_44440_);
           p_44440_.writeItem(p_44441_.result);
        }

    }
}
