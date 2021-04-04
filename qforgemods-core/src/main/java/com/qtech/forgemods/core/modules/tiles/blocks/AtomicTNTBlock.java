package com.qtech.forgemods.core.modules.tiles.blocks;

import com.qtech.forgemods.core.common.TNTProperties;
import net.minecraft.world.Explosion;

/**
 * Atomic TNT block.
 *
 * @author Qboi123
 */
public class AtomicTNTBlock extends CustomTNTBlock<AtomicTNTBlock> {
    public AtomicTNTBlock(Properties properties) {
        super(properties, TNTProperties.builder().radius(48.0f).causesFire(true).mode(Explosion.Mode.DESTROY).build());
    }
}
