
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.List;

public class AddressBookApp extends JFrame {
    private AddressBook addressBook;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextArea displayArea;

    public AddressBookApp() {
        addressBook = new AddressBook();

        setTitle("Address Book System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeComponents();

        displayAllContacts();
    }

    private void initializeComponents() {
        // Input fields
        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel addressLabel = new JLabel("Address:");

        nameField = new JTextField(20);
        phoneField = new JTextField(10);
        emailField = new JTextField(20);
        addressField = new JTextField();

        // Buttons
        JButton addButton = new JButton("Add Contact");
        addButton.setBackground(Color.red);
        addButton.setForeground(Color.yellow);
        JButton removeButton = new JButton("Remove Contact");
        removeButton.setBackground(Color.red);
        removeButton.setForeground(Color.yellow);
        JButton searchButton = new JButton("Search Contact");
        searchButton.setBackground(Color.red);
        searchButton.setForeground(Color.yellow);
        JButton displayButton = new JButton("Display Contacts");
        displayButton.setBackground(Color.red);
        displayButton.setForeground(Color.yellow);

        // Display area for contacts
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeContact();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllContacts();
            }
        });

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(scrollPane), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phoneNum = phoneField.getText().trim();
        String emailAddress = emailField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || emailAddress.isEmpty() || address.isEmpty() || phoneNum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name, phone number, email and address are required.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int phoneNumber = Integer.parseInt(phoneNum);
            Contact newContact = new Contact(name, emailAddress, address, phoneNumber);
            addressBook.addContact(newContact);
            displayAllContacts();
            clearInputFields();
        }
    }

    private void removeContact() {
        int phonenumberToRemove = Integer.parseInt(phoneField.getText().trim());
        Contact contactToRemove = addressBook.searchContact(phonenumberToRemove);
        if (contactToRemove != null) {
            addressBook.removeContact(contactToRemove);
            displayAllContacts();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Contact not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchContact() {
        int phoneNumberToSearch = Integer.parseInt(phoneField.getText().trim());
        Contact searchedContact = addressBook.searchContact(phoneNumberToSearch);
        if (searchedContact != null) {
            displayArea.setText(getContactDetails(searchedContact));
        } else {
            JOptionPane.showMessageDialog(this, "Contact not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllContacts() {
        List<Contact> allContacts = addressBook.getAllContacts();
        StringBuilder displayText = new StringBuilder();
        for (Contact contact : allContacts) {
            displayText.append(getContactDetails(contact)).append("\n");
        }
        displayArea.setText(displayText.toString());
    }

    private String getContactDetails(Contact contact) {
        return "Name: " + contact.getName() + "\nPhone: " + contact.getPhoneNumber() + "\nEmail: "
                + contact.getEmailAddress() + "\nAddress: " + contact.getAddress() + "\n";
    }

    private void clearInputFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressBookApp().setVisible(true);
            }
        });
    }
}
