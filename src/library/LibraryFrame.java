package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LibraryFrame extends JFrame{
    public LibraryFrame() {
        setContentPane(mainPanel);
        setTitle("Library Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTitle.setText("testing");
                lblCreatorsName.setText("testing 123");
                lblCreatorsName.setVisible(true);
                lblCreatorsName.updateUI();
                mainPanel.updateUI();
            }
        });
    }

    private JCheckBox bookFilter;
    private JCheckBox audioBooksFilter;
    private JTextField searchTitle;
    private JCheckBox newspaperFilter;
    private JCheckBox eTextbookFilter;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JComboBox searchResultsComboBox;
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton searchButton;
    private JLabel searchResultsLabel;
    private JLabel resultCreatorsName;
    private JPanel mainPanel;
    private JLabel lblCreatorsName;

}
