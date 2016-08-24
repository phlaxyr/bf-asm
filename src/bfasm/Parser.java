package bfasm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import bfasm.commands.AddCommand;
import bfasm.commands.Command;
import bfasm.commands.HltCommand;
import bfasm.commands.JitCommand;
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
		JitCommand.register();
		HltCommand.register();
	}
	
	public String getBf() {
		
		ArrayList<Command> cmds = new ArrayList<>();
		
		while(fscan.hasNext()) {
			String line = fscan.nextLine();
			Command c = Command.getCommand(line);
			
			cmds.add(c);
		}
		
		ArrayList<LblCommand> labels = new ArrayList<>();
		LblCommand recent = null;
		
		for(Command c : cmds) {
			System.out.println(c);
			
			if(c instanceof LblCommand) {
				
				//TODO: Binary insertion to replace ugly inefficient code
				
				LblCommand lbcl = ((LblCommand) c);
				if(labels.size() == 0)
					labels.add(lbcl);
				else for (int i = 0; i < labels.size(); i++) {
					LblCommand lbc = labels.get(i);
					
					if(lbc.lblnum < lbcl.lblnum) {
						labels.add(i, lbcl);
						break;
					}
				}
				recent = lbcl;
			} else recent.addCommand(c);
		}
		
		Collections.reverse(labels);
		
		return LblCommand.wrapProgram(labels.toArray(new LblCommand[]{}));
	}
}
