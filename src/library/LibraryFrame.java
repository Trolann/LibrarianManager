package library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

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
                updateLibraryUI(searchFilter);
            }
        });
        audiobookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Audiobook";
                updateLibraryUI(searchFilter);

            }
        });
        eTextbookRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "eTextbook";
                updateLibraryUI(searchFilter);

            }
        });
        newspaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Newspaper";
                updateLibraryUI(searchFilter);

            }
        });
        publishedPaperRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "PublishedPaper";
                updateLibraryUI(searchFilter);

            }
        });
        videoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilter = "Video";
                updateLibraryUI(searchFilter);

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
        String _searchString = searchString.getText();

        // Add new values into drop-down
        LinkedList<LibraryFunctions> displayList = new LinkedList<>();
        Utility.listMedia().forEach((key, value) -> {
            if(value.toString().contains(searchFilter)) {
                // If there's a search string, see if it's value matches (short-circuits)
                if (_searchString.length() > 0 && value.displayInfo().toLowerCase().contains(_searchString.toLowerCase())) {
                    displayList.add(value);
                }
                if (_searchString.length() == 0) {
                    // If we're here it's because there's no title search, displayList should have all objects
                    displayList.add(value);
                }

            }
        });

        for (LibraryFunctions mediaObject : displayList) {
            searchResultsComboBox.addItem(mediaObject.displayInfo());
        }

    }

    private JTextField searchString;
    private JComboBox<String> searchResultsComboBox;
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
    private JButton randomMediaButton;

}