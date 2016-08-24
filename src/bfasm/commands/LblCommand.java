package bfasm.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class LblCommand extends ParentCommand {
	
	ArrayList<Command> subcommands;
	
	static HashMap<Integer, Integer> uhm = new HashMap<>();
	static int curnum = 0;
	
	public int lblnum;
	
	private LblCommand() {
		super(new int[]{0});
	}
	
	public LblCommand(int[] args) {
		super(args);
		
		lblnum = curnum;
		uhm.put(curnum++, args[0]);
	}

	@Override
	public ArrayList<Command> getSubCommands() {
		return subcommands;
	}

	@Override
	public void setSubCommands(ArrayList<Command> commands) {
		subcommands = commands;

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
	public Command getClone() {
		return new LblCommand();
	}
	
	public static String wrapProgram(LblCommand[]labels) {
		StringBuilder sb = new StringBuilder();
		StringBuilder prepos = new StringBuilder();
		StringBuilder tmp = new StringBuilder();
		StringBuilder tmp2 = new StringBuilder();
		List<LblCommand> l = Arrays.asList(labels);
		Collections.sort(l, new Comparator<LblCommand>(){

			@Override
			public int compare(LblCommand arg0, LblCommand arg1) {
				return Integer.compare(arg0.lblnum, arg1.lblnum);
			}
			
		});
		
		for (int i = 0; i < labels.length; i++) {
			String label = l.get(i).getBf();
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
}
