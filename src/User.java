public class User{
    private String name;
    private double income;
    private Goal goal;
    public User(String name, double income){
        this.name=name;
        this.income=income;
    }
    public String getname(){
        return name;
    }
    public double getincome(){
        return income;
    }
    public void setgoal(Goal g){
        this.goal=g;
    }
    public Goal getgoal(){
        return goal;
    }
}