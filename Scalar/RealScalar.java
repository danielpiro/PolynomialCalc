package Scalar;

import java.text.DecimalFormat;

public class RealScalar implements Scalar{
	private double v;
	public RealScalar(double v){ //ctor
		this.v=v;
	}
	public double getV(){ //getter
		return this.v;
	}
	public Scalar add(Scalar s) {//add scalar to another
		if(!(s instanceof RealScalar))
			return null;
		var realScalar=new RealScalar(this.v+((RealScalar)s).v);
		return realScalar;
	}

	public Scalar mul(Scalar s) { //multiply scalar to another
		if(!(s instanceof RealScalar))
			return null;
		var mulScalar=new RealScalar(this.v);
		mulScalar.v*=((RealScalar)s).v;
		return mulScalar;
	}
	public Scalar mul(int i) {//multiply the scalar by the input
		return new RealScalar(this.v*i);

	}
	public Scalar power(int exp) {//raise the number by the input
		this.v= Math.pow(this.v, exp);
		return this;
	}
	public int sign() {//check if positive or negative and return 1 or -1 by the same order
		return this.v>=0?1:-1;

	}
	public Scalar neg() {//return the negative of the number
		return new RealScalar(this.v*(-1));
	}
	public boolean isMatch(Scalar s) {
		return (s instanceof RealScalar) ;
	}
	public String toString(){
		if(this.v==(int)this.v)
			if(this.v==0)
				return  (int)(this.v)+"";
		var decimalFormat=new DecimalFormat("#.###");
		return decimalFormat.format(getV()).replaceAll("\\.000$", "");
	}
	public boolean divide() {
		return(this.v==1.0||this.v==-1.0);
	}
	public String signStr()
	{if(this.v==-1.0)
		return "-";
		return "";
	}
	public boolean checkZero() {
		return(this.v==0);
	}

}


