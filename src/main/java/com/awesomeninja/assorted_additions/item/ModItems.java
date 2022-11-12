package com.awesomeninja.assorted_additions.item;

import com.awesomeninja.assorted_additions.AssortedAdditions;
import com.awesomeninja.assorted_additions.block.ModBlocks;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AssortedAdditions.MODID);
    
    //public static final RegistryObject<Item> QUICKSAND_BUCKET = ITEMS.register("quicksand_bucket", () -> new SolidBucketItem(ModBlocks.QUICKSAND.get(), SoundEvents.SAND_PLACE, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
