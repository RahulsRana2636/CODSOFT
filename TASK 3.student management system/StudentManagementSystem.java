import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;
    private String dataFile = "student_data.ser";

    public StudentManagementSystem() {
        students = readStudentsFromStorage();
    }

    public Boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                return false;
            }
        }
        students.add(student);

        saveStudentsToStorage();
        return true;

    }

    public void removeStudent(Student student) {
        students.remove(student);
        saveStudentsToStorage();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null; // Student not found
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void saveStudentsToStorage() {
        try {
            FileOutputStream fileOut = new FileOutputStream(dataFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(students);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Student> readStudentsFromStorage() {
        List<Student> studentList = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            studentList = (ArrayList<Student>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // If the file does not exist or cannot be read, return an empty list
            System.err.println("No existing data. Starting with an empty student list.");
        }
        return studentList;
    }
}
