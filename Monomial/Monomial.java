package Monomial;
import Scalar.Scalar;

public class Monomial {
	private Scalar coe;
	private int exp;

	public Monomial(Scalar coe, int exp) { //require ctor
		this.coe = coe;
		this.exp = exp;
	}
	public int GetExp() {
		return this.exp;
	} //receive the exp

	public boolean isMatch(Monomial m) {
		return (this.coe.isMatch(m.coe));
	} //return true if coe is scalar or false else

	public boolean canAdd(Monomial m) {
		return (isMatch(m) && this.exp == m.exp);
	} //check if they the same type

	public Monomial add(Monomial m) { //add monomial to another
		if (canAdd(m)) {
			return new Monomial(m.coe.add(this.coe), exp);
		}
		return null;
	}

	public Monomial mul(Monomial m) { //multiply monomial to another
		if(m.coe.isMatch(this.coe))
			return new Monomial(m.coe.mul(this.coe), m.exp+this.exp);
		return null;

	}

	public Scalar evaluate(Scalar scalar) { //input the current coe into polynomial
		if(!scalar.isMatch(this.coe))
			return null;
		if(exp==0) {
			scalar=this.coe;
			return scalar;
		}
		return scalar.mul(this.coe);

	}

	public Monomial derivative() { //derivative the polynomial by 1
		if(this.exp-1<0)
			return new Monomial (this.coe.add(this.coe.neg()),0);
		else {
			return new Monomial(this.coe.mul(this.exp),exp-1);
		}
	}

	public int sign() { //check if positive or negative and return 1 or -1 in order
		return  this.coe.sign();
	}

	public String toString() { //print the monomial
		if(this.coe.checkZero())
			return "";
		if(this.exp==0) {
			return this.coe.toString();
		}
		if(this.exp>1&&this.coe.divide()) {
			String p = this.coe.signStr();
			return p+"x^" + this.exp;
		}
		if((this.exp==1)&&(this.coe.divide()))
		{   String p = this.coe.signStr();
			return p + "x" ;
		}

		if(this.exp==1)
			return this.coe.toString() + "x";
		return  (this.coe.toString()+ "x^"+ this.exp);
	}
}
 


