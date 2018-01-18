package com.gildedrose;

class GildedRose {
    private static final int MIN_QUALITY = 0;
	private static final int MAX_QUALITY = 50;
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static final int SELLING_DATE = 0;
	Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item) || isBackstagePass(item)) {
                if (isItemQualityBelowMaxQuality(item)) {
                    increaseQualityByOne(item);

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            increaseQuality(item);
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item);
                        }
                    }
                }
            } else {
                if (isItemQualityOverMinQuality(item)) {
                    if (!isSulfuras(item)) {
                        decreaseQualityByOne(item);
                    }
                }
            }

            if (!isSulfuras(item)) {
                decreaseSellingDateByOne(item);
            }

            if (isSellinDatePassed(item)) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePass(item)) {
                        if (isItemQualityOverMinQuality(item)) {
                            if (!isSulfuras(item)) {
                                decreaseQualityByOne(item);
                            }
                        }
                    } else {
                        setQualityToMinQuality(item);
                    }
                } else {
                    increaseQuality(item);
                }
            }
        }
    }

    private void increaseQuality(Item item) {
        if (isItemQualityBelowMaxQuality(item)) {
            increaseQualityByOne(item);
        }
    }

    private void decreaseSellingDateByOne(Item item) {
        item.sellIn--;
    }

    private void decreaseQualityByOne(Item item) {
        item.quality--;
    }

    private void setQualityToMinQuality(Item item) {
        item.quality = MIN_QUALITY;
    }

    private boolean isSellinDatePassed(Item item) {
        return item.sellIn < SELLING_DATE;
    }

    private boolean isItemQualityOverMinQuality(Item item) {
        return item.quality > MIN_QUALITY;
    }

    private int increaseQualityByOne(Item item) {
        return item.quality++;
    }

    private boolean isItemQualityBelowMaxQuality(Item item) {
        return item.quality < MAX_QUALITY;
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}
