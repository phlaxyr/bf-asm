package bfasm.commands;

import bfasm.generators.AddrGen;

public class NotCommand extends Command {

	private int arg0;
	private int arg1;
	
	private NotCommand() {
		super(new int[]{0,0});
	}
	
	public NotCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf(AddrGen ag) {
		StringBuilder sb = new StringBuilder();
		
		AddrGen.doFormat(ag, sb, "a[->+<t1[-]+a]t0[-<+>]t1-[+<+>]", 
				"a", AddrGen.getDataCell(arg0),
				"b", AddrGen.getDataCell(arg1),
				"t0", AddrGen.getTempCell(arg0),
				"t1", AddrGen.getTempCell(arg1));
		
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "NOT";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0, arg1};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 2)
			throw new RuntimeException("Incorrect number of arguments to NOT! Expected 2, got "+args.length);
		
		this.arg0 = args[0];
		this.arg1 = args[1];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new NotCommand(args);
	}

	public static void register() {
		Command.registerCommand(new NotCommand());
	}

}
