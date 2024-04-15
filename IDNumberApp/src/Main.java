import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean validID = false;
        while (!validID) {
            System.out.println("Please enter your ID Number:");                             // SA ID Number
            String idNumber = scanner.nextLine();

            try {
                if (isValidIDNumber(idNumber)) {
                    System.out.println("Valid ID number.");
                    int year = getYearOfBirth(idNumber);
                    int month = getMonthOfBirth(idNumber);
                    int day = getDayOfBirth(idNumber);
                    String gender = checkGender(idNumber);
                    String citizenship = getCitizenship(idNumber);

                    System.out.println("Year of Birth: " + year +
                            "\nMonth of Birth: " + month +
                            "\nDay of Birth: " + day +
                            "\nGender: " + gender +
                            "\nCitizenship: " + citizenship);
                    validID = true;                                             // Set validID to true to exit the loop
                } else {
                    throw new InvalidIDNumberException("Invalid ID number.");
                }
            } catch (InvalidIDNumberException e) {
                System.out.println("Message: " + e.getMessage());
                                                                                    // Continue to prompt for ID number
            }
        }

        scanner.close();
    }

    public static boolean isValidIDNumber(String idNumber) {
        return idNumber.length() == 13;
    }

    public static int getYearOfBirth(String idNumber) {
        int year = Integer.parseInt(idNumber.substring(0, 2));
        int century = Integer.parseInt(idNumber.substring(0, 2));

        if (century >= 0 && century <= 21) {
            return 2000 + year;
        } else {
            return 1900 + year;
        }
    }

    public static int getMonthOfBirth(String idNumber) {
        return Integer.parseInt(idNumber.substring(2, 4));
    }

    public static int getDayOfBirth(String idNumber) {
        return Integer.parseInt(idNumber.substring(4, 6));
    }

    public static String checkGender(String idNumber) {                     // Extract the next 4 digits representing gender
        String genderDigits = idNumber.substring(6, 10);
        int genderValue = Integer.parseInt(genderDigits);                  // Convert the genderDigits string to an integer
        int threshold = 5000;                                             // Define the threshold to differentiate between male and female

        if (genderValue < threshold) {                                   // Check if the gender value is less than the threshold
            return "Female";
        } else {
            return "Male";
        }
    }

    public static String getCitizenship(String idNumber) {
        int citizenshipDigit = Integer.parseInt(idNumber.substring(10, 11));
        return (citizenshipDigit == 0) ? "SA Citizen" : "Permanent Resident";
    }
}

class InvalidIDNumberException extends Exception {
    public InvalidIDNumberException(String message) {
        super(message);
    }
}
