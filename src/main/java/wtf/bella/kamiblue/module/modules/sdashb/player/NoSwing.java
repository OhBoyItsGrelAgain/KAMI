package wtf.bella.kamiblue.module.modules.sdashb.player;

import wtf.bella.kamiblue.event.events.PacketEvent;
import wtf.bella.kamiblue.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketAnimation;

/**
 *Made by FINZ0
 */

@Module.Info(name = "NoSwing", category = Module.Category.PLAYER)
public class
NoSwing extends Module {

@EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketAnimation) {
            event.cancel();
        }
    });

}
