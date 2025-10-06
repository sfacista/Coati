// 
// Decompiled by Procyon v0.6.0
// 

package tileCreation;

public class TilingParametersObject
{
    private int gap;
    private int offset;
    private int tileLength;
    
    public TilingParametersObject(final int iTileLength, final int iGap, final int iOffset) {
        this.gap = iGap;
        this.offset = iOffset;
        this.tileLength = iTileLength;
    }
    
    public TilingParametersObject() {
        this.gap = 0;
        this.offset = 0;
        this.tileLength = 0;
    }
    
    public void setGap(final int nGap) {
        this.gap = nGap;
    }
    
    public void setOffset(final int nOffset) {
        this.offset = nOffset;
    }
    
    public void setTileLength(final int nTileLength) {
        this.tileLength = nTileLength;
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public int getTileLength() {
        return this.tileLength;
    }
}
