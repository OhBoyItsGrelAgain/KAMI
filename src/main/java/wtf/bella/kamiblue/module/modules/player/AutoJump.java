package wtf.bella.kamiblue.module.modules.player;

import wtf.bella.kamiblue.module.Module;

/**
 * Created by 086 on 24/12/2017.
 */
@Module.Info(name = "AutoJump", category = Module.Category.MOVEMENT, description = "Automatically jumps if possible")
public class AutoJump extends Module {

    @Override
    public void onUpdate() {
        if (mc.player.isInWater() || mc.player.isInLava()) mc.player.motionY = 0.1;
        else if (mc.player.onGround) mc.player.jump();
    }

}
