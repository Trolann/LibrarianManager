package library;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Objects;

public class LibraryFrame extends JFrame{
    // Components
    private JTextField searchString;
    private JComboBox<String> searchResultsComboBox;
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton searchButton;
    private JPanel mainPanel;
    private JRadioButton bookRadioButton;
    private JRadioButton audiobookRadioButton;
    private JRadioButton eTextbookRadioButton;
    private JRadioButton newspaperRadioButton;
    private JRadioButton publishedPaperRadioButton;
    private JRadioButton videoRadioButton;
    private JLabel availabilityLabel;
    private JButton pickSomethingForMeButton;
    private final ButtonGroup radioButtonGroup = new ButtonGroup();
    private String searchFilter;

    // Constructor
    public LibraryFrame() {
        setContentPane(mainPanel);
        setTitle("Library Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        // Setup radio buttons
        radioButtonGroup.add(publishedPaperRadioButton);
        radioButtonGroup.add(bookRadioButton);
        radioButtonGroup.add(eTextbookRadioButton);
        radioButtonGroup.add(videoRadioButton);
        radioButtonGroup.add(newspaperRadioButton);
        radioButtonGroup.add(audiobookRadioButton);

        // Setup dropdown
        searchResultsComboBox.addItem("Nothing selected");

        // Listeners

        // Pressing Search Button
        searchButton.addActionListener(e -> updateLibraryUI(searchFilter));

        // Pressing a radio button sets the search filter (of the objects .toString) and updates the UI
        bookRadioButton.addActionListener(e -> {
            searchFilter = "Book";
            updateLibraryUI(searchFilter);
        });
        audiobookRadioButton.addActionListener(e -> {
            searchFilter = "Audiobook";
            updateLibraryUI(searchFilter);

        });
        eTextbookRadioButton.addActionListener(e -> {
            searchFilter = "eTextbook";
            updateLibraryUI(searchFilter);

        });
        newspaperRadioButton.addActionListener(e -> {
            searchFilter = "Newspaper";
            updateLibraryUI(searchFilter);

        });
        publishedPaperRadioButton.addActionListener(e -> {
            searchFilter = "PublishedPaper";
            updateLibraryUI(searchFilter);

        });
        videoRadioButton.addActionListener(e -> {
            searchFilter = "Video";
            updateLibraryUI(searchFilter);

        });

        // Drop-down menu selection
        searchResultsComboBox.addActionListener(e -> {
            // If there's a search string in the GUI, use it to iterate thru the HashMap
            try {
                String selection = Objects.requireNonNull(searchResultsComboBox.getSelectedItem()).toString();
                Utility.listMedia().forEach((key, value) -> {
                    String available = value.isCheckedIn() ? "Available!" : "Checked out :(";
                    if(selection.contains(value.getTitle()))
                        availabilityLabel.setText(available);
                });
            }
            // This is thrown for the first result only
            catch (NullPointerException x) {
                System.out.println("nullptr");
            }
        });

        // Check-in button pressed
        checkInButton.addActionListener(e -> {
                try {
                    String selection = Objects.requireNonNull(searchResultsComboBox.getSelectedItem()).toString();
                    Utility.listMedia().forEach((key, value) -> {
                        // Confirm the title is checked out
                        if(selection.contains(value.getTitle()) && !value.isCheckedIn()) {
                            value.checkIn();
                            availabilityLabel.setText("Available!");
                        }
                    });
                }
                catch (NullPointerException x) {
                    System.out.println("nullptr");
                }
        });
        // Opposite of above
        checkOutButton.addActionListener(e -> {
            try {
                String selection = Objects.requireNonNull(searchResultsComboBox.getSelectedItem()).toString();
                Utility.listMedia().forEach((key, value) -> {
                    if(selection.contains(value.getTitle()) && value.isCheckedIn()) {
                        value.checkOut();
                        availabilityLabel.setText("Checked out :(");
                    }
                });
            }
            catch (NullPointerException x) {
                System.out.println("nullptr");
            }
        });

        // Random Media implementation
        pickSomethingForMeButton.addActionListener(e -> {
            searchResultsComboBox.removeAllItems();
            radioButtonGroup.clearSelection();
            searchResultsComboBox.addItem(Objects.requireNonNull(Utility.getRandom()).displayInfo());

        });
    }

    // Action which updates the UI with requires results, called by Search and RadioButton's
    public void updateLibraryUI(String searchFilter) {
        // Clear old values in drop-down (N/A check-in/out)
        searchResultsComboBox.removeAllItems();
        String _searchString = searchString.getText();

        // Add new values into drop-down
        LinkedList<LibraryFunctions> displayList = new LinkedList<>();
        Utility.listMedia().forEach((key, value) -> {
            if(value.toString().contains(searchFilter)) {
                // If there's a search string          Check the String, in lower case, to see if the given search string is in it
                // Short-circuits if no search string
                if (_searchString.length() > 0 && value.displayInfo().toLowerCase().contains(_searchString.toLowerCase())) {
                    displayList.add(value);
                } else {
                    // If we're here it's because there's no title search, displayList should have all objects
                    displayList.add(value);
                }

            }
        });

        // Add all found elements to the drop-down
        for (LibraryFunctions mediaObject : displayList) {
            searchResultsComboBox.addItem(mediaObject.displayInfo());
        }

    }
}