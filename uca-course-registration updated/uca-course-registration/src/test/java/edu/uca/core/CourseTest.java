package edu.uca.core;

import edu.uca.registration.model.Course;
import edu.uca.registration.model.Student;

public class CourseTest {

    public static void main(String[] args) {
        boolean passed = true;
        System.out.println("==== UT01: Enroll Student ====");

        try {
            Course c = new Course("CSCI4490", "Software Engineering", 2);
            Student studentA = new Student("B001", "Alice", "alice@uca.edu");
            c.getRoster().add(studentA.getId());

            if (!c.getRoster().contains(studentA.getId())) {
                System.out.println("UT-01 failed: Student not in roster.");
                passed = false;
            }

        } catch (Exception e) {
            System.out.println("UT-01 exception: " + e.getMessage());
            passed = false;
        }

        if (passed) {
            System.out.println("CourseTest: All tests passed.");
        } else {
            System.out.println("CourseTest: Some tests failed.");
        }

        System.out.println("==== End UT01 ====\n");
    }
}
