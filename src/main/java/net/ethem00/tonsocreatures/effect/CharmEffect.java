package net.ethem00.tonsocreatures.effect;

import com.mojang.logging.LogUtils;
import net.ethem00.tonsocreatures.world.entity.ai.goal.CharmGoal;
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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Collection;

public class CharmEffect extends MobEffect {

    private CharmGoal charmGoal;
    private RandomSource random = RandomSource.create();
    int i = 0;
    boolean doOnce = false;

    public CharmEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        //Check for last breed, if too soon, don't apply effect.
        //Apply Hearts              (DONE)
        //Get position
        //Search 12 block radius for another entity with Charm
        //Pathfind to entity
        //Create Child from parents
        //Add NBT data "Last breed" (Compare time between next attempt?) (World.# getWorldTime() )
        //Remove effect

        // Below DOES recognize whether or not the entity is a player.
        // Below DOES recognize current level.
        // Below does NOT emit particles?!
        if (!(pLivingEntity instanceof Player)){

            //Check if level is Server Side, so we can use server side methods.
            //System.out.println("Is client side: " + pLivingEntity.level().isClientSide() );
            if(pLivingEntity.level() instanceof ServerLevel servLevel)
            {
                this.i++;
                if(this.i > 40) {this.i = 0;}

                if(this.i % 8 == 0)
                {
                    //System.out.println("Execution: " + i);
                    double d0 = this.random.nextGaussian() * 0.02;
                    double d1 = this.random.nextGaussian() * 0.02;
                    double d2 = this.random.nextGaussian() * 0.02;
                    servLevel.sendParticles(ParticleTypes.HEART, pLivingEntity.getRandomX(1.0), pLivingEntity.getRandomY() + 1, pLivingEntity.getZ(0.0), 1, d0, d1, d2, 1);
                }

                if(pLivingEntity instanceof PathfinderMob mob)
                {
                    ((PathfinderMob) pLivingEntity).goalSelector.addGoal(0, new CharmGoal(((PathfinderMob) pLivingEntity), 1.25));
                    //Do once
                    /*Add charmgoal goal
                    if(this.doOnce == false)
                    {
                        ((PathfinderMob) pLivingEntity).goalSelector.addGoal(20, new CharmGoal(((PathfinderMob) pLivingEntity), 1.25));
                        this.doOnce = true;
                        System.out.println("HELLO I'M " + this.doOnce);
                    }
                    */


                    /*((Mob) pLivingEntity).goalSelector.addGoal();
                    Collection<MobEffectInstance> activeEffects = pLivingEntity.getActiveEffects();
                    for(MobEffectInstance effectInstance : activeEffects)
                    {
                        if(effectInstance.getEffect().get() == ModEffects.CHARM_EFFECT.get())
                        {
                            System.out.println("True!");
                        }
                        else
                        {
                            System.out.println(effectInstance.getEffect());
                            System.out.println("False!");
                        }
                        System.out.println(ModEffects.CHARM_EFFECT.get());
                        System.out.println(activeEffects);
                    }
                    */

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
