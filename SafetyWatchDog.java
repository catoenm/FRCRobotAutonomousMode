/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;

/**
 *
 * @author Mo
 */
public class SafetyWatchDog {
    Joystick Stick = new Joystick(1);
    Joystick ManualStick = new Joystick(2);
    DigitalInput bTopLimitSensor = new DigitalInput(1);
    PWM NautilusMotor = new Jaguar (1);
    boolean bHardButton, bManualMode;
    DriverStation Station;
    double dBatteryLevel;
    public static SafetyWatchDog safety;
    
    public void CockCheck (){
        bHardButton = Stick.getRawButton(1);
        if (bHardButton == true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            if (bTopLimitSensor.get() == true) {
                System.out.println("Nautilus Cam is Cocked");
            }
            if (bTopLimitSensor.get() == false) {
                System.out.println("Nautilus Cam NOT is Cocked");
            }
        }
    }
            
    public void BatteryCheck ()
    {
        dBatteryLevel = Station.getBatteryVoltage();
        if (dBatteryLevel >= 12)
        {
            System.out.println ("Battery is Good");
        }
        if (dBatteryLevel < 12 && dBatteryLevel >= 10)
        {
            System.out.println ("Battery is Low");
        }
        if (dBatteryLevel < 10)
        {
            System.out.println ("Battery is Critical");
        }
    }
    
    public void ManualMode ()
    {
        if (ManualStick.getRawButton(3))
        {
            bManualMode = true;
        }
        
        if (ManualStick.getRawButton(2))
        {
            bManualMode = false;
        }
        
        if (bManualMode == true)
        {
            if (ManualStick.getRawButton(1))
            {
                NautilusMotor.setRaw(1);
            }
            if (ManualStick.getRawButton(4))
            {
                NautilusMotor.setRaw(-1);
            }
        }
    }
    
    public SafetyWatchDog getSafe() {
        return safety;
    }
}