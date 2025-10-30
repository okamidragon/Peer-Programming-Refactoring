package edu.uca.core;

import edu.uca.registration.model.Course;
import edu.uca.registration.model.Student;
import edu.uca.registration.service.RegistrationService;

public class RegistrationServiceTest {

    public static void main(String[] args) {
        UT02_enrollStudent_courseHasCapacity();
    }

    public static void UT02_enrollStudent_courseHasCapacity() {
        System.out.println("==== UT02: Enroll Student When Course Has Capacity ====");

        try {
            RegistrationService service = new RegistrationService(".");
            Student studentA = new Student("B005", "Bob", "bob@uca.edu");
            Course s = new Course("CSCI4490", "Software Engineering", 2);

            service.addStudent(studentA.getId(), studentA.getName(), studentA.getEmail());
            service.addCourse(s.getCode(), s.getTitle(), s.getCapacity());

            service.enroll(studentA.getId(), s.getCode());

            Course loaded = service.allCourses()
                    .stream()
                    .filter(c -> c.getCode().equals("CSCI4490"))
                    .findFirst().orElse(null);

            boolean passed = (loaded != null && loaded.getRoster().contains(studentA.getId()));

            if (passed) {
                System.out.println("PASS: Student successfully enrolled in course.");
            } else {
                System.out.println("FAIL: Student was not enrolled.");
            }

        } catch (Exception e) {
            System.out.println("FAIL: Unexpected exception: " + e.getMessage());
        }

        System.out.println("==== End UT02 ====\n");
    }
}