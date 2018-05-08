import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGUI {


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

        searchField = new JTextField();

        searchButton = new JButton("Search");

        model = new DefaultListModel();

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeSwingWorker();
            }
        });
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isEmpty()) {
                    int index = list.getSelectedIndex();
                    search.getPollution(((Station) model.getElementAt(index)));
                    model.clear();
                    PollutionGUI.openPollutionGUI(frame.getAlignmentX(), frame.getAlignmentY());
                    frame.dispose();
                } else {
                    model.add(0, search.warnAboutEmptySelection().get(0));
                }
            }
        });
    }

    private static void executeSwingWorker() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                model.clear();
                searchField.setEnabled(false);
                search.setInput(searchField.getText());
                java.util.List<Station> stations = search.getStationList();
                for (Station station : stations) {
                    model.addElement(station);
                }
                searchField.setEnabled(true);
                return null;
            }
        }.execute();

    }

}
