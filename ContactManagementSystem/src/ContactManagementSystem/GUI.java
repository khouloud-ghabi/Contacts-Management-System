package ContactManagementSystem;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI {
   
	public static JLabel label(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Tahoma",Font.PLAIN,17));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setPreferredSize(new Dimension(200,30));
		return label;
	}
	
	public static JButton button(String text,Color background) {
		JButton btn = new JButton(text);
		btn.setSize(50,50);
		btn.setBackground(background);
		btn.setForeground(Color.white);
		btn.setFont(new  Font("Tahoma",Font.PLAIN,17));
		btn.setBorder(BorderFactory.createEmptyBorder(7,25, 7, 25));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}


	public static JTextField textField(String text) {
		JTextField tf  = new JTextField();
		tf.setText(text);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setFont(new  Font("Tahoma",Font.PLAIN,17));
		return tf;
		
		
	}
}
