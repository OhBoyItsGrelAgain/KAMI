package wtf.bella.kamiblue.command.commands;

import wtf.bella.kamiblue.KamiBlueMod;
import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.command.syntax.ChunkBuilder;
import wtf.bella.kamiblue.command.syntax.parsers.DependantParser;
import wtf.bella.kamiblue.command.syntax.parsers.EnumParser;
import wtf.bella.kamiblue.gui.kami.KamiGUI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 086 on 14/10/2018.
 */
public class ConfigCommand extends Command {

    public ConfigCommand() {
        super("config", new ChunkBuilder()
                .append("mode", true, new EnumParser(new String[]{"reload", "save", "path"}))
                .append("path", true, new DependantParser(0, new DependantParser.Dependency(new String[][]{{"path", "path"}}, "")))
                .build());
        setDescription("Change where your config is saved or manually save and reload your config");
    }

    @Override
    public void call(String[] args) {
        if (args[0] == null) {
            Command.sendChatMessage("Missing argument &bmode&r: Choose from reload, save or path");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                reload();
                break;
            case "save":
                try {
                    KamiBlueMod.saveConfigurationUnsafe();
                    Command.sendChatMessage("Saved configuration!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Command.sendChatMessage("Failed to save! " + e.getMessage());
                }
                break;
            case "path":
                if (args[1] == null) {
                    Path file = Paths.get(KamiBlueMod.getConfigName());
                    Command.sendChatMessage("Path to configuration: &b" + file.toAbsolutePath().toString());
                } else {
                    String newPath = args[1];
                    if (!KamiBlueMod.isFilenameValid(newPath)) {
                        Command.sendChatMessage("&b" + newPath + "&r is not a valid path");
                        break;
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("KAMILastConfig.txt"))) {
                        writer.write(newPath);
                        reload();
                        Command.sendChatMessage("Configuration path set to &b" + newPath + "&r!");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        Command.sendChatMessage("Couldn't set path: " + e.getMessage());
                        break;
                    }
                }
                break;
            default:
                Command.sendChatMessage("Incorrect mode, please choose from: reload, save or path");
        }
    }

    private void reload() {
        KamiBlueMod.getInstance().guiManager = new KamiGUI();
        KamiBlueMod.getInstance().guiManager.initializeGUI();
        KamiBlueMod.loadConfiguration();
        Command.sendChatMessage("Configuration reloaded!");
    }

}
