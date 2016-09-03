package bfasm.commands;

import java.util.Arrays;
import java.util.HashMap;

import bfasm.Parser;
import bfasm.generators.AddrGen;

public abstract class BranchingCommand extends Command {

	public BranchingCommand(int[] args) {
		super(args);
	}

	public abstract int jmpPos();
	
	@Override
	public int[] toArgs(String[] str, HashMap<String, Integer> lblnames) {
		int[] args = new int[str.length - 1];
		
		System.out.println(lblnames);
		
		System.out.println(Arrays.toString(str));
		
		for(int i = 1; i < str.length; i++) {
			if(i != jmpPos() + 1)
				args[i - 1] = Integer.parseInt(str[i]);
		}
		
		if(!str[jmpPos() + 1].matches("-?\\d+(\\.\\d+)?")) {
			args[jmpPos()] = lblnames.get(str[jmpPos() + 1]);
		} else {
			args = super.toArgs(str, lblnames);
		}
		
		return args;
	}
}
