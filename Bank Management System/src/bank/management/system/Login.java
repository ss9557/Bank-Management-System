package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
    
    JButton login, signup, Clear;
    JTextField CardTextField; 
    JPasswordField pinTextField;
    
    Login(){
        
        setTitle("Automated Teller Machine");
        
        setLayout (null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);
        
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(250,40,400,40);
        text.setForeground(Color.red);
        add(text);
        
        JLabel CardNumber = new JLabel("Card No. : ");
        CardNumber.setFont(new Font("Raleway", Font.BOLD, 28));
        CardNumber.setBounds(120, 150,150,30);
        CardNumber.setForeground(Color.white);
        add(CardNumber);
        
        CardTextField = new JTextField();
        CardTextField.setBounds(300, 150, 250, 30);
        CardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(CardTextField);
        
        JLabel Pin = new JLabel("PIN :");
        Pin.setFont(new Font("Osward", Font.BOLD, 28));
        Pin.setBounds(120,220,250,30);
        Pin.setForeground(Color.white);
        add(Pin);
        
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
        
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
//        login.setBackground(Color.white);
//        login.setForeground(Color.green);
        login.addActionListener(this);
        add(login); 
        
        Clear = new JButton("CLEAR");
        Clear.setBounds(430, 300, 100, 30);
//        login.setBackground(Color.white);
//        login.setForeground(Color.green);
        Clear.addActionListener(this);
        add(Clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
//        login.setBackground(Color.white);
//        login.setForeground(Color.green);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.black);
       
        setSize(800,500);
        setVisible(true);
        setLocation(350,190);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Clear){
             CardTextField.setText("");
             pinTextField.setText("");
            
            
        } else if (ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = CardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
        } else if(ae.getSource() == signup){
            setVisible(false);
            new SignupOne().setVisible(true);
            
        }
        
        
        
    }
    public static void main(String args[]){
        new Login();
    }
}