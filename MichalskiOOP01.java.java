

package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Week 4 Assignment due Thursday 10/01/2020 at 11:59
// Unit 1 template with week 4 requirements of a Student class
// Object Oriented Programming Student Class Assignment

// Public main code
public class Main {

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        String studentName="";
        String studentYear="";

        //double variable to hold value. -1 is outside the bounds and would indicate either bad data
        //or an invalid entry
        double gpa=-1;



        //create string array to hold answers from the printQuestions fundtion
        String answers[];
        answers = new String[5]; //limitations... review

        //create a continue variable to see if the user still wants to answer questions
        int cont = 1;

        //do loop
        do{
            //try/catch will let us handle errors if the user enters a bad number (not 1-5)
            try {
                //call our function to print the main menu
                mainMenu();
                //ask the user to enter their selection
                System.out.print("Please Enter Your Numeric Selection: ");
                //store the users selection as an int. Remember since we're encased in try/catch even if the user enters bad data
                //it will be caught
                cont = myScanner.nextInt();
                //all arrays start at 0. Our numbers start at 1. This is why we subtract 1 from the users selection
                //we also store our answers in an array
                answers[cont - 1] = printQuestion(cont, myScanner);
                //switch and case can clean up long/messy ifs
                switch (cont) {
                    case 1:
                        //get the students name if they select 1
                        studentName = answers[cont - 1];
                        break;
                    case 2:
                        //get the students year if they select 2
                        studentYear = answers[cont - 1].toLowerCase();
                        break;

                    case 3:
                        //get the students GPA if they select 3 but needs to be a double
                        try {
                            gpa = Double.parseDouble(answers[cont - 1]);
                            //Handles that only a double as required input
                        } catch (Exception ex) {
                            System.out.println("Only double is a valid entry. Try again.");
                            myScanner.nextLine();
                            System.out.println("Press Enter to Clear Terminal");
                            myScanner.nextLine();
                            clearTer();
                        }
                        break;
                    case 4:
                        //check the data and print out results and displays student info
                        checkData(studentName, studentYear, gpa);
                        Student myStudent = new Student(studentName, studentYear, gpa);
                        myStudent.printStudent();
                        myScanner.nextLine();
                        System.out.println("Press Enter to Clear Terminal");
                        myScanner.nextLine();
                        clearTer();
                        break;


                }
                //this catch will handle users sending non numeric text. As long as the user enters a number they'll either be asked
                //a valid question or the menu will print it self out again and ask them again.
            }catch (Exception ex){
                System.out.println("Please enter numeric values between 1 and 5");
                myScanner.nextLine();
                System.out.println("Press Enter to Clear Terminal");
                myScanner.nextLine();
                clearTer();
            }
            //option 5 exits the program
        }while(cont!=5);
    }
    //Check the data to determine if anything is missing or if any invalid selections were made.
    public static void checkData(String name, String acYear, double gpa){
        ArrayList<String> badData = new ArrayList<String>();
        int missingData=0;
        missingData = missingData(name,acYear,gpa);
        //method 1 with ArrayList see commented out
        badData.add(badAcdYearEntries(acYear));
        badData.add(badGpa(gpa));
        badData.removeAll(Collections.singleton(""));
        //

        //method 2 with independent variables
        String badAy = badAcdYearEntries(acYear);
        String badGpa = badGpa(gpa);
        if(missingData>0){
            return;
        }else if(!badAy.isEmpty() || !badGpa.isEmpty()){
            System.out.printf("%s%s\n",badAy,badGpa);
        }else{
            System.out.println("The students name is " + name);
            System.out.println("The students academic year is " + acYear);
            System.out.println("The students GPA is " + gpa);
        }
        /*
        if(missingData>0){
            return;
        }else if(!badData.isEmpty()) {
            System.out.println(badData);
        }else{
            System.out.println("The students name is " + name);
            System.out.println("The students academic year is " + acYear);
            System.out.println("The students GPA is " + gpa);
        }*/
    }

    //check for empty selections which is error checking for the first three options
    public static int missingData(String name, String acYear, double gpa) {
        String[] questions = {"Students Name", "Students Academic Year", "Students GPA"};
        int countOfMissing = 0;
        if (name.isEmpty()) {
            System.out.println("It looks like you left " + questions[0] + " blank");
            countOfMissing++;
        }
        if (acYear.isEmpty()) {
            System.out.println("It looks like you left " + questions[1] + " blank");
            countOfMissing++;
        }
        if (gpa < 0){
            System.out.println("It looks like you left " + questions[2] + " blank");
            countOfMissing++;
        }
        return countOfMissing;
    }
    //To check academic years
    public static String badAcdYearEntries(String acdmcYear){
        ArrayList<String> validAys = new ArrayList<String>();
        validAys.add("freshmen");
        validAys.add("sophomore");
        validAys.add("junior");
        validAys.add("senior");


        // Needs to be only those selections
        if(!validAys.contains(acdmcYear)){
            return "It appears you've entered an invalid Academic Year.";
        }
        return "";
    }
    //required method to check GPA
    public static String badGpa(double gpa){
        double lower = 0.0;
        double upper = 4.0;
        if(gpa< lower || gpa > upper){
            return "GPA appears to be outside of the limits of 0.0 and 4.0. Please Try Again";
        }
        return "";
    }



    public static void clearTer(){
        System.out.println("\033[H\033[2J");
        //System.out.flush();
    }

    //cuts down on the amount of code
    private static String printQuestion(int arryPoist, Scanner myScan){
        String retValue;
        if(arryPoist >3){
            return "exit";
        }
        String[] questions = {"Please Enter Students Name: ","Please Enter Students Academic Year: ","Please Enter Students GPA: "};
        System.out.print(questions[arryPoist-1]);
        retValue = myScan.next();
        return retValue;
    }


    private static int allAnswered(String name, String AcaYear, double GPA){
        if(name.isEmpty() || AcaYear.isEmpty() || GPA < 0){
            return 1;
        }else{
            return 0;
        }
    }

    // method to print main menu for user
    private static void mainMenu(){
        System.out.println("1. Enter Students Name");
        System.out.println("2. Enter Students Academic Year");
        System.out.println("3. Enter Students GPA");
        System.out.println("4. Display Students Info");
        System.out.println("5. Exit");


    }

}


class Student{
    String name;
    String year;
    double gpa;


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public double getGpa(){
        return gpa;
    }


    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    // Print out information from object
    public void printStudent(){
        System.out.println(getName());
        System.out.println(getYear());
        System.out.println(getGpa());
    }

    // Constructor statements
    public Student(String name, String year, double gpa){
        setName(name);
        setYear(year);
        setGpa(gpa);
    }

}
