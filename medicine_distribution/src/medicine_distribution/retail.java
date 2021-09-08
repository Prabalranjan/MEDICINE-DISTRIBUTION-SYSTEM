package medicine_distribution;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class retail {

	private JFrame frame;
	private JTextField taxbox;
	private JTable table;
	private JTextField totalbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					retail window = new retail();
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
	public retail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1172, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medicine Distribution System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(267, 25, 639, 82);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(56, 102, 995, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Types Of Meds");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(56, 104, 258, 54);
		frame.getContentPane().add(lblNewLabel_1);
		
		JRadioButton syrup = new JRadioButton("Syrup");
		syrup.setFont(new Font("Tahoma", Font.BOLD, 15));
		syrup.setBounds(56, 164, 88, 21);
		frame.getContentPane().add(syrup);
		
		JRadioButton capsule = new JRadioButton("Capsule");
		capsule.setFont(new Font("Tahoma", Font.BOLD, 15));
		capsule.setBounds(166, 164, 103, 21);
		frame.getContentPane().add(capsule);
		
		JRadioButton powder = new JRadioButton("Powder");
		powder.setFont(new Font("Tahoma", Font.BOLD, 15));
		powder.setBounds(56, 219, 88, 21);
		frame.getContentPane().add(powder);
		
		JComboBox combobox = new JComboBox();
		combobox.setMaximumRowCount(18);
		combobox.setFont(new Font("Tahoma", Font.BOLD, 16));
		combobox.setModel(new DefaultComboBoxModel(new String[] {"ELECTROL POWER", "PANTOCID 40", "LIV 52 TAB", "LIB 52 SYRUP", "SOBILIN SYRUP", "DEXORANGE SYRUP", "GLYCOMET 500 MG TAB", "TELMA 20 TAB", "TELMA 40 TAB", "PAN 40 TAB", "PAN D TAB", "OMEZ TAB", "NIZE TAB", "STAMLO 5 TAB", "SINAREST TAB", "SINAREST SYP", "MMOOV  OINT", "VOLINI GEL", "SORIDON TAB", "DISPRIN TAB", "NEOSPORIN POWDER", "NEOSPORIN OINT"}));
		combobox.setBounds(361, 137, 201, 21);
		frame.getContentPane().add(combobox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(56, 284, 292, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tax");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(71, 316, 100, 37);
		frame.getContentPane().add(lblNewLabel_2);
		
		taxbox = new JTextField();
		taxbox.setBounds(214, 324, 134, 28);
		frame.getContentPane().add(taxbox);
		taxbox.setColumns(10);
		
		
//		create every column to show in the jpanel
		DefaultTableModel dm = new DefaultTableModel();
		table = new JTable(dm);
		dm.addColumn("syrup");
		dm.addColumn("capsule");
		dm.addColumn("power");
		dm.addColumn("med_name");
		dm.addColumn("tax");
		dm.addColumn("total");
		dm.addRow(new Object[] {"syrup","capsule","power","med_name","tax","total"});
		dm.addRow(new Object[] {"","","","","","",""}); 
		
		JButton btnNewButton = new JButton("Total");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				add data to the retail table
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					PreparedStatement ps = conn.prepareStatement("insert into retail(syrup,capsule,power,med_name,tax,total) values(?,?,?,?,?,?);");	
					if(syrup.isSelected())
					{					
						ps.setString(1, syrup.getText());
						ps.setString(2, "");
						ps.setString(3, "");
					}
					if(capsule.isSelected())
					{
						ps.setString(1, "");
						ps.setString(2, capsule.getText());
						ps.setString(3, "");

					}
					if(powder.isSelected())
					{
						ps.setString(1, "");
						ps.setString(2, "");
						ps.setString(3, powder.getText());
					}
					ps.setString(4, combobox.getSelectedItem().toString());
					ps.setString(5, taxbox.getText());
					ps.setString(6, totalbox.getText());

					syrup.requestFocus();
					int x = ps.executeUpdate();
					if(x > 0)
					{
//						System.out.println("data added seccessfully");
						JOptionPane.showMessageDialog(null, "Record added !!!");
						
						
//						this is display
						try {
//							Class.forName("com.mysql.cj.jdbs.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
							String sql ="select * from retail";
							ps = conn.prepareStatement(sql);	
							ResultSet rs = ps.executeQuery();
							//bikas
							dm.setRowCount(0);
							while (rs.next()) {			
								dm.addRow(new Object[] {rs.getString("syrup"),rs.getString("capsule"),rs.getString("power"),rs.getString("med_name"),rs.getInt("tax"),rs.getInt("total")});
							}
							//end bikas
						}
						catch(Exception e2)
						{
//							System.out.println(e1);
							JOptionPane.showMessageDialog(null, e2);
						}
						
					}else
					{
					}
//					syrup.setText("");
//					capsule.setText("");
//					powder.setText("");
//					combobox.setText("");
					taxbox.setText("");
					totalbox.setText("");
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
				
				
	
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(68, 500, 103, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.setBounds(212, 500, 103, 42);
		frame.getContentPane().add(btnReset);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
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
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(1045, 519, 103, 42);
		frame.getContentPane().add(btnNewButton_1_1);
		
//		table = new JTable();
		table.setBounds(575, 130, 573, 382);
		frame.getContentPane().add(table);
		
		totalbox = new JTextField();
		totalbox.setColumns(10);
		totalbox.setBounds(214, 407, 134, 28);
		frame.getContentPane().add(totalbox);
		
		JLabel lblNewLabel_2_1 = new JLabel("Total");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(68, 399, 103, 37);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Asus\\Downloads\\med bag.png"));
		lblNewLabel_3.setBounds(41, 0, 125, 105);
		frame.getContentPane().add(lblNewLabel_3);
		
		
//		this is to refesh the panel
		try {
//			Class.forName("com.mysql.cj.jdbs.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
			String sql ="select * from retail";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery();
			//bikas
			dm.setRowCount(0);
			while (rs.next()) {			
				dm.addRow(new Object[] {rs.getString("syrup"),rs.getString("capsule"),rs.getString("power"),rs.getString("med_name"),rs.getInt("tax"),rs.getInt("total")});
			}
			//end bikas
		}
		catch(Exception e2)
		{
//			System.out.println(e1);
			JOptionPane.showMessageDialog(null, e2);
		}
	}
}
