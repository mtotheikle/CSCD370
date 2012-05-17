import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import sun.tools.tree.LengthExpression;

public class TriangleJFrame extends JFrame {
	
	private JLabel titleLabel, picLabel, sideALabel, sideBLabel, sideCLabel, angleALabel, angleBLabel, angleCLabel, perimeterLabel, areaLabel, triangleTypeLabel;
	
	private JTextField sideAText, sideBText, sideCText, angleAText, angleBText, angleCText, perimeterText, areaText, triangleTypeText;
	
	private JRadioButton degressButton, radiansButton;
	
	public TriangleJFrame()
	{		
		titleLabel = new JLabel("Triangle Classifier");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		
		sideALabel = new JLabel("Length of Side (a)");
		sideBLabel = new JLabel("Length of Side (b)");
		sideCLabel = new JLabel("Length of Side (c)");	
		
		angleALabel = new JLabel("Angle A");
		angleBLabel = new JLabel("Angle B");
		angleCLabel = new JLabel("Angle C");
		
		
		sideAText = new JTextField(10);
		sideBText = new JTextField(10);
		sideCText = new JTextField(10);
		
		angleAText = new JTextField(10);
		angleBText = new JTextField(10);
		angleCText = new JTextField(10);

		perimeterLabel = new JLabel("Perimeter");
		perimeterText = new JTextField();

		areaLabel = new JLabel("Area");
		areaText = new JTextField();

		triangleTypeLabel = new JLabel("Triangle Type");
		triangleTypeText = new JTextField();
		
		degressButton = new JRadioButton("Degress");
	    radiansButton = new JRadioButton("Radians");

	    JButton clearButton = new JButton("Clear");
        JButton calcButton = new JButton("Calculate");
        
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(degressButton);
	    group.add(radiansButton);
	    
	    setPicLabel("archery.gif");
	    setResizable(false);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    			.addComponent(titleLabel)
            	.addComponent(picLabel)
                .addComponent(calcButton) 
                .addGroup(layout.createSequentialGroup()
	           		.addGroup(layout.createParallelGroup(LEADING)
		                .addComponent(sideALabel)
		                .addComponent(sideBLabel) 
		                .addComponent(sideCLabel)
		             )
		             .addGroup(layout.createParallelGroup(LEADING)
		                .addComponent(sideAText)
		                .addComponent(sideBText)
            			.addComponent(sideCText)
		             )
	             )
             )
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		 .addGroup(layout.createSequentialGroup()
 	           		.addGroup(layout.createParallelGroup(LEADING)
 	           			.addComponent(perimeterLabel)
 	           			.addComponent(perimeterText)
           				.addComponent(radiansButton)
 		                .addComponent(angleALabel)
 		                .addComponent(angleBLabel) 
 		                .addComponent(angleCLabel)
 		             )
 		             .addGroup(layout.createParallelGroup(LEADING)
           				.addComponent(degressButton)
 		                .addComponent(angleAText)
 		                .addComponent(angleBText)
             			.addComponent(angleCText)
 		             )
 	             )
             )
		);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
               .addGroup(layout.createSequentialGroup()
        		   .addGroup(layout.createParallelGroup(BASELINE)
        				   .addComponent(titleLabel)
    				   .addComponent(picLabel)
    				)
    				.addGroup(layout.createParallelGroup(BASELINE)
    					.addComponent(radiansButton)
    					.addComponent(degressButton)
    				)
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                		.addComponent(sideALabel)
            		 	.addComponent(sideAText)
            		 	.addComponent(angleALabel)
            		 	.addComponent(angleAText)                      
                   )
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		   	.addComponent(sideBLabel)
                      	.addComponent(sideBText)
                      	.addComponent(angleBLabel)
                      	.addComponent(angleBText)
                   )  
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		   .addComponent(sideCLabel)
            		   .addComponent(sideCText)
            		   .addComponent(angleCLabel)
            		   .addComponent(angleCText)
        		   )
                )
             )
            .addGroup(layout.createParallelGroup(LEADING)
        		.addComponent(calcButton)
            )
		);
       
        setTitle("Triangle Classifier");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                new TriangleJFrame().setVisible(true);
            }
        });
    }
}
