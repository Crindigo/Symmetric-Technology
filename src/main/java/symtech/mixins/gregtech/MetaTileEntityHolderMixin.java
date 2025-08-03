package symtech.mixins.gregtech;

import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import symtech.api.SymtechLog;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "Next CEu update")
@Mixin(value = MetaTileEntityHolder.class)
public abstract class MetaTileEntityHolderMixin {

    @Shadow(remap = false)
    MetaTileEntity metaTileEntity;

    @Shadow (remap = false)
    protected abstract void setRawMetaTileEntity(MetaTileEntity metaTileEntity);

    @Inject(method = "readFromNBT",
            remap = true,
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;)V", remap = false))
    private void checkSymtech(
            NBTTagCompound compound,
            CallbackInfo ci,
            @Local(name = "metaTileEntityIdRaw") String metaTileEntityIdRaw,
            @Local(name = "metaTileEntityData") NBTTagCompound metaTileEntityData) {
        if (metaTileEntityIdRaw.startsWith("gregtech:")) {
            String stName = metaTileEntityIdRaw.replace("gregtech:", "symtech:");
            MetaTileEntity mte = GregTechAPI.MTE_REGISTRY.getObject(new ResourceLocation(stName));
            if (mte != null) {
                compound.setString("MetaId", stName);
                setRawMetaTileEntity(mte.createMetaTileEntity((IGregTechTileEntity) this));
                this.metaTileEntity.readFromNBT(metaTileEntityData);
                SymtechLog.logger.debug("Successfully migrated Symtech MetaTileEntity with ID {}", stName);
            }
        }
    }
}
