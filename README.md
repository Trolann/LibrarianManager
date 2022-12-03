# LibrarianManager

TODO: Make a readme.md


# Installation instructions
1) Developed using JDK 18
2) Create Run confliguration in IntellIJ
3) Run the PSVM in Utility.java

# Class Descriptions

## Media


## Utility


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