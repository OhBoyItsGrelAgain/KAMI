package wtf.bella.kamiblue.module.modules.player;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import wtf.bella.kamiblue.event.events.PacketEvent;
import wtf.bella.kamiblue.module.Module;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

/**
 * Created by 086 on 12/12/2017.
 */
@Module.Info(name = "AntiForceLook", category = Module.Category.PLAYER)
public class AntiForceLook extends Module {

    @EventHandler
    Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (mc.player == null) return;
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook) event.getPacket();
            packet.yaw = mc.player.rotationYaw;
            packet.pitch = mc.player.rotationPitch;
        }
    });

}
