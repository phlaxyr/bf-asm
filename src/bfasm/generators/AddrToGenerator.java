package bfasm.generators;

public final class AddrToGenerator {
	
	private AddrToGenerator() {}
	
	public static final String getAddrTo(int to) {
		char appendchar = (to > 0) ? '>' : '<';
		
		StringBuilder ret = new StringBuilder();
		
		for(int i=0;i<Math.abs(to);i++)
			ret.append(appendchar);
		
		return ret.toString();
	}
	
}
