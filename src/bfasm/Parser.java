package bfasm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import bfasm.commands.*;
import bfasm.generators.Preprocessor;


public class Parser {

	int lblnum = 1;
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
		IncCommand.register();
		
		Preprocessor.register();
	}
	
	public String getBf() {
		HashMap<String, Integer> lblnames = new HashMap<>();
		ArrayList<Command> cmds = new ArrayList<>();
		
		// TODO: Clean up ugly mess that allows for LBL
		
		
		ArrayList<String> cmdstrs = cmdStr(fscan, lblnames);
		
		
		for(String line : cmdstrs) {
			cmds.add(Command.getCommand(line, lblnames));
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
	
	public ArrayList<String> cmdStr(Scanner fscan, HashMap<String, Integer> lblnames) {
		ArrayList<String> cmdstrs = new ArrayList<>();
		while(fscan.hasNext()) {
			
			String line = Preprocessor.preParse(fscan.nextLine().trim());
			
			if(line.isEmpty())
				continue;
			

			System.out.println(line);
			if(line.startsWith("LBL ")) {
				String a1 = line.split(" ", 3)[1].trim();
				if(!a1.matches("-?\\d+(\\.\\d+)?"))
					lblnames.put(a1, lblnum++);
				else
					lblnames.put(a1, Integer.parseInt(a1));
			}
			
			if(line.startsWith("#INCLUDE ")) {
				
				try {
					cmdstrs.addAll(cmdStr(new Scanner(new File(line.split(" ", 2)[1])),
							lblnames));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				continue;
			} else cmdstrs.add(line);
		}
		
		return cmdstrs;
	}
}
