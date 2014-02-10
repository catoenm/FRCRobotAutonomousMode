package subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SafePWM;

/**
 *
 * @author mitch_000
 */
public class LoadingArms {
        //Buttons
        public int A = 1, B = 2, X = 3, Y = 4, LeftBump = 5, RightBump = 6, Back = 7, Start = 8, LeftJoy = 9, RightJoy = 10, Times;
	//Processes
	public boolean Loading = false, Ejecting = false, LoweringFront = false, LoweringBack = false, ClosingFront = false, ClosingBack = false, EjectingFront = false, EjectingBack = false, PostEjectionFront = false, PostEjectionBack, LoadingFront = false, LoadingBack = false, EjectingBallFront = false, EjectingBallBack = false, DoneEjecting;
	
        Joystick MainController = new Joystick(1);
        
        SafePWM FrontLoadingMotor = new SafePWM(1);
        SafePWM FrontArmMotor = new SafePWM(2);
        DigitalInput FrontArmOpenSensor = new DigitalInput(5);
        DigitalInput FrontArmMediumSensor = new DigitalInput(6);
        DigitalInput FrontArmClosedSensor = new DigitalInput(7);
        
        
        public void loadFront(){                                                                                         
            

            if (MainController.getRawAxis(5) < -0.95){
                                                                                                                        
                    System.out.println("Opening Now");
                    LoweringFront = true;										
                    ClosingFront = false;                                                                                                   

                    FrontLoadingMotor.setRaw(250);

                    if (!FrontArmOpenSensor.get() && LoweringFront){
                        System.out.println("Open Sensor was hit");
                        FrontArmMotor.setRaw(127);
                        LoweringFront = false;
                    }
                    else{
                        FrontArmMotor.setRaw(250);
                    }
            }
            else if (FrontArmMediumSensor.get()){
                    System.out.println("Closing Now");                                                                  
                    ClosingFront = true;										
                    LoweringFront= false;										
                    FrontArmMotor.setRaw(127);  
                    FrontArmMotor.setRaw(5);
            }
            if (!FrontArmMediumSensor.get() && ClosingFront){
                    System.out.println("Medium Sensonr has reached again");
                    FrontArmMotor.setRaw(127);
                    ClosingFront = false;
                    LoweringFront = false;
            }
        }
//}
        public void ejectFront(){
            
        }
        public void activity(){
            loadFront();
            //ejectFront();
        }
}
