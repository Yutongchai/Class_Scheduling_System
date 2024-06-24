import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Schedule {
    private ArrayList<Lesson> lessons;

    public Schedule() {
        lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(String studentName, String subject) {
        lessons.removeIf(
                lesson -> lesson.getStudent().getName().equalsIgnoreCase(studentName)
                        && lesson.getSubject().equalsIgnoreCase(subject));
    }

    public void displayStudentSchedule(Student student, boolean firstTime) {
        if (firstTime) {
            System.out.println("No registered subjects.");
        } else {
            List<Lesson> studentLessons = lessons.stream()
                    .filter(lesson -> lesson.getStudent().equals(student))
                    .collect(Collectors.toList());
            System.out.println("\n\nSubjects registered:");
            System.out.println("*******************************************************************");
            System.out.println("*  Subject         | Tutor       | Day       | Time               *");
            System.out.println("*******************************************************************");
            for (Lesson lesson : studentLessons) {
                System.out.printf("*  %-16s| %-12s| %-10s| %-12s *%n", lesson.getSubject(),
                        lesson.getTutor().getName(), lesson.getDay(),
                        getClassTime(lesson.getSubject()));
            }
            System.out.println("*******************************************************************");

        }
    }

    private String getClassTime(String subject) {
        return "8:00 PM - 10:00 PM";
    }

}
