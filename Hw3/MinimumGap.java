
/*
 * MinimumGap.java
 * 
 * @author: Rudresh Pandit(rmp7494)
 * @author: Niyati Shah(nxs6032)
 * 
 * Program to find the minimum gap interval scheduling using dynamic programming.
 */
import java.io.*;
import java.util.ArrayList;

public class MinimumGap {
	double start;
	double end;

	public MinimumGap(double start, double end) {
		/*
		 * Constructor to store the start and end points of the interval
		 */
		this.start = start;
		this.end = end;
	}

	// Sorting logic here
	public static void MergeSort(MinimumGap[] array) {
		int index_of_right = 0;
		if (array.length > 1) {
			int mid = array.length / 2;
			MinimumGap left[] = new MinimumGap[mid];
			MinimumGap right[] = new MinimumGap[array.length - left.length];
			for (int index = 0; index < mid; index++) {
				left[index] = array[index];
			}
			for (int index = mid; index < array.length; index++) {
				right[index_of_right] = array[index];
				index_of_right++;
			}
			MergeSort(left);
			MergeSort(right);
			int leftIndex = 0;
			int rightIndex = 0;
			int index_of_result = 0;
			while (leftIndex < left.length && rightIndex < right.length) {
				if (left[leftIndex].start < right[rightIndex].start) {
					array[index_of_result] = left[leftIndex];
					leftIndex++;
				} else {
					array[index_of_result] = right[rightIndex];
					rightIndex++;
				}
				index_of_result++;
			}
			while (leftIndex < left.length) {
				array[index_of_result] = left[leftIndex];
				leftIndex++;
				index_of_result++;
			}
			while (rightIndex < right.length) {
				array[index_of_result] = right[rightIndex];
				rightIndex++;
				index_of_result++;
			}
		}
	}
	// Sorting ends here

	public static double findMin(double[] S) {
		/*
		 * Function to find the minimum value in an array
		 */
		double min = S[0];
		for (int i = 1; i < S.length; i++) {
			if (S[i] < min) {
				min = S[i];
			}
		}
		return min;
	}

	// gap finding starts here
	public static double findMinGap(MinimumGap[] mg, double startInterval, double endInterval) {
		/*
		 * Function to find the minimum gap using dynamic programming
		 * 
		 * @param mg- An Array of MinimumGap type elements
		 * 
		 * @param startInterval - start interval
		 * 
		 * @param endInterval - end interval
		 */
		double current_wt, new_wt;
		double[] S = new double[mg.length + 1];
		double available_wt = endInterval - startInterval;
		ArrayList<Double> new_wt_array;
		S[0] = available_wt; // the first element is always the maximum weight
		for (int i = 1; i < mg.length + 1; i++) {
			current_wt = (mg[i - 1].end - mg[i - 1].start); // weight of current
															// interval
			boolean flag = false;
			new_wt_array = new ArrayList<>();
			for (int j = i - 2; j >= 0; j--) {
				/*
				 * loop for checking sequences which are compatible with current
				 * interval ( no overlapping )
				 */
				if (mg[i - 1].start >= mg[j].end) {
					/*
					 * The start time of current interval is greater than the
					 * end time of previous interval. So its compatible
					 */
					new_wt = S[j + 1] - current_wt;
					new_wt_array.add(new_wt);
					flag = true;
				}

			}
			if (flag == false) {
				/*
				 * If no elements are compatible with current, subtract it from
				 * max interval space available
				 */
				S[i] = available_wt - current_wt;
			} else {
				/*
				 * We find and add the interval which can form the largest
				 * sequence ( minimum gap )
				 */
				double[] anArray = new double[new_wt_array.size()];
				for (int count = 0; count < anArray.length; count++) {
					anArray[count] = new_wt_array.get(count);
				}
				double min = findMin(anArray);
				S[i] = min;
			}
		}
		double min = findMin(S);
		return min;

	}
	// gap finding ends here

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double no_of_ele, startInterval, endInterval;
		double start, end;
		no_of_ele = Double.parseDouble((br.readLine()));
		MinimumGap[] mg = new MinimumGap[(int) no_of_ele];

		String x = br.readLine();
		String[] y = x.split(" ");
		startInterval = Double.parseDouble(y[0]);
		endInterval = Double.parseDouble(y[1]);
		for (int i = 0; i < no_of_ele; i++) {
			String temp = br.readLine();
			String[] k = temp.split(" ");
			start = Double.parseDouble(k[0]);
			end = Double.parseDouble(k[1]);
			mg[i] = new MinimumGap(start, end);
		}

		// call the sort here
		MergeSort(mg);
		// call the minimum gap finding function here
		int finalvalue = (int) findMinGap(mg, startInterval, endInterval);
		// put the result in integer format
		System.out.println(finalvalue);

	}
}
