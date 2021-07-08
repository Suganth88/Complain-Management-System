/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComplainManagementSystem;
import java.io.Serializable;
import java.time.LocalDate;
import javax.swing.JLabel;
public class Beans implements Serializable{
    public  static String str_name,str_rollno,str_email,str_complaint;
    static JLabel main_heading,lable_time=new JLabel();
    static LocalDate today=null;
    public void setStr_name(String str_name) {
        this.str_name = str_name;
    }
    
    public String getStr_name() {
        return str_name;
    }
    
    public void setStr_rollno(String str_rollno) {
        this.str_rollno = str_rollno;
    }
   
    public String getStr_rollno() {
        return str_rollno;
    }
    public void setStr_email(String str_email) {
        this.str_email = str_email;
    }
    public String getStr_email() {
        return str_email;
    }
    public void setMain_heading(JLabel main_heading) {
        this.main_heading = main_heading;
    }
    public JLabel getMain_heading() {
        return main_heading;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public LocalDate getToday() {
        return today;
    }

    public static void setStr_complaint(String str_complaint) {
        Beans.str_complaint = str_complaint;
    }

    public static String getStr_complaint() {
        return str_complaint;
    }
    
}

