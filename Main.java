
public class Main {
	
	private PApplet processing;        // field for drawing to the screen
	private PImage hand;
	private VendingMachine vm;

	
	Main(PApplet processing) {
		this.processing = processing;
		this.hand = this.processing.loadImage("images/hand.png");
		this.hand.resize(0, 30);
		processing.surface.setTitle("Vending Machine");
		processing.surface.setSize(800, 800);
		this.processing.cursor(this.hand);
		vm = new VendingMachine(processing);

	}
	
	public void update() {
		this.processing.background(211,211,211);
		vm.update();

	}
	
	public void mouseDown() {
		vm.mouseDown();
	}
	
	public void mouseUp() {
		vm.mouseUp();
	}
	
	
	public static void main(String[]args) {
		Utility.startApplication();
	}
	
	
}
