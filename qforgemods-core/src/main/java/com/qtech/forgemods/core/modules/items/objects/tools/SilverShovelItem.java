package com.qtech.forgemods.core.modules.items.objects.tools;

import com.qtech.forgemods.core.QFMCore;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Infinity sword item class.
 *
 * @author Qboi123
 */
@Mod.EventBusSubscriber(modid = QFMCore.modId)
public class SilverShovelItem extends ShovelItem {
    public SilverShovelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean hitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, @NotNull LivingEntity player) {
        if (victim.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
            if (player instanceof PlayerEntity) {
                victim.attackEntityFrom(new EntityDamageSource("player", player), 8.0f);
            } else {
                victim.attackEntityFrom(new EntityDamageSource("entity", player), 8.0f);
            }
        }
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (!entity.world.isRemote && entity instanceof LivingEntity) {
            LivingEntity victim = (LivingEntity) entity;
            if (victim.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
                victim.attackEntityFrom(new EntityDamageSource("player", player), 8.0f);
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GRAY + "This item will deal more damage to undead."));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
