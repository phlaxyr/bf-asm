package bfasm.commands.preprocessor;

public abstract class PreprocessCommand {
	
	public abstract String getMnemonic();
	public abstract String process(String line);
}
