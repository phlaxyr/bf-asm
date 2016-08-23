package bfasm.generators;

public final class AddrGen {
	
	private AddrGen() {}
	
	public static final String getAddrTo(int to) {
		return getAddrTo(0,to);
	}
	
	public static final String getAddrTo(int from, int to) {
		int move = from - to;
		
		char appendchar = (move > 0) ? '>' : '<';
		
		StringBuilder ret = new StringBuilder();
		
		for(int i=0;i<Math.abs(move);i++)
			ret.append(appendchar);
		
		return ret.toString();
	}
	
	public static final String getBackToStart() {
		return ">-[+<<-]+<";
	}
	
	public static int getDataCell(int cellnum) {
		return 2 * cellnum + 2;
	}
	
	public static int getTempCell(int cellnum) {
		return 2 * cellnum + 3;
	}
	
	public static StringBuilder doAt(StringBuilder sb, String ins, int to) {
		
		sb.append(AddrGen.getAddrTo(0, to));
		sb.append(ins);
		sb.append(AddrGen.getAddrTo(to, 0));
		
		return sb;
	}
}
