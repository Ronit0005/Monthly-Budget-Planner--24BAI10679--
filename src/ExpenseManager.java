import java.io.*;
import java.util.*;
public class ExpenseManager {
    public void addexpense(String username, double amount, String note){
        String fname="expenses_"+username+".txt";
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fname, true))){
            bw.write(amount+" "+note.replace("|"," "));
            bw.newLine();
        }catch(Exception e){
            System.out.println("Error saving expense");
        }
    }
    public double totalspent(String username){
        String fname="expenses_"+username+".txt";
        File f=new File(fname);
        if (!f.exists()) return 0;
        double total=0;
        try(BufferedReader br=new BufferedReader(new FileReader(f))){
            String line;
            while((line=br.readLine())!=null){
                String[] p=line.split(" ",2);
                total+=Double.parseDouble(p[0]);
            }
        }catch(Exception e){
            System.out.println("Error reading expenses");
        }
        return total;
    }
    public void printexpenses(String username){
        String fname="expenses_"+username+".txt";
        File f=new File(fname);
        if(!f.exists()){
            System.out.println("No expenses recorder yet");
            return;
        }
        System.out.println("Expenses:");
        try(BufferedReader br=new BufferedReader(new FileReader(f))){
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch(Exception e){
            System.out.println("Error reading expenses");
        }
    }
}
