package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class AdminGUI extends JFrame {

	private JPanel w_pane;
	private JTextField txt_email;
	private JTextField txt_name;
	private JTextField txt_phone;
	private JTextField txt_street;
	private JTextField txt_city;
	private JTextField txt_country;
	private JTextField textField_1;
	private JTable table_show;

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
		lbl_phone.setBounds(23, 75, 70, 15);
		w_pane.add(lbl_phone);
		
		JComboBox cb_user_type = new JComboBox();
		cb_user_type.setModel(new DefaultComboBoxModel(new String[] {"MEMBER", "LIBRARIAN", "ADMIN"}));
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
		txt_phone.setBounds(120, 75, 220, 19);
		w_pane.add(txt_phone);
		
		JLabel lbl_street = new JLabel("street :");
		lbl_street.setBounds(23, 150, 70, 15);
		w_pane.add(lbl_street);
		
		JLabel lbl_city = new JLabel("city :");
		lbl_city.setBounds(23, 175, 70, 15);
		w_pane.add(lbl_city);
		
		JLabel lbl_state = new JLabel("state :");
		lbl_state.setBounds(23, 200, 70, 15);
		w_pane.add(lbl_state);
		
		JLabel lbl_country = new JLabel("country :");
		lbl_country.setBounds(23, 225, 70, 15);
		w_pane.add(lbl_country);
		
		txt_street = new JTextField();
		txt_street.setColumns(10);
		txt_street.setBounds(120, 150, 220, 19);
		w_pane.add(txt_street);
		
		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(120, 175, 220, 19);
		w_pane.add(txt_city);
		
		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(120, 225, 220, 19);
		w_pane.add(txt_country);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(120, 200, 220, 19);
		w_pane.add(textField_1);
		
		table_show = new JTable();
		table_show.setBounds(375, 25, 397, 220);
		w_pane.add(table_show);
		
		JButton btn_add = new JButton("add");
		btn_add.setBounds(23, 250, 117, 25);
		w_pane.add(btn_add);
		
		JButton btn_edit = new JButton("edit");
		btn_edit.setBounds(375, 250, 117, 25);
		w_pane.add(btn_edit);
		
		JButton btn_delete = new JButton("delete");
		btn_delete.setBounds(655, 250, 117, 25);
		w_pane.add(btn_delete);
	}
}
