package com.qtech.forgemods.core.listener;

import com.qtech.forgemods.core.QFMCore;
import lombok.experimental.UtilityClass;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

/**
 * Entity listener.
 *
 * @author Qboi123
 */
@Mod.EventBusSubscriber(modid = QFMCore.modId, value = Dist.CLIENT)
@UtilityClass
@Deprecated
public class EntityListener {
}
