package matrix;

public class TbtMatrix {

	private double[][] matrix = new double[2][2];
	private double[][] matrix3 = new double[3][3];
	private double values[] = new double[4];
	private double values3[] = new double[9];
	private int type;

//	Constructors
	public TbtMatrix(double[][] matrix, double[] values) {
		if (matrix.length == 2) {
			this.matrix = matrix;
			this.type = 2;
			this.values = values;
		} else {
			this.matrix3 = matrix;
			this.type = 3;
			this.values3 = values;
		}

	}

//	Script
	public void calculation() {
		if (this.type == 2) {
			this.fillMatrix(this.matrix, this.values);

			System.out.println("Normal matrix");
			this.printMatrix(matrix);
			System.out.println();

			this.swap(matrix);
			double det = this.determinant(matrix);

			if (det != 0) {
				this.inverse(matrix, det);

				System.out.println("Inversed matrix");
				this.printMatrix(matrix);
			} else {
				System.out.println("Determinant is zero, inverse matrix doesn't exist.");
			}
		} else {
			this.fillMatrix(this.matrix3, this.values3);

			System.out.println("Normal matrix");
			this.printMatrix(this.matrix3);
			System.out.println();

			double det = this.determinant3(this.matrix3);

			if (det != 0) {
				this.matrix3 = this.minorMatrix(this.matrix3);
				this.cofactorMatrix(this.matrix3);

				System.out.println("Inverse matrix");
				this.inverse3(this.matrix3, det);
			} else {
				System.out.println("Determinant is zero, inverse matrix doesn't exist.");
			}

		}

	}

	private double[][] subMatrix(double[][] matrix, int[] point) {

		double[] values = new double[4];
		int c = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (i != point[0] && j != point[1]) {
					values[c++] = matrix[i][j];
				}
			}
		}

		double[][] sub = new double[2][2];
		this.fillMatrix(sub, values);

		return sub;
	}

//	Helpers 3x3 matricies
	private double determinant3(double[][] matrix) {
		double det = 0;
		double[][] sub = new double[2][2];
		int sign = 1;
		int[] point = { 0, 0 };

		for (int i = 0; i < 3; i++) {

			point[1] = i;
			sub = this.subMatrix(matrix, point);

			det += matrix[point[0]][point[1]] * this.determinant(sub) * sign;
			sign = sign == 1 ? -1 : 1;

		}

		return det;
	}

	private double[][] minorMatrix(double[][] matrix) {
		int point[] = new int[2];
		double values[] = new double[9];
		double[][] minorMatrix = new double[3][3];
		int c = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				point[0] = i;
				point[1] = j;
				double[][] sub = this.subMatrix(matrix, point);
				values[c++] = this.determinant(sub);
			}
		}

		this.fillMatrix(minorMatrix, values);
		return minorMatrix;
	}

	private double[][] cofactorMatrix(double[][] matrix) {
		int sign = 1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] *= sign;
				sign = sign == 1 ? -1 : 1;
			}
		}
		return matrix;
	}

	private void inverse3(double[][] matrix, double det) {
		int points[] = { 0, 3, 6, 1, 4, 7, 2, 5, 8 };
		double values[] = new double[9];
		int c = 0;
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				values[c++] = matrix[i][j] / det;
			}
		}
		for (int i = 0; i < 9; i++) {

			System.out.print(values[points[i]] + " ");

			count++;
			if (count == 3) {
				System.out.println();
				count = 0;
			}

		}

	}

//	Helpers 2x2 matrices
	private void swap(double[][] matrix) {
		double temp;
		temp = matrix[0][0];
		matrix[0][0] = matrix[1][1];
		matrix[1][1] = temp;

		matrix[0][1] *= -1;
		matrix[1][0] *= -1;
	}

	private double determinant(double[][] matrix) {
		return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
	}

//	Divide by determinant

	private void inverse(double[][] matrix, double det) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] /= det;
			}
		}
	}

//	Printing and Filling

	private void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void fillMatrix(double[][] matrix, double[] values) {
		int c = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = values[c++];
			}
		}
	}

}
