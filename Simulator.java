import java.util.Scanner;

//import ATM.CardReader;

import java.io.File;

public class Simulator {
	static String[] input,last;
	static ATM atm;//=new ATM();
	//static CardReader cardRead;
	static int accountNum=0;
	public Simulator(){
	
	}
	public static void parse(String lineInput) throws NumberFormatException, Exception{
		input=lineInput.split(" ");
		//CARDREADER
		if(input[0].equals("CARDREADER")){
			System.out.print("");
			atm = new ATM();
			atm.cardRead(Integer.parseInt(input[1]));
		}
		
		//NUM
		else if(input[0].equals("NUM"))
			atm.number(Integer.parseInt(input[1]));			
		
		//DIS
		else if(input[0].equals("DIS")){
			for(int i=1;i<input.length;i++)
				System.out.print(input[i]+" ");
				System.out.println();
		}
		
		//PRINT
		else if(input[0].equals("PRINT")){
			String temp="";
			for(int i=1;i<input.length;i++)
				temp=temp+input[i]+" ";
			
			atm.print(temp);
		}
		//BUTTON
		else if(input[0].equals("BUTTON")){
			if(input[1].equals("CANCEL"))
				atm = new ATM();
			else
				atm.button(input[1]);
		}
		
		//INVALID CASES
		else{
			input=last;
			for(int i=1;i<input.length;i++)
				System.out.print(input[i]+" ");
				System.out.println(); 
		}
		last=input;
	}
	public static void main(String[] args) throws Exception{
		String lineInput; 
		ATM atm = new ATM();
		Scanner stdIn;
		
		if (args.length > 0 ){
			stdIn = new Scanner(new File(args[0]));
		}
		else{
			stdIn = new Scanner(System.in);
		}
		while(stdIn.hasNextLine()){
			lineInput=stdIn.nextLine();
			parse(lineInput);
		}
	}
}
