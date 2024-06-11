public class Lesson {
    private int lessonId;
    private String date;
    private String time;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private Day day;
    private boolean isConfirmed;
    private boolean isCancelled;

    // Constructor
    public Lesson(int lessonId, String date, String time, String subject, Tutor tutor, Student student, double price,
            Day day) {
        this.lessonId = lessonId;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.tutor = tutor;
        this.student = student;
        this.price = price;
        this.day = day;
        this.isConfirmed = false;
        this.isCancelled = false;
    }

    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    // Method to confirm the lesson
    public void confirmLesson() {
        if (!isCancelled) {
            this.isConfirmed = true;
            System.out.println("Lesson confirmed.");
        } else {
            System.out.println("Cannot confirm a cancelled lesson.");
        }
    }

    // Method to cancel the lesson
    public void cancelLesson() {
        if (isConfirmed) {
            System.out.println("Cannot cancel a confirmed lesson.");
        } else {
            this.isCancelled = true;
            System.out.println("Lesson cancelled.");
        }
    }

    // Method to calculate the lesson price (simple implementation)
    public void calculateLessonPrice() {
        double basePrice = 20.0; // Base price per lesson
        if ("Math".equalsIgnoreCase(subject)) {
            price = basePrice + 10; // Math lessons are more expensive
        } else if ("Science".equalsIgnoreCase(subject)) {
            price = basePrice + 15; // Science lessons are even more expensive
        } else {
            price = basePrice; // Default price for other subjects
        }
        System.out.println("The calculated price for the lesson is: " + price);
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", date=" + date + ", time=" + time + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price + ", day=" + day +
                ", isConfirmed=" + isConfirmed + ", isCancelled=" + isCancelled + "]";
    }
}
