import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PollutionGUI {
    private static JFrame frame;
    private static JButton close;
    private static DefaultListModel<Sensor> model;
    private static final Color backgroundCol = Color.WHITE;

    public static void openPollutionGUI(float x, float y) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                renderGUI(x,y);
            }
        });
    }

      private static void renderGUI(float x, float y) {


        frame = new JFrame("Wybierz Miasto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds((int)x, (int)y, 0, 0);
        frame = new JFrame("Dane na temat zanieczyszczenia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setBackground(backgroundCol);
        frame.add(panel);

        frame.pack();
        frame.setSize(600, 400);

        frame.setVisible(true);

        close = new JButton("Back to station search");
        close.setEnabled(true);

        model = new DefaultListModel<>();

        JList<Sensor> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(new JScrollPane(list), "growx, pushx, growy, pushy");
        panel.add(close, "dock south");
        java.util.List<Sensor> stationList = CurrentPollution.getInstance().getPollution();
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
                SearchGUI.main(null);
            }
        });
    }


}

