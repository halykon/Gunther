package net.fabricmc.example;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class NetherToolMaterial implements ToolMaterial {

    public static final net.fabricmc.example.NetherToolMaterial INSTANCE = new net.fabricmc.example.NetherToolMaterial();

    @Override
    public int getDurability() {
        return 500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 50.0F;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHERRACK);
    }
}