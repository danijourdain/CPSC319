# CPSC 319 Winter 2022 Assignment 3

## This file contains instructions on how to run the code files for Assignment 3.

In order to successfully run the main version of this program (Binary Search Tree):

    1) Use the command-line/terminal of your machine to navigate to the folder where this README file was found.
        - Ensure all code files remain in this folder, otherwise there will be issues.
        - Ensure the input text file to be used is also placed in this folder
    
    2) Run the command "javac *.java"

    3) After successful compilation, run the command "java Assign3 input.txt output1.txt output2.txt"
        - input.txt is the input text file containing one student's data and opcode per line
        - output1.txt is the output text file which will contain the results of depth-first, in order traversal
            of the tree with one student's data per line. Note that this file does not need to exist already.
        - output2.txt is the output text file which will contain the results of the breadth-first traversal
            of the tree with one student's data per line. Note that this file does not need to exist already.
        - IMPORTANT: both text files MUST be enetered with the .txt extension, otherwise the program will 
            encounter an error and terminate

In order to successfully run the AVL Tree version of this program:

*Note that the AVL version of this program can only do insertions into the tree. Do not use an input file where*
*there is a delete operation, otherwise the program will encounter an error and terminate.*

    1) Use the command-line/terminal of your machine to navigate to the folder where this README file was found.
        - Ensure all code files remain in this folder, otherwise there will be issues.
        - Ensure the input text file to be used is also placed in this folder
    
    2) Run the command "javac *.java"

    3) After successful compilation, run the command "java Assign3b input.txt output1.txt output2.txt"
        - input.txt is the input text file containing one student's data and opcode per line
        - output1.txt is the output text file which will contain the results of depth-first, in order traversal
            of the tree with one student's data per line. Note that this file does not need to exist already.
        - output2.txt is the output text file which will contain the results of the breadth-first traversal
            of the tree with one student's data per line. Note that this file does not need to exist already.
        - IMPORTANT: both text files MUST be enetered with the .txt extension, otherwise the program will 
            encounter an error and terminate

This program does not use any packages or external libraries, so there is no need for special commands to 
be entered. As long as the commands mentioned above are used, the program should run successfully.
