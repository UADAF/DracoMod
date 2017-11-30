package com.gt22.dracomod.registry;

import com.gt22.dracomod.DracoMod;
import com.gt22.dracomod.modules.DracoModuleBuilder;
import com.gt22.dracomod.modules.IDracoModule;
import com.gt22.dracomod.utils.DracoConfig;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import java.util.ArrayList;
import java.util.List;

public class ModuleRegistry {

    public static List<IDracoModule> dracoModules;

    public static int init() {
        dracoModules = new ArrayList<>();
        register(new DracoModuleBuilder("Astral Electrostatic Field").setTag("dm_asElFl").setDescription("Projection of one of the levels of the astral for interaction with living entities.").setRarity(EnumRarity.uncommon).setAction((player, world, draco) -> {
            List<EntityMob> mobs = player.getEntityWorld().getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(
                    player.posX-DracoConfig.asEsFlRange,
                    player.posY-DracoConfig.asEsFlRange,
                    player.posZ-DracoConfig.asEsFlRange,
                    player.posX+DracoConfig.asEsFlRange,
                    player.posY+DracoConfig.asEsFlRange,
                    player.posZ+DracoConfig.asEsFlRange));
            mobs.forEach(entityMob -> {
                if(world.rand.nextBoolean())
                    entityMob.attackEntityFrom(DamageSource.cactus, world.rand.nextInt(30));
            });
        }).build());
        register(new DracoModuleBuilder("Feeder").setTag("dm_fd").setDescription("Decompose food from inventory and insterts it directly into blood").setRarity(EnumRarity.uncommon).setAction(((player, world, draco) -> {
        	FoodStats food = player.getFoodStats();
			int hunger = 20 - food.getFoodLevel();
			while(hunger > 0) {
				for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
					ItemStack s = player.inventory.getStackInSlot(i);

					if(s.getItem() instanceof ItemFood) {

						ItemFood it = (ItemFood) s.getItem();
						int hungerRefill = it.func_150905_g(s);
						if(hunger >= hungerRefill) {
							food.func_151686_a(it, s);
							s.stackSize--;
						}
					}
				}
			}
        })).build());
        return dracoModules.size();
    }

    public static void register(IDracoModule module) {
        dracoModules.add(module);
        DracoMod.modLog.info("Added module: %s with meta: %s", module.getName(), dracoModules.size()-1);
    }

    public static int getCount() {
        return dracoModules.size();
    }

}
