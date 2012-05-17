public class MyMath {

	// *************************************************
	// * Round to arbitrary number of places (in JAVA).
	// * Last updated (efa) 9/12/09
	// *************************************************
	// ***********************************
	// num = 100.125;
	// num = Round(num,2);
	// The value of num will be 100.13
	// ***********************************
	public static double Round(double val, int plc) {
		double pwr = Math.pow(10, plc);
		val = val * pwr; // shift to the left
		double tmp = (int) val;

		if (((int) (val + .5)) == (int) val)
			return (tmp / pwr); // don't round up
		else
			return ((tmp + 1.0) / pwr); // round up
	}

	// isNumeric:: Check to make sure the string is numeric.
	// Written to us JAVA exceptions. (efa) 3/31/08
	// ***************************************************************
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (Exception e) {
			// Print out the exception that occurred
			// System.out.println(urlStr+": "+e.getMessage());

			return (false);
		}

		return (true);
	}
}
