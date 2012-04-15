
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.sun.tools.hat.internal.model.JavaBoolean;

import static javax.swing.GroupLayout.Alignment.*;

public class OhmsLawCalculator extends JFrame 
{
	private JFrame frame = null;
	private JPanel panel;
	
	public OhmsLawCalculator()
	{
        this.setResizable(false);
        this.setSize(250, 200);
        
		JLabel voltsLabel = new JLabel("Volts (E):");
        final JTextField voltsTextField = new JTextField();
        
		JLabel resistanceLabel = new JLabel("Resistance (R):");
		final JTextField resistanceTextField = new JTextField(10);
        
		JLabel currentLabel = new JLabel("Current (C):");
		final JTextField currentTextField = new JTextField(10);
       
        JLabel titleLabel = new JLabel("Ohms Law Calculator");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setForeground(Color.blue);

		JLabel wattsLabel = new JLabel("Watts (P) = I * E");
		final JTextField resultsTextField = new JTextField();

        JButton calcButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        
        String sFileName = "circuit1.png";
        BufferedImage image = null;
		try {
			image = ImageIO.read(new File(sFileName));
		} catch (IOException ex) {
			// handle exception...

			System.out.println(ex.toString());
			System.out.println(sFileName);

			JOptionPane.showMessageDialog(null, ex.toString() + " " + sFileName);

			System.exit(0); // exit program

		}

		JLabel labelPic1 = new JLabel(new ImageIcon(image));
		
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addGap(100)
        			.addComponent(titleLabel)
	    		)
    			.addGroup(layout.createSequentialGroup()
		        	.addGroup(layout.createParallelGroup(LEADING)
		    			.addComponent(labelPic1)
					)
		    		.addGroup(layout.createParallelGroup(LEADING)
		    			.addComponent(voltsLabel)
		    			.addComponent(resistanceLabel)
		    			.addComponent(currentLabel)
		    			.addComponent(calcButton)
					)
					.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(voltsTextField)
						.addComponent(resistanceTextField)
						.addComponent(currentTextField)
		    			.addComponent(clearButton)
					)
				)
    			.addGroup(layout.createSequentialGroup()
    				.addComponent(wattsLabel)
    				.addComponent(resultsTextField)
				)
			)
		);
        
        layout.setVerticalGroup(layout.createSequentialGroup()	
    		.addGroup(layout.createParallelGroup(BASELINE)
				.addComponent(titleLabel)
			)
			.addGap(25)
        	.addGroup(layout.createParallelGroup(BASELINE)
    			.addComponent(labelPic1)
    			.addGroup(layout.createSequentialGroup()
	    			//.addComponent(titleLabel)
					.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(voltsLabel)
						.addComponent(voltsTextField)
					)
					.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(resistanceLabel)
						.addComponent(resistanceTextField)
					)
					.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(currentLabel)
						.addComponent(currentTextField)
					)
					.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(calcButton)
						.addComponent(clearButton)
					)
				)
			)
			.addGroup(layout.createParallelGroup(BASELINE)
				.addComponent(wattsLabel)
				.addComponent(resultsTextField)
			)
		);
  
        clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentTextField.setText("");
				resistanceTextField.setText("");
				voltsTextField.setText("");
			}
		});
        
        calcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!currentTextField.getText().equals("") 
					&& !resistanceTextField.getText().equals("") 
					&& voltsTextField.getText().equals("")) {
					
					if (isNumeric(currentTextField.getText()) && isNumeric(resistanceTextField.getText())) {
						// Calculate volts
						double volts = Double.parseDouble(currentTextField.getText()) * Double.parseDouble(resistanceTextField.getText());
						
						double p = Double.parseDouble(currentTextField.getText()) * volts;
						resultsTextField.setText(Double.toString(round(p, 2)) + " = " + currentTextField.getText() + " * " + Double.toString(round(volts, 2)));
						
						currentTextField.setText("");
						resistanceTextField.setText("");
						voltsTextField.setText("");
						
					} else {
						JOptionPane.showMessageDialog(null, "Please enter valid mueric values for resistance and current");
					}
				} else if (currentTextField.getText().equals("") 
					&& !resistanceTextField.getText().equals("") 
					&& !voltsTextField.getText().equals("")) {
					
					if (isNumeric(voltsTextField.getText()) && isNumeric(resistanceTextField.getText())) {
						// Calculate current
						double current = Double.parseDouble(voltsTextField.getText()) / Double.parseDouble(resistanceTextField.getText());
						
						double p = Double.parseDouble(voltsTextField.getText()) * current;
						resultsTextField.setText(Double.toString(round(p, 2)) + " = " + Double.toString(round(current, 2)) + " * " + voltsTextField.getText());
						
						currentTextField.setText("");
						resistanceTextField.setText("");
						voltsTextField.setText("");
						
					} else {
						JOptionPane.showMessageDialog(null, "Please enter valid mueric values for resistance and volts");
					}
				} else if (!currentTextField.getText().equals("") 
					&& resistanceTextField.getText().equals("") 
					&& !voltsTextField.getText().equals("")) {
					
					if (isNumeric(currentTextField.getText()) && isNumeric(currentTextField.getText())) {
						// Calculate resistance
						double resistance = Double.parseDouble(voltsTextField.getText()) / Double.parseDouble(currentTextField.getText());
						resistanceTextField.setText(Double.toString(round(resistance, 2)));
						
						double p = Double.parseDouble(voltsTextField.getText()) * Double.parseDouble(currentTextField.getText());
						resultsTextField.setText(Double.toString(round(p, 2)) + " = " + currentTextField.getText() + " * " + voltsTextField.getText());						

						currentTextField.setText("");
						resistanceTextField.setText("");
						voltsTextField.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Please enter valid mueric values for current and volts");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter 2 vaules to calcualte the 3rd");
				}
			}
		});
        
        setTitle("Ohms Law Calculator");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public boolean isNumeric(String str)
	{
		try {
			Double.parseDouble(str);
		} catch(Exception e) {
			return(false);
		}

		return(true);
	}
	
	public double round(double val, int plc)
	{
		double pwr = Math.pow(10,plc);  
		val = val * pwr;   //shift to the left
		double tmp = (int) val;     

		if (((int)(val + .5)) == (int) val)
			return (tmp/pwr); //don't round up
		else
			return((tmp + 1.0)/pwr); //round up
    }

	/**
	 * Main method
	 * 
	 * Original author from linked site on class documentation
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new OhmsLawCalculator().setVisible(true);
            }
        });
    }
}
