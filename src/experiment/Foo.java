package experiment;

import java.util.Random;

public class Foo {

	public Foo() {
		// TODO Auto-generated constructor stub
	}

	public double createSmallNumber() {
		double a = Math.random();
		double r = Math.random();
		//int u = r.nextInt();
		double result = a / Math.pow(10, r*20);
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		Foo foo = new Foo();
		for (int i = 0; i < 1000; i++) {

			double result = foo.createSmallNumber();
			if (result < 1E-13) {
				count++;
			}
		}
		System.out.println(count);
	}

}
