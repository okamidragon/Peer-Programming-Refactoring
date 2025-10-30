package edu.uca.core;

import edu.uca.registration.model.Course;
import edu.uca.registration.service.RegistrationService;

public class SystemFlowTest {

    public static void main(String[] args) {
        ST01_dropAndPromote();
    }

    // --- System Test: Drop student and promote waitlist ---
    public static void ST01_dropAndPromote() {
        System.out.println("==== ST01: Drop and Promote Waitlist ====");

        try {
            RegistrationService service = new RegistrationService(".");

            service.addCourse("CSCI4490", "Software Engineering", 2);
            service.addStudent("B001", "Alice", "alice@uca.edu");
            service.addStudent("B002", "Bob", "bob@uca.edu");
            service.addStudent("B003", "Charlie", "charlie@uca.edu");

            service.enroll("B001", "CSCI4490");
            service.enroll("B002", "CSCI4490");
            service.enroll("B003", "CSCI4490");

            service.drop("B001", "CSCI4490");

            Course course = service.allCourses().stream()
                    .filter(c -> c.getCode().equals("CSCI4490"))
                    .findFirst().orElse(null);

            boolean pass = course != null &&
                           !course.getRoster().contains("B001") &&
                           course.getRoster().contains("B002") &&
                           course.getRoster().contains("B003") &&
                           course.getWaitlist().isEmpty();

            if (pass) {
                System.out.println("ST01: PASS - Drop and waitlist promotion succeeded.");
            } else {
                System.out.println("ST01: FAIL - Drop or promotion failed.");
            }

        } catch (Exception e) {
            System.out.println("ST01: FAIL - Exception: " + e.getMessage());
        }

        System.out.println("==== End ST01 ====\n");
    }
}
