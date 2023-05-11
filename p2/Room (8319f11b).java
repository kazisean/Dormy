package p2;

public class Room {
    private String roomIndex;
    private String roomType;
    private int floorNum;
    private String[] studentsLivingIn;
    private int capacity;
    private int cost; 

    public Room(int floorNum, String roomIndex){
        this.floorNum = floorNum;
        this.roomIndex = roomIndex;
    }

    public Room(int floorNum, String roomIndex, String[] studentsLivingIn){
        this.floorNum = floorNum;
        this.roomIndex = roomIndex;
        this.studentsLivingIn = studentsLivingIn;
    }

    /*
     * ALLOW USER TO CHANGE ROOM DETAILS 
     */

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    public void setCapacity(int num){
        this.capacity = num;
    }

    public void setCost(int num){
        this.cost = num;
    }

     /*
     * ALLOW USER TO CHANGE ROOM BASIC INFO 
     */

    public void setFloorNum(int num){
        this.floorNum = num;
    }

    public void roomIndex(String roomIndex){
        this.roomIndex = roomIndex;
    }

    public void setStudentsLivingIn(String[] studentsLivingIn){
        this.studentsLivingIn = studentsLivingIn;
    }

     /*
     * ALLOW USER TO CHANGE ROOM INFO 
     */

    public int getFloorNum(){
        return this.floorNum;
    }

    public String getRoomIndex(){
        return this.roomIndex;
    }

    public String[] getStudentsLivingIn(){
        return this.studentsLivingIn;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public int getCost(){
        return this.cost;
    }

    public int getCapacity(){
        return this.capacity;
    }

   
}

