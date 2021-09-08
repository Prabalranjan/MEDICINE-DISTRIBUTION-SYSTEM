package medicine_distribution;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class Medicine_distribution {

	private JFrame frame;
	private JTable table;
	private JTextField txtstock;
	private JTextField txtbuyer;
	private JTextField txtorder;
	static int selId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medicine_distribution window = new Medicine_distribution();
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
	public Medicine_distribution() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1110, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MEDICINE DISTRIBUTION SYSTEM");
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 40));
		lblNewLabel.setBounds(253, 31, 738, 66);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "BUYER DETAILS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(54, 145, 429, 255);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("STOCK");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(47, 38, 107, 48);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("BUYER");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(46, 98, 107, 48);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ORDER");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(47, 171, 107, 48);
		panel.add(lblNewLabel_1_2);
		
		txtstock = new JTextField();
		txtstock.setBounds(194, 56, 204, 19);
		panel.add(txtstock);
		txtstock.setColumns(10);
		
		txtbuyer = new JTextField();
		txtbuyer.setColumns(10);
		txtbuyer.setBounds(194, 119, 204, 19);
		panel.add(txtbuyer);
		
		txtorder = new JTextField();
		txtorder.setColumns(10);
		txtorder.setBounds(194, 189, 204, 19);
		panel.add(txtorder);
		
//		creates coloumns for jpanel
		//bikas
		DefaultTableModel dm = new DefaultTableModel();
		table = new JTable(dm);
		dm.addColumn("id");
		dm.addColumn("stock");
		dm.addColumn("buyer");
		dm.addColumn("total_order");
		dm.addRow(new Object[] {"ID","stock","buyer","total_order"});
		dm.addRow(new Object[] {"","","",""}); 
		
		//end bikas

//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null},
//			},
//			new String[] {
//				"quantity", "product", "packing"
//			}
//		));
		table.setBounds(545, 130, 525, 295);
		frame.getContentPane().add(table);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					PreparedStatement ps = conn.prepareStatement("insert into medicine3(stock,buyer,total_order)values(?,?,?);");	
//					ps.setInt(1, Integer.parseInt(txtstock.getText()));
//					ps.setString(2, txtbuyer.getText());
//					ps.setInt(3, Integer.parseInt(txtorder.getText()));
					ps.setString(1, txtstock.getText());
					ps.setString(2, txtbuyer.getText());
					ps.setString(3, txtorder.getText());
					txtstock.requestFocus();
					int x = ps.executeUpdate();
					if(x > 0)
					{
//						System.out.println("data added seccessfully");
						JOptionPane.showMessageDialog(new JFrame(), "Record added !!!");
						
					}else
					{
					}
					txtstock.setText("");
					txtbuyer.setText("");
					txtorder.setText("");
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}

				
//				to display the code on the jpanel.
				try {
//					Class.forName("com.mysql.cj.jdbs.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					String sql ="select * from medicine3";
					PreparedStatement ps = conn.prepareStatement(sql);	
					ResultSet rs = ps.executeQuery();
					//bikas
					dm.setRowCount(0);
					while (rs.next()) {			
						dm.addRow(new Object[] {rs.getInt("id"),rs.getInt("stock"),rs.getString("buyer"),rs.getInt("total_order")});
					}
					//end bikas
				}
				catch(Exception e2)
				{
//					System.out.println(e1);
					JOptionPane.showMessageDialog(null, e2);
				}
			}
			
		});
		save.setFont(new Font("Tahoma", Font.BOLD, 15));
		save.setBounds(44, 448, 121, 51);
		frame.getContentPane().add(save);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtstock.setText(null);
				txtbuyer.setText(null);
				txtorder.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(182, 448, 121, 51);
		frame.getContentPane().add(btnClear);
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				selId = (int) table.getValueAt(table.getSelectedRow(), 0);
				txtstock.setText(Integer.toString((int) table.getValueAt(table.getSelectedRow(), 1)));
				txtbuyer.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				txtorder.setText(Integer.toString((int) table.getValueAt(table.getSelectedRow(), 3)));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton btnClear_1 = new JButton("Update");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = selId;
				int stock = Integer.parseInt(txtstock.getText());
				String buyer = txtbuyer.getText();
				int order = Integer.parseInt(txtorder.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					String query = "update medicine3 set stock=?,buyer=?,total_order=? where id=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, stock);
					pstmt.setString(2, buyer);
					pstmt.setInt(3,order);
					pstmt.setInt(4, id);
					int flag = pstmt.executeUpdate();
					if (flag == 1) {
						JOptionPane.showMessageDialog(new JFrame(), "Updated");
						dm.setRowCount(2);
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
							String sql ="select * from medicine3";
							PreparedStatement ps = conn.prepareStatement(sql);	
							ResultSet rs = ps.executeQuery();
							//bikas
							while (rs.next()) {			
								dm.addRow(new Object[] {rs.getInt("id"),rs.getInt("stock"),rs.getString("buyer"),rs.getInt("total_order")});
							}
							//end bikas
						}
						catch(Exception e2)
						{
//							System.out.println(e1);
							JOptionPane.showMessageDialog(null, e2);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "failed");
					}
				}catch(Exception e0) {
					e0.printStackTrace();
				}
			}
		});
		btnClear_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear_1.setBounds(598, 448, 121, 51);
		frame.getContentPane().add(btnClear_1);
		
		JButton btnClear_2 = new JButton("Delete");
		btnClear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = selId;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					String query = "delete from medicine3 where id=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, id);
					int flag = pstmt.executeUpdate();
					if (flag == 1) {
						JOptionPane.showMessageDialog(new JFrame(), "Deleted!");
						dm.setRowCount(2);
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
							String sql ="select * from medicine3";
							PreparedStatement ps = conn.prepareStatement(sql);	
							ResultSet rs = ps.executeQuery();
							//bikas
							while (rs.next()) {			
								dm.addRow(new Object[] {rs.getInt("id"),rs.getInt("stock"),rs.getString("buyer"),rs.getInt("total_order")});
							}
							//end bikas
						}
						catch(Exception e2)
						{
//							System.out.println(e1);
							JOptionPane.showMessageDialog(null, e2);
						}
					}
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnClear_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear_2.setBounds(754, 448, 121, 51);
		frame.getContentPane().add(btnClear_2);
		
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
				
				try {
//					Class.forName("com.mysql.cj.jdbs.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
					String sql ="select * from medicine3";
					PreparedStatement ps = conn.prepareStatement(sql);	
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						dm.addRow(new Object[] {rs.getInt("id"),rs.getInt("stock")});
					}
					
				}
				catch(Exception e2)
				{
//					System.out.println(e1);
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		exit.setBounds(922, 448, 121, 51);
		
		
		try {
//			Class.forName("com.mysql.cj.jdbs.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medic_crud","root","");
			String sql ="select * from medicine3";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery();
			//bikas
			while (rs.next()) {			
				dm.addRow(new Object[] {rs.getInt("id"),rs.getInt("stock"),rs.getString("buyer"),rs.getInt("total_order")});
			}
			//end bikas
		}
		catch(Exception e2)
		{
//			System.out.println(e1);
			JOptionPane.showMessageDialog(null, e2);
		}
		
		frame.getContentPane().add(exit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(174, 109, 885, 11);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Asus\\Downloads\\med final.png"));
		lblNewLabel_2.setBounds(34, 10, 158, 141);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Asus\\Downloads\\next.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				retail info = new retail();
				retail.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(329, 448, 115, 51);
		frame.getContentPane().add(btnNewButton);
	}
}
