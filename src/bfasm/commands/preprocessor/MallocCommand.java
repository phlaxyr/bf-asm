package bfasm.commands.preprocessor;

import bfasm.generators.Preprocessor;

public class MallocCommand extends DefCommand {

	public int icount = 0;
	
	@Override
	public String getMnemonic() {
		return "#MALLOC";
	}
	
	@Override
	public String process(String line) {
		if(line.startsWith(getMnemonic())) {
			definitions.put(line.split(" ", 3)[1], "" + icount);
			icount++;
			
			line = "";
		} else {
			line = super.process(line);
		}
		
		return line;
	}

	
	public static void register() {
		PreprocessCommand c = new MallocCommand();
		Preprocessor.commands.put(c.getMnemonic(), c);
	}
}
