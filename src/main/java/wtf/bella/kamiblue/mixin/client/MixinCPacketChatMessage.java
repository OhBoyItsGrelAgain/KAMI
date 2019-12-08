package wtf.bella.kamiblue.mixin.client;

import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CPacketChatMessage.class)
public abstract class MixinCPacketChatMessage {
    /*@Inject(method = "<init>(Ljava/lang/String;)V", at = @At("HEAD"))
    public void CPacketChatMessage(String p_i46887_1_, CallbackInfo info) {
        if (p_i46887_1_.length() > 256) {
            p_i46887_1_ = p_i46887_1_.substring(0, 256);
        }

        this.message = p_i46887_1_;
    }*/
}
