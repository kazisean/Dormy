package p2;
import java.util.*;

public class University {
    private Student[] students;
    private ArrayList<Dorm> dorms;

    public University(Student[] students){
        this.students = students;
        this.dorms = new ArrayList<Dorm>();
    }


    public void setStudents(Student[] students){
        this.students = students;
    }


    public void addDorm(Dorm dorm){
        this.dorms.add(dorm);
    }

    //Add a new student to the university 
    public void addStudent(Student student){
        int newLength = this.students.length + 1;
        Student[] newStudents = new Student[newLength];
        Student[] prevStudents = this.students;

        //Loop through each value in the rooms to properly copy the value 
        for(int i = 0; i < prevStudents.length; i++){
            Student currentStudent = prevStudents[i];
            newStudents[i] = currentStudent;
          
        }

        newStudents[newLength - 1] = student;

        //update the field 
        this.students = newStudents;
    }


    //Remove a student from the university 
    public void removeStudent(String netId){
        int newLength = this.students.length - 1;
        Student[] newStudents = new Student[newLength];
        Student[] prevStudents = this.students;
        int counter = 0;

        //Loop through each value in the rooms to properly copy the value 
        for(int i = 0; i < prevStudents.length; i++){
            Student currentStudent = prevStudents[i];
            String currentStudentNetId = currentStudent.getNetId();

            //Only add room to the list if it doesn't match the index received
            if(!(currentStudentNetId.equals(netId))){
                newStudents[counter] = currentStudent;
                counter++;
            }
          
        }

       

        //update the field 
        this.students = newStudents;
    }

    /*
    * ALLOW USER TO GET University BASIC INFO 
    */
    public Student [] getStudents(){
        return this.students;
    }
    

    public Student getStudent(String netId){
        Student[] students = this.students;
        Student foundStudent = null;

        for(int i= 0; i < students.length; i++){
            String currentStudentNetId = students[i].getNetId();
            if(currentStudentNetId.equals(netId)){
                foundStudent = students[i];
            }
        }

        return foundStudent;
        
    }


    
}
