// 
// Decompiled by Procyon v0.6.0
// 

package tileCreation;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class TileBuilder
{
    private static double percentGC;
    private static double percentN;
    private static double percentInROI;
    private static int tileLength;
    private static double percentExonic;
    public static ArrayList<TileObject> tiles;
    static TileObject tile;
    
    static {
        TileBuilder.tiles = new ArrayList<TileObject>();
    }
    
    public static ArrayList<TileObject> returnTileArray() {
        return TileBuilder.tiles;
    }
    
    public static void BuildAllTiles(final ArrayList<ROIObject> rA, final TilingParametersObject tpobj) {
        final ArrayList<TileObject> someTiles = new ArrayList<TileObject>();
        for (final ROIObject r : rA) {
            someTiles.addAll(createTile(r, tpobj));
        }
        System.out.println("All the tiles were added to the master array!");
        TileBuilder.tiles = someTiles;
    }
    
    public static ArrayList<TileObject> createTile(final ROIObject roio, final TilingParametersObject tpo) {
        final ArrayList<TileObject> iT = new ArrayList<TileObject>();
        final long tileStartIndex = roio.getRoiStartPos();
        final long originalFinalIndex;
        final long finalIndex = originalFinalIndex = roio.getRoiEndPos();
        TileBuilder.tileLength = tpo.getTileLength();
        final int gap = tpo.getGap();
        long possibleTiles = (finalIndex - tileStartIndex) / (TileBuilder.tileLength + gap);
        possibleTiles = (int)possibleTiles;
        final String bases = roio.getBases();
        final char[] baseArray = bases.toCharArray();
        int i;
        TileObject aTile;
        long begin;
        int internalStart;
        long end;
        int internalEnd;
        char[] subset;
        String theBases;
        double[] gCN;
        for (i = 0, i = 0; i < possibleTiles; ++i) {
            aTile = new TileObject();
            begin = tileStartIndex + i * (TileBuilder.tileLength + gap);
            internalStart = 0 + i * (TileBuilder.tileLength + gap);
            end = begin + TileBuilder.tileLength - 1L;
            internalEnd = internalStart + TileBuilder.tileLength;
            try {
                subset = Arrays.copyOfRange(baseArray, internalStart, internalEnd);
                theBases = String.copyValueOf(subset);
                if (end - originalFinalIndex > 0L) {
                    TileBuilder.percentInROI = (double)((end - originalFinalIndex) / (end - begin));
                }
                else {
                    TileBuilder.percentInROI = 1.0;
                }
                gCN = calculatePercentGCN(subset);
                aTile.setPercentGC(gCN[0]);
                aTile.setPercentN(gCN[1]);
                aTile.setPercentExonic(calculatePercentExonic(theBases));
                aTile.setPercentInROI(TileBuilder.percentInROI);
                aTile.setTileStartPos(begin);
                aTile.setTileEndPos(end);
                aTile.setLength(TileBuilder.tileLength);
                aTile.setGapSize(gap);
                aTile.setOffset(tpo.getOffset());
                aTile.setTileBases(theBases);
                aTile.setChromosome(roio.getChromosome());
                aTile.setGeneName(roio.getGeneName());
                iT.add(aTile);
            }
            catch (final Exception e) {
                System.out.println("There was a problem listing the character or copying the char array to theBases");
                System.out.println("Check to make sure there is not a mismatch in the header nomenclature for the reference .fa file and the .bed file.");
                e.printStackTrace();
            }
        }
        return iT;
    }
    
    public static double[] calculatePercentGCN(final char[] someBases) {
        final double[] returnValue = new double[2];
        double aCounter = 0.0;
        double tCounter = 0.0;
        double cCounter = 0.0;
        double gCounter = 0.0;
        double nCounter = 0.0;
        try {
            for (int i = 0; i < someBases.length; ++i) {
                if (someBases[i] == 'a' || someBases[i] == 'A') {
                    ++aCounter;
                }
                else if (someBases[i] == 't' || someBases[i] == 'T') {
                    ++tCounter;
                }
                else if (someBases[i] == 'c' || someBases[i] == 'C') {
                    ++cCounter;
                }
                else if (someBases[i] == 'g' || someBases[i] == 'G') {
                    ++gCounter;
                }
                else if (someBases[i] == 'n' || someBases[i] == 'N') {
                    ++nCounter;
                }
                else {
                    System.out.println("Failure in character array. Unknown character!");
                    System.out.println("i is currently at: " + i);
                }
            }
        }
        catch (final Exception e) {
            System.out.println("Something in the counting of the caracter types for this contig failed during ATCG/N counting.");
        }
        TileBuilder.percentGC = (gCounter + cCounter) / (gCounter + cCounter + aCounter + tCounter + nCounter);
        TileBuilder.percentN = nCounter / (gCounter + cCounter + aCounter + tCounter + nCounter);
        aCounter = 0.0;
        tCounter = 0.0;
        cCounter = 0.0;
        gCounter = 0.0;
        nCounter = 0.0;
        returnValue[0] = TileBuilder.percentGC;
        returnValue[1] = TileBuilder.percentN;
        return returnValue;
    }
    
    public static double calculatePercentExonic(final String tileBases) {
        double exonicCounter = 0.0;
        for (int i0 = 0; i0 < tileBases.length(); ++i0) {
            if (Character.isUpperCase(tileBases.charAt(i0))) {
                ++exonicCounter;
            }
        }
        return TileBuilder.percentExonic = exonicCounter / tileBases.length();
    }
    
    public static void printTiles(final ArrayList<TileObject> tiles0) {
        int i = 0;
        for (final TileObject t : tiles0) {
            System.out.println("Start: " + t.getTileStartPos() + "\t" + "Length: " + t.getLength() + "\t" + "End: " + t.getTileEndPos() + "\t" + "Gap: " + t.getGapSize() + "\t" + "Offset: " + t.getOffset() + "\t" + "Bases: " + t.getTileBases() + "\t" + "PercentGC: " + t.getPercentGC() + "\t" + "PercentROI: " + t.getPercentInROI() + "\t" + "PercentExonic: " + t.getPercentExonic() + "\t" + "PercentN: " + t.getPercentN() + "\t" + "Gene: " + t.getGeneName() + "\t" + "Chromosome: " + t.getChromosome());
            System.out.println("Just printed the " + i + " th tile.");
            ++i;
        }
    }
    
    public static void makeFileTsv(final ArrayList<TileObject> tiles1) {
        PrintWriter outStream = null;
        try {
            final LocalDateTime dateTime = LocalDateTime.now();
            outStream = new PrintWriter(new FileOutputStream("Batch_Amplicon_Generation_" + dateTime + "_.tsv"));
        }
        catch (final FileNotFoundException e) {
            System.out.println("A file could not be generated for output.");
            e.printStackTrace();
        }
        try {
            outStream.println("Contig_or_Chromosome\tStart_Index\tEnd_Index\tGene_Name\tAmplicon_Size\tGap_Size\tOffset\tPercent_GC\tPercent_N\tPercent_Exonic\tAmplicon_Bases");
            for (final TileObject t : tiles1) {
                outStream.println(String.valueOf(t.getChromosome()) + "\t" + t.getTileStartPos() + "\t" + t.getTileEndPos() + "\t" + t.getGeneName() + "\t" + t.getLength() + "\t" + t.getGapSize() + "\t" + t.getOffset() + "\t" + t.getPercentGC() + "\t" + t.getPercentN() + "\t" + t.getPercentExonic() + "\t" + t.getTileBases());
            }
            outStream.close();
            System.out.println("A file was generated.");
            System.out.println("Coati version 1.0 - Salvatore Facista (sfacista@tgen.org)");
        }
        catch (final Exception e2) {
            System.out.println("The output stream could not be written to the file.");
            e2.printStackTrace();
        }
    }
}
