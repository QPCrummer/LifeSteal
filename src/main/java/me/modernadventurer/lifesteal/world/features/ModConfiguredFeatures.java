package me.modernadventurer.lifesteal.world.features;

import me.modernadventurer.lifesteal.Loader;
import me.modernadventurer.lifesteal.block.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

import static me.modernadventurer.lifesteal.Loader.vein_size;
import static me.modernadventurer.lifesteal.Loader.veins_per_chunk;

public class ModConfiguredFeatures {
    private static final ConfiguredFeature<?, ?> HEART_DUST_ORE_CONFIGURED_FEATURE = new ConfiguredFeature<>
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.HEARTDUSTORE.getDefaultState(), vein_size)); // vein size

    public static PlacedFeature HEART_DUST_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(HEART_DUST_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(veins_per_chunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(50))
            )); // height

    public static void registerOres() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Loader.MOD_ID, "heart_ore"), HEART_DUST_ORE_CONFIGURED_FEATURE);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Loader.MOD_ID, "heart_ore"),
                HEART_DUST_ORE_PLACED_FEATURE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Loader.MOD_ID, "heart_ore")));
    }
}
