package com.qtech.forgemods.core.modules.tiles.blocks.trees;

import com.qtech.forgemods.core.modules.environment.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class EucalyptusTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(@NotNull Random randomIn, boolean largeHive) {
        return ModFeatures.EUCALYPTUS_TREE;
    }
}
