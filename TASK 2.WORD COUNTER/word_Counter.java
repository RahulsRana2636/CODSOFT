
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class word_Counter extends JFrame implements ActionListener {

    JTextArea t;
    JButton button;
    JLabel label;
    JTextArea resultTextArea;

    public word_Counter() {
        setTitle("Word Counter");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        t = new JTextArea();
        t.setBounds(50, 50, 300, 200);

        button = new JButton("Count");
        button.setBounds(50, 260, 70, 35);
        button.setBackground(Color.red);
        button.setForeground(Color.yellow);

        button.addActionListener(this);

        label = new JLabel();

        add(t);
        add(button);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        add(new JScrollPane(resultTextArea), BorderLayout.EAST);
        add(label);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String text = t.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter some text ",
                        "Empty", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String wd[] = text.split("\\s");
            HashSet<String> set = new HashSet<String>();
            for (String element : wd) {
                set.add(element);
            }
            int totalWords = set.size();

            String statistics = generateStatistics(wd, totalWords);
            resultTextArea.setText(statistics);
        }
    }

    private String generateStatistics(String[] words, int totalWordCount) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Total unique words: ").append(totalWordCount).append("\n");
        sb.append("Word frequency:").append("\n");
        wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

        return sb.toString();
    }

    public static void main(String[] args) {

        word_Counter c = new word_Counter();
        c.setVisible(true);
    }
}
