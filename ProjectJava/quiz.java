import javax.swing.*;

public class quiz {
    static void startGatQuiz() {
        String[] questions = {
                "1. Which layer of the OSI model is responsible for end-to-end communication?\n a) Network\n b) Transport\n c) Data Link\n d) Application",
                "2. Which protocol is used to send email?\n a) FTP\n b) SMTP\n c) HTTP\n d) SNMP",
                "3. What does CPU stand for?\n a) Central Program Unit\n b) Central Processing Unit\n c) Control Processing Unit\n d) Central Primary Unit",
                "4. Which device is used to connect two networks?\n a) Hub\n b) Switch\n c) Router\n d) Modem",
                "5. Which memory is volatile?\n a) ROM\n b) HDD\n c) RAM\n d) SSD",
                "6. HTML is used to\n a) Program logic\n b) Design layout\n c) Structure web content\n d) Connect to database",
                "7. What is the binary of 5?\n a) 110\n b) 111\n c) 101\n d) 100",
                "8. Which of the following is an output device?\n a) Keyboard\n b) Mouse\n c) Monitor\n d) Scanner",
                "9. Java is a\n a) Hardware\n b) Language\n c) Protocol\n d) Compiler",
                "10. ICT stands for\n a) Internet Computing Technology\n b) Information and Communication Technology\n c) Integrated Circuit Technology\n d) International Communication Tool",
                "11. Capital of Canada?\n a) Toronto\n b) Montreal\n c) Vancouver\n d) Ottawa",
                "12. Who invented the telephone?\n a) Alexander Graham Bell\n b) Thomas Edison\n c) Nikola Tesla\n d) Isaac Newton",
                "13. Smallest continent?\n a) Europe\n b) Australia\n c) Antarctica\n d) South America",
                "14. Largest ocean?\n a) Atlantic\n b) Indian\n c) Arctic\n d) Pacific",
                "15. Longest river in the world?\n a) Amazon\n b) Nile\n c) Yangtze\n d) Mississippi",
                "16. Which planet is known as the Red Planet?\n a) Venus\n b) Jupiter\n c) Mars\n d) Mercury",
                "17. Which gas is essential for breathing?\n a) Hydrogen\n b) Oxygen\n c) Nitrogen\n d) Carbon dioxide",
                "18. Which is a programming language?\n a) Excel\n b) Python\n c) Windows\n d) Google",
                "19. Keyboard is a\n a) Output device\n b) Input device\n c) Storage device\n d) Display device",
                "20. Which company developed Windows?\n a) Google\n b) IBM\n c) Microsoft\n d) Apple",
                "21. What is the result of 10 * 2?\n a) 12\n b) 20\n c) 30\n d) 40",
                "22. 25 / 5 = ?\n a) 10\n b) 4\n c) 5\n d) 6",
                "23. 15 + 12 = ?\n a) 25\n b) 27\n c) 26\n d) 30",
                "24. 8 * 3 = ?\n a) 24\n b) 21\n c) 22\n d) 26",
                "25. 50 - 15 = ?\n a) 30\n b) 35\n c) 45\n d) 40"
        };

        char[] answers = {
                'b','b','b','c','c','c','c','c','b','b',
                'd','a','b','d','b','c','b','b','b','c',
                'b','c','b','a','b'
        };

        int score = 0;
        for (int i = 0; i < 25; i++) {
            String response = JOptionPane.showInputDialog(null, questions[i] + "\n(Enter: a/b/c/d)");
            if (response != null && !response.isEmpty() && response.toLowerCase().charAt(0) == answers[i]) {
                score++;
            }
        }

        UniversityAdmissionApp.currentUser.gatScore = (score / 25.0) * 100;

        if (UniversityAdmissionApp.currentUser.gatScore >= 60.0) {
            JOptionPane.showMessageDialog(null, "You passed the GAT quiz! Score: " + UniversityAdmissionApp.currentUser.gatScore + "%");
            University.showEligibleUniversities();
            UniversityAdmissionApp.SelectUni();
        } else {
            JOptionPane.showMessageDialog(null, "You failed the GAT quiz. Score: " + UniversityAdmissionApp.currentUser.gatScore + "%");
        }
    }
}
