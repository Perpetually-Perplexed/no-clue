package me.perplexed.skuffed.client;

import me.perplexed.skuffed.entities.AnthropomorphicFrog;
import me.perplexed.skuffed.entities.GrandmaFrog;
import me.perplexed.skuffed.entities.Rakesh;
import me.perplexed.skuffed.entities.Rohit;
import me.perplexed.skuffed.entities.renderer.AnthropomorphicFrogRenderer;
import me.perplexed.skuffed.entities.renderer.GrandmaFrogRenderer;
import me.perplexed.skuffed.entities.renderer.RakeshRenderer;
import me.perplexed.skuffed.entities.renderer.RohitRenderer;
import me.perplexed.skuffed.util.Holder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

@Environment(EnvType.CLIENT)
public class SkuffedClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        FabricDefaultAttributeRegistry.register(Holder.ROHIT_ENTITY_TYPE, Rohit.createRohitAttributes());
        FabricDefaultAttributeRegistry.register(Holder.RAKESH_ENTITY_TYPE, Rakesh.createRakeshAttributes());
        FabricDefaultAttributeRegistry.register(Holder.GRANDMA_FROG_TYPE, GrandmaFrog.createFrogAttributes());
        FabricDefaultAttributeRegistry.register(Holder.ANTROPOMORPHIC_FROG_TYPE, AnthropomorphicFrog.createAnthropomorphicFrogAttributes());

        EntityRendererRegistry.register(Holder.ROHIT_ENTITY_TYPE, RohitRenderer::new);
        EntityRendererRegistry.register(Holder.RAKESH_ENTITY_TYPE, RakeshRenderer::new);
        EntityRendererRegistry.register(Holder.GRANDMA_FROG_TYPE, GrandmaFrogRenderer::new);
        EntityRendererRegistry.register(Holder.ANTROPOMORPHIC_FROG_TYPE, AnthropomorphicFrogRenderer::new);
    }
}
