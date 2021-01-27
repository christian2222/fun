package ecc;

public class Real extends Field<Double> {

	@Override
	public Double getNewElement(int i) {
		// TODO Auto-generated method stub
		return Double.valueOf(i);
	}

	@Override
	public Double add(Double x, Double y) {
		// TODO Auto-generated method stub
		return x + y;
	}

	@Override
	public Double mult(Double x, Double y) {
		// TODO Auto-generated method stub
		return x * y;
	}

	@Override
	public Double invertAdd(Double x) {
		// TODO Auto-generated method stub
		return -x;
	}

	@Override
	public Double invertMult(Double x) {
		// TODO Auto-generated method stub
		if(this.isZero(x)) throw new IllegalArgumentException("Division by 0");
		return 1/x;
	}

	@Override
	public boolean isZero(Double x) {
		// TODO Auto-generated method stub
		return x.doubleValue() == 0.0;
	}

	@Override
	public boolean isField() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isGreaterEqualZero(Double x) {
		// TODO Auto-generated method stub
		return x.doubleValue() >= 0;
	}

	@Override
	public boolean isF2() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Double get2() {
		// TODO Auto-generated method stub
		return Double.valueOf(2);
	}

	@Override
	public boolean hasSquareRoot(Double x) {
		// TODO Auto-generated method stub
		return x.doubleValue() > 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "R";
	}

	@Override
	public Double squareRootOf(Double x) {
		// TODO Auto-generated method stub
		if(this.isGreaterEqualZero(x)) {
			return Double.valueOf(Math.sqrt(x.doubleValue()));
		}
		System.out.println("WARNING: No squareRootOf("+x.doubleValue()+") found!");
		return Double.valueOf(1);
	}



	

}
