package bfasm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	}	
}
