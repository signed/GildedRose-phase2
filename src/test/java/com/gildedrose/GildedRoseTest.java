package com.gildedrose;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void whenDayPasses_itemAgedBrie_shouldIncreaseQualityByOne()
			throws Exception {
		Item item = new Item(getAgedBrie(), notSellinDatePassed(), 10);
		GildedRose app = createApp(item);

		app.updateQuality();

		assertThat(item.quality, CoreMatchers.is(11));
	}
	
	@Test
	public void whenDayPasses_normalItem_shouldDecreaseQualityByOne()
			throws Exception {
		Item item = new Item(anyName(), notSellinDatePassed(), 10);
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.quality, CoreMatchers.is(9));
	}

	@Test
	public void whenDayPasses_normalItem_shouldDecreaseDaysLeftByOne()
			throws Exception {
		Item item = new Item(anyName(), notSellinDatePassed(), 10);
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.sellIn, CoreMatchers.is(notSellinDatePassed() - 1));
	}
	
	@Test
	public void whenDayPasses_itemSulfuras__shouldNotChangeQuality() throws Exception {
		Item item = new Item("Sulfuras, Hand of Ragnaros", notSellinDatePassed(), anyQuality());
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.quality, CoreMatchers.is(anyQuality()));
	}
	
	@Test
	public void whenDayPasses_normalItemWithZeroQuality_shouldNotDecreaseQuality()
			throws Exception {
		Item item = new Item(anyName(), anySellingDate(), zeroQuality());
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.quality, CoreMatchers.is(zeroQuality()));
	}
	
	@Test
	public void whenDayPasses_normalItemWithNegativeSellDate_shouldDecreaseQualityeByTwo()
			throws Exception {
		Item item = new Item(anyName(), anyNegativeSellingDate(), anyQuality());
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.quality, CoreMatchers.is(anyQuality() -2));
	}
	
	@Test
	public void whenDayPasses_itemAgedBrieWithQualityFifty_shouldNotIncreaseQuality()
			throws Exception {
		Item item = new Item(getAgedBrie(), anySellingDate(), getMaxQuality());
		GildedRose app = createApp(item);
		app.updateQuality();
		assertThat(item.quality, CoreMatchers.is(getMaxQuality()));
	}

	private int anyNegativeSellingDate() {
		return -1;
	}


	private int anyQuality() {
		return 10;
	}


	private int getMaxQuality() {
		return 50;
	}

	private int zeroQuality() {
		return 0;
	}

	private int anySellingDate() {
		return 0;
	}

	private GildedRose createApp(Item item) {
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

	private String getAgedBrie() {
		return "Aged Brie";
	}

	private int notSellinDatePassed() {
		return 3;
	}

	private String anyName() {
		return "foo";
	}
}
