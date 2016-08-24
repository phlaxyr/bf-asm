package bfasm.commands;

import java.util.ArrayList;

public abstract class ParentCommand extends Command {

	public ParentCommand(int[] args) {
		super(args);
	}

	public abstract ArrayList<Command> getSubCommands();
	
	public abstract void setSubCommands(ArrayList<Command> commands);
	
	public abstract void addCommand(Command command);

}
