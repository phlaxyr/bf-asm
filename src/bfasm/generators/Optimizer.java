package bfasm.generators;

public final class Optimizer {
	
	private Optimizer() {}
	
	//TODO: Optimize optimizer
	
	public static String removeRedundancy(String inp) {
		while(canBeOptimized(inp)) {
			inp = inp
				.replace("<>", "")
				.replace("><", "")
				.replace("+-", "")
				.replace("-+", "");
		}
		
		return inp.replaceAll(">+$", "")
				.replaceAll("<+$", "")
				.replaceAll("^>+", "")
				.replaceAll("^<+", "")
				.replaceAll("\\++$", "")
				.replaceAll("-+$", "");
	}
	
	public static boolean canBeOptimized(String inp) {
		return	inp.contains("><") ||
				inp.contains("<>") ||
				inp.contains("+-") ||
				inp.contains("-+")
				;
	}
	
}
