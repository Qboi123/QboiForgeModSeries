package com.qtech.forgemods.core.modules.environment.client.renderer;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.environment.client.model.MoobloomModel;
import com.qtech.forgemods.core.modules.environment.entities.MoobloomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/**
 * Moobloom entity renderer class.
 *
 * @author Qboi123
 */
@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, MoobloomModel<MoobloomEntity>> {
    public MoobloomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MoobloomModel<>(), 0.7F);
//        this.addLayer(new MoobloomFlowerLayer<>(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public @NotNull ResourceLocation getEntityTexture(@NotNull MoobloomEntity entity) {
        return new ResourceLocation(QFMCore.modId, "textures/entity/cow/moobloom/" + entity.getMoobloomType().getFilename());
    }
}
