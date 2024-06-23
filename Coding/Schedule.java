import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void displayTutorSchedule(Tutor tutor) {
        List<Lesson> tutorLessons = lessons.stream()
                .filter(lesson -> lesson.getTutor().equals(tutor))
                .collect(Collectors.toList());

        if (tutorLessons.isEmpty()) {
            System.out.println("No lessons scheduled.");
            return;
        }

        System.out.println("\n\nLessons scheduled for Tutor " + tutor.getName() + ":");
        System.out.println("******************************************************************");
        System.out.println("*  Subject         | Tutor       | Day       | Time              *");
        System.out.println("******************************************************************");

        for (Lesson lesson : tutorLessons) {
            System.out.printf("*  %-16s| %-12s| %-10s| %-12s*%n", lesson.getSubject(),
                    lesson.getStudent().getName(), lesson.getDay(),
                    getClassTime(lesson.getSubject()));
        }
        System.out.println("******************************************************************");
    }

    public void displayStudentSchedule(Student student, boolean firstTime) {
        if (firstTime) {
            System.out.println("No registered subjects.");
        } else {
            List<Lesson> studentLessons = lessons.stream()
                    .filter(lesson -> lesson.getStudent().equals(student))
                    .collect(Collectors.toList());
            System.out.println("\n\nSubjects registered:");
            System.out.println("******************************************************************");
            System.out.println("*  Subject         | Tutor       | Day       | Time              *");
            System.out.println("******************************************************************");
            for (Lesson lesson : studentLessons) {
                System.out.printf("*  %-16s| %-12s| %-10s| %-12s*%n", lesson.getSubject(),
                        lesson.getTutor().getName(), lesson.getDay(),
                        getClassTime(lesson.getSubject()));
            }
            System.out.println("******************************************************************");

        }
    }

    private String getClassTime(String subject) {
        return "8:00 PM - 10:00 PM"; // Assuming all classes have the same time
    }

    public void displayAllLessonsWithEnrollment() {
        Map<String, Long> subjectEnrollment = Lesson.getSubjectEnrollment();

        System.out.println("\nAll Lessons and Enrollments:");
        System.out.println("**************************************************");
        System.out.println("*  Subject         | Enrolled Students          *");
        System.out.println("**************************************************");

        subjectEnrollment.forEach((subject, count) -> {
            System.out.printf("*  %-16s| %-24d*%n", subject, count);
        });

        System.out.println("**************************************************");
    }

    public void displayStudentListByLesson() {
        Map<String, List<Student>> studentListBySubject = Lesson.getStudentListForSubjects();

        System.out.println("\nStudent List by Lesson:");
        System.out.println("**************************************************");
        System.out.println("*  Subject         | Students                    *");
        System.out.println("**************************************************");

        studentListBySubject.forEach((subject, students) -> {
            System.out.printf("*  %-16s| %-24s*%n", subject,
                    students.stream().map(Student::getName).collect(Collectors.joining(", ")));
        });

        System.out.println("**************************************************");
    }
}
