package com.gt22.dracomod.items;

import com.gt22.dracomod.modules.IDracoModule;
import com.gt22.dracomod.utils.Utilities;
import com.gt22.gt22core.utils.ChatComponentError;
import com.gt22.gt22core.utils.ChatComponentSucces;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class DracoModule extends DracoModMetaItem {

	public DracoModule(int maxmeta) {
		super("dracoModule", maxmeta);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return Utilities.getModuleByMeta(itemStack.getItemDamage()).getRarity();
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return Utilities.getModuleByMeta(itemStack.getItemDamage()).getName();
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		IDracoModule module = Utilities.getModuleByMeta(stack.getItemDamage());
		if (module == null) {
			list.add("INVALID MODULE. REMOVE IT, RIGHT NOW!!!!!!");
			return;
		}
		list.add(module.getDescription());
		list.add("");
		if (GuiScreen.isShiftKeyDown()) {
			list.add("Name: " + module.getName());
			list.add("Tag: " + module.getNbtTag());
			if (!module.getConflicts().isEmpty()) {
				list.add("Dependencies:");
				list.addAll(module.getDependencies().stream().map(m -> " -" + m.getName()).collect(Collectors.toList()));
			}
			if (!module.getConflicts().isEmpty()) {
				list.add("Blacklisted:");
				list.addAll(module.getConflicts().stream().map(m -> " -" + m.getName()).collect(Collectors.toList()));
			}
		} else {
			if(!module.getDependencies().isEmpty())
				list.add("Dependencies: " + module.getDependencies().size());
			if(!module.getConflicts().isEmpty())
				list.add("Conflicts: " + module.getConflicts().size());
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			ItemStack draco = Utilities.getDraco(player);
			IDracoModule thisModule = Utilities.getModuleByMeta(stack.getItemDamage());
			if (draco == null) {
				player.addChatComponentMessage(new ChatComponentError("You do not have a Draco in your Baubles Inventory"));
				return stack;
			}
			if (Utilities.isModuleInstalled(thisModule, draco)) {
				player.addChatComponentMessage(new ChatComponentError("This module already installed in your Draco"));
				return stack;
			}
			Utilities.installModule(draco, stack.getItemDamage());
			stack.stackSize--;
			if (Utilities.isModuleInstalled(thisModule, draco)) {
				player.addChatComponentMessage(new ChatComponentSucces("Module " + thisModule.getName() + " successfully installed into your Draco"));
			} else {
				player.addChatComponentMessage(new ChatComponentError("HTTP 500"));
			}
		}
		return stack;
	}
}
