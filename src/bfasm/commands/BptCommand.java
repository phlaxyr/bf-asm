package bfasm.commands;

public class BptCommand extends Command {

	private BptCommand() {
		this(new int[]{});
	}
	
	public BptCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		return "#";
	}

	@Override
	public String getMnemonic() {
		return "BPT";
	}

	@Override
	public int[] getArgs() {
		return new int[]{};
	}

	@Override
	public Command setArgs(int[] args) {
		if(args.length != 0)
			throw new RuntimeException("Incorrect number of arguments to BPT! Expected 0, got "+args.length);
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new BptCommand(args);
	}
	
	public static void register() {
		Command.registerCommand(new BptCommand());
	}

}
