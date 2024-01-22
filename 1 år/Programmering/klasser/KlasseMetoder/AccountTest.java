package TestPackage;

import java.util.Objects;


public class AccountTest{

    private long id = 0;
    private String name = "";
    private double saldo = 0;


    public AccountTest(long id, String name, double saldo){

        this.id = id;
        this.saldo = saldo;
        this.name = name;

    }

    public long getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getSaldo(){
        return this.saldo;
    }

    @Override
    public String toString() {
        return "AccountTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTest)) return false;
        AccountTest that = (AccountTest) o;
        return id == that.id && Double.compare(that.getSaldo(), getSaldo()) == 0 && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getSaldo());
    }
}