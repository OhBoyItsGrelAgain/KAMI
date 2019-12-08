package wtf.bella.kamiblue.module.modules.misc;

import wtf.bella.kamiblue.module.Module;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import wtf.bella.kamiblue.mixin.client.MixinEntityRenderer;

/**
 * Created by 086 on 11/12/2017.
 * @see MixinEntityRenderer#rayTraceBlocks(WorldClient, Vec3d, Vec3d)
 */
@Module.Info(name = "CameraClip", category = Module.Category.MISC, description = "Allows your camera to pass through blocks")
public class CameraClip extends Module {}
