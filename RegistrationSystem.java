import java.util.*;
import java.io.*;

public class RegistrationSystem {

    static Scanner sc = new Scanner(System.in);
    static final String USER_FILE = "users.txt";
    static final String COURSE_FILE = "course_reg.txt";

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n-----------------------------");
            System.out.println(" | COURSE REGISTRATION SYSTEM  |");
            System.out.println("  -----------------------------");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. View Courses");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    showCourses();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }

    // USER REGISTRATION
    static void registerUser() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true));

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            bw.write(username + "," + password + "," + email);
            bw.newLine();
            bw.close();

            System.out.println("User Registered Successfully!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // LOGIN
    static void loginUser() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean found = false;

        try {
            File file = new File(USER_FILE);
            if (!file.exists()) {
                System.out.println("No users found.");
                return;
            }

            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String[] data = fs.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    found = true;
                    break;
                }
            }
            fs.close();
        } catch (Exception e) {
        }

        if (found) {
            System.out.println("Login Successful!");
            courseMenu(username);
        } else {
            System.out.println("Invalid login!");
        }
    }

    // COURSE MENU
    static void courseMenu(String username) {
        int choice;
        do {
            System.out.println("\n--- COURSE MENU ---");
            System.out.println("1. Java Full Stack");
            System.out.println("2. Python with Data Science");
            System.out.println("3. Web Development (HTML, CSS, JS)");
            System.out.println("4. Exit");
            System.out.print("Choose course to view details: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    courseDetails("Java Full Stack", "6 Months", "Rs.12000", username);
                    break;
                case 2:
                    courseDetails("Python with Data Science", "4 Months", "Rs.10000", username);
                    break;
                case 3:
                    courseDetails("Web Development", "3 Months", "Rs.8000", username);
                    break;
                case 4:
                    System.out.println("Exiting course menu...");
                    break;
                default:
                    System.out.println("Invalid!");
            }

        } while (choice != 4);
    }

    // COURSE DETAILS + REGISTRATION
    static void courseDetails(String course, String duration, String fees, String username) {
        System.out.println("\n==============================");
        System.out.println("Course Name: " + course);
        System.out.println("Duration: " + duration);
        System.out.println("Fees: " + fees);
        System.out.println("Description: This course teaches practical skills with projects.");
        System.out.println("==============================");

        System.out.print("Do you want to register? (yes/no): ");
        String ans = sc.nextLine();

        if (ans.equalsIgnoreCase("yes")) {
            fakePayment(course, fees, username);
        }
    }

    // FAKE PAYMENT SYSTEM
    static void fakePayment(String course, String fees, String username) {
        System.out.println("\n--- PAYMENT PORTAL ---");
        System.out.println("Course: " + course);
        System.out.println("Amount to Pay: " + fees);
        System.out.print("Enter card number (fake): ");
        sc.nextLine();
        System.out.print("Enter CVV: ");
        sc.nextLine();

        System.out.println("Processing Payment...");
        System.out.println("Payment Successful!");

        saveCourseRegistration(username, course);
    }

    // SAVE COURSE REGISTRATION
    static void saveCourseRegistration(String username, String course) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(COURSE_FILE, true));
            bw.write(username + " registered for " + course);
            bw.newLine();
            bw.close();
            System.out.println("Course Registered Successfully!");
        } catch (Exception e) {
            System.out.println("Error saving registration.");
        }
    }

    // SHOW COURSES (WITHOUT LOGIN)
    static void showCourses() {
        System.out.println("\nAvailable Courses:");
        System.out.println("1. Java Full Stack");
        System.out.println("2. Python with Data Science");
        System.out.println("3. Web Development");
        System.out.println("Login to register!");
    }
}