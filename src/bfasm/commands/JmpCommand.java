package bfasm.commands;

import java.util.ArrayList;
import java.util.Arrays;

import bfasm.generators.AddrGen;

public class JmpCommand extends Command implements BranchingCommand {
	int to_addr;
	ArrayList<LblCommand> labels;
	
	private JmpCommand() {
		super(new int[]{0});
	}
	
	public JmpCommand(int[] args) {
		super(args);
	}
	
	@Override
	public String getMnemonic() {
		return "JMP";
	}

	@Override
	public int[] getArgs() {
		return new int[]{to_addr};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 1)
			throw new RuntimeException("Incorrect number of arguments to JMP! Expected 1, got "+args.length+": "+Arrays.toString(args));
		
		this.to_addr = args[0];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new JmpCommand(args);
	}

	@Override
	public String getBf(AddrGen ag) {
		StringBuilder ret = new StringBuilder();

		ag.doNext(ret, "+", AddrGen.getLabelCell(to_addr, labels));
		
		return ret.toString();
	}

	public static void register() {
		Command.registerCommand(new JmpCommand());
	}
	
	@Override
	public void setLabels(ArrayList<LblCommand> labels) {
		this.labels = labels;
	}

}
