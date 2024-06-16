class Schedule {
    private ArrayList<Lesson> lessons;

    public Schedule() {
        lessons = new ArrayList<>();
    }

    // Add a lesson to the schedule
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        System.out.println("Added: " + lesson);
    }

    // Remove a lesson from the schedule by title
    public void removeLesson(String title) {
        boolean removed = false;
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getSubject().equalsIgnoreCase(title)) {
                System.out.println("Removed: " + lessons.get(i));
                lessons.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Lesson with title \"" + title + "\" not found.");
        }
    }

    // Display all lessons in the schedule
    public void displayLessons() {
        if (lessons.isEmpty()) {
            System.out.println("No lessons in the schedule.");
        } else {
            System.out.println("Schedule:");
            for (Lesson lesson : lessons) {
                System.out.println(lesson);
            }
        }
    }

    // Display lessons for a specific student
    public void displayLessonsForStudent(Student student) {
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (lesson.getStudent().getStudentId() == student.getStudentId()) {
                System.out.println(lesson);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No lessons found for student: " + student.getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();

        // Assuming Tutor and Day classes are defined
        Tutor tutor = new Tutor(1, "John Doe");
        Day day = Day.MONDAY; // Assuming an enum Day is defined with values like MONDAY, TUESDAY, etc.

        // Create students
        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");

        // Create lessons
        Lesson lesson1 = new Lesson(1, "2024-06-01", "09:00 AM", "Math", tutor, student1, 50.0, day);
        Lesson lesson2 = new Lesson(2, "2024-06-01", "11:00 AM", "Science", tutor, student2, 50.0, day);
        Lesson lesson3 = new Lesson(3, "2024-06-01", "02:00 PM", "History", tutor, student1, 50.0, day);

        // Add lessons to the schedule
        schedule.addLesson(lesson1);
        schedule.addLesson(lesson2);
        schedule.addLesson(lesson3);

        // Display all lessons
        schedule.displayLessons();

        // Display lessons for a specific student
        System.out.println("Lessons for Alice:");
        schedule.displayLessonsForStudent(student1);

        System.out.println("Lessons for Bob:");
        schedule.displayLessonsForStudent(student2);

        // Remove a lesson
        schedule.removeLesson("Science");

        // Display lessons again
        schedule.displayLessons();
    }
}