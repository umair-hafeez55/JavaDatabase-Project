import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartUniversitySelectorFrontPage {

    private JFrame frame;

    public SmartUniversitySelectorFrontPage() {
        // Create main frame
        frame = new JFrame("Smart University Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // Set application icon
        try {
            frame.setIconImage(new ImageIcon("logo.png").getImage());
        } catch (Exception e) {
            System.out.println("Logo image not found, using default icon");
        }

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(25, 25, 112); // MidnightBlue
                Color color2 = new Color(0, 139, 139); // DarkCyan
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 30));
        frame.setContentPane(mainPanel);

        // Add logo at the top
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setOpaque(false);
        try {
            ImageIcon logoIcon = new ImageIcon("icon.png");
            Image scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel logoLabel = new JLabel("Smart University Selector");
            logoLabel.setFont(new Font("Georgia", Font.BOLD, 24));
            logoLabel.setForeground(Color.WHITE);
            logoPanel.add(logoLabel);
        }
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Add heading
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        JLabel headingLabel = new JLabel("SMART UNIVERSITY SELECTOR", JLabel.CENTER);
        headingLabel.setFont(new Font("Georgia", Font.BOLD, 36));
        headingLabel.setForeground(new Color(255, 215, 0)); // Gold color
        headingPanel.add(headingLabel);
        mainPanel.add(headingPanel, BorderLayout.PAGE_START);

        // Add center image
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setOpaque(false);
        try {
            ImageIcon centerIcon = new ImageIcon("img.png");
            Image scaledImage = centerIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("University Image");
            placeholder.setFont(new Font("Arial", Font.ITALIC, 24));
            placeholder.setForeground(Color.WHITE);
            imagePanel.add(placeholder);
        }
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Add buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 30, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 50));

        // Create buttons with different colors
        JButton aboutButton = createStyledButton("About the App",
                new Color(200, 255, 200), // Very light green
                new Color(0, 100, 0)); // Dark green

        JButton universitiesButton = createStyledButton("Top Universities",
                new Color(230, 230, 255), // Very light purple
                new Color(75, 0, 130)); // Dark purple

        JButton continueButton = createStyledButton("Continue",
                new Color(255, 255, 204), // Light yellow
                new Color(184, 134, 11)); // Dark goldenrod

        JButton quitButton = createStyledButton("Quit",
                new Color(255, 200, 200), // Very light red
                new Color(178, 34, 34)); // Dark red


        // Add action listeners
        aboutButton.addActionListener(e -> showAboutDialog());
        universitiesButton.addActionListener(e -> showUniversitiesList());
        continueButton.addActionListener(e -> frame.dispose()); // Only close this frame
        quitButton.addActionListener(e -> quitApplication());


        buttonPanel.add(aboutButton);
        buttonPanel.add(universitiesButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(quitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Center the frame on screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void showAboutDialog() {
        String aboutText = "<html><div style='text-align: center; width: 400px;'>"
                + "<h2 style='color: #006400;'>About Smart University Selector</h2>"
                + "<p style='font-size: 14px;'>"
                + "This application helps students find their ideal universities based on their qualifications.<br><br>"
                + "Features include:<br>"
                + "- User registration and login<br>"
                + "- GAT test to evaluate eligibility<br>"
                + "- Smart suggestions for top universities<br>"
                + "- Direct links to university admission portals<br><br>"
                + "Version 1.0<br>"
                + "Developed for educational purposes"
                + "</p></div></html>";

        JOptionPane.showMessageDialog(frame, aboutText, "About the App", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showUniversitiesList() {
        String[] universities = {
                "Oxford University", "Harvard University", "Sukkur IBA University",
                "University of Cambridge", "Stanford University", "Cornell University",
                "University of California", "Princeton University", "Yale University",
                "Columbia University", "University of Chicago", "University College London",
                "Massachusetts Institute of Technology", "University of Toronto",
                "National University of Singapore", "University of Edinburgh",
                "University of Manchester", "University of Birmingham",
                "King's College London", "Imperial College London"
        };

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 300));

        JLabel title = new JLabel("World's Top 20 Universities", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        JList<String> list = new JList<>(universities);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, panel, "Top Universities", JOptionPane.PLAIN_MESSAGE);
    }

    private void quitApplication() {
        JPanel thankYouPanel = new JPanel(new BorderLayout());
        thankYouPanel.setBackground(new Color(240, 248, 255));

        JLabel thankYouLabel = new JLabel("<html><center><h1 style='color:#006400;'>Thank You!</h1>"
                + "<p style='font-size:16px;'>For using Smart University Selector</p></center></html>",
                JLabel.CENTER);

        int option = JOptionPane.showConfirmDialog(frame, thankYouLabel, "Goodbye",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }
     /*
    public static void main(String[] args) {
        // Set look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new SmartUniversitySelectorFrontPage();
        });
    }
      */
}