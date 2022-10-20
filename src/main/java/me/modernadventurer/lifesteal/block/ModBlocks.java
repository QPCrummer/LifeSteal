package me.modernadventurer.lifesteal.block;

import eu.pb4.polymer.api.item.PolymerBlockItem;
import eu.pb4.polymer.ext.blocks.api.BlockModelType;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.ext.blocks.api.PolymerTexturedBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.modernadventurer.lifesteal.Loader.MOD_ID;

public class ModBlocks extends Block implements PolymerTexturedBlock {

    public static final Identifier IDENTIFIER = new Identifier(MOD_ID, "heart_ore");
    public static final Block HEARTDUSTORE = new ModBlocks(FabricBlockSettings
            .of(Material.STONE)
            .requiresTool()
            .strength(6.0f, 6.0f)
            .sounds(BlockSoundGroup.STONE));

    public static final BlockItem HEARTDUSTOREITEM = new PolymerBlockItem(HEARTDUSTORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS), Items.REDSTONE_ORE);

    public PolymerBlockModel blockModel = new PolymerBlockModel(new Identifier("heart_ore","block/heart_ore"),1,1,false,1);

    public BlockState resourceUtils = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, blockModel);

    public ModBlocks(Settings settings) {
        super(settings);
    }

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, IDENTIFIER, HEARTDUSTORE);
        Registry.register(Registry.ITEM, IDENTIFIER, HEARTDUSTOREITEM);
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return resourceUtils.getBlock();
    }

    @Override
    public Block getPolymerBlock(ServerPlayerEntity player, BlockState state) {
        return resourceUtils.getBlock();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return resourceUtils;
    }
}
