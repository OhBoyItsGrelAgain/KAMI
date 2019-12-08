package wtf.bella.kamiblue.module.modules.render;

import wtf.bella.kamiblue.module.Module;

/**
 * @author 086
 */
@Module.Info(name = "NoHurtCam", category = Module.Category.RENDER, description = "Disables the 'hurt' camera effect")
public class NoHurtCam extends Module {

    private static NoHurtCam INSTANCE;

    public NoHurtCam() {
        INSTANCE = this;
    }

    public static boolean shouldDisable() {
        return INSTANCE != null && INSTANCE.isEnabled();
    }

}
