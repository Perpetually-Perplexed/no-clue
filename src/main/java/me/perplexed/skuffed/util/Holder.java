package me.perplexed.skuffed.util;

import me.perplexed.skuffed.block.PinkBlock;
import me.perplexed.skuffed.block.XrayBlock;
import me.perplexed.skuffed.entities.AnthropomorphicFrog;
import me.perplexed.skuffed.entities.GrandmaFrog;
import me.perplexed.skuffed.entities.Rakesh;
import me.perplexed.skuffed.entities.Rohit;
import me.perplexed.skuffed.item.MoneyMusicDisc;
import me.perplexed.skuffed.item.MushroomHelmet;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.perplexed.skuffed.SkuffedMod.ID;

@SuppressWarnings("unused")
public class Holder {
    public static final EntityType<Rohit> ROHIT_ENTITY_TYPE =registerEntityType("rohit", FabricEntityTypeBuilder.create(SpawnGroup.MISC, Rohit::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));

    public static final EntityType<Rakesh> RAKESH_ENTITY_TYPE = registerEntityType("rakesh",FabricEntityTypeBuilder.create(SpawnGroup.MISC, Rakesh::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));

    public static final EntityType<GrandmaFrog> GRANDMA_FROG_TYPE = registerEntityType("grandma_frog",FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, GrandmaFrog::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)));
    public static final EntityType<AnthropomorphicFrog> ANTROPOMORPHIC_FROG_TYPE = registerEntityType("anthropomorphic_frog",FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, AnthropomorphicFrog::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));

    public static final SoundEvent MONEY_MUSIC = registerSoundEvent("money_disc");

    public static final Block XRAY_BLOCK = registerBlock("xray_block", new XrayBlock());
    public static final Block PINK_BLOCK = registerBlock("pink_block",new PinkBlock());
    public static final Block CRUDE_MONEY_ORE = registerBlock("crude_money_ore",new Block(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)));

    public static final Item ROHIT_SPAWN_EGG = registerItem("rohit_spawn_egg",new SpawnEggItem(ROHIT_ENTITY_TYPE, 11011847, 2634644, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item RAKESH_SPAWN_EGG = registerItem("rakesh_spawn_egg", new SpawnEggItem(RAKESH_ENTITY_TYPE, 5389608, 12198434, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item GRANDMA_FROG_SPAWN_EGG = registerItem("grandma_frog_spawn_egg", new SpawnEggItem(GRANDMA_FROG_TYPE, 2774041, 15455173, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item MUSHROOM_HAT = registerItem("mushroom_helmet", new MushroomHelmet());
    public static final Item XRAY_BLOCK_ITEM = registerItem("xray_block",new BlockItem(XRAY_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    public static final Item CRUDE_MONEY = registerItem("crude_money",new Item(new Item.Settings().group(ItemGroup.MISC)));
    public static final Item MONEY = registerItem("money",new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(69)));
    public static final Item MONEY_MUSIC_DISC = registerItem("money_music_disc",new MoneyMusicDisc());
    public static final Item PINK_BLOCK_ITEM = registerItem("pink_block",new BlockItem(PINK_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item CRUDE_MONEY_ORE_ITEM = registerItem("crude_money_ore",new BlockItem(CRUDE_MONEY_ORE, new Item.Settings().group(ItemGroup.MISC)));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(ID, name), block);
    }

    private static <T extends Entity> EntityType<T> registerEntityType(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE,new Identifier(ID,name),builder.build());
    }

    private static SoundEvent registerSoundEvent(String name) {
        var id = new Identifier(ID,name);
        return Registry.register(Registry.SOUND_EVENT,id,new SoundEvent(id));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ID, name), item);
    }
}
