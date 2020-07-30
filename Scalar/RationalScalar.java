package Scalar;
public class RationalScalar implements Scalar {
	private int a;
	private int b;

	public RationalScalar(int a,int b){ //ctor
		this.a=a;
		this.b=b;
	}
	public RationalScalar(int a){ //ctor
		this.a = a;
		this.b=1;
	}
	public Scalar add(Scalar s) { //add scalar to another
		if(!(s instanceof RationalScalar))
			return null;
		var addScalar=(RationalScalar)s;
		return new RationalScalar((this.a*addScalar.b)+(this.b*addScalar.a), this.b*addScalar.b);
	}

	public Scalar mul(Scalar s) { //multiply scalar to another
		if(!(s instanceof RationalScalar))
			return null;
		var mulScalar=((RationalScalar)s);
		var newScalar=new RationalScalar(this.a,this.b);
		newScalar.a=(newScalar.a*mulScalar.a);
		newScalar.b=(newScalar.b*mulScalar.b);
		return newScalar;
	}
	public Scalar mul(int i) { //multiply the scalar by the input
		return new RationalScalar(this.a*i,this.b);
	}

	public Scalar neg() { //return the negative of the number
		return new RationalScalar(a*(-1), b);
	}
	public int sign() { //check if positive or negative and return 1 or -1 by the same order
		if (this.a*this.b<0)
		return -1;
		return 1;

	}
	public Scalar power(int exp) { //raise the number by the input
		int k=this.a;
		int j=this.b;
		for(int i=0;i<exp;i=i+1) {
			k*=k;
			j*=j;
		}
		return new RationalScalar(k,j);
	}
	public boolean isMatch(Scalar s) {
		return (s instanceof RationalScalar) ;
	}
	public String toString(){ //print rational scalar
		if(a!=0){
			if(this.b==1)
				return this.a+"";
			if(a%b==0)
				return (this.a/this.b)+ "";
			else
			if(this.a<0&&this.b<0)
				return "("+ -this.a + "/" + -this.b+")";
			else
			if(this.a>0&&this.b<0)
				return "("+ -this.a + "/" + -this.b+")";
			else
				return "("+this.a + "/" + this.b+")";

		}
		return "0";
	}

	public boolean divide() { //divide the numbers
		if(this.a/this.b==1||this.a/this.b==-1)
			return true;
		return false;
	}
	public String signStr() //check if the numbers negative and produce the correct sign
	{if (this.a/this.b==-1)
		return "-";
		return "";
	}
	public boolean checkZero() { //check if the number is zero and return true
		return (a==0);
	}

}