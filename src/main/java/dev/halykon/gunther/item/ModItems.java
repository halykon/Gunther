package dev.halykon.gunther.item;

import dev.halykon.gunther.Gunther;
import dev.halykon.gunther.item.material.RainbowToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item RAINBOW_SWORD = registerItem("rainbow_sword", new SwordItem(RainbowToolMaterial.INSTANCE, 5, -2.0F, new Item.Settings().group(ItemGroup.COMBAT)));
    public static final Item NETHER_SWORD = registerItem("nether_sword", new SwordItem(RainbowToolMaterial.INSTANCE, 100, -2.0F, new Item.Settings().group(ItemGroup.COMBAT)));
    public static final Item TOPAZ = registerItem("topaz", new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Gunther.MOD_ID, name), item);
    }

    public static void registerModItems() {

    }
}
