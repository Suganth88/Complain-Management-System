package ComplainManagementSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
class MissingObject_Creaion_frame {
    private static JLabel main_heading,label_missingobject;
    private static JLabel label_object_description,label_lost_location,label_icon,label_mobile;    
    private static JTextField textfield_missingobject,textfield_lost_location,texfield_mobile;
    private static JTextArea textarea_description;
    private static JButton jbutton_submit,jbutton_importImage,jbutton_back;
    private static JComboBox jcombobox_object;
    private static JFrame mis_frame;
    private static String str_missingobject="NIl",str_lostLocation="NIL",str_description="NIL",str_warning=" ",str_mobile; 
    Beans bd=new Beans();
    /*add_missing_object
    * inserts missing object into database
    */
    private void add_missing_object(){
        try {
            //loading Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connectiom
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            PreparedStatement ps=con.prepareStatement("insert into "
                    + "missing_object values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, bd.str_name);
            ps.setString(2, bd.str_rollno);
            ps.setString(3, str_mobile);
            ps.setString(4,bd.getStr_email());
            ps.setString(5, jcombobox_object.getSelectedItem().toString());
            ps.setString(6, str_description);
            ps.setString(7, str_lostLocation);
            ps.setDate(8, Date.valueOf(LocalDate.now()));
            ps.setString(9,"Not Found");
            ps.setString(10, "Not Specified");
            ps.executeUpdate();
            System.out.println("Sucess");
        }catch (Exception e) {
            System.out.println("error => "+e);
        }
    }
    //constructor
    MissingObject_Creaion_frame(){
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
        mis_frame = new JFrame();
        mis_frame.setSize(650,630);
        mis_frame.setLayout(null);
        mis_frame.setLocationRelativeTo(null);
        mis_frame.setVisible(true);
        mis_frame.setResizable(false);
        mis_frame.getContentPane().setBackground(Color.white);
        
        main_heading = new JLabel("Find your Lost Things");
        main_heading.setBounds(110,20,820,70);
        mis_frame.add(main_heading);
        main_heading.setFont(metropolis.deriveFont(35f));

        label_missingobject = new JLabel("Object");
        mis_frame.add(label_missingobject);
        label_missingobject.setFont(metropolis.deriveFont(20f));
        label_missingobject.setBounds(150,140,120,20);

        String objects[]={"","Phone","ID Card","Bag","Key","Wallet","Helmet","Other"};
        jcombobox_object=new JComboBox(objects);
        jcombobox_object.setFont(opensans.deriveFont(20f));
        jcombobox_object.setBackground(Color.white);
        mis_frame.add(jcombobox_object);
        jcombobox_object.setBounds(290, 140, 200, 30);
        
        label_object_description = new JLabel("Description");

        mis_frame.add(label_object_description);
        label_object_description.setBounds(110,190,120,20);
        label_object_description.setFont(metropolis.deriveFont(20f));

        textarea_description = new JTextArea();

        mis_frame.add(textarea_description);
        textarea_description.setLineWrap(true);
        textarea_description.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        textarea_description.setFont(opensans.deriveFont(15f));
        textarea_description.setWrapStyleWord(true);
        textarea_description.setBounds(290,190,200,150);
        
        label_lost_location = new JLabel("Lost Location");
        mis_frame.add(label_lost_location);
        label_lost_location.setBounds(90,370,200,20);
        label_lost_location.setFont(metropolis.deriveFont(20f));
        
        textfield_lost_location = new JTextField();
        mis_frame.add(textfield_lost_location);
        textfield_lost_location.setBounds(290,370,200,30);
        textfield_lost_location.setFont(opensans.deriveFont(20f));
    
        label_mobile=new JLabel("Mobile Number");
        label_mobile.setBounds(80, 420, 170, 20);
        mis_frame.add(label_mobile);
        label_mobile.setFont(metropolis.deriveFont(20f));
        
        texfield_mobile=new JTextField();
        mis_frame.add(texfield_mobile);
        texfield_mobile.setBounds(290, 420, 200, 30);
        texfield_mobile.setFont(opensans.deriveFont(20f));
 
        jbutton_back = new JButton("BACK");
//        mis_frame.add(jbutton_back);
//        jbutton_back.setBounds(240,470,100,20);
        jbutton_back.setFont(new Font("Times New Roman",Font.BOLD,15));

        jbutton_submit = new JButton("APPLY");
//        mis_frame.add(jbutton_submit);
//        jbutton_submit.setBounds(360,470,100,20);
        jbutton_submit.setFont(new Font("Times New Roman",Font.BOLD,15));

        JLabel back_button = new JLabel();
        ImageIcon back_hover = new ImageIcon(getClass().getResource("button_back (3).png"));
        ImageIcon back_normal = new ImageIcon(getClass().getResource("button_back (2).png"));
        back_button.setIcon(back_normal);
        back_button.setBounds(90, 470, 197, 69);
        mis_frame.add(back_button);
        
        back_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                mis_frame.dispose();
                new Main_frame();
            }  
            public void mouseEntered(MouseEvent e) {
                back_button.setIcon(back_hover);
            }
            public void mouseExited(MouseEvent e) {
                back_button.setIcon(back_normal);
            } 
        } );
        
        ImageIcon complain_hover = new ImageIcon(getClass().getResource("button_complain (1).png"));
        ImageIcon complain_normal = new ImageIcon(getClass().getResource("button_complain.png"));
        JLabel complain_button = new JLabel();
        complain_button.setIcon(complain_normal);
        complain_button.setBounds(290, 470, 197, 69);
        mis_frame.add(complain_button);
        
        complain_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.white);
                UI.put("Panel.background", Color.white);
                str_description=textarea_description.getText();
                str_lostLocation=textfield_lost_location.getText();
                str_mobile=texfield_mobile.getText();
                if("NIL".equals("sad")){     //bd.gerSte_name()
                    str_warning=str_warning+"Name ";
                }
                //check whether user has not selected any object
                else if("".equals(jcombobox_object.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(mis_frame, "Missing object is not entered!!");

                }
                //check whether description is NUll
                else if("".equals(str_description)){
                    JOptionPane.showMessageDialog(mis_frame,"Description is missing." );
                }
                //check whether lostlocation is NULL 
                else if("".equals(str_lostLocation)){
                    JOptionPane.showMessageDialog(mis_frame, "Lost location is missing.");
                }
                //check whether str_mobile contain only number
                else if(str_mobile.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[!-.]).*")){
                    JOptionPane.showMessageDialog(mis_frame, "Invalid Mobile Number");
                }
                //check whether str_mobile length is 10 
                else if(str_mobile.length()!=10){
                    JOptionPane.showMessageDialog(mis_frame, "Invalid Mobile Number");
                }
                else{
                    add_missing_object();
                    JOptionPane.showMessageDialog(mis_frame, "Complain Registered");
                }
                
            }  
            public void mouseEntered(MouseEvent e) {
                complain_button.setIcon(complain_hover);
            }
            public void mouseExited(MouseEvent e) {
                complain_button.setIcon(complain_normal);
            } 
        } );
        
        mis_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

       
      