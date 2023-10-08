package dsaLinked;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Receipt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	int startPos = 36;

	public Receipt(boolean state, LinkedList list) {
		int num = 0;
		if (state) {
			num = list.size() - list.resSize();
		}
		else {
			num = list.resSize();
		}
		
		String[] temp = new String[num];
		
		if (state) {
			temp = list.availableSeats();
		}
		else {
			temp = list.reservedSeats();
		}
		
		JLabel[] jb = new JLabel[num];
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(30, 11, 124, 14);
		contentPane.add(lblNewLabel);
		
		if (state) lblNewLabel.setText("Available Seats");
		else lblNewLabel.setText("Reserved Seats");
			
		for (int i = 0; i < num; i++) {
			jb[i] = new JLabel(temp[i]);
			jb[i].setBounds(40, startPos, 134, 14);
			contentPane.add(jb[i]);
			
			startPos += 25;
		}
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(166, startPos + 30, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}

}
