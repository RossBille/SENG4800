@author Clinton Ryan  
@author Jordan Koncz  
@author Ross Bille  
@author Josh Brackenbury  
@author Rowan Burgess  
@author Andrew Thursby  
@author Peter O'Loughlin  

##Description
Repository for University of Newcaslte City Evolution Project 
#Requirements
The design goal is to provide an interactive digital experience for pedestrians of Newcastle. The current, broad scope details the use of motion detection and projectors to allow people to interact with our system in a fun way.
##Coding Style Guidelines
The coding style that is used for this project is the K&R and Allman coding style.
K&R desribes that all functions have a new line separated brace and condition statments have attached brackets.
Allman describes that all functions and condition statements have a new line separated bracket.

K&R will generally suit the purpose for coding clean Javascript. This is because there are extensive use of anonymous functions and clojures wrapped inside braces as arguments. K&R is extensively useful where braces and brackets are generally wrapped together. Allman generally suits the purpose for coding clean C style syntax code, where this is a separation from braces and brackets.

The indenting style uses 4 spaces for indentation. Coding style uses 4 spaces indents over using tabs.

### K&R Style
    public static void main(String[] args)
    {
      for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) {
          System.out.println("K&R");
        } 
        else {
          System.out.println("K&R Odd");
        }
      }
    }

### Allman style
    public static void main(String[] args)
    {
      for (int i = 0; i < 10; i++)
      {
        if (i % 2 == 0)
        {
          System.out.println("K&R Even");
        }
        else
        {
          System.out.println("K&R Odd");
        }
      }   
    }

##Commit Guidelines
The commit guidelines can be found @ http://wiki.typo3.org/CommitMessage_Format_(Git).
Example commit:
  Case 25. Added initial android app
###Topic Description (First Line)
* Prefix the first line with a proper tag: [BUGFIX],[FEATURE],[TASK] or [API]
Keep the description under 52 characters

###Message Body (Paragraph)
* Commit message body describes the problem and the change introduced by the commit. 
Keep it simple and don't repeat information

###Relationships
* Relationships refer the commit to a bug tracker entry. 
Integrate to close or update the tracker.
