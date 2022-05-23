package me.perplexed.skuffed.item;

import me.perplexed.skuffed.armor.MushroomHatMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MushroomHelmet extends ArmorItem {

    public MushroomHelmet() {
        this(new Item.Settings().group(ItemGroup.COMBAT).food(
                new FoodComponent.Builder().hunger(7).saturationModifier(0.6f).build()
        ));
    }

    public MushroomHelmet(Settings settings) {
        super(new MushroomHatMaterial(), EquipmentSlot.HEAD, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking())
            return super.use(world, user, hand);
        var helmet = user.getStackInHand(hand);
        if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(helmet);
        }

        return TypedActionResult.fail(helmet);
    }

    @Override
    public void postProcessNbt(NbtCompound nbt) {
        super.postProcessNbt(nbt);
    }
}
