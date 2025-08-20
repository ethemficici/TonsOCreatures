package net.ethem00.tonsocreatures.potion;

import net.ethem00.tonsocreatures.TonsOCreatures;
import net.ethem00.tonsocreatures.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, TonsOCreatures.MOD_ID);

    //?Rename to tonsoc_charming?
    public static final RegistryObject<Potion> CHARMING = POTIONS.register("charming",
            () -> new Potion(new MobEffectInstance(ModEffects.CHARM_EFFECT.getHolder().get(), 500, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
