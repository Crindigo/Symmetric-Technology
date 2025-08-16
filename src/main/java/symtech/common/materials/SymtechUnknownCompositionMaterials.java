package symtech.common.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import symtech.api.util.SymtechUtility;

import static gregtech.api.unification.material.Materials.*;
import static symtech.api.util.SymtechUtility.symId;
import static symtech.common.materials.SymtechMaterials.*;

public class SymtechUnknownCompositionMaterials {

    public static void init() {

        Latex = new Material.Builder(27050, symId("latex"))
                .dust().fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(293))
                .color(0xFFFADA)
                .build();

        Mud = new Material.Builder(27051, symId("mud"))
                .liquid()
                .color(0x211b14)
                .build();

        Seawater = new Material.Builder(27052, symId("sea_water"))
                .liquid()
                .color(0x3c5bc2)
                .build();

        RefractoryGunningMixture = new Material.Builder(27053, symId("refractory_gunning_mixture"))
                .liquid()
                .color(0x9c775c)
                .build();

        RareEarthHydroxides = new Material.Builder(27054, symId("rare_earth_hydroxides"))
                .dust()
                .color(0x8080a0)
                .build();

        RareEarthChlorides = new Material.Builder(27055, symId("rare_earth_chlorides"))
                .dust()
                .color(0x809e64)
                .build();

        ChlorinatedSeawater = new Material.Builder(27056, symId("chlorinated_seawater"))
                .liquid()
                .color(0x215a85)
                .build();
    }

}
