package com.gt22.dracomod.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL30;

public class DracoControllerGUI extends GuiScreen{

    private EntityPlayer player;

    public DracoControllerGUI(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

    }
}
