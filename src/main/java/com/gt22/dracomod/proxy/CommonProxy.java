package com.gt22.dracomod.proxy;

import com.gt22.dracomod.DracoMod;
import com.gt22.dracomod.gui.DracoGuiHandler;
import com.gt22.dracomod.registry.ItemRegistry;
import com.gt22.dracomod.utils.DracoEventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ItemRegistry.init();
	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(DracoMod.modInstance, new DracoGuiHandler());
		MinecraftForge.EVENT_BUS.register(new DracoEventHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
