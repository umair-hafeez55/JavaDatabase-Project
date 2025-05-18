import javax.swing.*;

class University {
    String name;
    double minCgpa;
    int passingYearStart;
    int passingYearEnd;
    String requiredDivision;
    double requiredGat;
    String programLevel;

    University(String name, double minCgpa, int start, int end, String division, double gat, String program) {
        this.name = name;
        this.minCgpa = minCgpa;
        this.passingYearStart = start;
        this.passingYearEnd = end;
        this.requiredDivision = division;
        this.requiredGat = gat;
        this.programLevel = program;
    }

    boolean isEligible(User user) {
        return user.cgpa >= minCgpa &&
                user.startingYear >= passingYearStart &&
                user.endingYear <= passingYearEnd &&
                user.division.equalsIgnoreCase(requiredDivision) &&
                user.gatScore >= requiredGat &&
                user.programLevel.equalsIgnoreCase(programLevel);
    }
    static void showEligibleUniversities() {
        StringBuilder eligibleList = new StringBuilder("You are eligible for the following universities:\n");
        boolean found = false;
        for (University u : UniversityAdmissionApp.universities) {
            if (u.isEligible(UniversityAdmissionApp.currentUser)) {
                eligibleList.append("- ").append(u.name).append("\n");
                found = true;
            }
        }
        if (!found) eligibleList = new StringBuilder("Sorry, you are not eligible for any university.");
        JOptionPane.showMessageDialog(null, eligibleList.toString());
    }

    static void seedUniversities() {
        UniversityAdmissionApp.universities.add(new University("Oxford University", 3.0, 2022, 2025, "first", 40.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("Harvard University", 3.5, 2022, 2025, "first", 45.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("Cambridge University", 3.5, 2022, 2025, "first", 50.0, "PhD"));
        UniversityAdmissionApp.universities.add(new University("Stanford University", 3.2, 2022, 2025, "first", 40.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("Sukkur IBA University", 2.2, 2022, 2025, "first", 30.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("Cornell University", 3.6, 2022, 2025, "first", 55.0, "PhD"));

        UniversityAdmissionApp.universities.add(new University("University of California", 3.4, 2022, 2025, "first", 38.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("Princeton University", 3.7, 2022, 2025, "first", 48.0, "PhD"));
        UniversityAdmissionApp.universities.add(new University("Yale University", 3.6, 2022, 2025, "first", 46.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("Columbia University", 3.5, 2022, 2025, "first", 47.0, "PhD"));
        UniversityAdmissionApp.universities.add(new University("University of Chicago", 3.5, 2022, 2025, "first", 45.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("University College London", 3.3, 2022, 2025, "first", 35.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("Massachusetts Institute of Technology", 3.8, 2022, 2025, "first", 50.0, "PhD"));
        UniversityAdmissionApp.universities.add(new University("University of Toronto", 3.2, 2022, 2025, "first", 37.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("National University of Singapore", 3.4, 2022, 2025, "first", 42.0, "PhD"));
        UniversityAdmissionApp.universities.add(new University("University of Edinburgh", 3.1, 2022, 2025, "first", 34.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("University of Manchester", 3.0, 2022, 2025, "first", 32.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("University of Birmingham", 3.0, 2022, 2025, "first", 31.0, "Undergraduate"));
        UniversityAdmissionApp.universities.add(new University("King's College London", 3.2, 2022, 2025, "first", 36.0, "Masters"));
        UniversityAdmissionApp.universities.add(new University("Imperial College London", 3.6, 2022, 2025, "first", 44.0, "PhD"));
    }

}
