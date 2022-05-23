package me.perplexed.skuffed.item;

import me.perplexed.skuffed.util.holder.SoundHolder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;

public class MoneyMusicDisc extends MusicDiscItem {

    public MoneyMusicDisc() {
        this(new Item.Settings().maxCount(1).rarity(Rarity.RARE).group(ItemGroup.MISC));
    }

    public MoneyMusicDisc(Settings settings) {
        super(7, SoundHolder.MONEY_MUSIC,settings);
    }
}
