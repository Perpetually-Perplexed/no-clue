package me.perplexed.skuffed.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class XrayBlock extends Block {

    public static final BooleanProperty XRAY = BooleanProperty.of("xray");

    public XrayBlock() {
        this(FabricBlockSettings.copyOf(Blocks.STONE));
    }

    public XrayBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(XRAY, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(XRAY);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        state = state.with(XRAY, !state.get(XRAY));
        world.setBlockState(pos, state);

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return state.get(XRAY) ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
    }
}
