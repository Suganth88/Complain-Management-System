package ComplainManagementSystem;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
/*Student
* 
*/
class Student_Signup 
{
    //Sign Up From
    JFrame main_frame = new JFrame("Student Sign Up");
    JButton Register = new JButton();
    JLabel signup = new JLabel();
    JLabel Roll_No = new JLabel();
    JLabel Name_label = new JLabel();
    JLabel email_label = new JLabel();
    JLabel Gender = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Repeat_password_label = new JLabel();
    JLabel image = new JLabel();
    JTextField Roll_No_Text = new JTextField();
    JTextField Your_name = new JTextField();
    JTextField Your_email = new JTextField();
    JPasswordField Your_password = new JPasswordField();
    JPasswordField Repeat_password = new JPasswordField();
    JLabel Go_Login_Frame = new JLabel();
    JRadioButton Male = new JRadioButton("Male");    
    JRadioButton Female = new JRadioButton("Female");   
    String username,email,password,rollno,gender; 
    //constructor
    Student_Signup()
    {
       Register_Frame();
    }
    public void Register_Frame()
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
        signup.setFont(metropolis.deriveFont(40f));
        signup.setText("Sign Up");
        signup.setBounds(260,0,242,52);
        main_frame.add(signup);
        
        Roll_No.setFont(metropolis.deriveFont(20f));
        Roll_No.setText("Roll No");
        Roll_No.setBounds(260,62,310,25);
        main_frame.add(Roll_No);
        
        Roll_No_Text.setFont(opensans.deriveFont(15f));
        Roll_No_Text.setBounds(260,92,310,30);
        main_frame.add(Roll_No_Text);
        
        Name_label.setFont(metropolis.deriveFont(20f));
        Name_label.setText("Name");
        Name_label.setBounds(260,147,310,25);
        main_frame.add(Name_label);
        
        Your_name.setFont(opensans.deriveFont(15f));
        Your_name.setBounds(260,177,310,30);
        main_frame.add(Your_name);
        
        email_label.setFont(metropolis.deriveFont(20f));
        email_label.setText("Email");
        email_label.setBounds(260,232,310,25);
        main_frame.add(email_label);
        
        Your_email.setFont(opensans.deriveFont(15f));
        Your_email.setBounds(260,262,310,30);
        main_frame.add(Your_email);
        
        Gender.setFont(metropolis.deriveFont(20f));
        Gender.setText("Gender");
        Gender.setBounds(260,317,310,25);
        main_frame.add(Gender);
        
        Male.setFont(metropolis.deriveFont(20f));
        Male.setBounds(265,347,100,30);
        Male.setBackground(Color.white);
        main_frame.add(Male);
        
        Female.setFont(metropolis.deriveFont(20f));
        Female.setBounds(450,347,155,30);
        Female.setBackground(Color.white);
        main_frame.add(Female);
        ButtonGroup bi = new ButtonGroup();
        bi.add(Male);
        bi.add(Female);
        
        password_label.setFont(metropolis.deriveFont(20f));
        password_label.setText("Password");
        password_label.setBounds(260,402,310,25);
        main_frame.add(password_label);
        
        Your_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Your_password.setBounds(260,432,310,30);
        main_frame.add(Your_password);
        
        Repeat_password_label.setFont(metropolis.deriveFont(20f));
        Repeat_password_label.setText("Repeat Your Password");
        Repeat_password_label.setBounds(260,487,310,25);
        main_frame.add(Repeat_password_label);
        
        Repeat_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Repeat_password.setBounds(260,517,310,30);
        main_frame.add(Repeat_password);
        
