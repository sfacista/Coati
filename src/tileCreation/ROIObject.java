// 
// Decompiled by Procyon v0.6.0
// 

package tileCreation;

public class ROIObject
{
    private long roiStartPos;
    private long roiEndPos;
    private String roiBases;
    private String chromosome;
    private String geneName;
    
    public ROIObject(final String iChromosome, final long iRoiStartPos, final long iRoiEndPos, final String iBases, final String iGeneName) {
        this.chromosome = iChromosome;
        this.roiStartPos = iRoiStartPos;
        this.roiEndPos = iRoiEndPos;
        this.roiBases = iBases;
        this.geneName = iGeneName;
    }
    
    public ROIObject() {
        this.chromosome = null;
        this.roiStartPos = 0L;
        this.roiEndPos = 0L;
        this.roiBases = null;
        this.geneName = null;
    }
    
    public void setChromosome(final String newChromosome) {
        this.chromosome = newChromosome;
    }
    
    public void setRoiStartPos(final long newroiStartPos) {
        this.roiStartPos = newroiStartPos;
    }
    
    public void setRoiEndPos(final long newTileStartPos) {
        this.roiEndPos = newTileStartPos;
    }
    
    public void setBases(final String newBases) {
        this.roiBases = newBases;
    }
    
    public void setGeneName(final String newGeneName) {
        this.geneName = newGeneName;
    }
    
    public String getChromosome() {
        return this.chromosome;
    }
    
    public long getRoiStartPos() {
        return this.roiStartPos;
    }
    
    public long getRoiEndPos() {
        return this.roiEndPos;
    }
    
    public String getBases() {
        return this.roiBases;
    }
    
    public String getGeneName() {
        return this.geneName;
    }
}
