package net.ethem00.tonsocreatures.effect;

import com.mojang.logging.LogUtils;
import net.minecraft.client.particle.HeartParticle;
import net.minecraft.core.BlockMath;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import org.slf4j.Logger;

public class CharmEffect extends MobEffect {

    private static final Logger LOGGER = LogUtils.getLogger();
    private RandomSource random = RandomSource.create();
    int i = 0;

    public CharmEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        //Apply Hearts
        //Get position
        //Search 12 block radius for another entity with Charm
        //Pathfind to entity
        //

        // Below DOES recognize whether or not the entity is a player.
        // Below DOES recognize current level.
        // Below does NOT emit particles?!
        if (!(pLivingEntity instanceof Player)){

            //Check if level is Server Side, so we can use server side methods.
            //System.out.println("Is client side: " + pLivingEntity.level().isClientSide() );
            if(pLivingEntity.level() instanceof ServerLevel servLevel)
            {
                i++;
                if(i % 4 == 0)
                {
                    System.out.println("Execution: " + i);
                    double d0 = this.random.nextGaussian() * 0.02;
                    double d1 = this.random.nextGaussian() * 0.02;
                    double d2 = this.random.nextGaussian() * 0.02;
                    servLevel.sendParticles(ParticleTypes.HEART, pLivingEntity.getRandomX(1.0), pLivingEntity.getRandomY() + 1, pLivingEntity.getZ(1.0), 1, d0, d1, d2, 1);
                }
            }
            return true;
        }

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {

        //Apply effect every 20 ticks
        //Set to always true for now, change later!
        return true;
    }



}
