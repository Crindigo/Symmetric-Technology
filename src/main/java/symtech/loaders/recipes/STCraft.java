package symtech.loaders.recipes;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.loaders.recipe.CraftingComponent;

import static gregtech.api.GTValues.*;

public class STCraft {

    public static Component cable = new Component(CraftingComponent.CABLE);
    public static Component pump = new Component(CraftingComponent.PUMP);
    public static Component hull = new Component(CraftingComponent.HULL);
    public static Component circuit = new Component(CraftingComponent.CIRCUIT);
    public static Component spring = new Component(CraftingComponent.SPRING);
    public static Component motor = new Component(CraftingComponent.MOTOR);
    public static Component arm = new Component(CraftingComponent.ROBOT_ARM);
    public static Component rotor = new Component(CraftingComponent.ROTOR);
    public static Component piston = new Component(CraftingComponent.PISTON);

    public static UnificationEntry plate(Material material) {
        return new UnificationEntry(OrePrefix.plate, material);
    }

    public static class Component {
        private final CraftingComponent.Component type;

        public Component(CraftingComponent.Component type) {
            this.type = type;
        }

        public Object ulv() {
            return this.type.getIngredient(ULV);
        }

        public Object lv() {
            return this.type.getIngredient(LV);
        }

        public Object mv() {
            return this.type.getIngredient(MV);
        }

        public Object hv() {
            return this.type.getIngredient(HV);
        }

        public Object ev() {
            return this.type.getIngredient(EV);
        }
    }
}
