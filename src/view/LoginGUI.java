package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import helper.DBConnection;
import helper.ErrWarrConstants;
import helper.StringValidators;
import model.User;

public class LoginGUI extends JFrame {
	private JPanel w_pane;
	private JTextField login_txt_name;
	private JPasswordField login_txt_pass;
	private JTextField login_txt_email;

	private String validationWarning = "";
	private boolean validationOK = false;
	private boolean accountNotFound = false;

	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel login_lbl_header = new JLabel("Password Manager Please Login");
		login_lbl_header.setFont(new Font("Ubuntu", Font.BOLD, 18));
		login_lbl_header.setBounds(117, 80, 279, 26);
		w_pane.add(login_lbl_header);

		JPanel login_panel_form = new JPanel();
		login_panel_form.setBounds(81, 118, 350, 165);
		w_pane.add(login_panel_form);
		login_panel_form.setLayout(null);

		JLabel login_lbl_name = new JLabel("username :");
		login_lbl_name.setFont(new Font("Ubuntu", Font.BOLD, 16));
		login_lbl_name.setBounds(26, 51, 125, 21);
		login_panel_form.add(login_lbl_name);

		JLabel login_lbl_pass = new JLabel("password :");
		login_lbl_pass.setFont(new Font("Ubuntu", Font.BOLD, 16));
		login_lbl_pass.setBounds(26, 79, 125, 21);
		login_panel_form.add(login_lbl_pass);

		login_txt_name = new JTextField();
		login_txt_name.setBounds(137, 51, 190, 19);
		login_panel_form.add(login_txt_name);
		login_txt_name.setColumns(10);

		login_txt_pass = new JPasswordField();
		login_txt_pass.setBounds(137, 79, 190, 19);
		login_panel_form.add(login_txt_pass);

		JButton login_btn_singup = new JButton("sign up");
		login_btn_singup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Signup page
//				SignupGUI sG = new SignupGUI();
//				sG.setVisible(true);
//				dispose();
			}
		});
		login_btn_singup.setBounds(26, 118, 117, 25);
		login_panel_form.add(login_btn_singup);

		JButton login_btn_login = new JButton("login");

		login_btn_login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				login_btn_login_addActionListener();

			}
		});

		login_btn_login.setBounds(210, 118, 117, 25);
		login_panel_form.add(login_btn_login);

		login_txt_email = new JTextField();
		login_txt_email.setColumns(10);
		login_txt_email.setBounds(137, 25, 190, 19);
		login_panel_form.add(login_txt_email);

		JLabel login_lbl_email = new JLabel("email :");
		login_lbl_email.setFont(new Font("Ubuntu", Font.BOLD, 16));
		login_lbl_email.setBounds(26, 23, 125, 21);
		login_panel_form.add(login_lbl_email);
	}

	private void login_btn_login_addActionListener() {

		validationWarning = "";
		validationOK = false;

		if (StringValidators.patternMatches(login_txt_email.getText().trim(), StringValidators.emailReg)) {

			validationOK = true;
		} else {
			validationOK = false;
			validationWarning += "\n" + ErrWarrConstants.emailMatchingWarr;
			System.out.println("email war :" + login_txt_email.getText());
		}

		if (((login_txt_name.getText().length() > 0)
				&& StringValidators.patternMatches(login_txt_name.getText(), StringValidators.usernameReg))) {

			validationOK = true;

		} else {
			validationOK = false;
			validationWarning += "\n" + ErrWarrConstants.userNameMatchingWarr;
			System.out.println("name war");
		}
		if (((String.valueOf(login_txt_pass.getPassword()).length() > 0) && StringValidators
				.patternMatches(String.valueOf(login_txt_pass.getPassword()), StringValidators.passwordReg))) {

			validationOK = true;

		} else {
			validationOK = false;
			validationWarning += "\n" + ErrWarrConstants.passwordWarr;
			System.out.println("pass war");
		}

		if (validationOK) {
			
			

			try {

				String query = "SELECT * FROM user WHERE email=? AND name=? AND password=?";
				Connection con = conn.connDb();

				PreparedStatement preparedStatement = con.prepareStatement(query);

				preparedStatement.setString(1, login_txt_email.getText());
				preparedStatement.setString(2, login_txt_name.getText());
				preparedStatement.setString(3, String.valueOf(login_txt_pass.getPassword()));
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs!=null) {
					System.out.println(preparedStatement);	
				}
				

				while (rs.next()) {
					
				
					
					if (login_txt_name.getText().equals(rs.getString("name"))
							&& login_txt_email.getText().equals(rs.getString("email"))
							&& String.valueOf(login_txt_pass.getPassword()).equals(rs.getString("password"))) {
						User userObj = new User();
						userObj.setId(rs.getInt("id"));
						userObj.setName(rs.getString("name"));
						userObj.setEmail(rs.getString("email"));
						userObj.setPhone(rs.getString("phone"));
						userObj.setUserType(rs.getString("user_type"));
						userObj.setStatus(rs.getString("status"));
						userObj.setAddressId(rs.getInt("address_id"));
						userObj.setDateOfMembership(rs.getString("date_of_membership"));
						userObj.setTotalBooksCheckedout(rs.getInt("total_books_checkedout"));
						userObj.setLibraryCardId(rs.getInt("library_card_id"));
						System.out.println(userObj.getName());
						// TODO send member or librarian or admin gui
						// UserGUI uGUI = new UserGUI(userObj);
						// uGUI.setVisible(true);
						accountNotFound = false;
						dispose();
						break;

					} else {
						accountNotFound = true;
					}

				}
				if (accountNotFound) {
					JOptionPane.showMessageDialog(w_pane, "Account not found!", "DB Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(w_pane, "DB error", "DB Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(w_pane, validationWarning, "Form Validation", JOptionPane.ERROR_MESSAGE);
		}
	}

}
