package gui.components;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {
    private static final float BOUNCE_FACTOR = 1.5f;//Para evitar que el player salga de los limites este rebotara contra el muro
    private static final float SPEED_DECAY = 0.66f;

    private PhysicsComponent physics;
    private float speed = 0;

    private Vec2 velocity = new Vec2();

    @Override
    public void onUpdate(double tpf) {
        speed = 600 * (float)tpf;

        velocity.mulLocal(SPEED_DECAY);

        if (entity.getX() < 0) {
            velocity.set(BOUNCE_FACTOR * (float) -entity.getX(), 0);
        } else if (entity.getRightX() > FXGL.getApp().getWidth()) {
            velocity.set(BOUNCE_FACTOR * (float) -(entity.getRightX() - FXGL.getApp().getWidth()), 0);
        }

        physics.setBodyLinearVelocity(velocity);
    }

    public void left() {
        velocity.set(-speed, 0);
    }

    public void right() {
        velocity.set(speed, 0);
    }
}
