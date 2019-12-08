package wtf.bella.kamiblue.setting.builder.primitive;

import wtf.bella.kamiblue.setting.builder.SettingBuilder;
import wtf.bella.kamiblue.setting.impl.StringSetting;

/**
 * Created by 086 on 13/10/2018.
 */
public class StringSettingBuilder extends SettingBuilder<String> {
    @Override
    public StringSetting build() {
        return new StringSetting(initialValue, predicate(), consumer(), name, visibilityPredicate());
    }
}
