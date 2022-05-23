package me.perplexed.skuffed.entities.renderer;

import me.perplexed.skuffed.entities.GrandmaFrog;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.util.Identifier;

public class GrandmaFrogRenderer extends MobEntityRenderer<GrandmaFrog, FrogEntityModel<GrandmaFrog>> {

    public GrandmaFrogRenderer(EntityRendererFactory.Context context) {
        super(context, new FrogEntityModel<>(context.getPart(EntityModelLayers.FROG)), 0.6f);
    }

    @Override
    public Identifier getTexture(GrandmaFrog entity) {
        return FrogVariant.COLD.texture();//new Identifier(SkuffedMod.ID, "textures/entity/grandma_frog.png");
    }
}
