import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;

/**
 * Aaron Varkonyi
 * Program #2: CRC codes
 * April 3, 2017
 * CIS-3360-17
 */
public class crcheck {

    /**
     * @param args the command line arguments
     */
    static final String poly = "1010000001010011";
    static byte[] bytes = new byte[512];
    static String verifyHex = "";
    static boolean verify;
	static String name = "";

    public static void ReadIntoBytes(File f) {
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder text = new StringBuilder();
            int value = 0;
            while ((value = br.read()) != -1) {
                // reading the file character by character
                if (text.length() % 64 == 0) {
                    System.out.println(); //printing out a new line every 64 characters
                }
				if (value != '\n'){
                char c = (char) value;
                System.out.print(c);
                text.append(c);
				}
            }
			System.out.println();
            if (text.length() > 504) {
                verifyHex = text.substring(508, 512); //we take the last four characters of our crc file and make them the verification hex
                text.delete(504, 512); //we delete the last 8 characters
            }
            while (text.length() < 504) { //appending extra dots if we have less than 504 characters
                text.append('.');
            }
            bytes = text.toString().getBytes();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String XOR(Integer a, Integer b) {
		return Integer.toBinaryString(a ^ b);
    }

    public static void Calculate() {
        StringBuilder remainder = new StringBuilder();
        for (short r = 1; r <= 8; r++) { //going through each 64 lines
            remainder = new StringBuilder();
            for (short i = 0; i < Math.min(r << 6, 504); i++) {
                String bitString = Integer.toBinaryString(bytes[i]); //converting each byte into a string of bits
                while (bitString.length() < 8 && i > 0) {
                    bitString = "0" + bitString; //adding extra 0s if we need to
                }
                remainder.append(bitString);
            } 
			//appending the 15 0's we will need for calculation
            remainder.append("000000000000000");
            while (remainder.length() > 16 || (remainder.length() == 16 && remainder.charAt(0) == '1')) { //while we have more than 16 bits in our remainder or we have exactly 16 bits but the MSB is a 1
			Integer rNum = 0;
                for (byte ii = 0; ii < 16; rNum += remainder.charAt(ii) == '1' ? (32768 >> ii) : 0, ii++); //building up a decimal version of the first 16 bits of our remainder
                remainder.replace(0, 16, XOR(41043, rNum)); //run an xor operation using the decimal version of our polynomial
            }
			while(remainder.length() < 16){
					remainder.insert(0, '0'); //adding extra zeroes at the beginning if we have less than 16 bits
				}	
			//Here, we convert our binary string into a hexidecimal string.
            String hexCode = "";
            for (byte i = 0; i < 16; i += 4) {
                Integer hexNum = 0;
                for (byte ii = 0; ii < 4; hexNum += remainder.charAt(i + ii) == '1' ? (8 >> ii) : 0, ii++);
				hexCode += Integer.toHexString(hexNum);
            }
            remainder.replace(0, 16, hexCode);
			//we now print out the current 64 characters we are looking at
            System.out.print(new String(bytes).substring((r - 1) << 6, Math.min(r << 6, 504)));
			//if we are at the last line, we print out the remainder crc as well
            if (r == 8) {
                System.out.print("0000" + remainder);
            }
			//we print out each line's cumulative XOR
            System.out.print(" - 0000" + remainder);
            System.out.println();
        }
        System.out.println("CRC16 result : 0000" + remainder);
        if (verify) { //if we are verifying
			System.out.println();
            Verify(remainder.toString()); //run the verify code
        } else {
			//outputting to crc file if we are only doing calculation
            try {
				File file2 = new File(name + ".crc");
                FileOutputStream outStream = new FileOutputStream(file2);
                outStream.write((new String(bytes) + "0000" + remainder.toString()).getBytes());
				outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
    }

    public static void Verify(String s) {
        if (s.equals(verifyHex)) {
            System.out.println("CRC16 verification passed");
        } else {
            System.out.println("CRC16 verification failed");
        }
    }

    public static void main(String[] args) {
        if (args[0].charAt(0) == 'c' || args[0].charAt(0) == 'v') {
			System.out.println();
            System.out.println("CRC16 Input text from file");
            File file1 = new File(args[1]);
            ReadIntoBytes(file1);
			name = args[1].toString().substring(0, args[1].toString().lastIndexOf('.')); //this determines the name of our file if we are calculating
            verify = args[0].charAt(0) == 'v'; //this tells us if we are verifying or not
			System.out.println();
            System.out.println("CRC 16 calculation progress:");
			System.out.println();
            Calculate();
        } else {
            System.out.println("Please use either 'c' or 'v' as your first argument.");
        }
    }
}