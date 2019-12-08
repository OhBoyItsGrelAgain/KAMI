package wtf.bella.kamiblue.mixin.client;

import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.gui.mc.KamiGuiChat;
import wtf.bella.kamiblue.module.ModuleManager;
import wtf.bella.kamiblue.util.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by 086 on 11/11/2017.
 */
@Mixin(GuiChat.class)
public abstract class MixinGuiChat {

    Minecraft mc = Minecraft.getMinecraft();

    @Shadow protected GuiTextField inputField;

    @Shadow public String historyBuffer;

    @Shadow public int sentHistoryCursor;

    /*@Inject(method = "initGui", at = @At("RETURN"))
    public void initGui(CallbackInfo info) {
        if (ModuleManager.getModuleByName("InfChatLength").isEnabled() && mc.player != null) {
            this.inputField.setMaxStringLength(2147483647);
        } else { this.inputField.setMaxStringLength(256); }
    }*/

    @Inject(method = "Lnet/minecraft/client/gui/GuiChat;keyTyped(CI)V", at = @At("RETURN"))
    public void returnKeyTyped(char typedChar, int keyCode, CallbackInfo info) {
        if (!(Wrapper.getMinecraft().currentScreen instanceof GuiChat) || Wrapper.getMinecraft().currentScreen instanceof KamiGuiChat) return;
        if (inputField.getText().startsWith(Command.getCommandPrefix())) {
            Wrapper.getMinecraft().displayGuiScreen(new KamiGuiChat(inputField.getText(), historyBuffer, sentHistoryCursor));
        }
    }
}
