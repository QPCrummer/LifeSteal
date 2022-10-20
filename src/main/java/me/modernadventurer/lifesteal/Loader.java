package me.modernadventurer.lifesteal;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import me.modernadventurer.lifesteal.block.ModBlocks;
import me.modernadventurer.lifesteal.item.ModItems;
import me.modernadventurer.lifesteal.world.features.ModConfiguredFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.fabricmc.fabric.mixin.event.lifecycle.MinecraftServerMixin;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;
import net.fabricmc.tinyremapper.extension.mixin.common.Logger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.storage.LevelStorage;

import java.util.Objects;

/**
 * Copyright 2021 BradBot_1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//NOTICE: This file was modified to remove all configuration setup and instead establish gamerules.

public class Loader implements ModInitializer, ServerLifecycleEvents.ServerStarting {

	public static final String MOD_ID = "lifesteal";

	public static final GameRules.Key<GameRules.BooleanRule> PLAYERRELATEDONLY =
			GameRuleRegistry.register(MOD_ID + ":playerKillOnly", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanRule> BANWHENMINHEALTH =
			GameRuleRegistry.register(MOD_ID + ":banWhenMinHealth", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.IntRule> STEALAMOUNT =
			GameRuleRegistry.register(MOD_ID + ":stealAmount", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(2));

	public static final GameRules.Key<GameRules.IntRule> MINPLAYERHEALTH =
			GameRuleRegistry.register(MOD_ID + ":minPlayerHealth", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(1));

	public static final GameRules.Key<GameRules.IntRule> MAXPLAYERHEALTH =
			GameRuleRegistry.register(MOD_ID + ":maxPlayerHealth", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(40));

	public static final GameRules.Key<GameRules.IntRule> HEARTBONUS =
			GameRuleRegistry.register(MOD_ID + ":healthPerUse", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(2));

	public static final GameRules.Key<GameRules.IntRule> VEINSIZE =
			GameRuleRegistry.register(MOD_ID + ":veinSize", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(1));

	public static final GameRules.Key<GameRules.IntRule> VEINPERCHUNK =
			GameRuleRegistry.register(MOD_ID + ":veinsPerChunk", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(1));

	public static int vein_size;
	public static int veins_per_chunk;

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.registerBlocks();
		ModConfiguredFeatures.registerOres();
		PolymerRPUtils.addAssetSource(MOD_ID);
	}

	@Override
	public void onServerStarting(MinecraftServer server) {
		vein_size = Objects.requireNonNull(server.getWorld(server.getOverworld().getRegistryKey())).getGameRules().getInt(VEINSIZE);
		veins_per_chunk = Objects.requireNonNull(server.getWorld(server.getOverworld().getRegistryKey())).getGameRules().getInt(VEINPERCHUNK);
	}
}
