package com.jeekrs.threatenbody.system;

import com.jeekrs.threatenbody.component.CoordinateComponent;
import com.jeekrs.threatenbody.entity.Entity;
import com.jeekrs.threatenbody.entity.Planet;
import com.jeekrs.threatenbody.interfaces.Collidable;
import com.jeekrs.threatenbody.interfaces.Gravity;
import com.jeekrs.threatenbody.interfaces.Physics;
import com.jeekrs.threatenbody.utils.Vec2d;

import java.util.ArrayList;

public class PhysicsSystem extends SimpleSystem {
    public final static double G = 1;

    public void collidate(Collidable lhs, Collidable rhs) {
        if (lhs instanceof Planet && rhs instanceof Planet) {
            System.out.println("boom" + lhs.getPhysicsComponent().p + rhs.getPhysicsComponent().p);
        }
    }

    @Override
    public void update(float delta) {
        ArrayList<Physics> phy = new ArrayList<>();
        systemManager.world.entities.forEach(e -> {
            if (e instanceof Physics) {
                phy.add((Physics) e);
                ((Physics) e).getPhysicsComponent().a.clear();
            }
        });


        // for collision
        ArrayList<Collidable> collidable = new ArrayList<>();
        phy.forEach(e -> {
            if (e instanceof Collidable) {
                collidable.add((Collidable) e);
            }
        });

        collidable.forEach(e1 -> collidable.forEach(e2 -> {
            if (e1 != e2 && e1.getPhysicsComponent().p.dist(e2.getPhysicsComponent().p) <= e1.getCollidingRadius(e2) + e2.getCollidingRadius(e1)) {
                collidate(e1, e2);
            }
        }));

        // for gravity
        for (Physics e1 : phy) {
            if (e1 instanceof Gravity) {
                for (Physics e2 : phy) {
                    if (e1 != e2 && e2 instanceof Gravity) {
                        CoordinateComponent p1 = e1.getPhysicsComponent().p;
                        CoordinateComponent p2 = e2.getPhysicsComponent().p;

                        double dist = p1.distSquare(p2);
                        if (dist < 5) continue;

                        double totAcc = G * e2.getMass() / dist;
                        Vec2d dis = new Vec2d(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));

                        double angle = Math.atan2(dis.y, dis.x);

                        Vec2d a = new Vec2d(Math.cos(angle), Math.sin(angle)).multiBy(totAcc);

                        if (p1.x > p2.x) {
                            a.x *= -1.0;
                        }

                        if (p1.y > p2.y) {
                            a.y *= -1.0;
                        }

                        e1.getPhysicsComponent().a.add(a);

                    }
                }
            }
        }

        double[] energies = new double[phy.size()];
        int index = 0;
        for (Physics e1 : phy) {
            double energy = getKineticEnergy(e1) + getGravitationalPotentialEnergy(e1);
            energies[index++] = energy;
        }

        // for velocity
        for (Physics physics : phy) {
            physics.getPhysicsComponent().v.add(physics.getPhysicsComponent().a.multi(delta));
        }


        // for position
        for (Physics physics : phy) {
            physics.getPhysicsComponent().p.add(physics.getPhysicsComponent().v.multi(delta));
        }

        // for velocity fix
        index = 0;
        for (Physics e1 : phy) {
            double energy = energies[index++];
            double Ek = energy - getGravitationalPotentialEnergy(e1);

            double vel = Math.sqrt(2 * Ek / e1.getMass());
            double scale = vel <= 1e-6 ? 0 : vel / e1.getPhysicsComponent().v.abs();
            e1.getPhysicsComponent().v.multiBy(scale);
        }

//        phy.forEach(o -> System.out.println(o.getPhysicsComponent().a + " " + o.getPhysicsComponent().v + " " + o.getPhysicsComponent().p));


    }

    public static double getKineticEnergy(Physics ph) {
        return 0.5 * ph.getMass() * ph.getPhysicsComponent().v.square();
    }

    public static double getGravitationalPotentialEnergy(Physics g1, Physics g2) {
        if (g1 instanceof Gravity && g2 instanceof Gravity)
            return -g1.getMass() * g2.getMass() / g1.getPhysicsComponent().p.dist(g2.getPhysicsComponent().p);
        return 0;
    }

    public double getGravitationalPotentialEnergy(Physics ph) {
        double sum = 0;
        if (ph instanceof Gravity) {
            for (Entity e : systemManager.world.entities) {
                if (e instanceof Physics && ph != e)
                    sum += getGravitationalPotentialEnergy(ph, (Physics) e);
            }
        }
        return sum;
    }
}
