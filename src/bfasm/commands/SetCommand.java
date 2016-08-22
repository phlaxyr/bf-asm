package bfasm.commands;

import bfasm.generators.AddrToGenerator;
import bfasm.generators.NumberConstantGenerator;

public class SetCommand extends Command {
	int arg0, arg1;
	
	private SetCommand() {
		super(new int[]{0,0});
	}
	
	public SetCommand(int[] args) {
		super(args);
	}
	
	@Override
	public String getMnemonic() {
		return "SET";
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
		return new SetCommand();
	}

	@Override
	public String getBf() {
		StringBuilder ret = new StringBuilder();

		ret.append(AddrToGenerator.getAddrTo(2 * arg0));
		
		ret.append(NumberConstantGenerator.getConstant(arg1));
		
		ret.append(AddrToGenerator.getAddrTo(-2 * arg0));
		
		return ret.toString();
	}

}
