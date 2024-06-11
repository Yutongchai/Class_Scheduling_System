import java.util.ArrayList;

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


public static void main(String[] args) {
        Schedule schedule = new Schedule();

        // Create lessons
        Lesson lesson1 = new Lesson("Math", "09:00 AM");
        Lesson lesson2 = new Lesson("Science", "11:00 AM");
        Lesson lesson3 = new Lesson("History", "02:00 PM");

        // Add lessons to the schedule
        schedule.addLesson(lesson1);
        schedule.addLesson(lesson2);
        schedule.addLesson(lesson3);

        // Display lessons
        schedule.displayLessons();

        // Remove a lesson
        schedule.removeLesson("Science");

        // Display lessons again
        schedule.displayLessons();
    }
}
