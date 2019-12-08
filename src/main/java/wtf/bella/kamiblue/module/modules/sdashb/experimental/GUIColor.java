package wtf.bella.kamiblue.module.modules.sdashb.experimental;

import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
//import wtf.bella.kamiblue.setting.builder.numerical.NumericalSettingBuilder;
//import wtf.bella.kamiblue.setting.impl.numerical.NumberSetting;

@Module.Info(name="GUI Color", description="Change GUI Colors", category=Module.Category.EXPERIMENTAL)
public class GUIColor extends Module {
    public Setting<Integer> red = this.register(Settings.integerBuilder("Red").withMinimum(0).withValue(13).withMaximum(255).build());
    public Setting<Integer> green = this.register(Settings.integerBuilder("Green").withMinimum(0).withValue(13).withMaximum(255).build());
    public Setting<Integer> blue = this.register(Settings.integerBuilder("Blue").withMinimum(0).withValue(13).withMaximum(255).build());
    public Setting<Integer> alpha = this.register(Settings.integerBuilder("Alpha").withMinimum(0).withValue(117).withMaximum(255).build());
}
