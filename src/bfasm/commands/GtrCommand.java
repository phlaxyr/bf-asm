package bfasm.commands;

import bfasm.generators.AddrGen;

public class GtrCommand extends Command {

	private int arg0;
	private int arg1;
	private int outp;
	
	private GtrCommand() {
		super(new int[]{0,0,0});
	}
	
	public GtrCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		StringBuilder sb = new StringBuilder();

		int t1 = 2 + Math.max(AddrGen.getTempCell(arg0), AddrGen.getTempCell(arg1));
		int t2 = 6 + Math.max(AddrGen.getTempCell(arg0), AddrGen.getTempCell(arg1));
		int b = 4 + Math.max(AddrGen.getTempCell(arg0), AddrGen.getTempCell(arg1));
		int a = AddrGen.getDataCell(arg0) + 1;
		AddrGen ag = new AddrGen();

		ag.doNext(sb, "[->+>>+<<<]>>>[-<<<+>>>]<<<", AddrGen.getDataCell(arg0));
		ag.doNext(sb, "[-", AddrGen.getDataCell(arg1));
		ag.doNext(sb, "+", b);
		ag.doNext(sb, "+", t2);
		ag.doNext(sb, "]", AddrGen.getDataCell(arg1));
		ag.doNext(sb, "[-", t2);
		ag.doNext(sb, "+", AddrGen.getDataCell(arg1));
		ag.doNext(sb, "]", t2);
		
			/* TODO: Finish GTR */
		
		ag.reset(sb);
		
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "GTR";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0, arg1, outp};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 3)
			throw new RuntimeException("Incorrect number of arguments to GTR! Expected 3, got "+args.length);
		
		this.arg0 = args[0];
		this.arg1 = args[1];
		this.outp = args[2];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new GtrCommand(args);
	}

	public static void register() {
		Command.registerCommand(new GtrCommand());
	}

}
