public class Budget {
    protected double savings;
    protected double food;
    protected double travel;
    protected double personal;
    protected double other;
    public Budget(double income, double savings){
        this.savings=savings;
        double remaining=income-savings;
        if (remaining<0) remaining=0;
        food=remaining*0.4;
        travel=remaining*0.2;
        personal=remaining*0.2;
        other=remaining*0.2;
    }
    public void print(){
        System.out.println("\nBudget Breakdown:");
        System.out.printf("Savings : Rs.%.2f\n", savings);
        System.out.printf("Food    : Rs.%.2f\n", food);
        System.out.printf("Travel  : Rs.%.2f\n", travel);
        System.out.printf("Personal: Rs.%.2f\n", personal);
        System.out.printf("Other   : Rs.%.2f\n", other);
    }
}
