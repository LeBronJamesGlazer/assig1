import models.Person;
import models.School;
import models.Student;
import models.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        School school = new School();

        try {
            BufferedReader studentReader = new BufferedReader(new FileReader("src/students.txt"));
            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] data = line.split(" ");
                String name = data[0];
                String surname = data[1];
                int age = Integer.parseInt(data[2]);
                boolean gender = data[3].equalsIgnoreCase("Male");

                Student student = new Student(name, surname, age, gender);
                for (int i = 4; i < data.length; i++) {
                    student.addGrade(Integer.parseInt(data[i]));
                }
                school.addMember(student);
            }
            studentReader.close();

            BufferedReader teacherReader = new BufferedReader(new FileReader("src/teachers.txt"));
            while ((line = teacherReader.readLine()) != null) {
                String[] data = line.split(" ");
                String name = data[0];
                String surname = data[1];
                int age = Integer.parseInt(data[2]);
                boolean gender = data[3].equalsIgnoreCase("Male");
                String subject = data[4];
                int yearsOfExperience = Integer.parseInt(data[5]);
                int salary = Integer.parseInt(data[6]);

                Teacher teacher = new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
                if (yearsOfExperience > 10) {
                    teacher.giveRaise(10);
                }
                school.addMember(teacher);
            }
            teacherReader.close();

        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }

        System.out.println("School Members:");
        System.out.println(school);

        System.out.println("GPA Calculations:");
        for (Person member : school.getMembers()) {
            if (member instanceof Student) {
                Student student = (Student) member;
                System.out.println(student.getName() + "'s GPA: " + student.calculateGPA());
            }
        }

        System.out.println("\nUpdated Salaries for Teachers:");
        for (Person member : school.getMembers()) {
            if (member instanceof Teacher) {
                Teacher teacher = (Teacher) member;
                System.out.println(teacher.getName() + "'s salary: " + teacher.getSalary());
            }
        }
    }
}
