package ComplainManagementSystem;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/* OTP class
*  This class sends Otp to default admin and stores admin detail in database
*/
class Admin_otpframe implements ActionListener
{
    //otp Frame
    JFrame otp_frame = new JFrame("Enter OTP");
    JButton submit = new JButton();
    JButton Back = new JButton();
    JLabel otp = new JLabel();
    JLabel Resend = new JLabel();
    JTextField otp_number = new JTextField();
    static String Generate_Number = "";
    static String Name,Email,Password="Nil",Rollno="Nil"; 
    
    /*Otp_frame
    *  Create frame
    *  calls otp sender and Otp generator function 
    */
    public void Otp_Frame(String username,String email,String password)
    {
        Font metropolis = null;
        Font opensans = null;
        final InputStream metro = getClass().getResourceAsStream("Metropolis-Bold.otf");
        final InputStream open = getClass().getResourceAsStream("OpenSans-Regular.ttf");
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();  
     
            metropolis = Font.createFont(Font.TRUETYPE_FONT, metro);
            genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, metro));
            metropolis = metropolis.deriveFont(Font.PLAIN, 25);
            
        }catch(Exception e){
           
        }
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();  
            opensans = Font.createFont(Font.TRUETYPE_FONT, open);
            genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, open));
            opensans = opensans.deriveFont(Font.PLAIN, 25);
            
        }catch(Exception e){
            
        }
        Container con = otp_frame.getContentPane();
        con.setLayout(null);
        
        Name = username;
        Email = email;
        Password = password;
        
        otp.setFont(metropolis.deriveFont(40f));
        otp.setText("Enter the OTP");
        otp.setBounds(180,143,304,44);
        otp_frame.add(otp);
        
        otp_number.setFont(opensans.deriveFont(15f));
        otp_number.setText("");
        otp_number.setBounds(180,232,440,35);
        otp_frame.add(otp_number);
        
        ImageIcon resend_normal = new ImageIcon(getClass().getResource("button_resend.png"));
        ImageIcon resend_hover = new ImageIcon(getClass().getResource("button_resend (1).png"));
        JLabel resend_button = new JLabel();
        resend_button.setIcon(resend_normal);
        resend_button.setBounds(330, 300, 138, 76);
        otp_frame.add(resend_button);
        resend_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Resend_Otp();
            }
            public void mouseEntered(MouseEvent e) {
                resend_button.setIcon(resend_hover);
            }
            public void mouseExited(MouseEvent e) {
                resend_button.setIcon(resend_normal);
            }
        });
        
        ImageIcon submit_normal = new ImageIcon(getClass().getResource("button_submit.png"));
        ImageIcon submit_hover = new ImageIcon(getClass().getResource("button_submit (1).png"));
        JLabel submit_button = new JLabel();
        submit_button.setIcon(submit_normal);
        submit_button.setBounds(180, 300, 204, 76); 
        otp_frame.add(submit_button);
        submit_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Otp_Checker(Name,Email,Password);
            }
            public void mouseEntered(MouseEvent e) {
                submit_button.setIcon(submit_hover);
            }
            public void mouseExited(MouseEvent e) {
                submit_button.setIcon(submit_normal);
            }
        });
        
        ImageIcon back_normal = new ImageIcon(getClass().getResource("button_back.png"));
        ImageIcon back_hover = new ImageIcon(getClass().getResource("button_back (1).png"));
        JLabel back_button = new JLabel();
        back_button.setIcon(back_normal);
        back_button.setBounds(480, 300, 138, 76);
        otp_frame.add(back_button);
        back_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Student_Signup();
                otp_frame.dispose();
            }
            public void mouseEntered(MouseEvent e) {
                back_button.setIcon(back_hover);
            }
            public void mouseExited(MouseEvent e) {
                back_button.setIcon(back_normal);
            }
        });
        
        Generate_Number = Otp_Generate();            //return values from otp_generate is stores
        
        Otp_Sender(Generate_Number,Name);
        
        Back.addActionListener(this);
        submit.addActionListener(this);
         
        otp_frame.getContentPane().setBackground(Color.white);
        otp_frame.setSize(820,600);
        otp_frame.setLayout(null);
        otp_frame.setResizable(false);
        otp_frame.setLocationRelativeTo(null);
        otp_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        otp_frame.setVisible(true);
    }
    /*Opt_Checker
    * check whether Otp generated and enter are same
    * IF same , call Detail_Add_Database methode
    * ELSE , Displays Error
    */
    public void Otp_Checker(String Name,String email,String Password)
    { 
        String otp_num = otp_number.getText();
        if(otp_num.equals(Generate_Number))        //equal user user input and Generated otp
        {
            Detail_Add_Database(Name,email,Password);
            new  Admin_main_frame();
            this.otp_frame.dispose();
        }
        else{
         JOptionPane.showMessageDialog(otp_frame,"Invalid OTP Number");
        }
    }
    /* Otp_Generate
    * Create random string with integers
    * @return Generate_number
    */
    public String Otp_Generate()
    {
        Random rand = new Random();                 //
        for(int i=0;i<6;i++)
        {
            int otp_num = rand.nextInt(10);
            Generate_Number = Generate_Number + Integer.toString(otp_num);
        }
        return Generate_Number;
    }
    /* Otp_Sender
    *  Send otp to admin through gmail
    */
    public void Otp_Sender(String OTP,String name)
    {
       Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");     // setting simple mail transfer protocol 
        properties.setProperty("mail.smtp.port", "465");                // setting port value 465
        properties.put("mail.smtp.socketFactory.port", "465");          //
        properties.put("mail.smtp.socketFactory.class",                 //
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");          //setting authentication true 
 
        Session session = Session.getDefaultInstance(properties,
                new javax.mail. Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){ 
                        return new PasswordAuthentication("complainmangementsystem@gmail.com"
                                ,"#1Complain");
                    }
                });                                                         //authentication of user and password
        try 
        {
            MimeMessage message  = new MimeMessage(session);                
            message.setFrom(new InternetAddress("18tucs234@skct.edu.in"));   // setting from address 
            message.addRecipient(Message.RecipientType.TO,                  
                    new InternetAddress("Suganthguna88@gmail.com"));          // Setting to address
            message.setText("OTP for "+name+" is "+OTP);                       // text message
            message.setSubject("Complaint Management System - OTP for new Sign up");               // mail subject
            Transport.send((message));                                      // sends mail
            //System.out.println("Sent message successfully...");
        }
        catch (MessagingException max)
        {
            max.printStackTrace();
        }
    }
    /*Detail_Add_Database
     *add detail to the database
    */
    public void Detail_Add_Database(String Name,String email,String Password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");      
            System.out.println("Connection to SQLite has been established.");
            Statement  st = conn.createStatement();
            String query="insert into admin_database values('"+Name+"',"
                    + "'"+Email+"','"+Password+"')";
            st.executeUpdate(query);
            conn.close();
           
        }
        catch (Exception e)
        {
            System.out.println("error "+e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == submit)
            Otp_Checker(Name,Email,Password);
        else if(ae.getSource() == Back)
        {
            Admit_Signup s = new Admit_Signup();
            this.otp_frame.dispose();
        }
    }  
    /*Resent_Otp
    * resend send mail to admin   
    */
    public void Resend_Otp()
    {
        Generate_Number = "";
        Generate_Number = Otp_Generate();
        Otp_Sender(Generate_Number,Name);
    } 
}

   
