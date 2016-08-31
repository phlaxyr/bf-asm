package bfasm.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;

import bfasm.generators.AddrGen;

public abstract class Command {
	private static final ArrayList<Command> commands = new ArrayList<>();
	
	public Command(int[] args) {
		setArgs(args);
	}
	
	public String getBf() { return getBf(new AddrGen()); }
	
	public abstract String getBf(AddrGen ag);
	
	public abstract String getMnemonic();

	public abstract int[] getArgs();
	
	public abstract Command setArgs(int[] args);
	
	public static final void registerCommand(Command c) {
		commands.add(c);
	}
	
	public static final ArrayList<Command> getCommands() {
		return commands;
	}
	
	public static final Command getCommand(String line, HashMap<String, Integer> lblnames) {
		
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
		
		int[] args = cmd.toArgs(str, lblnames);
		
		return cmd.getClone(args);
	}
	
	public int[] toArgs(String[] str, HashMap<String, Integer> lblnames) {
		int[] args = new int[str.length - 1];
		
		for(int i = 1; i < str.length; i++)
			args[i - 1] = Integer.parseInt(str[i]);
		
		return args;
	}
	
	public abstract Command getClone(int[] args);
	
	@Override
	public String toString() {
		Formatter formatter = new Formatter();
		String s = formatter.format("%s %s", this.getMnemonic(), Arrays.toString(this.getArgs())).toString();
		formatter.close();
		return s;
	}

	public void setLabels(ArrayList<LblCommand> labels) { }
}
