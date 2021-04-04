package com.qtech.texturedmodels.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class IllusionPressurePlatePressedModelLoader implements IModelLoader<IllusionPressurePlatePressedModelGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public IllusionPressurePlatePressedModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new IllusionPressurePlatePressedModelGeometry();
    }
}
