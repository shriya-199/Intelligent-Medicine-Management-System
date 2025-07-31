import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Medicine Management System
 *
 * This class provides a console-based application to manage a list of medicines.
 * Features include adding, viewing, updating, and deleting medicines, as well as
 * tracking the complete history (added, updated, deleted) with timestamps.
 *
 * Author: [Shriya Verma]
 * Date: [2025-07-31]
 */
public class Medicine {
    // Map to store medicine names and their schedules
    static Map<String, MedicineSchedule> medicineSchedules = new HashMap<>();
    // List to store current medicines
    static List<String> medicines = new ArrayList<>();
    // List to store all medicine history events
    static List<MedicineHistory> medicineHistories = new ArrayList<>();

    // Console Color Codes for enhanced output readability
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    /**
     * Main method to run the Medicine Management System.
     * Displays a menu and handles user input for various operations.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Medicine Management System");
        System.out.println("You can manage your medicines, view schedules, and track history.");
        // Background thread for reminders
    new Thread(() -> {
    while (true) {
        try {
            for (Map.Entry<String, MedicineSchedule> entry : medicineSchedules.entrySet()) {
                String medicineName = entry.getKey();
                MedicineSchedule schedule = entry.getValue();
                schedule.checkAndNotifyDose(medicineName);
            }
            Thread.sleep(60000); //  wait 1 minute
        } catch (InterruptedException e) {
            System.out.println("Reminder thread interrupted.");
            return;
        }
    }
}).start();

        System.out.println("==========================================================");
        System.out.println("Please follow the prompts to manage your medicines.");
        System.out.println("==========================================================");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(YELLOW + "Enter 1 to add medicines");
            System.out.println("Enter 2 to view medicines");
            System.out.println("Enter 3 to update medicines");
            System.out.println("Enter 4 to delete medicines");
            System.out.println("Enter 5 to view medicine history");
            System.out.println("Enter 6 to update schedule for an existing medicine ");
            System.out.println("Enter 7 to view the schedule of a medicine");
            System.out.println("Enter 8 to exit the system");
            System.out.print(BLUE + "Your choice: " + RESET);

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
                scanner.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addMedicines(scanner);
                    break;
                case 2:
                    viewMedicines();
                    break;
                case 3:
                    updateMedicines(scanner);
                    break;
                case 4:
                    deleteMedicines(scanner);
                    break;
                case 5:
                    viewMedicineHistory();
                    break;
                case 6:
                    updateScheduleForExistingMedicine(scanner);
                    break;
                case 7:
                    viewMedicineSchedule();
                    break;

                case 8:
                    System.out.println(GREEN + "Exiting the system. Goodbye!" + RESET);
                    scanner.close();
                    return;
                default:
                    System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }
    }

    /**
     * Adds new medicines to the list after validating input.
     * If a duplicate is entered, asks user if they want to skip or continue (for schedule).
     * Records addition in history.
     *
     * @param sc Scanner object for user input
     */
    public static void addMedicines(Scanner sc) {
        System.out.print(YELLOW + "Enter the number of medicines to add: " + RESET);
        int numberOfMedicines;
        try {
            numberOfMedicines = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            sc.nextLine(); // clear invalid input
            return;
        }
        if (numberOfMedicines <= 0) {
            System.out.println(RED + "Number of medicines must be greater than zero." + RESET);
            return;
        }

        System.out.println(YELLOW + "Please enter the names of the medicines:" + RESET);
        sc.nextLine(); // Consume the newline character
        for (int i = 1; i <= numberOfMedicines; i++) {
            String medicineName;
            while (true) {
                System.out.print(BLUE + "Medicine " + i + ": " + RESET);
                medicineName = sc.nextLine();
                if (medicineName == null || medicineName.trim().isEmpty()) {
                    System.out.println(RED + "Medicine name cannot be empty. Please try again." + RESET);
                    continue;
                }
                medicineName = medicineName.trim().toLowerCase();
                if (medicines.contains(medicineName)) {
                    System.out.println(PURPLE + "Medicine " + medicineName + " is already in the list." + RESET);
                    while (true) {
                        System.out.print(YELLOW + "Do you want to skip adding this medicine? (yes/no): " + RESET);
                        String skipChoice = sc.nextLine().trim().toLowerCase();
                        if (skipChoice.equals("yes")) {
                            medicineName = null; // So we don't add it
                            break;
                        } else if (skipChoice.equals("no")) {
                            // Keep asking for a new name until it's different
                            while (true) {
                                System.out.print(YELLOW + "Enter the name of another medicine: " + RESET);
                                String anotherName = sc.nextLine();
                                if (anotherName == null || anotherName.trim().isEmpty()) {
                                    System.out.println(RED + "Medicine name cannot be empty. Please try again." + RESET);
                                    continue;
                                }
                                anotherName = anotherName.trim().toLowerCase();
                                if (medicines.contains(anotherName)) {
                                    System.out.println(PURPLE + "Medicine " + anotherName + " is already in the list." + RESET);
                                    continue;
                                }
                                medicineName = anotherName;
                                break;
                            }
                            break;
                        } else {
                            System.out.println(RED + "Please answer with 'yes' or 'no'." + RESET);
                        }
                    }
                    if (medicineName == null || medicineName.trim().isEmpty() || medicines.contains(medicineName)) {
                        // If user chose to skip, or still duplicate, skip this iteration
                        medicineName = null;
                        break;
                    }
                }
                break;
            }
            if (medicineName == null || medicineName.trim().isEmpty() || medicines.contains(medicineName)) {
                // Skip to next medicine if user chose to skip or duplicate
                continue;
            }
            medicines.add(medicineName);
            String dateAdded = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            medicineHistories.add(new MedicineHistory(medicineName, "added", dateAdded, null, null));
            System.out.println(GREEN + "Medicine " + medicineName + " added successfully." + RESET);

            // Prompt user for schedule details after successful addition
            System.out.println(CYAN + "Enter schedule details for " + medicineName + ":" + RESET);
            MedicineSchedule schedule = MedicineSchedule.createScheduleFromInput();
            medicineSchedules.put(medicineName, schedule); // Store the schedule for the medicine
            System.out.println(GREEN + "Schedule for " + medicineName + " added successfully." + RESET);
            // You may want to store or process the schedule as needed

            // After adding schedule, continue to next medicine
        }
    }
    /**
     * Displays the current list of medicines.
     */
    public static void viewMedicines() {
        if (medicines.isEmpty()) {
            System.out.println(RED + "No medicines available to view." + RESET);
        } else {
            System.out.println(CYAN + "List of Medicines:" + RESET);
            for (String medicine : medicines) {
                System.out.println(BLUE + "- " + medicine + RESET);
            }
        }
    }

