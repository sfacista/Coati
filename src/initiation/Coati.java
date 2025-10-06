// 
// Decompiled by Procyon v0.6.0
// 

package initiation;

import java.util.Scanner;
import java.io.IOException;
import tileCreation.TileObject;
import tileCreation.ROIObject;
import bedFileImport.BedFileObject;
import java.util.ArrayList;
import tileCreation.TileBuilder;
import tileCreation.ROIBuilder;
import bedFileImport.BedFileImporter;
import tileCreation.TilingParametersObject;

public class Coati
{
    public static String refFA;
    public static String bedF;
    public static int offset;
    public static int gap;
    public static int tileLen;
    
    static {
        Coati.refFA = null;
        Coati.bedF = null;
        Coati.offset = 0;
        Coati.gap = 0;
        Coati.tileLen = 0;
    }
    
    public static void main(final String[] args) throws IOException {
        setParams();
        final TilingParametersObject tP = new TilingParametersObject();
        tP.setOffset(Coati.offset);
        tP.setGap(Coati.gap);
        tP.setTileLength(Coati.tileLen);
        BedFileImporter.readInBedFile(Coati.bedF);
        final ArrayList<BedFileObject> whirrrBleep = BedFileImporter.returnBedArrays();
        ROIBuilder.makeROIObjectFromArrayList(whirrrBleep, tP, Coati.refFA);
        final ArrayList<ROIObject> bleepBloop = ROIBuilder.returnROIarray();
        TileBuilder.BuildAllTiles(bleepBloop, tP);
        final ArrayList<TileObject> bleepBleep = TileBuilder.returnTileArray();
        TileBuilder.makeFileTsv(bleepBleep);
    }
    
    public static void setParams() {
        final Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the full path for reference .fa (fasta): ");
        Coati.refFA = keyboard.nextLine();
        System.out.println("Enter the path for the .bed file with the regions you want amplicons (tiles) for: ");
        Coati.bedF = keyboard.nextLine();
        System.out.println("Enter the offset in bases: ");
        Coati.offset = keyboard.nextInt();
        System.out.println("Enter the gap netween amplicons (tiles) in bases: ");
        Coati.gap = keyboard.nextInt();
        System.out.println("Enter the desired amplicon (tile) length in bases: ");
        Coati.tileLen = keyboard.nextInt();
        keyboard.close();
    }
}
