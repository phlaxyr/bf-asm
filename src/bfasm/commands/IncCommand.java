package bfasm.commands;

import bfasm.generators.AddrGen;
import bfasm.generators.NumberGen;

public class IncCommand extends Command {
	int arg0, arg1;
	
	private IncCommand() {
		super(new int[]{0,0});
	}
	
	public IncCommand(int[] args) {
		super(args);
	}
	
	@Override
	public String getMnemonic() {
		return "INC";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0,arg1};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 2)
			throw new RuntimeException("Incorrect number of arguments to INC! Expected 2, got "+args.length);
		
		this.arg0 = args[0];
		this.arg1 = args[1];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new IncCommand(args);
	}

	@Override
	public String getBf(AddrGen ag) {
		StringBuilder ret = new StringBuilder();

		ag.doNext(ret, NumberGen.getConstant(arg1), AddrGen.getDataCell(arg0));
		
		return ret.toString();
	}

	public static void register() {
		Command.registerCommand(new IncCommand());
	}

}
