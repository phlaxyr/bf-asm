package bfasm.commands;

import bfasm.generators.AddrGen;

public class SubCommand extends Command {

	private int arg0;
	private int arg1;

	private SubCommand() {
		super(new int[]{0,0});
	}

	public SubCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf(AddrGen ag) {
		StringBuilder sb = new StringBuilder();
		
		AddrGen.doFormat(ag, sb, "b[-a-t+b]t[-<+>]", 
				"a", AddrGen.getDataCell(arg0),
				"b", AddrGen.getDataCell(arg1),
				"t", AddrGen.getTempCell(arg1)
				);
		
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "SUB";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0,arg1};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 2)
			throw new RuntimeException("Incorrect number of arguments to SUB! Expected 2, got "+args.length);
		
		this.arg0 = args[0];
		this.arg1 = args[1];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new SubCommand(args);
	}

	public static void register() {
		Command.registerCommand(new SubCommand());
	}

}
