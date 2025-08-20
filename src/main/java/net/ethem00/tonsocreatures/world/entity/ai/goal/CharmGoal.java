package net.ethem00.tonsocreatures.world.entity.ai.goal;

import net.ethem00.tonsocreatures.effect.CharmEffect;
import net.ethem00.tonsocreatures.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class CharmGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(12).ignoreLineOfSight();
    protected final PathfinderMob mainMob;
    protected final Level level;
    private final double speedModifier;
    protected LivingEntity partner;

    //Constructor
    public CharmGoal(PathfinderMob pMob, double pSpeedModifier) {
        this.mainMob = pMob;
        this.level = pMob.level();
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse()
    {
        if(!checkIfCharmed(mainMob))
        {
            return false;
        }
        else
        {
            this.partner = getFreePartner();
            return this.partner != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.partner.isAlive(); //&& this.partner.isInLove() && this.loveTime < 60 && !this.partner.isPanicking();
    }

    @Override
    public void stop() {
        this.partner = null;
        //this.loveTime = 0;
    }

    private boolean checkIfCharmed(LivingEntity checkedEntity)
    {
        Collection<MobEffectInstance> activeEffects = checkedEntity.getActiveEffects();
        for(MobEffectInstance effectInstance : activeEffects) {
            if (effectInstance.getEffect().get() == ModEffects.CHARM_EFFECT.get()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if(this.partner != null)
        {
            System.out.println("Partner is " + this.partner.getName());
        }
        else
        {
            System.out.println("Partner is NULL");
        }
        this.mainMob.getLookControl().setLookAt(this.partner, 10.0F, (float)this.mainMob.getMaxHeadXRot());
        this.mainMob.getNavigation().moveTo(this.partner, this.speedModifier);
        //this.loveTime++;
        //if (this.loveTime >= this.adjustedTickDelay(60) && this.mainMob.distanceToSqr(this.partner) < 9.0) {
        //    this.breed();
        //}

    }

    @Nullable
    private Mob getFreePartner() {
        List<? extends Mob> mobList = this.level.getNearbyEntities(Mob.class, PARTNER_TARGETING, this.mainMob, this.mainMob.getBoundingBox().inflate(12.0));
        double d0 = Double.MAX_VALUE;
        Mob chosenMob = null;

        for(Mob listedMob : mobList)
        {
            if(checkIfCharmed(listedMob) && checkIfCharmed(this.mainMob) && this.mainMob.distanceToSqr(listedMob) < d0)
            {
                chosenMob = listedMob;
                d0 = this.mainMob.distanceToSqr(listedMob);
            }
        }
        return chosenMob;
    }

    /*
    @Nullable
    private Animal getFreePartner() {
        List<? extends Animal> list = this.level.getNearbyEntities(this.livingEntity, PARTNER_TARGETING, this.livingEntity, this.livingEntity.getBoundingBox().inflate(8.0));
        double d0 = Double.MAX_VALUE;
        Animal animal = null;

        for (LivingEntity livingEntity1 : list) {
            checkIfCharmed(livingEntity1);

            if (checkIfCharmed(this.livingEntity) && checkIfCharmed(livingEntity1) && this.livingEntity.distanceToSqr(livingEntity1) < d0) {
                livingEntity = livingEntity1;
                d0 = this.livingEntity.distanceToSqr(livingEntity1);
            }
        }

        return animal;
    }
    */

}
