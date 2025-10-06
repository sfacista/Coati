// 
// Decompiled by Procyon v0.6.0
// 

package tileCreation;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import bedFileImport.BedFileObject;
import java.util.ArrayList;

public class ROIBuilder
{
    private static long roiStartPos;
    private static long roiEndPos;
    private static String roiBases;
    private static String chromosome;
    private static String geneName;
    private static long oldRoiStartPos;
    private static long oldRoiEndPos;
    private static long roiLength;
    private static int tileSize;
    private static int gapSize;
    private static int numberPossibleTiles;
    private static int tileOffset;
    private static ArrayList<ROIObject> roiArray;
    
    public static void makeROIObjectFromArrayList(final ArrayList<BedFileObject> bfo, final TilingParametersObject tpo, final String refFA) throws IOException {
        final ArrayList<ROIObject> rA = new ArrayList<ROIObject>();
        for (final BedFileObject b : bfo) {
            fixIndicesForTileBuilder(b, tpo);
            executeCommands(refFA);
            final ROIObject newROIObject = new ROIObject(ROIBuilder.chromosome, ROIBuilder.roiStartPos, ROIBuilder.roiEndPos, ROIBuilder.roiBases, ROIBuilder.geneName);
            rA.add(newROIObject);
            ROIBuilder.roiArray = rA;
        }
    }
    
    public static ArrayList<ROIObject> returnROIarray() {
        return ROIBuilder.roiArray;
    }
    
    public static void fixIndicesForTileBuilder(final BedFileObject someBedFileObject, final TilingParametersObject someTilingParameters) {
        ROIBuilder.chromosome = someBedFileObject.getChromosomeName();
        ROIBuilder.oldRoiStartPos = someBedFileObject.getStartPosition();
        ROIBuilder.oldRoiEndPos = someBedFileObject.getEndPosition();
        ROIBuilder.geneName = someBedFileObject.getGeneName();
        ROIBuilder.tileSize = someTilingParameters.getTileLength();
        ROIBuilder.gapSize = someTilingParameters.getGap();
        ROIBuilder.tileOffset = someTilingParameters.getOffset();
        ROIBuilder.roiLength = ROIBuilder.oldRoiEndPos - ROIBuilder.oldRoiStartPos;
        ROIBuilder.roiStartPos = ROIBuilder.oldRoiStartPos + ROIBuilder.tileOffset;
        ROIBuilder.numberPossibleTiles = 0;
        try {
            ROIBuilder.numberPossibleTiles = (int)(ROIBuilder.roiLength / (ROIBuilder.tileSize + ROIBuilder.gapSize));
            if (ROIBuilder.roiLength % (ROIBuilder.tileSize + ROIBuilder.gapSize) > 1L) {
                ++ROIBuilder.numberPossibleTiles;
            }
        }
        catch (final Exception e) {
            System.out.println("Were the tile and gap size variables define before running this?");
            e.printStackTrace();
        }
        final long tileAreaOnGenome = ROIBuilder.numberPossibleTiles * (ROIBuilder.tileSize + ROIBuilder.gapSize);
        ROIBuilder.roiEndPos = ROIBuilder.roiStartPos + tileAreaOnGenome;
    }
    
    public static void executeCommands(final String refFN) throws IOException {
        final File bashScript = createTempScript(refFN);
        try {
            final ProcessBuilder bashPB = new ProcessBuilder(new String[] { "bash", bashScript.toString() });
            final Process proc = bashPB.start();
            final StringBuilder sb = new StringBuilder();
            final BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            br.readLine();
            String line2;
            while ((line2 = br.readLine()) != null) {
                sb.append(line2);
            }
            ROIBuilder.roiBases = sb.toString();
            ROIBuilder.roiBases = ROIBuilder.roiBases.trim();
            proc.waitFor();
            br.close();
        }
        catch (final InterruptedException e) {
            System.out.println("The processbuilder process failed.");
            e.printStackTrace();
            return;
        }
        finally {
            bashScript.delete();
        }
        bashScript.delete();
    }
    
    public static File createTempScript(final String referenceFile) {
        File theFile = null;
        try {
            final long samToolsEndPos = ROIBuilder.roiEndPos - 1L;
            final File tempScript = File.createTempFile("script", null);
            final Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
            final PrintWriter printWriter = new PrintWriter(streamWriter);
            printWriter.println("#!/bin/bash");
            printWriter.println("samtools faidx " + referenceFile + " " + ROIBuilder.chromosome + ":" + ROIBuilder.roiStartPos + "-" + samToolsEndPos);
            printWriter.close();
            theFile = tempScript;
        }
        catch (final IOException e) {
            System.out.println("There was a problem gnerating the custom script for the proc. builder command.");
            e.printStackTrace();
        }
        return theFile;
    }
    
    public static void printROIs(final ArrayList<ROIObject> rois0) {
        int i = 0;
        for (final ROIObject r : rois0) {
            System.out.println(String.valueOf(r.getChromosome()) + "\t" + r.getRoiStartPos() + "\t" + r.getRoiEndPos() + "\t" + "\t" + r.getGeneName() + "\t" + r.getBases());
            System.out.println("Just printed the " + i + " th ROI.");
            ++i;
        }
    }
}
