package bfasm.commands;

import bfasm.generators.AddrGen;

public class MovCommand extends Command {

	private int arg0;
	private int arg1;

	private MovCommand() {
		super(new int[]{0,0});
	}
	
	public MovCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(AddrGen.doAt(sb, "[-", AddrGen.getDataCell(arg0)));
		sb.append(AddrGen.doAt(sb, "+", AddrGen.getDataCell(arg1)));
		sb.append(AddrGen.doAt(sb, "+]", AddrGen.getTempCell(arg0)));
		sb.append(AddrGen.doAt(sb, "[-<+>]", AddrGen.getTempCell(arg0)));
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "MOV";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0,arg1};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 2)
			throw new RuntimeException("Incorrect number of arguments to SET! Expected 2, got "+args.length);
		
		this.arg0 = args[0];
		this.arg1 = args[1];
		
		return this;
	}

	@Override
	public Command getClone() {
		return new MovCommand();
	}

	public static void register() {
		Command.registerCommand(new MovCommand());
	}
	
}
