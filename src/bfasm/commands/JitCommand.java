package bfasm.commands;

public class JitCommand extends Command {
	
	public int mem, to;
	
	private JitCommand() {
		this(new int[]{0,0});
	}
	
	public JitCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		return null;
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
