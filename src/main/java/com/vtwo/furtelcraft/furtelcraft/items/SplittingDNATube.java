package com.vtwo.furtelcraft.furtelcraft.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SplittingDNATube extends Item {
    public SplittingDNATube(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()){
            NbtCompound nbt = stack.getNbt();
            assert nbt != null;
            String nbts = nbt.getString("Sequence");
            tooltip.add(new TranslatableText("item.furtelcraft.tooltip.sequence").append(nbts).formatted(Formatting.DARK_PURPLE));
        }
    }
}
