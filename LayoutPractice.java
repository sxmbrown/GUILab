
/**
 * Samuel Brown
 * November 8, 2016
 * Lab 8 -- GUIs 
 */
import javax.swing.*;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class LayoutPractice extends JFrame implements ActionListener {
    /**
     * member variables to create GUI components.
     */
    private static final long serialVersionUID = 1L;
    JButton clickHere = new JButton("Don't click here!!");
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel panelTwo = new JPanel(new BorderLayout());
    JLabel whiteBox = new JLabel("You've clicked in the central panel 0 times.");
    JButton musicButton = new JButton("How about some music?");
    int boxWidth = 200;
    int boxHeight = 200;
    int count = 0;
    int number = 0;

    /**
     * Constructor of the main class. Adds all the components to the GUI.
     */
    public LayoutPractice() {
        super("Layout Practice");
        this.setPreferredSize(new Dimension(1200, 1200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        makeAPanel centerPanel = new makeAPanel();
        centerPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                number++;
                if (number == 1) {
                    whiteBox.setText("You've clicked in the central panel " + number + " time.");
                } else {
                    whiteBox.setText("You've clicked in the central panel " + number + " times.");
                }
            }
        });
        centerPanel.add(whiteBox, BorderLayout.CENTER);
        clickHere.addActionListener(this);
        panelTwo.add(clickHere, BorderLayout.SOUTH);
        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(panelTwo, BorderLayout.EAST);
        musicButton.addActionListener(new ActionListener() {
            /**
             * actionPreformed override to play music when musicButton is clicked. Button
             * will display an error message dialog if the file isn't found.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioPlayer MGP = AudioPlayer.player;
                AudioStream BGM = null;
                AudioData MD;
                ContinuousAudioDataStream loop = null;

                try {
                    BGM = new AudioStream(new FileInputStream("ChillingMusic.wav"));
                    MD = BGM.getData();
                    loop = new ContinuousAudioDataStream(MD);
                } catch (IOException error) {
                                       
                }

                MGP.start(BGM);

            }

        });
        panelTwo.add(musicButton, BorderLayout.NORTH);

        setContentPane(basePanel);
        pack();
    }

    /**
     * Inner class to be used to create instances of a panel object that can be
     * drawn on.
     * 
     * @author Samuel Brown
     */
    class makeAPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D square = (Graphics2D) g;
            Graphics2D squareTwo = (Graphics2D) g;
            Graphics2D cirlce = (Graphics2D) g;
            this.setBackground(Color.WHITE);
            double height = super.getSize().getHeight();
            double width = super.getSize().getWidth();

            squareTwo.setColor(Color.pink);
            squareTwo.fillRect((int) (((width) - (boxWidth * 4)) / 2), (int) (((height) - (boxHeight * 4)) / 2),
                    boxWidth * 4, boxHeight * 4);

            square.setColor(Color.cyan);
            square.fillRect((int) (((width) - (boxWidth * 2)) / 2), (int) (((height) - (boxHeight * 2)) / 2),
                    boxWidth * 2, boxHeight * 2);
            
            cirlce.setColor(Color.YELLOW);
            cirlce.fillOval((int) (((width) - (boxWidth)) / 2), (int) (((height) - (boxHeight)) / 2),
                    boxWidth, boxHeight);

        }
    }

    /**
     * actionPerformed keeps track of how man times clickHere has been clicked and
     * displays a message notifying the user of such.
     */
    public void actionPerformed(ActionEvent event) {
        count++;
        if (count == 1) {
            JOptionPane.showMessageDialog(clickHere, "Please stop!! You've clicked me " + count + " time.", "WHY?!", 1);
        } else {
            JOptionPane.showMessageDialog(clickHere, "Please stop!! You've clicked me " + count + " times.", "WHY?!",
                    1);
        }
    }

    /**
     * main method runs GUI on the event dispatch thread. USAGE: java LayoutPractice
     * 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LayoutPractice().setVisible(true);

            }
        });
    }

}
