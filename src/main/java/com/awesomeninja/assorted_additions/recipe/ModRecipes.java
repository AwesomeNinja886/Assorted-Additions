package com.awesomeninja.assorted_additions.recipe;

import com.awesomeninja.assorted_additions.AssortedAdditions;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AssortedAdditions.MODID);

    public static final RegistryObject<RecipeSerializer<CyanRoseConversionRecipe>> CYAN_ROSE_SERIALIZER =
            SERIALIZERS.register("cyan_rose_converting", () -> CyanRoseConversionRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
