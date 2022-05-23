package me.perplexed.skuffed.entities.renderer;

import me.perplexed.skuffed.SkuffedMod;
import me.perplexed.skuffed.entities.AnthropomorphicFrog;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class AnthropomorphicFrogRenderer extends BipedEntityRenderer<AnthropomorphicFrog, PlayerEntityModel<AnthropomorphicFrog>>
    implements FeatureRendererContext<AnthropomorphicFrog,PlayerEntityModel<AnthropomorphicFrog>> {

    public AnthropomorphicFrogRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PlayerEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER_SLIM),true), 0.5F);
        this.addFeature(new CapeFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(AnthropomorphicFrog mobEntity) {
        return new Identifier(SkuffedMod.ID,"textures/entity/frog/anthropomorphic_frog.png");
    }
}
