import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    static Random rng = new Random();
    int roll;
    int lastRoll = -1;
    boolean firstTime = true;
    public FortuneTellerFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(540, 425);
        setLocation(screenSize.width / 4, screenSize.height / 4);
        // define panels
        JPanel container = new JPanel();
        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        // define and set elements
        JLabel fortuneTellerLabel = new JLabel("Fortune Teller");
        fortuneTellerLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        JLabel fortuneTellerIcon = new JLabel(new ImageIcon("src/fc.jpg"));
        JScrollPane scrollPane = new JScrollPane();
        JTextArea fortuneDisplay = new JTextArea();
        fortuneDisplay.setRows(5);
        fortuneDisplay.setColumns(44);
        fortuneDisplay.setFont(new Font("Tahoma", Font.PLAIN, 11));
        scrollPane.add(fortuneDisplay);
        scrollPane.setViewportView(fortuneDisplay);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        JButton readMyFortuneButton = new JButton();
        readMyFortuneButton.setText("Read my fortune!");
        readMyFortuneButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                roll = rng.nextInt(11);
                if (roll == lastRoll) {
                    do {
                        roll = rng.nextInt(11);
                    } while (roll == lastRoll);
                }
                if (firstTime) {
                    fortuneDisplay.append(getFortune(roll));    // so there isn't an empty space in the panel
                    firstTime = false;
                } else {
                    fortuneDisplay.append("\n" + getFortune(roll));
                }
                System.out.println(roll);
                lastRoll = roll;
            }
        });
        JButton quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        readMyFortuneButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        // add elements to panels
        panel01.add(fortuneTellerLabel);
        panel01.add(fortuneTellerIcon);
        panel02.add(scrollPane);
        panel03.add(readMyFortuneButton);
        panel03.add(quitButton);
        // add panels to frame
        add(container);
        container.add(panel01);
        container.add(panel02);
        container.add(panel03);
        // misc
        setVisible(true);
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static String getFortune(int roll) {
        ArrayList<String> FORTUNES = new ArrayList<>();
        FORTUNES.add("This lab is most definitely overdue.");
        FORTUNES.add("Windows has a 67% chance of blue-screening today.");
        FORTUNES.add("Where you seek riches, you may come across eternal friendships.");
        FORTUNES.add("Someone, somewhere is plotting a war against shredded cheese.");
        FORTUNES.add("The time will be 5:37 PM at some point today.");
        FORTUNES.add("Hatred can easily consume anybody.");
        FORTUNES.add("Blink. Blink again. HAHA! Now you're blinking manually! >:D");
        FORTUNES.add("(fortune left intentionally blank)");
        FORTUNES.add("If you don't eat your meat, you can't have any pudding!");
        FORTUNES.add("There's a good chance that today's weather forecast is a lie.");
        FORTUNES.add("Did you remember to turn your stove off?");
        FORTUNES.add("Huh? Did you say something?");
        return FORTUNES.get(roll);
    }

}
