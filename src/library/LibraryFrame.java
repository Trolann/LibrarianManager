package library;

import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryFrame extends JFrame{
    private String searchFilter;

    public LibraryFrame() {
        setContentPane(mainPanel);

        setTitle("Library Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        searchResultsComboBox.addItem("Nothing selected");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLibraryUI(searchFilter);
            }
        });
        bookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Book";
            }
        });
        audiobookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Audiobook";
            }
        });
        eTextbookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "eTextbook";
            }
        });
        newspaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Newspaper";
            }
        });
        publishedPaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "PublishedPaper";
            }
        });
        videoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Video";
            }
        });
        searchResultsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String selection = searchResultsComboBox.getSelectedItem().toString();
                    Utility.listMedia().forEach((key, value) -> {
                        String available = value.isCheckedIn() ? "Available!" : "Checked out :(";
                        if(selection.toString().contains(value.getTitle()))
                            availabilityLabel.setText(available);
                    });
                }
                catch (NullPointerException x) {
                    System.out.println("nullptr");
                }
            }
        });
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selection = searchResultsComboBox.getSelectedItem().toString();
                    Utility.listMedia().forEach((key, value) -> {
                        if(selection.toString().contains(value.getTitle())) {
                            value.checkIn();
                            String available = value.isCheckedIn() ? "Available!" : "Checked out :(";
                            availabilityLabel.setText(available);
                        }
                    });
                }
                catch (NullPointerException x) {
                    System.out.println("nullptr");
                }
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selection = searchResultsComboBox.getSelectedItem().toString();
                    Utility.listMedia().forEach((key, value) -> {
                        if(selection.toString().contains(value.getTitle())) {
                            value.checkOut();
                            String available = value.isCheckedIn() ? "Available!" : "Checked out :(";
                            availabilityLabel.setText(available);
                        }
                    });
                }
                catch (NullPointerException x) {
                    System.out.println("nullptr");
                }
            }
        });
    }


    public void updateLibraryUI(String searchFilter) {
        // Clear old values in drop-down (N/A check-in/out)
        searchResultsComboBox.removeAllItems();

        // Add new values into drop-down
        ArrayList<LibraryFunctions> displayList = new ArrayList<>();
        Utility.listMedia().forEach((key, value) -> {
            if(value.toString().contains(searchFilter))
                displayList.add(value);
        });

        for (LibraryFunctions mediaObject : displayList) {
            searchResultsComboBox.addItem(mediaObject.displayInfo());
        }

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

}
