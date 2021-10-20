package dev.halykon.gunther;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gunther implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("gunther");

	// an instance of our new item
	// public static final RainbowSword FABRIC_ITEM = new RainbowSword(new FabricItemSettings().group(ItemGroup.MISC));

	public static ToolItem RAINBOW_SWORD = new SwordItem(RainbowToolMaterial.INSTANCE, 5, -2.0F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem NETHER_SWORD = new SwordItem(RainbowToolMaterial.INSTANCE, 100, -2.0F, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Block TOPAZ_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier("gunther", "rainbow_sword"), RAINBOW_SWORD);
		Registry.register(Registry.ITEM, new Identifier("gunther", "nether_sword"), NETHER_SWORD);
		Registry.register(Registry.BLOCK, new Identifier("gunther", "topaz_block"), TOPAZ_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("gunther", "topaz_block"), new BlockItem(TOPAZ_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

		LOGGER.info("Hello Fabric world!");
	}
}
