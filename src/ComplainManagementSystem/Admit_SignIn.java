package ComplainManagementSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/*Admint_SignIn 
  This class gets admin's emailID, password and approves sigin 
*/
final class Admit_SignIn implements ActionListener
{
    JFrame Login = new JFrame("Faculty Sign In");
    JLabel Login_label = new JLabel("Log In");
    JLabel email_label = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Register = new JLabel();
    JTextField email_text = new JTextField();
    JPasswordField password_text = new JPasswordField();
    JLabel image = new JLabel();
    JButton submit = new JButton();
    
    
    //Admit_SignIn constructor call submit_frame() methode
    
    Admit_SignIn()
    {
        submit_frame();
    }
    //submit_frame create Frame and display button 
    public void submit_frame()
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
        
        Login_label.setFont(new java.awt.Font("Tahoma", 1, 36));
        Login_label.setText("Sign In");
        Login_label.setFont(metropolis.deriveFont(40f));
        Login_label.setBounds(465,105,140,44);
        Login.add(Login_label);
        
        email_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_label.setText("Email Id");
        email_label.setFont(metropolis.deriveFont(25f));
        email_label.setBounds(465,179,310,25);
        Login.add(email_label);
       
        email_text.setFont(opensans.deriveFont(20f));
        email_text.setBounds(465,209,310,40);
        Login.add(email_text);
        
        //password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_label.setFont(metropolis.deriveFont(25f));
        password_label.setText("Password");
        password_label.setBounds(465,259,310,25);
        Login.add(password_label);
        
        password_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_text.setBounds(465,289,310,40);
        Login.add(password_text);
        
        
        ImageIcon register_hover = new ImageIcon(getClass().getResource("button_signin.png"));
        ImageIcon register_normal = new ImageIcon(getClass().getResource("button_signin(1).png"));
        JLabel signup_button = new JLabel();
        signup_button.setBounds(465,349,132,56);
        signup_button.setIcon(register_normal);
        Login.add(signup_button);
        
        signup_button.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {  
              Valid_Checker();
            }  
            public void mouseEntered(MouseEvent e) {
                signup_button.setIcon(register_hover);
            }
            public void mouseExited(MouseEvent e) {
                signup_button.setIcon(register_normal);
            }
        });
        
        ImageIcon signup_hover = new ImageIcon(getClass().getResource("button_signup.png"));
        ImageIcon signup_normal = new ImageIcon(getClass().getResource("button_signup(1).png"));
        JLabel signin_button = new JLabel();
        signin_button.setBounds(631,349,142,56);
        signin_button.setIcon(signup_normal);
        Login.add(signin_button);
        
        signin_button.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {  
              Register_page();
            }  
            public void mouseEntered(MouseEvent e) {
                signin_button.setIcon(signup_hover);
            }
            public void mouseExited(MouseEvent e) {
                signin_button.setIcon(signup_normal);
            }
        });
        
        
        ImageIcon image1 = new ImageIcon(getClass().getResource("signin.jpeg"));
        image.setIcon(image1);
        image.setBounds(60,110, 295, 350);
        Login.add(image);
        
        
        Login.setLayout(null);
        Login.getContentPane().setBackground(Color.white);
        Login.setSize(830,600);
        Login.setResizable(false);
        Login.setLocationRelativeTo(null);
        Login.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Login.setVisible(true);
    }    
    public void Register_page()
    {
        Admit_Signup Frame = new Admit_Signup();              //Creating for SignUp class
        this.Login.dispose();                                //Closing Login frame
    }
    
    @Override
    public void actionPerformed(ActionEvent a)
    {
        Valid_Checker();                          //checks whether the inputs are valid
    }
    /* Valid_Checker methode 
    * checks whether the input of email_text and password_text is not NULL,
    * IF NUll , Display error Message
    * ELSE , Call Database_Check methode
    */
    public void Valid_Checker()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
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
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        JLabel enter_mail = new JLabel("Enter the Email ID");
        enter_mail.setFont(metropolis.deriveFont(15f));
        JLabel enter_password = new JLabel("Enter the Password");
        enter_password.setFont(metropolis.deriveFont(15f));
        JLabel invalid = new JLabel("Invalid Email And Password");
        invalid.setFont(metropolis.deriveFont(15f));
        
        if("".equals(email))
            JOptionPane.showMessageDialog(Login,enter_mail,"Error",JOptionPane.ERROR_MESSAGE);
        else if("".equals(Password))
            JOptionPane.showMessageDialog(Login,enter_password,"Error",JOptionPane.ERROR_MESSAGE);
        else if(Database_Check()){
            JOptionPane.showMessageDialog(Login,invalid,"Error",JOptionPane.ERROR_MESSAGE);
        }else{
            new Admin_main_frame();                     
            this.Login.dispose(); 
        }
    }
    /* Database_Check Methode
    *   Checks whether the entered email and password matches with database
    *   If Matches , Calls Admin_main_frame
    *   ELSE , Displays Error Message
    */
    public boolean Database_Check()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            System.out.println("Connection has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from admin_database where Email='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next())                              
            {
                if(Password.equals(rs.getString("Password"))){
                    return false;                      //closes Login Frame
                }
            }
            conn.close();
        }catch (ClassNotFoundException | SQLException | HeadlessException e){
            System.out.println(e);
        } 
        return true;
    }
}