package ComplainManagementSystem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.InputStream;
import javax.swing.table.JTableHeader;
/*Return_object
* Create and display missing object detail in tabel
* notifies them if the object found and also
* Change status of the complaint
*/
public class Return_object{
    
    Connection con;
    Statement st;
    PreparedStatement ps;
    
    Statement stextfield_for_name,stextfield_for_roll_number;
    PreparedStatement ps1;
    ResultSet rs1;
    
    DefaultTableModel table_model = new DefaultTableModel(
            new Object[]{"Name","Roll No","Phone No"
                    ,"Email Id","Things","Description"
                    ,"Location","Date","Status","Collected By"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false ;
        }
    };
    ArrayList<String> rollnumberlist;
    ArrayList<String> searchresultlist;
     
    JFrame frame_for_notify,frame_for_common_mail;
    JScrollPane scrollPane_for_missing_objects_table;
    JTable table_for_missing_objects;
    JLabel label_for_name,label_for_roll_number,label_for_phone_number,label_for_email_id,label_for_missing_objects;
    JLabel label_for_description,label_for_location,label_for_date_of_lost,label_for_status,label_for_collected_by;
    JTextField textfield_for_name,textfield_for_roll_number,textfield_for_phone_number,textfield_for_email_id,textfield_for_missing_objects;
    JTextField textfield_for_description,textfield_for_location,textfield_for_date_of_lost,textfield_for_status,textfield_for_collected_by;
    JComboBox combobox_for_search;
    JButton button_for_search;
    JButton button_for_notify,button_for_common_mail,button_for_delete,button_for_update,button_for_back,button_for_notify_in_frame_forcommon_mail;
    JRadioButton radiobutton_for_send_mail,radiobutton_for_delete,radiobutton_for_update;
    JTextField textfield_for_missing_objects_in_common_mail,textfield_for_description_in_common_mail,textfield_for_location_in_common_mail;
    JTextField textfield_for_date_found_in_common_mail;
    JTextField textfield_for_uniq_id;
    JButton button_for_notify_in_common_mail;
    JLabel label_for_missing_objects_in_common_mail,label_for_description_in_common_mail,label_for_location_in_common_mail,label_for_date_found_in_common_mail;
    JDialog dialogbox_for_common_mail;
    JOptionPane error_box_for_common_mail;
    
    String Uniq_id;
    
    String string_for_combobox_search[] = {"Full Table","Phone","ID Card","Bag","Wallet","Helmet","Key","Others"};

    Return_object(){
                 try{
                     
                    Class.forName("com.mysql.jdbc.Driver");          //loading driver
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
                    st = con.createStatement();                      //creating Statment
                    stextfield_for_name = con.createStatement();
                    stextfield_for_roll_number = con.createStatement();
            
                }
                catch(Exception e){
                    System.out.println(e);
                }
                this.rollnumberlist = getComplaintList();
                Components();                                   //display button
                showtable();                                    //display missing object in table form
    }
    /*Components
    * it display buttons and labels
    */
    private void Components() {
        
        frame_for_notify = new JFrame("Return Missing Ojects");
        
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
        table_for_missing_objects = new JTable();
        table_for_missing_objects.setModel(table_model);
        table_for_missing_objects.setBackground(Color.white);
        table_for_missing_objects.getTableHeader().setBackground(Color.white);
        table_for_missing_objects.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_for_missing_objects.setFillsViewportHeight(true);
        table_for_missing_objects.setFocusable(false);
        table_for_missing_objects.setFont(opensans.deriveFont(15f));
        table_for_missing_objects.setRowHeight(40);
        table_for_missing_objects.setRowSelectionAllowed(true);
        table_for_missing_objects.setColumnSelectionAllowed(false);
        scrollPane_for_missing_objects_table = new JScrollPane(table_for_missing_objects);
        scrollPane_for_missing_objects_table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_for_missing_objects_table.setLocation(30, 30);
        scrollPane_for_missing_objects_table.setSize(1420, 150);
        
        JTableHeader tableHeader = table_for_missing_objects.getTableHeader();
        tableHeader.setFont(metropolis.deriveFont(22f));
        Font fontextfield_for_name = new Font("Serif",Font.PLAIN,20);
        
        
        
        label_for_name = new JLabel("Name");
        label_for_name.setBounds(30, 270, 200, 30);
        label_for_name.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_name);
        
