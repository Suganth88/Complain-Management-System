/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComplainManagementSystem;
import java.awt.Component;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.sql.*;
import javax.swing.JOptionPane;
public class Notify_mail {
          final String username = "complainmangementsystem@gmail.com";
        final String password = "#1Complain";
        ResultSet rs1;
        Statement s1;
        Connection con1;
    private Component dialogbox_for_common_mail;
   public Notify_mail(String temp_name,
           String temp_mail_id,String temp_mo,
           String temp_loc,String temp_date,String temp_rollno){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            s1 = con1.createStatement();
            
        }
        catch(ClassNotFoundException | SQLException ew){
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
                    InternetAddress.parse(temp_mail_id)
            );
            message.setSubject(temp_mo+" have been Found");
            message.setText("Hello "+temp_name+"\n\tYour "
                    +temp_mo+" which have been lost in "+
                    temp_loc+" on "+temp_date+" have been found come and collect ASAP");
            Transport.send(message);
            //update info of the database
            s1.executeUpdate("update missing_object set Status "
                    + "='Found and Notified' where Roll_number = " 
                    + "'" + temp_rollno + "'");

        } catch(SendFailedException notify_mail_exception){
            //displays error message
            JOptionPane.showMessageDialog(dialogbox_for_common_mail,
                    "Select a complain and Try Again","Error",
                    JOptionPane.ERROR_MESSAGE);
        }catch (MessagingException | SQLException notify_mail_exception) {
            System.out.println(notify_mail_exception);
        }
   }
}
