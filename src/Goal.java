public class Goal {
    private double amount;
    private int months;
    public Goal(double amount, int months){
        this.amount=amount;
        this.months=months;
    }
    public double getamount(){
        return amount;
    }
    public int getmonths(){
        return months;
    }
    public double monthsave(){
        if (months<=0) return 0;
        return amount/months;
    }
}
