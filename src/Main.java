import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main (String [] args){

        Map<String,Army> mapOfArmies =  new HashMap<>();
        Map<String, UnitGroup> mapOfUnitGroups =  new HashMap<>();
        Map<String, Unit> mapOfUnits =  new HashMap<>();

        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        System.out.println("Press Enter to continue");

        while (sc.nextLine() != "exit") {
            System.out.println(
                    "=========================\n" +
                    "======= Main Menu =======\n" +
                    "=========================\n" +
                    "Choose between :\n" +
                    " - Create a new army (na)\n" +
                    " - Modify an army (ma)\n" +
                    " - Delete an army (da)\n" +
                    " - Show all armies (sa)\n" +
                    " - Create a new unit group (ng)\n" +
                    " - Modify a unit group (mg)\n" +
                    " - Delete a unit group (dg)\n" +
                    " - Show all unit groups (sg)\n" +
                    " - Create a new unit (nu)\n" +
                    " - Modify a unit (mu)\n" +
                    " - Delete a unit (du)\n" +
                    " - Show all unites (su)\n" +
                    " - Exit the software (exit)");
            switch (sc.nextLine()) {
                case "na":
                    System.out.println("======= Create a new army =======");
                    Army newArmy = Army.createNewArmy(sc);
                    if(mapOfArmies.containsKey(newArmy.getNameArmy())){
                        sc.nextLine();
                        System.out.println("Warning !\nThis army already exists, choose another name\nPress ENTER to continue");
                        break;
                    }
                    mapOfArmies.put(newArmy.getNameArmy(), newArmy);
                    break;
                case "ma":
                    if(mapOfArmies.isEmpty()) {
                        System.out.println("Warning !\nYou must first create an army\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Modify an army =======\n" + "Choose the army to modify by its name : ");
                    mapOfArmies.forEach((name,army)->{
                        System.out.println(" - " + army.getNameArmy());
                    });
                    String nameArmyForModify =  sc.nextLine();
                    mapOfArmies.forEach((name,army)->{
                        if(nameArmyForModify.equals(army.getNameArmy())) {
                            army.printArmy(army);
                            System.out.println("Choose between :\n" +
                                    " - Modify the name (n)\n" +
                                    " - Modify the faction (f)\n" +
                                    " - Modify the max number of points (p)\n" +
                                    " - Add a new unit group (a)\n" +
                                    " - Remove a unit group (r)\n" +
                                    " - Go to the main menu (menu)");
                            switch (sc.nextLine()){
                                case "n":
                                    System.out.println("======= Modify the name =======");
                                    army.setNameArmy(Army.modifyArmyName(army,sc));
                                    mapOfArmies.replace(army.getNameArmy(),army);
                                    break;
                                case "f":
                                    System.out.println("======= Modify the faction =======");
                                    army.setFactionArmy(Army.modifyFaction(army,sc));
                                    //mapOfArmies.replace(army.getFactionArmy(),army);
                                    break;
                                case "p":
                                    System.out.println("======= Modify the max number of points =======");
                                    army.setMaxArmyPoints(Army.modifyMaxArmyPoints(army,sc));
                                    //mapOfArmies.replace(army.getMaxArmyPoints(),army);
                                    break;
                                case "a":
                                    System.out.println("======= Add a unit group =======");
                                    Army.addUnitGroups(army,sc,mapOfUnitGroups);
                                    break;
                                case "r":
                                    System.out.println("======= Remove a unit group =======");
                                    Army.removeUnitGroups(army,sc);
                                    break;
                                case "menu":
                                    break;
                            }
                        }
                    });
                    break;
                case "da":
                    if(mapOfArmies.isEmpty()) {
                        System.out.println("Warning !\nThere is no registered army\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Delete an army =======\n" + "Choose the army to delete by its name : ");
                    mapOfArmies.forEach((name,army)->{
                        System.out.println(" - " + army.getNameArmy());
                    });
                    String nameArmyForDelete = sc.next();
                    mapOfArmies.entrySet().removeIf(entry -> entry.getKey().equals(nameArmyForDelete));
                    break;
                case "sa":
                    System.out.println("======= Show all armies =======");
                    if(mapOfArmies.isEmpty()) {
                        System.out.println("There is no registered army\nPress ENTER to continue");
                        break;
                    }
                    mapOfArmies.forEach((name,army)->{
                        army.printArmy(army);
                    });
                    System.out.println("\nPress ENTER to continue");
                    break;
                case "ng":
                    System.out.println("======= Create a new unit group =======");
                    UnitGroup newUnitGroup = UnitGroup.createNewUnitGroup(sc);
                    if(mapOfUnitGroups.containsKey(newUnitGroup.getName())) {
                        sc.nextLine();
                        System.out.println("Warning !\nThis unit group already exists, choose another name\nPress ENTER to continue");
                    }
                    mapOfUnitGroups.put(newUnitGroup.getName(), newUnitGroup);
                    break;
                case "mg":
                    System.out.println("======= Modify a unit group =======");
                    mapOfUnitGroups.forEach((name,unitGroup)->{
                        System.out.println(" - " + unitGroup.getName());
                    });
                    String unitGroupForModify = sc.nextLine();
                    mapOfUnitGroups.forEach((name,unitGroup)->{
                        if(unitGroupForModify.equals(unitGroup.getName())) {
                            unitGroup.printUnitGroup(unitGroup);
                            System.out.println("Choose between:\n" +
                                    " - Modify the name (n)\n" +
                                    " - Add a unit (a)\n" +
                                    " - Remove a unit (r)\n" +
                                    " - Go to the main menu (menu)");
                        }
                        switch (sc.nextLine()){
                            case "n":
                                System.out.println("======= Modify the name =======");
                                unitGroup.setName(UnitGroup.modifyUnitGroupName(unitGroup,sc));
                                mapOfUnitGroups.replace(unitGroup.getName(),unitGroup);
                                break;
                            case "a":
                                System.out.println("======= Add a unit =======");
                                UnitGroup.addUnit(unitGroup,sc,mapOfUnits);
                                break;
                            case "r":
                                System.out.println("======= Remove a unit =======");
                                UnitGroup.removeUnit(unitGroup,sc);
                                break;
                            case "menu":
                                break;
                        }
                    });
                    break;
                case "dg":
                    if(mapOfUnitGroups.isEmpty()) {
                        System.out.println("Warning !\nThere is no registered unit groups\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Delete a unit group =======\n" + "Choose the unit group to delete by its name : ");
                    mapOfUnitGroups.forEach((name,unitGroup)->{
                        System.out.println(" - " + unitGroup.getName());
                    });
                    String nameUnitGroupForDelete = sc.next();
                    mapOfUnitGroups.entrySet().removeIf(entry -> entry.getKey().equals(nameUnitGroupForDelete));
                    break;
                case "sg":
                    System.out.println("======= Show all unit groups =======");
                    if(mapOfUnitGroups.isEmpty()) {
                        System.out.println("There is no registered unit groups\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnitGroups.forEach((name,unitGroup)->{
                        unitGroup.printUnitGroup(unitGroup);
                    });
                    System.out.println("\nPress ENTER to continue");
                    break;
                case "nu":
                    System.out.println("======= Create a new unit =======\nChoose between Infantry or Vehicle (i or v)");
                    switch (sc.nextLine()){
                        case "i":
                            Infantry newInfantry = Infantry.createNewInfantry(sc);
                            mapOfUnits.put(newInfantry.getNameUnit(), newInfantry);
                            break;
                        case "v":
                            Vehicle newVehicle = Vehicle.createNewVehicle(sc);
                            mapOfUnits.put(newVehicle.getNameUnit(), newVehicle);
                            break;
                    }
                    break;

                case "mu":
                    System.out.println("======= Modify a unit =======");
                    mapOfUnits.forEach((name,units)->{
                        System.out.println(" - " + units.getNameUnit());
                    });
                    String unityForModify = sc.nextLine();
                    mapOfUnits.forEach((name,units)->{
                        if(units.getNameUnit().equals(unityForModify)) {
                            if(units instanceof Infantry) {
                                units.printInfantry((Infantry) units);
                            }
                            else if(units instanceof Vehicle) {
                                units.printVehicle((Vehicle) units);
                            }
                        System.out.println("Choose between:\n" +
                                " - Modify the name (n)\n" +
                                " - Modify the points (p)\n" +
                                " - Modify the type (t)\n" +
                                " - Modify the capacity (c)\n" +
                                " - Go to the main menu (menu)");
                        }
                        switch (sc.next()){
                            case "n":
                                System.out.println("======= Modify the name =======");
                                units.setNameUnit(Unit.modifyUnitName(units,sc));
                                break;
                        }
                    });



                    break;
                case "du":
                    if(mapOfUnits.isEmpty()) {
                        System.out.println("Warning !\nThere is no registered units\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Delete a unit =======\n" + "Choose the unit to delete by its name : ");
                    mapOfUnits.forEach((name,unit)->{
                        System.out.println(" - " + unit.getNameUnit());
                    });
                    String nameUnitForDelete = sc.next();
                    mapOfUnits.entrySet().removeIf(entry -> entry.getKey().equals(nameUnitForDelete));
                    break;
                case "su":
                    System.out.println("======= Show all unit =======");
                    if(mapOfUnits.isEmpty()) {
                        System.out.println("There is no registered units\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnits.forEach((name,unit)->{
                        if(unit instanceof Infantry){
                            ((Infantry) unit).printInfantry((Infantry) unit);
                        }
                        if(unit instanceof Vehicle){
                            ((Vehicle) unit).printVehicle((Vehicle) unit);
                        }
                    });
                    System.out.println("\nPress ENTER to continue");
                    break;

                case "exit":
                    return;
            }
        }
    }
}