package me.perplexed.skuffed.entities.renderer;

import me.perplexed.skuffed.SkuffedMod;
import me.perplexed.skuffed.entities.Rakesh;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class RakeshRenderer extends BipedEntityRenderer<Rakesh, PlayerEntityModel<Rakesh>> {


    public RakeshRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER_SLIM),true), 0.6f);
    }

    @Override
    public Identifier getTexture(Rakesh entity) {
        return new Identifier(SkuffedMod.ID,
                entity.getDataTracker().get(Rakesh.FEMALE) ? "textures/entity/rakesh_woman.png" : "textures/entity/rakesh.png");
    }
}
