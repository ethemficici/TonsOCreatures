package net.ethem00.tonsocreatures.effect;

import net.ethem00.tonsocreatures.TonsOCreatures;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.jar.Attributes;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TonsOCreatures.MOD_ID);

    public static final RegistryObject<MobEffect> CHARM_EFFECT = MOB_EFFECTS.register("charm",
            () -> new CharmEffect(MobEffectCategory.NEUTRAL, 0xff96ef));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
