
package com.johnwesthoff.bending.spells.chemistry;

import static com.johnwesthoff.bending.util.network.ResourceLoader.loadIcon;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.johnwesthoff.bending.Constants;
import com.johnwesthoff.bending.Session;
import com.johnwesthoff.bending.entity.InvisibleBallEntity;
import com.johnwesthoff.bending.logic.World;
import com.johnwesthoff.bending.spells.Spell;
import com.johnwesthoff.bending.util.math.Ops;

public class ChemistryInvisibility extends Chemistry {
    public ChemistryInvisibility() {
        ID = Constants.CHEMISTRY;
        subID = 0;
        try {
            icon = (loadIcon("invisible.png"));
        } catch (Exception ex) {
            Logger.getLogger(Spell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getAction(Session app) {
        X = app.world.x;
        Y = app.world.y - World.head;
        mx = app.world.viewX;
        my = app.world.mouseY - app.world.viewY;
        double direction = 360 - Ops.pointDir(app.world.x - app.world.viewX, app.world.y - World.head - app.world.viewY,
                app.world.mouseX, app.world.mouseY);
        mx = ((int) (Ops.lengthdir_x(9, direction)));
        my = ((int) (Ops.lengthdir_y(9, direction)));
        maker = ID;
        getMessage(app.out);
    }

    @Override
    public int getCoolDown() {
        return (int) (3000 * Constants.FPS / 600);
    }

    @Override
    public String getName() {
        return "Invisibility Potion";
    }

    @Override
    public String getTip() {
        return "<html>An advanced chemistry spell<br>High Cooldown<br>Makes invisibility potion</html>";
    }

    @Override
    public void getActionNetwork(World world, int px, int py, int mx, int my, int pid, int eid, ByteBuffer buf) {
        world.entityList.add(new InvisibleBallEntity(px, py, mx, my, pid).setID(eid));
    }
}
