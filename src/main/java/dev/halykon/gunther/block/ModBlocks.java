package dev.halykon.gunther.block;

import dev.halykon.gunther.Gunther;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block TOPAZ_ORE = registerBlock("topaz_block",
            new Block(FabricBlockSettings
                    .of(Material.METAL)
                    .strength(3.0f)
                    .breakByTool(FabricToolTags.PICKAXES, 2)
                    .requiresTool()
            )
    );

    public static final Block RUBY_ORE = registerBlock("ruby_block",
            new Block(FabricBlockSettings
                    .of(Material.METAL)
                    .strength(3.0f)
                    .breakByTool(FabricToolTags.PICKAXES, 2)
                    .requiresTool()
            )
    );

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(Gunther.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Gunther.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(ItemGroup.MISC)));
    }

    public static void registerModBLocks() {

    }
}
