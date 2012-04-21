import java.awt.Component;
import java.awt.event.*;
import java.awt.*;

import javax.script.*;
import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
   
/**
 * CalorieCalculator is a simple class that builds and manages a GUI interface responding
 * to events the user takes within this class it self. Information on how many calories an
 * activity burns is from http://www.nutristrategy.com/activitylist4.htm
 * 
 * @author Michael Williams
 */
public class CalorieCalculator extends JFrame
{
	private JLabel titleLabel, activityLabel, weightLabel, timeLabel, burnedLabel, picLabel;
	
	private JComboBox activityCombo, timeCombo;
	
	private JTextField weightText, timeText, burnedText;
	
	// Store formulas for later use, the "wt" text will get replaced
	// with the user weight
	private String[] formulas = { 
		"(wt * 354) / 130", // Weight lifting
		"(wt * 177) / 130", // Bowling
		"(wt * 266) / 130", // Golfing
		"(wt * 207) / 130", // Archery
		"(wt * 236) / 130", // Curling
	};
	
	public CalorieCalculator()
	{
		// Initiate all our labels, combo boxes and text fields
		titleLabel = new JLabel("Calories Burned During Exercise");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setForeground(Color.blue);
        
        // Show our first picture
        setPicLabel("weightlifting-vigorous.gif");
        
        activityLabel = new JLabel("Choose an activity:");
        String[] activityStrings = { 
    		"Weight lifting - vigorous", // This is also first picture! 
    		"Bowling", 
    		"Golfing",
    		"Archery",
    		"Curling",
		};
        activityCombo = new JComboBox(activityStrings);
        weightLabel = new JLabel("Current Weight");
        weightText = new JTextField(10);
        
        timeLabel = new JLabel("How long did you workout?");
        String[] timeStrings = { "Minutes", "Hours" };
        timeCombo = new JComboBox(timeStrings);
        timeText = new JTextField(5);
        
        burnedLabel = new JLabel("Calories Burned:");
        burnedText = new JTextField();
        
        JButton clearButton = new JButton("Clear");
        JButton calcButton = new JButton("Calculate");
        
        // Place our components in the layout
        setResizable(false);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            	.addComponent(picLabel)
                .addComponent(clearButton)
                .addComponent(calcButton) 
             )
            .addGroup(layout.createParallelGroup(LEADING)
        		.addGroup(layout.createSequentialGroup()
	    			.addGap(40)
	    			.addComponent(titleLabel)
    			)
           		.addGroup(layout.createSequentialGroup()
	           		.addGroup(layout.createParallelGroup(LEADING)
		                .addComponent(activityLabel)
		                .addComponent(weightLabel) 
		                .addComponent(timeLabel)
		                .addComponent(burnedLabel)
		             )
		            .addGroup(layout.createParallelGroup(LEADING)
		                .addComponent(activityCombo)
		                .addComponent(weightText)
		                .addGroup(layout.createSequentialGroup()
	                		.addComponent(timeText)
                			.addComponent(timeCombo)
            			)
	            		.addComponent(burnedText)
		             )
	             )
	             .addGroup(layout.createSequentialGroup()
        		 )
            )
		);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
               .addComponent(picLabel)
               .addGroup(layout.createSequentialGroup()
        		   .addGroup(layout.createParallelGroup(BASELINE)
    					.addComponent(titleLabel)
    				)
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                		.addComponent(activityLabel)
            		 	.addComponent(activityCombo)
                      
                   )
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		   	.addComponent(weightLabel)
                      	.addComponent(weightText)
                   )  
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		   .addComponent(timeLabel)
            		   .addComponent(timeText)
            		   .addComponent(timeCombo)
        		   )
                )
             )           
             .addGroup(layout.createParallelGroup(LEADING)
        		.addComponent(clearButton) 
                                
            )
            .addGroup(layout.createParallelGroup(LEADING)
        		.addComponent(calcButton)
                .addComponent(burnedLabel)
        		.addComponent(burnedText)
            )       
		);
        
        // Setup action listeners
        calcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String weight = weightText.getText();
				
				if (weight.equals("") || !isNumeric(weight)) {
					JOptionPane.showMessageDialog(null, "Please enter a weight value that is an integer");
					
					return;
				}
				
				if (timeText.getText().equals("") || !isNumeric(timeText.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter the amount of time you workedout as an integer");
					
					return;
				}
				
				// Regulate and parse our formula using the javascript engine.
				// This avoids a lot of if else statements.
				// Idea googled and solution found at http://stackoverflow.com/questions/3422673/java-evaluate-string-to-math-expression
				String formula = (String) formulas[(int) activityCombo.getSelectedIndex()];
				formula = formula.replaceAll("wt", weight);
				ScriptEngineManager mgr = new ScriptEngineManager();
		        ScriptEngine engine = mgr.getEngineByName("JavaScript");
		        try {
		        	double value = Double.parseDouble(engine.eval(formula).toString());
		        	
		        	// The value is 1 hour of work out, compute now how many calories
		        	// the user really burned by taking into account the time worked out
		        	double multiplier = Double.parseDouble(timeText.getText());
		        	if (timeCombo.getSelectedItem().equals("Minutes")) {
		        		multiplier = Double.parseDouble(timeText.getText()) / 60;
		        	}
		        	
		        	value = round(value * multiplier, 2);
		        	
		        	// Finally we have the correct calculation
		        	burnedText.setText(""+value);
		        } catch (Exception ex) {
		        	JOptionPane.showMessageDialog(null, "Could not compute the amount of calories you burned. Please make sure all values are valid integers");
		        	ex.printStackTrace();
		        }
			}
		});
        
        activityCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Update image based on activity name
				JComboBox cb = (JComboBox)e.getSource();
		        String workoutName = (String)cb.getSelectedItem();
		        setPicLabel(workoutName.toLowerCase().replaceAll("\\s", "")+".gif");
			}
		});
        
        clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Clear all text inputs and rest combo boxes
				activityCombo.setSelectedIndex(0);
				timeCombo.setSelectedIndex(0);
				weightText.setText("");
				timeText.setText("");
				burnedText.setText("");
			}
		});
        
        setTitle("Calories Burned Calculator");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Rounds a double to the given number of places
	 * 
	 * Reference from http://penguin.ewu.edu/cscd370/Spr2012/Labs1/round.txt
	 * 
	 * @param val
	 * @param plc
	 * @return
	 */
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
	 * Returns true if the string value can be parsed as a double
	 * 
	 * @param str
	 * @return boolean 
	 */
	public boolean isNumeric(String str)
	{
		try {
			Double.parseDouble(str);
		} catch(Exception e) {
			return(false);
		}

		return(true);
	}
	
	/**
	 * Sets the picLabel to a picture from the given file name
	 * 
	 * If the file can not be loaded it will show a message dialog.
	 * 
	 * @author Michael Williams (4/20/12)
	 * @param fileName
	 */
	private void setPicLabel(String fileName)
	{
        BufferedImage image = null;
		try {
			image = ImageIO.read(new File(fileName));
	        Image newImage = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
	        
	        if (picLabel == null) {
	        	picLabel = new JLabel(new ImageIcon(newImage));
	        } else { 
	        	// Update existing pic label and redraw since the component is probably
	        	// already in a layout
	        	picLabel.setIcon(new ImageIcon(newImage));
	        	picLabel.repaint();
	        }
	        
		} catch (IOException ex) {

			JOptionPane.showMessageDialog(null, ex.toString() + " " + fileName);
		}
	}
	
	/**
	 * Main method, calls the OhmsLawCalculator class to create the GUI
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                        // "javax.swing.plaf.metal.MetalLookAndFeel");
                        // "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                         UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new CalorieCalculator().setVisible(true);
            }
        });
    }
}
