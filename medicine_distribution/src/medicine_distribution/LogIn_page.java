package medicine_distribution;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn_page {

	private JFrame frame;
	private JTextField txtname;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn_page window = new LogIn_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 431, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(46, 81, 116, 47);
		frame.getContentPane().add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setBounds(46, 138, 296, 40);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(46, 188, 116, 47);
		frame.getContentPane().add(lblPassword);
		
		JButton login = new JButton("Log In");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtpassword.getText();
				String name = txtname.getText();

				if(password.contains("12345") && name.contains("prabal"))
				{
					txtpassword.setText(null);
					txtname.setText(null);
					
					Medicine_distribution info = new Medicine_distribution();
					Medicine_distribution.main(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
					txtpassword.setText(null);
					txtname.setText(null);
				}
				
			}
		});
		login.setIcon(new ImageIcon("C:\\Users\\Asus\\Downloads\\key.png"));
		login.setFont(new Font("Tahoma", Font.BOLD, 15));
		login.setBounds(31, 333, 116, 40);
		frame.getContentPane().add(login);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(46, 245, 296, 40);
		frame.getContentPane().add(txtpassword);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Asus\\Downloads\\login (2).png"));
		lblNewLabel_1.setBounds(165, 10, 108, 64);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText(null);
				txtpassword.setText(null);
			}
		});
		clear.setFont(new Font("Tahoma", Font.BOLD, 15));
		clear.setBounds(157, 333, 116, 40);
		frame.getContentPane().add(clear);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Login System",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
				{
					System.exit(0);
				}
				{
					System.exit(0);
				}
			
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		exit.setBounds(283, 333, 116, 40);
		frame.getContentPane().add(exit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 307, 350, 2);
		frame.getContentPane().add(separator);
	}
}
