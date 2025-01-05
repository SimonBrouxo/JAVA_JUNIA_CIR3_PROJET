import java.util.Arrays;
import java.util.Scanner;

public class Vehicle extends Unit {

    // Attributes
    public TypeVehicle typeVehicle;
    int transportCapacity;

    // Constructors
    public Vehicle() {}

    // Setters and getters
    public TypeVehicle getTypeVehicle() {
        return typeVehicle;
    }
    public void setTypeVehicle(TypeVehicle typeVehicle) {
        this.typeVehicle = typeVehicle;
    }
    public int getTransportCapacity() {
        return transportCapacity;
    }
    public void setTransportCapacity(int transportCapacity) {
        if(this.typeVehicle == TypeVehicle.Attack) {
            this.transportCapacity = 0;
        }
        else {
            this.transportCapacity = transportCapacity;
        }
    }

    static Vehicle createNewVehicle(Scanner scanner){
        Vehicle newVehicle = new Vehicle();
        System.out.println(" - Choose the name of the Vehicle: ");
        newVehicle.setNameUnit(scanner.nextLine());
        System.out.println(" - Choose the point of the Vehicle: ");
        newVehicle.setPoints(scanner.nextInt());
        System.out.println(" - Choose the type of the Vehicle between: \n" + Arrays.toString(TypeVehicle.values()));
        try {
            newVehicle.setTypeVehicle(TypeVehicle.valueOf(scanner.next()));
        } catch (IllegalArgumentException e) {
            System.out.println("Warning!\nInvalid type entered. Please choose from: " + Arrays.toString(TypeVehicle.values()));
        }
        if(newVehicle.typeVehicle==TypeVehicle.Transport){
            System.out.println(" - Choose the point of capacity: ");
            newVehicle.setTransportCapacity(scanner.nextInt());
        }
        scanner.nextLine();
        newVehicle.printVehicle(newVehicle);
        System.out.println("\nPress ENTER to continue");

        return newVehicle;
    }

    @Override
    public void printVehicle(Vehicle vehicle) {
        System.out.println(" - "+ vehicle.getNameUnit() +
                " " + vehicle.getPoints() + "pts " +
                "[" + vehicle.getTypeVehicle() + "] " +
                "(Transport Capacity : " + vehicle.getTransportCapacity() + ")"
        );
    }
}
