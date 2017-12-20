import javax.swing.*;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/*
 * Skeleton GUI code courtesy of https://stackoverflow.com/questions/3325546/how-to-color-a-pixel
 * StackOverflow user: I82Much
 * 
 * Algorithmic code and Set-Generating Methods by A. Anakru
 * 
 * Rich mathematical theory due to B. Mandelbrot, P. Fatou, G. Julia, A. Douady, J. H. Hubbard
 * 
 */

public class MandelbrotView extends JPanel implements ActionListener{

	//This generates the Douady Rabbit. (meow)
	public static final Complex DOUADY_C = new Complex(-0.12256, 0.74486);
	
	public  final JButton button = new JButton("Refresh");
	public  final JTextField real = new JTextField(9);
	public  final JTextField imag = new JTextField(9);

	private final int DIMENSION_X = 1500;
	private final int DIMENSION_Y = 1000;
	private final int SCALE_FACTOR = 410;
	
	private final double COMPLEX_DIM_X = DIMENSION_X/SCALE_FACTOR;
	private final double COMPLEX_DIM_Y = DIMENSION_Y/SCALE_FACTOR;
	private final double COMPLEX_PLANE_AREA = COMPLEX_DIM_X*COMPLEX_DIM_Y;
	
	
    private BufferedImage canvas;
    
    private double current_seed_real;
    private double current_seed_imag;
    
    
    private Color[] colors = {Color.BLACK ,Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.MAGENTA};

    public MandelbrotView() {
        canvas = new BufferedImage(DIMENSION_X, DIMENSION_Y, BufferedImage.TYPE_INT_ARGB);
       
      
       
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
     
        System.out.println(generateMandelbrotSet( new Complex(0, 0)));
    }
    
    
    /***
     * Generates, depending on method body, the Fatou Dust/Julia Set corresponding to a complex parameter. 
     * Generates a "deformed" Mandelbrot set if z !=0.
     * Returns the area of the Fatou Dust as a double (needs debugging)
     */
    public double generateMandelbrotSet(Complex z) {
    	
    	Color red = Color.RED;
    	
    	for(int x=0; x<DIMENSION_X; x++) {
    		for(int y=0; y<DIMENSION_Y; y++) {
    			canvas.setRGB(x, y, red.getRGB());
    		}
    	}

    	int pixel_count=0;
    for(int x=0; x<DIMENSION_X; x++) {
    		for(int y=0; y<DIMENSION_Y; y++) {
    			
    			double x_coord = x-800;
    			double y_coord = 475-y;
    			
    		
    			
    			Complex coord = new Complex( x_coord /SCALE_FACTOR , y_coord / SCALE_FACTOR     );
    			
    			
    			for(int i=0; i<Complex.ITER.length; i++) {
    			Complex res = Complex.iterateQuadMap(z, coord, Complex.ITER[i]); //this is the regular mandelbrot set for z=0, else deformed
    			if(res.norm() < 5 ) {  canvas.setRGB(x, y, colors[5-i].getRGB()); if(i==Complex.ITER.length-1) {pixel_count++;} }
    			}
    			
    		}
    }
    return ((double)pixel_count/((double)DIMENSION_X*(double)DIMENSION_Y))*(COMPLEX_PLANE_AREA);
    	
    	
    }
    
public double generateJuliaSet(Complex z) {
    	
    	Color red = Color.RED;
    	
    	for(int x=0; x<DIMENSION_X; x++) {
    		for(int y=0; y<DIMENSION_Y; y++) {
    			canvas.setRGB(x, y, red.getRGB());
    		}
    	}

    	int pixel_count=0;
    for(int x=0; x<DIMENSION_X; x++) {
    		for(int y=0; y<DIMENSION_Y; y++) {
    			
    			double x_coord = x-800;
    			double y_coord = 475-y;
    			
    		
    			
    			Complex coord = new Complex( x_coord /SCALE_FACTOR , y_coord / SCALE_FACTOR     );
    			
    			
    			for(int i=0; i<Complex.ITER.length; i++) {
    			Complex res = Complex.iterateQuadMap(coord, z, Complex.ITER[i]); 
    			if(res.norm() < 5 ) {  canvas.setRGB(x, y, colors[5-i].getRGB()); if(i==Complex.ITER.length-1) {pixel_count++;} }
    			}
    			
    		}
    }
    return ((double)pixel_count/((double)DIMENSION_X*(double)DIMENSION_Y))*(COMPLEX_DIM_X*COMPLEX_DIM_Y);
    	
    	
    }

public double generateCubicSet(Complex mapSecOrder, Complex mapFirstOrder, Complex mapConstant) {
	Color red = Color.RED;
	
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			canvas.setRGB(x, y, red.getRGB());
		}
	}

	int pixel_count=0;
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			
			double x_coord = x-800;
			double y_coord = 475-y;
			
		
			
			Complex coord = new Complex( x_coord /SCALE_FACTOR , y_coord / SCALE_FACTOR     );
			
			
			for(int i=0; i<Complex.ITER.length; i++) {
			Complex res = Complex.iterateCubicMap(coord, mapSecOrder, mapFirstOrder, mapConstant, Complex.ITER[i]); 
			if(res.norm() < 5 ) {  canvas.setRGB(x, y, colors[5-i].getRGB()); if(i==Complex.ITER.length-1) {pixel_count++;} }
			}
			
		}
}
return ((double)pixel_count/((double)DIMENSION_X*(double)DIMENSION_Y))*(COMPLEX_DIM_X*COMPLEX_DIM_Y);
	
	
	
}

