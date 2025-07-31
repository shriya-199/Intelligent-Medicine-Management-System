import java.util.*; 
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents the schedule for a medicine, including the number of doses,
 * the times for each dose, and the days when the medicine should be taken.
 */
public class MedicineSchedule {
    private int numberOfDoses;
    private List<String> doseTimes;
    private List<String> days;
    private static List<String> existingDayTimeSlots = new ArrayList<>();

    public MedicineSchedule(int numberOfDoses, List<String> doseTimes, List<String> days) {
        this.numberOfDoses = numberOfDoses;
        this.doseTimes = doseTimes;
        this.days = days;
    }

    // Getters
    public int getNumberOfDoses() { return numberOfDoses; }
    public List<String> getDoseTimes() { return doseTimes; }
    public List<String> getDays() { return days; }

    // Setters
    public void setNumberOfDoses(int numberOfDoses) { this.numberOfDoses = numberOfDoses; }
    public void setDoseTimes(List<String> doseTimes) { this.doseTimes = doseTimes; }
    public void setDays(List<String> days) { this.days = days; }

    @Override
    public String toString() {
        return "Schedule {" +
                "Doses per day = " + numberOfDoses +
                // both days time will be printed in the format "Day-Time"
                ", Existing Slots = " + existingDayTimeSlots +
                '}';
    }

    /**
     * Adds 10 minutes to a given time in "HH:mm" format.
     */
    private static String addTenMinutes(String time) {
        if (time == null || !time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm.");
        }
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        minutes += 10;
        if (minutes >= 60) {
            minutes -= 60;
            hours = (hours + 1) % 24;
        }

        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Creates a schedule with conflict resolution for overlapping time slots.
     */
    public static MedicineSchedule createScheduleFromInput() {
        Scanner scanner = new Scanner(System.in);
        int numberOfDoses = 0, numberOfDays = 0;

        // Number of doses per day
        while (true) {
            try {
                System.out.print("Enter number of doses per day: ");
                numberOfDoses = Integer.parseInt(scanner.nextLine());
                if (numberOfDoses <= 0) {
                    System.out.println("Number of doses must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Number of days
        while (true) {
            try {
                System.out.print("Enter number of days to take the medicine: ");
                numberOfDays = Integer.parseInt(scanner.nextLine());
                if (numberOfDays <= 0) {
                    System.out.println("Number of days must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Days list
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= numberOfDays; i++) {
            System.out.print("Enter day " + i + " (e.g., Monday): ");
            String day = scanner.nextLine().trim();
            if (day.isEmpty()) {
                System.out.println("Day cannot be empty. Please try again.");
                i--;
                continue;
            }
            days.add(day);
        }

        // Dose times with conflict check
        List<String> doseTimes = new ArrayList<>();
        for (int i = 1; i <= numberOfDoses; i++) {
            while (true) {
                System.out.print("Enter time for dose " + i + " (HH:mm): ");
                String time = scanner.nextLine().trim();

                if (!time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                    System.out.println("Invalid time format. Use HH:mm.");
                    continue;
                }

                // Conflict resolution
                for (String day : days) {
                    String slot = day + "-" + time;

                    while (existingDayTimeSlots.contains(slot)) {
                        System.out.println("Conflict on " + day + " at " + time);
                        time = addTenMinutes(time);
                        slot = day + "-" + time;
                        System.out.println("Adjusted to: " + time);
                    }

                    existingDayTimeSlots.add(slot);
                }

                doseTimes.add(time);
                break;
            }
        }
        
        

        return new MedicineSchedule(numberOfDoses, doseTimes, days);
    }
    public void checkAndNotifyDose(String medicineName) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = LocalTime.now().format(timeFormatter);
        String today = LocalDate.now().getDayOfWeek().toString(); // e.g., MONDAY

        boolean found = false;
        for (String scheduledDay : days) {
            if (scheduledDay.equalsIgnoreCase(today)) {
                for (String doseTime : doseTimes) {
                    if (doseTime.equals(currentTime)) {
                        System.out.println("\u001B[Reminder: It's time to take your medicine: " + medicineName + " at " + doseTime + "\u001B[0m");
                        found = true;
                    }
                }
            }
        }
    }
}
