package subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SafePWM;

public class Catapult {
        //Buttons
        public int Increment = 0, A = 1, B = 2, X = 3, Y = 4, LeftBump = 5, RightBump = 6, Back = 7, Start = 8, LeftJoy = 9, RightJoy = 10;
	//Processes
	public boolean bHardShooting = false, bSoftShooting = false, bWaiting;
	
        Joystick MainController = new Joystick(1);
        
        public DigitalInput NautilusTopSen = new DigitalInput(1);
        public DigitalInput NautilusBottomSen = new DigitalInput(2);
        SafePWM NautilusMotor = new SafePWM(3);
        
	public void hardShot(){
		if (MainController.getRawButton(X) && !NautilusTopSen.get()){
				NautilusMotor.setRaw(250);
				//bHardShooting = true;
                                bWaiting = true;
		} 
                if (bWaiting) { 
                    NautilusMotor.setRaw(250);
                    if (NautilusTopSen.get()){
                        bHardShooting = true;
                        bWaiting = false;
                    }
                }
                if (bHardShooting){
                    if (!NautilusTopSen.get()){
                        NautilusMotor.setRaw(127);
                        bHardShooting = false;
                    } else {
                        NautilusMotor.setRaw(250);
                    }
                }
	}
	public void softShot(){
		if (MainController.getRawButton(B) && !NautilusTopSen.get()){
				NautilusMotor.setRaw(5);
				bSoftShooting = true;
		}
		if (bSoftShooting && !NautilusBottomSen.get()){
                    NautilusMotor.setRaw(127);
                    NautilusMotor.setRaw(250);
                    bHardShooting = true;
                    bSoftShooting = false;
		}
	}
	public void activity(){
		hardShot();
		softShot();
	}
}
