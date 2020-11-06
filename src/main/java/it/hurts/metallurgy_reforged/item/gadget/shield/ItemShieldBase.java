package it.hurts.metallurgy_reforged.item.gadget.shield;

import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ItemShieldBase extends ItemExtra {

    public ItemShieldBase(String name, int durability)
    {
        super("lemurite_shield", MetallurgyTabs.tabSpecial, "gadget");
        setMaxStackSize(1);
        //setMaxDamage(250);

        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public boolean isShield(@Nonnull ItemStack stack, @Nullable EntityLivingBase entity)
    {
        return true;
    }

    @Override
    public abstract int getItemEnchantability();

    @Override
    public abstract int getMaxItemUseDuration(@Nonnull ItemStack stack);

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
}