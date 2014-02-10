package main;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import subsystems.*;


public class Main extends SimpleRobot {

    long currentTime;
    
    //DriveTrain driveSystem = new DriveTrain();
    //Catapult catapult = new Catapult();
    LoadingArms loadingArms = new LoadingArms();
    
    public void autonomous() {
        
    }

    public void operatorControl() {
        try {
            System.out.println("test 1");
            currentTime = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis());
            while (this.isEnabled()) {
                System.out.println("test 2");
                //System.out.println("Limit 1: " + catapult.NautilusTopSen.get());
                //System.out.println("Limit 2: " + catapult.NautilusBottomSen.get());
                /*driveSystem.checkTrim();
                driveSystem.checkGear();
                driveSystem.checkThrottle();
                driveSystem.arcadeDrive();
                driveSystem.checkTurn();*/
                //catapult.activity();
                
                loadingArms.activity();
                //System.out.println(driveSystem.iGear);
                Timer.delay(0.01);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    public void test() {
    
    }
}





 
        