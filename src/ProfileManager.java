import java.io.*;
import java.util.*;
public class ProfileManager {
    private static final String FILE="profiles.txt";
    public void saveprofile(String name, String job, double income, double goalamt, int goalmonths){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE, true))){
            bw.write(name+"|"+job+"|"+income+"|"+goalamt+"|"+goalmonths);
            bw.newLine();
        }
        catch(Exception e){
            System.out.println("Error saving profile");
        }
    }
    public List<String> getallPN(){
        List<String> list=new ArrayList<>();
        File f=new File(FILE);
        if(!f.exists()) return list;
        try (BufferedReader br=new BufferedReader(new FileReader(FILE))){
            String line;
            while((line=br.readLine())!=null){
                String[] p=line.split("\\|");
                if (p.length>=1) list.add(p[0]);
            }
        }
        catch (Exception e){
            System.out.println("Error loading profiles");
        }
        return list;
    }
    public String[] loadprofile(String name){
        File f=new File(FILE);
        if (!f.exists()) return null;
        try (BufferedReader br=new BufferedReader(new FileReader(FILE))){
            String line;
            while((line=br.readLine())!=null){
                String[] p=line.split("\\|");
                if (p[0].equals(name)){
                    return p;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error loading profile");
        }
        return null;
    }
    public void updateprofile(String name, String job, double income, double goalamt, int goalmonths){
        File original=new File(FILE);
        File temp=new File("temp_profiles.txt");
        try(
            BufferedReader br=new BufferedReader(new FileReader(original));
            BufferedWriter bw=new BufferedWriter(new FileWriter(temp))
        ){
           String line;
           while((line=br.readLine())!=null) {
            String[] p=line.split("\\|");
            if(p[0].equals(name)){
                bw.write(name+"|"+job+"|"+income+"|"+goalamt+"|"+goalmonths);
            }else{
                bw.write(line);
            }
            bw.newLine();
           }
        }catch(Exception e){
            System.out.println("Error updating profile");
        }
        original.delete();
        temp.renameTo(original);
    }
}
