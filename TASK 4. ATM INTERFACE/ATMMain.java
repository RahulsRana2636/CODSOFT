import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    JFrame frame = new JFrame();

    public String deposit(double amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(frame, "Invalid amount!");
            return "Invalid amount. Deposit amount should be greater than zero.";

        }
        balance += amount;
        JOptionPane.showMessageDialog(frame, "Deposit successful!");
        return "Successfully deposited $" + amount + ". Current balance: $" + balance;
    }

    public String withdraw(double amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(frame, "Invalid amount!");
            return "Invalid amount. Withdrawal amount should be greater than zero.";
        }
        if (balance < amount) {
            JOptionPane.showMessageDialog(frame, "Insufficient balance!");
            return "Insufficient balance. Unable to process withdrawal.";
        }
        balance -= amount;
        JOptionPane.showMessageDialog(frame, "Withdrawal successful!");
        return "Withdrew $" + amount + ". Current balance: $" + balance;
    }

    public String checkBalance() {
        return "Your current balance is $" + balance;
    }
}

class ATMFrame extends JFrame implements ActionListener {
    private BankAccount bankAccount;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMFrame(BankAccount bankAccount) {
        this.bankAccount = bankAccount;

        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Amount Field
        amountField = new JTextField(10);

        // Output Area
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        // Buttons
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(exitButton);

        // Add components to the frame
        add(amountField, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Withdraw")) {
            double amount = Double.parseDouble(amountField.getText());
            String result = bankAccount.withdraw(amount);
            outputArea.append(result + "\n");
        } else if (command.equals("Deposit")) {
            double amount = Double.parseDouble(amountField.getText());
            String result = bankAccount.deposit(amount);
            outputArea.append(result + "\n");
        } else if (command.equals("Check Balance")) {
            String result = bankAccount.checkBalance();
            outputArea.append(result + "\n");
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
    }
}

public class ATMMain {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount userAccount = new BankAccount(1000);

        // Create the ATM frame
        SwingUtilities.invokeLater(() -> {
            ATMFrame atmFrame = new ATMFrame(userAccount);
            atmFrame.setVisible(true);
        });
    }
}