        label_for_roll_number = new JLabel("Roll Number");
        label_for_roll_number.setBounds(30, 330, 200, 30);
        label_for_roll_number.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_roll_number);
        
        label_for_phone_number = new JLabel("Phone Number");
        label_for_phone_number.setBounds(30, 390, 200, 30);
        label_for_phone_number.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_phone_number);
        
        label_for_email_id = new JLabel("Email ID");
        label_for_email_id.setBounds(30, 450, 200, 30);
        label_for_email_id.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_email_id);
        
        label_for_missing_objects = new JLabel("Missing Objects");
        label_for_missing_objects.setBounds(500, 270, 200, 35);
        label_for_missing_objects.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_missing_objects);
        
        label_for_description = new JLabel("Description");
        label_for_description.setBounds(500, 330, 200, 35);
        label_for_description.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_description);
        
        label_for_location = new JLabel("Location");
        label_for_location.setBounds(500, 450, 200, 30);
        label_for_location.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_location);
        
        label_for_date_of_lost = new JLabel("Date of Lost");
        label_for_date_of_lost.setBounds(1000, 270, 200, 30);
        label_for_date_of_lost.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_date_of_lost);
        
        label_for_status = new JLabel("Status");
        label_for_status.setBounds(1000, 330, 200, 30);
        label_for_status.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_status);
        
        label_for_collected_by = new JLabel("Collected By");
        label_for_collected_by.setBounds(1000, 390, 200, 30);
        label_for_collected_by.setFont(metropolis.deriveFont(25f));
        frame_for_notify.add(label_for_collected_by);
        
        textfield_for_name = new JTextField();
        textfield_for_name.setBounds(260,270,200,30);
        textfield_for_name.setFont(fontextfield_for_name);
        textfield_for_name.setEditable(false);
        textfield_for_name.setBackground(Color.white);
        frame_for_notify.add(textfield_for_name);
        
        textfield_for_roll_number = new JTextField();
        textfield_for_roll_number.setBounds(260,330,200,30);
        textfield_for_roll_number.setEditable(false);
        textfield_for_roll_number.setBackground(Color.white);
        textfield_for_roll_number.setFont(opensans.deriveFont(20f));
        frame_for_notify.add(textfield_for_roll_number);

        textfield_for_phone_number = new JTextField();
        textfield_for_phone_number.setBounds(260,390,200,30);
        textfield_for_phone_number.setEditable(false);
        textfield_for_phone_number.setBackground(Color.white);
        textfield_for_phone_number.setFont(opensans.deriveFont(20f));
        frame_for_notify.add(textfield_for_phone_number);

        textfield_for_email_id = new JTextField();
        textfield_for_email_id.setBounds(260,450,200,30);
        textfield_for_email_id.setEditable(false);
        textfield_for_email_id.setBackground(Color.white);
        textfield_for_email_id.setFont(opensans.deriveFont(20f));
        frame_for_notify.add(textfield_for_email_id);

        textfield_for_missing_objects = new JTextField();
        textfield_for_missing_objects.setBounds(730,270,200,30);
        textfield_for_missing_objects.setEditable(false);
        textfield_for_missing_objects.setBackground(Color.white);
        textfield_for_missing_objects.setFont(opensans.deriveFont(20f));
        frame_for_notify.add(textfield_for_missing_objects);

        textfield_for_description = new JTextField();
        textfield_for_description.setFont(opensans.deriveFont(20f));
        textfield_for_description.setBounds(730,330,200,90);
        textfield_for_description.setEditable(false);
        textfield_for_description.setBackground(Color.white);
        frame_for_notify.add(textfield_for_description);

        textfield_for_location = new JTextField();
        textfield_for_location.setFont(opensans.deriveFont(20f));
        textfield_for_location.setBounds(730,450,200,30);
        textfield_for_location.setEditable(false);
        textfield_for_location.setBackground(Color.white);
        frame_for_notify.add(textfield_for_location);

        textfield_for_date_of_lost = new JTextField();
        textfield_for_date_of_lost.setFont(opensans.deriveFont(20f));
        textfield_for_date_of_lost.setBounds(1200,270,248,30);
        textfield_for_date_of_lost.setEditable(false);
        textfield_for_date_of_lost.setBackground(Color.white);
        frame_for_notify.add(textfield_for_date_of_lost);
        
        textfield_for_status = new JTextField();
        textfield_for_status.setFont(opensans.deriveFont(20f));
        textfield_for_status.setBounds(1200,330,248,30);
        textfield_for_status.setEditable(false);
        textfield_for_status.setBackground(Color.white);
        frame_for_notify.add(textfield_for_status);

        textfield_for_collected_by = new JTextField();
        textfield_for_collected_by.setFont(opensans.deriveFont(20f));
        textfield_for_collected_by.setBounds(1200,390,248,30);
        textfield_for_collected_by.setEditable(false);
        textfield_for_collected_by.setBackground(Color.white);
        frame_for_notify.add(textfield_for_collected_by);
        
        combobox_for_search = new JComboBox(string_for_combobox_search);
        combobox_for_search.setBounds(500, 200, 200, 30);
        combobox_for_search.setFont(opensans.deriveFont(20f));
        combobox_for_search.setBackground(Color.white);
        frame_for_notify.add(combobox_for_search);
        
         
        button_for_back = new JButton();
        button_for_back.setText("Back");
        button_for_back.setFont(opensans.deriveFont(20f));
        button_for_back.setBackground(Color.white);
        button_for_back.setEnabled(true);
        button_for_back.setBounds(1200,590,200,30);
        frame_for_notify.add(button_for_back);
        
        
        
        button_for_notify = new JButton();
        button_for_notify.setText("Notify");
        button_for_notify.setFont(opensans.deriveFont(20f));
        button_for_notify.setBackground(Color.white);
        button_for_notify.setEnabled(false);
        button_for_notify.setBounds(30,590,200,30);
        frame_for_notify.add(button_for_notify);
        
        
        button_for_common_mail = new JButton();
        button_for_common_mail.setText("Common Mail");
        button_for_common_mail.setFont(opensans.deriveFont(20f));
        button_for_common_mail.setEnabled(false);
        button_for_common_mail.setBackground(Color.white);
        button_for_common_mail.setBounds(260,590,200,30);
        frame_for_notify.add(button_for_common_mail);
             
        button_for_update = new JButton("Update");
        button_for_update.setBounds(550, 590, 248, 30);
        button_for_update.setFont(opensans.deriveFont(20f));
        button_for_update.setBackground(Color.white);
        frame_for_notify.add(button_for_update);
        button_for_update.setEnabled(false);
        
        button_for_delete = new JButton("Delete");
        button_for_delete.setBounds(550, 590, 200, 30);
        button_for_delete.setFont(opensans.deriveFont(20f));
        button_for_delete.setBackground(Color.white);
     
        
        button_for_search = new JButton("Search");
        button_for_search.setBounds(730,200,200,30);
        button_for_search.setFont(opensans.deriveFont(20f));
        button_for_search.setBackground(Color.white);
        frame_for_notify.add(button_for_search);
        
        radiobutton_for_update = new JRadioButton("Click to Update");
        radiobutton_for_update.setBounds(550,530,250,30);
        radiobutton_for_update.setFont(metropolis.deriveFont(25f));
        radiobutton_for_update.setBackground(Color.white);
        frame_for_notify.add(radiobutton_for_update);
        
        radiobutton_for_delete = new JRadioButton("Click to Delete");
        radiobutton_for_delete.setBounds(550,530,200,30);
        radiobutton_for_delete.setFont(metropolis.deriveFont(25f));
        radiobutton_for_delete.setBackground(Color.white);

        
        radiobutton_for_send_mail = new JRadioButton("Send Mail");
        radiobutton_for_send_mail.setBounds(30,530,200,30);
        radiobutton_for_send_mail.setFont(metropolis.deriveFont(25f));
        radiobutton_for_send_mail.setBackground(Color.white);
        frame_for_notify.add(radiobutton_for_send_mail);
                
        ButtonGroup bg = new ButtonGroup();
        bg.add(radiobutton_for_update);
        bg.add(radiobutton_for_delete);
        bg.add(radiobutton_for_send_mail);
        
        button_for_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin_main_frame();                 //invokes Admin_main_frame constructor
                frame_for_notify.dispose();             //dispose frame
            }
        });
        
        radiobutton_for_update.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== 1){
                    textfield_for_name.setEditable(false);
                    textfield_for_phone_number.setEditable(false);
                    textfield_for_email_id.setEditable(false);
                    textfield_for_missing_objects.setEditable(false);
                    textfield_for_description.setEditable(false);
                    textfield_for_location.setEditable(false);
                    textfield_for_date_of_lost.setEditable(false);
                    textfield_for_status.setEditable(true);
                    textfield_for_collected_by.setEditable(true);
                    button_for_notify.setEnabled(false);
                    button_for_common_mail.setEnabled(false);
                    button_for_delete.setEnabled(false);
                    button_for_update.setEnabled(true);
                }else{
                    textfield_for_name.setEditable(false);
                    textfield_for_roll_number.setEditable(false);
                    textfield_for_phone_number.setEditable(false);
                    textfield_for_email_id.setEditable(false);
                    textfield_for_missing_objects.setEditable(false);
                    textfield_for_description.setEditable(false);
                    textfield_for_location.setEditable(false);
                    textfield_for_date_of_lost.setEditable(false);
                    textfield_for_status.setEditable(false);
                    textfield_for_collected_by.setEditable(false);
                    button_for_update.setEnabled(false);
                }
            }
        });
        

        
        radiobutton_for_send_mail.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1){
                    button_for_notify.setEnabled(true);
                    button_for_common_mail.setEnabled(true);
                    button_for_update.setEnabled(false);
                    button_for_delete.setEnabled(false);
                }
            }
        });
        
        frame_for_notify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                textfield_for_name.setText(null);
                textfield_for_roll_number.setText(null);
                textfield_for_phone_number.setText(null);
                textfield_for_email_id.setText(null);
                textfield_for_missing_objects.setText(null);
                textfield_for_description.setText(null);
                textfield_for_location.setText(null);
                textfield_for_date_of_lost.setText(null);
                textfield_for_status.setText(null);
                textfield_for_collected_by.setText(null);
                button_for_notify.setEnabled(false);
                button_for_common_mail.setEnabled(false);
                bg.clearSelection();
                
            }
        
        });
        /*
        * selected row's data is add to the textfeild in the frame
        */
        table_for_missing_objects.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int selected_row = table_for_missing_objects.getSelectedRow();
                    String id = (String) table_for_missing_objects.getValueAt(selected_row, 0);
                    ResultSet rs = st.executeQuery("select * from "
                            + "missing_object where Name = " + "'"+id+"'");   
                    while(rs.next()){
                        textfield_for_name.setText(rs.getString("Name"));
                        textfield_for_roll_number.setText(rs.getString("Roll_Number"));
                        textfield_for_phone_number.setText(rs.getString("Phone_Number"));
                        textfield_for_email_id.setText(rs.getString("Email_ID"));
                        textfield_for_missing_objects.setText(rs.getString("Missing_Object"));
                        textfield_for_description.setText(rs.getString("Description"));
                        textfield_for_location.setText(rs.getString("Location"));
                        textfield_for_date_of_lost.setText(rs.getString("Date"));
                        textfield_for_status.setText(rs.getString("Status"));
                        textfield_for_collected_by.setText(rs.getString("collected_by"));
                    }
                    
                }
                catch(Exception Ex){
                    System.out.println(Ex+ "  1");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               
            }
            @Override
            public void mouseExited(MouseEvent e) {
               
            }@Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });
        /*
        * onclick of button send mail  
        */
        button_for_notify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp_name = textfield_for_name.getText();
                String temp_mail_id = textfield_for_email_id.getText();
                String temp_mo = textfield_for_missing_objects.getText();
                String temp_loc = textfield_for_location.getText();
                String temp_date = textfield_for_date_of_lost.getText();
                String temp_rollno = textfield_for_roll_number.getText();
                
                //Textfeild input are passed to Mail_concept methode
                Notify_mail m = new Notify_mail(temp_name               
                        ,temp_mail_id,temp_mo,temp_loc,temp_date,temp_rollno);
                //refresh_table();                      //refreshs table  
            }
        });
        
        button_for_common_mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 common_mail();
            }
        });
        
        button_for_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
                    ps = conn.prepareStatement("UPDATE missing_object"
                            + " SET Name = ? ,Roll_Number = ? ,Phone_Number = ? "
                            + ",Email_ID = ? ,Missing_Object = ? ,Description = ? "
                            + ",Location = ?,Status = ?,Collected_by = ?"
                            + " where Roll_Number = ? and Description= ? ");
                    String tp_textfield_for_name =textfield_for_name.getText();                       
                    String tp_textfield_for_roll_number =textfield_for_roll_number.getText();
                    String tp_textfield_for_phone_number =textfield_for_phone_number.getText();
                    String tp_textfield_for_email_id =textfield_for_email_id.getText();
                    String tp_textfield_for_missing_objects =textfield_for_missing_objects.getText();
                    String tp_textfield_for_description =textfield_for_description.getText();
                    String tp_textfield_for_location =textfield_for_location.getText();
                    String tp_textfield_for_date_of_lost =textfield_for_date_of_lost.getText();
                    String tp_textfield_for_status =textfield_for_status.getText();
                    String tp_textfield_for_collected_by= textfield_for_collected_by.getText();
                    ps.setString(1, tp_textfield_for_name);
                    ps.setString(2, tp_textfield_for_roll_number);
                    ps.setString(3, tp_textfield_for_phone_number);
                    ps.setString(4, tp_textfield_for_email_id);
                    ps.setString(5, tp_textfield_for_missing_objects);
                    ps.setString(6, tp_textfield_for_description);
                    ps.setString(7, tp_textfield_for_location);
                    ps.setString(8, tp_textfield_for_status);
                    ps.setString(9, tp_textfield_for_collected_by);
                    ps.setString(10, tp_textfield_for_roll_number);
                    ps.setString(11, tp_textfield_for_description);
                    ps.executeUpdate();     
                    frame_for_notify.dispose();
                    new Return_object();
                }catch(Exception exe){
                     System.out.println(exe+"   1");
                }
            }
        });
        /*
        * Searches the selected missing object in the database 
        */
        button_for_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String Search;
                Search = (String)combobox_for_search.
                        getItemAt(combobox_for_search.getSelectedIndex());
                 if(Search.equals("Phone")|| Search.equals("ID Card")      
                         || Search.equals("Wallet") || Search.equals("Bag")
                         || Search.equals("Helmet")  || Search.equals("Key")){
                     search_class(Search);
                 }
                 else if(Search.equals("Full Table")){
                     showtable();
                
                 }
                 else if(Search.equals("Others")){
                     search_others(Search);
                 }
            }
        });
        frame_for_notify.add(scrollPane_for_missing_objects_table);
        frame_for_notify.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame_for_notify.setSize(1500, 700);
        frame_for_notify.getContentPane().setBackground(Color.white);
        frame_for_notify.setLayout(null);
        frame_for_notify.setVisible(true);
        frame_for_notify.setResizable(false);
        frame_for_notify.setLocationRelativeTo(null);
        
    }
