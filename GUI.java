import p2.*;
import java.util.*;
import javax.swing.*;

public class GUI {

    public String getMessageTxt(ArrayList<String> msgLines){
        String messageTxt = "";
        for(int i = 0; i < msgLines.size(); i++){
            messageTxt += msgLines.get(i) + "\n";
        }
        return messageTxt;

    }

    public String getStringInput(String message){
        String option = JOptionPane.showInputDialog(null, message, "Dormy", JOptionPane.PLAIN_MESSAGE);
        return option;

    }
    public String getStringInput(ArrayList<String> msgLines){
        String messageTxt = this.getMessageTxt(msgLines);
        String option = JOptionPane.showInputDialog(null, messageTxt, "Dormy", JOptionPane.PLAIN_MESSAGE);
        
        return option;
    }

    public void showMessage(ArrayList<String> msgLines){
        String messageTxt = this.getMessageTxt(msgLines);
        JOptionPane.showMessageDialog(null, messageTxt, "Dormy", JOptionPane.PLAIN_MESSAGE);
    }

    public void showConfirmDialog(String message){
        JOptionPane.showConfirmDialog(null, message, "Dormy", JOptionPane.CANCEL_OPTION);
    }

    public int openMenuScreen(){
        //JOption Pane version 
        ArrayList<String> msgLines = new ArrayList<String>();
        msgLines.add("What would you like to search for:");
        msgLines.add("0. To quit");
        msgLines.add("1. Get Room Information");
        msgLines.add("2. Get Student Information");
        msgLines.add("3. Get Dorm Information");
        msgLines.add("4. Add Student");
        msgLines.add("5. Delete Student");
        msgLines.add("6. Add Student to Room");
        msgLines.add("7. Remove Student from Room");


        String option = this.getStringInput(msgLines);
        int optionNum = Integer.parseInt(option);

        return optionNum;

    }

    public String askUserForDorm(){
        ArrayList<String> msgLines = new ArrayList<String>();

        msgLines.add("What dorm would you like to search through");
        msgLines.add(".Rubin Hall (rubin)");
        msgLines.add(".Third North (third)");
        msgLines.add("");
        msgLines.add("Enter dorm: ");


        String option = this.getStringInput(msgLines);
        return option;
    }

    public void printRoomDetails(Dorm dorm, String selectedRoomIndex){
        Room selectedRoom = dorm.getRoom(selectedRoomIndex);
        String[] studentsNetIds = selectedRoom.getStudentsLivingIn();
        Floor floorOfRoom = dorm.getFloor(selectedRoom.getFloorNum());

        String studentsLivingInOutput = "";
        for(int i = 0; i < studentsNetIds.length; i++){
            studentsLivingInOutput += studentsNetIds[i] + " ";
        }

        ArrayList<String> msgLines = new ArrayList<String>();

        msgLines.add("Room " + selectedRoom.getRoomIndex() + " Information");
        msgLines.add("______________________________");
        msgLines.add("Dorm Name: " + dorm.getDormName());
        msgLines.add("Has UpperClassmen: " + dorm.getHasUpperClassmen());
        msgLines.add("Room Type: " + selectedRoom.getRoomType());
        msgLines.add("Kitchen Available: " + dorm.getHasKitchen());
        msgLines.add("Room Floor: "+ selectedRoom.getFloorNum());
        msgLines.add("Room Cost: " + selectedRoom.getCost());
        msgLines.add("Room Capacity: " + selectedRoom.getCapacity());
        msgLines.add("Students: " + studentsLivingInOutput);
        msgLines.add("RA NetId: " + floorOfRoom.getNetIdOfRA());
        msgLines.add("RA RoomIndex: " + floorOfRoom.getRoomIndexOfRA());

        this.showMessage(msgLines);
    } 
 
    public void printDormDetails(Dorm selectedDorm){
        ArrayList<String> msgLines = new ArrayList<String>();
        msgLines.add("Dorm Name: " + selectedDorm.getDormName());
        msgLines.add("Has UpperClassmen: " + selectedDorm.getHasUpperClassmen());
        msgLines.add("Kitchen Available: " + selectedDorm.getHasKitchen());
        msgLines.add("Current Occupancy: " + selectedDorm.getOccupancy());

        this.showMessage(msgLines);
    }

    public void printStudentDetails(University NYU, String netId){
        Student foundStudent = NYU.getStudent(netId);
        ArrayList<String> msgLines = new ArrayList<String>();

        msgLines.add("Student Information for " + foundStudent.getNetId());
        msgLines.add("______________________________");
        msgLines.add("Name: " + foundStudent.getName());
        msgLines.add("School: " + foundStudent.getSchoolName());
        msgLines.add("GPA:" + foundStudent.getGPA());
        msgLines.add("Dorm: " + foundStudent.getDormName());
        msgLines.add("RoomIndex: " + foundStudent.getRoomIndex());

        this.showMessage(msgLines);

    }
    


    
    public String getRoomIndex(Dorm selectedDorm){
        ArrayList<String> msgLines = new ArrayList<String>();
        msgLines.add("Rooms in dorm: ");
       
        Room[] dormRooms = selectedDorm.getRooms();
        String roomsLine = "";
        //Show the rooms the user can search through 
        for(int i = 0; i < dormRooms.length; i++){
            roomsLine += dormRooms[i].getRoomIndex() + " ";
        }

        msgLines.add(roomsLine);
        msgLines.add(" ");

        
        String input = getStringInput(msgLines);
        return input;
    }

    public String getAvailableRoomIndex(Dorm selectedDorm){
        //Show to user the available indexes they can add to 
        ArrayList<String> availableRooms =  selectedDorm.getAvailableRooms();
        ArrayList<String> msgLines = new ArrayList<String>();

        msgLines.add("The rooms available to add are: ");
        String roomsLine = "";
        for(int i= 0; i < availableRooms.size(); i++){
            roomsLine += availableRooms.get(i) + " ";
        }
        msgLines.add(roomsLine);
        msgLines.add("");

        msgLines.add("Enter room index: ");
        String input = this.getStringInput(msgLines);
        return input;


    }

    public void openErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Dormy", JOptionPane.ERROR_MESSAGE);
    }
}
