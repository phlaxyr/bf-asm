package bfasm.commands;

import java.util.ArrayList;

public interface BranchingCommand {
	public void setLabels(ArrayList<LblCommand> labels);
}
