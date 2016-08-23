package bfasm.generators;

public final class NumberGen {
	
	private NumberGen() {}
	
	public static final String getConstant(int inp) {
		//TODO: implement proper generation; current code is placeholder
		
		String ret = "";
		for(int i=0;i<inp;i++)
			ret += inp > 0 ? "+" : "-";
		return ret;
	}
}
