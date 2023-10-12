package robot;

import org.concordion.api.ConcordionResources;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.awt.*;
import java.util.Random;

@RunWith(ConcordionRunner.class)
@ConcordionResources( {"im/*"} )
public class RobotFixture {
    private Robot wallE = new Robot(0 , new Battery(), new RoadBookCalculator());

    public String directionAfterLanding(String coordonnees) throws LandSensorDefaillance {
        String trim = coordonnees.replace('(', ' ').replace(')', ' ').trim();
        String[] split = trim.split(", ");
        wallE.land(new Coordinates(Integer.valueOf(split[0]), Integer.valueOf(split[1])), new LandSensor(new Random()));
        return directionAsString();
    }

    private String directionAsString() {
        Direction direction;
        try {
            direction = wallE.getDirection();
        } catch (UnlandedRobotException e) {
            return e.getMessage();
        }
        switch (direction) {
            case NORTH:
                return "nord";
            case EAST:
                return "est";
            case SOUTH:
                return "sud";
            case WEST:
                return "ouest";
        }
        return "";
    }
}
