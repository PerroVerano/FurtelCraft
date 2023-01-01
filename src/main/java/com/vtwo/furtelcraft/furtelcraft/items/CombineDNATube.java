package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.utils.ProgressBar;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CombineDNATube extends Item {
    public CombineDNATube(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()){
            NbtCompound nbt = stack.getNbt();
            assert nbt != null;
            String nbts = nbt.getString("Sequence");
            tooltip.add(Text.translatable("item.furtelcraft.tooltip.sequence").append(nbts).formatted(Formatting.DARK_PURPLE));
            int time = nbt.getInt("time");
            if (time != 0) {
                tooltip.add(Text.translatable("item.furtelcraft.time").formatted(Formatting.GRAY).append(ProgressBar.ProgressBarTooltip(time,20 * 10)));
            }
        }
    }
}
