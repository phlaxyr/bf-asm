package bfasm.commands;

import java.util.ArrayList;
import java.util.Arrays;

public class LblCommand extends ParentCommand {
	
	ArrayList<Command> subcommands = new ArrayList<>();
	
	public static ArrayList<LblCommand> uhm = new ArrayList<>();
	
	
	public int lblnum;
	
	private LblCommand() {
		super(new int[]{0});
	}
	
	public LblCommand(int[] args) {
		super(args);
		
		lblnum = args[0];
	}

	@Override
	public ArrayList<Command> getSubCommands() {
		return subcommands;
	}

	@Override
	public void setSubCommands(ArrayList<Command> commands) {
		subcommands = new ArrayList<>(commands);

	}

	@Override
	public void addCommand(Command command) {
		subcommands.add(command);
	}

	@Override
	public String getBf() {
		StringBuilder sb = new StringBuilder();
		
		for(Command c : subcommands)
			sb.append(c.getBf());
		
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "LBL";
	}

	@Override
	public int[] getArgs() {
		return new int[]{lblnum};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 1)
			throw new RuntimeException("Incorrect number of arguments to LBL! Expected 1, got "+args.length);
		
		this.lblnum = args[0];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new LblCommand(args);
	}
	
	public static String wrapProgram(LblCommand[]labels) {
		StringBuilder sb = new StringBuilder();
		StringBuilder prepos = new StringBuilder();
		StringBuilder tmp = new StringBuilder();
		StringBuilder tmp2 = new StringBuilder();

		
		System.out.println(Arrays.asList(labels));
		for (int i = 0; i < labels.length; i++) {
			System.out.println(labels[i].subcommands);
			String label = labels[i].getBf();
			prepos.append(">");

			tmp.append("<");
			tmp2.append(">");
			sb.append("<[");
			sb.append(tmp2);
			sb.append(label);
			sb.append(tmp);
			sb.append("-");
			sb.append("]");
			
		}
		prepos.deleteCharAt(prepos.length() - 1);
		prepos.append("+>+>+<[");
		prepos.append(sb);
		prepos.append(tmp2);
		prepos.append("]");
		return prepos.toString();
	}
	


	public static void register() {
		Command.registerCommand(new LblCommand());
	}
	
	protected static void clear() {
		uhm.clear();
	}
}
