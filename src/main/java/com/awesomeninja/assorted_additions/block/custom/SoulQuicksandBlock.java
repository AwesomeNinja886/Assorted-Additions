package com.awesomeninja.assorted_additions.block.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class SoulQuicksandBlock extends QuicksandBlock {
    public static ParticleOptions particleType = ParticleTypes.SOUL;
    public SoulQuicksandBlock(Properties pProperties) {
        super(Items.SOUL_SAND, pProperties);
    }

    public static boolean canEntityWalkOnQuicksand(Entity p_154256_) {
        if (p_154256_ instanceof LivingEntity) {
            if (((LivingEntity)p_154256_).getItemBySlot(EquipmentSlot.FEET).getAllEnchantments().containsKey(Enchantments.SOUL_SPEED)) {
                return true;
            }
        }
        return QuicksandBlock.canEntityWalkOnQuicksand(p_154256_);
    }
}
