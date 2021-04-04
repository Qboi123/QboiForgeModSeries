package com.qtech.forgemods.core.listener;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.debug.OreProfiler;
import com.qtech.forgemods.core.modules.environment.ores.Ores;
import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Client listener
 *
 * @author (partial) CoFH - https://github.com/CoFH
 */
@Mod.EventBusSubscriber(modid = QFMCore.modId, bus = Mod.EventBusSubscriber.Bus.FORGE)
@UtilityClass
public class ServerListener {
    @SubscribeEvent
    public static void handleClientChatEvent(ChunkEvent.Load event) {
        if (OreProfiler.isActive()) {
            IChunk chunk = event.getChunk();
            ChunkPos chunkPos = chunk.getPos();
//            event.getWorld().getPlayers().forEach((p) -> p.sendMessage(new StringTextComponent("> Scanning chunk " + chunkPos.toString()), p.getUniqueID()));
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 255; y++) {
                    for (int z = 0; z < 255; z++) {
                        BlockState blockState = chunk.getBlockState(new BlockPos(x, y, z));
                        Block block = blockState.getBlock();
                        BlockPos pos = new BlockPos(chunkPos.x * 16 + x, y, chunkPos.z * 16 + z);
                        if (block == Ores.INFINITY.getOre()) {
                            event.getWorld().getPlayers().forEach((p) -> p.sendMessage(new StringTextComponent("= Found Infinity Ore at: " + pos.getCoordinatesAsString()), p.getUniqueID()));
                        }
//                        if (block == Ores.ULTRINIUM.getOre()) {
//                            event.getWorld().getPlayers().forEach((p) -> p.sendMessage(new StringTextComponent("= Found Ultrinium Ore at: " + pos.getCoordinatesAsString()), p.getUniqueID()));
//                        }
                    }
                }
            }
//            event.getWorld().getPlayers().forEach((p) -> p.sendMessage(new StringTextComponent("+ Scanned chunk " + chunkPos.toString()), p.getUniqueID()));
        }
    }
}