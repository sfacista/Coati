// 
// Decompiled by Procyon v0.6.0
// 

package tileCreation;

public class TileObject extends ROIObject
{
    private long tileStartPos;
    private long tileEndPos;
    private int length;
    private int gapSize;
    private int offset;
    private String tileBases;
    private double percentGC;
    private double percentInROI;
    private double percentExonic;
    private double percentN;
    
    public TileObject(final String iChromosome, final long iRoiStartPos, final long iTileStartPos, final int iTileSize, final int iLength, final long iEndPos, final int iGapSize, final int iStartIndexAdj, final String iBases, final String iGeneName, final double iPercentGC, final double iPercentInROI, final double iPercentExonic, final double iPercentN) {
        this.setChromosome(iChromosome);
        this.tileStartPos = iTileStartPos;
        this.length = iLength;
        this.tileEndPos = iEndPos;
        this.gapSize = iGapSize;
        this.offset = iStartIndexAdj;
        this.tileBases = iBases;
        this.setGeneName(iGeneName);
        this.percentGC = iPercentGC;
        this.percentInROI = iPercentInROI;
        this.percentExonic = iPercentExonic;
        this.percentN = iPercentN;
    }
    
    public TileObject() {
        this.setChromosome(null);
        this.tileStartPos = 0L;
        this.length = 0;
        this.tileEndPos = 0L;
        this.gapSize = 0;
        this.offset = 0;
        this.setGeneName(this.tileBases = null);
        this.percentGC = 0.0;
        this.percentInROI = 0.0;
        this.percentExonic = 0.0;
        this.percentN = 0.0;
    }
    
    public void setTileStartPos(final long newTileStartPos) {
        this.tileStartPos = newTileStartPos;
    }
    
    public void setLength(final int newLength) {
        this.length = newLength;
    }
    
    public void setTileEndPos(final long newEndPos) {
        this.tileEndPos = newEndPos;
    }
    
    public void setGapSize(final int newGapSize) {
        this.gapSize = newGapSize;
    }
    
    public void setOffset(final int newOffset) {
        this.offset = newOffset;
    }
    
    public void setTileBases(final String newTileBases) {
        this.tileBases = newTileBases;
    }
    
    public void setPercentGC(final double newPercentGC) {
        this.percentGC = newPercentGC;
    }
    
    public void setPercentInROI(final double newPercentInROI) {
        this.percentInROI = newPercentInROI;
    }
    
    public void setPercentExonic(final double newPercentExonic) {
        this.percentExonic = newPercentExonic;
    }
    
    public void setPercentN(final double newPercentN) {
        this.percentN = newPercentN;
    }
    
    public long getTileStartPos() {
        return this.tileStartPos;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public long getTileEndPos() {
        return this.tileEndPos;
    }
    
    public int getGapSize() {
        return this.gapSize;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public String getTileBases() {
        return this.tileBases;
    }
    
    public double getPercentGC() {
        return this.percentGC;
    }
    
    public double getPercentInROI() {
        return this.percentInROI;
    }
    
    public double getPercentExonic() {
        return this.percentExonic;
    }
    
    public double getPercentN() {
        return this.percentN;
    }
}
