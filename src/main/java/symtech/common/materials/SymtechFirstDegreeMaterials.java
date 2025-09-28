package symtech.common.materials;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;
import symtech.api.util.SymtechUtility;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static symtech.api.unification.material.info.SymtechMaterialFlags.SUPERALLOY;
import static symtech.api.util.SymtechUtility.symId;
import static symtech.common.materials.SymtechMaterials.*;

public class SymtechFirstDegreeMaterials {

    public static void init() {

        ManganeseIronArsenicPhosphide = new Material.Builder(27100, symId("manganese_iron_arsenic_phosphide"))
                .ingot()
                .color(0x03FCF0).iconSet(MaterialIconSet.METALLIC)
                .cableProperties(GTValues.V[4], 2, 4)
                .components(Manganese, 2, Iron, 2, Arsenic, 1, Phosphorus, 1)
                .blastTemp(2100, BlastProperty.GasTier.LOW)
                .build();

        PraseodymiumNickel = new Material.Builder(27101, symId("praseodymium_nickel"))
                .ingot()
                .color(0x03BAFC).iconSet(MaterialIconSet.METALLIC)
                .cableProperties(GTValues.V[4], 2, 4)
                .components(Praseodymium, 5, Nickel, 1)
                .blastTemp(2100, BlastProperty.GasTier.MID)
                .build();

        GadoliniumSiliconGermanium = new Material.Builder(27102, symId("gadolinium_silicon_germanium"))
                .ingot()
                .color(0x0388FC).iconSet(MaterialIconSet.SHINY)
                .cableProperties(GTValues.V[4], 2, 4)
                .components(Gadolinium, 5, Silicon, 2, Germanium, 2)
                .blastTemp(2100, BlastProperty.GasTier.HIGH)
                .build();

        //Minerals

        Anorthite = new Material.Builder(27103, symId("anorthite"))
                .dust()
                .gem()
                .color(0x595853).iconSet(CERTUS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Calcium, 1, Aluminium, 2, Silicon, 2, Oxygen, 8)
                .build()
                .setFormula("Ca(Al2Si2O8)", true);


        Albite = new Material.Builder(27104, symId("albite"))
                .dust()
                .gem()
                .color(0xc4a997).iconSet(CERTUS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
                .build()
                .setFormula("Na(AlSi3O8)", true);

        Oligoclase = new Material.Builder(27105, symId("oligoclase"))
                .dust()
                .gem()
                .color(0xd5c4b8).iconSet(CERTUS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Albite, 4, Anorthite, 1)
                .build()
                .setFormula("(Na,Ca)(Si,Al)4O8", true);

        Andesine = new Material.Builder(27106, symId("andesine"))
                .dust()
                .gem()
                .color(0xe18e6f).iconSet(EMERALD)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Albite, 3, Anorthite, 2)
                .build()
                .setFormula("(Na,Ca)(Si,Al)4O8", true);

        Labradorite = new Material.Builder(27107, symId("labradorite"))
                .dust()
                .gem()
                .color(0x5c7181).iconSet(RUBY)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Albite, 2, Anorthite, 3)
                .build()
                .setFormula("(Na,Ca)(Si,Al)4O8", true);

        Bytownite = new Material.Builder(27108, symId("bytownite"))
                .dust()
                .gem()
                .color(0xc99c67).iconSet(LAPIS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Albite, 1, Anorthite, 4)
                .build()
                .setFormula("(Na,Ca)(Si,Al)4O8", true);

        Clinochlore = new Material.Builder(27109, symId("clinochlore"))
                .dust()
                .gem()
                .color(0x303e38).iconSet(EMERALD)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Magnesium, 5, Aluminium, 2, Silicon, 3, Oxygen, 18, Hydrogen, 8)
                .build()
                .setFormula("(Mg5Al)(AlSi3)O10(OH)8", true);

        Augite = new Material.Builder(27110, symId("augite"))
                .dust()
                .color(0x1b1717).iconSet(ROUGH)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Calcium, 2, Magnesium, 3, Iron, 3, Silicon, 8, Oxygen, 24)
                .build()
                .setFormula("(Ca2MgFe)(MgFe)2(Si2O6)4", true);


        Dolomite = new Material.Builder(27111, symId("dolomite"))
                .dust()
                .color(0xbbb8b2)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Calcium, 1, Magnesium, 1, Carbon, 2, Oxygen, 6)
                .build()
                .setFormula("CaMg(CO3)2", true);

        Muscovite = new Material.Builder(27112, symId("muscovite"))
                .dust()
                .color(0x8b876a)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Potassium, 1, Aluminium, 3, Silicon, 3, Oxygen, 12, Hydrogen, 10)
                .build()
                .setFormula("KAl2(AlSi3O10)(OH)2)", true);

        Fluorite = new Material.Builder(27113, symId("fluorite"))
                .dust()
                .gem()
                .ore()
                .color(0x276a4c).iconSet(CERTUS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Calcium, 1, Fluorine, 2)
                .build();

        Forsterite = new Material.Builder(27114, symId("forsterite"))
                .dust()
                .gem()
                .color(0x1d640f).iconSet(LAPIS)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Magnesium, 2, Sulfur, 1, Oxygen, 4)
                .build()
                .setFormula("Mg2(SiO4)",true);

        Lizardite = new Material.Builder(27115, symId("lizardite"))
                .dust()
                .color(0xa79e42)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .components(Magnesium, 3, Silicon, 2, Oxygen, 9, Hydrogen, 4)
                .build()
                .setFormula("Mg3Si2O5(OH)4",true);

        SodiumIodate = new Material.Builder(27116, symId("sodium_iodate"))
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Sodium, 1, Iodine, 1, Oxygen, 3)
                .build();

        GermaniumDioxide = new Material.Builder(27117, symId("germanium_dioxide"))
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Germanium, 1, Oxygen, 2)
                .build();

        SodiumHydroxideSolution = new Material.Builder(27118, symId("sodium_hydroxide_solution"))
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SodiumHydroxide, 1, Water, 1)
                .build();

        Nimonic105 = new Material.Builder(8637, SymtechUtility.symId("nimonic_105"))
                .ingot().liquid(new FluidBuilder().temperature(1770))
                .iconSet(SHINY)
                .flags(GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_PLATE, GENERATE_RING, GENERATE_ROTOR, SUPERALLOY)
                .components(Nickel, 19, Chrome, 6, Manganese, 1, Iron, 1, Aluminium, 2, Titanium, 1, Cobalt, 8)
                .colorAverage()
                .blastTemp(3000, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV])
                .build();

        Incoloy825 = new Material.Builder(8628, SymtechUtility.symId("incoloy_825"))
                .ingot().liquid(new FluidBuilder().temperature(1675))
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_ROTOR, SUPERALLOY)
                .components(Iron, 9, Nickel, 16, Chrome, 7, Manganese, 1, Copper, 2, Aluminium, 1, Titanium, 1,
                        Molybdenum, 2)
                .colorAverage()
                .blastTemp(3000, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV])
                .build();

        SiliconCarbide = new Material.Builder(8145, SymtechUtility.symId("silicon_carbide"))
                .dust()
                .flags(GENERATE_PLATE)
                .components(Silicon, 1, Carbon, 1)
                .color(0x404040)
                .build();
    }
}
