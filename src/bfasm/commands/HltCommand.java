package bfasm.commands;

public class HltCommand extends Command {

	private HltCommand() {
		this(new int[]{});
	}
	
	public HltCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		return "[-]";
	}

	@Override
	public String getMnemonic() {
		return "HLT";
	}

	@Override
	public int[] getArgs() {
		return new int[]{};
	}

	@Override
	public Command setArgs(int[] args) {
		if(args.length != 0)
			throw new RuntimeException("Incorrect number of arguments to HLT! Expected 0, got "+args.length);
		return this;
	}

	@Override
	public Command getClone(int[] args) {
		return new HltCommand(args);
	}
	
	public static void register() {
		Command.registerCommand(new HltCommand());
	}

}
