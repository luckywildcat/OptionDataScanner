package com.Parser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
 * @author Carl von Havighorst
 * @description Option Data Parser to scan in and process Equity Options Data from Yahoo Finance.
 * 
 * Run this class for demo output.
 * 
 */
public class OptionParser {

	private static String URLTemplate = "https://finance.yahoo.com/quote/SYMBOL/options?p=SYMBOL";
	private String[] symbols;
	
	public static void main(String[] args) {
		OptionParser parser = new OptionParser(args);
		
		if (args == null || args.length ==0) {
			String[] demoSymbols = {"AAPL", "NFLX", "AMZN"};
			parser = new OptionParser(demoSymbols);
		}
		
		parser.runScan();
	}
	
	public OptionParser(String[] syms) {
		if (syms == null || syms.length ==0) {
			return;
		}
		
		this.symbols = syms;
	}
	
	public String runScan() {
		if(symbols == null || symbols.length == 0) {
			System.out.println("Symbols empty. Provide Symbols.");
		}
		String results = null;
		
		for(String currentSymbol : this.symbols) {
			try {
				URL optionDataURL = new URL(URLTemplate.replaceAll("SYMBOL", currentSymbol));
				System.out.println("Current URL: "+optionDataURL);

				String crtLine = null;
				URLConnection con1 = optionDataURL.openConnection();
				DataInputStream dis = new DataInputStream(con1.getInputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
				crtLine = br.readLine();
				while(crtLine != null){
	                System.out.print(crtLine);
	                crtLine = br.readLine();
	            }

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
}
