package bfasm.commands.preprocessor;

import java.util.HashMap;
import java.util.Map.Entry;

import bfasm.generators.Preprocessor;

public class DefCommand extends PreprocessCommand {

	HashMap<String, String> definitions = new HashMap<>();
	
	public DefCommand() { }

	@Override
	public String getMnemonic() {
		// TODO Auto-generated method stub
		return "#DEF";
	}

	@Override
	public String process(String line) {
		String[] parts = line.split(" ", 3);
		if(parts[0].equals(getMnemonic())) {
			definitions.put(parts[1], parts[2]);
			line = "";
		} else for(Entry<String, String> e : definitions.entrySet()) {
			line = line.replace(e.getKey(), e.getValue());
		}
		
		return line;
	}
	
	public static void register() {
		PreprocessCommand c = new DefCommand();
		Preprocessor.commands.put(c.getMnemonic(), c);
	}

}
