import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class UniversityOptionsGUI1 {

    private static final String[] universities = {
            "Oxford University", "Harvard University", "Sukkur IBA University",
            "University of Cambridge", "Stanford University", "Cornell University",
            "University of California", "Princeton University", "Yale University",
            "Columbia University", "University of Chicago", "University College London",
            "Massachusetts Institute of Technology", "University of Toronto",
            "National University of Singapore", "University of Edinburgh",
            "University of Manchester", "University of Birmingham",
            "King's College London", "Imperial College London"
    };

    private static final String[][] universityUrls = {
            {"https://www.ox.ac.uk/admissions/undergraduate/courses/admission-requirements/admission-requirements-table",
                    "https://www.ox.ac.uk/admissions/undergraduate/applying-to-oxford", "https://www.ox.ac.uk/about"},
            {"https://college.harvard.edu/admissions/apply/application-requirements",
                    "https://college.harvard.edu/admissions/apply", "https://www.harvard.edu/about/"},
            {"https://www.iba-suk.edu.pk/admission-requirements",
                    "https://applyadmission.iba-suk.edu.pk", "https://www.iba-suk.edu.pk/"},
            {"https://www.cam.ac.uk/study-at-cambridge/undergraduate-admissions/entry-requirements",
                    "https://www.undergraduate.study.cam.ac.uk/apply", "https://www.cam.ac.uk/about-the-university"},
            {"https://gradadmissions.stanford.edu/apply/eligibility",
                    "https://gradadmissions.stanford.edu/apply/apply-now", "https://www.stanford.edu/about/"},
            {"https://admissions.cornell.edu/how-to-apply/first-year-applicants/college-and-school-admissions-requirements",
                    "https://gradschool.cornell.edu/admissions/application-steps/apply-now/", "https://www.cornell.edu/about/"},
            {"https://admission.universityofcalifornia.edu/",
                    "https://admission.universityofcalifornia.edu/freshman/apply/index.html", "https://www.universityofcalifornia.edu/about"},
            {"https://www.princeton.edu/admission-aid/apply/freshman/eligibility",
                    "https://www.princeton.edu/admission-aid/apply/freshman/application", "https://www.princeton.edu/about-princeton"},
            {"https://admissions.yale.edu/eligibility",
                    "https://admissions.yale.edu/apply", "https://www.yale.edu/about"},
            {"https://www.columbia.edu/content/admissions",
                    "https://www.columbia.edu/content/apply", "https://www.columbia.edu/content/about-columbia"},
            {"https://www.chicagobooth.edu/programs/full-time-mba/admissions/requirements",
                    "https://www.chicagobooth.edu/programs/full-time-mba/admissions/application", "https://www.chicagobooth.edu/about"},
            {"https://www.ucl.ac.uk/prospective-students/undergraduate/apply/entry-requirements",
                    "https://www.ucl.ac.uk/prospective-students/undergraduate/apply", "https://www.ucl.ac.uk/about-ucl"},
            {"https://www.mit.edu/admissions/apply/index.html",
                    "https://www.mit.edu/admissions/apply/application.html", "https://www.mit.edu/about/"},
            {"https://www.utoronto.ca/admissions/first-year/requirements",
                    "https://www.utoronto.ca/admissions/first-year/apply", "https://www.utoronto.ca/about-u-of-t"},
            {"https://www.nus.edu.sg/oam/requirements",
                    "https://www.nus.edu.sg/oam/apply", "https://www.nus.edu.sg/about"},
            {"https://www.ed.ac.uk/studying/undergraduate/apply/entry-requirements",
                    "https://www.ed.ac.uk/studying/undergraduate/apply", "https://www.ed.ac.uk/about"},
            {"https://www.manchester.ac.uk/study/undergraduate/courses/entry-requirements/",
                    "https://www.manchester.ac.uk/study/undergraduate/apply/", "https://www.manchester.ac.uk/about"},
            {"https://www.birmingham.ac.uk/undergraduate/apply/entry-requirements/index.aspx",
                    "https://www.birmingham.ac.uk/undergraduate/apply/index.aspx", "https://www.birmingham.ac.uk/about/index.aspx"},
            {"https://www.kcl.ac.uk/study/undergraduate/apply/requirements",
                    "https://www.kcl.ac.uk/study/undergraduate/apply", "https://www.kcl.ac.uk/about"},
            {"https://www.imperial.ac.uk/study/ug/requirements/",
                    "https://www.imperial.ac.uk/study/ug/apply/", "https://www.imperial.ac.uk/about-imperial"}
    };

    private JFrame frame;
    private JComboBox<String> universityComboBox;
    private JLabel infoLabel;

    public UniversityOptionsGUI1() {
        frame = new JFrame("University Application Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Gradient background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(245, 245, 255);
                Color color2 = new Color(220, 230, 245);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 20));
        frame.setContentPane(backgroundPanel);

        // Top panel with heading and image
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel headingLabel = new JLabel("University Admission System", JLabel.CENTER);
        headingLabel.setFont(new Font("Georgia", Font.BOLD, 32));
        headingLabel.setForeground(new Color(0, 51, 102));
        topPanel.add(headingLabel, BorderLayout.NORTH);

        try {
            ImageIcon originalIcon = new ImageIcon("uni.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
            topPanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("University image not found.");
        }

        backgroundPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel with dropdown and buttons
        JPanel centerPanel = new JPanel(new BorderLayout(10, 20));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectPanel.setOpaque(false);
        JLabel selectLabel = new JLabel("Select University:");
        selectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectLabel.setForeground(new Color(70, 70, 70));

        universityComboBox = new JComboBox<>(universities);
        universityComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        universityComboBox.setBackground(Color.WHITE);
        universityComboBox.setPreferredSize(new Dimension(350, 35));

        selectPanel.add(selectLabel);
        selectPanel.add(universityComboBox);
        centerPanel.add(selectPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton eligibilityBtn = createStyledButton("Check Eligibility", new Color(144, 238, 144));
        JButton applyBtn = createStyledButton("Apply Now", new Color(173, 216, 230));
        JButton websiteBtn = createStyledButton("University Website", new Color(221, 160, 221));
        JButton exitBtn = createStyledButton("Exit", new Color(255, 160, 122));

        buttonPanel.add(eligibilityBtn);
        buttonPanel.add(applyBtn);
        buttonPanel.add(websiteBtn);
        buttonPanel.add(exitBtn);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        // Info label at bottom
        infoLabel = new JLabel("Select a university and choose an action", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        infoLabel.setForeground(new Color(70, 70, 70));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        backgroundPanel.add(infoLabel, BorderLayout.SOUTH);

        // Button actions
        ActionListener actionHandler = e -> {
            int index = universityComboBox.getSelectedIndex();
            String url = "", action = "";

            if (e.getSource() == eligibilityBtn) {
                url = universityUrls[index][0];
                action = "Eligibility Requirements";
            } else if (e.getSource() == applyBtn) {
                url = universityUrls[index][1];
                action = "Application Page";
            } else if (e.getSource() == websiteBtn) {
                url = universityUrls[index][2];
                action = "University Website";
            }

            try {
                Desktop.getDesktop().browse(new URI(url));
                infoLabel.setText("Opening " + action + " for " + universities[index]);
            } catch (Exception ex) {
                infoLabel.setText("Error: Unable to open the link.");
            }
        };

        eligibilityBtn.addActionListener(actionHandler);
        applyBtn.addActionListener(actionHandler);
        websiteBtn.addActionListener(actionHandler);

        exitBtn.addActionListener(e -> {
            JLabel thankYouLabel = new JLabel("<html><center><h1 style='color:#006400;'>Thank You!</h1>"
                    + "<p style='font-size:14px; color:#333;'>Thanks for using the University Admission System.</p></center></html>",
                    JLabel.CENTER);
            JOptionPane.showMessageDialog(frame, thankYouLabel, "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);  // Changed from white to black
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(12, 25, 12, 25)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }
/*
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new UniversityOptionsGUI1());
    }

     */
}
