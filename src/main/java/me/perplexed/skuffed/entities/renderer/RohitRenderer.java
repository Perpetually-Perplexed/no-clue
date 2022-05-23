package me.perplexed.skuffed.entities.renderer;

import me.perplexed.skuffed.SkuffedMod;
import me.perplexed.skuffed.entities.Rohit;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class RohitRenderer extends BipedEntityRenderer<Rohit, PlayerEntityModel<Rohit>> {

    //todo fix held item render
    public RohitRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER_SLIM),true), 0.6f);
    }

    @Override
    public Identifier getTexture(Rohit entity) {
        return new Identifier(SkuffedMod.ID, "textures/entity/rohit.png");
    }
}
