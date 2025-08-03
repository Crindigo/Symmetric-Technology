package symtech.common.metatileentities.storage;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityUIFactory;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.storage.MetaTileEntityCrate;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import symtech.api.capability.impl.InaccessibleHandlerDelegate;
import symtech.api.sound.SusySounds;
import symtech.client.renderer.textures.SusyTextures;
import symtech.common.item.SuSyMetaItems;
import symtech.mixins.gregtech.MetaTileEntityCrateAccessor;

import java.util.List;

public class MetaTileEntityLockedCrate extends MetaTileEntityCrate {

    public MetaTileEntityLockedCrate(ResourceLocation metaTileEntityId, Material material, int inventorySize) {
        super(metaTileEntityId, material, inventorySize);
        ((MetaTileEntityCrateAccessor) this).setTaped(true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        var self = (MetaTileEntityCrateAccessor) this;
        return new MetaTileEntityLockedCrate(metaTileEntityId, self.getMaterial(), self.getInventorySize());
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        // Overriding the inventory to prevent I/O
        this.itemInventory = new InaccessibleHandlerDelegate(inventory);
    }

    @Override
    public boolean keepsInventory() {
        return true;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        // Copied from super class to ensure typed overlay won't be rendered
        var self = (MetaTileEntityCrateAccessor) this;
        if (self.getMaterial().toString().contains("wood")) {
            Textures.WOODEN_CRATE.render(renderState, translation,
                    GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()), pipeline);
        } else {
            int baseColor = ColourRGBA.multiply(GTUtility.convertRGBtoOpaqueRGBA_CL(self.getMaterial().getMaterialRGB()),
                    GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()));
            Textures.METAL_CRATE.render(renderState, translation, baseColor, pipeline);
        }
        // Always render the overlay texture on the TOP face only
        SusyTextures.CODE_BREACHER_OVERLAY.renderOrientedState(renderState, translation, pipeline, Cuboid6.full, EnumFacing.UP, false, false);
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false; // To avoid opening the GUI on the right-click in the super method
    }

    @Override
    public boolean canPlaceCoverOnSide(@NotNull EnumFacing side) {
        return false;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public void initFromItemStackData(NBTTagCompound data) {
        super.initFromItemStackData(data);
        // Overriding Typed field to ensure it is always true
        ((MetaTileEntityCrateAccessor) this).setTaped(true);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", ((MetaTileEntityCrateAccessor) this).getInventorySize()));
        // Skipping the Taped information (It's a lie)
    }
}