        ImageIcon signup_hover = new ImageIcon(getClass().getResource("button_signup.png"));
        ImageIcon signup_normal = new ImageIcon(getClass().getResource("button_signup(1).png"));
        JLabel signup_button = new JLabel();
        signup_button.setBounds(260,575,142,56);
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
        signin_button.setBounds(435,575,132,56);
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
        main_frame.setLayout(null);
        main_frame.setResizable(false);
        main_frame.setLocationRelativeTo(null);
        main_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }
    public void Valid_checker()
    {
        rollno = Roll_No_Text.getText();
        username = Your_name.getText();
        email = Your_email.getText();
        password = Your_password.getText();
        email = email.toLowerCase();
        String repeat_password = Repeat_password.getText();
        
        String skct_email_id = (email.contains("@")) ?
                email.substring(email.indexOf("@")+1).toLowerCase() : "";
        
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        
        if(Already_Exists_Rollno()){
            JOptionPane.showMessageDialog(main_frame,"Already Exists Rollno");
        }
        //check whether rollno is NULL
        else if("".equals(rollno)){
            JOptionPane.showMessageDialog(main_frame,"Enter The RollNo");
        }
        //check whether username is NULL
        else if("".equals(username)){
            JOptionPane.showMessageDialog(main_frame,"Enter The UserName");
        }
        //check whether email is NULL
        else if("".equals(email)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Email ID");
        }
        //validate email
        else if(!email.contains("@")){
            JOptionPane.showMessageDialog(main_frame,"Email Id Is Wrong");
        }
        //validate domain of mail id
        else if(!(skct_email_id.equals("skct.edu.in")
                || skct_email_id.equals("skct.edu.in.test-google-a.com")
                || skct_email_id.equals("skct.edu.in.test-google-a.com"))){
            JOptionPane.showMessageDialog(main_frame,"Doesn't Match Skct Email Id");
        }
        else if(Already_Exists_Email(email)){
            JOptionPane.showMessageDialog(main_frame,"Already Exists Email");
        }
        //check whether any one of gender is selected
        else if(!(Male.isSelected() || Female.isSelected())){
            JOptionPane.showMessageDialog(main_frame,"Enter The Gender");
        }
        //checks whether password is not NULL
        else if("".equals(password)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Password");
        }
        //checks whether repeated password is not NULL
        else if("".equals(repeat_password)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Repeat password");
        }
        //checks whether password and repeated password matches
        else if(!password.equals(repeat_password)){
            JOptionPane.showMessageDialog(main_frame,"Password Doesn't Match");
        }
        //checks password length
        else if( password.length() < 8)
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength "
                    + "Low\n - Almost 8 Lettter\n - Atleast One Special"
                    + " Character\n - Atleast One Lower and Upper Case\n "
                    + "- Atleast One Number");
        //checks password constraints 
        else if(!password.matches("(?=.*[0-9]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n"
                    + " - Almost 8 Lettter\n - Atleast One Special Character\n "
                    + "- Atleast One Lower and Upper Case\n - Atleast One Number");
        else if(!password.matches("(?=.*[a-z]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - "
                    + "Almost 8 Lettter\n - Atleast One Special Character\n -"
                    + " Atleast One Lower and Upper Case\n - Atleast One Number");
        else if(!password.matches("(?=.*[A-Z]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - Almost "
                    + "8 Lettter\n - Atleast One Special Character\n - Atleast One"
                    + " Lower and Upper Case\n - Atleast One Number");
        else{
            gender = (Male.isSelected()) ? "male" : "female";
            Student_Otp Otp = new Student_Otp();
            Otp.Otp_Frame(rollno,username, email, gender, password);
            this.main_frame.dispose();
        }
    }
    //checks whether the user has already registered with rollno
    public boolean Already_Exists_Rollno()
    {
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            //Creating Statements          
            Statement smt=conn.createStatement();
            String q = "Select * from student_database where Rollno='"+rollno+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                return true;
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        } 
        return false;
    }
     //checks whether the user has already registered with email
    public boolean Already_Exists_Email(String email)
    {
        try 
        {
            Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            System.out.println("Connection to SQLite has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from Student_Database where Email='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next())
                return true;
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        } 
        return false;
    }
    
    
    //dispose current page and opens signin frame
    public void Login_page(MouseEvent evt)
    {
        Student_Signin Frame = new Student_Signin();
        this.main_frame.dispose();
    }
    public static void main(String[] args) {
        new Student_Signup();
    }
}