package p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MethodHelpers {
    /* DORM HELPERS  */

    private ArrayList<Floor> createFloorClasses(String filePath) throws FileNotFoundException{
        //CREATE THE FLOORS 
        File floorsFile = new File(filePath);
        Scanner floorsText = new Scanner(floorsFile);
        ArrayList<Floor> DormFloorsList = new ArrayList<Floor>();

    
           //Create new floor based the RA floor (each floor needs to have an RA)
        while(floorsText.hasNextLine()){
           String line =  floorsText.nextLine();
           String[] floorFields = line.split("-");
           //[floorNum]-[netIdOfRA]-[roomIndexOfRA]

           int floorNum = Integer.parseInt(floorFields[0]);
           String netIdOfRA = floorFields[1];
           String roomIndexOfRA = floorFields[2];

           Floor newFloor = new Floor(floorNum, netIdOfRA, roomIndexOfRA);
           DormFloorsList.add(newFloor);
       }

       floorsText.close();

       return DormFloorsList;
   }

    private ArrayList<Room> createRoomClasses(String filePath) throws FileNotFoundException{
        //CREATE THE ROOMS 
        File testFile = new File(filePath);
        Scanner dormFile = new Scanner(testFile);
        
        ArrayList<Room> DormRoomsList = new ArrayList<Room>();



        while(dormFile.hasNextLine()){
            String line = dormFile.nextLine();
            String[] roomFields = line.split("-");
            

            //[FloorNumber]-[RoomIndex]-[roomType]-[cost]-[capacity]-[NumOfPeople]-[netId1]-[netId2]-[netId3]
            int roomFloorNum = Integer.parseInt(roomFields[0]);
            String roomIndex = roomFields[1];
            String roomType = roomFields[2];
            int roomCost = Integer.parseInt(roomFields[3]);
            int roomCapacity = Integer.parseInt(roomFields[4]);
            int numOfPeopleLiving = Integer.parseInt(roomFields[5]);
            String[] studentsLivingIn = new String[numOfPeopleLiving];

            for(int j = 0; j < studentsLivingIn.length; j++){
                //Get the unique netIds of the students at the end of the chain index 5 
                studentsLivingIn[j] = roomFields[j + 6];
            }


            //CREATE THE NEW ROOM  
            Room newRoom = new Room(roomFloorNum, roomIndex);
            newRoom.setRoomType(roomType);
            newRoom.setCost(roomCost);
            newRoom.setCapacity(roomCapacity);
            newRoom.setStudentsLivingIn(studentsLivingIn);

            //Only add the new room if it has been added yet
            if(!DormRoomsList.contains(newRoom)){
                DormRoomsList.add(newRoom);

            } 
        }

        dormFile.close();
        return DormRoomsList;
    }

    /* CREATE INDIVIDUAL DORMS  */
    public Dorm createApartmentDorm( String dormName, String floorsFilePath, String roomsFilePath) throws FileNotFoundException{
        ArrayList<Floor> DormFloorsList = this.createFloorClasses(floorsFilePath);
        ArrayList<Room> DormRoomsList = this.createRoomClasses(roomsFilePath);

        Room[] DormRooms = new Room[DormRoomsList.size()];
        Floor[] DormFloors = new Floor[DormFloorsList.size()];

        for(int i = 0; i < DormFloorsList.size(); i++){
            Floor savedFloor = DormFloorsList.get(i);
            DormFloors[i] = savedFloor;
        }

        for(int i = 0; i < DormRoomsList.size(); i++){
            Room savedRoom = DormRoomsList.get(i);
            DormRooms[i] = savedRoom;
        }

        Dorm newDorm = new ApartmentDorm(dormName, DormFloors, DormRooms);
        newDorm.setFilePath(roomsFilePath);
        return newDorm;

    }

    public Dorm createTraditonalDormClass(String dormName, String floorsFilePath, String roomsFilePath) throws FileNotFoundException{
        ArrayList<Floor> DormFloorsList = this.createFloorClasses(floorsFilePath);
        ArrayList<Room> DormRoomsList = this.createRoomClasses(roomsFilePath);

        Room[] DormRooms = new Room[DormRoomsList.size()];
        Floor[] DormFloors = new Floor[DormFloorsList.size()];

        for(int i = 0; i < DormFloorsList.size(); i++){
            Floor savedFloor = DormFloorsList.get(i);
            DormFloors[i] = savedFloor;
        }

        for(int i = 0; i < DormRoomsList.size(); i++){
            Room savedRoom = DormRoomsList.get(i);
            DormRooms[i] = savedRoom;
        }

        Dorm newDorm = new TraditionalDorm(dormName, DormFloors, DormRooms); 
        newDorm.setFilePath(roomsFilePath);
        return newDorm;
    }

     /* CREATE THE UNIVERSITY WITH ALL THE STUDENTS  */
    public University createNYU(String filePath) throws FileNotFoundException{
        File studentFile = new File(filePath);
        Scanner studentText = new Scanner(studentFile);
        ArrayList<Student> StudentsList = new ArrayList<Student>();

        while(studentText.hasNextLine()){
            String line = studentText.nextLine();
            String[] studentFields = line.split("-");

            //[netId]-[firstName]-[lastName]-[school]-[gpa]-[dormName]-[roomIndex]-[numOfMajors]-[major1]-[major2]
            String netId = studentFields[0];
            String firstName = studentFields[1];
            String lastName = studentFields[2];
            String schoolName = studentFields[3];
            double GPA = Double.parseDouble(studentFields[4]);
            String dormName = studentFields[5];
            String roomIndex= studentFields[6];

            if(!dormName.equals("null")){
                Student newStudent = new Student(netId, firstName, lastName, schoolName,  dormName, roomIndex, GPA);
                StudentsList.add(newStudent);
            }else{
                Student newStudent = new Student(netId, firstName, lastName, schoolName, GPA);
                StudentsList.add(newStudent);
            }

            
        }

        studentText.close();

        Student[] students = new Student[StudentsList.size()];
        for(int i = 0; i < students.length; i++){
            students[i] = StudentsList.get(i);
        }

        University NYU = new University(students);
        return NYU;

    }

    public void updateStudentFile(Student[] updatedStudents) throws FileNotFoundException{
        String[] studentLines = new String[updatedStudents.length];

        for(int i = 0; i < updatedStudents.length; i++){
            Student currentStudent = updatedStudents[i];

            String currentStudentLine = "";

            //[netId]-[firstName]-[lastName]-[school]-[gpa]-[dormName]-[roomIndex]-[numOfMajors]-[major1]-[major2]
            String currentStudentId = currentStudent.getNetId();
            String currentStudentFirstName = currentStudent.getFirstName();
            String currentStudentLastName = currentStudent.getLastName();
            String currentStudentSchool = currentStudent.getSchoolName();
            double currentStudentGPA = currentStudent.getGPA();
            String currentStudentDormName = currentStudent.getDormName();
            String currentStudentRoomIndex = currentStudent.getRoomIndex();

            currentStudentLine += currentStudentId + "-" + currentStudentFirstName + "-" + currentStudentLastName + "-";
            currentStudentLine += currentStudentSchool + "-" + currentStudentGPA + "-";
            currentStudentLine += currentStudentDormName + "-" + currentStudentRoomIndex;

            //Save each line to be written in the file 
            studentLines[i] = currentStudentLine;
        }

        File studentFile = new File("Students.txt");
        PrintWriter myWriter = new PrintWriter(studentFile);
        for(int i = 0; i < studentLines.length; i++){
            myWriter.println(studentLines[i]);
        }

        myWriter.close();

    }

    public void updateRoomsFile(Dorm studentDorm, Room[] updatedRooms) throws FileNotFoundException{
        String[] updatedRoomsLines = new String[updatedRooms.length];

        for(int i = 0; i < updatedRooms.length; i++){
            Room currentRoom = updatedRooms[i];
            String currentRoomLine = "";
            //[FloorNumber]-[RoomIndex]-[roomType]-[cost]-[capacity]-[NumOfPeople]-[netId1]-[netId2]-[netId3]

            currentRoomLine += currentRoom.getFloorNum() + "-" + currentRoom.getRoomIndex() + "-" + currentRoom.getRoomType();
            currentRoomLine +="-" + currentRoom.getCost() + "-" + currentRoom.getCapacity() + "-" + currentRoom.getStudentsLivingIn().length; 

            if(currentRoom.getStudentsLivingIn().length > 0){
                for(int j=0; j < currentRoom.getStudentsLivingIn().length; j++){
                    currentRoomLine += "-" + currentRoom.getStudentsLivingIn()[j];
                }
            }
        
            updatedRoomsLines[i] = currentRoomLine;
        }

        //Write into the Rooms file 
        File roomsFile = new File(studentDorm.getFilePath());
        PrintWriter myWriter = new PrintWriter(roomsFile);
        for(int i = 0; i < updatedRoomsLines.length; i++){
            myWriter.println(updatedRoomsLines[i]);
        }

        myWriter.close();
       
    }   
    

}

