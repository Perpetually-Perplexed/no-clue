package me.perplexed.skuffed.util.holder;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

public class FeatureHolder {

    public static final ConfiguredFeature<?,?> CRUDE_MONEY_ORE_CONFIGURED = new ConfiguredFeature<>(Feature.ORE,
            new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,BlockHolder.CRUDE_MONEY_ORE.getDefaultState(),9)
    );

    public static final PlacedFeature CRUDE_MONEY_ORE = new PlacedFeature(RegistryEntry.of(CRUDE_MONEY_ORE_CONFIGURED),
            Arrays.asList(CountPlacementModifier.of(3), SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(64),YOffset.TOP)));
}
