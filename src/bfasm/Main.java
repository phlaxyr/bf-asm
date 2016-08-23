package bfasm;

import bfasm.commands.Command;
import bfasm.commands.MovCommand;
import bfasm.commands.SetCommand;

public class Main {
	public static void main(String[] args) {
		SetCommand.register();
		MovCommand.register();
		
		String command = ">+<"+Command.getCommand("SET 5 5").getBf()+Command.getCommand("MOV 5 0").getBf();
		
		System.out.println(command);
	}
}
