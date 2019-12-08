package wtf.bella.kamiblue.command.commands;

import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.command.syntax.ChunkBuilder;
import wtf.bella.kamiblue.command.syntax.parsers.ModuleParser;
import wtf.bella.kamiblue.module.Module;
import wtf.bella.kamiblue.module.ModuleManager;

/**
 * Created by 086 on 17/11/2017.
 */
public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle", new ChunkBuilder()
                .append("module", true, new ModuleParser())
                .build());
        setDescription("Quickly toggle a module on and off");
    }

    @Override
    public void call(String[] args) {
        if (args.length == 0) {
            Command.sendChatMessage("Please specify a module!");
            return;
        }
        Module m = ModuleManager.getModuleByName(args[0]);
        if (m == null) {
            Command.sendChatMessage("Unknown module '" + args[0] + "'");
            return;
        }
        m.toggle();
        Command.sendChatMessage(m.getName() + (m.isEnabled() ? " &aenabled" : " &cdisabled"));
    }
}
