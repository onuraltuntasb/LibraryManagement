package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import helper.DBConnection;
import helper.PasswordOps;
import model.LibraryCard;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private JPanel w_pane;
	private JTextField txt_email;
	private JTextField txt_name;
	private JTextField txt_phone;
	private JTextField txt_street;
	private JTextField txt_city;
	private JTextField txt_country;
	private JTextField txt_state;

	private JComboBox cb_user_type;
	private JComboBox cb_status;

	private JTable table_show;

	private JTextField txt_zipcode;
	private JTextField txt_password;

	private DefaultTableModel userTableModel = null;
	private Object[] userTableData = null;

	Date date = new Date();

	static User user = new User();

	private DBConnection conn = new DBConnection();
	private Statement st = null;
	private ResultSet rs = null;

	private Connection con = conn.connDb();
	private PreparedStatement preparedStatement = null;

	private int delUserId = -1;
	private int delUserAddressId = -1;
	private int delUserLibraryCardId = -1;

	// TODO form validations and move functions in relative models remove globals
	// and constants
	// TODO update event is not firing

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI();
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
	public AdminGUI() {

		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_name = new JLabel("name :");
		lbl_name.setBounds(23, 50, 70, 15);
		w_pane.add(lbl_name);

		JLabel lbl_email = new JLabel("email :");
		lbl_email.setBounds(23, 25, 70, 15);
		w_pane.add(lbl_email);

		JLabel lbl_phone = new JLabel("phone :");
		lbl_phone.setBounds(350, 50, 70, 15);
		w_pane.add(lbl_phone);

		cb_user_type = new JComboBox();
		cb_user_type.setModel(new DefaultComboBoxModel(user.getUserTypes()));
		cb_user_type.setBounds(120, 100, 220, 24);
		w_pane.add(cb_user_type);

		JLabel lbl_user_type = new JLabel("type :");
		lbl_user_type.setBounds(23, 100, 70, 15);
		w_pane.add(lbl_user_type);

		txt_email = new JTextField();
		txt_email.setBounds(120, 25, 220, 19);
		w_pane.add(txt_email);
		txt_email.setColumns(10);

		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(120, 50, 220, 19);
		w_pane.add(txt_name);

		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(425, 50, 220, 19);
		w_pane.add(txt_phone);

		JLabel lbl_street = new JLabel("street :");
		lbl_street.setBounds(23, 175, 70, 15);
		w_pane.add(lbl_street);

		JLabel lbl_city = new JLabel("city :");
		lbl_city.setBounds(23, 200, 70, 15);
		w_pane.add(lbl_city);

		JLabel lbl_state = new JLabel("state :");
		lbl_state.setBounds(23, 225, 70, 15);
		w_pane.add(lbl_state);

		JLabel lbl_country = new JLabel("country :");
		lbl_country.setBounds(350, 25, 70, 15);
		w_pane.add(lbl_country);

		txt_street = new JTextField();
		txt_street.setColumns(10);
		txt_street.setBounds(120, 175, 220, 19);
		w_pane.add(txt_street);

		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(120, 200, 220, 19);
		w_pane.add(txt_city);

		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(425, 25, 220, 19);
		w_pane.add(txt_country);

		txt_state = new JTextField();
		txt_state.setColumns(10);
		txt_state.setBounds(120, 225, 220, 19);
		w_pane.add(txt_state);

		JButton btn_add = new JButton("add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_add_ActionPerformed();
			}

		});
		btn_add.setBounds(405, 220, 80, 25);
		w_pane.add(btn_add);

		JButton btn_edit = new JButton("edit");
		btn_edit.setBounds(520, 220, 80, 25);
		w_pane.add(btn_edit);

		JButton btn_delete = new JButton("delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_delete_ActionPerformed();
			}
		});
		btn_delete.setBounds(639, 220, 80, 25);
		w_pane.add(btn_delete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 283, 765, 268);
		w_pane.add(scrollPane);

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

		JLabel lbl_zipcode = new JLabel("zipcode :");
		lbl_zipcode.setBounds(23, 250, 70, 15);
		w_pane.add(lbl_zipcode);

		txt_zipcode = new JTextField();
		txt_zipcode.setColumns(10);
		txt_zipcode.setBounds(120, 250, 220, 19);
		w_pane.add(txt_zipcode);

		JLabel lbl_password = new JLabel("password :");
		lbl_password.setBounds(23, 75, 70, 15);
		w_pane.add(lbl_password);

		txt_password = new JTextField();
		txt_password.setColumns(10);
		txt_password.setBounds(120, 73, 220, 19);
		w_pane.add(txt_password);

		JLabel lbl_status = new JLabel("status :");
		lbl_status.setBounds(23, 125, 70, 15);
		w_pane.add(lbl_status);

		cb_status = new JComboBox();
		cb_status.setModel(
				new DefaultComboBoxModel(new String[] { "ACTIVE", "CLOSED", "CANCELED", "BLACKLISTED", "NONE" }));
		cb_status.setBounds(120, 125, 220, 24);
		w_pane.add(cb_status);

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
