package wtf.bella.kamiblue.module.modules.render;

import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.setting.Setting;
import wtf.bella.kamiblue.setting.Settings;
import wtf.bella.kamiblue.util.Friends;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;

/**
 * Created by 086 on 8/04/2018.
 */
@Module.Info(name = "ExtraTab", description = "Expands the player tab menu", category = Module.Category.RENDER)
public class ExtraTab extends Module {

    public Setting<Integer> tabSize = register(Settings.integerBuilder("Players").withMinimum(1).withValue(80).build());

    public static ExtraTab INSTANCE;

    public ExtraTab() {
        ExtraTab.INSTANCE = this;
    }

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String dname = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (Friends.isFriend(dname)) return String.format("%sa%s", Command.SECTIONSIGN(), dname);
        return dname;
    }
}
