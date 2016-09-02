package bfasm.commands.preprocessor;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import bfasm.generators.Preprocessor;

public class DefCommand extends PreprocessCommand {

	HashMap<String, String> definitions = new HashMap<>();
	
	public DefCommand() { }

	@Override
	public String getMnemonic() {
		return "#DEF";
	}

	@Override
	public String process(String line) {
		String[] parts = line.split(" ", 3);
		if(parts[0].equals(getMnemonic())) {
			definitions.put(parts[1], parts[2]);
			line = "";
		} else for(Entry<String, String> e : definitions.entrySet()) {
			// Replace at beginning of line
            if(line.startsWith(e.getKey()) && tokenSeperator(line.charAt(e.getKey().length()))) {
                line = e.getValue() + line.substring(e.getKey().length());
            }
           
            // Replace in middle of line
            line = line.replaceAll(tokenRegex() +
                    Pattern.quote(e.getKey()) + tokenRegex(),
                    "$1" + e.getValue() + "$2");
           
            // Replace at end of line
            if(line.endsWith(e.getKey()) && tokenSeperator(
                    line.charAt(line.length() - e.getKey().length() - 1)))
                line = line.substring(0,
                        line.length() - e.getKey().length()) + e.getValue();
            }
		
		return line;
	}
	
	private String tokenRegex() {
		return "(\\s+)";
	}

	private boolean tokenSeperator(char charAt) {
		return Pattern.matches(tokenRegex(), "" + charAt);
	}

	public static void register() {
		PreprocessCommand c = new DefCommand();
		Preprocessor.commands.put(c.getMnemonic(), c);
	}

}
