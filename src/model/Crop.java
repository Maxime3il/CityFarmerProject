package model;

public class Crop {
    private int growthTime;
    private int harvestTime;
    private int quality;
    
    public Crop(int growthTime, int harvestTime, int quality) {
        this.growthTime = growthTime;
        this.harvestTime = harvestTime;
        this.quality = quality;
    }
    
    public void incrementTime() {
        growthTime++;
        harvestTime++;
    }
    
    public int getGrowthTime() {
        return growthTime;
    }
    
    public int getHarvestTime() {
        return harvestTime;
    }
    
    public int getQuality() {
        return quality;
    }
}
