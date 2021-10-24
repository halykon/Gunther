package dev.halykon.gunther.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LaserBlock extends HorizontalFacingBlock {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    public LaserBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(ACTIVATED, false)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ACTIVATED);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);
        if (world.getBlockState(pos).get(ACTIVATED)) {
            System.out.println("active");
        }
        world.setBlockState(pos, state.with(ACTIVATED, true));
        System.out.println("-------------------------------");

        if (world.getBlockState(pos).get(Properties.HORIZONTAL_FACING) == Direction.NORTH) {
            System.out.println(Direction.NORTH);
        } else if (world.getBlockState(pos).get(Properties.HORIZONTAL_FACING) == Direction.SOUTH) {
            System.out.println(Direction.SOUTH);
        } else if (world.getBlockState(pos).get(Properties.HORIZONTAL_FACING) == Direction.EAST) {
            System.out.println(Direction.EAST);
        } else if (world.getBlockState(pos).get(Properties.HORIZONTAL_FACING) == Direction.WEST) {
            System.out.println(Direction.WEST);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (world.getBlockState(pos).get(ACTIVATED)){
            //Summoning the Lighting Bolt at the block
            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(lightningEntity);
        }

        world.setBlockState(pos, state.with(ACTIVATED, false));
        super.onSteppedOn(world, pos, state, entity);
    }
}
