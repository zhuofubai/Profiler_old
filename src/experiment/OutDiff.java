package experiment;

public class OutDiff {
	double truth;
	double result;
	double diff;
	public OutDiff() {
		truth=0;
		result=0;
		diff=0;
		// TODO Auto-generated constructor stub
	}
	public void setTruth(double t){
		truth=t;
	}
	public void setResult(double r){
		result=r;
	}
	public void setDiff(double d){
		diff=d;
	}
	public double getTruth(){
		return truth;
	}
	public double getResult(){
		return result;
	}
	public double getDiff(){
		return diff;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
