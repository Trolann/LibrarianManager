# LibrarianManager
###### Written by: [Trevor Mathisen](https://github.com/Trolann), [Zohreh Ashtarilarki](https://github.com/ZohrehAshtarilarki) [and Osman Saeday](https://github.com/osmansaeday)
###### YouTube Video: [https://youtu.be/P5EPoCfXCiI](https://youtu.be/P5EPoCfXCiI)
###### Github Link: [https://github.com/Trolann/LibrarianManager](https://github.com/Trolann/LibrarianManager)
### Installation instructions
1) Developed using JDK 19
2) Ensure the following packages are installed:
   - java.io.*
   - java.util.*
   - java.time.*
   - javax.swing.*
   - java.awt.event.*
2) Main method contained in library.Utility
3) Run 

# Class Descriptions

## Media
#### ***Constructor***
```java
public Media(String mediaTitle, String creator, boolean checkedIn)
```

#### *Primitives*
```java
protected String creator;
protected String title;
protected boolean checkedIn;
```

#### *Abstract Methods*

```java
abstract public String displayInfo(); 
abstract public String toString();
abstract public int hashCode();
```

#### *Public Methods*

```java
public boolean isCheckedIn(); //returns a boolean value of whether or not a media object has been checked in 
public void checkInOut(); //reverses the isCheckedIn status of a media object
```

#### *Overridden Methods*

```java
public int compareTo(Media media) //compares two media objects based on the alphabetical order of their title
```

## Utility
### Main/GUI Function

```java
public static void main(String[] args)
```

### Common filename primitive and getter
```java
public final static String libraryFileName
public static String getLibraryFileName()
```

### Recursive Scanner function
```
This function is called by listMedia and recursively scans _fileScanner
and returns a completed hashMap _returnMap.
```
```java
public static HashMap<String, LibraryFunctions> _listMedia(HashMap<String, 
                                                           LibraryFunctions> _returnMap, 
                                                           Scanner _fileScanner)
```

### Media getter (all)
```
This method is called by the GUI and returns a hashMap of all available media
by calling the recursive _listMedia function.
```
```java
public static HashMap<String, LibraryFunctions> listMedia()
```

### Media getter (random)
```java
public static LibraryFunctions getRandom()
```

### Video
#### ***Constructor***
```
The only constructor takes in the entire input line and parses it
```
```java
Video(String inputLine)
```

#### *Primitives*
```java
private final String videoDirector;
private final String videoStarActor;
private int videoRating;
private int videoRuntime;
```

#### *Overridden functions*

```java
public String displayInfo() // Display info to GUI
public String toString() // Parse information to debug string
public boolean equals(Object o) // For hashing
public int hashCode() // for hashing

```

```
public void checkIn() // Wrapper function to _checkInOut
public void checkOut() // Wrapper function to _checkInOut

```
```
public String displayOtherInfo() // Created to quiet down IDE
```


#### *Check-in/out *
```java
private boolean _checkInOut(boolean checkInMedia) {
```
### Audiobook
```
See Video class for method descriptions
```
#### ***Constructor***
```java
Audiobook(String inputLine)
```

#### *Primitives*
```java
private final String bookAuthor; // And associated getter
private final String readBy; // And associated getter
private final int bookRating; // And associated getter
private final int listeningTime; // And associated getter
```

### Book
#### ***Constructor***
```java
Book(String inputLine)
```

#### *Primitives*
```java
private final String bookPublisher;
private final String bookISSN;
private final localdate bookPublicationDate;
```

#### *Overridden Methods*

```java
public String displayInfo() //displays the title and author of book 
public String toString() //returns a String of the book object
public int hashCode() //hashes object based on title
public void checkIn()  //wrapper method for checking in 
public void checkOut() //wrapper method for checking out
```


#### *Helper Method*
```java
private void handler(boolean checkInMedia) //checks a book object in or out based on the boolean arg
```


### PublishedPaper
#### ***Constructor***
```java
PublishedPaper(String inputLine)
```

#### *Primitives*
```java
private final String pulishedPaperTopic;
private final String publishedPaperISSN;
private final localdate publishedPaperPublicationDate;
```

#### *Overridden Methods*

```java
public String displayInfo() //displays the title and author of publishedPaper 
public String toString() //returns a String of the publishedPaper object
public int hashCode() //hashes object based on title
public void checkIn()  //wrapper method for checking in 
public void checkOut() //wrapper method for checking out
```


#### *Helper Method*
```java
private void handler(boolean checkInMedia) //checks a publishedPaper object in or out based on the boolean arg
```


### Newspaper
#### ***Constructor***
```java
Newspaper(String inputLine)
```

#### *Primitives*
```java
private final String newspaperPublisher;
private final LocalDate newspaperReleasedDate;
private final int newspaperISSN;
```

#### *Overridden Methods*

```java
public String toString()
public String displayInfo()
public boolean equals(Object obj)
public int hashCode()
public void checkIn()
public void checkOut()
```


### eTextbook
#### ***Constructor***
```java
eTextbook(String inputLine)
```

#### *Primitives*
```java
private final String eTextbookPublisher;
private final LocalDate eTextbookReleasedDate;
private final String eTextbookISBN;
```

#### *Overridden Methods*

```java
public String toString()
public String displayInfo()
public boolean equals(Object obj)
public int hashCode()
public void checkIn()
public void checkOut()
```