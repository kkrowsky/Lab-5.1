import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ATM {
	boolean validated,cancel,withdrawl,checkBalance;
	Bank bank=new Bank();
	CashDispenser cash;
	CardReader reader;
	Printer printer;
	Bank.Account account;
	
	public int accountNumber,pin,amount;
	public ATM(){
		validated=false;
		withdrawl=false;
		cancel=false;
		checkBalance=false;
	}
	public void start() throws Exception {
		accountNumber=reader.accountNumber();
		Simulator.parse("DIS \"Enter Pin\"");
	}	
	
	
	public void cardRead(int read) throws Exception{
		reader = new CardReader(read);
		start();
	}
	public void number(int number) throws NumberFormatException, Exception{
		if(!validated){
			account=bank.validate(bank.new Account(accountNumber,number,0));
			if(account==null){
				Simulator.parse("DIS \"Enter Pin\"");
			}
			else{
				validated=true;
				Simulator.parse("DIS \"Choose Transaction\"");
			}
		}
		else if(withdrawl){
			this.amount=number;
			if(account.validate(amount)){
				cash=new CashDispenser(amount);
				cash.dispense();
				Simulator.parse("PRINT "+ new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())+" Withdrawl $"+amount);
			}
			else
				Simulator.parse("PRINT "+ new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())+" Invalid Withdrawl $"+amount);
			withdrawl=false;
			Simulator.parse("DIS \"Choose Transaction\"");
		}
	}
	public void print(String transaction){
		printer=new Printer(transaction);
		printer.print();
	}
	public void button(String button) throws NumberFormatException, Exception{
		if(button.equals("W")){
			withdrawl=true;
			Simulator.parse("DIS \"Amount?\"");
		}
		else if(button.equals("CB")){
			Simulator.parse("PRINT "+ new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())+" Check Balance $"+account.getBalance());
			Simulator.parse("DIS \"Choose Transaction\"");
		}
	}

	public class CardReader{
		int accountNumber;
		public CardReader(int accountNumber) throws Exception{
			this.accountNumber=accountNumber;
		}
		public int accountNumber() throws Exception{
			return accountNumber;
		}
	}
	public class Printer{
		String receipt;
		public Printer(String receipt){
			this.receipt=receipt;
		}
		public void print(){
			System.out.println("****Receipt****");
			System.out.println(receipt);
		}
	}
	public class CashDispenser{
		int amount;
		public CashDispenser(int amount){
			this.amount=amount;
		}
		public int dispense(){
			return account.withdrawl(amount);
		}
	}
	
}
