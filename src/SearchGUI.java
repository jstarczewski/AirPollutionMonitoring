import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchGUI {


    public static class okienko extends JFrame {
        private JButton button = new JButton("Szukaj");
        private JTextField input = new JTextField("", 5);
        private JLabel label = new JLabel("Wpisz miasto");
        private JLabel labellist = new JLabel("lub wybierz");
        String []str = {"Warszawa", "Krakow", "Wroclaw", "Gdansk", "Bialystok"};
        private JList list = new JList(str);

        public okienko () {
            super("Zaniczyszczenie powietrza");
            this.setBounds(100, 100, 250, 300);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(3, 2, 2, 2));
            container.add(label);
            container.add(input);
            container.add(labellist);
            container.add(list);
            button.addActionListener(new ButtonEventListener ());
            container.add(button);
        }

        class ButtonEventListener implements ActionListener {
            public void actionPerformed (ActionEvent e){
                String message = "";
                message += "Jezeli miasto znalieziono, to - Wynik wyszukiwania zaniczyszczenia dla " + input.getText() + list.getSelectedValue() + "\n";
                message += "Jezeli miasta nie ma, to - Miasto " + input.getText() + " nie znaleziono \n";
                JOptionPane.showMessageDialog(null, message, "Wynik", JOptionPane.PLAIN_MESSAGE);
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

            }
        }
    }



}
