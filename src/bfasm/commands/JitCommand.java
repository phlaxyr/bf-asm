package bfasm.commands;

import java.util.ArrayList;

import bfasm.generators.AddrGen;

public class JitCommand extends Command implements BranchingCommand {
	
	public int mem, to;
	
	private ArrayList<LblCommand> labels;
	
	private JitCommand() {
		this(new int[]{0,0});
	}
	
	public JitCommand(int[] args) {
		super(args);
	}

	@Override
	public void setLabels(ArrayList<LblCommand> labels) {
		this.labels = labels;
	}
	
	@Override
	public String getBf(AddrGen ag) {
		StringBuilder sb = new StringBuilder();
		
		AddrGen.doFormat(ag, sb, "mem[->+<tmp[-]+mem]>[-<+>]<tmp[-lbl+tmp]", 
				"mem", AddrGen.getDataCell(mem),
				"tmp", AddrGen.getTempCell(mem + 1),
				"lbl", AddrGen.getLabelCell(to, labels));
		
		return sb.toString();
	}

	@Override
	public String getMnemonic() {
		return "JIT";
	}

	@Override
	public int[] getArgs() {
		return new int[] {mem, to};
	}

	@Override
	public Command setArgs(int[] args) {
		
		if(args.length != 2)
			throw new RuntimeException("Incorrect number of arguments to JIT! Expected 2, got "+args.length);
		
		this.mem = args[0];
		this.to = args[1];
		
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new JitCommand(args);
	}
	
	public static void register() {
		Command.registerCommand(new JitCommand());
	}

}
