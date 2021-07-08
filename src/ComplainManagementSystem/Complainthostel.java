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
* get college complaint detail from user and inserts it into database
*/
public class Complainthostel {
    JFrame frame_complainthostel;
    JLabel text_title, label_complaint, label_block, label_uniqueid,label5,
            success_message, error_message, hostel_icon;
    JLabel label_Room_no,label_complaint_description;
    JButton button_back, button_apply;
    JTextArea complaint_textarea;
    JTextField jtextfeild_complaint;
    JComboBox combobox_block,combobox_room;
    JPasswordField password_field;
    JDialog success_box, error_box;   
    String block,complaint,uniqueid;
    final String block1[]={"","A block","B block","C block","D block"};     //block in hostel
    final String Room[]={"","100","101","102","103","104","105","106","107"}; //room no in hostel
    Beans bd = new Beans();
    
    public Complainthostel()
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
        frame_complainthostel = new JFrame("Hostel Complain");
        frame_complainthostel.getContentPane().setBackground(Color.white);
        frame_complainthostel.setResizable(false);
        frame_complainthostel.setSize(610,630);
        frame_complainthostel.setLayout(null);
        frame_complainthostel.setLocationRelativeTo(null);
        frame_complainthostel.setVisible(true);

        text_title = new JLabel("Hostel Complain Form");

        text_title.setBounds(110,20,820,70);
        frame_complainthostel.add(text_title);
        text_title.setFont(metropolis.deriveFont(35f));
      
        
        label_complaint = new JLabel("Complain");
        frame_complainthostel.add(label_complaint);
        label_complaint.setBounds(140,120,120,20);
        label_complaint.setFont(metropolis.deriveFont(20f));

        jtextfeild_complaint=new JTextField();
        frame_complainthostel.add(jtextfeild_complaint);
        jtextfeild_complaint.setFont(opensans.deriveFont(20f));
        jtextfeild_complaint.setBounds(290, 120, 200, 30);
        
        complaint_textarea = new JTextArea();
        
        label_complaint_description=new JLabel("Complain Description");
        frame_complainthostel.add(label_complaint_description);
        label_complaint_description.setFont(metropolis.deriveFont(20f));
        label_complaint_description.setBounds(30, 170, 230, 20);
        
        frame_complainthostel.add(complaint_textarea);
        complaint_textarea.setLineWrap(true);
        complaint_textarea.setWrapStyleWord(true);
        complaint_textarea.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        complaint_textarea.setFont(opensans.deriveFont(15f));
        complaint_textarea.setBounds(290,170,200,150);

        label_block = new JLabel("Bock");
        label_block.setFont(metropolis.deriveFont(20f));
        frame_complainthostel.add(label_block);
        label_block.setBounds(90,350,120,20);
        label_block.setFont(metropolis.deriveFont(20f));
      

        combobox_block = new JComboBox(block1);
        combobox_block.setBackground(Color.white);
        frame_complainthostel.add(combobox_block);
        combobox_block.setBounds(290,350,200,30);

        combobox_room=new JComboBox(Room);
        frame_complainthostel.add(combobox_room);
        combobox_room.setBounds(290,390, 200, 30);
        combobox_room.setBackground(Color.white);
        combobox_block.setFont(opensans.deriveFont(20f));
        combobox_room.setFont(opensans.deriveFont(20f));
        label_Room_no=new JLabel("Room No");
        label_Room_no.setBounds(90,390,120,20);
        label_Room_no.setFont(metropolis.deriveFont(20f));
        frame_complainthostel.add(label_Room_no);
        
        label_uniqueid = new JLabel("Unique ID");
        frame_complainthostel.add(label_uniqueid);
        label_uniqueid.setBounds(90,440,120,20);
        label_uniqueid.setFont(metropolis.deriveFont(20f));

        password_field = new JPasswordField();

        frame_complainthostel.add(password_field);
        password_field.setBounds(290,440,200,30);


        JLabel back_button = new JLabel();
        ImageIcon back_hover = new ImageIcon(getClass().getResource("button_back (3).png"));
        ImageIcon back_normal = new ImageIcon(getClass().getResource("button_back (2).png"));
        back_button.setIcon(back_normal);
        back_button.setBounds(110, 500, 197, 69);
        frame_complainthostel.add(back_button);
        
        back_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                frame_complainthostel.dispose();
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
        frame_complainthostel.add(complain_button);
        
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
        frame_complainthostel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            System.out.println("Connection to SQLite has been established.");
            //Creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from complaint_hostel where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "UID is already used");
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
        complaint= complaint_textarea.getText();
        block = combobox_block.getSelectedItem().toString();
        uniqueid = password_field.getText();
        if(complaint.isEmpty()||block.isEmpty()||uniqueid.isEmpty())
        {
            JOptionPane.showMessageDialog(frame_complainthostel,"Enter all feilds");
            return;
        }
        if(CheckUID(uniqueid)){
            return;
        }
        else{
            insert_into_database_hostel();
            JOptionPane.showMessageDialog(frame_complainthostel,"Complaint registered");
//            jtextarea_complaintcollege_complaintDescription.setText("");
//            jtextfeild_complaint.setText("");
//            jcombobox_block.setSelectedIndex(0);
//            jcombobox_classNo.setSelectedIndex(0);
//            jpasswordl_complaintcollege_uniqueid.setText("");
        }
        
    }
    void insert_into_database_hostel(){
         try {
             //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            //Creating PerparedStatement
            PreparedStatement ps=con.prepareStatement("insert into"
                    + " complaint_hostel values(?,?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, bd.getStr_name());
            ps.setString(2, bd.getStr_rollno());
            ps.setString(3, bd.getStr_email());
            ps.setString(4,combobox_block.getSelectedItem().toString());
            ps.setString(5, combobox_room.getSelectedItem().toString());
            ps.setString(6, jtextfeild_complaint.getText());
            ps.setString(7, complaint_textarea.getText());
            ps.setDate(8, Date.valueOf(LocalDate.now()));
            ps.setString(9, "Pending");
            ps.setString(10, password_field.getText());
            //Executing Query
            ps.executeUpdate();
            System.out.println("Sucess");
        } 
        catch (Exception e) {
            System.out.println("error => "+e);
        }
    }
    
    public static void main(String[] args) {
        new Complainthostel();
    }
}
