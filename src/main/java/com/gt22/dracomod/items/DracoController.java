package com.gt22.dracomod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DracoController extends DracoModItemBase{
    public DracoController() {
        super("dracoController");
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
