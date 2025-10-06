// 
// Decompiled by Procyon v0.6.0
// 

package bedFileImport;

public class BedFileObject
{
    private String chromosomeName;
    private long startPosition;
    private long endPosition;
    private String geneName;
    
    public BedFileObject() {
        this.chromosomeName = null;
        this.startPosition = 0L;
        this.endPosition = 0L;
        this.geneName = null;
    }
    
    public BedFileObject(final String iChromosomeName, final long iStartPosition, final long iEndPosition, final String iGeneName) {
        this.chromosomeName = iChromosomeName;
        this.startPosition = iStartPosition;
        this.endPosition = iEndPosition;
        this.geneName = iGeneName;
    }
    
    public BedFileObject(final String iChromosomeName, final long iStartPosition, final long iEndPosition) {
        this.chromosomeName = iChromosomeName;
        this.startPosition = iStartPosition;
        this.endPosition = iEndPosition;
    }
    
    public void setChromosomeName(final String newChromosomeName) {
        this.chromosomeName = newChromosomeName;
    }
    
    public void setStartPosition(final long newStartPosition) {
        this.startPosition = newStartPosition;
    }
    
    public void setEndPosition(final long newEndPosition) {
        this.endPosition = newEndPosition;
    }
    
    public void setGeneName(final String newGeneName) {
        this.geneName = newGeneName;
    }
    
    public String getChromosomeName() {
        return this.chromosomeName;
    }
    
    public long getStartPosition() {
        return this.startPosition;
    }
    
    public long getEndPosition() {
        return this.endPosition;
    }
    
    public String getGeneName() {
        return this.geneName;
    }
}
