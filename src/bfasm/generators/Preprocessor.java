package bfasm.generators;

import java.util.HashMap;

import bfasm.commands.preprocessor.DefCommand;
import bfasm.commands.preprocessor.MallocCommand;
import bfasm.commands.preprocessor.PreprocessCommand;

public class Preprocessor {

	private Preprocessor() { }
	
	public static HashMap<String, PreprocessCommand> commands = new HashMap<>();
	
	public static void register() {
		DefCommand.register();
		MallocCommand.register();
	}
	
	public static String preParse(String line) {
		
		// Implementation for comments
		line = line.split("`", 2) [0].trim();
		
		PreprocessCommand pc = commands.getOrDefault(line.split(" ", 2)[0], null);
		
		if(pc != null)
			line = pc.process(line);
		else for(PreprocessCommand c : commands.values()) {
			line = c.process(line);
		}
		
		return line;
	}
}
