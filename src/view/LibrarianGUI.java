package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LibrarianGUI extends JFrame {

	static User user = new User();

	private JPanel w_pane;
	private JLabel lbl_welcome;

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

		JButton btn_logout = new JButton("Log out");
		btn_logout.setBounds(1237, 5, 117, 25);
		w_pane.add(btn_logout);

	}
}
