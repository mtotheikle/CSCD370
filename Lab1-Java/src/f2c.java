import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Lab 1
 * 4/2/12
 * 
 * From http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
 * 
 * @author Michael Williams
 * @see http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
 * 
 */
public class f2c implements ActionListener {

	JFrame frame;
	JPanel panel;
	JTextField fahrenheitText;
	JLabel celLabel, fahLabel;
	JButton changeTemp;
	JTextField celciusText;

	/**
	 * Main class for celcius to fahrenheit conversion. Builds a GUI interface 
	 * with 2 text inputs and 
	 * 
	 * @original http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
	 */
	public f2c() {

		frame = new JFrame("Change Celsius to Fahrenheit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(180, 80));
		frame.setResizable(false);
		
		panel = new JPanel(new GridLayout(3, 2));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));// top,left,bottom,right

		addItems();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Builds the panel UI. Adds fahrenheit and celcius text inputs and labels
	 * 
	 * @original http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
	 */
	private void addItems() {
		fahLabel = new JLabel("Fahrenheit", SwingConstants.LEFT);
		fahrenheitText = new JTextField(20);

		changeTemp = new JButton("Convert");
		celLabel = new JLabel("Celcius", SwingConstants.LEFT);
		celciusText = new JTextField(20);

		changeTemp.addActionListener(this);
		panel = new JPanel(new GridLayout(4, 1));
		panel.add(fahLabel);
		panel.add(fahrenheitText);
		panel.add(celLabel);
		panel.add(celciusText);
		panel.add(changeTemp);

		celLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		fahLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
	}

	/**
	 * Converts celcius to fahrenheit when the convert button is pressed. It will
	 * correctly round the values so that 37C converts to 98.6F
	 * 
	 * @original http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
	 * @author Michael Williams
	 */
	public void actionPerformed(ActionEvent event) {
		String selected = celciusText.getText();

		if (selected.equals("")) {
			fahrenheitText.setText("");
			System.out.println("enter the celcius value");
			JOptionPane.showMessageDialog(null, "Enter the celcius value");
		} else if (isNumeric(selected)) {
			float celcius = (float) (Double.parseDouble(selected));
			float fahrenheit = (float) (celcius * 1.8 + 32);
			fahrenheitText.setText(fahrenheit + "");
		} else {
			celciusText.setText("");
			JOptionPane.showMessageDialog(null, "Please enter a valid number in the celcius box");
		}
	}

	/**
	 * Check to make sure the string is numeric.
	 * 
	 * Written to us JAVA exceptions. (efa) 3/31/08
	 * 
	 */
	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (Exception e) {
			// Print out the exception that occurred
			// System.out.println(urlStr+": "+e.getMessage());

			return (false);
		}

		return (true);
	}

	/**
	 * Simply creats and show the GUI
	 */
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		f2c converter = new f2c();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}// end main

}
