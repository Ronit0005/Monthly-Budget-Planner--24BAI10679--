import java.util.*;
public class Main {
      public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ProfileManager pm=new ProfileManager();
        ExpenseManager em=new ExpenseManager();
        while(true){
            System.out.println("Monthly Budget Planner:");
            System.out.println("1. Create New Profile");
            System.out.println("2. Select Existing Profile");
            System.out.println("3. Exit");
            System.out.println("Choose: ");
            int choice=sc.nextInt();
            sc.nextLine();
            if(choice==1){
                createprofile(sc,pm);
            }
            else if(choice==2){
                selectprofile(sc,pm,em);
            }
            else{
                System.out.println("Goodbye!");
                break;
            }
        }
      }
      static void createprofile(Scanner sc, ProfileManager pm){
        System.out.print("Enter name: ");
        String name=sc.nextLine();
        System.out.print("Job: ");
        String prof=sc.nextLine();
        System.out.print("Monthly Income: ");
        double income=sc.nextDouble();
        sc.nextLine();
        System.out.print("Goal amount(or 0): ");
        double goal=sc.nextDouble();
        sc.nextLine();
        System.out.print("Goal months(or 0): ");
        int months=sc.nextInt();
        sc.nextLine();
        pm.saveprofile(name, prof, income, goal, months);
        System.out.println("Profile created successfully");
      }
      static void selectprofile(Scanner sc, ProfileManager pm, ExpenseManager em){
        List<String> names=pm.getallPN();
        if(names.isEmpty()){
            System.out.println("No profiles found");
            return;
        }
        System.out.println("\nAvailable profiles:");
        for(int i=0; i<names.size(); i++){
            System.out.println((i+1)+". "+names.get(i));
        }
        System.out.print("Select: ");
        int choice=sc.nextInt();
        sc.nextLine();
        if (choice<1 || choice>names.size()){
            System.out.println("Invalid!");
            return;
        }
        String selected=names.get(choice-1);
        String[] p=pm.loadprofile(selected);
        if(p==null){
            System.out.println("Error loading profile!");
            return;
        }
        String name=p[0];
        String job=p[1];
        double income=Double.parseDouble(p[2]);
        double goalamt=Double.parseDouble(p[3]);
        int goalmonths=Integer.parseInt(p[4]);
        User u=new User(name, income);
        if (goalamt>0 && goalmonths>0){
            u.setgoal(new Goal(goalamt, goalmonths));
        }
        usermenu(sc,u,job,pm,em);
    }
    static void usermenu(Scanner sc, User u, String job, ProfileManager pm, ExpenseManager em){
        while(true){
            System.out.println("User Menu ("+u.getname()+")");
            System.out.println("1. View Budget");
            System.out.println("2. Add Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Update Income");
            System.out.println("5. Update Job");
            System.out.println("6. Update Goal");
            System.out.println("7. Back");
            System.out.print("Choose: ");
            int c=sc.nextInt();
            sc.nextLine();
            if(c==1){
                double base=u.getincome()*0.1;
                double needed=(u.getgoal()!=null)?u.getgoal().monthsave():0;
                double finalsave=Math.max(base,needed);
                Budget b=new Budget(u.getincome(), finalsave);
                b.print();
                System.out.println("Total spent: Rs."+em.totalspent(u.getname()));
            }
            else if(c==2){
                System.out.print("Amount: ");
                double amt=sc.nextDouble();
                sc.nextLine();
                System.out.print("Note: ");
                String note=sc.nextLine();
                em.addexpense(u.getname(), amt, note);
                System.out.println("Expense added!");
            }
            else if(c==3){
                em.printexpenses(u.getname());
            }
            else if(c==4){
                System.out.print("New income: ");
                double newincome=sc.nextDouble();
                sc.nextLine();
                u=new User(u.getname(), newincome);
                System.out.println("Income Updated!");
                pm.updateprofile(u.getname(), job, newincome, (u.getgoal()!=null?u.getgoal().getamount():0),(u.getgoal()!=null?u.getgoal().getmonths():0));
            }
            else if(c==5){
                System.out.print("New job: ");
                job=sc.nextLine();
                pm.updateprofile(u.getname(), job, u.getincome(), (u.getgoal()!=null?u.getgoal().getamount():0),(u.getgoal()!=null?u.getgoal().getmonths():0));
                System.out.println("Job Updated!");
            }
            else if(c==6){
                System.out.print("Goal amount: ");
                double gamt=sc.nextDouble();
                sc.nextLine();
                System.out.print("Goal months: ");
                int gmon=sc.nextInt();
                sc.nextLine();
                u.setgoal(new Goal(gamt, gmon));
                pm.updateprofile(u.getname(), job, u.getincome(), gamt, gmon);
                System.out.println("Goal Updated!");
            }
            else if(c==7){
                return;
            }
        }
    }
      
}
