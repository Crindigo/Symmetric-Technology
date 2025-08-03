package symtech.common.metatileentities.multiblockpart;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import symtech.common.metatileentities.SymtechMetaTileEntities;

public class SymtechMetaTileEntityEnergyHatch extends MetaTileEntityEnergyHatch implements IMultiblockAbilityPart<IEnergyContainer> {

    public SymtechMetaTileEntityEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amperage, boolean isExportHatch) {
        super(metaTileEntityId, tier, amperage, isExportHatch);
    }

    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems) {
        for (MetaTileEntityEnergyHatch hatch : SymtechMetaTileEntities.NEW_ENERGY_OUTPUT_HATCH_4A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityEnergyHatch hatch : SymtechMetaTileEntities.NEW_ENERGY_OUTPUT_HATCH_16A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
    }
}