    /**
     * Updates the name of an existing medicine.
     * Records both the update and removal/addition in history.
     * After a successful update or addition, prompts for schedule details
     * using MedicineSchedule.createScheduleFromInput().
     *
     * @param sc Scanner object for user input
     */
/**
 * Updates the name of an existing medicine.
 * Records both the update and removal/addition in history.
 * After a successful update or addition, prompts for schedule details
 * using MedicineSchedule.createScheduleFromInput().
 *
 * @param sc Scanner object for user input
 */
public static void updateMedicines(Scanner sc) {
    sc.nextLine(); // Consume leftover newline
    while (true) {
        System.out.println(YELLOW + "What do you want to do?" + RESET);
        System.out.println(YELLOW + "Choose 1 for 'Remove and Add' or 2 for 'Edit while keeping others':" + RESET);
        System.out.print(BLUE + "Your choice: " + RESET);
        int actionChoice;
        try {
            actionChoice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            sc.nextLine(); // clear invalid input
            return;
        }
        sc.nextLine(); // Consume leftover newline

        if (actionChoice == 1) {
            boolean continueUpdating = true;
            while (continueUpdating) {
                System.out.println(GREEN + "You chose to remove a medicine and add a new one." + RESET);
                System.out.print(YELLOW + "Enter the name of the medicine to remove: " + RESET);
                String oldMedicineName = sc.nextLine().trim().toLowerCase();

                if (!medicines.contains(oldMedicineName)) {
                    System.out.println(GREEN + "No matching medicine found. Update successful." + RESET);
                    break;
                }
                medicines.remove(oldMedicineName);
                String dateDeleted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                medicineHistories.add(new MedicineHistory(oldMedicineName, "deleted", null, dateDeleted, null));

                String newMedicineName;
                while (true) {
                    System.out.print(YELLOW + "Enter the new name for the medicine: " + RESET);
                    newMedicineName = sc.nextLine().trim().toLowerCase();

                    if (newMedicineName.isEmpty()) {
                        System.out.println(RED + "Invalid new medicine name. Please try again." + RESET);
                        continue;
                    }
                    if (newMedicineName.equals(oldMedicineName)) {
                        System.out.println(RED + "New medicine name cannot be the same as the old one." + RESET);
                        continue;
                    }
                    if (medicines.contains(newMedicineName)) {
                        System.out.println(PURPLE + "Medicine " + newMedicineName + " already exists in your list." + RESET);
                        continue;
                    }
                    break; // Only break if all checks pass and name is entirely new
                }

                String dateAdded = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                medicines.add(newMedicineName);
                medicineHistories.add(new MedicineHistory(newMedicineName, "added", dateAdded, null, null));
                medicineHistories.add(new MedicineHistory(oldMedicineName + " -> " + newMedicineName, "updated", null, null, dateAdded));
                System.out.println(GREEN + "Medicine updated successfully from " + oldMedicineName + " to " + newMedicineName + "." + RESET);

                // Prompt for schedule details after update
                System.out.println(CYAN + "Enter schedule details for " + newMedicineName + ":" + RESET);
                MedicineSchedule schedule = MedicineSchedule.createScheduleFromInput();
                medicineSchedules.put(newMedicineName, schedule); // Store the schedule for the new medicine    
                System.out.println(GREEN + "Schedule for " + newMedicineName + " added successfully." + RESET); 


                System.out.print(YELLOW + "Do you want to update another medicine? (yes/no): " + RESET);
                String continueChoice = sc.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("yes")) {
                    continueUpdating = false;
                    System.out.println(GREEN + "Exiting update mode." + RESET);
                }
            }
        } else if (actionChoice == 2) {
            String newMedicineName;
            while (true) {
                System.out.println(GREEN + "You chose to add a new medicine while keeping others." + RESET);
                System.out.print(YELLOW + "Enter the new medicine name: " + RESET);
                newMedicineName = sc.nextLine().trim().toLowerCase();
                if (newMedicineName.isEmpty()) {
                    System.out.println(RED + "Invalid medicine name. Please try again." + RESET);
                    continue;
                }
                if (medicines.contains(newMedicineName)) {
                    System.out.println(PURPLE + "Medicine " + newMedicineName + " already exists." + RESET);
                    continue;
                }
                break; // Only break if name is entirely new
            }
            String dateAdded = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            medicines.add(newMedicineName);
            medicineHistories.add(new MedicineHistory(newMedicineName, "added", dateAdded, null, null));
            System.out.println(GREEN + "Medicine " + newMedicineName + " added successfully." + RESET);

            // Prompt for schedule details after addition
            System.out.println(CYAN + "Enter schedule details for " + newMedicineName + ":" + RESET);
            MedicineSchedule schedule = MedicineSchedule.createScheduleFromInput();
            medicineSchedules.put(newMedicineName, schedule); // Store the schedule for the new medicine
            System.out.println(GREEN + "Schedule for " + newMedicineName + " added successfully." + RESET);
        } else {
            System.out.println(RED + "Invalid action. Please try again." + RESET);
            return;
        }
        break;
    }
}

