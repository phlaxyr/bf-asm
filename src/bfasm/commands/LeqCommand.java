package bfasm.commands;

import bfasm.generators.AddrGen;

public class LeqCommand extends Command {

	private int arg0;
	private int arg1;
	private int outp;
	
	private LeqCommand() {
		this(new int[]{0,0,0});
	}
	
	public LeqCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf(AddrGen ag) {
		return new GtrCommand(new int[]{arg1, arg0, outp}).getBf(ag);
	}

	@Override
	public String getMnemonic() {
		return "LEQ";
	}

	@Override
	public int[] getArgs() {
		return new int[]{arg0, arg1, outp};
	}

	@Override
	public Command setArgs(int[] args) {
		if(args.length != 3)
			throw new RuntimeException("Incorrect number of arguments to LEQ! Expected 3, got "+args.length);
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new LeqCommand(args);
	}
	
	public static void register() {
		Command.registerCommand(new LeqCommand());
	}

}
