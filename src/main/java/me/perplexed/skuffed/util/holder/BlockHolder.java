package me.perplexed.skuffed.util.holder;

import me.perplexed.skuffed.block.PinkBlock;
import me.perplexed.skuffed.block.XrayBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.perplexed.skuffed.SkuffedMod.ID;

public class BlockHolder {

    public static final Block XRAY_BLOCK = register("xray_block", new XrayBlock());
    public static final Block PINK_BLOCK = register("pink_block",new PinkBlock());
    public static final Block CRUDE_MONEY_ORE = register("crude_money_ore",new Block(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)));

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(ID, name), block);
    }
}
