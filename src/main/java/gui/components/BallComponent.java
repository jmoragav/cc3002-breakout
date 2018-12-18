package gui.components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

/**
 * Component of a ball , this class has methods to limit the velocity of the ball and give the ball certain velocity
 * @autor Joaquin Moraga
 */
public class BallComponent extends Component {

    private PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf) {

       limitVelocity();

    }

   private void limitVelocity() {
        if (Math.abs(physics.getLinearVelocity().getX()) <300) {


            physics.setLinearVelocity(Math.signum(physics.getLinearVelocity().getX()) * 5 * 60,
                    physics.getLinearVelocity().getY());

        }

        if (Math.abs(physics.getLinearVelocity().getY()) < 300) {


            physics.setLinearVelocity(physics.getLinearVelocity().getX(),
                    Math.signum(physics.getLinearVelocity().getY()) * 5 * 60);

        }
    }

    public void release() {
        physics.setBodyLinearVelocity(new Vec2(5, 5));


    }
}
