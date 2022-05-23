package me.perplexed.skuffed.entities;

import me.perplexed.skuffed.util.holder.EntityTypeHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Rakesh extends AnimalEntity {


    public static final TrackedData<Boolean> FEMALE = DataTracker.registerData(Rakesh.class,TrackedDataHandlerRegistry.BOOLEAN);

    public Rakesh(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createRakeshAttributes() {
        return new DefaultAttributeContainer.Builder().add(EntityAttributes.GENERIC_FOLLOW_RANGE,32)
                .add(EntityAttributes.GENERIC_MAX_HEALTH,12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.512312)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,2)
                .add(EntityAttributes.GENERIC_ARMOR,1)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,2)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0.3)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.2);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(FEMALE,this.random.nextBoolean());
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this,Rohit.class,10.0F,0.512313D,0.512318D));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new AttackGoal(this));
        this.goalSelector.add(4, new LookAtEntityGoal(this,SilverfishEntity.class,4));
        this.goalSelector.add(5, new AnimalMateGoal(this,1.0D));
        this.goalSelector.add(6, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.NAUTILUS_SHELL), false));
        if (dataTracker.get(FEMALE))
            this.goalSelector.add(7,new NagatoroBeingUn(this));


        this.targetSelector.add(0,new ActiveTargetGoal<>(this,SilverfishEntity.class,true));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("female",dataTracker.get(FEMALE));
        return super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        dataTracker.set(FEMALE,nbt.getBoolean("female"));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new Rakesh(EntityTypeHolder.RAKESH_ENTITY_TYPE,world);
    }


    private static class NagatoroBeingUn extends Goal{

        private final Rakesh nagatoro;
        private static final String[] INSULTS = {"<Nagatoro #%s> Are you hard?","<Nagatoro #%s> SENPAI! You shouldn't be doing those things around first years like me! :)"};

        public NagatoroBeingUn(Rakesh nagatoro) {
            if (!nagatoro.dataTracker.get(FEMALE)) {
                nagatoro.dataTracker.set(FEMALE,true);
            }
            this.nagatoro = nagatoro;
        }


        @Override
        public boolean canStart() {
            return nagatoro.random.nextFloat() > 0.9991231231231232131;
        }


        @Override
        public void start() {
            nagatoro.getCommandSource().getServer().getCommandManager().execute(nagatoro.getCommandSource(),
                    String.format("tellraw @a \"%s\"",
                            String.format(INSULTS[nagatoro.random.nextInt(INSULTS.length-1)],nagatoro.getId())));
        }
    }
}
