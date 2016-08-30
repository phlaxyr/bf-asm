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
		int t3 = 8 + Math.max(AddrGen.getTempCell(arg0), AddrGen.getTempCell(arg1));
		int b = 4 + Math.max(AddrGen.getTempCell(arg0), AddrGen.getTempCell(arg1));
		int a = AddrGen.getDataCell(arg0) + 1;
		
		AddrGen.doFormat(sb, "d0[->+>>+<<<]>>>[-<<<+>>>]<<<d1[-y+t2+d1]t2[-d1+t2]"	// Copy to a and b
				+ "z[-]x[t0+y[-t0[-]t1+y]t0[-z+t0]t1[-y+t1]y-x-]y[-]", 		// The actual grunt work
				"x", a, 
				"y", b, 
				"d0", AddrGen.getDataCell(arg0), 
				"d1", AddrGen.getDataCell(arg1), 
				"t0", t1, 
				"t2", t2,
				"t1", t3,
				"z", AddrGen.getDataCell(outp)
				);
		
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
