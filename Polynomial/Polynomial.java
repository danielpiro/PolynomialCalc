package Polynomial;
import java.util.TreeMap;
import Scalar.RationalScalar;
import Scalar.RealScalar;
import Scalar.Scalar;
import Monomial.Monomial;

public class Polynomial {
	private TreeMap<Integer, Monomial> monomials;

	public Polynomial() {
		this.monomials = new TreeMap<Integer, Monomial>();
	}

	public Polynomial build(char type, String input) {
		if (type == 'R')
			realScalarBuilder(input);
		else
			rationalScalarBuilder(input);
		return this;
	}

	public void realScalarBuilder(String input) {// build function to monomial type of realscalar
		int exp = 0;
		for (String word : input.split(" ")) {
			if(word.isBlank())
				continue;
			var convertNum = Double.parseDouble(word);
			monomials.put(exp, new Monomial(new RealScalar(convertNum), exp));
			exp++;
		}
	}
	public void rationalScalarBuilder(String input) {// build function to monmial type of rationalscalar
		int exp = 0;
		for (String word : input.split(" ")) { // we make spilt from the input of " "
			if(word.isBlank())
				continue;
			if (word.charAt(0) != '0') {
				int b = 1;
				int a;
				if (word.contains("/")) { // if the number fraction
					a = Integer.parseInt(word.substring(0, word.indexOf("/")));
					b = Integer.parseInt(word.substring(word.indexOf("/")+1));
				} else
					a = Integer.parseInt(word); // if the number is not fraction
				monomials.put(exp, new Monomial(new RationalScalar(a, b), exp));
			}
			exp++;
		}
	}

	public boolean isMatch(Polynomial p) {
		if (monomials.isEmpty() | p.monomials.isEmpty())// if one is empty we can return true
			return true;
		if (monomials.firstEntry().getValue().isMatch(p.monomials.firstEntry().getValue()))
			return true;
		return false;
	}

	public Polynomial add(Polynomial p) {
		if (!isMatch(p)) // we check if the poly match else we return null
			return null;
		var monomials = p.monomials; // we save the treemap from p
		var integerSet = monomials.keySet();// we save all the keys
		var integers = this.monomials.keySet();
		var iterator = integers.iterator();
		var iterator1 = integerSet.iterator();
		var polynomial= new Polynomial();
		while (iterator1.hasNext()) {// run over all the p keys and add it to our treemap collection
			int x = iterator1.next();
			if (this.monomials.get(x) == null)
				polynomial.monomials.put(x, monomials.get(x)); // if that key not in out monomial
			else {
				polynomial.monomials.put(x, this.monomials.get(x).add(monomials.get(x)));
			}
		}
		while (iterator.hasNext()) {
			int h=iterator.next();
			if(polynomial.monomials.get(h)==null)
				polynomial.monomials.put(h, this.monomials.get(h));
		}
		return polynomial;
	}
	public void addMonomial(Monomial m) {
		int exp = m.GetExp();
		if (this.monomials.get(exp) == null) // i check if there monomial with exp like the mono we get
			this.monomials.put(exp, m);
		else
			this.monomials.put(exp, this.monomials.get(exp).add(m));
	}
	public Polynomial mul(Polynomial p) { //multiply polynomial by another
		var monomials = p.monomials;
		var integerSet = monomials.keySet();
		var iterator = integerSet.iterator();
		var integers = this.monomials.keySet();
		var iterator1 = integers.iterator();
		var newPolynomial = new Polynomial();
		while (iterator1.hasNext()) {
			var nextMonomial = this.monomials.get(iterator1.next());
			while (iterator.hasNext()) {
				var prevMonomial = monomials.get(iterator.next());
				newPolynomial.addMonomial(nextMonomial.mul(prevMonomial));
			}
			iterator = integerSet.iterator();
		}
		return newPolynomial;
	}
	public Scalar evaluate(Scalar scalar) { //input the current scalar into to polynomial and calculate it
		var integers=this.monomials.keySet();
		var iterator=integers.iterator();
		Scalar scalar1;
		if(scalar instanceof RealScalar)
			scalar1=new RealScalar(0);
		else
			scalar1=new RationalScalar(0,1);
		while(iterator.hasNext()){
			int next=iterator.next();
			scalar1=scalar1.add(this.monomials.get(next).evaluate(scalar));
		}
		return scalar1;
	}
	public Polynomial derivative() { //derivative the polynomial by 1
		var polynomial=new Polynomial();
		var integerSet=this.monomials.keySet();
		var iterator=integerSet.iterator();
		while(iterator.hasNext()){
			polynomial.addMonomial(this.monomials.get(iterator.next()).derivative());
		}
		return polynomial;
	}
	public String toString(){ //print the polynomial
		var integerSet=this.monomials.keySet();
		var iterator=integerSet.iterator();
		String Result="";
		while(iterator.hasNext()){
			int next=iterator.next();
			Result+=this.monomials.get(next).toString();
			if(!Result.equals("")){
				if(iterator.hasNext()){
					int tNext=iterator.next();
					if(this.monomials.get(tNext).toString().length()!=0){
						if(this.monomials.get(tNext).toString().charAt(0)!='-') {
							Result+="+";
						}
					}
					iterator=integerSet.iterator();
					while(iterator.next()!=next);
				}
			}
		}
		if(Result.equals(""))
			return "0";
		return Result;
	}
}