public double generateGeneralPowerSet(int exp, Complex mapConstant) {
	Color red = Color.RED;
	
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			canvas.setRGB(x, y, red.getRGB());
		}
	}

	int pixel_count=0;
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			
			double x_coord = x-800;
			double y_coord = 475-y;
			
		
			
			Complex coord = new Complex( x_coord /SCALE_FACTOR , y_coord / SCALE_FACTOR     );
			
			
			for(int i=0; i<Complex.ITER.length; i++) {
			Complex res = Complex.iterateGeneralizedPowerMapExperimental(coord, exp, mapConstant, Complex.ITER[i]);
			if(res.norm() < 5 ) {  canvas.setRGB(x, y, colors[5-i].getRGB()); if(i==Complex.ITER.length-1) {pixel_count++;} }
			}
			
		}
}
return ((double)pixel_count/((double)DIMENSION_X*(double)DIMENSION_Y))*(COMPLEX_DIM_X*COMPLEX_DIM_Y);
	
	
}
public double generateGeneralPowerParameterSpace(int exp) {
	Color red = Color.RED;
	
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			canvas.setRGB(x, y, red.getRGB());
		}
	}

	int pixel_count=0;
	for(int x=0; x<DIMENSION_X; x++) {
		for(int y=0; y<DIMENSION_Y; y++) {
			
			double x_coord = x-800;
			double y_coord = 475-y;
			
		
			
			Complex coord = new Complex( x_coord /SCALE_FACTOR , y_coord / SCALE_FACTOR     );
			
			
			for(int i=0; i<Complex.ITER.length; i++) {
			Complex res = Complex.iterateGeneralizedPowerMapExperimental(new Complex(0,0), exp, coord, Complex.ITER[i]);
			if(res.norm() < 5 ) {  canvas.setRGB(x, y, colors[5-i].getRGB()); if(i==Complex.ITER.length-1) {pixel_count++;} }
			}
			
		}
}
return ((double)pixel_count/((double)DIMENSION_X*(double)DIMENSION_Y))*(COMPLEX_DIM_X*COMPLEX_DIM_Y);
	
	
}
    

    



 


    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Mandelbrot");

        MandelbrotView panel = new MandelbrotView();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		current_seed_real = Double.parseDouble(real.getText());
		current_seed_imag = Double.parseDouble(imag.getText());
		repaint();
			
		}
		catch(NumberFormatException ex) {System.out.println("Enter a double please");}
		
	}


}