/**
 * Updates the schedule for an existing medicine.
 * Prompts the user for the medicine name and new schedule details.
 * (Assumes schedule storage/handling is implemented as needed.)
 *
 * @param sc Scanner object for user input
 */
public static void updateScheduleForExistingMedicine(Scanner sc) {
    sc.nextLine(); // Consume leftover newline
    System.out.print(YELLOW + "Enter the name of the medicine to update schedule: " + RESET);
    String medicineName = sc.nextLine().trim().toLowerCase();
    if (!medicines.contains(medicineName)) {
        System.out.println(RED + "Medicine " + medicineName + " not found." + RESET);
        return;
    }
    System.out.println(CYAN + "Enter new schedule details for " + medicineName + ":" + RESET);
    MedicineSchedule schedule = MedicineSchedule.createScheduleFromInput();
    medicineSchedules.put(medicineName, schedule); // Update the schedule for the medicine
    System.out.println(GREEN + "Schedule for " + medicineName + " updated successfully." + RESET);
    // Record the update in history
    // You may want to store or update the schedule as needed
    String dateUpdated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    medicineHistories.add(new MedicineHistory(medicineName, "updated", null, null, dateUpdated));
    System.out.println(GREEN + "Schedule for " + medicineName + " updated successfully." + RESET);
}

    /**
     * Deletes a medicine from the list and records the deletion with the current date and time.
     *
     * @param sc Scanner object for user input
     */
    public static void deleteMedicines(Scanner sc) {
        sc.nextLine(); // Consume leftover newline
        System.out.print(YELLOW + "Enter the name of the medicine to delete: " + RESET);
        String medicineName = sc.nextLine().trim().toLowerCase();
        if (!medicines.contains(medicineName)) {
            System.out.println(RED + "Medicine " + medicineName + " not found." + RESET);
            return;
        }
        medicines.remove(medicineName);
        String dateDeleted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        medicineHistories.add(new MedicineHistory(medicineName, "deleted", null, dateDeleted, null));
        System.out.println(GREEN + "Medicine " + medicineName + " deleted successfully." + RESET);
    }

    /**
     * Displays the complete history of medicines, including all add, update, and delete events with timestamps.
     */
    public static void viewMedicineHistory() {
        if (medicineHistories.isEmpty()) {
            System.out.println(RED + "No medicine history available." + RESET);
            return;
        }
        System.out.println(BOLD + CYAN + "Medicine History:" + RESET);
        for (MedicineHistory mh : medicineHistories) {
            StringBuilder sb = new StringBuilder();
            sb.append(BLUE).append("- ").append(mh.name).append(RESET);
            if ("added".equals(mh.action) && mh.dateAdded != null) {
                sb.append(GREEN).append(" [Added on ").append(mh.dateAdded).append("]").append(RESET);
            } else if ("deleted".equals(mh.action) && mh.dateDeleted != null) {
                sb.append(RED).append(" [Deleted on ").append(mh.dateDeleted).append("]").append(RESET);
            } else if ("updated".equals(mh.action) && mh.dateUpdated != null) {
                sb.append(YELLOW).append(" [Updated on ").append(mh.dateUpdated).append("]").append(RESET);
            } else if ("updated".equals(mh.action) && mh.dateAdded != null) {
                sb.append(YELLOW).append(" [Updated on ").append(mh.dateAdded).append("]").append(RESET);
            }
            System.out.println(sb);
        }
        System.out.println(BOLD + CYAN + "Current Medicines:" + RESET);
        if (medicines.isEmpty()) {
            System.out.println(PURPLE + "No current medicines." + RESET);
        } else {
            for (String medicine : medicines) {
                System.out.println(BLUE + "- " + medicine + RESET);
            }
        }
    }
    /**
     * Displays the schedule for a specific medicine entered by the user.
     * It checks whether the medicine exists in the schedule map, and if found,
     * prints the number of doses per day, the times for each dose, and the days 
     * on which the medicine should be taken.
     *
     * This method helps users quickly retrieve dosage instructions for any stored medicine.
     * If the medicine is not found, an appropriate message is shown.
     */
    public static void viewMedicineSchedule() {
        Scanner sc = new Scanner(System.in);
        System.out.print(YELLOW + "Enter the name of the medicine to view schedule: " + RESET);
        String medicineName = sc.nextLine().trim().toLowerCase();
        MedicineSchedule schedule = medicineSchedules.get(medicineName.toLowerCase());
        if (schedule == null) {
            System.out.println(RED + "No schedule found for " + medicineName + "." + RESET);
            return;
        }
        System.out.println(CYAN + "Schedule for " + medicineName + ":" + RESET);
        System.out.println(schedule);
    }
    
    
    /**
     * Helper class to track all medicine history events (added, updated, deleted) with timestamps.
     */
    static class MedicineHistory {
        String name;
        String action; // "added", "deleted", "updated"
        String dateAdded;
        String dateDeleted;
        String dateUpdated;

        MedicineHistory(String name, String action, String dateAdded, String dateDeleted, String dateUpdated) {
            this.name = name;
            this.action = action;
            this.dateAdded = dateAdded;
            this.dateDeleted = dateDeleted;
            this.dateUpdated = dateUpdated;
        }
    }
    
}
