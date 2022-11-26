package library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

public class LibraryFrame extends JFrame{
    private String searchFilter;

    public LibraryFrame() {
        setContentPane(mainPanel);

        setTitle("Library Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        radioButtonGroup.add(publishedPaperRadioButton);
        radioButtonGroup.add(bookRadioButton);
        radioButtonGroup.add(eTextbookRadioButton);
        radioButtonGroup.add(videoRadioButton);
        radioButtonGroup.add(newspaperRadioButton);
        radioButtonGroup.add(audiobookRadioButton);
        searchResultsComboBox.addItem("Nothing selected");

        searchButton.addActionListener(e -> updateLibraryUI(searchFilter));
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
        searchResultsComboBox.addActionListener(e -> {

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
        });
        checkInButton.addActionListener(e -> {
                try {
                    String selection = Objects.requireNonNull(searchResultsComboBox.getSelectedItem()).toString();
                    Utility.listMedia().forEach((key, value) -> {
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
        pickSomethingForMeButton.addActionListener(e -> {
            searchResultsComboBox.removeAllItems();
            radioButtonGroup.clearSelection();
            searchResultsComboBox.addItem(Objects.requireNonNull(Utility.getRandom()).displayInfo());

        });
    }

    public String getSearchFilter(){
        return this.searchFilter;
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
    private JButton pickSomethingForMeButton;
    private JButton randomMediaButton;
    private ButtonGroup radioButtonGroup = new ButtonGroup();




}