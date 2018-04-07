
public class Button {
	protected int halfWidth; //= 30;
	protected int halfHeight; //= 60;
	
	protected int xCenter;
	protected int yCenter;
	protected int xLabel;
	protected int yLabel;
	protected String label;
	protected PApplet processing; 
	
	public Button(PApplet processing, int xCenter, int yCenter, int halfWidth,
			int halfHeight, String label) {
		
		this.processing = processing;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.halfWidth = halfWidth;
		this.halfHeight = halfHeight;
		this.label = label;
		this.xLabel = xCenter;
		this.yLabel = yCenter;
		
	}
	
	public void update() {
		this.processing.rect(xCenter - halfWidth, yCenter - halfHeight, 
				xCenter + halfWidth, yCenter + halfHeight);
		
		this.processing.fill(255);
		this.processing.text(this.label, xLabel, yLabel);
	}
	
	public boolean isMouseOver() {
		
		if (this.processing.mouseX <= xCenter + halfWidth
				&& this.processing.mouseX >= xCenter - halfWidth) {

			if (this.processing.mouseY <= yCenter + halfHeight
					&& this.processing.mouseY >= yCenter - halfHeight) {

				// then the mouse is over this button
				return true;

			}
		}

		return false;
	}
	
	public void mouseDown() {
		if (isMouseOver()) 
			System.out.println("Clicked");
	}
	
	public void changeLabelPos(int x, int y) {
		this.xLabel = x;
		this.yLabel = y;
	}
	
}
