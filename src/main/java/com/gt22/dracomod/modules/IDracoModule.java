package com.gt22.dracomod.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface IDracoModule {

    interface IDracoAction {
        void perform(EntityPlayer player, World world, ItemStack draco);
    }

    String getName();

    String getNbtTag();

    IDracoAction getAction();

    EnumRarity getRarity();

    List<IDracoModule> getDependencies();

    List<IDracoModule> getConflicts();

    String getDescription();

    boolean doNeedTick();

    default boolean equals(IDracoModule m) {
        return m.getName().equals(getName());
    }

}
