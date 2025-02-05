package a05.e1;

public class BankAccountFactoryImpl implements BankAccountFactory {

    private class generic extends AbstractBankAccount {

        private int balance;

        public generic(int balance) {
            this.balance = balance;
        }

        @Override
        protected boolean canDeposit(int amount) {
            return true;
        }

        @Override
        protected void onDisallowedDeposit() {
            return;
        }

        @Override
        protected void onDisallowedWithdraw() {
            return;
        }

        @Override
        protected int newBalanceOnWithdraw(int amount) {
            this.balance = this.balance - amount;
            return this.balance;
        }

        @Override
        protected boolean canWithdraw(int amount) {
            return true;
        }

    }

    @Override
    public BankAccount simple() {
        return new generic(0);
    }

    @Override
    public BankAccount withFee(int fee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withFee'");
    }

    @Override
    public BankAccount checked() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checked'");
    }

    @Override
    public BankAccount gettingBlocked() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gettingBlocked'");
    }

    @Override
    public BankAccount pool(BankAccount primary, BankAccount secondary) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pool'");
    }

}
