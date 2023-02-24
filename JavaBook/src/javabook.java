import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class javabook {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtbedition;
	private JTextField txtbprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javabook window = new javabook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","Wrqgwjkp@967@#5");
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void table_load() {
		try {
			pst=con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public javabook() {
		initialize();
		connect();
		table_load();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 674, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Store");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(270, 11, 117, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 83, 289, 154);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 38, 76, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Edition");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 69, 76, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 101, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtbname = new JTextField();
		txtbname.setBounds(113, 36, 123, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtbedition = new JTextField();
		txtbedition.setBounds(113, 67, 123, 20);
		panel.add(txtbedition);
		txtbedition.setColumns(10);
		
		txtbprice = new JTextField();
		txtbprice.setBounds(113, 99, 123, 20);
		panel.add(txtbprice);
		txtbprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price;
				bname = txtbname.getText();
				edition = txtbedition.getText();
				price = txtbprice.getText();
				
				try {
					String query = "insert into book (name, edition, price)values(?,?,?)";
					pst=con.prepareStatement(query);
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!!");
					table_load();
					txtbname.setText("");
					txtbedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 248, 74, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(115, 248, 74, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtbedition.setText("");
				txtbprice.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(224, 248, 75, 32);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(334, 83, 289, 208);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 297, 289, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Book id");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 24, 55, 14);
		panel_1.add(lblNewLabel_4);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					 try {
				          
				            String id = txtbid.getText();

				                pst = con.prepareStatement("select name,edition,price from book where id = ?");
				                pst.setString(1, id);
				                ResultSet rs = pst.executeQuery();

				            if(rs.next()==true)
				            {
				              
				                String name = rs.getString(1);
				                String edition = rs.getString(2);
				                String price = rs.getString(3);
				                
				                txtbname.setText(name);
				                txtbedition.setText(edition);
				                txtbprice.setText(price);
				                
				                
				            }   
				            else
				            {
				            	txtbname.setText("");
				            	txtbedition.setText("");
				                txtbprice.setText("");
				                 
				            }
				            


				        } 
					
					 catch (SQLException ex) {
				           
				        }
			}
		});
		txtbid.setBounds(85, 21, 107, 20);
		panel_1.add(txtbid);
		txtbid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price,bid;
				bname = txtbname.getText();
				edition = txtbedition.getText();
				price = txtbprice.getText();
				bid = txtbid.getText();
				
				try {
					String query = "update book set name=?, edition=?, price=? where id=?";
					pst=con.prepareStatement(query);
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!");
					table_load();
					txtbname.setText("");
					txtbedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(355, 312, 89, 40);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				bid = txtbid.getText();
				
				try {
					String query = "delete from book where id=?";
					pst=con.prepareStatement(query);
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!!");
					table_load();
					txtbname.setText("");
					txtbedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(472, 312, 89, 40);
		frame.getContentPane().add(btnNewButton_4);
	}
}
