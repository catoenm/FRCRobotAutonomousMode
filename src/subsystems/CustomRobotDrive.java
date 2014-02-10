package subsystems;

import edu.wpi.first.wpilibj.SafePWM;

public class CustomRobotDrive {
    
    SafePWM RightFrontTalon = new SafePWM(1);
    SafePWM RightBackTalon = new SafePWM(2);
    SafePWM LeftFrontTalon = new SafePWM(3);
    SafePWM LeftBackTalon = new SafePWM(4);
    
    private int iTrim = 0;
    private final int iMiddl0eOfByte = 125;
    
    public void setTrim(int args) {
        iTrim = args;
    }
    
    public void setSafetyEnabled(boolean args) {
        RightFrontTalon.setSafetyEnabled(args);
        RightBackTalon.setSafetyEnabled(args);
        LeftFrontTalon.setSafetyEnabled(args);
        LeftBackTalon.setSafetyEnabled(args);
    }
    
    public boolean isSafetyEnabled() {
        boolean send;
        send = RightFrontTalon.isSafetyEnabled() != false && RightBackTalon.isSafetyEnabled() != false && LeftFrontTalon.isSafetyEnabled() != false && LeftBackTalon.isSafetyEnabled() != false;
        return send;
    }
    
    public void arcadeDrive(double drive, double steer) {
        
        int center = iMiddl0eOfByte + iTrim;
        int iFinalRightRaw;
        int iFinalLeftRaw;

        
        iFinalRightRaw = (int) (center + ((iMiddl0eOfByte * drive) + (steer * iMiddl0eOfByte)));
        iFinalLeftRaw = (int) (center - ((iMiddl0eOfByte * drive) - (steer * iMiddl0eOfByte)));
        
        if (iFinalRightRaw > 250) {
            iFinalRightRaw = 250;
        } else if (iFinalRightRaw < 4) {
           iFinalRightRaw = 4;
        }
        
        if (iFinalLeftRaw > 250) {
            iFinalLeftRaw = 250;
        } else if (iFinalLeftRaw < 4) {
           iFinalLeftRaw = 4;
        }
        
        RightFrontTalon.setRaw(iFinalRightRaw);
        RightBackTalon.setRaw(iFinalRightRaw);
        LeftFrontTalon.setRaw(iFinalLeftRaw);
        LeftBackTalon.setRaw(iFinalLeftRaw);
    }
}
