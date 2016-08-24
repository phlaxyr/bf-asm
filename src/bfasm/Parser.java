package bfasm;

import java.util.ArrayList;
import java.util.Scanner;

import bfasm.commands.AddCommand;
import bfasm.commands.Command;
import bfasm.commands.HltCommand;
import bfasm.commands.LblCommand;
import bfasm.commands.MovCommand;
import bfasm.commands.SetCommand;

public class Parser {
	Scanner fscan;
	
	public Parser(Scanner fscan) {
		this.fscan = fscan;
	}
	
	public static void init() {
		SetCommand.register();
		MovCommand.register();
		AddCommand.register();
		LblCommand.register();
		HltCommand.register();
	}
	
	public String getBf() {
		ArrayList<LblCommand> labels = new ArrayList<>();
		
		while(fscan.hasNext()) {
			String line = fscan.nextLine();
			Command c = Command.getCommand(line);
			
			if(!c.getMnemonic().equals("LBL"))
				labels.get(labels.size() - 1).addCommand(c);
			
			if(c.getMnemonic().equals("LBL")) {
				labels.add((LblCommand) c);
			}
		}
	
		return LblCommand.wrapProgram(labels.toArray(new LblCommand[]{}));
	}
}
