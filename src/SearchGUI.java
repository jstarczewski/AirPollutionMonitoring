import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGUI {

    /**
     * TODO
     * <p>
     * Puścić 2 wątkiem do rysowania aby nie było zwiechy gdy przykładowo dzownimi do Resta
     */

    private static JFrame frame;
    private static JButton searchButton;
    private static JTextField searchField;
    private static JList list;
    private static JButton select;
    private static DefaultListModel model;
    private static final Color backgroundCol = Color.WHITE;
    private static Search search = new Search();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                renderGUI();
            }
        });

    }

    private static void renderGUI() {

        frame = new JFrame("Wybierz Miasto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 0, 0);
        JPanel panel = new JPanel();
        new MigLayout();
        panel.setLayout(new MigLayout());
        panel.setBackground(backgroundCol);
        frame.add(panel);

        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);

        JLabel city = new JLabel("City:");

        searchField = new JTextField();

        searchButton = new JButton("Search");

        model = new DefaultListModel();

        /**TODO
         *
         *  Tu po prostu brak parametru dla modelu bo wyswietlamy wszystko w jednym okeinku
         *  potem jak pójdzie do PollutionGUI to sie warninga usunie
         *
         */

        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        select = new JButton("Select");
        select.setEnabled(true);

        panel.add(searchField, "growx, pushx");
        panel.add(searchButton, "wrap, skip");
        panel.add(new JScrollPane(list), "growx, pushx, growy, pushy");
        panel.add(select, "skip, wrap");


        readInput();


    }

    private static void readInput() {

        /*
         *
         * InteliDżej radzi użyc lambyd z javy 8, jednakze moim zdaniem nie bedzie
         * zle jak zrobiby przez inner klasę
         *
         */


        /**TO-DO
         *  Napisac nowy watek, w ktorym bedzie dzialal search, bo swing worker wywoluje sie tylko raz
         *
         *
         * */

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                model.clear();
                search.setInput(searchField.getText());
                java.util.List<Station> stations = search.getStationList();
                for (Station station : stations) {
                    model.addElement(station);
                }

                return null;
            }
        };

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swingWorker.execute();
            }
        });
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                java.util.List<Sensor> sensors = search
                        .getPollution(
                                ((Station) model.getElementAt(index))
                        );
                model.clear();
//               for(Sensor sensor : sensors) {
//                   model.addElement(sensor);
//               }

                PollutionGUI.openPollutionGUI();
                frame.dispose();

            }
        });
    }


}
