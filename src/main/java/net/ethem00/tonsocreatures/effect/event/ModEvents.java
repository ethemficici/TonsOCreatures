package net.ethem00.tonsocreatures.effect.event;

import net.ethem00.tonsocreatures.TonsOCreatures;
import net.ethem00.tonsocreatures.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TonsOCreatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    //NEVER FORGET TO SUBSCRIBE TO EVENTS! (Needs to be public static void!)
    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, Items.ROSE_BUSH, ModPotions.CHARMING.getHolder().get());
    }
}
