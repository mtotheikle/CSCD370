import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Lab 1
 * 4/2/12
 * 
 * Converts from celcius to fahrenheit and fahrenheit to celcius
 * 
 * @author Michael Williams
 * @see http://penguin.ewu.edu/cscd370/Spr2012/Labs1/cisc370_l1.html
 * 
 */
public class TempConverter implements ActionListener {

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
	public TempConverter() {

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
		frame.getRootPane().setDefaultButton(changeTemp); // Make key press on enter trigger the convert method
		
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
		if (!celciusText.getText().equals("") && fahrenheitText.getText().equals("")) {
			if (!isNumeric(celciusText.getText())) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer in celcius field");
				celciusText.setText("");
			} else {
				// Compute fahrenheit
				float celcius = (float) (Double.parseDouble(celciusText.getText()));
				float fahrenheit = (float) (celcius * 1.8 + 32);
				fahrenheitText.setText(Math.round(fahrenheit*100)/100.0d + ""); // Do a little rounding
				celciusText.setText("");
			}
		} else if (celciusText.getText().equals("") && !fahrenheitText.getText().equals("")) {
			
			if (!isNumeric(fahrenheitText.getText())) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer in fahrenheit field");
				fahrenheitText.setText("");
			} else { // Compute celcius
				float fahrenheit = (float) (Double.parseDouble(fahrenheitText.getText()));
			    float celcius = (float) ((fahrenheit - 32) / 1.8); // Do a little rounding
			    celciusText.setText(Math.round(celcius*100)/100.0d + "");
			    fahrenheitText.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please enter either celcius value or fahrenheit value, but not both");
		}
	}
	
	/**
	 * Check to make sure the string is numeric.
	 * 
	 * Written to use JAVA exceptions. (efa) 3/31/08
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
	 * Simply createss and show the GUI
	 * 
	 * Original author from linked site on class documentation
	 */
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		TempConverter converter = new TempConverter();
	}
	
	/**
	 * Main method
	 * 
	 * Original author from linked site on class documentation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}// end main

}
