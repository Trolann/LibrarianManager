package library.media;

import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class eTextbook extends Media implements LibraryFunctions {
    private String eTextbookPublisher;
    private LocalDate eTextbookReleasedDate;
    private int eTextbookISBN;

    public eTextbook(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        //this.eTextbookISBN = Integer.parseInt(values[4]);
        this.eTextbookPublisher = values[5];
        this.eTextbookReleasedDate = LocalDate.parse(values[6]);
    }

    public void seteTextbookPublisher(String eTextbookPublisher) {
        this.eTextbookPublisher = eTextbookPublisher;
    }
    public String geteTextbookPublisher() {
        return eTextbookPublisher;
    }

    public void seteTextbookReleasedDate(LocalDate eTextbookReleasedDate) {
        this.eTextbookReleasedDate = eTextbookReleasedDate;
    }
    public LocalDate geteTextbookReleasedDate() {
        return eTextbookReleasedDate;
    }

    public void seteTextbookISBN(int eTextbookISBN) {
        this.eTextbookISBN = eTextbookISBN;
    }
    public int geteTextbookISBN() {
        return eTextbookISBN;
    }

    @Override
    public String toString() {
        return "eTextbook{" +
                "eTextbookPublisher='" + eTextbookPublisher + '\'' +
                ", eTextbookReleasedDate=" + eTextbookReleasedDate +
                ", eTextbookISBN=" + eTextbookISBN +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof eTextbook eTextbook)) return false;
        return geteTextbookISBN() == eTextbook.geteTextbookISBN() &&
                geteTextbookPublisher().equals(eTextbook.geteTextbookPublisher()) &&
                geteTextbookReleasedDate().equals(eTextbook.geteTextbookReleasedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(geteTextbookPublisher(), geteTextbookReleasedDate(), geteTextbookISBN());
    }

    @Override
    public boolean checkIn() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        String value = "";
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String input = inFile.nextLine();
                Scanner readWord = new Scanner(input);
                readWord.useDelimiter(",");
                while(readWord.hasNext()) {
                    value =  readWord.next();
                    if(input.indexOf(this.title) > 0) {
                        if (value.equals("out")) {
                            value.replace("out", "in");
                            break;
                        }
                        else if (value.equals("in")) {
                            System.out.println("The eTextbook you are looking for is not currently available.");
                            break;
                        }
                    }
                }
                readWord.close();
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(int i = 0; i < value.length(); i++) {
                fileWriter.println(value.charAt(i));
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean checkOut() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        String value = "";
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String input = inFile.nextLine();
                Scanner readWord = new Scanner(input);
                readWord.useDelimiter(",");
                while(readWord.hasNext()) {
                    value =  readWord.next();
                    if(input.indexOf(this.title) > 0) {
                        if (value.equals("in")) {
                            value.replace("in", "out");
                            break;
                        } else {
                            System.out.println("You can't check out the eTextbook since it's in the library.");
                        }
                    }
                }
                readWord.close();
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(int i = 0; i < value.length(); i++) {
                fileWriter.println(value.charAt(i));
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
    @Override
    public String displayInfo() {
        return "null";
    }
}