package com.gt22.gt22core.core;

import com.gt22.gt22core.command.CommandHandler;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;
import com.gt22.gt22core.proxy.CommonProxy;
import com.gt22.gt22core.utils.ResourceLocGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;


@Mod(modid = Core.modid, name = Core.modid, version = Core.version, dependencies = "after:Thaumcraft")
public class Core
{
	public static final int majorversion = 1, minorversion = 3, mcversion = 1710, bugfixversion = 0;
	public static final String modid = "gt22core", name = "Gt22 core", version = majorversion + "." + minorversion + "." + mcversion + "." + bugfixversion;
	
	@SidedProxy(modId = modid, clientSide = "com.gt22.gt22core.proxy.ClientProxy", serverSide = "com.gt22.gt22core.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Instance(modid)
	public static Core instance;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e)
	{
		if(Loader.isModLoaded("Thaumcraft"))
			AdvThaumApi.init();
		proxy.preInit(e);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e)
	{
		proxy.init(e);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit(e);
	}
	
	@EventHandler
	public static void onServerStarted(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new CommandHandler());
	}
	
}
