package ComplainManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.border.LineBorder;
/*Complaintcollege
* get complaint detail from user and inserts it into database
*/
public class Complaintcollege {
    JLabel label_complaint_description, label_complaintcollege_title,
            label_complaintcollege_date, label_complaintcollege_complaint,
            label_complaintcollege_block,
            label_complaintcollege_uniqueid,label_complaintcollege_messagesucess,
            label_complaintcollege_messagerror,icon1;
    JButton button1,jbutton_complaintcollege_apply;
    JFrame col_comp = new JFrame();
    JTextArea jtextarea_complaintcollege_complaintDescription;
    JTextField jtextfeild_complaint;
    JComboBox jcombobox_block,jcombobox_classNo;
    JPasswordField jpasswordl_complaintcollege_uniqueid;
    JDialog jdialog_complaintcollege_boxsucess,jdialog_complaintcollege_boxerror;
    static String Str_complaintcollege_complaintDescription="NIL",
           Str_complaintcollege_complaint="NIl",Str_uniqueid;
    Beans bd=new Beans();
    static String Str_complaintcollege_block;   
    private final JLabel label_classNo;
    
    //Constructor
    public Complaintcollege()
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
        col_comp.setSize(610,630);
        col_comp.setLayout(null);
        col_comp.setLocationRelativeTo(null);
        col_comp.setVisible(true);

        label_complaintcollege_title = new JLabel("College complaint form");

        label_complaintcollege_title.setBounds(90,20,820,70);
        col_comp.add(label_complaintcollege_title);
        label_complaintcollege_title.setFont(metropolis.deriveFont(35f));
        
        label_complaintcollege_complaint = new JLabel("Complain");

        col_comp.add(label_complaintcollege_complaint); 
        label_complaintcollege_complaint.setBounds(140,120,120,20);
        label_complaintcollege_complaint.setFont(metropolis.deriveFont(20f));

        label_complaint_description=new JLabel("Complain Description");
        col_comp.add(label_complaint_description);
        label_complaint_description.setFont(metropolis.deriveFont(20f));
        label_complaint_description.setBounds(30, 170, 230, 20);
        
        jtextfeild_complaint=new JTextField();
        col_comp.add(jtextfeild_complaint);
        jtextfeild_complaint.setFont(opensans.deriveFont(20f));
        jtextfeild_complaint.setBounds(290, 120, 200, 30);
        
        jtextarea_complaintcollege_complaintDescription = new JTextArea();

        col_comp.add(jtextarea_complaintcollege_complaintDescription);
        jtextarea_complaintcollege_complaintDescription.setLineWrap(true);
        jtextarea_complaintcollege_complaintDescription.setWrapStyleWord(true);
        jtextarea_complaintcollege_complaintDescription.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        jtextarea_complaintcollege_complaintDescription.setBounds(290,170,200,150);

        label_complaintcollege_block = new JLabel("Block");
        col_comp.add(label_complaintcollege_block);
        label_complaintcollege_block.setBounds(170,340,120,20);
        label_complaintcollege_block.setFont(metropolis.deriveFont(20f));
        String block1[]={"","Main Block","Library","ECE BLock","CSE Block","IT Block","EEE Block","MECH Block","Parking"};

        jcombobox_block = new JComboBox(block1);
        col_comp.add(jcombobox_block);
        jcombobox_block.setFont(opensans.deriveFont(20f));
        jcombobox_block.setBounds(290,340,200,30);
        jcombobox_block.setBackground(Color.white);
        
        label_classNo=new JLabel("Class Sec");
        col_comp.add(label_classNo);
        label_classNo.setBounds(150,390,120,20);
        label_classNo.setFont(metropolis.deriveFont(20f));  
        String classNo[]={"","CR01","CR02","CR03","CR04","CR05","CR06","CR07","CR08","CR09"};
        jcombobox_classNo=new JComboBox(classNo);
        jcombobox_classNo.setFont(opensans.deriveFont(20f));
        col_comp.add(jcombobox_classNo);
        jcombobox_classNo.setBounds(290,390, 200, 30);  
        jcombobox_classNo.setBackground(Color.white);
        label_complaintcollege_uniqueid = new JLabel("Unique ID");

