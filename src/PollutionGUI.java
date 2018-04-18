import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PollutionGUI {
    private static JFrame frame;
    private static JButton close;
    private static JList list;
    private static DefaultListModel model;
    private static final Color backgroundCol = Color.WHITE;

    public static void openPollutionGUI() {
        frame = new JFrame("Wybierz Miasto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 500, 0, 0);
        renderGUI();
        frame.pack();
        frame.setVisible(true);
    }

    public static void renderGUI() {


        frame = new JFrame("Dane na temat zanieczyszczenia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 500, 0, 0);

        frame.pack();
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setBackground(backgroundCol);
        frame.add(panel);

        JLabel city = new JLabel("City:");


        close = new JButton("Back to station search");
        close.setEnabled(true);

        model = new DefaultListModel();

        /**TODO
         *
         *  Tu po prostu brak parametru dla modelu bo wyswietlamy wszystko w jednym okeinku
         *  potem jak pójdzie do PollutionGUI to sie warninga usunie
         *
         */

        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(new JScrollPane(list), "growx, pushx, growy, pushy");
        panel.add(close, "dock south");
        java.util.List<Sensor> stationList = CurrentPollution.getInstance("").getPollution();
        for (Sensor sensor : stationList) {
            model.addElement(sensor);
        }


        readInput();

    }

    private static void readInput() {

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                frame.dispose();

            }
        });
    }


}

