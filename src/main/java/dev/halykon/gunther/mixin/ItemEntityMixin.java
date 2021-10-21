package dev.halykon.gunther.mixin;

import dev.halykon.gunther.Gunther;
import dev.halykon.gunther.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        World WORLD = getEntityWorld();

        Item ITEM = getStack().getItem();
        Vec3d POS = getPos();

        if(ITEM == Items.DIAMOND_SWORD) {
            List<Item> REQUIRED_ITEMS = Arrays.asList(Items.AMETHYST_SHARD, Items.DIAMOND_SWORD, Items.EMERALD, ModItems.TOPAZ, ModItems.RUBY);
//            Gunther.LOGGER.info(String.format("Hey, i am a %s at %s, %s, %s", ITEM, POS.getX(), POS.getY(), POS.getZ()));
            List<ItemEntity> ITEMS = this.world.getEntitiesByClass(ItemEntity.class, this.getBoundingBox().expand(0.5D, 0.0D, 0.5D), (itemEntityx) -> {
                return REQUIRED_ITEMS.contains(itemEntityx.getStack().getItem());
            });

            if(ITEMS.parallelStream().map(itemEntity -> itemEntity.getStack().getItem()).toList().containsAll(REQUIRED_ITEMS)){
                ITEMS.forEach(ItemEntity::setDespawnImmediately);

                world.spawnEntity(new ItemEntity(world, POS.getX(), POS.getY(), POS.getZ(), new ItemStack(ModItems.RAINBOW_SWORD)));
            }
        }
    }
}
