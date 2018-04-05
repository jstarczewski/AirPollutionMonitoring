

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class SearchGUI {

    private static JFrame frame;
    private static JButton searchButton;
    private static JTextField searchField;
    private static JList list;
    private static JButton select;
    private static DefaultListModel model;
    private static int currentIndex = -1;
    private static final Color backgroundCol = Color.WHITE;

    public static void main(String []args) {

        frame = new JFrame("Wybierz Miasto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 500, 0, 0);
        renderGUI();
        frame.pack();
        frame.setVisible(true);

    }
    private static void renderGUI() {


        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setBackground(backgroundCol);
        frame.add(panel);

        JLabel city = new JLabel("City:");

        searchField = new JTextField();

        searchButton = new JButton("Search");

        model = new DefaultListModel();
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        select = new JButton("Choose");
        select.setEnabled(false);

        panel.add(searchField, "growx, pushx");
        panel.add(searchButton, "wrap, skip");
        panel.add(new JScrollPane(list), "growx, pushx, growy, pushy");
        panel.add(select, "skip, wrap");

        java.util.List<Sensor> Sensors = new Search("Warszawa").getSensorList();
        for (Sensor sensor : Sensors) {
            model.addElement(sensor);
        }

    }

}
