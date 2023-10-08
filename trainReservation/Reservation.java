package dsaLinked;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInputSeatNumber;
	
	public Reservation(int numSeats) {
		LinkedList lList = new LinkedList();
		lList.createSeat(numSeats);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of seats: " + numSeats);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 172, 14);
		contentPane.add(lblNewLabel);
		
		txtInputSeatNumber = new JTextField();
		txtInputSeatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtInputSeatNumber.setText("Input Seat Number");
		txtInputSeatNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if (!Character.isDigit(c)) e.consume();
			}
		});
		txtInputSeatNumber.setBounds(24, 200, 291, 41);
		contentPane.add(txtInputSeatNumber);
		txtInputSeatNumber.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("Result");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 36, 295, 41);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Reserve a Seat");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String seatNum = txtInputSeatNumber.getText();
				
				if (Integer.parseInt(seatNum) > numSeats) {
					lblNewLabel_1.setText("Seat Number: " + seatNum + " Does Not Exist!");
					return;
				}
				
				boolean checker = lList.reserveSeat(Integer.parseInt(seatNum));
				
				if (checker) lblNewLabel_1.setText("Seat Number: " + seatNum + " Succesfully reserved!");
				else lblNewLabel_1.setText("Seat Number: " + seatNum + " Already has a reservation!");
			}
		});
		btnNewButton_2.setBounds(24, 258, 132, 58);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1_1 = new JButton("Check Availability");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seatNum = txtInputSeatNumber.getText();
				boolean checker = lList.checkSeatAvailability(Integer.parseInt(seatNum));
				
				if (checker) lblNewLabel_1.setText("Seat Number: " + seatNum + " is already reserved!");
				else lblNewLabel_1.setText("Seat Number: " + seatNum + " not yet reserved!");
				
			}
		});
		btnNewButton_2_1_1.setBounds(66, 327, 200, 58);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Cancel Reservation");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String seatNum = txtInputSeatNumber.getText();
				boolean checker = lList.cancelReservation(Integer.parseInt(seatNum));
				
				if (checker) lblNewLabel_1.setText("Reservation for Seat Number: " + seatNum + " cancelled!");
				else lblNewLabel_1.setText("Error! Seat Number: " + seatNum + " is available!");
				
			}
		});
		btnNewButton_2_1.setBounds(183, 258, 132, 58);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Available Seats");
		btnNewButton_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Receipt rc = new Receipt(true, lList);
				rc.setVisible(true);
			}
		});
		btnNewButton_2_2.setBounds(24, 96, 132, 58);
		contentPane.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_1_2 = new JButton("Reserved Seats");
		btnNewButton_2_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Receipt rc = new Receipt(false, lList);
				rc.setVisible(true);
			}
		});
		btnNewButton_2_1_2.setBounds(183, 96, 132, 58);
		contentPane.add(btnNewButton_2_1_2);
		
		
		
		
	}
}
