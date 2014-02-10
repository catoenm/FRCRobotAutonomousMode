package subsystems;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

    DriveTrain driveSystem = new DriveTrain();
    
    long lInitalTime = 0;
    long lCurrentTime = 0;
    long lSecondTime = 0;
    long lThirdTime = 0;
    long lFourthTime = 0;
    long lFifthTime = 0;
    long lSwitchTime = 0;
    boolean bGoal = false;
    
    public void Run(long args) {
        if (lInitalTime == 0) {
            lCurrentTime = args;
            lSecondTime = lInitalTime + 1000;
            lThirdTime = lInitalTime + 2000;
            lFourthTime = lInitalTime + 3000;
            lFifthTime = lInitalTime + 4000;
            lSwitchTime = lInitalTime + 5000;
        } else if (lCurrentTime > lInitalTime && lCurrentTime < lSecondTime)  {
            driveSystem.autonomousDrive(1, 0);
        } else if (lCurrentTime > lSecondTime && lCurrentTime < lThirdTime) {
            driveSystem.autonomousDrive(0, 0);
            // bGoal = camera.getGoal();
        } else if (lCurrentTime > lThirdTime && lCurrentTime < lFourthTime) {
            driveSystem.autonomousDrive(0, 1);
        } else if (lCurrentTime > lFourthTime && lCurrentTime < lFifthTime) {
            driveSystem.autonomousDrive(0, 0);
            if (bGoal = false) {
                //catapult.fire();
            } else if (bGoal = false && lCurrentTime > lSwitchTime) {
                driveSystem.autonomousDrive(-1, 0);
                Timer.delay(1);
                //catapult.fire();
            } else {
                //catapult.fire();
            }
        }
    }
}
