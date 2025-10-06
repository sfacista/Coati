// 
// Decompiled by Procyon v0.6.0
// 

package bedFileImport;

import java.util.Iterator;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class BedFileImporter
{
    static ArrayList<BedFileObject> bL;
    
    public static void readInBedFile(final String bedFileName) {
        try {
            final String fileName = bedFileName;
            String line0 = null;
            final BufferedReader in0 = new BufferedReader(new FileReader(fileName));
            line0 = in0.readLine();
            final ArrayList<BedFileObject> bedLines = new ArrayList<BedFileObject>();
            while (line0 != null) {
                final String[] strArray = line0.split("\t");
                final BedFileObject newBedFileObject = new BedFileObject(strArray[0], Long.parseLong(strArray[1]), Long.parseLong(strArray[2]), strArray[3]);
                bedLines.add(newBedFileObject);
                BedFileImporter.bL = bedLines;
                line0 = in0.readLine();
            }
            in0.close();
        }
        catch (final Exception e0) {
            System.out.println("There was a problem with the format of your bed file.\nThis program accepts only files with format:\n chromosome_name\tstart_position\tstop_position\nor\n\n chromosome_name\tstart_position\tstop_position\texternal_gene_name\n\nNo headers are permitted");
            e0.printStackTrace();
        }
    }
    
    public static ArrayList<BedFileObject> returnBedArrays() {
        return BedFileImporter.bL;
    }
    
    public static void printLinesBedFile() {
        int i = 0;
        for (final BedFileObject o : BedFileImporter.bL) {
            System.out.println("This is the " + i + " object");
            System.out.println("The chromosome name is " + o.getChromosomeName());
            System.out.println("The start position is " + o.getStartPosition());
            System.out.println("The end position is " + o.getEndPosition());
            System.out.println("The gene name is " + o.getGeneName());
            ++i;
        }
    }
}
