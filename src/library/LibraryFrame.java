package library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryFrame extends JFrame{
    public LibraryFrame() {
        setContentPane(mainPanel);

        setTitle("Library Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        searchResultsComboBox.addItem("Nothing selected");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchString.setText("testing");
                testLabel.setText(searchFilter);
            }
        });
        bookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "book";
            }
        });
        audiobookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "audiobook";
            }
        });
        eTextbookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "etextbook";
            }
        });
        newspaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "newspaper";
            }
        });
        publishedPaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "publishedpaper";
            }
        });
        videoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "video";
            }
        });
    }

    private String searchFilter;


    public void updateLibraryUI(String searchString, String searchFilter) {
        // Clear old values in drop-down (N/A check-in/out)

        // Add new values into drop-down

        // Update check-in/out label for first value
    }

    private JTextField searchString;
    private JComboBox searchResultsComboBox;
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton searchButton;
    private JLabel resultCreatorsName;
    private JPanel mainPanel;
    private JLabel lblCreatorsName;
    private JRadioButton bookRadioButton;
    private JRadioButton audiobookRadioButton;
    private JRadioButton eTextbookRadioButton;
    private JRadioButton newspaperRadioButton;
    private JRadioButton publishedPaperRadioButton;
    private JRadioButton videoRadioButton;
    private JLabel availabilityLabel;
    private JLabel testLabel;

}
