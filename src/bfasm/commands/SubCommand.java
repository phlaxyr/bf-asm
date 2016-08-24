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
	public String getBf() {
		StringBuilder sb = new StringBuilder();
		AddrGen ag = new AddrGen();
		
		ag.doNext(sb, "[-", AddrGen.getDataCell(arg1));
		ag.doNext(sb, "-", AddrGen.getDataCell(arg0));
		ag.doNext(sb, "+", AddrGen.getTempCell(arg1));
		ag.doNext(sb, "]", AddrGen.getDataCell(arg1));
		ag.doNext(sb, "[-<+>]", AddrGen.getTempCell(arg1));
		ag.reset(sb);
		
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
