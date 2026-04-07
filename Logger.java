import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Logger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        String[] activity = new String[10];
        String[] section = new String[10];
        String[] comment = new String[10];
        int[] rating = new int[10];
        String[] timestamp = new String[10];
        int count = 0;

        while (running) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add new data");
            System.out.println("2. Edit data");
            System.out.println("3. View data");
            System.out.println("4. Exit");

            int choice = 0;
            while (true) {
                System.out.print("Choose option: ");
                String input = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 4) break;
                    System.out.println("Invalid choice. Enter 1-4 only.");
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter a number 1-4.");
                }
            }

            switch (choice) {
                case 1:
                    addData:
                    while (count < activity.length) {
                        System.out.print("Activity/Subject Code: ");
                        String actCode = sc.nextLine().trim();

                        if (actCode.isEmpty()) {
                            System.out.println("Activity code cannot be empty.");
                            continue;
                        }

                        boolean duplicate = false;
                        for (int i = 0; i < count; i++) {
                            if (activity[i].equalsIgnoreCase(actCode)) {
                                duplicate = true;
                                break;
                            }
                        }

                        if (duplicate) {
                            System.out.println("This activity already exists. Enter a new one.");
                            continue;
                        }

                        activity[count] = actCode;

                        while (true) {
                            System.out.print("Section: ");
                            String sec = sc.nextLine().trim();
                            if (!sec.isEmpty()) {
                                section[count] = sec;
                                break;
                            }
                            System.out.println("Section cannot be empty.");
                        }

                        System.out.print("Comment: ");
                        comment[count] = sc.nextLine().trim();

                        int r = 0;
                        while (true) {
                            System.out.print("Rating (1-5): ");
                            String rateInput = sc.nextLine().trim();
                            try {
                                r = Integer.parseInt(rateInput);
                                if (r >= 1 && r <= 5) break;
                                System.out.println("Invalid rating. Enter 1-5 only.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Enter a number 1-5.");
                            }
                        }

                        rating[count] = r;
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        timestamp[count] = dtf.format(LocalDateTime.now());
                        count++;

                        while (true) {
                            System.out.print("Add another data? (y/n): ");
                            String again = sc.nextLine().trim().toLowerCase();
                            if (again.equals("y")) continue addData;
                            if (again.equals("n")) break addData;
                            System.out.println("Invalid input. Enter 'y' or 'n' only.");
                        }
                    }

                    if (count >= activity.length) {
                        System.out.println("Storage full.");
                    }
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No data available to edit.");
                        break;
                    }

                    System.out.println("\n=== DATA LIST ===");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + activity[i]);
                    }

                    int editIndex = -1;
                    while (true) {
                        System.out.print("Choose data to edit (1-" + count + "): ");
                        String input = sc.nextLine().trim();
                        try {
                            editIndex = Integer.parseInt(input) - 1;
                            if (editIndex >= 0 && editIndex < count) break;
                            System.out.println("Invalid selection.");
                        } catch (Exception e) {
                            System.out.println("Invalid input. Enter a number.");
                        }
                    }

                    while (true) {
                        System.out.print("New Activity/Subject Code: ");
                        String newCode = sc.nextLine().trim();

                        if (newCode.isEmpty()) {
                            System.out.println("Activity code cannot be empty.");
                            continue;
                        }

                        boolean duplicate = false;
                        for (int i = 0; i < count; i++) {
                            if (i != editIndex && activity[i].equalsIgnoreCase(newCode)) {
                                duplicate = true;
                                break;
                            }
                        }

                        if (duplicate) {
                            System.out.println("This activity already exists. Enter a new one.");
                            continue;
                        }

                        activity[editIndex] = newCode;
                        break;
                    }

                    while (true) {
                        System.out.print("New Section: ");
                        String newSec = sc.nextLine().trim();
                        if (!newSec.isEmpty()) {
                            section[editIndex] = newSec;
                            break;
                        }
                        System.out.println("Section cannot be empty.");
                    }

                    System.out.print("New Comment: ");
                    comment[editIndex] = sc.nextLine().trim();

                    while (true) {
                        System.out.print("New Rating (1-5): ");
                        String rateInput = sc.nextLine().trim();
                        try {
                            int r = Integer.parseInt(rateInput);
                            if (r >= 1 && r <= 5) {
                                rating[editIndex] = r;
                                break;
                            }
                            System.out.println("Invalid rating. Enter 1-5 only.");
                        } catch (Exception e) {
                            System.out.println("Invalid input. Enter a number 1-5.");
                        }
                    }

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    timestamp[editIndex] = dtf.format(LocalDateTime.now());
                    System.out.println("Data updated successfully.");
                    break;

                case 3:
                    if (count == 0) {
                        System.out.println("No data stored.");
                    } else {
                        System.out.println("\n=== STORED DATA ===");
                        for (int i = 0; i < count; i++) {
                            System.out.println("\nRecord " + (i + 1));
                            System.out.println("Activity: " + activity[i]);
                            System.out.println("Section: " + section[i]);
                            System.out.println("Comment: " + comment[i]);
                            System.out.println("Rating: " + rating[i]);
                            System.out.println("Timestamp: " + timestamp[i]);
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.print("Are you sure you want to exit? (y/n): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        if (confirm.equals("y")) {
                            running = false;
                            System.out.println("Program exited.");
                            break;
                        } else if (confirm.equals("n")) {
                            break;
                        }
                        System.out.println("Invalid input. Enter 'y' or 'n' only.");
                    }
                    break;
            }
        }

        sc.close();
    }
}