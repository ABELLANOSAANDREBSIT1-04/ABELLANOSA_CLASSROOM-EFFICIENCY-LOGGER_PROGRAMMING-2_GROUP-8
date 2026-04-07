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
        String[] timestamp = new String[10]; // for real-time entry

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
                    else System.out.println("Invalid choice. Enter 1-4 only.");
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter a number 1-4.");
                }
            }

            switch (choice) {

                // ADD DATA PART
                case 1:
                    String addMore;
                    do {
                        if (count >= activity.length) {
                            System.out.println("Storage full.");
                            break;
                        }

                        // Activity code (no empty, no duplicates)
                        String actCode;
                        while (true) {
                            System.out.print("Activity/Subject Code: ");
                            actCode = sc.nextLine().trim();
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
                            } else break;
                        }
                        activity[count] = actCode;

                        // Section (cannot be empty)
                        while (true) {
                            System.out.print("Section: ");
                            String sec = sc.nextLine().trim();
                            if (!sec.isEmpty()) {
                                section[count] = sec;
                                break;
                            }
                            System.out.println("Section cannot be empty.");
                        }

                        // Comment (can be empty)
                        System.out.print("Comment: ");
                        comment[count] = sc.nextLine().trim();

                        // Rating input validation
                        int r;
                        while (true) {
                            System.out.print("Rating (1-5): ");
                            String rateInput = sc.nextLine().trim();
                            try {
                                r = Integer.parseInt(rateInput);
                                if (r >= 1 && r <= 5) break;
                                else System.out.println("Invalid rating. Enter 1-5 only.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Enter a number 1-5.");
                            }
                        }
                        rating[count] = r;

                        // Timestamp
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        timestamp[count] = dtf.format(LocalDateTime.now());

                        count++;

                        // y/n input validation
                        while (true) {
                            System.out.print("Add another data? (y/n): ");
                            addMore = sc.nextLine().trim().toLowerCase();
                            if (addMore.equals("y") || addMore.equals("n")) break;
                            System.out.println("Invalid input. Enter 'y' or 'n' only.");
                        }

                    } while (addMore.equals("y"));

                    break;

                // EDIT DATA
                case 2:
                    if (count == 0) {
                        System.out.println("No data available to edit.");
                        break;
                    }

                    System.out.println("\n=== DATA LIST ===");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + activity[i]);
                    }

                    int edit = -1;
                    while (true) {
                        System.out.print("Choose data to edit (1-" + count + "): ");
                        String editInput = sc.nextLine().trim();
                        try {
                            edit = Integer.parseInt(editInput) - 1;
                            if (edit >= 0 && edit < count) break;
                            else System.out.println("Invalid selection.");
                        } catch (Exception e) {
                            System.out.println("Invalid input. Enter a number.");
                        }
                    }

                    // Editing same as adding
                    String actCode;
                    while (true) {
                        System.out.print("New Activity/SubjectCode: ");
                        actCode = sc.nextLine().trim();
                        if (actCode.isEmpty()) {
                            System.out.println("Activity code cannot be empty.");
                            continue;
                        }
                        boolean duplicate = false;
                        for (int i = 0; i < count; i++) {
                            if (i != edit && activity[i].equalsIgnoreCase(actCode)) {
                                duplicate = true;
                                break;
                            }
                        }
                        if (duplicate) {
                            System.out.println("This activity already exists. Enter a new one.");
                        } else break;
                    }
                    activity[edit] = actCode;

                    while (true) {
                        System.out.print("New Section: ");
                        String sec = sc.nextLine().trim();
                        if (!sec.isEmpty()) {
                            section[edit] = sec;
                            break;
                        }
                        System.out.println("Section cannot be empty.");
                    }

                    System.out.print("New Comment: ");
                    comment[edit] = sc.nextLine().trim();

                    int newRating;
                    while (true) {
                        System.out.print("New Rating (1-5): ");
                        String rateInput = sc.nextLine().trim();
                        try {
                            newRating = Integer.parseInt(rateInput);
                            if (newRating >= 1 && newRating <= 5) break;
                            else System.out.println("Invalid rating. Enter 1-5 only.");
                        } catch (Exception e) {
                            System.out.println("Invalid input. Enter a number 1-5.");
                        }
                    }
                    rating[edit] = newRating;

                    // Update timestamp
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    timestamp[edit] = dtf.format(LocalDateTime.now());

                    System.out.println("Data updated successfully.");
                    break;

                // VIEW DATA
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

                // EXIT
                case 4:
                    String exit;
                    while (true) {
                        System.out.print("Are you sure you want to exit? (y/n): ");
                        exit = sc.nextLine().trim().toLowerCase();
                        if (exit.equals("y") || exit.equals("n")) break;
                        System.out.println("Invalid input. Enter 'y' or 'n' only.");
                    }

                    if (exit.equals("y")) {
                        running = false;
                        System.out.println("Program is dead.");
                    
                    }
                    break;
            }
        }

        sc.close();
    }
}