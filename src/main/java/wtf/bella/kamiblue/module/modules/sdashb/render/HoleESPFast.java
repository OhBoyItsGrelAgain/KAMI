package wtf.bella.kamiblue.module.modules.sdashb.render;

import wtf.bella.kamiblue.event.events.RenderEvent;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.module.ModuleManager;
import wtf.bella.kamiblue.module.modules.combat.CrystalAura;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
import wtf.bella.kamiblue.util.GeometryMasks;
import wtf.bella.kamiblue.util.KamiTessellator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static wtf.bella.kamiblue.module.modules.combat.CrystalAura.getPlayerPos;

/**
 * Created 16 November 2019 by hub
 */
@Module.Info(name = "HoleESPFast", category = Module.Category.RENDER, description = "Show safe holes")
public class HoleESPFast extends Module {

    private final BlockPos[] surroundOffset = {
            new BlockPos(0, -1, 0), // down
            new BlockPos(0, 0, -1), // north
            new BlockPos(1, 0, 0), // east
            new BlockPos(0, 0, 1), // south
            new BlockPos(-1, 0, 0) // west
    };

    private Setting<Double> renderDistance = register(Settings.d("Render Distance", 8.0d));
    private Setting<RenderMode> renderMode = register(Settings.e("Render Mode", RenderMode.DOWN));
    private Setting<Integer> renderAlpha = register(Settings.integerBuilder("Render Alpha").withMinimum(0).withValue(42).withMaximum(255).build());

    private ConcurrentHashMap<BlockPos, Boolean> safeHoles;

    @Override
    public void onUpdate() {

        if (safeHoles == null) {
            safeHoles = new ConcurrentHashMap<>();
        } else {
            safeHoles.clear();
        }

        int range = (int) Math.ceil(renderDistance.getValue());

        CrystalAura crystalAura = (CrystalAura) ModuleManager.getModuleByName("CrystalAura");
        List<BlockPos> blockPosList = crystalAura.getSphere(getPlayerPos(), range, range, false, true, 0);

        for (BlockPos pos : blockPosList) {

            // block gotta be air
            if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                continue;
            }

            // block 1 above gotta be air
            if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }

            // block 2 above gotta be air
            if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }

            boolean isSafe = true;
            boolean isBedrock = true;

            for (BlockPos offset : surroundOffset) {
                Block block = mc.world.getBlockState(pos.add(offset)).getBlock();
                if (block != Blocks.BEDROCK) {
                    isBedrock = false;
                }
                if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                    isSafe = false;
                    break;
                }
            }

            if (isSafe) {
                safeHoles.put(pos, isBedrock);
            }

        }

    }

    @Override
    public void onWorldRender(final RenderEvent event) {

        if (mc.player == null || safeHoles == null) {
            return;
        }

        if (safeHoles.isEmpty()) {
            return;
        }

        KamiTessellator.prepare(GL11.GL_QUADS);

        safeHoles.forEach((blockPos, isBedrock) -> {
            if (isBedrock) {
                drawBox(blockPos, 81, 12, 104);
            } else {
                drawBox(blockPos, 104, 12, 35);
            }
        });

        KamiTessellator.release();

    }

    private void drawBox(BlockPos blockPos, int r, int g, int b) {
        Color color = new Color(r, g, b, renderAlpha.getValue());
        if (renderMode.getValue().equals(RenderMode.DOWN)) {
            KamiTessellator.drawBox(blockPos, color.getRGB(), GeometryMasks.Quad.DOWN);
        } else if (renderMode.getValue().equals(RenderMode.BLOCK)) {
            KamiTessellator.drawBox(blockPos, color.getRGB(), GeometryMasks.Quad.ALL);
        }
    }

    private enum RenderMode {
        DOWN, BLOCK
    }

}
