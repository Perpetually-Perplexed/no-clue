package me.perplexed.skuffed.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class MushroomHatMaterial implements ArmorMaterial {

    public static final int[] BASE_DURA = {232,1,1,1};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURA[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return 42;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.BROWN_MUSHROOM,Items.RED_MUSHROOM);
    }

    @Override
    public String getName() {
        return "mushroom";
    }

    @Override
    public float getToughness() {
        return 3;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.25f;
    }

}
