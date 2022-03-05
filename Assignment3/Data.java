import java.util.regex.*;

public class Data {
    private String studentNumber;
    private String lastName;
    private String department;
    private String program;
    private String year;

    private static final Pattern REGEX = Pattern.compile("^(\\d{7})(\\w+)\\s+(\\d{4})(\\w{1,4})\\s*(\\d{1})$");
    //this regex is used to separate the components of the student's info

    /**
     * Constructor for Data.
     * @param data A String containing all components of the data.
     */
    public Data(String data) {
        Matcher match = REGEX.matcher(data);
        match.find();
        //use the regex to find the separate components
    
        this.studentNumber = match.group(1);
        this.lastName = match.group(2);
        this.department = match.group(3);
        this.program = match.group(4);
        this.year = match.group(5);
    }

    /**
     * This method takes input data and finds the last name and returns it.
     * @param data The input data.
     * @return The student's last name.
     */
    public static String findLastName(String data) {
        Matcher match = REGEX.matcher(data);
        match.find();
        //use the regex to find the separate components

        return match.group(2);
        //return the last name.
    }

    /**
     * This method prints all the elements of a Data instance
     */
    public void printData() {
        System.out.println(this.lastName + ", " + this.studentNumber + ", " + 
            this.department + ", " + this.program + ", " + this.year);
    }

    /**
     * Getter for student number.
     * @return Student number.
     */
    public String getStudentNumber() {
        return this.studentNumber;
    }

    /**
     * Getter for last name.
     * @return Student's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter for department.
     * @return Student's department.
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Getter for program.
     * @return Student's program.
     */
    public String getProgram() {
        return this.program;
    }

    /**
     * Getter for year of program.
     * @return Student's year.
     */
    public String getYear() {
        return this.year;
    }
}
