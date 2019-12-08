package wtf.bella.kamiblue.module.modules.misc;

import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.mixin.client.MixinNetworkManager;

/**
 * @author 086
 * @see MixinNetworkManager
 */
@Module.Info(name = "NoPacketKick", category = Module.Category.MISC, description = "Prevent large packets from kicking you")
public class NoPacketKick {
    private static NoPacketKick INSTANCE;

    public NoPacketKick() {
        INSTANCE = this;
    }

    public static boolean isEnabled() {
        return INSTANCE.isEnabled();
    }

}
