package me.modernadventurer.lifesteal.block;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import static me.modernadventurer.lifesteal.Loader.MOD_ID;

public class DeepslateHeartOreItem extends BlockItem implements PolymerItem {

    private final PolymerModelData polymerModel;

    public DeepslateHeartOreItem(Settings settings, Block block, String modelId) {
        super(block, settings);
        this.polymerModel = PolymerRPUtils.requestModel(Items.BARRIER, new Identifier(MOD_ID, modelId));

    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.value();
    }
}