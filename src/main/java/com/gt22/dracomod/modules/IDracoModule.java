package com.gt22.dracomod.modules;

import com.gt22.dracomod.items.Draco;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public interface IDracoModule {

    interface IDracoAction {
        void perform(EntityPlayer player, World world, ItemStack draco, IDracoModule module);
    }

    interface IDracoStateChangeAction {
        void perform(EntityPlayer player, World world, ItemStack draco, IDracoModule module, boolean newState);
    }

    String getName();

    String getNbtTag();

    IDracoAction getAction();

    EnumRarity getRarity();

    List<IDracoModule> getDependencies();

    List<IDracoModule> getConflicts();

    String getDescription();

    boolean doNeedTick();

    default boolean isEnabled(ItemStack draco){
        NBTTagCompound nbt = Draco.getModuleNbt(this, draco);
        return nbt.hasKey("isEnabled") && nbt.getBoolean("isEnabled");
    }

    IDracoStateChangeAction getOnStateChangeAction();


    default boolean equals(IDracoModule m) {
        return m.getName().equals(getName());
    }

}
