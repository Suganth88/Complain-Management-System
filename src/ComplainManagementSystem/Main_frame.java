package ComplainManagementSystem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
/*Main_frame
* this class enable navigatoion to complaint registration and view frame
*/
public class Main_frame {
    JLabel label_clg_icon, label_welcome, label_complaint_icon,label4,
            label_complaint_here,label_view_icon;
    JLabel label_status2,label_status1,label_missing_icon,label10
            ,label_missing,label_logout,label_location_cateroy,label_name;
    JLabel label_day,label_date;
    JFrame main_frame = new JFrame("College Complain Management");
    JLabel label_description;
    JButton jbutton_next_missing,jbutton_next_complaint,jbutton_next_view,
            jbutton_logout,jbutton_college,jbutton_hostel;
    JButton jbutton_college_view,jbutton_hostel_view;
    JDialog d1;
    Beans bd=new Beans();
    Date currentdate = new Date();
    //constructor
    public Main_frame()
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
        main_frame.add(label_welcome);
        label_welcome.setFont(metropolis.deriveFont(35f));
        label_welcome.setBounds(60,90,780,35);

//        label_name = new JLabel("QWQW");
//        add(label_name);
//        label_name.setFont(metropolis);
//        label_name.setBounds(80,220,180,30);
        label_description = new JLabel("Click Complain to register complain (or) Click View to view complains (or) Click find to complaim about lost things");
        label_description.setBounds(60,140,780,30);
        label_description.setFont(opensans.deriveFont(14f));
        main_frame.add(label_description);
        
        ImageIcon complain_hover = new ImageIcon(getClass().getResource("button_complain (1).png"));
        ImageIcon complain_normal = new ImageIcon(getClass().getResource("button_complain.png"));
        JLabel complain_button = new JLabel();
        complain_button.setIcon(complain_normal);
        complain_button.setBounds(340, 190, 197, 69);
        main_frame.add(complain_button);
        
        complain_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              Location_complaint();
            }  
            public void mouseEntered(MouseEvent e) {
                complain_button.setIcon(complain_hover);
            }
            public void mouseExited(MouseEvent e) {
                complain_button.setIcon(complain_normal);
            } 
        } );
        
        ImageIcon view_normal = new ImageIcon(getClass().getResource("button_view (1).png"));
        ImageIcon view_hover = new ImageIcon(getClass().getResource("button_view.png"));
        JLabel view_button = new JLabel();
        view_button.setIcon(view_normal);
        view_button.setBounds(140, 190, 197, 69);
        main_frame.add(view_button);
        
        view_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              Location_view();
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
        find_button.setBounds(570, 190, 197, 69);
        main_frame.add(find_button);
        
        find_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
              new MissingObject_Creaion_frame();
              main_frame.dispose();
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
        main_frame.add(label_day);

        JLabel sign_out = new JLabel("Sign Out");
        Font font = sign_out.getFont();
        sign_out.setFont(metropolis.deriveFont(25f));
        sign_out.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sign_out.setBounds(378, 340, 150, 35);
        main_frame.add(sign_out);
        
        sign_out.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                new Student_Signin();
                main_frame.dispose();
            }  
            public void mouseEntered(MouseEvent e) {
                
            }
            public void mouseExited(MouseEvent e) {
                
            } 
        } );

        main_frame.getContentPane().setBackground(Color.white);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setResizable(false);
        main_frame.setSize(900,500);
        main_frame.setLayout(null);
        main_frame.setLocationRelativeTo(null);
        main_frame.setVisible(true);
    }
    /*Location_complanit
    * display dialogbox to choose by
    */
    public void Location_complaint()
    {
        d1 = new JDialog(main_frame,"Choose a Location",Dialog.ModalityType.DOCUMENT_MODAL);
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
        label_location_cateroy= new JLabel("Select Location");
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
              main_frame.dispose();
              new Complaintcollege();
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
              main_frame.dispose();
              new Complainthostel();
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
    void Location_view(){
       
       d1 = new JDialog(main_frame,"Choose a Location",Dialog.ModalityType.DOCUMENT_MODAL);
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
        
        label_location_cateroy= new JLabel("Select Location");
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
              main_frame.dispose();
              new Complaint_college_view();
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
              main_frame.dispose();
              new Complaint_hostel_view();
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
    public static void main(String[] args) {
       new Main_frame();
    }
}
