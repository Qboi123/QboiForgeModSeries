package com.qtech.forgemods.core.modules.tiles;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.CoreRegisterWrapperModule;
import com.qtech.forgemods.core.common.ModuleSecurity;
import com.qsoftware.modlib.silentlib.registry.BlockDeferredRegister;
import com.qsoftware.modlib.silentlib.registry.BlockRegistryObject;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlocksModule extends CoreRegisterWrapperModule<Block> {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(QFMCore.modId);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, QFMCore.modId);

    @Override
    public void onEnable() {
        ModBlocks.register();
        ModBlocksAlt.register();

        BLOCKS.register(modEventBus);
    }

    @Override
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.SAFE;
    }

    @Override
    public String getName() {
        return "blocks";
    }

    @Override
    public ModuleCompatibility getCompatibility() {
        return ModuleCompatibility.FULL;
    }

    @Override
    public BlockDeferredRegister getDeferredRegister() {
        return BLOCKS;
    }

    @Override
    public <O extends Block> BlockRegistryObject<O> register(String name, Supplier<O> supplier) {
        return BLOCKS.register(name, supplier);
    }
}
