package experiment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.math.linear.EigenDecomposition;
import org.apache.commons.math.linear.EigenDecompositionImpl;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.EigenDecompositionImpl_bug;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.util.MathUtils;

import edu.cwru.eecs.gang.faultlocalization.expressionvalue.profiler.Profiler;

public class TestEigenDecomposition {
	
	public static Random r = new Random(17899l);
	//public static int upper = 999;
	static double tolerance=1E-12;
	public static int ncover;

	public TestEigenDecomposition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
//	public static int testOut(double[] truth, double[] result) {
//		//double tolerance = 1E-12;// 5E-2;
//		if (truth.length != result.length) {
//			System.out
//					.println("two inputs' dimension is mismatch, the result length is: "
//							+ result.length);
//			return 1;
//		}
//		double sum = 0;
//		double sum2 = 0;
//		for (int i = 0; i < truth.length; i++) {
//			sum = sum + Math.abs(Math.abs(truth[i]) - Math.abs(result[i]));
//			sum2 = sum2 + Math.abs(truth[i]);
//		}
//		double ratio = sum / sum2;
//
//		if (ratio > tolerance) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
	public static int testOut(double[] truth, double[] result) {
		//double tolerance = 1E-12;

		if (truth.length != result.length) {
			System.out
					.println("two inputs' dimension is mismatch, the result length is: "
							+ result.length);
			return 1;
		}

		double diff = 0;
		for (int i = 0; i < truth.length; i++) {

			diff = Math.abs(truth[i] - result[i]);
			if (diff > tolerance) {
				
				//System.out.println(i);
				return 1;
			}

		}
		return 0;
	}
//	public void testBigMatrix() {
//		Random r = new Random(17748333525117l);
//		double[] bigValues = new double[200];
//		for (int i = 0; i < bigValues.length; ++i) {
//			bigValues[i] = 2 * r.nextDouble() - 1;
//		}
//		Arrays.sort(bigValues);
//		EigenDecomposition ed = new EigenDecompositionImpl(createTestMatrix(r,
//				bigValues), MathUtils.SAFE_MIN);
//		double[] eigenValues = ed.getRealEigenvalues();
//		// assertEquals(bigValues.length, eigenValues.length);
//		// for (int i = 0; i < bigValues.length; ++i) {
//		// assertEquals(bigValues[bigValues.length - i - 1], eigenValues[i],
//		// 2.0e-14);
//		// }
//	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Profiler.visitNewTest(-1);
		try {
			int count = 0;
			int count2=0;
			String filedir = "TestEigenDecomposition/out";
			String filename = filedir + "/" + "out.txt";
			String truthFile = filedir + "/" + "truth.txt";
			String resultFile= filedir + "/" + "result.txt";
			String diffFile=filedir + "/" + "diff.txt";
			String diffFile2=filedir+"/"+"diff2.txt";
			BufferedWriter out, Twriter, Rwriter,Dwriter,Dwriter2;
			out = new BufferedWriter(new FileWriter(filename, false));
			Twriter = new BufferedWriter(new FileWriter(truthFile, false));
			Rwriter =  new BufferedWriter(new FileWriter(resultFile, false));
			Dwriter =  new BufferedWriter(new FileWriter(diffFile, false));
			Dwriter2= new BufferedWriter(new FileWriter(diffFile2, false));
			
			//Random r2=new Random();
			for (int i = 0; i < 3000; i++) {
				Profiler.visitNewTest(i);
				double[] bigValues = new double[3+r.nextInt(5)];
				for (int j = 0; j < bigValues.length; ++j) {
					bigValues[j] = 2 * r.nextDouble() - 1;
				}
				Arrays.sort(bigValues);
				RealMatrix matrix = createTestMatrix(r, bigValues);
				// RealMatrix matrix = MatrixUtils.createRealMatrix(new
				// double[][] {
				// { -0.5608, -0.2016, 0.1152, -0.2976 },
				// { -0.2016, 0.4432, -0.2304, 0.1152 },
				// { 0.1152, -0.2304, 0.3088, -0.1344 },
				// { -0.2976, 0.1152, -0.1344, 0.3872 }
				// });
				// for (int j = 0; j < mainTridiagonal.length-1; ++j) {
				// mainTridiagonal[j] = Math.random()-0.5;
				// secondaryTridiagonal[j]=Math.random()-0.5;
				// }
				// mainTridiagonal[mainTridiagonal.length-1]=-30+Math.random()*10;
				// secondaryTridiagonal[secondaryTridiagonal.length-1]=Math.random()*0.001;
				try {
					EigenDecomposition ed2 = new EigenDecompositionImpl(matrix,
							MathUtils.SAFE_MIN);
					EigenDecompositionImpl_bug ed = new EigenDecompositionImpl_bug(
							matrix, MathUtils.SAFE_MIN);

//					double[] result = ed.getRealEigenvalues();
//					double[] truth = ed2.getRealEigenvalues();
					double []result =ed.getEigenvector(1).getData();
					double []truth =ed2.getEigenvector(1).getData();

					int yout = testOut(truth, result);
					OutDiff outdiff=getDiff(truth,result);
					OutDiff outdiff2=getDiff2(truth,result);
					double truth1=outdiff.getTruth();
					double result1=outdiff.getResult();
					double diff=outdiff.getDiff();
					double diff2=outdiff2.getDiff();
					if (yout == 1) {
						count++;
					}
					out.write(i + " " + yout);
					out.write("\n");
					out.flush();
					
					Twriter.write(truth1 + " ");
					Twriter.write("\n");
					Twriter.flush();
					
					Rwriter.write(result1 + " ");
					Rwriter.write("\n");
					Rwriter.flush();
					

					Dwriter.write(diff +" ");
					Dwriter.write("\n");
					Dwriter.flush();
					
					Dwriter2.write(diff2 +" ");
					Dwriter2.write("\n");
					Dwriter2.flush();
					// transformer_bug.log.setTestId(i);ffffff
					count2++;
				} catch (org.apache.commons.math.linear.InvalidMatrixException e ) {

					//System.out.println("invalid matrix");
				}catch(java.lang.ArrayIndexOutOfBoundsException e){
					
				}
			}
			//
			// EigenDecomposition ed2 = new
			// EigenDecompositionImpl(mainTridiagonal,secondaryTridiagonal,
			// MathUtils.SAFE_MIN);
			// EigenDecompositionImpl_bug ed = new
			// EigenDecompositionImpl_bug(mainTridiagonal,secondaryTridiagonal,
			// filedir,MathUtils.SAFE_MIN,i);
			System.out.println("count is :" + count+"  count2 is"+count2);
			System.out.println("ncover is "+ncover);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static RealMatrix createTestMatrix(final Random r,
			final double[] eigenValues) {
		final int n = eigenValues.length;
		final RealMatrix v = createOrthogonalMatrix(r, n);
		final RealMatrix d = createDiagonalMatrix(eigenValues, n, n);
		return v.multiply(d).multiply(v.transpose());
	}

	public static RealMatrix createOrthogonalMatrix(final Random r,
			final int size) {

		final double[][] data = new double[size][size];

		for (int i = 0; i < size; ++i) {
			final double[] dataI = data[i];
			double norm2 = 0;
			do {

				// generate randomly row I
				for (int j = 0; j < size; ++j) {
					dataI[j] = 2 * r.nextDouble() - 1;
				}

				// project the row in the subspace orthogonal to previous rows
				for (int k = 0; k < i; ++k) {
					final double[] dataK = data[k];
					double dotProduct = 0;
					for (int j = 0; j < size; ++j) {
						dotProduct += dataI[j] * dataK[j];
					}
					for (int j = 0; j < size; ++j) {
						dataI[j] -= dotProduct * dataK[j];
					}
				}

				// normalize the row
				norm2 = 0;
				for (final double dataIJ : dataI) {
					norm2 += dataIJ * dataIJ;
				}
				final double inv = 1.0 / Math.sqrt(norm2);
				for (int j = 0; j < size; ++j) {
					dataI[j] *= inv;
				}

			} while (norm2 * size < 0.01);
		}

		return MatrixUtils.createRealMatrix(data);

	}
    public static double createSmallNumber() {
		double a = r.nextDouble();
		double b = r.nextDouble();
		double c=r.nextDouble();
		double number= (a - 0.5)*Math.pow(10,c*2) / Math.pow(10, b * 10);
		//System.out.println(number);
		return number;
		//return a;
	}
	public static RealMatrix createDiagonalMatrix(final double[] diagonal,
			final int rows, final int columns) {
		final double[][] dData = new double[rows][columns];
		for (int i = 0; i < Math.min(rows, columns); ++i) {
			dData[i][i] = diagonal[i];
		}
		return MatrixUtils.createRealMatrix(dData);
	}
	
    public static OutDiff getDiff(double[] truth, double[] result){
    	double diff=0;
    	OutDiff outdiff=new OutDiff();
    	for(int i=0;i<truth.length;i++){
    		diff = Math.abs(truth[i] - result[i]);
    		if(diff>tolerance){
    			outdiff.setTruth(truth[i]);
    			outdiff.setDiff(diff);
    			outdiff.setResult(result[i]);
    			return outdiff;    			
    		}
    	}
    	return outdiff;
    }
	
	public static OutDiff getDiff2(double[] truth, double[] result){
    	double diff=0;
    	double sumd=0,sumt=0,sumr=0;
    	OutDiff outdiff=new OutDiff();
    	for(int i=0;i<truth.length;i++){
    		diff = truth[i] - result[i];
    		sumd=sumd+diff*diff;
    		sumt=sumt+truth[i]*truth[i];
    		sumr=sumr+result[i]*result[i];
    	}
    	
    	outdiff.setDiff(Math.sqrt(sumd));
    	outdiff.setResult(Math.sqrt(sumr));
    	outdiff.setTruth(Math.sqrt(sumt));
    	return outdiff;
    }
	
}
