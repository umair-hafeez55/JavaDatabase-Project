import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class UniversityAdmissionApp {
    static List<User> users = new ArrayList<>();
    static User currentUser = new User();
    static List<University> universities = new ArrayList<>();
    static JFrame mainFrame;

    public static void main(String[] args) {
        University.seedUniversities();
        FirstClass();
        showMainMenu();
    }

    static void showMainMenu() {
        mainFrame = new JFrame("Welcome to Smart University Selector");
        mainFrame.getContentPane().setBackground(new Color(240, 248, 255));
        mainFrame.setLayout(null);

        JLabel title = new JLabel("Smart University Selector", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setBounds(60, 20, 300, 40);
        mainFrame.add(title);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(90, 70, 300, 300);
        ImageIcon icon = new ImageIcon("icon.png");
        imageLabel.setIcon(icon);
        mainFrame.add(imageLabel);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");
        JButton testConnBtn = new JButton("Test DB Connection");

        loginBtn.setBounds(100, 240, 200, 40);
        registerBtn.setBounds(100, 290, 200, 40);
        testConnBtn.setBounds(100, 340, 200, 40);

        loginBtn.setBackground(Color.GREEN);
        registerBtn.setBackground(Color.CYAN);
        testConnBtn.setBackground(Color.ORANGE);

        loginBtn.addActionListener(e -> showLoginForm());
        registerBtn.addActionListener(e -> showRegistrationForm());
        testConnBtn.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                JOptionPane.showMessageDialog(null, "Database connection successful!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Database connection failed: " + ex.getMessage());
            }
        });

        mainFrame.add(loginBtn);
        mainFrame.add(registerBtn);
        mainFrame.add(testConnBtn);

        mainFrame.setSize(400, 450);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void showLoginForm() {
        loadUsersFromDatabase();

        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No registered users found. Please register first.");
            return;
        }

        JFrame loginFrame = new JFrame("Login Portal");
        loginFrame.setLayout(new GridLayout(4, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        loginFrame.add(new JLabel("Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Password:"));
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel(""));
        loginFrame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            String sql = "SELECT * FROM users WHERE username = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    String inputHash = PasswordHasher.hashPassword(password);

                    if (storedHash.equals(inputHash)) {
                        currentUser = new User();
                        currentUser.username = username;
                        currentUser.name = rs.getString("name");
                        currentUser.age = rs.getInt("age");
                        currentUser.gender = rs.getString("gender").charAt(0);
                        currentUser.cgpa = rs.getDouble("cgpa");
                        currentUser.startingYear = rs.getInt("starting_year");
                        currentUser.endingYear = rs.getInt("ending_year");
                        currentUser.division = rs.getString("division");
                        currentUser.gatScore = rs.getDouble("gat_score");
                        currentUser.programLevel = rs.getString("program_level");

                        loginFrame.dispose();
                        mainFrame.dispose();
                        quiz.startGatQuiz(); // Assuming quiz is implemented elsewhere
                    } else {
                        JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(loginFrame, "Database error: " + ex.getMessage());
            }
        });

        loginFrame.setSize(300, 180);
        loginFrame.setVisible(true);
    }

    static void loadUsersFromDatabase() {
        users.clear();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.name = rs.getString("name");
                user.fatherName = rs.getString("father_name");
                user.age = rs.getInt("age");
                user.gender = rs.getString("gender").charAt(0);
                user.cgpa = rs.getDouble("cgpa");
                user.startingYear = rs.getInt("starting_year");
                user.endingYear = rs.getInt("ending_year");
                user.division = rs.getString("division");
                user.gatScore = rs.getDouble("gat_score");
                user.programLevel = rs.getString("program_level");

                users.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load users: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void showRegistrationForm() {
        JFrame regFrame = new JFrame("Registration Portal");
        regFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        regFrame.setLayout(new GridLayout(15, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField nameField = new JTextField();
        JTextField fatherNameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField cgpaField = new JTextField();

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JComboBox<String> programBox = new JComboBox<>(new String[]{"Undergraduate", "Graduate", "Masters", "Ph.D"});

        JComboBox<String> startYearBox = new JComboBox<>();
        JComboBox<String> endYearBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 20; i <= currentYear + 10; i++) {
            startYearBox.addItem(String.valueOf(i));
            endYearBox.addItem(String.valueOf(i));
        }

        JRadioButton firstDiv = new JRadioButton("First");
        JRadioButton secondDiv = new JRadioButton("Second");
        ButtonGroup divGroup = new ButtonGroup();
        divGroup.add(firstDiv);
        divGroup.add(secondDiv);

        JButton registerBtn = new JButton("Submit");
        registerBtn.setBackground(Color.ORANGE);

        regFrame.add(new JLabel("Registered Yourself!", JLabel.CENTER));
        regFrame.add(new JLabel(""));

        regFrame.add(new JLabel("Username:"));
        regFrame.add(usernameField);
        regFrame.add(new JLabel("Password:"));
        regFrame.add(passwordField);
        regFrame.add(new JLabel("Name:"));
        regFrame.add(nameField);
        regFrame.add(new JLabel("Father's Name:"));
        regFrame.add(fatherNameField);
        regFrame.add(new JLabel("Age:"));
        regFrame.add(ageField);
        regFrame.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);
        regFrame.add(genderPanel);
        regFrame.add(new JLabel("CGPA:"));
        regFrame.add(cgpaField);
        regFrame.add(new JLabel("Starting Year:"));
        regFrame.add(startYearBox);
        regFrame.add(new JLabel("Ending Year:"));
        regFrame.add(endYearBox);
        regFrame.add(new JLabel("Division:"));
        JPanel divPanel = new JPanel();
        divPanel.add(firstDiv);
        divPanel.add(secondDiv);
        regFrame.add(divPanel);
        regFrame.add(new JLabel("Program Level:"));
        regFrame.add(programBox);
        regFrame.add(new JLabel(""));
        regFrame.add(registerBtn);

        registerBtn.addActionListener(e -> {
            try {
                User user = new User();
                user.username = usernameField.getText();
                user.password = new String(passwordField.getPassword());
                user.name = nameField.getText();
                user.fatherName = fatherNameField.getText();

                try {
                    user.age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(regFrame, "Invalid Age. Please enter a number.");
                    return;
                }

                if (male.isSelected()) user.gender = 'M';
                else if (female.isSelected()) user.gender = 'F';
                else {
                    JOptionPane.showMessageDialog(regFrame, "Please select Gender.");
                    return;
                }

                try {
                    user.cgpa = Double.parseDouble(cgpaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(regFrame, "Invalid CGPA. Please enter a decimal.");
                    return;
                }

                user.startingYear = Integer.parseInt((String) startYearBox.getSelectedItem());
                user.endingYear = Integer.parseInt((String) endYearBox.getSelectedItem());

                if (firstDiv.isSelected()) user.division = "First";
                else if (secondDiv.isSelected()) user.division = "Second";
                else {
                    JOptionPane.showMessageDialog(regFrame, "Please select Division.");
                    return;
                }

                user.programLevel = (String) programBox.getSelectedItem();

                String sql = "INSERT INTO users (username, password, name, father_name, age, gender, " +
                        "cgpa, starting_year, ending_year, division, program_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    String hashedPassword = PasswordHasher.hashPassword(user.password);

                    pstmt.setString(1, user.username);
                    pstmt.setString(2, hashedPassword);
                    pstmt.setString(3, user.name);
                    pstmt.setString(4, user.fatherName);
                    pstmt.setInt(5, user.age);
                    pstmt.setString(6, String.valueOf(user.gender));
                    pstmt.setDouble(7, user.cgpa);
                    pstmt.setInt(8, user.startingYear);
                    pstmt.setInt(9, user.endingYear);
                    pstmt.setString(10, user.division);
                    //pstmt.setDouble(11, currentUser.gatScore);
                    pstmt.setString(11, user.programLevel);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(regFrame, "Registration successful!");
                        regFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(regFrame, "Registration failed. Try again.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(regFrame, "Database error: " + ex.getMessage());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(regFrame, "Unexpected error: " + ex.getMessage());
            }
        });

        regFrame.setSize(500, 600);
        regFrame.setVisible(true);
        regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    static void SelectUni() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new UniversityOptionsGUI1());
    }

    static void FirstClass() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new SmartUniversitySelectorFrontPage());
    }
}

class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }
}
