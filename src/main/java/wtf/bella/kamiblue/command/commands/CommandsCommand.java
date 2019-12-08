package wtf.bella.kamiblue.command.commands;

import wtf.bella.kamiblue.KamiBlueMod;
import wtf.bella.kamiblue.command.Command;
import wtf.bella.kamiblue.command.syntax.SyntaxChunk;

import java.util.Comparator;

/**
 * Created by 086 on 12/11/2017.
 */
public class CommandsCommand extends Command {

    public CommandsCommand() {
        super("commands", SyntaxChunk.EMPTY);
        setDescription("Gives you this list of commands");
    }

    @Override
    public void call(String[] args) {
        KamiBlueMod.getInstance().getCommandManager().getCommands().stream().sorted(Comparator.comparing(command -> command.getLabel())).forEach(command ->
            Command.sendChatMessage("&7" + Command.getCommandPrefix() + command.getLabel() + "&r ~ &8" + command.getDescription())
        );
    }
}
