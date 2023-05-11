package p2;
import java.util.*;

public abstract class Dorm {
    private Floor[] floors;
    private int capacity;
    private String name;
    private Room[] rooms; //all rooms will saved in the dorm rooms 
    private String filePath;
 

    public Dorm(String name, Floor[] floors, int capacity){
        this.name = name;
        this.floors = floors;
        this.capacity = capacity;
    }

    public Dorm(String name, Floor[] floors, Room[] rooms){
        this.name = name;
        this.floors = floors;
        this.rooms = rooms;
    }

    /*
    * ALLOW USER TO CHANGE DORM BASIC INFO 
    */

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    
    public void setFloors(Floor[] floors){
        this.floors = floors;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity; 
    }

    public void setRooms(Room[] rooms){
        this.rooms = rooms;
    }

    public void addStudentToDorm(String roomIndex, String netId){
        Room studentNewRoom = this.getRoom(roomIndex);
        String[] currentStudentsLivingIn = studentNewRoom.getStudentsLivingIn(); 
        String[] newStudentsLivingIn = new String[currentStudentsLivingIn.length + 1];

        for(int i = 0; i < currentStudentsLivingIn.length;i++ ){
            newStudentsLivingIn[i] = currentStudentsLivingIn[i];
        }

        newStudentsLivingIn[newStudentsLivingIn.length - 1] = netId;
        studentNewRoom.setStudentsLivingIn(newStudentsLivingIn);
    }


    public void removeStudentFromDorm(String roomIndex, String netId){
        Room studentRoom = this.getRoom(roomIndex);
        String[] currentStudentsLivingIn = studentRoom.getStudentsLivingIn();
        String[] newStudentsLivingIn = new String[currentStudentsLivingIn.length - 1];
        int counter = 0;
        for(int i = 0; i < currentStudentsLivingIn.length; i++){
            //check to see if netId is not the same so we can remove it
            if(!currentStudentsLivingIn[i].equals(netId)){
                newStudentsLivingIn[counter] = currentStudentsLivingIn[i];
                counter++;
            }
        }

        studentRoom.setStudentsLivingIn(newStudentsLivingIn);

    }
    /*
    * ALLOW USER TO UPDATE DORM BASIC INFO 
    */

    public int getNumOfFloors(){
        return this.floors.length;
    }

    public String getDormName(){
        return this.name; 
    }

    public int getCapacity(){
        return this.capacity;
    }

    

    public Room[] getRooms(){
        return this.rooms;
    }

    public Room getRoom(String roomIndex){
        Room[] dormRooms = this.rooms;
        Room foundRoom = null; 
        for(int i = 0; i < dormRooms.length; i++){
            String currentRoomIndex = dormRooms[i].getRoomIndex();
            if(currentRoomIndex.equals(roomIndex)){
               foundRoom =  this.rooms[i];
            }
        }

        return foundRoom; 
    }

    public Floor getFloor(int num){
        Floor floor = null;
        for(int i = 0; i < this.floors.length; i++){
            if(this.floors[i].getFloorNum() == num){
                floor = this.floors[i];
            }
        }

        return floor;
    }

    public int getOccupancy(){
        int counter = 0;
        for(int i = 0; i < this.rooms.length; i++){
            String[] studentsLiving = this.rooms[i].getStudentsLivingIn(); 
            counter += studentsLiving.length;
        }

        return counter;
    }

    public ArrayList<String> getAvailableRooms(){
        ArrayList<String> availableRooms = new ArrayList<String>();

        for(int i = 0; i < this.rooms.length; i++){
            Room room = this.rooms[i];
            if(room.getCapacity() > room.getStudentsLivingIn().length){
                availableRooms.add(room.getRoomIndex());
            }
        }

        return availableRooms;
    }

    public String getFilePath(){
        return this.filePath;
    }


    /*
    * Mandatory functions that have to be overrided 
    */


    public abstract boolean getHasKitchen();
    public abstract boolean getHasUpperClassmen();
}
