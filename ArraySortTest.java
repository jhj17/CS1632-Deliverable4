/*
 * Jeffrey Josephs
 * jhj17
 * CS 1632 Deliverable 4
 * PROPERTY-BASED TESTING
 */

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.*;

// JUnit tests that will test properties of array sort function
public class ArraySortTest {

	@Test
	// Test array sort property that array will be the same
	// size after being passed to the sort method
	public void testArraySameSize() {
		int[][] arrs = generateRandomArrays();
		
		// loop through all arrays
		for(int index = 0; index < arrs.length; index++) {
			int[] testArr = arrs[index];
			int[] preSortedArray = new int[testArr.length];
	
			// create copy for comparison
			System.arraycopy(testArr,0,preSortedArray,0, testArr.length );
	
			// pass array to Arrays.sort
			Arrays.sort(testArr);
	
			// check to make sure length has not changed
			assertEquals(testArr.length, preSortedArray.length);
		}
	}

	@Test
	// Test array sort property that sorted array will contain
	// all elements in original array
	public void testArraySameContents () {
		int[][] arrs = generateRandomArrays();
		
		// loop through all arrays
		for(int index = 0; index < arrs.length; index++) {	
			int[] testArr = arrs[index];
			int[] preSortedArray = new int[testArr.length];
			
			// create copy for comparison
			System.arraycopy(testArr,0,preSortedArray,0, testArr.length );
			
			// pass array to Arrays.sort
			Arrays.sort(testArr);
			
			// check that all elements in sorted array are in original array
			for(int i = 0; i < preSortedArray.length; i++) {
				if(Arrays.binarySearch(testArr, preSortedArray[i]) < 0) {
					fail("testArraySameContents");
				}
			}
		}
	}
	
	@Test
	// Test array sort property that sorted array will not
	// change after being passed to sort method again
	public void testArrayIdempotent () {
		int[][] arrs = generateRandomArrays();
		
		// loop through all arrays
		for(int index = 0; index < arrs.length; index++) {
			int[] testArr = arrs[index];
			int[] preSortedArray = new int[testArr.length];
	
			// pass array to Arrays.sort
			Arrays.sort(testArr);
	
			// create copy for comparison
			System.arraycopy(testArr,0,preSortedArray,0, testArr.length );
			
			// pass sorted array to Arrays.sort again
			Arrays.sort(testArr);
			
			// check to make sure contents have not changed
			assertArrayEquals(preSortedArray, testArr);
		}
	}

	@Test
	// Test array sort property running it twice on 
	// same input array should always result in same output array
	public void testArrayPure() {
		int[][] arrs = generateRandomArrays();
		
		// loop through all arrays
		for(int index = 0; index < arrs.length; index++) {
			int[] testArr = arrs[index];
			int[] preSortedArray = new int[testArr.length];
	
			// create copy for comparison
			System.arraycopy(testArr,0,preSortedArray,0, testArr.length );
			
			// pass array to Arrays.sort
			Arrays.sort(testArr);
			
			// pass copy of array to Arrays.sort
			Arrays.sort(preSortedArray);
			
			// check that output of both sorted arrays are equal
			assertArrayEquals(preSortedArray, testArr);
		}
	}

	/*
	 * Function to generate up to 100 arrays with array
	 * of random size and random contents (max size 1000)
	 * @return 2-D jagged array with random size and random elements
	 */
	public static int[][] generateRandomArrays() {
		Random rand = new Random();
		int numArrays = rand.nextInt(100);
		int[][] arrs = new int[numArrays][];

		// create array with x number of integer arrays
		for(int i = 0; i < numArrays; i++) {
			// create sub-array with up to 1000 elements
			int arraySize = rand.nextInt(500);  
			arrs[i] = new int[arraySize];

			// fill array with number between 0 and 10000
			for(int j = 0; j < arraySize; j++) {
				arrs[i][j] =  rand.nextInt(1000);
			}
		}

		return arrs;
	}

}

