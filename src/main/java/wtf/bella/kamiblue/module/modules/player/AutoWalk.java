package wtf.bella.kamiblue.module.modules.player;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.module.ModuleManager;
import wtf.bella.kamiblue.module.modules.render.Pathfind;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
import net.minecraft.pathfinding.PathPoint;
import net.minecraftforge.client.event.InputUpdateEvent;

import static wtf.bella.kamiblue.util.EntityUtil.calculateLookAt;

/**
 * Created by 086 on 16/12/2017.
 */
@Module.Info(name = "AutoWalk", category = Module.Category.MOVEMENT)
public class AutoWalk extends Module {

    private Setting<AutoWalkMode> mode = register(Settings.e("Mode", AutoWalkMode.FORWARD));

    @EventHandler
    private Listener<InputUpdateEvent> inputUpdateEventListener = new Listener<>(event -> {
        switch (mode.getValue()) {
            case FORWARD:
                event.getMovementInput().moveForward = 1;
                break;
            case BACKWARDS:
                event.getMovementInput().moveForward = -1;
                break;
            case PATH:
                if (Pathfind.points.isEmpty()) return;
                event.getMovementInput().moveForward = 1;
                if (mc.player.isInWater() || mc.player.isInLava()) mc.player.movementInput.jump = true;
                else if (mc.player.collidedHorizontally && mc.player.onGround) mc.player.jump();
                if (!ModuleManager.isModuleEnabled("Pathfind") || Pathfind.points.isEmpty()) return;
                PathPoint next = Pathfind.points.get(0);
                lookAt(next);
                break;
        }
    });

    private void lookAt(PathPoint pathPoint) {
        double[] v = calculateLookAt(pathPoint.x + .5f, pathPoint.y, pathPoint.z + .5f, mc.player);
        mc.player.rotationYaw = (float) v[0];
        mc.player.rotationPitch = (float) v[1];
    }

    private static enum AutoWalkMode {
        FORWARD, BACKWARDS, PATH
    }
}
