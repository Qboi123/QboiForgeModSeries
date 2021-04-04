package com.qtech.forgemods.core.client;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.environment.ModEntities;
import com.qtech.forgemods.core.modules.environment.client.renderer.RenderBabyCreeper;
import com.qtech.forgemods.core.modules.environment.client.renderer.RenderBabyEnderman;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.StrayRenderer;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@UtilityClass
@Mod.EventBusSubscriber(modid = QFMCore.modId, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdditionsClientRegistration {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
//        new AdditionsKeyHandler();

        //Register entity rendering handlers
        ClientRegistrationUtil.registerEntityRenderingHandler(ModEntities.BABY_CREEPER, RenderBabyCreeper::new);
        ClientRegistrationUtil.registerEntityRenderingHandler(ModEntities.BABY_ENDERMAN, RenderBabyEnderman::new);
        ClientRegistrationUtil.registerEntityRenderingHandler(ModEntities.BABY_SKELETON, SkeletonRenderer::new);
        ClientRegistrationUtil.registerEntityRenderingHandler(ModEntities.BABY_STRAY, StrayRenderer::new);
        ClientRegistrationUtil.registerEntityRenderingHandler(ModEntities.BABY_WITHER_SKELETON, WitherSkeletonRenderer::new);
    }

}