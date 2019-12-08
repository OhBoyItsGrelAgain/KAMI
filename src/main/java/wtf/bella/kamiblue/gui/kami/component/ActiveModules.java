package wtf.bella.kamiblue.gui.kami.component;

import wtf.bella.kamiblue.gui.rgui.component.container.use.Frame;
import wtf.bella.kamiblue.gui.rgui.component.listen.RenderListener;
import wtf.bella.kamiblue.gui.rgui.component.use.Label;
import wtf.bella.kamiblue.gui.rgui.util.ContainerHelper;
import wtf.bella.kamiblue.gui.rgui.util.Docking;

public class ActiveModules extends Label {
//    public HashMap<Module, Integer> slide = new HashMap<>();

    public boolean sort_up = true;

    public ActiveModules() {
        super("");

        addRenderListener(new RenderListener() {
            @Override
            public void onPreRender() {
                Frame parentFrame = ContainerHelper.getFirstParent(Frame.class, ActiveModules.this);
                if (parentFrame == null) return;
                Docking docking = parentFrame.getDocking();
                if (docking.isTop()) sort_up = true;
                if (docking.isBottom()) sort_up = false;
            }

            @Override
            public void onPostRender() {}
        });
    }
};