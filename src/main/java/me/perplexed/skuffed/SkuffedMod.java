package me.perplexed.skuffed;

import me.perplexed.skuffed.util.holder.FeatureHolder;
import me.perplexed.skuffed.util.holder.ItemHolder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;

public class SkuffedMod implements ModInitializer {

    public static final String ID = "skuffed";

    @Override
    public void onInitialize() {
        Identifier cmoId = new Identifier(ID, "crude_money_ore");
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, cmoId, FeatureHolder.CRUDE_MONEY_ORE_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, cmoId, FeatureHolder.CRUDE_MONEY_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,cmoId));

        ItemHolder.load();
    }
}
