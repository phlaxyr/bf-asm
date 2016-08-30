package bfasm.generators;

import java.util.ArrayList;

import bfasm.commands.LblCommand;

public class AddrGen {
	private int lastaddr = 0;
	
	public AddrGen() {}
	
	public static final String getAddrTo(int to) {
		return getAddrTo(0,to);
	}
	
	public static final String getAddrTo(int from, int to) {
		int move = to - from;
		
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
	
	public static int getLabelCell(int cellnum, ArrayList<LblCommand> labels) {
		
		for(int i = 0;i < labels.size(); i++) {
			if(labels.get(i).lblnum == cellnum) {
				cellnum = i;
				break;
			}
		}
		
		return -cellnum-1;
	}
	
	public static StringBuilder doAt(StringBuilder sb, String ins, int to) {
		
		return doAt(sb, ins, 0, to);
	}
	
	public static StringBuilder doAt(StringBuilder sb, String ins, int from, int to) {
		
		
		sb.append(AddrGen.getAddrTo(from, to));
		sb.append(ins);
		sb.append(AddrGen.getAddrTo(to, from));
		
		return sb;
	}
	
	public StringBuilder doNext(StringBuilder sb, String ins, int to) {
		sb.append(AddrGen.getAddrTo(lastaddr, to));
		sb.append(ins);
		
		lastaddr = to;
		return sb;
	}
	
	public static StringBuilder doFormat(StringBuilder sb, String toformat, Object...inp) {
		ArrayList<String> inpstrings = new ArrayList<>();
		ArrayList<Integer> inptos = new ArrayList<>();
		
		// Get all the string / int pairs
		for(int i = 0; i < inp.length; i += 2) {
			inpstrings.add((String) inp[i]);
			inptos.add((int) inp[i + 1]);
		}
		
		// Cut string apart
		int lastsplit = 0;
		
		int laddr = 0;
		AddrGen ag = new AddrGen();
		for(int i = 0; i < toformat.length(); i++) {
			String s = toformat.substring(i);
			
			for (int j = 0; j < inpstrings.size(); j++) {
				String ft = inpstrings.get(j);
				if(s.startsWith(ft)) {
					
					// This is the stuff between the memory positions
					ag.doNext(sb, toformat.substring(lastsplit, i), laddr);
					
					// Remember this address for next time (we're taking the stuff before the address)
					laddr = inptos.get(j);
					
					// Jump forward so we don't re-read the processed parts
					lastsplit = (i += ft.length());
					
					break;
				}
			}
		}
		
		// If there's more stuff after the last address location, add it
		if(lastsplit != toformat.length())
			ag.doNext(sb, toformat.substring(lastsplit), laddr);
		
		ag.reset(sb);
		
		return sb;
	}
	
	public void reset(StringBuilder sb) {
		sb.append(AddrGen.getAddrTo(lastaddr, 0));
	}
}
