package com.awesomeninja.assorted_additions.event;

import com.awesomeninja.assorted_additions.AssortedAdditions;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = AssortedAdditions.MODID)
    public static class CommonEvents {
        
    }
    @Mod.EventBusSubscriber(modid = AssortedAdditions.MODID, bus = Bus.MOD)
    public static class EventBusEvents {
        
    }
}
