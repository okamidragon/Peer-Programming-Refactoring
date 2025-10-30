package edu.uca.core;

import edu.uca.registration.service.RegistrationService;

public class PerformanceTest {

    public static void main(String[] args) {
        NFT01_massEnrollment();
    }

    public static void NFT01_massEnrollment() {
        System.out.println("==== NFT01: Mass Enrollment Performance Test ====");

        RegistrationService service = new RegistrationService(".");

        int numCourses = 50;
        int numStudents = 10000;

        for (int i = 0; i < numCourses; i++) {
            service.addCourse("C" + i, "Course " + i, 300);
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < numStudents; i++) {
            String sid = "B00" + i;
            service.addStudent(sid, "Student" + i, "student" + i + "@uca.edu");
            service.enroll(sid, "C" + (i % numCourses));
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Total time: " + duration + " ms");

        if (duration < 5000) {
            System.out.println("NFT01: PASS Completed within 5 seconds");
        } else {
            System.out.println("NFT01: FAIL Took too long");
        }

        System.out.println("==== End NFT01 ====\n");
    }
}