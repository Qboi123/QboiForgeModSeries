package com.qtech.forgemods.core.modules.environment.client.model;

import com.qtech.forgemods.core.QFMCore;

public class AdditionsModelCache extends BaseModelCache {

    public static final AdditionsModelCache INSTANCE = new AdditionsModelCache();

    public final JSONModelData BALLOON = registerJSON(QFMCore.rl("item/balloon"));
    public final JSONModelData BALLOON_FREE = registerJSON(QFMCore.rl("item/balloon_free"));
}
