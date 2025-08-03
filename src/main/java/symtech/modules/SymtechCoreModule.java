package symtech.modules;

import gregtech.api.GregTechAPI;
import gregtech.api.modules.GregTechModule;
import gregtech.api.modules.IGregTechModule;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import symtech.Symtech;
import symtech.api.SymtechLog;
import symtech.common.network.SPacketFirstJoin;
import symtech.common.network.SPacketRemoveFluidState;

@GregTechModule(
        moduleID = SymtechModules.MODULE_CORE,
        containerID = Symtech.MODID,
        name = "Symtech Core",
        description = "Core module of Symtech Core, so this should be called Symtech Core Core ngl.",
        coreModule = true)
public class SymtechCoreModule implements IGregTechModule {

    @Override
    public @NotNull Logger getLogger() {
        return SymtechLog.logger;
    }

    @Override
    public void registerPackets() {
        GregTechAPI.networkHandler.registerPacket(SPacketRemoveFluidState.class);
        GregTechAPI.networkHandler.registerPacket(SPacketFirstJoin.class);
    }
}
