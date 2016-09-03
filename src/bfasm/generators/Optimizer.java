package bfasm.generators;

public final class Optimizer {
	
	private Optimizer() {}
	
	public static String removeRedundancy(String inp) {
		// The single character here is an ugly, one-off fix. 
		StringBuilder sb = new StringBuilder(".");

		for(char c : inp.toCharArray()) {
			char prevc = sb.charAt(sb.length() - 1);
			if((c == '>' && prevc == '<') || (c == '<' && prevc == '>') ||
				(c == '+' && prevc == '-') || (c == '-' && prevc == '+'))
				sb = sb.deleteCharAt(sb.length() - 1);
			else
				sb.append(c);
		}
		
		sb = sb.deleteCharAt(0);
		
		String ret = null;
		
		for(int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if(c == '<') 
				continue;
			else {
				ret = sb.substring(i);
				break;
			}
		}
		
		for(int i = ret.length() - 1; i > 0; i--) {
			char c = ret.charAt(i);
			if(c == '<' || c == '>' || c == '+' || c == '-' || c == ',') 
				continue;
			else {
				ret = ret.substring(0, i + 1);
				break;
			}
		}
		
		return ret;
	}
	
	public static boolean canBeOptimized(String inp) {
		return	inp.contains("><") ||
				inp.contains("<>") ||
				inp.contains("+-") ||
				inp.contains("-+")
				;
	}
	
}
