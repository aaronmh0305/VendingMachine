
public class FoodButton extends Button {

	private PImage foodImage;
	private double price;
	private boolean itemSelected;

	public FoodButton(PApplet processing, int xCenter, int yCenter, int halfWidth, int halfHeight, String label,
			String foodName) {

		super(processing, xCenter, yCenter, halfWidth, halfHeight, label);
		changeLabelPos(xCenter, yCenter + 60);
		this.foodImage = this.processing.loadImage("images/" + foodName + ".png");
		this.itemSelected = false;
	}

	public void update() {

		if (isMouseOver()) {
			this.processing.fill(180);
			this.processing.rect(xCenter - halfWidth, yCenter - halfHeight, xCenter + halfWidth, yCenter + halfHeight);
			this.processing.fill(0);
			this.processing.text("$" + this.price, xCenter, yCenter);
		} else {
			
			this.processing.fill(128);
			super.update();
			this.foodImage.resize(0, halfHeight + 30);
			this.processing.image(this.foodImage, xCenter, yCenter);
		}
		
		if (itemSelected) {
			this.processing.image(this.foodImage,VendingMachine.outputX,VendingMachine.outputY);
		}
	}

	public void mouseDown() {
		if (isMouseOver() && VendingMachine.totalMoney > price) {
			VendingMachine.totalMoney -= price;
			if (VendingMachine.totalMoney < 0) {
				VendingMachine.totalMoney = 0;
				return;
			}
			this.itemSelected = true;
		} 
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getItemSelected() { return itemSelected;}
	
	public void setItemUnSelected() {
		this.itemSelected = false;
	}
}
