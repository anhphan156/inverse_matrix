package matrix;

public class Main {

	public static void main(String[] args) {
		double [][] matrix = new double [3][3];
		double values [] = {1,0,0,1,1,2,4,5,0};
		
		TbtMatrix a = new TbtMatrix(matrix,values);
		
		a.calculation();
		
		

	}
	
	

}
