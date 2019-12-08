package wtf.bella.kamiblue.command.syntax.parsers;


import wtf.bella.kamiblue.command.syntax.SyntaxChunk;
import wtf.bella.kamiblue.command.syntax.SyntaxParser;

public abstract class AbstractParser implements SyntaxParser {

    @Override
    public abstract String getChunk(SyntaxChunk[] chunks, SyntaxChunk thisChunk, String[] values, String chunkValue);
    protected String getDefaultChunk(SyntaxChunk chunk){
        return (chunk.isHeadless() ? "" : chunk.getHead()) + (chunk.isNecessary() ? "<" : "[") + chunk.getType() + (chunk.isNecessary() ? ">" : "]");
    }

}
