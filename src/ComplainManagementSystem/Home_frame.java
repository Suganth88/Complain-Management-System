package ComplainManagementSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class Home_frame 
{
    
    JFrame Page_Frame = new JFrame("Complaint Management System");
    JLabel label_student=new JLabel("Student");
    JLabel label_admin=new JLabel("Faculty");
    
    public Home_frame() {
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
        
        JLabel label_title=new JLabel();
        label_title.setText("Complaint Management System");
        label_title.setBounds(130, 100, 700, 60);
        label_title.setFont(metropolis.deriveFont(40f));
        Page_Frame.add(label_title);

        JLabel label_description = new JLabel();
        label_description.setText("Welcome to Complaint Management System choose your category and continue");
        label_description.setBounds(157, 160, 580, 30);
        label_description.setFont(opensans.deriveFont(15f));
        Page_Frame.add(label_description);
        
        ImageIcon admin_hover = new ImageIcon(getClass().getResource("button_faculty.png"));
        ImageIcon admin_normal = new ImageIcon(getClass().getResource("button_faculty (1).png"));
        label_admin.setIcon(admin_normal);
        label_admin.setFont(metropolis);
        label_admin.setBounds(187, 250, 230, 89);
        Page_Frame.add(label_admin);
        
        label_admin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              Admin_page();
            }  
            public void mouseEntered(MouseEvent e) {
                label_admin.setIcon(admin_hover);
            }
            public void mouseExited(MouseEvent e) {
                label_admin.setIcon(admin_normal);
            } 
        } );
        
        ImageIcon student_hover = new ImageIcon(getClass().getResource("button_student.png"));
        ImageIcon student_normal = new ImageIcon(getClass().getResource("button_student (1).png"));
        label_student.setIcon(student_normal);
        label_student.setFont(metropolis);
        label_student.setBounds(447, 250, 230, 89);
        Page_Frame.add(label_student);
        
        label_student.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              Student_page();
            }  
            public void mouseEntered(MouseEvent e) {
                label_student.setIcon(student_hover);
            }
            public void mouseExited(MouseEvent e) {
                label_student.setIcon(student_normal);
            } 
        } );
       
        Page_Frame.getContentPane().setBackground(Color.white);
        Page_Frame.setSize(900,500);
        Page_Frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Page_Frame.setLayout(null);
        Page_Frame.setLocationRelativeTo(null);
        Page_Frame.setResizable(false);
        Page_Frame.setVisible(true);   
        
    }
    // Calls Faculty Sign in Page
    public void Admin_page()
    {
        new Admit_SignIn();
        this.Page_Frame.dispose();
    }
    // Calls Student Sign in Page
    public void Student_page()
    {
        new Student_Signin();
        this.Page_Frame.dispose();
    }
    
}

