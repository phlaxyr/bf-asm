package bfasm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import bfasm.commands.*;


public class Parser {
	Scanner fscan;
	
	public Parser(Scanner fscan) {
		this.fscan = fscan;
	}
	
	public static void init() {
		SetCommand.register();
		MovCommand.register();
		AddCommand.register();
		SubCommand.register();
		LblCommand.register();
		HltCommand.register();
		InpCommand.register();
		OutCommand.register();
		JitCommand.register();
		NotCommand.register();
		GtrCommand.register();
		BptCommand.register();
		JmpCommand.register();
		LeqCommand.register();
	}
	
	public String getBf() {
		
		ArrayList<Command> cmds = new ArrayList<>();
		
		while(fscan.hasNext()) {
			String line = fscan.nextLine().trim();
			
			if(line.isEmpty())
				continue;
			
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
			} else {
				try {
					c.setLabels(labels);
					recent.addCommand(c);
				} catch (NullPointerException e)  {
					System.out.println("Added implicit LBL 0");
					recent = new LblCommand(new int[]{0});
					recent.addCommand(c);

					labels.add(0, recent);
				}
			}
		}
		
		Collections.reverse(labels);
		
		return LblCommand.wrapProgram(labels.toArray(new LblCommand[]{}));
	}
}
