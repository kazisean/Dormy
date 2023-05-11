package p2;

//No need for instance fields (because they will never change )
public class ApartmentDorm extends Dorm{
    public ApartmentDorm(String name, Floor[] floors, Room[] rooms){
        super(name, floors, rooms);
    }
    
    public boolean getHasKitchen(){
        return true;
    }

    public boolean getHasUpperClassmen(){
        return true;
    }
}
