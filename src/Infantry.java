import java.util.Arrays;
import java.util.Scanner;

public class Infantry extends Unit {

    // Attributes
    public TypeInfantry typeInfantry;

    // Constructors
    public Infantry() {}
    public Infantry(String name, int points, TypeInfantry type) {
        super(name, points);
        this.typeInfantry = type;
    }

    // Setters and getters
    public TypeInfantry getTypeInfantry() {
        return typeInfantry;
    }
    public void setTypeInfantry(TypeInfantry type) {
        this.typeInfantry = type;
    }

    static Infantry createNewInfantry(Scanner scanner) {
        Infantry newInfantry = new Infantry();
        System.out.println(" - Choose the name of the Infantry: ");
        newInfantry.setNameUnit(scanner.nextLine());
        System.out.println(" - Choose the points of the Infantry: ");
        newInfantry.setPoints(scanner.nextInt());
        scanner.nextLine();
        System.out.println(" - Choose the type of the Infantry between: \n" + Arrays.toString(TypeInfantry.values()));
        try {
            newInfantry.setTypeInfantry(TypeInfantry.valueOf(scanner.next()));
        } catch (IllegalArgumentException e) {
            System.out.println("Warning!\nInvalid type entered. Please choose from: " + Arrays.toString(TypeInfantry.values()));
        }
        newInfantry.printInfantry(newInfantry);

        return newInfantry;
    }

    @Override
    public void printInfantry(Infantry infantry) {
        System.out.println(" - " + infantry.getNameUnit() +
                " " + infantry.getPoints() + "pts " +
                "[" + infantry.getTypeInfantry() + "]"
        );
    }
}