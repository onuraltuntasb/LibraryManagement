package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.LibraryCard;
import model.User;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibrarianGUI extends JFrame {

	static User user = new User();

	private JPanel w_pane;
	private JLabel lbl_welcome;
	private JTextField txt_email;
	private JTextField txt_name;
	private JTextField txt_street;
	private JTextField txt_city;
	private JTextField txt_state;
	private JTextField txt_zipcode;
	private JTextField txt_password;
	private JTextField txt_phone;
	private JTextField txt_country;
	private JTable table_show;
	
	private JComboBox cb_user_type;
	private JComboBox cb_status;
	
	
	private int delUserId = -1;
	private int delUserAddressId = -1;
	private int delUserLibraryCardId = -1;
	
	private DefaultTableModel userTableModel = null;
	private Object[] userTableData = null;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianGUI frame = new LibrarianGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibrarianGUI(User user) {
		setTitle("Librarian Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		lbl_welcome = new JLabel("Welcome " + user.getName());
		lbl_welcome.setBounds(12, 15, 336, 15);
		w_pane.add(lbl_welcome);

		JTabbedPane tab_pane = new JTabbedPane(JTabbedPane.TOP);
		tab_pane.setBounds(12, 40, 1342, 692);
		w_pane.add(tab_pane);
		
		JPanel tab_user_ops = new JPanel();
		tab_pane.addTab("User", null, tab_user_ops, null);
		tab_user_ops.setLayout(null);
		
		JLabel lbl_name = new JLabel("name :");
		lbl_name.setBounds(31, 37, 70, 15);
		tab_user_ops.add(lbl_name);
		
		JLabel lbl_email = new JLabel("email :");
		lbl_email.setBounds(31, 12, 70, 15);
		tab_user_ops.add(lbl_email);
		
	 cb_user_type = new JComboBox();
		cb_user_type.setBounds(128, 87, 220, 24);
		tab_user_ops.add(cb_user_type);
		
		JLabel lbl_user_type = new JLabel("type :");
		lbl_user_type.setBounds(31, 87, 70, 15);
		tab_user_ops.add(lbl_user_type);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(128, 12, 220, 19);
		tab_user_ops.add(txt_email);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(128, 37, 220, 19);
		tab_user_ops.add(txt_name);
		
		JLabel lbl_street = new JLabel("street :");
		lbl_street.setBounds(420, 62, 70, 15);
		tab_user_ops.add(lbl_street);
		
		JLabel lbl_city = new JLabel("city :");
		lbl_city.setBounds(420, 87, 70, 15);
		tab_user_ops.add(lbl_city);
		
		JLabel lbl_state = new JLabel("state :");
		lbl_state.setBounds(420, 112, 70, 15);
		tab_user_ops.add(lbl_state);
		
		txt_street = new JTextField();
		txt_street.setColumns(10);
		txt_street.setBounds(495, 62, 220, 19);
		tab_user_ops.add(txt_street);
		
		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(495, 87, 220, 19);
		tab_user_ops.add(txt_city);
		
		txt_state = new JTextField();
		txt_state.setColumns(10);
		txt_state.setBounds(495, 112, 220, 19);
		tab_user_ops.add(txt_state);
		
		JLabel lbl_zipcode = new JLabel("zipcode :");
		lbl_zipcode.setBounds(420, 137, 70, 15);
		tab_user_ops.add(lbl_zipcode);
		
		txt_zipcode = new JTextField();
		txt_zipcode.setColumns(10);
		txt_zipcode.setBounds(495, 137, 220, 19);
		tab_user_ops.add(txt_zipcode);
		
		JLabel lbl_password = new JLabel("password :");
		lbl_password.setBounds(31, 62, 70, 15);
		tab_user_ops.add(lbl_password);
		
		txt_password = new JTextField();
		txt_password.setColumns(10);
		txt_password.setBounds(128, 60, 220, 19);
		tab_user_ops.add(txt_password);
		
		JLabel lbl_status = new JLabel("status :");
		lbl_status.setBounds(31, 112, 70, 15);
		tab_user_ops.add(lbl_status);
		
		 cb_status = new JComboBox();
		cb_status.setBounds(128, 112, 220, 24);
		tab_user_ops.add(cb_status);
		
		JLabel lbl_phone = new JLabel("phone :");
		lbl_phone.setBounds(420, 37, 70, 15);
		tab_user_ops.add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(495, 37, 220, 19);
		tab_user_ops.add(txt_phone);
		
		JLabel lbl_country = new JLabel("country :");
		lbl_country.setBounds(420, 12, 70, 15);
		tab_user_ops.add(lbl_country);
		
		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(495, 12, 220, 19);
		tab_user_ops.add(txt_country);
		
		JButton btn_add = new JButton("add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_add_ActionPerformed();
			}

		});
		btn_add.setBounds(31, 212, 80, 25);
		tab_user_ops.add(btn_add);
		
		JButton btn_edit = new JButton("edit");
		btn_edit.setBounds(146, 212, 80, 25);
		tab_user_ops.add(btn_edit);
		
		JButton btn_delete = new JButton("delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_delete_ActionPerformed();
			}
		});
		btn_delete.setBounds(261, 212, 80, 25);
		tab_user_ops.add(btn_delete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 268, 1275, 364);
		tab_user_ops.add(scrollPane);
		
		table_show = new JTable();
		scrollPane.setViewportView(table_show);
		
		table_show.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println(e);

				if (e.getType() == TableModelEvent.UPDATE) {

					int updateUserId = (int) table_show.getValueAt(table_show.getSelectedRow(), 0);
					String selectPassword = (String) table_show.getValueAt(table_show.getSelectedRow(), 1);
					String selectStatus = (String) table_show.getValueAt(table_show.getSelectedRow(), 2);
					String selectName = (String) table_show.getValueAt(table_show.getSelectedRow(), 3);
					String selectEmail = (String) table_show.getValueAt(table_show.getSelectedRow(), 4);
					String selectPhone = (String) table_show.getValueAt(table_show.getSelectedRow(), 5);
					String selectUserType = (String) table_show.getValueAt(table_show.getSelectedRow(), 6);
					int selectTBC = (int) table_show.getValueAt(table_show.getSelectedRow(), 7);
					if (user.updateUser(selectPassword, selectStatus, selectName, selectEmail, selectPhone,
							selectUserType, selectTBC, updateUserId)) {
						userTableFetch();
					}
				}

			}
		});

		table_show.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// System.out.println(e);
				try {
					delUserId = (int) table_show.getValueAt(table_show.getSelectedRow(), 0);
					delUserAddressId = (int) table_show.getValueAt(table_show.getSelectedRow(), 9);
					delUserLibraryCardId = (int) table_show.getValueAt(table_show.getSelectedRow(), 10);
				} catch (Exception e2) {

				}

			}
		});

		JButton btn_logout = new JButton("Log out");
		btn_logout.setBounds(1231, 15, 117, 25);
		w_pane.add(btn_logout);
		
		userTableFetch();

	}
	
	public void btn_add_ActionPerformed() {
		if (user.addUser(txt_street.getText(), txt_city.getText(), txt_state.getText(), txt_zipcode.getText(),
				txt_country.getText(), txt_password.getText(), String.valueOf(cb_status.getSelectedItem()),
				txt_name.getText(), txt_email.getText(), txt_phone.getText(),
				String.valueOf(cb_user_type.getSelectedItem()), new Date(), LibraryCard.createBarcode(), new Date(),
				1)) {
			userTableFetch();
		}
		;

	}

	public void btn_delete_ActionPerformed() {
		if (user.deleteUser(delUserId, delUserAddressId, delUserLibraryCardId)) {
			userTableFetch();
		}

	}

	public void userTableFetch() {

		userTableModel = new DefaultTableModel();
		Object[] colUserTableName = new Object[11];
		colUserTableName[0] = "ID";
		colUserTableName[1] = "Password";
		colUserTableName[2] = "Status";
		colUserTableName[3] = "Name";
		colUserTableName[4] = "Email";
		colUserTableName[5] = "Phone";
		colUserTableName[6] = "UserType";
		colUserTableName[7] = "DOM";
		colUserTableName[8] = "TBC";
		colUserTableName[9] = "AI";
		colUserTableName[10] = "LCI";

		userTableModel.setColumnIdentifiers(colUserTableName);
		userTableData = new Object[11];

		try {

			if (userTableModel != null && table_show.getModel() != null) {
				table_show.setModel(userTableModel);
				DefaultTableModel clearModel = (DefaultTableModel) table_show.getModel();
				clearModel.setRowCount(0);
			}

			for (int i = 0; i < user.getUserList().size(); i++) {
				userTableData[0] = user.getUserList().get(i).getId();
				userTableData[1] = user.getUserList().get(i).getPassword();
				userTableData[2] = user.getUserList().get(i).getStatus();
				userTableData[3] = user.getUserList().get(i).getName();
				userTableData[4] = user.getUserList().get(i).getEmail();
				userTableData[5] = user.getUserList().get(i).getPhone();
				userTableData[6] = user.getUserList().get(i).getUserType();
				userTableData[7] = user.getUserList().get(i).getDateOfMembership();
				userTableData[8] = user.getUserList().get(i).getTotalBooksCheckedout();
				userTableData[9] = user.getUserList().get(i).getAddressId();
				userTableData[10] = user.getUserList().get(i).getLibraryCardId();

				userTableModel.addRow(userTableData);

			}

			table_show.setModel(userTableModel);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
