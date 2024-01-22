class Account {
    // attributer til klasse konto
    private long accountnr;
    private String name;
    private double saldo;
    // konstruktør #1
    public Account (long accountnr, String name, double saldo){
        this.accountnr = accountnr;
        this.name = name;
        this.saldo = saldo;
    }
    // konstruktør #2
    public Account (long accountnr, String name){
        this.accountnr = accountnr;
        this.name = name;
        this.saldo = 0;
    }
    public long getAccountnr() {
        return accountnr;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getName() {
        return name;
    }
    public void doTransaction(double amount) {
        saldo += amount;
        //saldo = saldo + amount
    }
    public String toString() {
        return accountnr + " " + name + " " + saldo;
    }
    class eks2 {
        public static void main(String[] args) {
            // Oppretter et objekt av klassen eks2
            Account olesAccount = new Account(123456676756L, "Ole Olsen", 2300.50);
            Account alisAccount = new Account(12312312312L, "Ali");
            // Setter inn 100 kroner
            olesAccount.doTransaction(1000.0);
            alisAccount.doTransaction(50.5);

            // Spør objektet om saldo
            double saldo = olesAccount.getSaldo();

            // Skriver ut ny saldo
            System.out.println("Ny saldo er: " + saldo);
            System.out.println(olesAccount);
            System.out.println(alisAccount);
        }
    }
}