import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HomeTuitionSystem {
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private Schedule schedule;

    public HomeTuitionSystem() {
        students = new ArrayList<>();
        tutors = new ArrayList<>();
        schedule = new Schedule();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }

    public void displayTutors() {
        for (Tutor tutor : tutors) {
            System.out.println(tutor.getDetails());
        }
    }

    public void studentInterface(Student student, Scanner scanner) {
        int count = 0;
        boolean firstTime = true;
        while (true) {
            System.out.println("1. View Schedule");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        if (firstTime) {
                            System.out.println("No registered subjects.");
                        } else {
                            // schedule.displayLessonsForStudent(student);
                            List<Lesson> studentLessons = Lesson.getLessons().stream()
                                    .filter(lesson -> lesson.getStudent().equals(student))
                                    .collect(Collectors.toList());
                            System.out.println("\n\nSubjects registered:");
                            System.out.println("*****************************************************");
                            System.out.println("*  Subject         | Day       | Time               *");
                            System.out.println("*****************************************************");
                            for (Lesson lesson : studentLessons) {
                                System.out.printf("*  %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                                        lesson.getDay(), getClassTime(lesson.getSubject()));
                            }
                            System.out.println("*****************************************************");
                        }
                        break;
                    case 2:
                        boolean registerAnother;
                        do {
                            ArrayList<String> registeredSubjects = (ArrayList<String>) Lesson.getLessons().stream()
                                    .filter(lesson -> lesson.getStudent().equals(student))
                                    .map(Lesson::getSubject)
                                    .collect(Collectors.toList());

                            System.out.print("List of subjects offered:\n");
                            System.out.print("*****************************************************\n");
                            System.out.print("*  Subject         | Day       | Time               *\n");

                            if (!registeredSubjects.contains("Malay")) {
                                System.out.print("*  Malay           | FRIDAY    | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("English")) {
                                System.out.print("*  English         | MONDAY    | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("History")) {
                                System.out.print("*  History         | THURSDAY  | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("Mathematics")) {
                                System.out.print("*  Mathematics     | TUESDAY   | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("Science")) {
                                System.out.print("*  Science         | WEDNESDAY | 8:00 PM - 10:00 PM *\n");
                            }

                            System.out.print("*******************************************************\n");
                            System.out.print("Enter course details (subject):\n");
                            String subject = scanner.nextLine();

                            List<String> validSubjects = List.of("Malay", "English", "History", "Mathematics",
                                    "Science");

                            if (validSubjects.contains(subject) && !registeredSubjects.contains(subject)) {
                                Day day = getDayBySubject(subject);
                                Tutor tutor = tutors.get(count++ % tutors.size()); // Assigning first tutor for
                                                                                   // simplicity
                                double price = 50.0; // Assuming a fixed price
                                int lessonId = Lesson.getLessons().size() + 1;
                                Lesson.addLesson(lessonId, subject, tutor, student, price, day);

                                while (true) {
                                    System.out.println("Do you want to register another subject? (yes/no)");
                                    String response = scanner.nextLine();
                                    if (response.equalsIgnoreCase("yes")) {
                                        registerAnother = true;
                                        break;
                                    } else if (response.equalsIgnoreCase("no")) {
                                        List<Lesson> studentLessons = Lesson.getLessons().stream()
                                                .filter(lesson -> lesson.getStudent().equals(student))
                                                .collect(Collectors.toList());

                                        System.out.println("\n\nSubjects registered:");
                                        System.out.println("*****************************************************");
                                        System.out.println("*  Subject         | Day       | Time               *");
                                        System.out.println("*****************************************************");
                                        for (Lesson lesson : studentLessons) {
                                            System.out.printf("*  %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                                                    lesson.getDay(), getClassTime(lesson.getSubject()));
                                        }
                                        System.out.println("*****************************************************");

                                        double totalPrice = Lesson.calculateTotalFeePerMonth(student);
                                        System.out.println("\nTotal price to pay: $" + totalPrice + "\n");
                                        // schedule.displayLessonsForStudent(student);
                                        registerAnother = false;
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                                    }
                                }
                            } else {
                                System.out.println(
                                        "Invalid subject or already registered. Please enter a valid subject.");
                                registerAnother = true;
                            }
                        } while (registerAnother);
                        firstTime = false;
                        break;
                    case 3:
                        System.out.print("Enter course subject to delete: ");
                        String courseToDelete = scanner.nextLine();
                        schedule.removeLesson(student.getName(), courseToDelete);

                        // Display updated subjects after deletion
                        List<Lesson> updatedLessons = Lesson.getLessons().stream()
                                .filter(lesson -> lesson.getStudent().equals(student))
                                .collect(Collectors.toList());

                        System.out.println("\n\nUpdated subjects registered:");
                        System.out.println("*****************************************************");
                        System.out.println("*  Subject         | Day       | Time               *");
                        System.out.println("*****************************************************");
                        for (Lesson lesson : updatedLessons) {
                            System.out.printf("*  %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                                    lesson.getDay(), getClassTime(lesson.getSubject()));
                        }
                        System.out.println("*****************************************************");

                        break;

                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next();
            }
        }
    }

    public void tutorInterface(Tutor tutor, Scanner scanner) {
        int count = 0;
        boolean firstTime = true;
        while (true) {
            System.out.println("1. View Schedule");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        if (firstTime) {
                            System.out.println("No registered subjects.");
                        }
                        // else {
                        // // schedule.displayLessonsForStudent(student);
                        // List<Lesson> studentLessons = Lesson.getLessons().stream()
                        // .filter(lesson -> lesson.getStudent().equals(student))
                        // .collect(Collectors.toList());
                        // System.out.println("\n\nSubjects registered:");
                        // System.out.println("*****************************************************");
                        // System.out.println("* Subject | Day | Time *");
                        // System.out.println("*****************************************************");
                        // for (Lesson lesson : studentLessons) {
                        // System.out.printf("* %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                        // lesson.getDay(), getClassTime(lesson.getSubject()));
                        // }
                        // System.out.println("*****************************************************");
                        // }
                        break;
                    case 2:
                        boolean registerAnother;
                        // do {
                        // ArrayList<String> registeredSubjects = (ArrayList<String>)
                        // Lesson.getLessons().stream()
                        // .filter(lesson -> lesson.getStudent().equals(student))
                        // .map(Lesson::getSubject)
                        // .collect(Collectors.toList());

                        // System.out.print("List of subjects offered:\n");
                        // System.out.print("*****************************************************\n");
                        // System.out.print("* Subject | Day | Time *\n");

                        // if (!registeredSubjects.contains("Malay")) {
                        // System.out.print("* Malay | FRIDAY | 8:00 PM - 10:00 PM *\n");
                        // }
                        // if (!registeredSubjects.contains("English")) {
                        // System.out.print("* English | MONDAY | 8:00 PM - 10:00 PM *\n");
                        // }
                        // if (!registeredSubjects.contains("History")) {
                        // System.out.print("* History | THURSDAY | 8:00 PM - 10:00 PM *\n");
                        // }
                        // if (!registeredSubjects.contains("Mathematics")) {
                        // System.out.print("* Mathematics | TUESDAY | 8:00 PM - 10:00 PM *\n");
                        // }
                        // if (!registeredSubjects.contains("Science")) {
                        // System.out.print("* Science | WEDNESDAY | 8:00 PM - 10:00 PM *\n");
                        // }

                        // System.out.print("*******************************************************\n");
                        // System.out.print("Enter course details (subject):\n");
                        // String subject = scanner.nextLine();

                        // List<String> validSubjects = List.of("Malay", "English", "History",
                        // "Mathematics",
                        // "Science");

                        // if (validSubjects.contains(subject) && !registeredSubjects.contains(subject))
                        // {
                        // Day day = getDayBySubject(subject);
                        // Tutor tutor = tutors.get(count++ % tutors.size()); // Assigning first tutor
                        // for
                        // // simplicity
                        // double price = 50.0; // Assuming a fixed price
                        // int lessonId = Lesson.getLessons().size() + 1;
                        // Lesson.addLesson(lessonId, subject, tutor, student, price, day);

                        // while (true) {
                        // System.out.println("Do you want to register another subject? (yes/no)");
                        // String response = scanner.nextLine();
                        // if (response.equalsIgnoreCase("yes")) {
                        // registerAnother = true;
                        // break;
                        // } else if (response.equalsIgnoreCase("no")) {
                        // List<Lesson> studentLessons = Lesson.getLessons().stream()
                        // .filter(lesson -> lesson.getStudent().equals(student))
                        // .collect(Collectors.toList());

                        // System.out.println("\n\nSubjects registered:");
                        // System.out.println("*****************************************************");
                        // System.out.println("* Subject | Day | Time *");
                        // System.out.println("*****************************************************");
                        // for (Lesson lesson : studentLessons) {
                        // System.out.printf("* %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                        // lesson.getDay(), getClassTime(lesson.getSubject()));
                        // }
                        // System.out.println("*****************************************************");

                        // double totalPrice = Lesson.calculateTotalFeePerMonth(student);
                        // System.out.println("\nTotal price to pay: $" + totalPrice + "\n");
                        // // schedule.displayLessonsForStudent(student);
                        // registerAnother = false;
                        // break;
                        // } else {
                        // System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        // }
                        // }
                        // } else {
                        // System.out.println(
                        // "Invalid subject or already registered. Please enter a valid subject.");
                        // registerAnother = true;
                        // }
                        // } while (registerAnother);
                        firstTime = false;
                        break;
                    case 3:
                        System.out.print("Enter course subject to delete: ");
                        // String courseToDelete = scanner.nextLine();
                        // schedule.removeLesson(student.getName(), courseToDelete);

                        // // Display updated subjects after deletion
                        // List<Lesson> updatedLessons = Lesson.getLessons().stream()
                        // .filter(lesson -> lesson.getStudent().equals(student))
                        // .collect(Collectors.toList());

                        // System.out.println("\n\nUpdated subjects registered:");
                        // System.out.println("*****************************************************");
                        // System.out.println("* Subject | Day | Time *");
                        // System.out.println("*****************************************************");
                        // for (Lesson lesson : updatedLessons) {
                        // System.out.printf("* %-16s| %-10s| %-19s*%n", lesson.getSubject(),
                        // lesson.getDay(), getClassTime(lesson.getSubject()));
                        // }
                        // System.out.println("*****************************************************");

                        break;

                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next();
            }
        }
    }

    private Day getDayBySubject(String subject) {
        switch (subject.toLowerCase()) {
            case "malay":
                return Day.FRIDAY;
            case "english":
                return Day.MONDAY;
            case "history":
                return Day.THURSDAY;
            case "mathematics":
                return Day.TUESDAY;
            case "science":
                return Day.WEDNESDAY;
            default:
                return null; // Or handle unknown subjects appropriately
        }
    }

    private String getClassTime(String subject) {
        return "8:00 PM - 10:00 PM"; // Assuming all classes have the same time
    }
}
