package subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain {
    
    Joystick mainController = new Joystick(1);
    Joystick secondController = new Joystick(2);
    CustomRobotDrive drive = new CustomRobotDrive();
    
    private double dThrottle;
    private int iTrim = 0;
    public int iGear = 3;
    private boolean bGearButtonDown;
    private boolean bTrimButtonDown;
    private boolean bTurnButtonDown;
    
    public void setSafetyEnabled(boolean args) {
        drive.setSafetyEnabled(args);
    }
    
    public boolean isSafetyEnabled() {
        return drive.isSafetyEnabled();
    }
    
    public void checkGear() {
        
        if (mainController.getRawButton(5) == false && mainController.getRawButton(6) == false && bGearButtonDown) {
            bGearButtonDown = false;
        }
        
        if (mainController.getRawButton(5) && mainController.getRawButton(6) && bGearButtonDown == false) {
            iGear = 2;
            bGearButtonDown = true;
        } else if (mainController.getRawButton(6) && bGearButtonDown == false && iGear < 3) {
            iGear += 1;
            bGearButtonDown = true;
        } else if (mainController.getRawButton(5) && bGearButtonDown == false && iGear > 1) {
            iGear -= 1;
            bGearButtonDown = true;
        }
            
        if (mainController.getRawButton(5) == false && mainController.getRawButton(6) == false && bGearButtonDown) {
            bGearButtonDown = false;
        }
    }
    
    public void checkTrim() {
        if (secondController.getRawButton(5) && bTrimButtonDown == false && iGear > 1) {
            iTrim += 1;
            bTrimButtonDown = true;
        } else if (secondController.getRawButton(6) && bTrimButtonDown == false && iGear < 3) {
            iTrim -= 1;
            bTrimButtonDown = true;
        } else if (secondController.getRawButton(5) == false && secondController.getRawButton(6) == false) {
            bTrimButtonDown = false;
        }
    }
    
    public void checkThrottle() {
        if (iGear == 1) {
            dThrottle = 0.3;
        } else if (iGear == 2) {
            dThrottle = 0.5;
        } else if (iGear == 3) {
            dThrottle = 1;
        }
    }
    
    public void arcadeDrive() {
        drive.setTrim(iTrim);
        double driveX;
        if (mainController.getX() > 0.15) {
            driveX = (mainController.getX() - 0.15) / 0.85;
        } else if (mainController.getX() < -0.15) {
            driveX = (mainController.getX() - 0.15) / 0.85;
        } else {
            driveX = 0;
        }
        
        drive.arcadeDrive(mainController.getZ() * dThrottle, driveX * dThrottle);
    }
    
    public void checkTurn() {
        if (mainController.getRawAxis(6) == 1 && bTurnButtonDown == false) {
            drive.arcadeDrive(0, 1 * dThrottle);
            Timer.delay(0.3 / dThrottle);
            drive.arcadeDrive(0, 0);
            bTurnButtonDown = true;
        } else if (mainController.getRawAxis(6) == -1 && bTurnButtonDown == false) {
            drive.arcadeDrive(0, -1 * dThrottle);
            Timer.delay(0.3 / dThrottle);
            drive.arcadeDrive(0, 0); 
            bTurnButtonDown = true;
        } else if (mainController.getRawAxis(6) == 0) {
            bTurnButtonDown = false;
        }
    }
    
    public void autonomousDrive(double args, double args1) {
        drive.setTrim(iTrim);
        drive.arcadeDrive(args, args1);
    }
}
