package me.perplexed.skuffed.util.holder;

import me.perplexed.skuffed.entities.AnthropomorphicFrog;
import me.perplexed.skuffed.entities.GrandmaFrog;
import me.perplexed.skuffed.entities.Rakesh;
import me.perplexed.skuffed.entities.Rohit;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.perplexed.skuffed.SkuffedMod.ID;


public class EntityTypeHolder {

    public static final EntityType<Rohit> ROHIT_ENTITY_TYPE = register("rohit", FabricEntityTypeBuilder.create(SpawnGroup.MISC, Rohit::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));

    public static final EntityType<Rakesh> RAKESH_ENTITY_TYPE = register("rakesh",FabricEntityTypeBuilder.create(SpawnGroup.MISC, Rakesh::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));

    public static final EntityType<GrandmaFrog> GRANDMA_FROG_TYPE = register("grandma_frog",FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, GrandmaFrog::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)));
    public static final EntityType<AnthropomorphicFrog> ANTROPOMORPHIC_FROG_TYPE = register("anthropomorphic_frog",FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, AnthropomorphicFrog::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)));


    private static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE,new Identifier(ID,name),builder.build());
    }
}
