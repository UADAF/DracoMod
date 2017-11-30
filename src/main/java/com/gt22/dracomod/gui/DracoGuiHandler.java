package com.gt22.dracomod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class DracoGuiHandler implements IGuiHandler{

    public static final int dracoControllerGUIID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case dracoControllerGUIID: return new DracoControllerGUI(player);
        }
        return null;
    }
}
