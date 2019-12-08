package wtf.bella.kamiblue.module.modules.misc;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.event.events.GuiScreenEvent;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
import net.minecraft.client.gui.GuiGameOver;

/**
 * Created by 086 on 9/04/2018.
 * Updated 16 November 2019 by hub
 */
@Module.Info(name = "AutoRespawn", description = "Respawn utility", category = Module.Category.MISC)
public class AutoRespawn extends Module {

    private Setting<Boolean> respawn = register(Settings.b("Respawn", true));
    private Setting<Boolean> deathCoords = register(Settings.b("DeathCoords", true));
    private Setting<Boolean> antiGlitchScreen = register(Settings.b("Anti Glitch Screen", true));

    @EventHandler
    public Listener<GuiScreenEvent.Displayed> listener = new Listener<>(event -> {

        if (!(event.getScreen() instanceof GuiGameOver)) {
            return;
        }

        if (deathCoords.getValue() && mc.player.getHealth() <= 0) {
            Command.sendChatMessage(String.format("You died at x %d y %d z %d", (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ));
        }

        if (respawn.getValue() || (antiGlitchScreen.getValue() && mc.player.getHealth() > 0)) {
            mc.player.respawnPlayer();
            mc.displayGuiScreen(null);
        }

    });

}
