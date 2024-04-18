package ContactManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class OpenContact {
	
	public OpenContact(Contact c , String oper,Database database, ContactsList contact) throws SQLException {
		JFrame frame = new JFrame("Contacts Management System");
		frame.setLayout(new BorderLayout());
		frame.setSize(600,400);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.white);
		
		
	JPanel table = new JPanel(new GridLayout(6,2,15,15));
	table.setBackground(Color.white);
	table.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	
	table.add(GUI.label("ID:"));
	JLabel id = GUI.label(String.valueOf(c.getID()));
	table.add(id);
	table.add(GUI.label("First Name:"));
	JTextField firstname = GUI.textField(c.getFirstname());
	table.add(firstname);
	table.add(GUI.label("Last Name:"));
	JTextField lastname = GUI.textField(c.getLastname());
	table.add(lastname);
	table.add(GUI.label("Phone Number:"));
	JTextField phonenumber = GUI.textField(c.getPhoneNumber());
	table.add(phonenumber );
	table.add(GUI.label("Email:"));
	JTextField email = GUI.textField(c.getEmail());
	table.add(email);
	
	JButton cancel = GUI.button("Cancel", new Color(200,11,3));
	cancel.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.dispose();
		}
		
	});;
	
	table.add(cancel);
	
	JButton save = GUI.button("Save", new Color(88,179,88));
	table.add(save);
	
	frame.add(table,BorderLayout.CENTER);
	
	if(oper.equals("new")) {
		id.setText(String.valueOf(database.getNextID()));
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c.setID(Integer.parseInt(id.getText().toString()));
				c.setFirstname(firstname.getText().toString());
				c.setLastname(lastname.getText().toString());
				c.setPhoneNumber(phonenumber.getText().toString());
				c.setEmail(email.getText().toString());
				try {
				database.insertContact(c);
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
					}
			}
		});
	}else if (oper.equals("edit")) {
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c.setID(Integer.parseInt(id.getText().toString()));
				c.setFirstname(firstname.getText().toString());
				c.setLastname(lastname.getText().toString());
				c.setPhoneNumber(phonenumber.getText().toString());
				c.setEmail(email.getText().toString());
				try {
				database.updateContact(c);
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
					}
			}
		});
		
	}else if (oper.equals("view")) {
		save.setVisible(false);
		cancel.setVisible(false);
		
	}
	
	frame.setVisible(true);
	}

}
