package p2;

//No need for instance fields (because they will never change )
public class TraditionalDorm extends Dorm{

    public TraditionalDorm(String name, Floor[] floors, Room[] rooms){
        super(name, floors, rooms);
    }
    
    public boolean getHasKitchen(){
        return false;
    }

    public boolean getHasUpperClassmen(){
        return false;
    }
}
