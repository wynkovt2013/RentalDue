import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RentalDueTest{

    public static void main(String[] args) {
        File file = new File("./rentalDB.txt");
        try {
            Scanner scan = new Scanner(file);
            int l1 = 0;
            int l2 = 0;
            SingleFamilyRental[] s = new SingleFamilyRental[10];
            ApartmentRental[] a = new ApartmentRental[10];
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                switch (str.charAt(0)) {
                    case 'S':
                        s[l1] = new SingleFamilyRental(str);
                        l1++;
                        break;
                    case 'A':
                        a[l2] = new ApartmentRental(str);
                        l2++;
                        break;
                    default:
                        System.err.printf("invalid first character of [%s].",str.charAt(0));
                        System.exit(1);
                }
            }

            // sort
            for (int i = 0; i < l1 -1; i++) {
                for (int j = 1; j < l1; j++) {
                    if (s[i].getBedNum() < s[j].getBedNum()) {
                        SingleFamilyRental temp = s[i];
                        s[i] = s[j];
                        s[j] = temp;
                    }
                }
            }

            // print rent due for all single families
            s[0].OutputHeader();
            for (int i = 0; i < l1; i++) {
                s[i].OutputCurrentRent();
            }
            if (l1 < s.length) {
                System.out.println("...");
            }

            // blank line
            System.out.println();

            // print rent due for all apartments
            a[0].OutputHeader();
            for (int i = 0; i < l2; i++) {
                a[i].OutputCurrentRent();
            }
            if (l2 < a.length) {
                System.out.println("...");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
}
