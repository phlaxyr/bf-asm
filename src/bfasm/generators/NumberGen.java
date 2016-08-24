package bfasm.generators;

import javafx.util.Pair;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public final class NumberGen {
	
	private NumberGen() {}
	
	public static final String getConstant(int n) {
		//TODO: implement adding c

		int ab[] = getLowestFactors(n);
		int a = ab[0]; int b = ab[1];

		StringBuilder result = new StringBuilder("");
		result.append(">");
		result.append(new String(new char[a]).replace("\0", "+")); // repeating + a times
		result.append("[<");
		result.append(new String(new char[b]).replace("\0", n > 0 ? "+" : "-"));
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
}
