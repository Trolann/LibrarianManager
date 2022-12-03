# LibrarianManager
###### Written by: [Trevor Mathisen](https://github.com/Trolann), [Zohreh Ashtarilarki](https://github.com/ZohrehAshtarilarki) [and Osman Saeday](https://github.com/osmansaeday)
### Installation instructions
1) Developed using JDK 18
2) Create Run confliguration in IntellIJ
3) Run the PSVM in Utility.java
# ^^^^ TODO: This section ^^^^
# Class Descriptions

## Media


## Utility
### Main/GUI Function

```java
public static void main(String[] args)
```

### Common filename primitive
```java
public final static String libraryFileName
```

### Common filename getter
```java
public static String getLibraryFileName()
```

### Recursive Scanner function
```java
public static HashMap<String, LibraryFunctions> _listMedia(HashMap<String, 
                                                           LibraryFunctions> _returnMap, 
                                                           Scanner _fileScanner)
```

### Media getter (all)
```java
public static HashMap<String, LibraryFunctions> listMedia()
```

### Media getter (random)
```java
public static LibraryFunctions getRandom()
```

### Video
#### ***Constructor***
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
public String displayInfo()
public String toString()
public boolean equals(Object o)
public int hashCode()
public void checkIn() // Wrapper function to _checkInOut
public void checkOut() // Wrapper function to _checkInOut
public String displayOtherInfo()
```


#### *Check-in/out *
```java
private boolean _checkInOut(boolean checkInMedia) {
```
### Audiobook
#### ***Constructor***
```java
Video(String inputLine)
```

#### *Primitives*
```java
private final String bookAuthor;
private final String readBy;
private final int bookRating;
private final int listeningTime;
```
```java
AudioBook(String inputLine)
```
#### *Overridden functions*

```java
public String displayInfo()
public String toString()
public boolean equals(Object o)
public int hashCode()
public void checkIn() // Wrapper function to _checkInOut
public void checkOut() // Wrapper function to _checkInOut
public String displayOtherInfo()
```

#### *Check-in/out *
```java
private boolean _checkInOut(boolean checkInMedia) {
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
public String displayInfo()
public String toString()
public boolean equals(Object o)
public int hashCode()
public void checkIn() 
public void checkOut() 
public String displayOtherInfo()
```


#### *Helper Method*
```java
private void handler(boolean checkInMedia) 
```


### Book
#### ***Constructor***
```java
Book(String inputLine)
```

#### *Primitives*
```java
private final String pulishedPaperTopic;
private final String publishedPaperISSN;
private final localdate publishedPaperPublicationDate;
```

#### *Overridden Methods*

```java
public String displayInfo()
public String toString()
public boolean equals(Object o)
public int hashCode()
public void checkIn() 
public void checkOut() 
public String displayOtherInfo()
```


#### *Helper Method*
```java
private void handler(boolean checkInMedia) 
```