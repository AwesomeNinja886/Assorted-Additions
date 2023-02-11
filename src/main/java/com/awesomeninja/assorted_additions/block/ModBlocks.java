package com.awesomeninja.assorted_additions.block;

import java.util.function.Supplier;

import com.awesomeninja.assorted_additions.AssortedAdditions;
import com.awesomeninja.assorted_additions.block.custom.BuddingGlowstoneBlock;
import com.awesomeninja.assorted_additions.block.custom.ChiseledPillarBlock;
import com.awesomeninja.assorted_additions.block.custom.ChorusLogBlock;
import com.awesomeninja.assorted_additions.block.custom.CyanRoseBlock;
import com.awesomeninja.assorted_additions.block.custom.GlowstoneClusterBlock;
import com.awesomeninja.assorted_additions.block.custom.PottedCyanRoseBlock;
import com.awesomeninja.assorted_additions.block.custom.QuicksandBlock;
import com.awesomeninja.assorted_additions.block.custom.ValidPortalFrameBlock;
import com.awesomeninja.assorted_additions.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AssortedAdditions.MODID);

    // Polished Vanilla Blocks
    public static final RegistryObject<Block> POLISHED_NETHERRACK = registerBlock("polished_netherrack", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.NETHERRACK)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_OBSIDIAN = registerBlock("polished_obsidian", () -> new ValidPortalFrameBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_BASALT_BRICKS = registerBlock("polished_basalt_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BASALT)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    
    // Stone Brick Variants
    public static final RegistryObject<Block> SMOOTH_STONE_BRICKS = registerBlock("smooth_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STONE_TILES = registerBlock("stone_tiles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> NETHER_BRICK_TILES = registerBlock("nether_brick_tiles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ELDER_PRISMARINE_BRICKS = registerBlock("elder_prismarine_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DARK_ELDER_PRISMARINE = registerBlock("dark_elder_prismarine", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Chiseled Pillars
    public static final RegistryObject<Block> CHISELED_DEEPSLATE_PILLAR = registerBlock("chiseled_deepslate_pillar", () -> new ChiseledPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_QUARTZ_PILLAR = registerBlock("chiseled_quartz_pillar", () -> new ChiseledPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Budding Glowstone
    public static final RegistryObject<Block> BUDDING_GLOWSTONE = registerBlock("budding_glowstone", () -> new BuddingGlowstoneBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_GLOWSTONE_BUD = registerBlock("small_glowstone_bud", () -> new GlowstoneClusterBlock(9, 2, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MEDIUM_GLOWSTONE_BUD = registerBlock("medium_glowstone_bud", () -> new GlowstoneClusterBlock(10, 2, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> LARGE_GLOWSTONE_BUD = registerBlock("large_glowstone_bud", () -> new GlowstoneClusterBlock(12, 1, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLOWSTONE_CLUSTER = registerBlock("glowstone_cluster", () -> new GlowstoneClusterBlock(16, 1, BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Gabbro
    public static final RegistryObject<Block> GABBRO = registerBlock("gabbro", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_GABBRO = registerBlock("polished_gabbro", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_GABBRO_BRICKS = registerBlock("polished_gabbro_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    
    // Remastered Classic Blocks
    public static final RegistryObject<Block> CLASSIC_STONE = registerBlock("classic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CLASSIC_COBBLESTONE = registerBlock("classic_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RETRO_COBBLESTONE = registerBlock("retro_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYAN_ROSE = registerBlock("cyan_rose", () -> new CyanRoseBlock(BlockBehaviour.Properties.of(Material.PLANT).lightLevel(state -> 3).sound(SoundType.GLOW_LICHEN).noCollission()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POTTED_CYAN_ROSE = BLOCKS.register("potted_cyan_rose", () -> new PottedCyanRoseBlock(BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).lightLevel(state -> 3)));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN = registerBlock("glowing_obsidian", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).strength(50f, 1200f).lightLevel(state -> 12)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    // public static final RegistryObject<Block> CLASSIC_LOG = registerBlock("classic_log", () -> new ClassicLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    // public static final RegistryObject<Block> STRIPPED_CLASSIC_LOG = registerBlock("classic_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Remastered Education Edition Blocks
    public static final RegistryObject<Block> REINFORCED_GLASS = registerBlock("reinforced_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(25.0F, 1200.0F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::always).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Chorus Wood
    public static final RegistryObject<Block> POTTED_CHORUS_FLOWER = BLOCKS.register("potted_chorus_flower", () -> new FlowerPotBlock(Blocks.CHORUS_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));
    public static final RegistryObject<Block> CHORUS_BLOCK = registerBlock("chorus_block", () -> new ChorusLogBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHORUS_BLOCK = registerBlock("stripped_chorus_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_PLANKS = registerBlock("chorus_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_SLAB = registerBlock("chorus_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_FENCE = registerBlock("chorus_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_STAIRS = registerBlock("chorus_stairs", () -> new StairBlock(CHORUS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_BUTTON = registerBlock("chorus_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_PRESSURE_PLATE = registerBlock("chorus_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_DOOR = registerBlock("chorus_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_TRAPDOOR = registerBlock("chorus_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHORUS_FENCE_GATE = registerBlock("chorus_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 4.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    
    // Quicksand
    //public static final RegistryObject<Block> QUICKSAND = registerBlock("quicksand", () -> new QuicksandBlock(ModItems.QUICKSAND_BUCKET.get(), BlockBehaviour.Properties.copy(Blocks.SAND).dynamicShape()), CreativeModeTab.TAB_MISC, false);
    
    // Compacted Items
    public static final RegistryObject<Block> PACKED_LEATHER = registerBlock("packed_leather", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Industrial Blocks
    public static final RegistryObject<Block> INDUSTRIAL_BRICKS = registerBlock("industrial_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.NETHER_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CAST_IRON_BLOCK = registerBlock("cast_iron_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.5F, 8.0F).sound(SoundType.METAL)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab, boolean registerItem) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if (registerItem) {
            registerBlockItem(name, toReturn, tab);
        }
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
    /**
    * Method copied from {@link net.minecraft.world.level.block.Blocks}
    */
    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return (boolean)false;
    }
    /**
    * Method copied from {@link net.minecraft.world.level.block.Blocks}
    */
    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_) {
        return false;
    }
    /**
    * Method copied from {@link net.minecraft.world.level.block.Blocks}
    */
    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_, EntityType<?> p_50782_) {
        return true;
    }
    /**
    * Method copied from {@link net.minecraft.world.level.block.Blocks}
    */
    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }
}
