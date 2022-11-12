package com.awesomeninja.assorted_additions.misc.painting;

import com.awesomeninja.assorted_additions.AssortedAdditions;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, AssortedAdditions.MODID);
    
    public static final RegistryObject<PaintingVariant> ENDER = PAINTING_VARIANTS.register("ender", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> ITEM = PAINTING_VARIANTS.register("item", () -> new PaintingVariant(32, 32));

    public static void register(IEventBus modEventBus) {
        PAINTING_VARIANTS.register(modEventBus);
    }
}
