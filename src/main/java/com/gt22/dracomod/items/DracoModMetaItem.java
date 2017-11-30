package com.gt22.dracomod.items;

import com.gt22.dracomod.DracoMod;
import com.gt22.gt22core.baseclasses.item.MetaItem;

public class DracoModMetaItem extends MetaItem {
	public DracoModMetaItem(String unlocName, String textureDir, int maxmeta) {
		super(unlocName, DracoMod.modInstance, textureDir, maxmeta);
	}

	public DracoModMetaItem(String unlocName, int maxmeta) {
		super(unlocName, DracoMod.modInstance, maxmeta);
	}
}
