package com.qtech.forgemods.core.modules.tiles.blocks.base;

import com.qtech.forgemods.core.common.OreProperties;
import com.qtech.forgemods.core.common.interfaces.IHasOreProperties;
import net.minecraft.block.OreBlock;

public class BaseOreBlock extends OreBlock implements IHasOreProperties {
    private final OreProperties oreProperties;

    public BaseOreBlock(Properties properties, OreProperties oreProperties) {
        super(properties);
        this.oreProperties = oreProperties;
    }

    @Override
    public OreProperties getOreProperties() {
        return oreProperties;
    }
}
