package com.gt22.dracomod.registry;

import com.gt22.dracomod.items.Draco;
import com.gt22.dracomod.items.DracoModule;
import com.gt22.gt22core.baseclasses.item.ItemBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Draco draco;
	public static DracoModule dracoModule;

	private static void register(ItemBase i) {
		GameRegistry.registerItem(i, i.getPainName());
	}

	public static void init() {
		register(draco = new Draco());
		register(dracoModule = new DracoModule(ModuleRegistry.init()));
	}
}

