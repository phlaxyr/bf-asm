package bfasm.commands;

public class JifCommand extends Command {
	
	int mem, to;
	
	private JifCommand() {
		this(new int[]{0, 0,});
	}

	public JifCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMnemonic() {
		return "JIF";
	}

	@Override
	public int[] getArgs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command setArgs(int[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getClone(int[] args) {
		return new JifCommand(args);
	}
	
	public void register() {
		Command.registerCommand(new JifCommand());
	}

}
