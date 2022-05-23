package me.perplexed.skuffed.block;

import me.perplexed.skuffed.util.holder.ItemHolder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class PinkBlock extends Block {

    public static final IntProperty FILLED_STATE = IntProperty.of("filled",0,3);

    public PinkBlock() {
        this(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE).requiresTool());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILLED_STATE);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    public PinkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(FILLED_STATE,0).with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (world.isClient ||state.get(FILLED_STATE) == 0) return;

        ItemStack stackToSpawn =switch (state.get(FILLED_STATE)) {
            case 1 -> new ItemStack(Items.GOLD_INGOT,1);
            case 2 -> new ItemStack(ItemHolder.MONEY,4);
            case 3-> new ItemStack(ItemHolder.MONEY_MUSIC_DISC,1);
            default -> ItemStack.EMPTY;
        };

        ItemEntity toSpawn = new ItemEntity(EntityType.ITEM,world);
        toSpawn.setStack(stackToSpawn);
        toSpawn.setPosition(Vec3d.ofCenter(pos));
        toSpawn.setVelocity(new Vec3d(world.random.nextDouble()*0.2D-0.1D,0.2D,world.random.nextDouble()*0.2D-0.1D));
        world.spawnEntity(toSpawn);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(Properties.HORIZONTAL_FACING,ctx.getPlayerFacing());
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;
        if (hand != Hand.MAIN_HAND) return ActionResult.PASS;
        var stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.GOLD_INGOT) && !stack.isOf(ItemHolder.MONEY) && !stack.isOf(ItemHolder.CRUDE_MONEY)) return ActionResult.PASS;

        if (stack.isOf(ItemHolder.CRUDE_MONEY)) {
            int process = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(),GLFW.GLFW_KEY_LEFT_SHIFT) ? stack.getCount() : 1;
            stack.decrement(process);
            world.playSound(null,pos,SoundEvents.UI_STONECUTTER_TAKE_RESULT,SoundCategory.BLOCKS,1.0F,1.0F);
            var toInsert = new ItemStack(ItemHolder.MONEY,process);

            if (!player.getInventory().insertStack(toInsert)) {
                ItemEntity toSpawn = new ItemEntity(EntityType.ITEM,world);
                toSpawn.setStack(toInsert);
                toSpawn.setPosition(player.getPos());
                toSpawn.setVelocity(new Vec3d(world.random.nextDouble()*0.2D-0.1D,0.2D,world.random.nextDouble()*0.2D-0.1D));
                world.spawnEntity(toSpawn);
            }

            return ActionResult.CONSUME;
        }

        if (state.get(FILLED_STATE) == 3) return ActionResult.PASS;

        if (state.get(FILLED_STATE) == 0) {

            if (stack.isOf(Items.GOLD_INGOT)) {
                if (!player.isCreative()) stack.decrement(1);
                world.setBlockState(pos,state.with(FILLED_STATE,1));
                return ActionResult.CONSUME;
            }

            if (stack.getCount() < 4) return ActionResult.PASS;
            if (!player.isCreative()) stack.decrement(4);
            world.setBlockState(pos,state.with(FILLED_STATE,2));
            return ActionResult.CONSUME;
        }

        if ((stack.isOf(Items.GOLD_INGOT) && state.get(FILLED_STATE) == 1) || (stack.isOf(ItemHolder.MONEY) && state.get(FILLED_STATE) == 2))
            return ActionResult.PASS;
        world.setBlockState(pos,state.with(FILLED_STATE,3));
        return ActionResult.CONSUME;
    }

}
