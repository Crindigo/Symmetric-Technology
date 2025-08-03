package symtech.common.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import symtech.api.util.SymtechUtility;

import static symtech.common.materials.SymtechMaterials.*;

public class SymtechUnknownCompositionMaterials {

    public static void init() {

        Latex = new Material.Builder(27050, SymtechUtility.symId("latex"))
                .dust().fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(293))
                .color(0xFFFADA)
                .build();

        Mud = new Material.Builder(27051, SymtechUtility.symId("mud"))
                .liquid()
                .color(0x211b14)
                .build();

        Seawater = new Material.Builder(27052, SymtechUtility.symId("sea_water"))
                .liquid()
                .color(0x3c5bc2)
                .build();

        RefractoryGunningMixture = new Material.Builder(27053, SymtechUtility.symId("refractory_gunning_mixture"))
                .liquid()
                .color(0x9c775c)
                .build();
    }

}
