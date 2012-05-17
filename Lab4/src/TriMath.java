
public class TriMath extends MyMath {

	protected double sideA, sideB, sideC;
	
	/**
	 * 
	 * @param sideA
	 * @param sideB
	 * @param sideC
	 */
	public TriMath(double sideA, double sideB, double sideC) throws Exception {
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
		
		if (!validTriangle()) {
			throw new Exception("Invalid triangle lenghths. The sum of the length of every two sides must exceed the length of the third side.");
		}
	}
	
	/**
	 * Helper method for determining if a triangle is valid or not
	 * 
	 * @return
	 */
	private boolean validTriangle()
	{
		if ((this.sideA + this.sideB) < this.sideC) {
			return false;
		}
		
		if ((this.sideB + this.sideC) < this.sideA) {
			return false;
		}
		
		if ((this.sideC + this.sideA) < this.sideB) {
			return false;
		}
		
		return true;
	}
	
	public double getArea()
	{
		double s = (sideA + sideB + sideC) / 2;
		double subArea = s * (s - sideA) * (s - sideB) * (s - sideC);
		
		return Math.sqrt(subArea);
	}

	public double getPerimeter()
	{
		return sideA + sideB + sideC;
	}
	
	public double getAngleA()
	{
		return calulateAngle(sideA, sideB, sideC);		
	}
	
	public double getAngleB()
	{
		return calulateAngle(sideB, sideA, sideC);
	}
	
	public double getAngleC()
	{
		return calulateAngle(sideC, sideB, sideA);
	}
	
	/**
	 * Calculates the angle of side one. 
	 * 
	 * @param side1
	 * @param side2
	 * @param side3
	 * @return double The angle for side 1 in radians
	 */
	private double calulateAngle(double side1, double side2, double side3)
	{
		double side1Squared = Math.pow(side1, 2);
		double side2Squared = Math.pow(side2, 2);
		double side3Squared = Math.pow(side3, 2);
		
		double innerFormula = (side3Squared + side2Squared - side1Squared) / (2 * side2 * side3);
		
		return Math.acos(innerFormula);
	}
	
}
