package com.gt22.dracomod.utils;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class DracoConfig {

    public static Configuration config;

    public static int asEsFlRange;
    public static int dracoTickDelay;

    public static void init(File f){
        config = new Configuration(f);
        config.load();
        asEsFlRange = config.getInt("asEsFlRange", "modules", 20, 1, 100, "Range of Astral Electrostatic Field");
        dracoTickDelay = config.getInt("dracoTickDelay", "draco", 25,15, 10000, "");
        config.save();
    }

}
