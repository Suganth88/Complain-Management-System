package ComplainManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/*Admin_main_frame
* this class enable navigatoion between admin_complaint view and return Object table
*/
public class Admin_main_frame{

    JFrame frame_mainform = new JFrame();
    JLabel label_skcticon,label_welcome,label1_name;
    JLabel label_description;
    JLabel  label_day,label3_date,label_viewicon, label_sentence1;
    JLabel   label_missingicon,label_view, label_sentence2,location_title,label_logout;
    JButton next2_button,next3_button,logout_button;
    String name="ADMIN";
    Date currentdate = new Date();       

    public Admin_main_frame()                                //constructor
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
        
        label_welcome = new JLabel("Welcome to Complaint Management System");
        frame_mainform.add(label_welcome);
        label_welcome.setFont(metropolis.deriveFont(35f));
        label_welcome.setBounds(60,90,780,35);
        
        label_description = new JLabel("Click View to view complains made by students (or) Click Find to find the person who lost and return any lost things");
        label_description.setBounds(60,140,780,30);
        label_description.setFont(opensans.deriveFont(14f));
        frame_mainform.add(label_description);
        
        ImageIcon view_normal = new ImageIcon(getClass().getResource("button_view (1).png"));
        ImageIcon view_hover = new ImageIcon(getClass().getResource("button_view.png"));
        JLabel view_button = new JLabel();
        view_button.setIcon(view_normal);
        view_button.setBounds(240, 190, 197, 69);
        frame_mainform.add(view_button);
        
        view_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                //new Admin_complaint_view().frame();
                Location_view();
                frame_mainform.dispose();
            }  
            public void mouseEntered(MouseEvent e) {
                view_button.setIcon(view_hover);
            }
            public void mouseExited(MouseEvent e) {
                view_button.setIcon(view_normal);
            } 
        } );
        
        ImageIcon find_hover = new ImageIcon(getClass().getResource("button_find (1).png"));
        ImageIcon find_normal = new ImageIcon(getClass().getResource("button_find.png"));
        JLabel find_button = new JLabel();
        find_button.setIcon(find_normal);
        find_button.setBounds(470, 190, 197, 69);
        frame_mainform.add(find_button);
        
        find_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              //new MissingObject_Creaion_frame();
              new Return_object();
              frame_mainform.dispose();
            }  
            public void mouseEntered(MouseEvent e) {
                find_button.setIcon(find_hover);
            }
            public void mouseExited(MouseEvent e) {
                find_button.setIcon(find_normal);
            } 
        } );
        
        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        label_day = new JLabel(date.format(currentdate)+" - "+day.format(currentdate));
        label_day.setFont(opensans.deriveFont(14f));
        label_day.setBounds(360,300,250,30);
        frame_mainform.add(label_day);
        
        JLabel sign_out = new JLabel("Sign Out");
        Font font = sign_out.getFont();
        sign_out.setFont(metropolis.deriveFont(25f));
        sign_out.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sign_out.setBounds(378, 340, 150, 35);
        frame_mainform.add(sign_out);
        
        sign_out.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                new Admit_SignIn();
                frame_mainform.dispose();
            }  
            public void mouseEntered(MouseEvent e) {
                
            }
            public void mouseExited(MouseEvent e) {
                
            } 
        } );

        frame_mainform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame_mainform.getContentPane().setBackground(Color.white);
        frame_mainform.setSize(900,500);
        frame_mainform.setLayout(null);
        frame_mainform.setLocationRelativeTo(null);
        frame_mainform.setVisible(true);

    }
    void Location_view(){
       
       JDialog d1 = new JDialog(frame_mainform,"Choose a Location",Dialog.ModalityType.DOCUMENT_MODAL);
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
        
        JLabel label_location_cateroy= new JLabel("Select Location");
        label_location_cateroy.setFont(metropolis.deriveFont(33f));
        label_location_cateroy.setBounds(70,45,300,40);
        d1.add(label_location_cateroy);

        ImageIcon college_hover = new ImageIcon(getClass().getResource("button_college.png"));
        ImageIcon college_normal = new ImageIcon(getClass().getResource("button_college (1).png"));
        JLabel college_button = new JLabel();
        college_button.setIcon(college_normal);
        college_button.setBounds(70, 100, 154, 69);
        d1.add(college_button);
        
        college_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              d1.dispose();
              frame_mainform.dispose();
              new Admin_view_college();
            }  
            public void mouseEntered(MouseEvent e) {
                college_button.setIcon(college_hover);
            }
            public void mouseExited(MouseEvent e) {
                college_button.setIcon(college_normal);
            } 
        } );
        
        ImageIcon hostel_hover = new ImageIcon(getClass().getResource("button_hostel.png"));
        ImageIcon hostel_normal = new ImageIcon(getClass().getResource("button_hostel (1).png"));
        JLabel hostel_button = new JLabel();
        hostel_button.setIcon(hostel_normal);
        hostel_button.setBounds(240, 100, 154, 69);
        d1.add(hostel_button);
        
        hostel_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              d1.dispose();
              frame_mainform.dispose();
              new Admin_view_hostel();
            }  
            public void mouseEntered(MouseEvent e) {
                hostel_button.setIcon(hostel_hover);
            }
            public void mouseExited(MouseEvent e) {
                hostel_button.setIcon(hostel_normal);
            } 
        } );
        
        d1.setResizable(false);
        d1.getContentPane().setBackground(Color.white);
        d1.setSize(480,260);
        d1.setLayout(null);
        d1.setLocationRelativeTo(null);
        d1.setVisible(true);

    }
    
}

