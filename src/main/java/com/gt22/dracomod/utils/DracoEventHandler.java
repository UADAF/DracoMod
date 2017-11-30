package com.gt22.dracomod.utils;

import com.gt22.dracomod.DracoMod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class DracoEventHandler {

    @SubscribeEvent
    public void onModDrop(LivingDropsEvent e){
        System.out.println(e.source.damageType);
        e.setCanceled(e.source == DracoMod.dracoDamage || e.entityLiving.getEntityData().hasKey("dracoWithered"));
    }


}
