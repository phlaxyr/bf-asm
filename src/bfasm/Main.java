package bfasm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bfasm.commands.Command;
import bfasm.commands.MovCommand;
import bfasm.commands.SetCommand;
import bfasm.generators.NumberGen;
import bfasm.commands.LblCommand;
import bfasm.generators.Optimizer;

public class Main {
	public static void main(String[] args) {
		
		Parser.init();
		
		
		Parser p = new Parser(new Scanner(""));
		try {
			p = new Parser(new Scanner(new FileInputStream("testfile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String command = p.getBf();
		System.out.println(Optimizer.removeRedundancy(command));
		String test = NumberGen.getConstant(564);
		System.out.println(test);
	}	
}
