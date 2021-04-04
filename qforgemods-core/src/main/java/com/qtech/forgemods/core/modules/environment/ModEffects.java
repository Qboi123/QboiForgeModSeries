package com.qtech.forgemods.core.modules.environment;

import com.qtech.forgemods.core.effects.CurseEffect;
import com.qtech.forgemods.core.init.Registration;
import com.qtech.forgemods.core.modules.environment.effects.RadiationEffect;
import com.qtech.forgemods.core.util.ExceptionUtil;
import lombok.experimental.UtilityClass;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@UtilityClass
public final class ModEffects {

    ///////////////////
    //     Racks     //
    ///////////////////
    public static final RegistryObject<CurseEffect> CURSE = register("curse", CurseEffect::new);
    public static final RegistryObject<RadiationEffect> RADIATION = register("radiation", RadiationEffect::new);

    //////////////////////////////
    //     Utility methods     //
    //////////////////////////////
    private static <T extends Effect> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return Registration.POTIONS.register(name, supplier);
    }

    public static void register() {

    }
}
