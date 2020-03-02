//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           JunglePark1000
// Files:           Jungle Park 1000
// Course:          CS300, Fall 2018
//
// Author:          Chaiyeen Oh
// Email:           coh26@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A   (name of your pair programming partner)
// Partner Email:N/A   (email address of your programming partner)
// Partner Lecturer's Name: N/A(name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  Piazza (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.Random;

public class JunglePark {
	
private static PApplet processing; // PApplet object that represents the graphic interface of the JunglePark application
private static PImage backgroundImage; // PImage object that represents the background image
private static Tiger[] tigers; // array storing the current tigers present in the Jungle Park
private static Random randGen; // Generator of random numbers

	public static void main(String[] args) {
		Utility.startApplication();
	}
	
	/**
	 * Defines the initial environment properties of the application
	 * @param processingObj represents a reference to the graphical
	 *  interface of the application
	 */
	public static void setup(PApplet processingObj) {
		processing = processingObj; // initialize the processing field to the one passed into 
	    // the input argument parameter

		// initialize and load the image of the background
		backgroundImage = processing.loadImage("images/background.png");
		
		// Draw the background image at the center of the screen
		//processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// width [resp. height]: System variable of the processing library that stores the width 
		// [resp. height] of the display window.
		tigers = new Tiger[8]; //creating the single array tigers of up to 8 tigers references that must begin with 8 null references
		randGen = new Random();
		}

	public static void update() { 
		// Set the color used for the background of the Processing window
		processing.background(245, 255, 250); // Mint cream color
		
		// continuously draw the application display window and updates its content with respect to any change or any event that affects its appearance
		// Draw the background image at the center of the screen
		// As it is moved from setup() to update() -- the background image should only be loaded once per call of the setup() method and
		// should be drawn to the screen once per call of the update() method
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// width [resp. height]: System variable of the processing library that stores the width 
		// [resp. height] of the display window.
			
		for(int i=0; i<tigers.length; i++) { //drawing 8 tigers at random positions on the window
			if(tigers[i]!=null) {
			   tigers[i].draw(); //method drawing tiger on the window
			}
		}
		}
	
	public static boolean isMouseOver(Tiger tiger) { //checking if the mouse is over the image
	   float X = tiger.getPositionX();
	   float Y = tiger.getPositionY();
		
		if((X-tiger.getImage().width/2)< processing.mouseX && processing.mouseX < (X + tiger.getImage().width/2) && 
		   (Y - tiger.getImage().height/2)< processing.mouseY && processing.mouseY < (Y + tiger.getImage().height/2)  ) {
			return true; // if the mouse is over the boundaries of the tiger with origin of X and Y, return true
		   }

		else {
		   return false;
		   }
	}
	
	public static void mouseDown() { //dragging selected tigers down			
		for(int i=0; i<tigers.length; i++) {
			if(tigers[i]!=null) {
				
			if(isMouseOver(tigers[i]) == true) {
				tigers[i].setDragging(true);
				break;
				}
			}
		}
	}

	public static void mouseUp() { // dragging selected tigers up 		
		for(int i=0; i<tigers.length; i++) {
			if(tigers[i]!=null) {
			tigers[i].setDragging(false);			
		    }
		}
	}
	
	public static void keyPressed() {
		if(processing.key == 't'|| processing.key == 'T') { //adding tiger by pressing t or T on the keyboard
			for(int i=0; i<tigers.length; i++) {
				if(tigers[i] == null) {
				   tigers[i] = new Tiger(processing, (float)randGen.nextInt(processing.width), (float)randGen.nextInt(processing.height));	
				   break;
				}
			}
		}
		
		if(processing.key == 'r'|| processing.key == 'R') { //removing selected tiger by pressing r or R on the keyboard
			for(int i=0; i<tigers.length; i++) {
			   if (tigers[i]!= null && isMouseOver(tigers[i]) == true) {
				   tigers[i] = null;
				   break;
			   }
			}
		}
	}
}
