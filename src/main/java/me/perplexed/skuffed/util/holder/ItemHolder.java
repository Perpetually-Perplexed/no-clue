package me.perplexed.skuffed.util.holder;

import me.perplexed.skuffed.item.MoneyMusicDisc;
import me.perplexed.skuffed.item.MushroomHelmet;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.perplexed.skuffed.SkuffedMod.ID;

@SuppressWarnings("unused")
public class ItemHolder {

    //80 char limit lol
    public static final Item ROHIT_SPAWN_EGG = register("rohit_spawn_egg",new SpawnEggItem(EntityTypeHolder.ROHIT_ENTITY_TYPE, 11011847, 2634644, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item RAKESH_SPAWN_EGG = register("rakesh_spawn_egg", new SpawnEggItem(EntityTypeHolder.RAKESH_ENTITY_TYPE, 5389608, 12198434, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item GRANDMA_FROG_SPAWN_EGG = register("grandma_frog_spawn_egg", new SpawnEggItem(EntityTypeHolder.GRANDMA_FROG_TYPE, 2774041, 15455173, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item MUSHROOM_HAT = register("mushroom_helmet", new MushroomHelmet());
    public static final Item XRAY_BLOCK_ITEM = register("xray_block",new BlockItem(BlockHolder.XRAY_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    public static final Item CRUDE_MONEY = register("crude_money",new Item(new Item.Settings().group(ItemGroup.MISC)));
    public static final Item MONEY = register("money",new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(69)));
    public static final Item MONEY_MUSIC_DISC = register("money_music_disc",new MoneyMusicDisc());
    public static final Item PINK_BLOCK_ITEM = register("pink_block",new BlockItem(BlockHolder.PINK_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item CRUDE_MONEY_ORE_ITEM = register("crude_money_ore",new BlockItem(BlockHolder.CRUDE_MONEY_ORE, new Item.Settings().group(ItemGroup.MISC)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ID, name), item);
    }

    //class call for java to load, most other stuff will load after this
    public static void load(){}
}
