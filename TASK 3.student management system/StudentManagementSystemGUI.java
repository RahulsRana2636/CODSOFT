import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystemGUI extends JFrame {
    private StudentManagementSystem managementSystem = new StudentManagementSystem();
    private DefaultListModel<String> studentListModel = new DefaultListModel<>();
    private JList<String> studentList = new JList<>(studentListModel);
    private JTextField nameField = new JTextField(20);
    private JTextField rollNumberField = new JTextField(10);
    private JTextField gradeField = new JTextField(5);

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons and add action listeners
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addStudent());
        addButton.setBackground(Color.red);
        addButton.setForeground(Color.yellow);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> removeStudent());
        removeButton.setBackground(Color.red);
        removeButton.setForeground(Color.yellow);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchStudent());
        searchButton.setBackground(Color.red);
        searchButton.setForeground(Color.yellow);

        JButton displayButton = new JButton("Display All");
        displayButton.addActionListener(e -> updateStudentList());
        displayButton.setBackground(Color.red);
        displayButton.setForeground(Color.yellow);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number: "));
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade: "));
        inputPanel.add(gradeField);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumberStr = rollNumberField.getText();
        String gradeStr = gradeField.getText();

        if (name.isEmpty() || rollNumberStr.isEmpty() || gradeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            char grade = gradeStr.charAt(0);

            Boolean st = managementSystem.addStudent(new Student(name, rollNumber, grade));

            if (st == true) {
                JOptionPane.showMessageDialog(this,
                        "Name: " + name + ", Roll Number: " + rollNumberStr + ", Grade: " + gradeStr + ".",
                        "Successfully", JOptionPane.INFORMATION_MESSAGE);
                updateStudentList();
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(this, "Roll number " + rollNumberStr + " already exist.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Roll number must be a valid integer.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to remove.", "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        managementSystem.removeStudent(managementSystem.getAllStudents().get(selectedIndex));
        updateStudentList();
        JOptionPane.showMessageDialog(this, "Remove Successfully.", "Remove Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchStudent() {
        String rollNumberStr = rollNumberField.getText();

        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a roll number to search.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = managementSystem.searchStudent(rollNumber);
            if (student != null) {
                JOptionPane.showMessageDialog(this, student.toString(), "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.", "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Roll number must be a valid integer.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentList() {
        studentListModel.clear();
        for (Student student : managementSystem.getAllStudents()) {

            studentListModel
                    .addElement(student.getRollNumber() + " - " + student.getName() + " - " + student.getGrade());
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(StudentManagementSystemGUI::new);
    }
}
