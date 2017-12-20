
public class Complex {
	
	private final static int FAUX_INFTY = 30;
	public final static int[] ITER = {5, 7, 12, 15, 20, 50};
	
	double re;
	double im;
	
	int divergenceClass;
	
	public Complex(double re, double im) {
		this.re=re;
		this.im=im;
	}
	public Complex(Complex z) {
		re=z.re;
		im=z.im;
	}
	
	public static Complex mult(Complex z1, Complex z2) {
		
		double re1=z1.re;
		double im1=z1.im;
		double re2=z2.re;
		double im2=z2.im;
		
		double f = re1*re2;
		double o = re1*im2;
		double i = re2*im1;
		double l = im1*im2;
		
		
		
		return new Complex(f-l, o+i);
		
	}
	
	
	public static Complex power(Complex z, int n){
		
		if(n==0) {return new Complex(1, 0);}
		return mult(z, power(z, n-1));
		
	
		
	}
	
	public static Complex add(Complex z1, Complex z2) {
		double re1=z1.re;
		double im1=z1.im;
		double re2=z2.re;
		double im2=z2.im;
		
		return new Complex(re1+re2, im1+im2);
	}
	
	public double norm() {
		return re*re + im*im;
	}
	
	public String toString() {
		return ""+re+" + "+im+"i";
	}
	
	public static Complex quadMap(Complex z, Complex mapConstant) {
		
		return Complex.add(Complex.power(z, 2), mapConstant);
		
	}
	
	public static Complex iterateQuadMap(Complex z, Complex mapConstant, int iter) {
		Complex z1=new Complex(z);
		for(int i=1; i<iter; i++) {
			z1=quadMap(z1, mapConstant);
			if((z1.norm()>1&&z1.norm()<=1)) {z1.divergenceClass = (int)(7 - Math.sqrt(Math.sqrt(i))/35);}
		}
		return z1;
	}
	public static Complex quadBarMap(Complex z, Complex mapConstant) {
		
		return Complex.add(Complex.power(conj(z), 2), mapConstant);
		
	}
	
	public static Complex iterateQuadBarMap(Complex z, Complex mapConstant, int iter) {
		Complex z1=new Complex(z);
		for(int i=1; i<iter; i++) {
			z1=quadBarMap(z1, mapConstant);
			if((z1.norm()>1&&z1.norm()<=1)) {z1.divergenceClass = (int)(7 - Math.sqrt(Math.sqrt(i))/35);}
		}
		return z1;
	}
	
	public static Complex iterateQuadMapLogistic(Complex z, int iter) {
		Complex z1=new Complex(z);
		for(int i=0; i<iter; i++) {
			z1=quadMap(z1, z1);
		}
		return z1;
	}
	
	public static Complex cubicMap(Complex z, Complex mapSecOrder, Complex mapFirstOrder, Complex mapConstant) {
		
		Complex poly = power(z, 3);
		poly = add(poly, mult(mapSecOrder, power(z, 2)));
		poly = add(poly, mult(mapFirstOrder, z));
		poly = add(poly, mapConstant);
		
		return poly;
		
		
	}
	public static Complex generalizedPowerMapExperimental(Complex z, int exp, Complex mapConstant) {
		Complex poly = power(z, exp);
		return add(poly, mapConstant);
	}
	public static Complex iterateGeneralizedPowerMapExperimental(Complex z, int exp, Complex mapConstant, int iter) {
		Complex z1=new Complex(z);
		for(int i=0; i<iter; i++) {
			z1=generalizedPowerMapExperimental(z1, exp, mapConstant);
		}
		return z1;
	}
	public static Complex iterateCubicMap(Complex z, Complex mapSecOrder, Complex mapFirstOrder, Complex mapConstant, int iter) {
		Complex z1=new Complex(z);
		for(int i=0; i<iter; i++) {
			z1=cubicMap(z1, mapSecOrder, mapFirstOrder, mapConstant);
		}
		return z1;
		
	}
	
	public static Complex conj(Complex z) {
		return new Complex(z.re, z.im*-1);
	}
	
	


}
