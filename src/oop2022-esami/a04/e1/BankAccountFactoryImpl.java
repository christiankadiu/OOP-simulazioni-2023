package a04.e1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import javax.print.DocFlavor.INPUT_STREAM;

public class BankAccountFactoryImpl implements BankAccountFactory{

    @Override
    public BankAccount createBasic() {
        return new BankAccount() {

            int balance = 0;
            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance = this.balance + amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (this.balance - amount >= 0){
                    this.balance = this.balance - amount;
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
        return new BankAccount() {
            int balance = 0;
            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance = this.balance + amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (amount > 0 && this.balance > amount){
                    if (amount >= 35){
                        this.balance = this.balance - amount - feeFunction.apply(amount);
                        return true;
                    }else {
                        this.balance = this.balance - amount;
                        return true;
                    }   
                }
                return false;
            }
            
        };
    }

    @Override
    public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {
            int balance = 0;
            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance = this.balance + amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if ((amount > this.balance)){
                    if (allowedCredit.test(amount - this.balance)){
                        this.balance = this.balance - amount - rateFunction.apply(amount);
                        return true;
                    }else{
                        return false;
                    }
                }else {
                    this.balance = this.balance - amount;
                    return true;
                }        
            }
        };
    }

    @Override
    public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
        return new BankAccount() {
            int balance = 0;
            boolean blocked = false;
            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance = this.balance + amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (blocked){
                    return false;
                }else{
                    if (blockingPolicy.test(amount, this.balance)){
                        blocked = true;
                        return false;
                    }else{
                        this.balance = this.balance - amount;
                        return true;
                    }
                }
            }
            
        };
    }

    @Override
    public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
        UnaryOperator<Integer> rateFunction) {
    return new BankAccount() {
        int balance = 0;
        
        @Override
        public int getBalance() {
            return this.balance;
        }

        @Override
        public void deposit(int amount) {
            this.balance += amount;
        }

        @Override
        public boolean withdraw(int amount) {
            // Se il prelievo è maggiore o uguale a 35
            if (amount >= 35) {
                // Se il saldo non è sufficiente per coprire il prelievo
                if (amount > this.balance) {
                    // Calcolare il credito necessario
                    int creditRequired = amount - this.balance;

                    // Verifica che il credito sia consentito
                    if (allowedCredit.test(creditRequired) && this.balance + creditRequired <= 100) {
                        // Se il saldo è insufficiente e il credito è sufficiente,
                        // applica il prelievo, la tassa fissa e la tassa sul credito
                        this.balance = this.balance - amount - 1 - rateFunction.apply(amount);
                        return true;
                    } else {
                        // Il credito non è consentito
                        return false;
                    }
                } else {
                    // Se il saldo è sufficiente, preleva solo la somma con tassa fissa
                    this.balance = this.balance - amount - 1;
                    return true;
                }
            } else {
                // Se il prelievo è inferiore a 35, preleva senza tassa aggiuntiva
                this.balance = this.balance - amount;
                return true;
            }
        }
    };
}



        
}