        col_comp.add(label_complaintcollege_uniqueid);
        label_complaintcollege_uniqueid.setBounds(140,440,120,20);
        label_complaintcollege_uniqueid.setFont(metropolis.deriveFont(20f));

        jpasswordl_complaintcollege_uniqueid = new JPasswordField();
        col_comp.add(jpasswordl_complaintcollege_uniqueid);
        jpasswordl_complaintcollege_uniqueid.setBounds(290,440,200,30);
        
        JLabel back_button = new JLabel();
        ImageIcon back_hover = new ImageIcon(getClass().getResource("button_back (3).png"));
        ImageIcon back_normal = new ImageIcon(getClass().getResource("button_back (2).png"));
        back_button.setIcon(back_normal);
        back_button.setBounds(110, 500, 197, 69);
        col_comp.add(back_button);
        
        back_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                col_comp.dispose();
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
        complain_button.setBounds(290, 500, 197, 69);
        col_comp.add(complain_button);
        
        complain_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.white);
                UI.put("Panel.background", Color.white);
                mandatory();
                
            }  
            public void mouseEntered(MouseEvent e) {
                complain_button.setIcon(complain_hover);
            }
            public void mouseExited(MouseEvent e) {
                complain_button.setIcon(complain_normal);
            } 
        } );
        col_comp.setResizable(false);
        col_comp.getContentPane().setBackground(Color.white);
        col_comp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        col_comp.setTitle("Complain in COllege");
    }
    /*CheckUID
    * Checks whether unqiue id entered is already used in the database
    * IF used already ,error message
    * ELSE , inserts complaint into database
    */
    boolean CheckUID(String UID){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            System.out.println("Connection to SQLite has been established.");
            //Creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from complaint_college where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(col_comp, "UID is already used");
                return true;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return false;
    }
    /*mandatory
    * checks whether all the textfields are NULL
    * IF  NULL, @return false
    * checks whether unqiueid is unique
    * IF Unique,@return true and call insert_data_db methode
    * ELSE @return true
    */
    public void mandatory()
    {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        Str_complaintcollege_complaint=jtextfeild_complaint.getText();
        Str_complaintcollege_complaintDescription= jtextarea_complaintcollege_complaintDescription.getText();
        Str_complaintcollege_block = jcombobox_block.getSelectedItem().toString();
        System.err.println(Str_complaintcollege_block+" "
                +Str_complaintcollege_complaintDescription+" ");
        Str_uniqueid = jpasswordl_complaintcollege_uniqueid.getText();
        if(Str_complaintcollege_complaint.equals("") ||Str_complaintcollege_block.equals("")||Str_uniqueid.equals("")){
            JOptionPane.showMessageDialog(col_comp, "Enter all feilds");
            return;
        }
        else if(CheckUID(Str_uniqueid)){
            return;
        }
        else{
            Insert_data_db();
            JOptionPane.showMessageDialog(col_comp,"Complaint registered");
            jtextarea_complaintcollege_complaintDescription.setText("");
            jtextfeild_complaint.setText("");
            jcombobox_block.setSelectedIndex(0);
            jcombobox_classNo.setSelectedIndex(0);
            jpasswordl_complaintcollege_uniqueid.setText("");
        }
    }
    void Insert_data_db(){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            PreparedStatement ps=con.prepareStatement("insert into complaint_college values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, bd.getStr_name());
            ps.setString(2,bd.getStr_rollno());
            ps.setString(3, bd.getStr_email());            
            ps.setString(4,jcombobox_block.getSelectedItem().toString() );
            ps.setString(5, jcombobox_classNo.getSelectedItem().toString());
            ps.setString(6,jtextfeild_complaint.getText());
            ps.setString(7,jtextarea_complaintcollege_complaintDescription.getText());
            ps.setDate(8,Date.valueOf(LocalDate.now()));
            ps.setString(9,"Pending");
            ps.setString(10, Str_uniqueid);
          
            ps.executeUpdate();
            System.out.print("sucess----");
        } 
        catch (Exception e){
            System.out.println("project.Complaintcollege.Insert_data_db() "+e);
        }
    }
    public static void main(String[] args) {
        new Complaintcollege();
    }
}