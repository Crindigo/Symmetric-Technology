package symtech.api.fluids;

import gregtech.api.fluids.FluidState;
import gregtech.api.fluids.store.FluidStorageKey;
import symtech.api.unification.material.info.SymtechMaterialIconType;

import static symtech.api.util.SymtechUtility.symId;

public final class SymtechFluidStorageKeys {

    public static final FluidStorageKey SLURRY = new FluidStorageKey(symId("slurry"),
            SymtechMaterialIconType.slurry,
            s -> s + "_slurry",
            m -> "symtech.fluid.slurry",
            FluidState.LIQUID, -1);

    public static final FluidStorageKey IMPURE_SLURRY = new FluidStorageKey(symId("impure_slurry"),
            SymtechMaterialIconType.slurry,
            s -> "impure_" + s + "_slurry",
            m -> "symtech.fluid.impure_slurry",
            FluidState.LIQUID, -1);

    public static final FluidStorageKey SUPERCRITICAL = new FluidStorageKey(symId("supercritical"),
            SymtechMaterialIconType.supercritical,
            s -> "supercritical_" + s,
            m -> "symtech.fluid.supercritical",
            FluidState.GAS, -1);

    private SymtechFluidStorageKeys() {}
}
