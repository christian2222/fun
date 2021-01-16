package ecc;

public class Real extends Field<Double> {

	@Override
	public Double getNewElement() {
		// TODO Auto-generated method stub
		return Double.valueOf(1);
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

	

}
