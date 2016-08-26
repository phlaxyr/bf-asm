package bfasm.generators;

public final class Optimizer {
	
	private Optimizer() {}
	
	public static String removeRedundancy(String inp) {
		StringBuilder sb = new StringBuilder(" ");
		for(char c : inp.toCharArray()) {
			if((c == '>' && sb.charAt(sb.length() - 1) == '<') || (c == '<' && sb.charAt(sb.length() - 1) == '>'))
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
