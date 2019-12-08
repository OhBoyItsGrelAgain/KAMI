package wtf.bella.kamiblue.gui.kami.theme.kami;

import wtf.bella.kamiblue.gui.kami.KamiGUI;
import wtf.bella.kamiblue.gui.kami.theme.staticui.RadarUI;
import wtf.bella.kamiblue.gui.kami.theme.staticui.TabGuiUI;
import wtf.bella.kamiblue.gui.rgui.component.container.use.Frame;
import wtf.bella.kamiblue.gui.rgui.component.use.Button;
import wtf.bella.kamiblue.gui.rgui.render.AbstractComponentUI;
import wtf.bella.kamiblue.gui.rgui.render.font.FontRenderer;
import wtf.bella.kamiblue.gui.rgui.render.theme.AbstractTheme;

/**
 * Created by 086 on 26/06/2017.
 */
public class KamiTheme extends AbstractTheme {

    FontRenderer fontRenderer;

    public KamiTheme() {
        installUI(new RootButtonUI<Button>());
        installUI(new GUIUI());
        installUI(new RootGroupboxUI());
        installUI(new KamiFrameUI<Frame>());
        installUI(new RootScrollpaneUI());
        installUI(new RootInputFieldUI());
        installUI(new RootLabelUI());
        installUI(new RootChatUI());
        installUI(new RootCheckButtonUI());
        installUI(new KamiActiveModulesUI());
        installUI(new KamiSettingsPanelUI());
        installUI(new RootSliderUI());
        installUI(new KamiEnumbuttonUI());
        installUI(new RootColorizedCheckButtonUI());
        installUI(new KamiUnboundSliderUI());

        installUI(new RadarUI());
        installUI(new TabGuiUI());

        fontRenderer=KamiGUI.fontRenderer;
    }

    @Override
    public FontRenderer getFontRenderer() {
        return fontRenderer;
    }

    public class GUIUI extends AbstractComponentUI<KamiGUI> {
    }
}
