import java.util.HashMap;

public class Money {
	
	private PApplet processing;
	private String moneyName;
	private PImage moneyImage;
	private boolean isDragging;
	private int xPos;
	private int yPos;
	private int originxPos;
	private int originyPos;
	
	public Money(PApplet processing, String moneyName, int xPos, int yPos) {
		this.processing = processing;
		this.moneyName = moneyName;
		this.moneyImage = this.processing.loadImage("images/" + moneyName + ".png");
		this.xPos = xPos;
		this.yPos = yPos;
		this.originxPos = xPos;
		this.originyPos = yPos;
		this.isDragging = false;
	}
	
	
	public void update(HashMap<Money,Double> amounts) {
		this.moneyImage.resize(0, 70);
		this.processing.image(this.moneyImage, xPos, yPos);
		
		if (this.isDragging) {
			this.xPos = this.processing.mouseX;
			this.yPos = this.processing.mouseY;
			
			if (this.processing.mouseX <= VendingMachine.putMoneyX + 45
					&& this.processing.mouseX >= VendingMachine.putMoneyX - 45) {

				if (this.processing.mouseY <= VendingMachine.putMoneyY + 25
						&& this.processing.mouseY >= VendingMachine.putMoneyY - 25) {

						VendingMachine.totalMoney += amounts.get(this);
						mouseUp();

				}
			}

		}else {
			this.xPos = this.originxPos;
			this.yPos = this.originyPos;
		}
	}
	
	public boolean isMouseOver() {
		
		if (this.processing.mouseX <= xPos + 35
				&& this.processing.mouseX >= xPos - 35) {

			if (this.processing.mouseY <= yPos + 35
					&& this.processing.mouseY >= yPos - 35) {

				// then the mouse is over this button
				return true;

			}
		}

		return false;
	}
	
	public void mouseDown() {
		if (isMouseOver()) {
			isDragging = true;
		}
			
	}
	
	public void mouseUp() {
		isDragging = false;
	}
	
	
}
