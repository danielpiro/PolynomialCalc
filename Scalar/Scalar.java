package Scalar;

public interface Scalar {
	boolean isMatch(Scalar s);
	Scalar add(Scalar s);
	Scalar mul(Scalar s);
	Scalar mul(int i);
	Scalar power(int exp);
	Scalar neg();
	String toString();
	int sign();
	boolean divide();
	String signStr();
	boolean checkZero();
}
