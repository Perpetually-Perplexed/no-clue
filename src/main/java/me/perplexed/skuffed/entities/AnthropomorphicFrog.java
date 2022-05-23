package me.perplexed.skuffed.entities;

import me.perplexed.skuffed.SkuffedMod;
import me.perplexed.skuffed.util.Holder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AnthropomorphicFrog extends MobEntity {

    @Nullable
    private GrandmaFrog.GrandmaFrogEntityData frog;

    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(AnthropomorphicFrog.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DANCING = DataTracker.registerData(AnthropomorphicFrog.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> DANCE_TIME = DataTracker.registerData(AnthropomorphicFrog.class, TrackedDataHandlerRegistry.INTEGER);
    public AnimationState dancing = new AnimationState();
    private final List<LivingEntity> futureTargets = new ArrayList<>();

    public double prevCapeX;
    public double prevCapeY;
    public double prevCapeZ;

    public double capeX;
    public double capeY;
    public double capeZ;

    public AnthropomorphicFrog(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAnthropomorphicFrogAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(SHOOTING, false);
        dataTracker.startTracking(DANCING, true);
        dataTracker.startTracking(DANCE_TIME, 300);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        dataTracker.set(SHOOTING, nbt.getBoolean("Shooting"));
        dataTracker.set(DANCING, nbt.getBoolean("Dancing"));
        dataTracker.set(DANCE_TIME, nbt.getInt("DanceTime"));
        super.readCustomDataFromNbt(nbt);
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("Shooting", dataTracker.get(SHOOTING));
        nbt.putBoolean("Dancing", dataTracker.get(DANCING));
        nbt.putInt("DanceTime", dataTracker.get(DANCE_TIME));
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
//        this.goalSelector.add(0,);
        this.goalSelector.add(1, new StareAtInitialTarget(this));

        this.targetSelector.add(1, new PickNextTarget(this));
    }



    public void setFrog(GrandmaFrog.GrandmaFrogEntityData frog) {
        if (this.frog == null)
            this.frog = frog;
    }

    @Override
    public void tick() {
        super.tick();

        updateCapeAngles();

        if (dataTracker.get(DANCING)) {
            dataTracker.set(DANCE_TIME, dataTracker.get(DANCE_TIME) - 1);
            if (dataTracker.get(DANCE_TIME) <= 0) {
                returnToFrog();
                return;
            }
        }

        if (dataTracker.get(SHOOTING)) {
            if (this.getTarget() == null && this.futureTargets.isEmpty()) {
                returnToFrog();
            }
        }
    }

    public void returnToFrog() {
        GrandmaFrog frog = Holder.GRANDMA_FROG_TYPE.create(world);
        frog.setPosition(this.getX(), this.getY(), this.getZ());
        frog.setYaw(this.getYaw());
        frog.setPitch(this.getPitch());
        frog.readData(this.frog);
        frog.setHealth(this.getHealth());

        world.spawnEntity(frog);

        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof LivingEntity attack)) return super.damage(source, amount);
        if (dataTracker.get(DANCING)) {
            this.setTarget(attack);
            this.dataTracker.set(DANCING,false);
            return super.damage(source, amount);
        }
        this.futureTargets.add(attack);
        return super.damage(source, amount);
    }

    public Identifier getCapeTexture() {
        //todo change to absolute
        if (MinecraftClient.getInstance().player == null) return null;
//        return MinecraftClient.getInstance().player.getCapeTexture();
        return new Identifier(SkuffedMod.ID,"textures/entity/frog/anthropomorphic_frog_cape");
    }

    private void updateCapeAngles() {
        this.prevCapeX = this.capeX;
        this.prevCapeY = this.capeY;
        this.prevCapeZ = this.capeZ;
        double d = this.getX() - this.capeX;
        double e = this.getY() - this.capeY;
        double f = this.getZ() - this.capeZ;
        if (d > 10.0D) {
            this.capeX = this.getX();
            this.prevCapeX = this.capeX;
        }

        if (f > 10.0D) {
            this.capeZ = this.getZ();
            this.prevCapeZ = this.capeZ;
        }

        if (e > 10.0D) {
            this.capeY = this.getY();
            this.prevCapeY = this.capeY;
        }

        if (d < -10.0D) {
            this.capeX = this.getX();
            this.prevCapeX = this.capeX;
        }

        if (f < -10.0D) {
            this.capeZ = this.getZ();
            this.prevCapeZ = this.capeZ;
        }

        if (e < -10.0D) {
            this.capeY = this.getY();
            this.prevCapeY = this.capeY;
        }

        this.capeX += d * 0.25D;
        this.capeZ += f * 0.25D;
        this.capeY += e * 0.25D;
    }

    private static class PickNextTarget extends TrackTargetGoal {

        private final AnthropomorphicFrog frog;

        public PickNextTarget(AnthropomorphicFrog frog) {
            super(frog, true);
            this.frog = frog;
        }

        @Override
        public boolean canStart() {
            return !frog.isRemoved() && !frog.dancing.isRunning() && frog.getTarget() == null && frog.futureTargets.size() != 0;
        }

        @Override
        public void start() {
            var next = frog.futureTargets.get(0);
            frog.setTarget(next);
            frog.futureTargets.remove(next);
        }

    }

    private static class StareAtInitialTarget extends Goal {

        private final AnthropomorphicFrog frog;
        private static final long maxStareTime = 300L;
        private long timeLeft = maxStareTime;

        public StareAtInitialTarget(AnthropomorphicFrog frog) {
            this.frog = frog;
        }

        @Override
        public boolean canStart() {
            return !frog.dataTracker.get(SHOOTING) && !frog.dataTracker.get(DANCING) && frog.getTarget() != null;
        }

        @Override
        public boolean shouldContinue() {
            return timeLeft != 0 && !frog.isRemoved() && frog.getTarget() != null;
        }

        @Override
        public void start() {
            --timeLeft;
//            frog.getLookControl().lookAt(this.frog.getTarget().getX(), this.frog.getTarget().getEyeY(), this.frog.getTarget().getZ());
            frog.getLookControl().lookAt(frog.getTarget());
        }

        @Override
        public void stop() {
            frog.dataTracker.set(SHOOTING, true);
        }
    }
}
