package bfasm.commands;

public class JezCommand extends Command {
	
	int mem, to_true, to_false;
	
	private JezCommand() {
		this(new int[]{0, 0, 0});
	}

	public JezCommand(int[] args) {
		super(args);
	}

	@Override
	public String getBf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMnemonic() {
		return "JEZ";
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
	public Command getClone() {
		return new JezCommand();
	}
	
	public void register() {
		Command.registerCommand(new JezCommand());
	}

}
