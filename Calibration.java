package subsystems;

import com.sun.squawk.util.Arrays;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 * @author Mo
 */
public class Calibration {
    DigitalInput TopSensor = new DigitalInput (1);
    public void StartGameCaliration (boolean bUserInput) 
    {
        boolean bCocked = false;
        boolean bTopPositionSensor = false;
        boolean bUserCockedInput = false;
        
        bTopPositionSensor = TopSensor.get();
        if (bTopPositionSensor == true)
        {
            bCocked = true;
            System.out.println ("Cocked");
        }
        else
        {
           if (bUserCockedInput == true)
           {
               System.out.println ("Not Cocked");
           }
        }
    }
    
    public void PreGameCalibration (boolean bBallLoaded, boolean bSpotClose, boolean bSpotFar,
            boolean bShotClose, boolean bShotFar)
    {
        boolean bArray [] = new boolean[3];
        Arrays.fill (bArray, false);
        if (bBallLoaded == true)
        {
            if (bSpotClose == true)
            {
                if (bShotClose == true)
                {
                    bArray[1] = true;
                }
            }
            if (bSpotFar == true)
            {
                if (bShotFar == true)
                {
                    bArray[2] = true;
                }
            }
            for (int Loop = 1; Loop < bArray.length; Loop++)
            {
                System.out.println (bArray[Loop]);
            }
        }
    }
}
