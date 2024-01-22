
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 */
public class Employee {
    GregorianCalendar calendar = new java.util.GregorianCalendar();
    private Person personalDetails;

    private int employeeNr;
    private int yearHired;
    private float monthlyPayment;
    private float taxPercent;

    public Employee(Person personalDetails, int employeeNr, int yearHired, float monthlyPayment, float taxPercent){

        this.personalDetails = personalDetails;
        this.employeeNr = employeeNr;
        this.yearHired = yearHired;
        this.monthlyPayment = monthlyPayment;
        this.taxPercent = taxPercent;

    }

    public Employee(int employeeNr, int yearHired, float monthlyPayment, float taxPercent){

        this.employeeNr = employeeNr;
        this.yearHired = yearHired;
        this.monthlyPayment = monthlyPayment;
        this.taxPercent = taxPercent;

    }

    public Employee(Person personalDetails){
        this.personalDetails = personalDetails;
    }

    public float getMonthlyPayment() {
        return monthlyPayment;
    }

    public float getTaxPercent() {
        return taxPercent;
    }

    public int getEmployeeNr() {
        return employeeNr;
    }

    public int getYearHired() {
        return yearHired;
    }


    public void setMonthlyPayment(float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setTaxPercent(float taxPercent) {
        this.taxPercent = taxPercent;
    }


    public float taxCut(){
        return taxPercent/100 * monthlyPayment;
    }

    public float grossSalary(){
        return 12.0f*monthlyPayment;
    }

    public float taxCutYearly(){
        return 10.5f*taxCut();
    }

    public String name(){
        return personalDetails.getLastname()+", "+personalDetails.getFirstname()+";";
    }

    public int age(){
        int Year = calendar.get(Calendar.YEAR);
        return Year-personalDetails.getBirthyear();
    }

    /**
     *
     * @return
     */
    public int totalHiredYears(){
        return calendar.get(Calendar.YEAR)-yearHired;
    }

    public boolean isHiredYearsMore(int years){
        return years < totalHiredYears();
    }

    public void showDetails(){
        System.out.println("Name = "+name()+"\n");
        System.out.println("Nr emplyee number = "+getEmployeeNr()+"\n");
        System.out.println("Year hired = "+getYearHired()+"\n");
        System.out.println("Hired for = "+totalHiredYears()+" years"+"\n");
        System.out.println("Gross salary = "+grossSalary()+"kr"+"\n");
        System.out.println("Tax percent = "+getTaxPercent()+"\n");
        System.out.println("Yearly tax cut = "+taxCutYearly()+"kr"+"\n");
        System.out.println("was hired for more than 2 years = "+isHiredYearsMore(2)+"\n");
    }

    @Override
    public String toString() {
        return "Employee{" +

                "  personalDetails=" + personalDetails +
                ", EmployeeNr=" + employeeNr +
                ", yearHired=" + yearHired +
                ", monthlyPayment=" + monthlyPayment +
                ", taxPercent=" + taxPercent +
                '}';
    }
}
