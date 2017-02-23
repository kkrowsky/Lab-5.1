import java.util.ArrayList;
import java.util.List;

public class Bank{
		List<Account> accounts = new ArrayList<Account>();

		public Bank(){
			accounts.add(new Account(1234, 6789, 80));
			accounts.add(new Account(6789, 4321, 60));
		}
		public Account validate(Account a)throws Exception{
			int i=0;
			while(accounts.get(i).accountNumber!= a.accountNumber){
				i++;
				if(i>=accounts.size())
					throw new Exception("Not a Valid Account");
			}
			if(accounts.get(i).pinNumber==a.pinNumber)
				return accounts.get(i);
			else
				return null;
		}
		
		public class Account {
			int accountNumber;
			int pinNumber;
			int balance;
	
			public Account(int account, int pin, int balance){
				accountNumber=account;
				pinNumber=pin;
				this.balance=balance;
			}
			public Account(Account a){
				accountNumber=a.accountNumber;
				pinNumber=a.pinNumber;
				this.balance=a.balance;
			}
			public boolean validate(int amount){
				return amount<balance;
			}
			public int withdrawl(int amount){
				if(amount>balance){
					return balance;
				}
				balance=balance-amount;
				return balance;
			}
			public int deposit(int amount){
				balance=balance+amount;
				return balance;
			}
			public int getBalance(){
				return balance;
			}
		}
}