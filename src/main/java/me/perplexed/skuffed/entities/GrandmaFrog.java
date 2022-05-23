package me.perplexed.skuffed.entities;

import me.perplexed.skuffed.util.Holder;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GrandmaFrog extends FrogEntity {

    private static final TrackedData<Integer> LIVES = DataTracker.registerData(GrandmaFrog.class, TrackedDataHandlerRegistry.INTEGER);
    private static final double ANTHRO_CHANCE = 10^-3;

    public GrandmaFrog(EntityType<? extends FrogEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {

    }

    public void readData(GrandmaFrogEntityData data) {
        if (data == null) return;
        dataTracker.set(LIVES, data.lives);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(LIVES, 9);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("lives", dataTracker.get(LIVES));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound   nbt) {
        super.readCustomDataFromNbt(nbt);
        dataTracker.set(LIVES, dataTracker.get(LIVES));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getStackInHand(Hand.MAIN_HAND).isEmpty() && dataTracker.get(LIVES) != 1) {
            this.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.TOTEM_OF_UNDYING));
        }

        if (this.random.nextFloat() <= ANTHRO_CHANCE) {
            AnthropomorphicFrog antro = Holder.ANTROPOMORPHIC_FROG_TYPE.create(this.world);
            antro.setPosition(this.getX(), this.getY(), this.getZ());
            antro.setYaw(this.getYaw());
            antro.setPitch(this.getPitch());
            antro.setHealth(this.getHealth());
            antro.setFrog(new GrandmaFrogEntityData(this));

            this.world.spawnEntity(antro);
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        FrogEntity f = new FrogEntity(EntityType.FROG, world);
        f.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(2.5D);
        return f;
    }

    static class GrandmaFrogEntityData implements EntityData {
        public final int lives;

        public GrandmaFrogEntityData(GrandmaFrog frog) {
            this.lives = frog.dataTracker.get(LIVES);
        }
    }
}
