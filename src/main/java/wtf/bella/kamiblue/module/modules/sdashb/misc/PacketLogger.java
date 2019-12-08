package wtf.bella.kamiblue.module.modules.sdashb.misc;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.event.events.PacketEvent;
import wtf.bella.kamiblue.module.Module;

@Module.Info(name = "PacketLogger", description = "Logs packets for debugging", category = Module.Category.MISC)
public class PacketLogger extends Module {
    @EventHandler
    Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() != null) {
            Command.sendChatMessage("Received" + event.getPacket().toString());
        }
    });
    @EventHandler
    Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {
        if (event.getPacket() != null) {
            Command.sendChatMessage("Sent" + event.getPacket().toString());
        }
    });
}
