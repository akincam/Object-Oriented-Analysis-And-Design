package part2;

/**
 * @author Akin Cam
 * Complex number includes real and imaginary.
 */
public class ComplexNumber {
    /**
     * real num
     */
    public double real;
    /**
     * imaginary side
     */
    public double imaginary;

    /**
     *Constructor
     */
    public ComplexNumber(){
        this.real = 0;
        this.imaginary   = 0;
    }

    /**
     *Constructor
     * @param reel num
     * @param img num
     */
    public ComplexNumber(double reel,double img){
        this.real = reel;
        this.imaginary   = img;
    }

    /**
     * Sets the complex number
     * @param complexNumber includes real and imaginary number
     */
    public void set(ComplexNumber complexNumber){
        this.real = complexNumber.real;
        this.imaginary = complexNumber.imaginary;
    }
    /**
     * Sums two Complex number and return it
     * @param complexNumber includes real and imaginary number
     * @return Complex Number
     */
    public ComplexNumber sum(ComplexNumber complexNumber){
        ComplexNumber complexNumber1 = new ComplexNumber();
        complexNumber1.real         = this.real+complexNumber.real;
        complexNumber1.imaginary    = this.imaginary+complexNumber.imaginary;
        return new ComplexNumber(complexNumber1.real,complexNumber1.imaginary);
    }

    /**
     * return complex number's real
     * @return real num
     */
    public double getReal() {
        return real;
    }

    /**
     *sets complex number's real
     * @param real num
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     *get complex number's imaginary
     * @return imaginary number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     *sets complex number's imaginary
     * @param imaginary number
     */
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }
}
