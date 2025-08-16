package symtech.common.materials;

import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.*;
import symtech.api.SymtechLog;
import symtech.api.unification.material.info.SymtechMaterialFlags;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;

public class SymtechMaterials {

    public static Material ManganeseIronArsenicPhosphide;
    public static Material PraseodymiumNickel;
    public static Material GadoliniumSiliconGermanium;


    public static Material Gabbro;
    public static Material Gneiss;
    public static Material Limestone;
    public static Material Phyllite;
    public static Material Shale;
    public static Material Slate;
    public static Material Kimberlite;
    public static Material Anorthosite;

    public static Material Latex;
    public static Material Mud;
    public static Material Seawater;
    public static Material ChlorinatedSeawater;

    public static Material RefractoryGunningMixture;
    public static Material RareEarthHydroxides;
    public static Material RareEarthChlorides;

    //Minerals
    public static Material Anorthite;
    public static Material Albite;
    public static Material Oligoclase;
    public static Material Andesine;
    public static Material Labradorite;
    public static Material Bytownite;
    public static Material Clinochlore;
    public static Material Augite;
    public static Material Dolomite;
    public static Material Muscovite;
    public static Material Forsterite;
    public static Material Lizardite;
    public static Material Fluorite;
    public static Material SodiumIodate;
    public static Material GermaniumDioxide;
    public static Material SodiumHydroxideSolution;

    public static void init() {
        SymtechElementMaterials.init();
        SymtechFirstDegreeMaterials.init();
        SymtechSecondDegreeMaterials.init();
        SymtechOrganicChemistryMaterials.init();
        SymtechHighDegreeMaterials.init();
        SymtechUnknownCompositionMaterials.init();
        changeProperties();
    }

    private static void changeProperties() {
        //removeProperty(PropertyKey.ORE, Materials.Graphite);

        //removeProperty(PropertyKey.ORE, Materials.Soapstone);
        //removeProperty(PropertyKey.ORE, Materials.Quartzite);
        //removeProperty(PropertyKey.ORE, Materials.Mica);
        removeProperty(PropertyKey.FLUID_PIPE, Materials.Lead);
        Materials.Lead.setProperty(PropertyKey.FLUID_PIPE, new FluidPipeProperties(1200, 8, true, true, false, false));

        //Add dusts and fluids for elements that do not have them
        Materials.Iodine.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Scandium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Germanium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Selenium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Bromine.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.Rubidium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Strontium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Zirconium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Technetium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Tellurium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Praseodymium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Promethium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Gadolinium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Terbium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Dysprosium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Holmium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Erbium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Thulium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Ytterbium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Hafnium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Rhenium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Thallium.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.CalciumChloride.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.MagnesiumChloride.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.RockSalt.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.Salt.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.SodiumHydroxide.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.Sodium.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.Phosphorus.setProperty(PropertyKey.INGOT, new IngotProperty());
        Materials.Phosphorus.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(317)));
        Materials.Phosphorus.setMaterialRGB(0xfffed6);

        // keep these the same as CEu
        //Materials.HydrochloricAcid.setFormula("(H2O)(HCl)", true);
        //Materials.HydrofluoricAcid.setFormula("(H2O)(HF)", true);

        removeProperty(PropertyKey.FLUID, Materials.Dimethyldichlorosilane);
        Materials.Dimethyldichlorosilane.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Materials.Iron3Chloride.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Nitrochlorobenzene.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Dichlorobenzene.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Dichlorobenzidine.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.PhthalicAcid.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.DiphenylIsophtalate.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Diaminobenzidine.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.PolyvinylAcetate.setProperty(PropertyKey.DUST, new DustProperty());

        Materials.Platinum.addFlags(SymtechMaterialFlags.GENERATE_CATALYST_BED);

        Materials.Cobalt.addFlags(SymtechMaterialFlags.GENERATE_CATALYST_BED);

        Materials.Palladium.addFlags(SymtechMaterialFlags.GENERATE_CATALYST_BED);

        Materials.Rhodium.addFlags(SymtechMaterialFlags.GENERATE_CATALYST_BED);

        Materials.Copper.addFlags(SymtechMaterialFlags.GENERATE_CATALYST_BED);

        //Materials.Electrum.setProperty(PropertyKey.ORE, new OreProperty());

        Materials.Hydrogen.addFlags(MaterialFlags.FLAMMABLE);
    }

    private static void removeProperty(PropertyKey<?> key, Material material) {
        Map<PropertyKey<?>, IMaterialProperty> map = null;
        try {
            Field field = MaterialProperties.class.getDeclaredField("propertyMap");
            field.setAccessible(true);
            //noinspection unchecked
            map = (Map<PropertyKey<?>, IMaterialProperty>) field.get(material.getProperties());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            SymtechLog.logger.error("Failed to reflect material property map", e);
        }
        if (map != null) {
            map.remove(key);
        }
    }
}
