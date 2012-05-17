

public class Classify extends TriMath {
	
	public Classify(double side1, double side2, double side3) throws Exception
	{
		super(side1, side2, side3);
	}
	
	public String getCalssification()
	{
		if (sideA == sideB && sideB == sideC && sideA == sideC) {
			return "Equilateral";
		} else if (sideA == sideB && sideB == sideC) {
			return "Isosceles";
		}
		
		return "Scalene";
	}
	
}
