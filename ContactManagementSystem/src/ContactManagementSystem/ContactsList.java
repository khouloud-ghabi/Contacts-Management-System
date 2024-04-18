package ContactManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ContactsList {
    private GridLayout gridLayout;
    private JPanel table;
    private Database database;

    public ContactsList(Database database) {
    	this.database = database;
        JFrame frame = new JFrame("Cantacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);

        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(77, 50, 50, 50));
        top.setBackground(null);

        JLabel title = new JLabel("Welcome to Contact Management System ");
        title.setFont(new Font("Calibri", Font.BOLD, 34));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        top.add(title, BorderLayout.CENTER);

        JButton newContact = new JButton("New Contact");
        newContact.setFont(new Font("Tahoma", Font.BOLD, 18));
        newContact.setBackground(new Color(88, 179, 88));
        newContact.setForeground(Color.white);
        newContact.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	try {
					new OpenContact(new Contact(),"new",database,ContactsList.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.toString());
				}
            }
        });

        top.add(newContact, BorderLayout.EAST);

        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(8, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(Color.white);
        try {
        	refresh(database.getContacts());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.toString());
		}

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void refresh(ArrayList<Contact> contacts) {
        table.removeAll();
        table.repaint();
        table.revalidate();

        int rows = contacts.size();
        if (rows < 8) rows = 8;
        gridLayout.setRows(rows);

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            panel.setBackground(null);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            if (i % 2 == 0) panel.setBackground(Color.decode("#e5e5e5"));
            panel.setPreferredSize(new Dimension(100, 55));
            panel.add(GUI.label(c.getFirstname() + " " + c.getLastname()));
            panel.add(GUI.label(c.getPhoneNumber()));
            panel.add(GUI.label(c.getEmail()));
            JButton view = GUI.button("view", new Color(88, 179, 99));
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                	try {
						new OpenContact(c,"view",database,ContactsList.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					}
                }
            });

            panel.add(view);
            JButton edit = GUI.button("edit", new Color(63, 134, 196));
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                	try {
						new OpenContact(c,"edit",database,ContactsList.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					}
                }
            });
            panel.add(edit);
            JButton delete = GUI.button("delete", new Color(208, 11, 3));
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                	try {
						database.deleteContact(c);
						refresh(database.getContacts());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					}
                }
            });
            panel.add(delete);
            table.add(panel);
        }
    }
}
