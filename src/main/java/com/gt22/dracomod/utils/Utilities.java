package com.gt22.dracomod.utils;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.google.common.collect.Lists;
import com.gt22.dracomod.DracoMod;
import com.gt22.dracomod.modules.IDracoModule;
import com.gt22.dracomod.registry.ItemRegistry;
import com.gt22.dracomod.registry.ModuleRegistry;
import com.gt22.gt22core.utils.ItemNbt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

    public static boolean hasItemInBaubles(Item item, EntityPlayer player){
        IInventory inv = BaublesApi.getBaubles(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if(inv.getStackInSlot(i) != null && inv.getStackInSlot(i).getItem() == item)
                return true;
        }
        return false;
    }

    public static IDracoModule getModuleByMeta(int i) {
        if(i >= ModuleRegistry.getCount())
            return null;
        return ModuleRegistry.dracoModules.get(i);
    }

    public static ItemStack getDraco(EntityPlayer player) {
        IInventory inv = BaublesApi.getBaubles(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if(s != null && s.getItem() == ItemRegistry.draco)
                return s;
        }
        return null;
    }

    public static List<IDracoModule> getInstalledModules(ItemStack draco) {
        List<IDracoModule> ret = new ArrayList<>();
        if(!draco.hasTagCompound())
            return ret;
        if(!draco.getTagCompound().hasKey(R.dracoModulesTag))
            return ret;
        int[] installed = draco.getTagCompound().getIntArray(R.dracoModulesTag);
        for (int i : installed)
            if(i >= 0 && i < ModuleRegistry.getCount())
                ret.add(getModuleByMeta(i));
        return ret;
    }

    public static boolean isModuleInstalled(IDracoModule module, ItemStack draco) {
        System.out.println(getInstalledModules(draco));
        for (IDracoModule d : getInstalledModules(draco)){
            DracoMod.modLog.debug(d);
            if(d.equals(module))
                return true;
        }
        return false;
    }

    public static void installModule(ItemStack draco, int meta) {
        ItemNbt.initNBT(draco);
        int[] modules = {meta};
        int[] arr = draco.getTagCompound().getIntArray(R.dracoModulesTag);
        if(draco.getTagCompound().hasKey(R.dracoModulesTag) && arr != null && arr.length > 0) {
            modules = Arrays.copyOf(arr, arr.length + 1);
            modules[arr.length] = meta;
        }
        draco.getTagCompound().setIntArray(R.dracoModulesTag, modules);
    }

    public static void removeModule(ItemStack draco, int meta) {
        try {
            int[] arr = draco.getTagCompound().getIntArray(R.dracoModulesTag);
            if(arr == null || arr.length == 0)
                return;
            draco.getTagCompound().setIntArray(R.dracoModulesTag, ArrayUtils.remove(arr, ArrayUtils.indexOf(arr, meta)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<String> formatModuleDescription(String desc) {
        String[] words = desc.split(" ");
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            double t = Math.max(Math.ceil(i/4.)-1, 0);
            if(ret.size()-1 < t)
                ret.add("");
            ret.set((int) t, ret.get((int) t)  + words[i] + " ");
        }
       return ret;
     }
}
