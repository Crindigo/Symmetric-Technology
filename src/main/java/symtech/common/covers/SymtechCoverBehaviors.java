package symtech.common.covers;

import symtech.api.SymtechLog;
import symtech.api.util.SymtechUtility;
import symtech.common.item.SymtechMetaItems;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public final class SymtechCoverBehaviors {

    private SymtechCoverBehaviors() {
    }

    public static void init() {
        SymtechLog.logger.info("Registering cover behaviors ...");

        registerBehavior(SymtechUtility.symId("conveyor.steam"), SymtechMetaItems.CONVEYOR_STEAM,
                (definition, coverableView, attachedSide) -> new CoverSteamConveyor(definition, coverableView, attachedSide, 4));

        registerBehavior(SymtechUtility.symId("pump.steam"), SymtechMetaItems.PUMP_STEAM,
                (definition, coverableView, attachedSide) -> new CoverSteamPump(definition, coverableView, attachedSide, 640));

        registerBehavior(SymtechUtility.symId("air_vent"), SymtechMetaItems.AIR_VENT,
                (definition, coverableView, attachedSide) -> new CoverAirVent(definition, coverableView, attachedSide, 100));

        registerBehavior(SymtechUtility.symId("restrictive_filter"), SymtechMetaItems.RESTRICTIVE_FILTER, CoverRestrictive::new);
    }

}
