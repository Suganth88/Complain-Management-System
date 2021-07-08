/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComplainManagementSystem;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author sugan
 */
public class Admin_view_hostel {
    private Connection conn = null;
    static JButton table_title,jbutton_back,jbutton_update;
    JFrame frame = new JFrame("Complaints Registered");
    
    DefaultTableModel table_model = new DefaultTableModel(
            new Object[]{"Name","Roll No","Email"
                    ,"Block","Room","Complain","Description","Date","Status","UID"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false ;
        }
    };
    ArrayList<String> rollnumberlist;
    ArrayList<String> searchresultlist;
    JScrollPane scrollPane_for_table;
    JTable table_for_complains;
    JFrame update_frame;
    JLabel jlabel_unique;
    JTextField jtextfield_text;
    //JButton table_title,jbutton_update,jbutton_back;
    Beans bd=new Beans();
    
    public Admin_view_hostel() {
        components();
        this.rollnumberlist = getComplaintList();
        showtable();
    }
    public void components(){
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
        JLabel label_heading = new JLabel("Your Complains on College");
        label_heading.setFont(metropolis);
        label_heading.setBounds(30, 30, 400, 30);
        frame.add(label_heading);
        table_for_complains = new JTable();
        table_for_complains.setModel(table_model);
        table_for_complains.setBackground(Color.white);
        table_for_complains.getTableHeader().setBackground(Color.white);
        table_for_complains.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_for_complains.setFillsViewportHeight(true);
        table_for_complains.setFocusable(false);
        table_for_complains.setRowSelectionAllowed(true);
        table_for_complains.setColumnSelectionAllowed(false);
        table_for_complains.setFont(opensans.deriveFont(15f));
        table_for_complains.setRowHeight(40);
        scrollPane_for_table = new JScrollPane(table_for_complains);
        scrollPane_for_table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_for_table.setLocation(30, 90);
        scrollPane_for_table.setSize(1420, 230);
        
        JTableHeader tableHeader = table_for_complains.getTableHeader();
        tableHeader.setFont(metropolis.deriveFont(22f));
        Font fontextfield_for_name = new Font("Serif",Font.PLAIN,20);
        
        JLabel label_description = new JLabel("If you complains are processing click on update and enter the UID to mark it as Processing (Or) Click Back to go to Home Page");
        label_description.setBounds(300,340,880,30);
        label_description.setFont(opensans.deriveFont(14f));
        frame.add(label_description);
        
        JLabel back_button = new JLabel();
        ImageIcon back_hover = new ImageIcon(getClass().getResource("button_back (3).png"));
        ImageIcon back_normal = new ImageIcon(getClass().getResource("button_back (2).png"));
        back_button.setIcon(back_normal);
        back_button.setBounds(820, 410, 197, 69);
        frame.add(back_button);
        
        back_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                frame.dispose();
                new Admin_main_frame();
            }  
            public void mouseEntered(MouseEvent e) {
                back_button.setIcon(back_hover);
            }
            public void mouseExited(MouseEvent e) {
                back_button.setIcon(back_normal);
            } 
        } );
        
        JLabel update_button = new JLabel("Update");
        ImageIcon update_hover = new ImageIcon(getClass().getResource("button_update.png"));
        ImageIcon update_normal = new ImageIcon(getClass().getResource("button_update (1).png"));
        update_button.setIcon(update_normal);
        update_button.setBounds(540, 410, 197, 69);
        frame.add(update_button);
        
        update_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                updateHostel();
            }  
            public void mouseEntered(MouseEvent e) {
                update_button.setIcon(update_hover);
            }
            public void mouseExited(MouseEvent e) {
                update_button.setIcon(update_normal);
            } 
        } );
        
        frame.add(scrollPane_for_table);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1500, 600);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private ArrayList<String> getComplaintList() { 
        ArrayList<String> rollnumberlist;
        rollnumberlist = new ArrayList();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Rollno from complaint_hostel " );
            
            while (rs.next()) {
                String rollnumber_string = rs.getString("Rollno");
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
                        "select * from complaint_hostel where"
                        + " Rollno = " + "'" + rollnumber_string + "'");
                
               while( rs.next()){
                    table_model.addRow(new Object[]{rs.getString("Name")
                            ,rollnumber_string,rs.getString("Email"),rs.getString("Block")
                            ,rs.getString("Room_No"),rs.getString("Complain")
                            ,rs.getString("Complaint_Description"),rs.getString("Date")
                            ,rs.getString("Status"),rs.getString("UID")});
               }
            }
        } catch(SQLException e){
            System.out.println("Error 2 : " + e);
        }
        
    }
    public void updateHostel(){
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
        JDialog d1 = new JDialog(frame,"Enter UID",Dialog.ModalityType.DOCUMENT_MODAL);
        
        JLabel title = new JLabel("Enter UID");
        title.setFont(metropolis);
        title.setBounds(175,30,400,30);
        d1.add(title);
        
        JLabel des = new JLabel("Enter UID of the complain solved to mark it as resolved");
        des.setFont(opensans.deriveFont(15f));
        des.setBounds(40,70,400,30);
        d1.add(des);
        
        jtextfield_text = new JTextField();
        jtextfield_text.setBounds(140,120,197,30);
        jtextfield_text.setFont(opensans.deriveFont(15f));
        d1.add(jtextfield_text);
        
        JLabel update_button = new JLabel("Update");
        ImageIcon update_hover = new ImageIcon(getClass().getResource("button_update.png"));
        ImageIcon update_normal = new ImageIcon(getClass().getResource("button_update (1).png"));
        update_button.setIcon(update_normal);
        update_button.setBounds(140, 180, 197, 69);
        d1.add(update_button);
        
        update_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                String str_uniqueid = jtextfield_text.getText();
                String str_res = "Processing";
                try{
                    UIManager UI=new UIManager();
                    UI.put("OptionPane.background", Color.white);
                    UI.put("Panel.background", Color.white);
                    //loading Driver
                    Class.forName("com.mysql.jdbc.Driver");
                    //Creating Connection
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/complain_mangement_system","suganth","suganth123");
                    String sql = "update complaint_hostel SET Status = ?"
                            + " WHERE UID=?";
                    //Creating PreparedStatement
                    PreparedStatement pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,str_res);
                    pstmt.setString(2,str_uniqueid);
                    //Executing Query
                    pstmt.executeUpdate();
                    String s = "select * from Complaint_college "
                            + "WHERE UID='" + str_uniqueid + "'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(s);
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null
                                ,"Updation Success!"
                                ,"Success",JOptionPane.INFORMATION_MESSAGE);
                        d1.dispose();
                        frame.dispose();
                        new Admin_view_hostel();
                        
                    }else{
                        
                        JOptionPane.showMessageDialog(null,
                                "Error! Name not found.","Failed",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch(Exception ed){
                    System.out.println(ed);
                }
            }  
            public void mouseEntered(MouseEvent e) {
                update_button.setIcon(update_hover);
            }
            public void mouseExited(MouseEvent e) {
                update_button.setIcon(update_normal);
            } 
        } );
        
        d1.setSize(500,350);
        d1.setResizable(false);
        d1.getContentPane().setBackground(Color.white);
        d1.setLayout(null);
        d1.setLocationRelativeTo(null);
        d1.setVisible(true);
        
    }
    
    
}