//
private ArrayList<String> getComplaintList() { 
        ArrayList<String> rollnumberlist;
        rollnumberlist = new ArrayList();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Roll_Number from missing_object " );
            
            while (rs.next()) {
                String rollnumber_string = rs.getString("Roll_Number");
                rollnumberlist.add(rollnumber_string);
            }
            //removes Duplicate rollno to
            for(int i =0;i<rollnumberlist.size();i++){
                for(int j=i+1;j<rollnumberlist.size();j++){
                    if(rollnumberlist.get(i).equals(rollnumberlist.get(j))){
                        rollnumberlist.remove(j);
                        j--;
                       
                    }
                }
            }
        } catch(SQLException e) {
            System.out.println("Error 1 : " + e);
        }
        return rollnumberlist;
    }
    /*showtable
    * displays all data in the database
    */
    private void showtable() {
        int row_count = table_model.getRowCount();
        for(int i = row_count-1;i>=0;i--){
            table_model.removeRow(i);
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            Statement st = con.createStatement();
        
            for (String rollnumber_string: rollnumberlist) {
                
                ResultSet rs = st.executeQuery(
                        "select * from missing_object where"
                        + " Roll_Number = " + "'" + rollnumber_string + "'");
                
               while( rs.next()){
                    table_model.addRow(new Object[]{rs.getString("Name")
                            ,rollnumber_string,rs.getString("Phone_Number")
                            ,rs.getString("Email_ID"),rs.getString("Missing_Object")
                            ,rs.getString("Description"),rs.getString("Location")
                            ,rs.getString("Date"),rs.getString("Status")
                            ,rs.getString("Collected_by")});
               }
            }
        } catch(SQLException e){
            System.out.println("Error 2 : " + e);
        }
        
    }
    /*refresh_table
    * refrehes table data    
    */
    public void refresh_table() {  
      this.dialogbox_for_common_mail.dispose();           //disposes dialogbox_for_common_mail
      frame_for_notify.dispose();
      new Return_object();          
    }
    /*common_mail
    * sends mail to all students  
    */
    public void common_mail(){
        
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
        dialogbox_for_common_mail = new JDialog(frame_for_notify
                ,"Send Common Mail",Dialog.ModalityType.DOCUMENT_MODAL);
        Font cfontextfield_for_roll_number = new Font("Serif",Font.PLAIN,20);

        label_for_missing_objects_in_common_mail = new JLabel();
        label_for_missing_objects_in_common_mail.setText("Object Found");
        label_for_missing_objects_in_common_mail.setFont(metropolis.deriveFont(25f));
        label_for_missing_objects_in_common_mail.setBounds(30,30,200,30);
        dialogbox_for_common_mail.add(label_for_missing_objects_in_common_mail);

        textfield_for_missing_objects_in_common_mail = new JTextField();
        textfield_for_missing_objects_in_common_mail.setFont(cfontextfield_for_roll_number);
        textfield_for_missing_objects_in_common_mail.setBounds(260, 30, 200, 30);
        dialogbox_for_common_mail.add(textfield_for_missing_objects_in_common_mail);

        label_for_description_in_common_mail = new JLabel();
        label_for_description_in_common_mail.setText("Description");
        label_for_description_in_common_mail.setFont(metropolis.deriveFont(25f));
        label_for_description_in_common_mail.setBounds(30,90,200,30);
        dialogbox_for_common_mail.add(label_for_description_in_common_mail);

        textfield_for_description_in_common_mail = new JTextField();
        textfield_for_description_in_common_mail.setFont(cfontextfield_for_roll_number);
        textfield_for_description_in_common_mail.setBounds(260, 90, 200, 30);
        dialogbox_for_common_mail.add(textfield_for_description_in_common_mail);

        label_for_location_in_common_mail = new JLabel();
        label_for_location_in_common_mail.setText("Loction");
        label_for_location_in_common_mail.setFont(metropolis.deriveFont(25f));
        label_for_location_in_common_mail.setBounds(30,150,200,30);
        dialogbox_for_common_mail.add(label_for_location_in_common_mail);

        textfield_for_location_in_common_mail = new JTextField();
        textfield_for_location_in_common_mail.setFont(cfontextfield_for_roll_number);
        textfield_for_location_in_common_mail.setBounds(260, 150, 200, 30);
        dialogbox_for_common_mail.add(textfield_for_location_in_common_mail);

        label_for_date_found_in_common_mail = new JLabel();
        label_for_date_found_in_common_mail.setText("Date Found");
        label_for_date_found_in_common_mail.setFont(metropolis.deriveFont(25f));
        label_for_date_found_in_common_mail.setBounds(30,210,200,30);
        label_for_date_found_in_common_mail.setBackground(Color.white);
        dialogbox_for_common_mail.add(label_for_date_found_in_common_mail);

        textfield_for_date_found_in_common_mail = new JTextField();
        textfield_for_date_found_in_common_mail.setFont(cfontextfield_for_roll_number);
        textfield_for_date_found_in_common_mail.setBounds(260, 210, 200, 30);
        dialogbox_for_common_mail.add(textfield_for_date_found_in_common_mail);

        button_for_notify_in_common_mail = new JButton("Notify");
        button_for_notify_in_common_mail.setFont(cfontextfield_for_roll_number);
        button_for_notify_in_common_mail.setBounds(260,270,200,30);
        button_for_notify_in_common_mail.setBackground(Color.white);
        dialogbox_for_common_mail.getContentPane().setBackground(Color.white);
        dialogbox_for_common_mail.setLocationRelativeTo(null);
        dialogbox_for_common_mail.add(button_for_notify_in_common_mail);
        /*
        * check whether the input are null.
        * If NULL , displays error message
        * ELSE    , invoke main_concept_common
        */
        button_for_notify_in_common_mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String temp_name = "Unknown";
               String temp_rollno = "Unknown";
               String temp_phoneno = "Unknown";
               String temp_mail_id = "Unknown";
               String temp_mo = textfield_for_missing_objects_in_common_mail.getText();
               String temp_desc = textfield_for_description_in_common_mail.getText();
               String temp_loc = textfield_for_location_in_common_mail.getText();
               String temp_date = textfield_for_date_found_in_common_mail.getText();
                  if(temp_mo.isEmpty()||temp_desc.isEmpty()
                          ||temp_loc.isEmpty()||temp_date.isEmpty()){
                   JOptionPane.showMessageDialog(dialogbox_for_common_mail,
                           "Please fill all the textboxes and Try Again"
                           ,"Error",JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                    Mail_Concept_Common m = new Mail_Concept_Common(temp_name,
                            temp_rollno,temp_phoneno,temp_mail_id,temp_mo,
                            temp_desc,temp_loc,temp_date);

                    refresh_table();
                }

            }
        });

        dialogbox_for_common_mail.setSize(500, 500);
        dialogbox_for_common_mail.setLayout(null);
        dialogbox_for_common_mail.setVisible(true);
        dialogbox_for_common_mail.setResizable(false);
        dialogbox_for_common_mail.setLocationRelativeTo(null);
    }
    
    /*Search_class
    * it display the selected set of missing object complaint
    */
    public void search_class(String missing_object_string){
        int row_count = table_model.getRowCount();
        for(int i = row_count-1;i>=0;i--){
            table_model.removeRow(i);
        }
        show_search_list(missing_object_string);
    }
    //show search list display table with only seleted objects
    private void show_search_list(String missingString) {
        
        try {
                ResultSet rs2 = stextfield_for_roll_number
                        .executeQuery("select * from missing_object "
                                + "where Missing_Object = " + "'" 
                                + missingString + "'");
                    while(rs2.next()){
                    table_model.addRow(new Object[]{
                        rs2.getString("Name"),rs2.getString("Roll_number"),
                        rs2.getString("Phone_Number"),rs2.getString("Email_ID"),
                        rs2.getString("Missing_Object"),rs2.getString("Description"),
                        rs2.getString("Location"),rs2.getString("Date"),
                        rs2.getString("Status"),rs2.getString("Collected_by")});
                    }
                
        } catch (SQLException e) {
            System.out.println("Error ShowTable : " + e);
        }
    
}
//display only missing_object seleted other
 private void search_others(String others) {
     int row_count = table_model.getRowCount();
        for(int i = row_count-1;i>=0;i--){
            table_model.removeRow(i);
        }
        try {
            ResultSet rs = st.executeQuery("select * from missing_object ");
            while(rs.next()){
                if(((!rs.getString("Missing_Object").equals("Phone") 
                        && !rs.getString("Missing_Object").equals("ID Card"))
                        && !rs.getString("Missing_Object").equals("Wallet"))
                        && !rs.getString("Missing_Object").equals("Helmet")
                        && !rs.getString("Missing_Object").equals("Key")
                        &&!rs.getString("Missing_Object").equals("Bag")){
                    table_model.addRow(new Object[]{rs.getString("Name")
                            ,rs.getString("Roll_number")
                            ,rs.getString("Phone_Number")
                            ,rs.getString("Email_ID")
                            ,rs.getString("Missing_Object")
                            ,rs.getString("Description")
                            ,rs.getString("Location")
                            ,rs.getString("Date")
                            ,rs.getString("Status")
                            ,rs.getString("Collected_by")
                    });
                } 
            }
        }catch (SQLException e) {
            System.out.println("Error 2 : " + e);
        }
    }
 public static void main(String[] args) {
        new Return_object();
    }
}