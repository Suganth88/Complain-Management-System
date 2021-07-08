package ComplainManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.time.LocalDate;
import javax.mail.MessagingException;

public class Mail_Concept_Common{
        final String username = "complainmangementsystem@gmail.com";  //admin mail id
        final String password = "#1Complain";                //admin password
        ResultSet rs1;
        Statement s1;
        Connection con1;
        PreparedStatement ps;
   public Mail_Concept_Common(String temp_name,String temp_rollno,String temp_phoneno,String temp_mail_id,String temp_mo,String temp_desc,String temp_loc,String temp_date){
       try{
                     
                Class.forName("com.mysql.jdbc.Driver");
                Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");                
                s1 = con1.createStatement();
                System.out.println("project.Mail_Concept_Common.<init>()");
        }
        catch(Exception ew){
                    System.out.println(ew);
        }
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("18tucs234@skct.edu.in"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("18tucs234@skct.edu.in")
            );
            message.setSubject(temp_mo+" Found");
            message.setText("Hello Students \n\tA "
                    +temp_mo+" have been found in "+temp_loc+" on "+temp_date+
                    "\nRespective owners come and collect ASAP");
            Transport.send(message);
            Connection con1=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp", "project","livends@2020");   
            //inseting newly found object detail into database
            ps = con1.prepareStatement("insert into "
                    + "missing_object values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,temp_name);
            ps.setString(2,temp_rollno);
            ps.setString(3,temp_phoneno);
            ps.setString(4,temp_mail_id);
            ps.setString(5,temp_mo);
            ps.setString(6,temp_desc);
            ps.setString(7,temp_loc);
            ps.setDate(8,Date.valueOf(LocalDate.now()));
            ps.setString(9,"Found and Notified");
            ps.setString(10, "Not Specified");
            ps.executeUpdate();
        } catch (MessagingException | SQLException e) {
            System.out.println(e);
        }
   }
}