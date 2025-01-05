import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main{
    public static void main (String [] args){

        Map<String,Army> mapOfArmies =  new HashMap<>();
        Map<String, UnitGroup> mapOfUnitGroups =  new HashMap<>();
        Map<String, Unit> mapOfUnits =  new HashMap<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("Press Enter to continue");

        while (!Objects.equals(sc.nextLine(), "exit")) {
            System.out.println(
                    """
                            =========================
                            ======= Main Menu =======
                            =========================
                            Choose between :
                             - Create a new army (na)
                             - Modify an army (ma)
                             - Delete an army (da)
                             - Show all armies (sa)
                             - Create a new unit group (ng)
                             - Modify a unit group (mg)
                             - Delete a unit group (dg)
                             - Show all unit groups (sg)
                             - Create a new unit (nu)
                             - Modify a unit (mu)
                             - Delete a unit (du)
                             - Show all unites (su)
                             - Exit the software (exit)""");
            switch (sc.nextLine()) {
                case "na":
                    System.out.println("======= Create a new army =======");
                    Army newArmy = Army.createNewArmy(sc);
                    if(mapOfArmies.containsKey(newArmy.getNameArmy())){
                        sc.nextLine();
                        System.out.println("Warning!\n\nThis army already exists, choose another name\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfArmies.put(newArmy.getNameArmy(), newArmy);
                    break;
                case "ma":
                    if(mapOfArmies.isEmpty()) {
                        System.out.println("Warning!\n\nYou must first create an army\n\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Modify an army =======\n" + "Choose the army to modify by its name : ");
                    mapOfArmies.forEach((name,army)-> System.out.println(" - " + army.getNameArmy()));
                    String nameArmyForModify =  sc.nextLine();
                    mapOfArmies.forEach((name,army)->{
                        if(nameArmyForModify.equals(name)) {
                            army.printArmy(army);
                            System.out.println("""
                                    Choose between :
                                     - Modify the name (n)
                                     - Modify the faction (f)
                                     - Modify the max number of points (p)
                                     - Add a new unit group (a)
                                     - Remove a unit group (r)
                                     - Go to the main menu (menu)""");
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
                                    if(mapOfUnitGroups.isEmpty()) {
                                        System.out.println("Warning!\n\nYou must first create a unit group\n\nPress ENTER to continue");
                                        break;
                                    }
                                    Army.addUnitGroups(army,sc,mapOfUnitGroups);
                                    break;
                                case "r":
                                    System.out.println("======= Remove a unit group =======");
                                    if(army.getUnitGroups().isEmpty()) {
                                        System.out.println("Warning!\n\nThere is no unit group in this army\n\nPress ENTER to continue");
                                        break;
                                    }
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
                        System.out.println("Warning!\n\nThere is no registered army\n\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Delete an army =======\n" + "Choose the army to delete by its name : ");
                    mapOfArmies.forEach((name,army)-> System.out.println(" - " + army.getNameArmy()));
                    String nameArmyForDelete = sc.nextLine();
                    mapOfArmies.entrySet().removeIf(entry -> entry.getKey().equals(nameArmyForDelete));
                    break;

                case "sa":
                    System.out.println("======= Show all armies =======");
                    if(mapOfArmies.isEmpty()) {
                        System.out.println("Warning!\n\nThere is no registered army\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfArmies.forEach((name,army)-> army.printArmy(army));
                    System.out.println("\nPress ENTER to continue");
                    break;

                case "ng":
                    System.out.println("======= Create a new unit group =======");
                    UnitGroup newUnitGroup = UnitGroup.createNewUnitGroup(sc);
                    if(mapOfUnitGroups.containsKey(newUnitGroup.getName())) {
                        sc.nextLine();
                        System.out.println("Warning!\n\nThis unit group already exists, choose another name\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnitGroups.put(newUnitGroup.getName(), newUnitGroup);
                    break;

                case "mg":
                    System.out.println("======= Modify a unit group =======\n");
                    if(mapOfUnitGroups.isEmpty()) {
                        System.out.println("Warning!\n\nYou must first create a unit group\n\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("Choose the unit group to modify by its name : ");
                    mapOfUnitGroups.forEach((name,unitGroup)-> System.out.println(" - " + unitGroup.getName()));
                    String unitGroupForModify = sc.nextLine();
                    mapOfUnitGroups.forEach((name,unitGroup)->{
                        if(unitGroupForModify.equals(unitGroup.getName())) {
                            unitGroup.printUnitGroup(unitGroup);
                            System.out.println("""
                                    Choose between:
                                     - Modify the name (n)
                                     - Add a unit (a)
                                     - Remove a unit (r)
                                     - Go to the main menu (menu)""");
                            switch (sc.next()) {
                                case "n":
                                    System.out.println("======= Modify the name =======");
                                    unitGroup.setName(UnitGroup.modifyUnitGroupName(unitGroup, sc));
                                    mapOfUnitGroups.replace(unitGroup.getName(), unitGroup);
                                    break;
                                case "a":
                                    System.out.println("======= Add a unit =======");
                                    if (mapOfUnits.isEmpty()) {
                                        System.out.println("Warning!\n\nYou must first create a unit\n\nPress ENTER to continue");
                                        break;
                                    }
                                    UnitGroup.addUnit(unitGroup, sc, mapOfUnits);
                                    break;
                                case "r":
                                    System.out.println("======= Remove a unit =======");
                                    if (unitGroup.getUnits().isEmpty()) {
                                        System.out.println("Warning!\n\nThere is no unit in this unit group\n\nPress ENTER to continue");
                                        break;
                                    }
                                    UnitGroup.removeUnit(unitGroup, sc);
                                    break;
                                case "menu":
                                    break;
                            }
                        }
                    });
                    break;

                case "dg":
                    System.out.println("======= Delete a unit group =======\n");
                    if(mapOfUnitGroups.isEmpty()) {
                        System.out.println("Warning!\n\nThere is no registered unit groups\n\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("Choose the unit group to delete by its name : ");
                    mapOfUnitGroups.forEach((name,unitGroup)-> System.out.println(" - " + unitGroup.getName()));
                    String nameUnitGroupForDelete = sc.nextLine();
                    mapOfUnitGroups.entrySet().removeIf(entry -> entry.getKey().equals(nameUnitGroupForDelete));
                    break;
                case "sg":
                    System.out.println("======= Show all unit groups =======");
                    if(mapOfUnitGroups.isEmpty()) {
                        System.out.println("Warning!\n\nThere is no registered unit groups\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnitGroups.forEach((name,unitGroup)-> unitGroup.printUnitGroup(unitGroup));
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
                    if(mapOfUnits.isEmpty()){
                        System.out.println("Warning!\n\nYou must first create a unit\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnits.forEach((name,units)-> System.out.println(" - " + units.getNameUnit()));
                    String unityForModify = sc.nextLine();
                    mapOfUnits.forEach((name,units)->{
                        if(units.getNameUnit().equals(unityForModify)) {
                            if (units instanceof Infantry) {
                                units.printInfantry((Infantry) units);
                            } else if (units instanceof Vehicle) {
                                units.printVehicle((Vehicle) units);
                            }
                            System.out.println("""
                                    Choose between:
                                     - Modify the name (n)
                                     - Modify the points (p)
                                     - Modify the type (t)
                                     - Modify the capacity (c)
                                     - Go to the main menu (menu)""");
                            switch (sc.nextLine()) {
                                case "n":
                                    System.out.println("======= Modify the name =======");
                                    units.setNameUnit(Unit.modifyUnitName(units, sc));
                                    break;
                                case "p":
                                    System.out.println("======= Modify the points =======");
                                    units.setPoints(Unit.modifyPoints(units, sc));
                                    break;
                                case "t":
                                    System.out.println("======= Modify the type =======");
                                    if (units instanceof Infantry) {
                                        ((Infantry) units).setTypeInfantry(Unit.modifyTypeInfantry((Infantry) units, sc));
                                        break;
                                    } else if (units instanceof Vehicle) {
                                        ((Vehicle) units).setTypeVehicle(Unit.modifyVehicle((Vehicle) units, sc));
                                        break;
                                    }
                                case "c":
                                    System.out.println("======= Modify the capacity =======");
                                    if (units instanceof Vehicle) {
                                        ((Vehicle) units).setTransportCapacity(Unit.modifyTransportCapacity((Vehicle) units, sc));
                                        break;
                                    } else if (units instanceof Infantry) {
                                        System.out.println("Warning!\n\nAn Infantry unit has no capacity\n\nPress ENTER to continue");
                                        sc.nextLine();
                                        break;
                                    }
                                case "menu":
                                    break;
                            }
                        }
                    });
                    break;

                case "du":
                    if(mapOfUnits.isEmpty()) {
                        System.out.println("Warning!\n\nThere is no registered units\n\nPress ENTER to continue");
                        break;
                    }
                    System.out.println("======= Delete a unit =======\n" + "Choose the unit to delete by its name : ");
                    mapOfUnits.forEach((name,unit)-> System.out.println(" - " + unit.getNameUnit()));
                    String nameUnitForDelete = sc.nextLine();
                    mapOfUnits.entrySet().removeIf(entry -> entry.getKey().equals(nameUnitForDelete));
                    break;

                case "su":
                    System.out.println("======= Show all unit =======");
                    if(mapOfUnits.isEmpty()) {
                        System.out.println("Warning!\n\nThere is no registered units\n\nPress ENTER to continue");
                        break;
                    }
                    mapOfUnits.forEach((name,unit)->{
                        if(unit instanceof Infantry){
                            unit.printInfantry((Infantry) unit);
                        }
                        if(unit instanceof Vehicle){
                            unit.printVehicle((Vehicle) unit);
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