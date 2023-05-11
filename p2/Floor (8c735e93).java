package p2;

public class Floor {
    private int floorNum;
    private String  netIdOfRA; 
    private String roomIndexOfRA; 

    public Floor(int num){
        this.floorNum = num;
    }

    public Floor(int num, String netIdOfRA, String roomIndexOfRA){
        this.floorNum = num;
        this.netIdOfRA = netIdOfRA;
        this.roomIndexOfRA = roomIndexOfRA;
    }


    /*
    * ALLOW USER TO CHANGE Floor DETAILS 
    */

    public void setRAInfo(String netId, String roomIndex){
        this.netIdOfRA = netId;
        this.roomIndexOfRA = roomIndex;
    }

    public void setFloorNum(int floorNum){
        this.floorNum = floorNum;
    }



    /*
    * ALLOW USER TO GET Floor DETAILS 
    */

    public int getFloorNum(){
        return this.floorNum;
    }

    public String getNetIdOfRA(){
        return this.netIdOfRA;
    }

    public String getRoomIndexOfRA(){
        return this.roomIndexOfRA;
    }


}
