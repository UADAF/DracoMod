package com.gt22.dracomod.items;

import baubles.api.BaubleType;
import com.gt22.dracomod.DracoMod;
import com.gt22.dracomod.modules.IDracoModule;
import com.gt22.dracomod.utils.DracoConfig;
import com.gt22.dracomod.utils.Utilities;
import com.gt22.gt22core.integration.baubles.item.BaubleBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Draco extends BaubleBase {
	private static final ExecutorService dracoExecutor = Executors.newCachedThreadPool();
	public Draco() {
		super("draco", DracoMod.modInstance, BaubleType.AMULET);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        List<IDracoModule> installed = Utilities.getInstalledModules(stack);
        list.addAll(installed.stream().map(m -> " " + (m.isEnabled(stack) ? "+" : "-") +m.getName()).collect(Collectors.toSet()));
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return EnumRarity.epic;
	}

	int tick = 0;

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
        if(!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) entity;
        List<IDracoModule> modules = Utilities.getInstalledModules(itemstack);
        tick++;
        if(tick >= DracoConfig.dracoTickDelay){
            tick = 0;
            dracoExecutor.submit(() -> modules.forEach(module -> {
            	if(module.doNeedTick() && module.isEnabled(itemstack))
            		module.getAction().perform(player, player.getEntityWorld(), itemstack, module);
			}), player.getDisplayName() + "_Draco_TickThread");
        }
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return player instanceof EntityPlayer;
	}

	public static NBTTagCompound getModuleNbt(IDracoModule module, ItemStack draco){
		if(!draco.getTagCompound().hasKey(module.getNbtTag())) {
			draco.getTagCompound().setTag(module.getNbtTag(), new NBTTagCompound());
		}
		return draco.getTagCompound().getCompoundTag(module.getNbtTag());
	}

	public static void setModuleNbt(NBTTagCompound tag, ItemStack draco, IDracoModule module){
		draco.getTagCompound().setTag(module.getNbtTag(),  tag);
	}
}
