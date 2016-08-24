package bfasm.generators;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public final class NumberGen {
	
	private NumberGen() {}
	
	public static final String getConstant(int n) {
		//TODO: implement adding c

		int ab[] = getLowestFactors(n);
		int a = ab[0];
		int b = ab[1];
		
		String ch = (n > 0) ? "+" : "-";

		StringBuilder result = new StringBuilder();
		result.append(">");
		result.append(repeat(a,"+")); // repeating + a times
		result.append("[<");
		result.append(repeat(b, ch));
		result.append(">-]<");

		return result.toString();
	}

	public static final int[] getLowestFactors(int n) {
		int a = (int) floor(sqrt(n));
		while (a > 1) {
			if (n % a == 0) break;
			a--;
		}

		int b = n / a;
		return new int[]{a, b};
	}
	
	public static String repeat(int times, String tr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < times; i++)
			sb.append(tr);
		
		return sb.toString();
	}
}
