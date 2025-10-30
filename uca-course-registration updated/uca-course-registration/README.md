# UCA Course Registration – Baseline (for Refactoring Assignment)

This is a runnable but intentionally messy Java CLI app.  
Your assignment: **Refactor** into clean layers (model, repo, service, ui).  
Preserve existing behaviors: students, courses, enrollments, waitlists, drops.

## Run
```bash
mvn -q -DskipTests package
java -jar target/course-registration-0.1.0.jar
```

## Run Tests

Each test can be run independently from the main.
The easiest way to do so is to upload the project into Visual Studio Code, clear the csv files, and run the test.
