package wtf.bella.kamiblue.module.modules.sdashb.misc;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import wtf.bella.kamiblue.event.events.PacketEvent;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChunkData;

@Module.Info(name = "AntiChunkBan", description = "Spams /kill, gets out of ban chunks.", category = Module.Category.MISC)

/***
 * Kill mode
 * @author Fums
 * @coauthor S-B99
 * Updated by S-B99 on 01/12/19
 */
/***
* Packet mode
* @author cats
 * Updated by cats on 02/12/19
*/
public class AntiChunkBan extends Module {

    private static long startTime = 0;
    private Setting<ModeThing> modeThing = register(Settings.e("Mode", ModeThing.PACKET));
    private Setting<Float> delayTime = register(Settings.f("Kill Delay", 10));
    private Setting<Boolean> disable = register(Settings.b("Disable After Kill", false));

    private enum ModeThing {
        PACKET, KILL, BOTH
    }

    @Override
    public void onUpdate() {
        if (mc.player == null) return;

        if (modeThing.getValue().equals(ModeThing.KILL) || modeThing.getValue().equals(ModeThing.BOTH)) {
            if (Minecraft.getMinecraft().getCurrentServerData() != null) {
                if (startTime == 0) startTime = System.currentTimeMillis();
                if (startTime + delayTime.getValue() <= System.currentTimeMillis()) {
                    if (Minecraft.getMinecraft().getCurrentServerData() != null) {
                        Minecraft.getMinecraft().playerController.connection.sendPacket(new CPacketChatMessage("/kill"));
                    }
                    if (mc.player.getHealth() <= 0) {
                        mc.player.respawnPlayer();
                        mc.displayGuiScreen(null);
                        if (disable.getValue()) {
                            this.disable();
                        }
                    }
                    startTime = System.currentTimeMillis();
                }
            }
        }
    }

    @EventHandler
    Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (modeThing.getValue().equals(ModeThing.PACKET) || modeThing.getValue().equals(ModeThing.BOTH)) {
            if (mc.player == null) return;
            if (event.getPacket() instanceof SPacketChunkData && ((SPacketChunkData) event.getPacket()).getExtractedSize() >= 60000) {
                event.cancel();
            }
        }
    });
}