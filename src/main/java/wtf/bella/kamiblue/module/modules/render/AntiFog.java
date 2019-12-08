package wtf.bella.kamiblue.module.modules.render;

import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;

/**
 * Created by 086 on 9/04/2018.
 */
@Module.Info(name = "AntiFog", description = "Disables or reduces fog", category = Module.Category.RENDER)
public class AntiFog extends Module {

    public static Setting<VisionMode> mode = Settings.e("Mode", VisionMode.NOFOG);
    private static AntiFog INSTANCE = new AntiFog();

    public AntiFog() {
        INSTANCE = this;
        register(mode);
    }

    public static boolean enabled() {
        return INSTANCE.isEnabled();
    }

    public enum VisionMode {
        NOFOG, AIR
    }

}
