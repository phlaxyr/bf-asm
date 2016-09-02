package bfasm.commands.preprocessor;

import java.util.HashMap;
import java.util.Map.Entry;

import bfasm.generators.Preprocessor;

public class MacroCommand extends PreprocessCommand {

	private HashMap<String, String> definitions = new HashMap<>();

	public MacroCommand() { }

	@Override
	public String process(String line) {
		String[] parts = line.split(" ", 2);
		if(parts[0].equals(getMnemonic())) {
			String[] cmp = parts[1].split("->", 2);
			definitions.put(cmp[0], cmp[1]);
			line = "";
		} else for(Entry<String, String> e : definitions.entrySet()) {
			line = line.replaceAll(e.getKey(), e.getValue());
		}
		
		
		return line;
	}
	
	public static void register() {
		PreprocessCommand c = new MacroCommand();
		Preprocessor.commands.put(c.getMnemonic(), c);
	}

	@Override
	public String getMnemonic() {
		return "#MACRO";
	}
}
