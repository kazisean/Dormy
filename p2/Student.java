package p2;

public class Student {

    private String netId;
    private String firstName;
    private String lastName;
    private String schoolName;
    private String dormName;
    private String roomIndex;
    private double GPA;


    public Student(String netId, String firstName, String lastName, String schoolName, double GPA){
        this.netId = netId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.GPA = GPA; 
        this.dormName ="null";
        this.roomIndex = "null";
    }


    public Student(String netId, String firstName, String lastName, String schoolName, String dormName, String roomIndex, double GPA){
        this.netId = netId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.dormName = dormName;
        this.roomIndex = roomIndex;
        this.GPA = GPA; 
    }

    /*
    * ALLOW USER TO CHANGE STUDENT BASIC INFO 
    */

    public void setNetId(String netId){
        this.netId = netId;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setSchool(String school){
        this.schoolName = school;
    }

    public void setDormName(String dormName){
        this.dormName = dormName;
    }

    public void setRoomIndex(String roomIndex){
        this.roomIndex = roomIndex;
    }

    public void setGPA (double GPA){
        this.GPA = GPA;
    }

    /*
    * ALLOW USER TO GET STUDENT BASIC INFO 
    */

    public String getNetId(){
        return this.netId;
    }

    public String getName(){
        return this.firstName + " "+ this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getSchoolName(){
        return this.schoolName;
    }

    public String getDormName(){
        return this.dormName;
    }

    public String getRoomIndex(){
        return this.roomIndex;
    }

    public double getGPA(){
        return this.GPA;
    }
}
