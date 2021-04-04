package com.qtech.forgemods.core.util;

import com.qtech.forgemods.core.QFMCore;
import lombok.experimental.UtilityClass;
import net.minecraft.util.ResourceLocation;

@UtilityClass
public final class Constants {
    // Recipe resource locations.
    public static final ResourceLocation ALLOY_SMELTING = QFMCore.rl("alloy_smelting");
    public static final ResourceLocation ARCANE_ESCALATING = QFMCore.rl("arcane_escalating");
    public static final ResourceLocation ENCHANTING = QFMCore.rl("enchanting");
    public static final ResourceLocation COMPRESSING = QFMCore.rl("compressing");
    public static final ResourceLocation CRUSHING = QFMCore.rl("crushing");
    public static final ResourceLocation DRYING = QFMCore.rl("drying");
    public static final ResourceLocation INFUSING = QFMCore.rl("infusing");
    public static final ResourceLocation MIXING = QFMCore.rl("mixing");
    public static final ResourceLocation REFINING = QFMCore.rl("refining");
    public static final ResourceLocation SOLIDIFYING = QFMCore.rl("solidifying");

    // Machine upgrades
    public static final int UPGRADES_PER_SLOT = 1;
    public static final float UPGRADE_PROCESSING_SPEED_AMOUNT = 0.5f;
    public static final float UPGRADE_SECONDARY_OUTPUT_AMOUNT = 0.1f;
    public static final float UPGRADE_ENERGY_EFFICIENCY_AMOUNT = -0.15f;
    public static final int UPGRADE_RANGE_AMOUNT = 2;
}
