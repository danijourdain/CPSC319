import java.util.regex.*;

public class Data {
    private String number;
    private String lastName;
    private String department;
    private String program;
    private String year;

    private static final Pattern REGEX = Pattern.compile("^(\\d{7})(\\w+)\\s+(\\d{4})(\\w{1,4})\\s*(\\d{1})$");

    /**
     * Constructor for Data class. 
     * @param studentData A string contaning the student's data. Will be broken down into its individual parts.
     */
    public Data(String studentData) {
        Matcher findData = REGEX.matcher(studentData);

        if(findData.find()) {
            this.number = findData.group(1);
            this.lastName = findData.group(2);
            this.department = findData.group(3);
            this.program = findData.group(4);
            this.year = findData.group(5);
        }
        else {
            System.out.println("Invalid student data line. Program terminating");
            System.exit(1);
        }
    }
    
    /**
     * This method prints all the elements of a Data instance
     */
    public void printData() {
        System.out.println (this.lastName + ", " + this.number + ", " + 
            this.department + ", " + this.program + ", " + this.year);
    }

    /**
     * This method returns all the elements of a Data instance.
     * @return The elements of a Data instance formatted as a string separated by commas.
     */
    public String getData() {
        return (this.lastName + ", " + this.number + ", " + 
            this.department + ", " + this.program + ", " + this.year);
    }

    /**
     * Getter for student number.
     * @return Student number.
     */
    public String getStudentNumber() {
        return this.number;
    }

    /**
     * Getter for last name.
     * @return Student's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method is a static method that can be used to find the student's last name from any valid data String.
     * @param data the String containing all student data.
     * @return The student's last name.
     */
    public static String getLastName(String data) {
        Matcher findName = REGEX.matcher(data);
        findName.find();

        return findName.group(2);
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
