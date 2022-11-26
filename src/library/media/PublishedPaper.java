package library.media;

import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class PublishedPaper extends Media implements LibraryFunctions {

    private final String publishedPaperTopic;
    private final String publishedPaperISSN;
    private final LocalDate publishedPaperPublicationDate;

    public PublishedPaper(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.publishedPaperISSN = values[4];
        this.publishedPaperTopic = values[5];
        this.publishedPaperPublicationDate = LocalDate.parse(values[6]);
    }

    @Override
    public String toString() {
        return "PublishedPaper{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
    }

    public String getPublishedPaperTopic() {
        return publishedPaperTopic;
    }

    public LocalDate getPublishedPaperPublicationDate() {
        return publishedPaperPublicationDate;
    }

    public String getPublishedPaperISSN() {
        return publishedPaperISSN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    @Override
    public void checkIn() {
        handler(false);
    }

    @Override
    public void checkOut() {
        handler(true);
    }

    private void handler(boolean availability) {
        Queue<String> fileLines = new LinkedList<>();

        try(Scanner inputFile = new Scanner(new File(library.Utility.getLibraryFileName()))) {
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();

                if (line.indexOf(this.title) > 0) {
                    line = line.replaceFirst(availability ? "in" : "out", !availability ? "in" : "out");
                    this.checkInOut();
                }
                fileLines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not in the specified directory.");
            return;
        }
        try(PrintWriter outputFile = new PrintWriter(library.Utility.getLibraryFileName())){
            while(!fileLines.isEmpty()) {
                outputFile.println(fileLines.poll());
            }
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not in the specified directory.");
        }
    }


    @Override
    public String displayInfo() {
        return this.title +" by " + this.creator;
    }

}
