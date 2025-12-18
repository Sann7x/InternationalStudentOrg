import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Student {

    private String name;
    private String studentId;
    private String country;
    private LocalDate visaExpiryDate;
    private int maxWorkHours;

    public Student(String name, String studentId, String country,
                   LocalDate visaExpiryDate, int maxWorkHours) {
        this.name = name;
        this.studentId = studentId;
        this.country = country;
        this.visaExpiryDate = visaExpiryDate;
        this.maxWorkHours = maxWorkHours;
    }

    public String getName() {
        return name;
    }

    public LocalDate getVisaExpiryDate() {
        return visaExpiryDate;
    }

    public int getMaxWorkHours() {
        return maxWorkHours;
    }

    public boolean isVisaExpiringSoon() {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), visaExpiryDate);
        return days <= 30;
    }

    public void checkWorkHours(int workedHours) {
        if (workedHours > maxWorkHours) {
            System.out.println("WARNING: You exceeded your allowed work hours!");
        } else {
            System.out.println("You are within the allowed work hours.");
        }
    }

}
