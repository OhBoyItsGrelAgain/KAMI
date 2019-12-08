package wtf.bella.kamiblue.gui.kami.theme.kami;

import wtf.bella.kamiblue.gui.kami.RenderHelper;
import wtf.bella.kamiblue.gui.kami.component.SettingsPanel;
import wtf.bella.kamiblue.gui.rgui.render.AbstractComponentUI;
import wtf.bella.kamiblue.gui.rgui.render.font.FontRenderer;

import static org.lwjgl.opengl.GL11.glColor4f;

/**
 * Created by 086 on 6/08/2017.
 */
public class RootSettingsPanelUI extends AbstractComponentUI<SettingsPanel> {

    @Override
    public void renderComponent(SettingsPanel component, FontRenderer fontRenderer) {
        glColor4f(1,0.33f,0.33f,0.2f);
        RenderHelper.drawOutlinedRoundedRectangle(0,0,component.getWidth(),component.getHeight(), 6f, 0.14f,0.14f,0.14f,component.getOpacity(),1f);
    }

}
