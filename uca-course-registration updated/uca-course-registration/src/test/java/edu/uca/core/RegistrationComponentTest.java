package edu.uca.core;

import java.io.File;

import edu.uca.registration.model.Course;
import edu.uca.registration.service.RegistrationService;

public class RegistrationComponentTest {

    public static void main(String[] args) {
        ensureDataFolder();
        CT01_waitlistThirdStudent();
    }

    private static void ensureDataFolder() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public static void CT01_waitlistThirdStudent() {
        System.out.println("==== CT01: Waitlist Third Student ====");

        try {
            RegistrationService service = new RegistrationService(".");

            service.addCourse("CSCI4490", "Software Engineering", 2);
            service.addStudent("B001", "Alice", "alice@uca.edu");
            service.addStudent("B002", "Bob", "bob@uca.edu");
            service.addStudent("B003", "Charlie", "charlie@uca.edu");

            service.enroll("B001", "CSCI4490");
            service.enroll("B002", "CSCI4490");
            service.enroll("B003", "CSCI4490"); 

            Course course = service.allCourses().stream()
                    .filter(c -> c.getCode().equals("CSCI4490"))
                    .findFirst().orElse(null);

            boolean pass = course != null &&
                           course.getRoster().contains("B001") &&
                           course.getRoster().contains("B002") &&
                           !course.getRoster().contains("B003") &&
                           course.getWaitlist().contains("B003");

            if (pass) {
                System.out.println("CT01: PASS - Third student waitlisted correctly.");
            } else {
                System.out.println("CT01: FAIL - Roster or waitlist incorrect.");
            }

        } 
        
        catch (Exception e) {
            System.out.println("CT01: FAIL - Exception: " + e.getMessage());
        }

        System.out.println("==== End CT01 ====\n");
    }
}