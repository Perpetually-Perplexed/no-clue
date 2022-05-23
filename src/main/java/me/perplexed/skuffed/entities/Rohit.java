package me.perplexed.skuffed.entities;

import me.perplexed.skuffed.util.holder.EntityTypeHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Rohit extends PassiveEntity {


    public Rohit(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createRohitAttributes() {
        return new DefaultAttributeContainer.Builder().add(EntityAttributes.GENERIC_FOLLOW_RANGE,32)
                .add(EntityAttributes.GENERIC_MAX_HEALTH,20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.512319)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,2)
                .add(EntityAttributes.GENERIC_ARMOR,2)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,2)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,1)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,1);
    }



    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new Rohit(EntityTypeHolder.ROHIT_ENTITY_TYPE, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() == null) {
            if (this.hasStackEquipped(EquipmentSlot.MAINHAND)) {
                this.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
            }
            return;
        }

        if (!this.hasStackEquipped(EquipmentSlot.MAINHAND)) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
        }
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0,new SwimGoal(this));
        goalSelector.add(1,new WanderAroundGoal(this,1.0D));
        goalSelector.add(2,new AttackGoal(this));
        goalSelector.add(3,new LookAtEntityGoal(this, Rakesh.class,8.0F));
        goalSelector.add(4,new LookAroundGoal(this));

        targetSelector.add(0, new ActiveTargetGoal<>(this, Rakesh.class, true));
    }


}
