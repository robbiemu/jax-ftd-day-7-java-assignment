package com.cooksys.ftd.test.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;

import com.cooksys.butterpillar.model.IButterpillar;

public class ButterpillarSortingTest {

	private static Reflections reflections;
	private static Set<Class<? extends IButterpillar>> implementations;
	private static Class<? extends IButterpillar> implementation;

	private Random rng;
	private List<IButterpillar> butterpillars;
	private Comparator<IButterpillar> comparator;

	@BeforeClass
	public static void beforeClass() {
		ButterpillarSortingTest.reflections = new Reflections("com.cooksys");
		ButterpillarSortingTest.implementations = reflections.getSubTypesOf(IButterpillar.class);
		for (Class<? extends IButterpillar> implementation : ButterpillarSortingTest.implementations) {
			ButterpillarSortingTest.implementation = implementation;
		}
	}

	@AfterClass
	public static void afterClass() {
		ButterpillarSortingTest.reflections = null;
		ButterpillarSortingTest.implementations.clear();
		ButterpillarSortingTest.implementations = null;
		ButterpillarSortingTest.implementation = null;
	}

	@Before
	public void before() throws InstantiationException, IllegalAccessException {
		this.rng = new Random();
		this.comparator = new Comparator<IButterpillar>() {
			@Override
			public int compare(IButterpillar o1, IButterpillar o2) {
				if (o1.getLength() < o2.getLength()) {
					return -1;
				} else if (o1.getLength() > o2.getLength()) {
					return +1;
				} else if (o1.getLeavesEaten() < o2.getLeavesEaten()) {
					return -1;
				} else if (o1.getLeavesEaten() > o2.getLeavesEaten()) {
					return +1;
				} else {
					return 0;
				}
			}
		};

		this.butterpillars = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			IButterpillar b = ButterpillarSortingTest.implementation.newInstance();
			b.setLength(rng.nextDouble());
			b.setLeavesEaten(rng.nextDouble());
			this.butterpillars.add(b);
		}
	}

	@After
	public void after() {
		this.rng = null;
		this.comparator = null;
		this.butterpillars.clear();
		this.butterpillars = null;
	}

	@Test
	public void testCompare() {
		List<IButterpillar> comparatorSorted = new ArrayList<>(this.butterpillars);
		List<IButterpillar> comparableSorted = new ArrayList<>(this.butterpillars);

		Collections.sort(comparatorSorted, this.comparator);
		Collections.sort(comparableSorted);

		for (int i = 0; i < this.butterpillars.size(); i++) {
			IButterpillar expected = comparatorSorted.get(i);
			IButterpillar actual = comparableSorted.get(i);
			Assert.assertEquals("Sort order differed at index " + i + " with expected Butterpillar [" + expected
					+ "] and actual Butterpillar [" + actual + "]", expected, actual);
		}

	}

}
