import java.util.HashMap;
import java.util.Random;

public class VendingMachine {
	private static final int HALFWIDTH = 250;
	private static final int HALFHEIGHT = 350;
	private static int xPosUL;
	private static int yPosUL;
	private static int xPosLR;
	private static int yPosLR;
	private static int xCenter;
	private static int yCenter;
	
	protected static int putMoneyX = 525;
	protected static int putMoneyY = 345;
	protected static int outputX = 530;
	protected static int outputY = 625;
	
	private PApplet processing;
	private PImage glass;
	private HashMap<Money, Double> moneyProperties;
	private String[][] foodNames;
	private FoodButton[][] snacks;
	protected static double totalMoney;
	
	public VendingMachine(PApplet processing) {
		
		this.processing = processing;
		this.glass = this.processing.loadImage("images/glass.png");
		this.foodNames = new String[4][4];
		this.snacks = new FoodButton[4][4];
		this.moneyProperties = new HashMap<Money,Double>();
		totalMoney = 0;
		
		setMoneyProperties();
		setFoodNames();
		
		VendingMachine.xPosUL = this.processing.width / 2 - HALFWIDTH - 75;
		VendingMachine.yPosUL = this.processing.height / 2 - HALFHEIGHT;
		VendingMachine.xPosLR = this.processing.width / 3 + HALFWIDTH + 75;
		VendingMachine.yPosLR = this.processing.height / 2 + HALFHEIGHT;
		VendingMachine.xCenter = xPosUL + (xPosLR - xPosUL) / 2;
		VendingMachine.yCenter = yPosUL + (yPosLR - yPosUL) / 2;
		
		setFoodButtons();
		
	}
	
	
	public void update() {
		
		this.processing.fill(0,0,0);
		this.processing.rect(xPosUL, yPosUL,xPosLR, yPosLR);
		this.processing.image(this.glass, VendingMachine.xCenter, VendingMachine.yCenter);
		
		this.processing.fill(128);
		this.processing.rect(160,80, 440, 120);
		this.processing.fill(255);
		this.processing.textSize(17);
		this.processing.text("AR VENDING MACHINE", 300, 100);
		
		this.processing.fill(128);
		this.processing.rect(480,250, 570, 300); // money amount
		this.processing.rect(480, 320, 570, 365); // put money here
		
		this.processing.fill(240,248,255);
		this.processing.rect(480, 550, 570, 700); // dispense 
		
		this.processing.ellipse(520, 425, 50, 50); // clear the balance
				
		this.processing.fill(255);
		this.processing.textSize(13);
		this.processing.text("$" + String.format("%.2f", totalMoney), 530, 275);
		
		this.processing.fill(255);
		this.processing.textSize(10);
		this.processing.text("Put money here", 530, 345);
		
		this.processing.fill(0);
		this.processing.textSize(16);
		this.processing.text("Dispense", 530, 625);
		
		this.processing.fill(0);
		this.processing.textSize(12);
		this.processing.text("Clear", 520, 425);
		
		for(int i=0; i<this.snacks.length; i++) {
			for(int j=0; j < this.snacks[i].length; j++) {
				
				this.snacks[i][j].update();
				
			}
		}
		
		for ( Money key : moneyProperties.keySet() ) {
		    key.update(moneyProperties);
		}
		
		
	}
	
	public void mouseDown() {
		for (Money key : moneyProperties.keySet()) {
			key.mouseDown();
		}
		for (int i = 0; i < this.snacks.length; i++) {
			for (int j = 0; j < this.snacks[i].length; j++) {
				if (snacks[i][j].getItemSelected()) snacks[i][j].setItemUnSelected();
				snacks[i][j].mouseDown();
			}
		}
		
		if (this.processing.mouseX <= 520 + 50
				&& this.processing.mouseX >= 520- 50) {

			if (this.processing.mouseY <= 425+50
					&& this.processing.mouseY >= 425-50) {
				totalMoney = 0;
			}
		}
		
	}
	
	public void mouseUp() {
		for (Money key : moneyProperties.keySet()) {
			key.mouseUp();
		}
	}

	public void setFoodNames() {
		this.foodNames[0][0] = "cheetos";
		this.foodNames[0][1] = "chexMix";
		this.foodNames[0][2] = "Doritos";
		this.foodNames[0][3] = "fritos";
		
		this.foodNames[1][0] = "lays-classic";
		this.foodNames[1][1] = "lays-flamin-hot";
		this.foodNames[1][2] = "Lays-Chips-Sea-Salt";
		this.foodNames[1][3] = "lays-sour-cream-onion";
		
		this.foodNames[2][0] = "Milky-way";
		this.foodNames[2][1] = "Oreo";
		this.foodNames[2][2] = "Reeses";
		this.foodNames[2][3] = "snickers";
		
		this.foodNames[3][0] = "Funyuns";
		this.foodNames[3][1] = "chipsAhoy";
		this.foodNames[3][2] = "TrailMix";
		this.foodNames[3][3] = "twix";
		
	}
	
	public void setFoodButtons() {
		int horizontalSpace = 90;
		int verticalSpace = 150;
		int foodButtonHalfWidth = 40 ;
		int foodButtonHalfHeight = 70;
		String row = "ABCD";
		String column = "1234";
		Random rd = new Random();
		
		for (int i = 0; i < this.snacks.length; i++) {
			for (int j = 0; j < this.snacks[i].length; j++) {

				this.snacks[i][j] = new FoodButton(this.processing, this.xPosUL + horizontalSpace * (j + 1),
						this.yPosUL + verticalSpace * (i + 1), foodButtonHalfWidth, foodButtonHalfHeight,
						"" + row.charAt(i) + column.charAt(j), this.foodNames[i][j]);
				String price = String.format("%.2f", rd.nextDouble() * 3 + 0.75);
				this.snacks[i][j].setPrice(Double.parseDouble(price));
				
			}
		}
		
	}
	
	public void setMoneyProperties() {
		int xPos = 700;
		int yPos = 150;
		int vSpace = 120; 
		this.moneyProperties.put(new Money(this.processing,"penny",xPos,yPos), 0.01);
		this.moneyProperties.put(new Money(this.processing,"nickel",xPos,yPos+vSpace),0.05);
		this.moneyProperties.put(new Money(this.processing,"dime",xPos,yPos+vSpace * 2),0.10);
		this.moneyProperties.put(new Money(this.processing,"quarter",xPos,yPos+vSpace * 3),0.25);
		this.moneyProperties.put(new Money(this.processing,"dollar",xPos,yPos+vSpace * 4),1.00);
	}
	
}
