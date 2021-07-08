package ComplainManagementSystem;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.UIManager;


/* SignUp 
    GET's admin register detail and invokes Admin_Otpframe
*/
final class Admit_Signup extends Admin_otpframe implements ActionListener
{
    JFrame main_frame = new JFrame("Faculty Sign Up");
    JButton jbutton_signup_register = new JButton();
    JLabel signup = new JLabel();
    JLabel label_signup_name = new JLabel();
    JLabel email_label = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Jpassword_signup_retypepassword_label = new JLabel();
    JLabel label_signup_image = new JLabel();
    JTextField jtextdfeild_signup_name = new JTextField();
    JTextField jtextfield_signup_email = new JTextField();
    JPasswordField Jpassword_signup_password = new JPasswordField();
    JPasswordField Jpassword_signup_retypepassword = new JPasswordField();
    JLabel Go_Login_Frame = new JLabel();
    String str_signup;                      
    String email;
    String password,rollno; 
  
    public Admit_Signup()                             //Constructor
    {
        
        Container con = main_frame.getContentPane();
        con.setLayout(null);
        
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
        signup.setFont(metropolis.deriveFont(40f));
        signup.setText("Sign Up");
        signup.setBounds(260,60,242,52);
        main_frame.add(signup);
        
        label_signup_name.setFont(metropolis.deriveFont(20f));
        label_signup_name.setText("Name");
        label_signup_name.setBounds(260,147,310,25);
        main_frame.add(label_signup_name);
        
        jtextdfeild_signup_name.setFont(opensans.deriveFont(15f));
        jtextdfeild_signup_name.setBounds(260,177,310,30);
        main_frame.add(jtextdfeild_signup_name);
        
        email_label.setFont(metropolis.deriveFont(20f));
        email_label.setText("Email");
        email_label.setBounds(260,232,310,25);
        main_frame.add(email_label);
        
        jtextfield_signup_email.setFont(opensans.deriveFont(15f));
        jtextfield_signup_email.setBounds(260,262,310,30);
        main_frame.add(jtextfield_signup_email);
        
        password_label.setFont(metropolis.deriveFont(20f));
        password_label.setText("Password");
        password_label.setBounds(260,317,310,25);
        main_frame.add(password_label);
        
        Jpassword_signup_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_password.setBounds(260,347,310,30);
        main_frame.add(Jpassword_signup_password);
        
        Jpassword_signup_retypepassword_label.setFont(metropolis.deriveFont(20f));
        Jpassword_signup_retypepassword_label.setText("Repeat Your Password");
        Jpassword_signup_retypepassword_label.setBounds(260,402,310,25);
        main_frame.add(Jpassword_signup_retypepassword_label);
        
        Jpassword_signup_retypepassword.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_retypepassword.setBounds(260,432,310,30);
        main_frame.add(Jpassword_signup_retypepassword);
      
        Jpassword_signup_retypepassword.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_retypepassword.setBounds(260,432,310,30);
        main_frame.add(Jpassword_signup_retypepassword);
        
        ImageIcon signup_hover = new ImageIcon(getClass().getResource("button_signup.png"));
        ImageIcon signup_normal = new ImageIcon(getClass().getResource("button_signup(1).png"));
        JLabel signup_button = new JLabel();
        signup_button.setBounds(260,502,142,56);
        signup_button.setIcon(signup_normal);
        main_frame.add(signup_button);
        
        signup_button.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {  
              Valid_checker();
            }  
            public void mouseEntered(MouseEvent e) {
                signup_button.setIcon(signup_hover);
            }
            public void mouseExited(MouseEvent e) {
                signup_button.setIcon(signup_normal);
            }
        });
        ImageIcon register_hover = new ImageIcon(getClass().getResource("button_signin.png"));
        ImageIcon register_normal = new ImageIcon(getClass().getResource("button_signin(1).png"));
        JLabel signin_button = new JLabel();
        signin_button.setBounds(435,502,132,56);
        signin_button.setIcon(register_normal);
        main_frame.add(signin_button);
        
        signin_button.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {  
               Login_page(e);
            }  
            public void mouseEntered(MouseEvent e) {
                signin_button.setIcon(register_hover);
            }
            public void mouseExited(MouseEvent e) {
                signin_button.setIcon(register_normal);
            }
        });
        
        main_frame.getContentPane().setBackground(Color.white);
        main_frame.setSize(830,730);
        main_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        main_frame.setResizable(false);
        main_frame.setLocationRelativeTo(null);
        main_frame.setVisible(true);
    }
    /* Valid_Checker Methode
    * Check whether user input's are vaild and not Null
    * IF not Valid or equal's NUll , Then Displays Error Message
    * ELSE , Then call Otp_frame Methode
    */
    public void Valid_checker()
    {
        str_signup = jtextdfeild_signup_name.getText();
        email = jtextfield_signup_email.getText();
        password = Jpassword_signup_password.getText();
        email = email.toLowerCase();
        String repeat_password = Jpassword_signup_retypepassword.getText();
        String skct_email_id = (email.contains("@")) ?
                email.substring(email.indexOf("@")+1).toLowerCase() : "";
        
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        if("".equals(str_signup))                                 //checks UserName whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The UserName");
          else if("".equals(email))                               //checks Useremail whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Email ID");
        else if(!email.contains("@"))                            //validates email for @sign
            JOptionPane.showMessageDialog(main_frame,"Email Id Is Wrong");
        else if(!(skct_email_id.equals("skct.edu.in")            //validates email for Domain
                    || skct_email_id.equals("skct.edu.in.test-google-a.com") 
                    || skct_email_id.equals("skct.edu.in.test-google-a.com"))){
                JOptionPane.showMessageDialog(main_frame,"Doesn't Match Skct Email Id");
        }
        else if(Already_Exists_Email()){                         //checks whether user already exists
            JOptionPane.showMessageDialog(main_frame,"Already Exists Email");
        }
        else if("".equals(password)){                            //Checks password whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Password");
        }
        else if("".equals(repeat_password)){                     //Checks repeat_password whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Repeat password");
        }
        else if(!password.equals(repeat_password)){             //Checks whether password and repeat_Password or Same                        
            JOptionPane.showMessageDialog(main_frame,"Password Doesn't Match");
        }
        else if( password.length() <= 8){                        //Validates password lenght
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n "
                    + "- Almost 8 Lettter\n - "
                    + "Atleast One Special Character\n - Atleast One Lower and "
                    + "Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[0-9]).*")){            //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n -"
                    + " Almost 8 Lettter\n -"
                    + " Atleast One Special Character\n - Atleast One Lower and"
                    + " Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[a-z]).*")){           //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n -"
                    + " Almost 8 Lettter\n -"
                    + " Atleast One Special Character\n - "
                    + "Atleast One Lower and "
                    + "Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[A-Z]).*")){           //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - "
                    + "Almost 8 Lettter\n - Atleast One Special Character\n - "
                    + "Atleast One Lower and Upper Case\n - Atleast One Number");
        }
        else{
            Admin_otpframe Otp = new Admin_otpframe();
            Otp.Otp_Frame(str_signup, email, password);
            this.main_frame.dispose();
        }
    }
    /*Already_Exist_Email
    * Check whether the entered email is already present in the database
    * @return False    ,  IF present
    * @return True     ,  ElSE
    */
    public boolean Already_Exists_Email()
    {
       
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            System.out.println("Connection to has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from admin_database where Email='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                return true;
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        } 
        return false;
    }    
    @Override
    public void actionPerformed(ActionEvent a) {   
        if(a.getSource().equals(jbutton_signup_register)){            
            Valid_checker();
        }
    }
    /*Login_page
    * invokes constructor of Admit_SignIn 
    */
    public void Login_page(MouseEvent evt){
        Admit_SignIn admit_SignIn = new Admit_SignIn();
        this.main_frame.dispose();
    }
}