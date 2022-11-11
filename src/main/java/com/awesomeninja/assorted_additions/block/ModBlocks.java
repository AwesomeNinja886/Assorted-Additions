package com.awesomeninja.assorted_additions.block;

import java.util.function.Supplier;

import com.awesomeninja.assorted_additions.AssortedAdditions;
import com.awesomeninja.assorted_additions.block.custom.BuddingGlowstoneBlock;
import com.awesomeninja.assorted_additions.block.custom.GlowstoneClusterBlock;
import com.awesomeninja.assorted_additions.item.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherrackBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AssortedAdditions.MODID);

    // STONE VARIANTS
    public static final RegistryObject<Block> POLISHED_NETHERRACK = registerBlock("polished_netherrack", () -> new NetherrackBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.NETHERRACK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BUDDING_GLOWSTONE = registerBlock("budding_glowstone", () -> new BuddingGlowstoneBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SMALL_GLOWSTONE_BUD = registerBlock("small_glowstone_bud", () -> new GlowstoneClusterBlock(9, 2, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MEDIUM_GLOWSTONE_BUD = registerBlock("medium_glowstone_bud", () -> new GlowstoneClusterBlock(10, 2, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> LARGE_GLOWSTONE_BUD = registerBlock("large_glowstone_bud", () -> new GlowstoneClusterBlock(12, 1, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLOWSTONE_CLUSTER = registerBlock("glowstone_cluster", () -> new GlowstoneClusterBlock(16, 1, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
