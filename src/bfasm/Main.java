package bfasm;

import bfasm.commands.AddCommand;
import bfasm.commands.Command;
import bfasm.commands.MovCommand;
import bfasm.commands.SetCommand;
import bfasm.generators.NumberGen;
import bfasm.generators.Optimizer;

public class Main {
	public static void main(String[] args) {
		SetCommand.register();
		MovCommand.register();
		AddCommand.register();
		
		String command = ">+<"+Command.getCommand("SET 5 8")
				.getBf()+Command.getCommand("MOV 5 6")
				.getBf()+Command.getCommand("ADD 5 6")
				.getBf();

		System.out.println(command);
		System.out.println(Optimizer.removeRedundancy(command));
		String test = NumberGen.getConstant(564);
		System.out.println(test);
	}	
}
