/*
 
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
public class Day09 {
public static void main (String[] args) {
    String diskMap = "2333133121414131402";
    // String diskMap = "12345";


    try {
        List<String> lines = Files.readAllLines(Paths.get("res/Day09_input.txt"));
        for (String line : lines) {
            diskMap += line;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }



    /*
    The disk map uses a dense format to represent the layout of files and free space on the disk. The digits alternate between indicating the length of a file and the length of free space.
    So, a disk map like 12345 would represent a one-block file, two blocks of free space, a three-block file, four blocks of free space, and then a five-block file. A disk map like 90909 would represent three nine-block files in a row (with no free space between them).
    Each file on disk also has an ID number based on the order of the files as they appear before they are rearranged, starting with ID 0. So, the disk map 12345 has three files: a one-block file with ID 0, a three-block file with ID 1, and a five-block file with ID 2. Using one character for each block where digits are the file ID and . is free space, the disk map 12345 represents these individual blocks:
    0..111....22222
    The first example above, 2333133121414131402, represents these individual blocks:
    00...111...2...333.44.5555.6666.777.888899
     */

    // 1. Parse the disk map
    // 2. Create a list of files
    // 3. Create a list of free spaces
    // 4. Create a list of file IDs
    // 5. Create a list of file lengths

    // 1. Parse the disk map
    List<Integer> diskMapList = new ArrayList<>();
    for (int i = 0; i < diskMap.length(); i++) {
        diskMapList.add(Character.getNumericValue(diskMap.charAt(i)));
    }

    // 2. Create a list of files
    List<Integer> files = new ArrayList<>();
    for (int i = 0; i < diskMapList.size(); i += 2) {
        files.add(diskMapList.get(i));
    }

    // 3. Create a list of free spaces
    List<Integer> freeSpaces = new ArrayList<>();
    for (int i = 1; i < diskMapList.size(); i += 2) {
        freeSpaces.add(diskMapList.get(i));
    }

    // 4. Create a list of file IDs
    List<Integer> fileIDs = new ArrayList<>();
    for (int i = 0; i < files.size(); i++) {
        fileIDs.add(i);
    }

    // 5. Create a list of file lengths
    List<Integer> fileLengths = new ArrayList<>();
    for (int i = 0; i < files.size(); i++) {
        fileLengths.add(files.get(i));
    }

    // 6. Print the disk map
    System.out.println("Disk map: " + diskMap);

    // 7. Print the files
    System.out.println("Files: " + files);

    // 8. Print the free spaces
    System.out.println("Free spaces: " + freeSpaces);

    // 9. Print the file IDs
    System.out.println("File IDs: " + fileIDs);

    // 10. Print the file lengths
    System.out.println("File lengths: " + fileLengths);

    // 11. Print the individual blocks
    String diskMapString = "";
    for (int i = 0; i < files.size(); i++) {
        for (int j = 0; j < files.get(i); j++) {
            System.out.print(i);
            diskMapString += i;
        }
        if(freeSpaces.size() > i) {
            for (int j = 0; j < freeSpaces.get(i); j++) {
                System.out.print(".");
                diskMapString += ".";
            }
        }
    }

    System.out.println();
    System.out.println("Disk map string: " + diskMapString);
    System.out.println();
    
    /*
     * The amphipod would like to move file blocks one at a time from the end of the disk to the leftmost 
     * free space block (until there are no gaps remaining between file blocks).
     */

    // 12. Move file blocks one at a time from the end of the disk to the leftmost free space block
    // For the disk map 12345, the process looks like this:

// 0..111....22222
// 02.111....2222.
// 022111....222..
// 0221112...22...
// 02211122..2....
// 022111222......
//Expected output: 022111222...... from 12345


    // Debes recorrer la lista de ficheros e ir incorporando en los huecos libres los ficheros
    // por ejemplo el fichero 2 tiene 2 huecos entre el 0  el 1 y luego 4 huecos libre (usara 3) despus del fichero 1
    // el resto de longitud permanecera igual y se imprimira con el espacio libre al final

    //Recorre diskmap String y ve poniendo los numeros de atras adelante en los huecos libres, hasta que solo queden huecos detras

    char[] diskArray = diskMapString.toCharArray();
    for (int i = diskArray.length - 1; i >= 0; i--) {
        if (Character.isDigit(diskArray[i])) {
            for (int j = 0; j < i; j++) {
                if (diskArray[j] == '.') {
                    diskArray[j] = diskArray[i];
                    diskArray[i] = '.';
                    break;
                }
            }
        }
    }

    System.out.println("Rearranged disk map: " + new String(diskArray));

    /*
     * The final step of this file-compacting process is to update the filesystem checksum. 
     * To calculate the checksum, add up the result of multiplying each of these blocks' position with the file ID number it contains. 
     * The leftmost block is in position 0. If a block contains free space, skip it instead.
     */

    // 13. Calculate the checksum
    BigInteger checksum = BigInteger.ZERO;
    for (int i = 0; i < diskArray.length; i++) {
        if (Character.isDigit(diskArray[i])) {
            checksum = checksum.add(BigInteger.valueOf(Character.getNumericValue(diskArray[i])).multiply(BigInteger.valueOf(i)));
        }
    }

    System.out.println("Checksum: " + checksum);


    }       
}
