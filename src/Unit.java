import java.util.Arrays;
import java.util.Scanner;

public abstract class Unit {

    // Attributes
    private String nameUnit;
    private int points;

    // Constructors
    public Unit() {}
    public Unit(String name, int points) {
        this.nameUnit = name;
        this.points = points;
    }

    // Setters and getters
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getNameUnit() {
        return nameUnit;
    }
    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public void printInfantry(Infantry infantry) {}
    public void printVehicle(Vehicle vehicle) {}

    static String modifyUnitName(Unit unit, Scanner scanner){
        System.out.println(" - Choose the new name of the Unit : ");
        unit.setNameUnit(scanner.nextLine());
        if(unit instanceof Infantry infantry){
            infantry.printInfantry(infantry);
            System.out.println("\nPress ENTER to continue");
        }
        else if(unit instanceof Vehicle vehicle){
            vehicle.printVehicle(vehicle);
            System.out.println("\nPress ENTER to continue");
        }

        return unit.getNameUnit();
    }

    static int modifyPoints(Unit unit, Scanner scanner){
        System.out.println(" - Choose the new points of the Unit : ");
        unit.setPoints(scanner.nextInt());
        if(unit instanceof Infantry infantry){
            infantry.printInfantry(infantry);
            System.out.println("\nPress ENTER to continue");
        }
        else if(unit instanceof Vehicle vehicle){
            vehicle.printVehicle(vehicle);
            System.out.println("\nPress ENTER to continue");
        }

        return unit.getPoints();
    }

    static TypeInfantry modifyTypeInfantry(Infantry unitInf, Scanner scanner){
        System.out.println(" - Choose the type of the Infantry between: \n: " + Arrays.toString(TypeInfantry.values()));
        try {
            unitInf.setTypeInfantry(TypeInfantry.valueOf(scanner.next()));
        } catch (IllegalArgumentException e) {
            System.out.println("Warning!\nInvalid type entered. Please choose from: " + Arrays.toString(TypeInfantry.values()));
        }
        scanner.nextLine();
        unitInf.printInfantry(unitInf);
        System.out.println("\nPress ENTER to continue");

        return unitInf.getTypeInfantry();
    }

    static TypeVehicle modifyVehicle(Vehicle unitVeh, Scanner scanner){
        System.out.println(" - Choose the type of the Vehicle between: \n: " + Arrays.toString(TypeVehicle.values()));
        try {
            unitVeh.setTypeVehicle(TypeVehicle.valueOf(scanner.next()));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Warning!\nInvalid type entered. Please choose from: " + Arrays.toString(TypeVehicle.values()));
        }
        scanner.nextLine();
        unitVeh.printVehicle(unitVeh);
        System.out.println("\nPress ENTER to continue");

        return unitVeh.getTypeVehicle();
    }

    static int modifyTransportCapacity(Vehicle unitVeh, Scanner scanner){
        System.out.println(" - Choose the new capacity of the Vehicle");
        unitVeh.setTransportCapacity(scanner.nextInt());
        unitVeh.printVehicle(unitVeh);
        System.out.println("\nPress ENTER to continue");

        return unitVeh.getTransportCapacity();
    }
}
