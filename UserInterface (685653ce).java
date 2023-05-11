import p2.*;
import java.io.FileNotFoundException;
import java.util.*;

public class UserInterface{
    private String studentsFileTxt = "Students.txt";
    private String rubinFloorsFileTxt = "RubinFloors.txt";
    private String rubinRoomsFileTxt = "RubinRooms.txt";
    private String thirdNorthFloorsFileTxt = "ThirdNorthFloors.txt";
    private String thirdNorthRoomsFileText = "ThirdNorthRooms.txt";

  
    
    public static void main(String [] args) throws FileNotFoundException{
        GUI GUI = new GUI();
        UserInterface thisClass = new UserInterface();
        
        MethodHelpers methodHelpers = new MethodHelpers();
        Dorm Rubin = methodHelpers.createTraditonalDormClass("Rubin Hall", thisClass.rubinFloorsFileTxt,thisClass.rubinRoomsFileTxt);
        Dorm ThirdNorth = methodHelpers.createApartmentDorm("Third North",thisClass.thirdNorthFloorsFileTxt, thisClass.thirdNorthRoomsFileText);
        University NYU = methodHelpers.createNYU(thisClass.studentsFileTxt);


        NYU.addDorm(ThirdNorth);
        NYU.addDorm(Rubin);


        /*
         * TEST IF WE COULD FIND DORM INFO 
         */

        int selectedOption = GUI.openMenuScreen();
       


        while (selectedOption != 0){

            //READ DORM OR ROOM INFORMATION 
            if(selectedOption == 1 || selectedOption == 3){
                String selectedDormString = GUI.askUserForDorm();
                Dorm selectedDorm = null;


                if(selectedDormString.equals("rubin")){
                    selectedDorm = Rubin;
                }
                else if(selectedDormString.equals("third")){
                    selectedDorm = ThirdNorth;
                }

                if(selectedDorm == null){
                    GUI.openErrorMessage(selectedDormString + "is not a valid dorm");
                }

                //If dorm is valid
                else{
                    if(selectedOption == 1){
                        String selectedRoomIndex = GUI.getRoomIndex(selectedDorm);
                        GUI.printRoomDetails(selectedDorm, selectedRoomIndex);
                    }
                    
                    //If user selected Option Three just show the dorm information 
                    else if(selectedOption == 3){ 
                        GUI.printDormDetails(selectedDorm);
                    }

                }


              


                //Open Menu Again 
                selectedOption = GUI.openMenuScreen();
                
            }
           
            //READ STUDENT INFORMATION 
            else if(selectedOption == 2){
                String netId = GUI.getStringInput( "Enter student netId: ");
                Student foundStudent = NYU.getStudent(netId);

                if(foundStudent != null){
                    GUI.printStudentDetails(NYU,netId);
                }else{
                    GUI.openErrorMessage("The student does not exist");
                }

                

                //Open Menu Again 
                selectedOption = GUI.openMenuScreen();
            }

            //ADD STUDENT TO DATABASE 
            else if(selectedOption == 4){
                String netId = GUI.getStringInput("Enter netId: ");
    
                //If student is already found
                Student foundStudent = NYU.getStudent(netId); 
                if(foundStudent != null){
                    GUI.openErrorMessage("Student already exists");
                }else{

                    //Ask for student's information 
                    String firstName = GUI.getStringInput("Enter first name: ");
                    String lastName = GUI.getStringInput("Enter last name: ");
                    String schoolName = GUI.getStringInput("Enter school name:");
                    double GPA = Double.parseDouble(GUI.getStringInput("Enter student's GPA (double):"));
                

                    Student newStudent = new Student(netId, firstName, lastName, schoolName,GPA);
                    NYU.addStudent(newStudent);

                    
                    Student[] updatedStudents = NYU.getStudents();
                    methodHelpers.updateStudentFile(updatedStudents);
                    GUI.showConfirmDialog("Student " + newStudent.getNetId() + " was added to the database");
                }

                //Open Menu Again 
                selectedOption = GUI.openMenuScreen();

            }

            //DELETE STUDENT FROM DATABASE
            else if(selectedOption == 5){
                String netId = GUI.getStringInput("Enter netId: ");

                //If student is not found
                Student foundStudent = NYU.getStudent(netId); 
                if(foundStudent == null){
                    GUI.openErrorMessage("Student does not exists");
                }else{
                    String foundStudentRoomIndex = foundStudent.getRoomIndex();
                    String foundStudentDormName = foundStudent.getDormName();
                    Dorm studentDorm = null;

                

                    //If the student has a room
                    if(!foundStudentRoomIndex.equals("null")){
                        if(foundStudentDormName.equals("Rubin Hall")){
                            studentDorm = Rubin;
                        }
                        else if(foundStudentDormName.equals("Third North")){
                            studentDorm = ThirdNorth;
                        }

                        //Update the room to it's proper status
                        studentDorm.removeStudentFromDorm(foundStudentRoomIndex, netId);

                        //Update the rooms file 
                        Room[] updatedRooms = studentDorm.getRooms();
                        methodHelpers.updateRoomsFile(studentDorm, updatedRooms);
                    }   

                    //Remove the student 
                    NYU.removeStudent(netId);


                    Student[] updatedStudents = NYU.getStudents();
                    methodHelpers.updateStudentFile(updatedStudents);
                    GUI.showConfirmDialog("Student " + netId + " was removed from the database");
                }

                //Open Menu Again 
                selectedOption = GUI.openMenuScreen();
            } 

            //ADD STUDENT TO ROOM 
            else if(selectedOption == 6){
                String netId = GUI.getStringInput("Enter netId: ");

                //If student is not found
                Student foundStudent = NYU.getStudent(netId); 
                if(foundStudent == null){
                    GUI.openErrorMessage("Student does not exists");
                }else{
                    String foundStudentRoomIndex = foundStudent.getRoomIndex();
                    // String foundStudentDormName = foundStudent.getDormName();
                    // Dorm studentDorm = null;

                    //If the student has a room
                    if(!foundStudentRoomIndex.equals("null")){
                        GUI.openErrorMessage("Student already has room");
                    }
                    else{
                        String selectedDormString = GUI.askUserForDorm();
                        Dorm selectedDorm = null;
            
            
                        if(selectedDormString.equals("rubin")){
                            selectedDorm = Rubin;
                        }
                        else if(selectedDormString.equals("third")){
                            selectedDorm = ThirdNorth;
                        }

                        //Show to user the available indexes they can add to 
                        ArrayList<String> availableRooms =  selectedDorm.getAvailableRooms();
                      

                        if(availableRooms.size() > 0){
                            String selectedRoomIndex = GUI.getAvailableRoomIndex(selectedDorm);
                            
                            //Add student to their selected new dorm
                            selectedDorm.addStudentToDorm(selectedRoomIndex, netId);

                            //Update the student info
                            foundStudent.setDormName(selectedDorm.getDormName());
                            foundStudent.setRoomIndex(selectedRoomIndex);


                            Room[] updatedRooms = selectedDorm.getRooms();
                            Student[] updatedStudents = NYU.getStudents();

                            //Update the files 
                            methodHelpers.updateRoomsFile(selectedDorm, updatedRooms);
                            methodHelpers.updateStudentFile(updatedStudents);
                            GUI.showConfirmDialog("Student " + netId + " was added to Room " + selectedRoomIndex + "," + selectedDorm.getDormName());
                        }else{
                            GUI.openErrorMessage("There are no more rooms left in this dorm");
                        }

                    }
                }

            //Open Menu Again 
            selectedOption = GUI.openMenuScreen();

        }

            //REMOVE STUDENT FROM A ROOM 
            else if(selectedOption == 7){
                String netId = GUI.getStringInput( "Enter netId: ");

                //If student is not found
                Student foundStudent = NYU.getStudent(netId); 
                if(foundStudent == null){
                    GUI.openErrorMessage("Student does not exists");
                }else{
                    String foundStudentRoomIndex = foundStudent.getRoomIndex();
                    String foundStudentDormName = foundStudent.getDormName();
                    Dorm studentDorm =null ;

                    if(foundStudentRoomIndex.equals("null")){
                        GUI.openErrorMessage("Student does not have a room");
                    }else{
                        if(foundStudentDormName.equals("Rubin Hall")){
                            studentDorm = Rubin;
                        }
                        else if(foundStudentDormName.equals("Third North")){
                            studentDorm = ThirdNorth;
                        }

                        studentDorm.removeStudentFromDorm(foundStudentRoomIndex, netId);

                        //Update student info 
                        foundStudent.setDormName("null");
                        foundStudent.setRoomIndex("null");


                        //Update the student and rooms file 
                        Room[] updatedRooms = studentDorm.getRooms();
                        Student[] updatedStudents = NYU.getStudents();
                        methodHelpers.updateRoomsFile(studentDorm, updatedRooms);
                        methodHelpers.updateStudentFile(updatedStudents);

                        GUI.showConfirmDialog("Student " + netId + " was removed to Room " + foundStudentRoomIndex + "," + studentDorm.getDormName());
                        
                    }

                } 

                //Open Menu Again 
                selectedOption = GUI.openMenuScreen();

        }
            else{
                GUI.openErrorMessage("That was not an option");
            }
    }

    System.exit(0);

    }
}