package bfasm.commands;

import java.util.ArrayList;

public abstract class Command {
	private static final ArrayList<Command> commands = new ArrayList<>();
	
	public Command(int[] args) {
		setArgs(args);
	}
	
	public abstract String getBf();
	
	public abstract String getMnemonic();

	public abstract int[] getArgs();
	
	public abstract Command setArgs(int[] args);
	
	public static final void registerCommand(Command c) {
		commands.add(c);
	}
	
	public static final ArrayList<Command> getCommands() {
		return commands;
	}
	
	public static final Command getCommand(String line) {
		
		String[] str = line.split(" ");
		String mnemonic = null;
		Command cmd = null;
		for(Command c : commands) {
			if(str[0].equals(c.getMnemonic())) {
				mnemonic = str[0];
				cmd = c;
				break;
			}
		}
		
		if(mnemonic == null)
			throw new RuntimeException("Invalid mnemonic: " + str[0]);
		
		int[] args = new int[str.length - 1];
		for(int i = 1; i < str.length; i++)
			args[i - 1] = Integer.parseInt(str[i]);
		
		return cmd.getClone().setArgs(args);
	}
	
	public abstract Command getClone();
}
