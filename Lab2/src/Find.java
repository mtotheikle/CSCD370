/*
   Example swing program wriiten by Edwin F. Armstrong
	4/11/2012
 */

//package layout;

import java.awt.Component;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Find extends JFrame {
	public Find() {

		JLabel titleLabel = new JLabel("MPG Calculator");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setForeground(Color.blue);

		JLabel emptyLabel = new JLabel("");

		JLabel milesLabel = new JLabel("Miles:", JLabel.RIGHT);// experiment
		final JTextField milesText = new JTextField(10);
		JLabel traveledLabel = new JLabel("traveled");

		JLabel gasLabel = new JLabel("Gas:");
		final JTextField gasText = new JTextField(10);
		JLabel gallonsLabel = new JLabel("gallons used");

		JLabel mpgLabel = new JLabel("Miles per gallon:");
		final JTextField mpgText = new JTextField(10);

		JButton clearButton = new JButton("Clear");
		JButton calcButton = new JButton("Calculate");

		// ****************** get images **************
		/*String sFileName = "gas_pump.png";

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(sFileName));
		} catch (IOException ex) {
			// handle exception...

			System.out.println(ex.toString());
			System.out.println(sFileName);

			JOptionPane
					.showMessageDialog(null, ex.toString() + " " + sFileName);

			System.exit(0); // exit program

		}

		JLabel labelPic1 = new JLabel(new ImageIcon(image));
		*/
		// ******************************************

		setResizable(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				//.addComponent(labelPic1)
				.addComponent(clearButton)
				.addComponent(calcButton)
			)
			.addGroup(layout.createParallelGroup(LEADING)
				.addComponent(titleLabel)
				.addComponent(milesLabel)
				.addComponent(gasLabel)
				.addComponent(mpgLabel)
			)
			.addGroup(layout.createParallelGroup(LEADING)
				.addComponent(milesText)
				.addComponent(gasText)
				.addComponent(mpgText)
			)

		);// layout close

		// layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(BASELINE)
				//.addComponent(labelPic1)
				.addGroup(layout.createSequentialGroup()
					.addComponent(titleLabel)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(milesLabel)
						.addComponent(milesText)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(gasLabel)
						.addComponent(gasText))
					)
			)
			.addGroup(layout.createParallelGroup(LEADING)
				.addComponent(clearButton)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(mpgLabel)
					.addComponent(mpgText)
				)
			)
			.addGroup(layout.createParallelGroup(LEADING)
				.addComponent(calcButton)
			)

		);// layout close

		// ************************************************
		// clearButton:: Clear the textBoxes
		// last updated 4/11/12 (efa)
		// ************************************************
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				milesText.setText("");
				gasText.setText("");
				mpgText.setText("");
			}
		});

		// ************************************************
		// calcButton:: Calculate mpg
		// last updated 4/11/12 (efa)
		// ************************************************
		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (isNumeric(milesText.getText())
						&& isNumeric(gasText.getText())) {
					double gallons;
					double miles;
					double result;

					gallons = Double.parseDouble(gasText.getText());
					miles = Double.parseDouble(milesText.getText());
					result = miles / gallons;
					result = Round(result, 2);

					mpgText.setText(String.format("%f", result));
				} else {
					JOptionPane.showMessageDialog(null,
							"Enter miles traveled and gas used");
				}
			}

		});

		setTitle("Find");
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	// ****************************************************
	// isNumeric: Varify textBox contains valid number.
	// last updated 4/11/12 (efa)
	// ****************************************************
	private static boolean isNumeric(String text) {
		try {
			Double.parseDouble(text);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// *************************************************
	// * Round to arbitray number of places (in JAVA).
	// * Last updated (efa) 9/12/09
	// *************************************************
	// ***********************************
	// num = 100.125;
	// num = Round(num,2);
	// The value of num will be 100.13
	// ***********************************
	public double Round(double val, int plc) {
		double pwr = Math.pow(10, plc);
		val = val * pwr; // shift to the left
		double tmp = (int) val;

		if (((int) (val + .5)) == (int) val)
			return (tmp / pwr); // don't round up
		else
			return ((tmp + 1.0) / pwr); // round up
	}

	// ****************************************************

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					// "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					// UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				new Find().setVisible(true);
			}
		});
	}
}