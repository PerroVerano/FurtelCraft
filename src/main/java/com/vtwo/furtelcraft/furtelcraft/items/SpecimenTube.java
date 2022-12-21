package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.utils.ProgressBar;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.stream.Stream;

public class SpecimenTube extends Item {
    public static boolean isSci = false;
    public SpecimenTube(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        return new ItemStack(this);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()){
            NbtCompound nbt = stack.getNbt();
            assert nbt != null;
            String nbts = nbt.getString("Sequence");
            tooltip.add(new TranslatableText("item.furtelcraft.tooltip.sequence").append(nbts).formatted(Formatting.DARK_PURPLE));
            int time = nbt.getInt("time");
            if (time != 0) {
                tooltip.add(new TranslatableText("item.furtelcraft.time").formatted(Formatting.GRAY).append(ProgressBar.ProgressBarTooltip(time,20 * 10)));
            }
        }
    }
}
