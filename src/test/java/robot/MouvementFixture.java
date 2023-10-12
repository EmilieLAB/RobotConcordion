package robot;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RunWith(ConcordionRunner.class)
public class MouvementFixture {
    private Robot wallE = new Robot(0, new Battery(), new RoadBookCalculator());

    public String resultat(String mouvement) throws InaccessibleCoordinate, UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance {
        wallE.land(new Coordinates(1, 5), new LandSensor(new Random()));
        // On suppose qu'il est intialement orienté Nord
        String result = "";
        Direction directionFinale;
        int posXInitiale = wallE.getXposition();
        int posYInitiale = wallE.getYposition();
        switch (mouvement) {
            case "avant":
                wallE.moveForward();
                if (wallE.getXposition() == posXInitiale && wallE.getYposition() == (posYInitiale - 1)) {
                    result = "avance d'une case vers le nord";
                }
                break;
            case "arrière":
                wallE.moveBackward();
                // Calcul position tout ça
                if (wallE.getXposition() == posXInitiale && wallE.getYposition() == (posYInitiale + 1)) {
                    result = "avance d'une case vers le sud";
                }
                break;
            case "tourne dans le sens des aiguilles":
                wallE.turnRight();
                // "Calcul" direction
                directionFinale = wallE.getDirection();
                if (directionFinale == Direction.EAST) {
                    result = "est orienté vers l'est";
                }
                break;
            case "tourne dans le sens inverse des aiguilles":
                wallE.turnLeft();
                directionFinale = wallE.getDirection();
                if (directionFinale == Direction.WEST) {
                    result = "est orienté vers l'ouest";
                }
        }
        return result;
    }
}
