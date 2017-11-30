package com.gt22.dracomod.items;

import com.gt22.dracomod.DracoMod;
import com.gt22.gt22core.baseclasses.item.ItemBase;

public class DracoModItemBase extends ItemBase {

	public DracoModItemBase(String unlocName, String texturedir) {
		super(unlocName, DracoMod.modInstance, texturedir);
	}

	public DracoModItemBase(String unlocName) {
		super(unlocName, DracoMod.modInstance);
	}


}
