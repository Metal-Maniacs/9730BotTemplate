package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.NEO;

public class ExampleSubsystem extends SubsystemBase {
    
    private NEO motor;

    // The code that runs when an object of "ExampleSubsystem" type is created.
    public ExampleSubsystem() {
        motor = new NEO(1, true);
    }
    
}
