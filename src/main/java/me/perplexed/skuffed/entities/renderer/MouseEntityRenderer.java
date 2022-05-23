package me.perplexed.skuffed.entities.renderer;

import me.perplexed.skuffed.entities.MouseEntity;
import me.perplexed.skuffed.entities.model.MouseModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class MouseEntityRenderer extends MobEntityRenderer<MouseEntity, MouseModel> {

    public MouseEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MouseModel(context.getPart(EntityModelLayers.SILVERFISH)), 0.1f);
    }

    @Override
    public Identifier getTexture(MouseEntity entity) {
        return null;
    }
}
