package com.gt22.dracomod.modules;

import net.minecraft.item.EnumRarity;

import java.util.ArrayList;
import java.util.List;

public class DracoModuleBuilder {

    private String mName, mTag;
    private IDracoModule.IDracoAction mAction;
    private List<IDracoModule> mDependencies, mBlacklist;
    private EnumRarity mRarity;
    private String mDesc;
    private boolean mTick;

    public DracoModuleBuilder(String mName) {
        this.mName = mName;
        this.mTag = "dm_" + mName;
        this.mAction = (player, world, draco) -> {};
        this.mDependencies = new ArrayList<>();
        this.mBlacklist = new ArrayList<>();
        this.mRarity = EnumRarity.common;
        this.mDesc = "Just Module";
        this.mTick = true;
    }

	public DracoModuleBuilder setTag(String mTag) {
		this.mTag = mTag;
		return this;
	}

	public DracoModuleBuilder setAction(IDracoModule.IDracoAction action) {
        this.mAction = action;
        return this;
    }

    public DracoModuleBuilder addDependencies(IDracoModule ...deps){
        for (IDracoModule m : deps){
            if(!mDependencies.contains(m) && !mBlacklist.contains(m))
                mDependencies.add(m);
        }
        return this;
    }

    public DracoModuleBuilder addConflictedModule(IDracoModule ...modules){
        for(IDracoModule m : modules){
            if(!mBlacklist.contains(m) && !mDependencies.contains(m))
                mBlacklist.add(m);
        }
        return this;
    }

    public DracoModuleBuilder setRarity(EnumRarity r) {
        this.mRarity = r;
        return this;
    }

    public DracoModuleBuilder setDescription(String str){
        this.mDesc = str;
        return this;
    }

    public DracoModuleBuilder disableTicks(){
        this.mTick = false;
        return this;
    }

    public IDracoModule build(){
        return new IDracoModule() {
            @Override
            public String getName() {
                return mName;
            }

            @Override
            public String getNbtTag() {
                return mTag;
            }

            @Override
            public IDracoAction getAction() {
                return mAction;
            }

            @Override
            public EnumRarity getRarity() {
                return mRarity;
            }

            @Override
            public List<IDracoModule> getDependencies() {
                return mDependencies;
            }

            @Override
            public List<IDracoModule> getConflicts() {
                return mBlacklist;
            }

            @Override
            public String getDescription() {
                return mDesc;
            }

            @Override
            public boolean doNeedTick() {
                return mTick;
            }
        };
    }
}
