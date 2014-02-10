package subsystems;

import edu.wpi.first.wpilibj.SafePWM;

public class RobotDrive {
    
    SafePWM RightFrontTalon = new SafePWM(5);
    SafePWM RightBackTalon = new SafePWM(6);
    SafePWM LeftFrontTalon = new SafePWM(7);
    SafePWM LeftBackTalon = new SafePWM(8);
    
    private int iTrim = 0;
    private final int iMiddl0eOfByte = 147;
    
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
        iFinalLeftRaw = (int) (center - ((iMiddl0eOfByte * drive) + (steer * iMiddl0eOfByte)));
        
        if (iFinalRightRaw > 254) {
            iFinalRightRaw = 254;
        } else if (iFinalRightRaw < 0) {
           iFinalRightRaw = 0;
        }
        
        if (iFinalLeftRaw > 254) {
            iFinalLeftRaw = 254;
        } else if (iFinalLeftRaw < 0) {
           iFinalLeftRaw = 0;
        }
        
        RightFrontTalon.setRaw(iFinalRightRaw);
        RightBackTalon.setRaw(iFinalRightRaw);
        LeftFrontTalon.setRaw(iFinalLeftRaw);
        LeftBackTalon.setRaw(iFinalLeftRaw);
    }
}
