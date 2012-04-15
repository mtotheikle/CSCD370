import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.awt.Color.*;
import static javax.swing.GroupLayout.Alignment.*;
public class MainScreen {
    int nGap = 20;
    JFrame frame = new JFrame();
    GridLayout grdlayout = new GridLayout(6, 1);
    JPanel Buttonlayout = new JPanel(grdlayout), TopPanel = new JPanel(),
            BottomPanel = new JPanel(), LeftPanel = new JPanel(),
            RightPanel = new JPanel();
    GroupLayout grplayout = new GroupLayout(frame.getContentPane()),
            grplayoutlabel = new GroupLayout(TopPanel);
    JButton[] ButtonList = new JButton[6];
    JLabel Title = new JLabel("Gooey Application");
    String[] ButtonNames = {"Home", "Getting Started Checklist", "Contacts",
    "To Do List", "Appointments", "Messenger"}, URLs = {"google.ca", 
    "onemanga.com", "java-forums.org", "onepieceofbleach.com",
    "hidden-street.net", "sing365.com"};
    public MainScreen(){
        Init();
        InitLayout();
        CreateGUI();
    }
    public void Init(){
        for (int i = 0; i < 6; i++){
            final int x = i;
            ButtonList[i] = new JButton();
            ButtonList[i].setText(ButtonNames[i]);
            ButtonList[i].setActionCommand(ButtonNames[i]);
            ButtonList[i].setBackground(Color.white);
            Buttonlayout.add(ButtonList[i]);
            ButtonList[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    try{
                        java.awt.Desktop.getDesktop().browse(new URI("http://www." + URLs[x]));
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
        Dimension buttonSize = ButtonList[1].getPreferredSize();
        grplayoutlabel.setHorizontalGroup(
            grplayoutlabel.createSequentialGroup()
                .addGap((int)(buttonSize.getWidth()/2) + nGap)
                .addComponent(Title)
        );
        grplayoutlabel.setVerticalGroup(
            grplayout.createSequentialGroup()
                .addGap(nGap)
                //.addComponent(Title)
        );
 
    }
    public void InitLayout(){
        Dimension buttonSize = ButtonList[1].getPreferredSize();
        Sizes(buttonSize);
        Colors();
        Buttonlayout.setPreferredSize(new Dimension((int)(buttonSize.getWidth()),
                (int)(buttonSize.getHeight() * 6)));
        grplayout.setHorizontalGroup(
            grplayout.createParallelGroup(LEADING)
                .addComponent(TopPanel)
                .addComponent(BottomPanel)
                .addGroup(grplayout.createSequentialGroup()
                    .addComponent(LeftPanel)
                    .addComponent(Buttonlayout)
                    .addComponent(RightPanel)
        ));
        grplayout.setVerticalGroup(
            grplayout.createSequentialGroup()
                .addComponent(TopPanel)
                .addGroup(grplayout.createParallelGroup()
                    .addComponent(LeftPanel)
                    .addComponent(RightPanel)
                    .addComponent(Buttonlayout))
                .addComponent(BottomPanel)
        );
    }
    public void Sizes(Dimension bs){
        LeftPanel.setPreferredSize(new Dimension(nGap, (int)(bs.getHeight()*6)));
        RightPanel.setPreferredSize(new Dimension(nGap, (int)(bs.getHeight()*6)));
        BottomPanel.setPreferredSize(new Dimension(nGap*2 + (int)(bs.getWidth()), 50));
        TopPanel.setPreferredSize(new Dimension(nGap*2 + (int)(bs.getWidth()), 50));
    }
    public void Colors(){
        LeftPanel.setBackground(Color.green);
        RightPanel.setBackground(Color.red);
        BottomPanel.setBackground(Color.black);
        TopPanel.setBackground(Color.black);
    }
    public void CreateGUI(){
        //frame.setUndecorated(true);
        frame.getContentPane().setLayout(grplayout);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String args[]){
        new MainScreen();
    }
}