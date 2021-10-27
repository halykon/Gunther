package dev.halykon.gunther.block;

import dev.halykon.gunther.Gunther;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

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
        System.out.println("------------------------------- POS:");
        System.out.println(pos);
        if (world.getBlockState(pos).get(ACTIVATED)) {
            System.out.println("active");
        }
        world.setBlockState(pos, state.with(ACTIVATED, true));
        System.out.println("-------------------------------");

        int current_x = pos.getX();
        int current_y = pos.getY();
        int current_z = pos.getZ();

        if (world.getBlockState(pos).get(Properties.HORIZONTAL_FACING) == Direction.EAST) {
            for (int i = 1; i <= 11; i++) {
                int check_x = current_x + i;
                BlockPos x = new BlockPos(check_x, current_y, current_z);
                System.out.println(world.getBlockState(x).getBlock());

                Block new_block = world.getBlockState(x).getBlock();
                Block current_block = world.getBlockState(pos).getBlock();

                if (new_block == current_block) {
                    System.out.println("is same type ja");

                    Boolean new_block_state = world.getBlockState(x).get(ACTIVATED);
                    System.out.println(new_block_state);

                    Direction direction = world.getBlockState(pos).get(Properties.HORIZONTAL_FACING);
                    if (new_block_state) {
                        System.out.println("is activate ja");
                    } else {
                        System.out.println("is deactivate sadge");
                    }
                }
            }
        }

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
