import java.util.ArrayList;
import java.util.List;

class Schedule {
    private ArrayList<Lesson> lessons;

    public Schedule() {}

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(String studentName, String subject) {
        List<Lesson> lessons = Lesson.getLessons();
        lessons.removeIf(
            lesson -> lesson.getStudent().getName().equalsIgnoreCase(studentName) 
                    && lesson.getSubject().equalsIgnoreCase(subject));
    }
    
    

    // // Display lessons for a specific student in a timetable style
    // public void displayLessonsForStudent(Student student) {
    //     String[][] timetable = new String[7][7]; // 7 columns for time slots, 7 rows for days

    //     // Initialize timetable with empty strings
    //     for (int i = 0; i < 7; i++) {
    //         for (int j = 0; j < 7; j++) {
    //             timetable[i][j] = " ";
    //         }
    //     }

    //     // Populate timetable with lessons
    //     String[] timeSlots = { "08:00 AM", "10:00 AM", "12:00 PM", "02:00 PM", "04:00 PM", "06:00 PM", "08:00 PM" };
    //     for (Lesson lesson : lessons) {
    //         if (lesson.getStudent().equals(student)) {
    //             int row = -1;
    //             int col = lesson.getDay().ordinal();

    //             // Match the lesson to the correct time slot
    //             for (int i = 0; i < timeSlots.length; i++) {
    //                 if (timeSlots[i].equals(getClassTime(lesson.getSubject()))) {
    //                     row = i;
    //                     break;
    //                 }
    //             }

    //             if (row >= 0 && col >= 0) {
    //                 timetable[row][col] = lesson.getSubject(); // Display subject in timetable
    //             }
    //         }
    //     }

    //     // Print headers for days
    //     System.out.print("Time/Day      ");
    //     for (Day day : Day.values()) {
    //         System.out.printf("%-15s", day.toString());
    //     }
    //     System.out.println();

    //     // Print timetable with time slots
    //     for (int i = 0; i < 7; i++) {
    //         System.out.printf("%-12s", timeSlots[i]);
    //         for (int j = 0; j < 7; j++) {
    //             System.out.printf("%-15s", timetable[i][j]);
    //         }
    //         System.out.println();
    //     }
    // }

    // // Method to get class time based on subject
    // private String getClassTime(String subject) {
    //     switch (subject) {
    //         case "Malay":
    //             return "08:00 PM - 10:00 PM";
    //         case "English":
    //             return "08:00 PM - 10:00 PM";
    //         case "History":
    //             return "08:00 PM - 10:00 PM";
    //         case "Mathematics":
    //             return "08:00 PM - 10:00 PM";
    //         case "Science":
    //             return "08:00 PM - 10:00 PM";
    //         default:
    //             return "";
    //     }
    // }

    public void displayLessonsForTutor(Tutor tutor) {
        for (Lesson lesson : lessons) {
            if (lesson.getTutor().equals(tutor)) {
                System.out.println(lesson);
            }
        }
    }
}
