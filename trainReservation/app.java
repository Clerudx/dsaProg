package dsaLinked;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class app extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numSeats;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app frame = new app();
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
	public app() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Number of seats to generate:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(108, 48, 246, 14);
		contentPane.add(lblNewLabel);
		
		numSeats = new JTextField();
		numSeats.setHorizontalAlignment(SwingConstants.CENTER);
		numSeats.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if (!Character.isDigit(c)) e.consume();
			}
		});
		numSeats.setText("0");
		numSeats.setBounds(163, 92, 86, 20);
		contentPane.add(numSeats);
		numSeats.setColumns(10);
		
		
		
		
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_2.setVisible(false);
				String seats = numSeats.getText();
				
				if (Integer.parseInt(seats) == 0) {
					lblNewLabel_2.setVisible(true);
				}
				else {
					Reservation open = new Reservation(Integer.parseInt(seats));
					open.setVisible(true);
				}
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setBounds(160, 123, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Cannot be 0");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFocusTraversalPolicyProvider(true);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setBounds(173, 68, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
