package tests;

import java.util.Vector;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.SlickCallable;

public class TrailEffect {

    Vector<TrailSegment> segments = new Vector<TrailSegment>();
    TrailSegment lastSegment;
    Vector2f objectCoordinates;
    Color color;
    float width = 5f;
    int segmentLifeTime = 2500;
    int segmentAdditionDelay = 5;
    int maxSegmentAdditionDelay = segmentAdditionDelay;

    public TrailEffect(Vector2f objectCoordinates, Color color)
    {
        this.objectCoordinates = objectCoordinates;
        this.color = color;
    }

    public TrailEffect(Vector2f objectCoordinates, Color color, int segmentLifeTime)
    {
        this.objectCoordinates = objectCoordinates;
        this.color = color;
        this.segmentLifeTime = segmentLifeTime;
    }

    public void render(Graphics g)
    {
        if (segments.size()>=2)
        {
            SlickCallable.enterSafeBlock();
            {
                GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

                for (int i = 0; i < segments.size(); i++)
                {
                    segments.get(i).render();
                }

                GL11.glEnd();
            }
            SlickCallable.leaveSafeBlock();
        }
    }

    public void update(int delta, int x, int y)
    {
        objectCoordinates.set(x, y);
        for (int i = 0; i < segments.size(); i++)
        {
            segments.get(i).update(delta);
            if (segments.get(i).isExpired())
            {
                segments.remove(i);
            }
        }

        segmentAdditionDelay -= delta;
        if (segmentAdditionDelay<=0)
        {
            addNewSegment();
            segmentAdditionDelay += maxSegmentAdditionDelay;
        }

    }

    private void addNewSegment()
    {
        Vector2f segmentPosition = new Vector2f(objectCoordinates);
        float segmentFacing = 0f;
        if (lastSegment!=null)
        {
            segmentFacing = (float) (Math.atan2(lastSegment.getPosition().y - segmentPosition.y,lastSegment.getPosition().x- segmentPosition.x));
        }
        TrailSegment segment = new TrailSegment(segmentPosition, segmentFacing);
        lastSegment = segment;
        segments.add(segment);
    }

    class TrailSegment
    {
        int life = segmentLifeTime;
        int maxLife = life;
        Vector2f position;
        float facing;

        public TrailSegment(Vector2f position, float facing)
        {
            this.position = position;
            this.facing = facing;
        }

        public Vector2f getPosition()
        {
            return position;
        }

        public float getLifeAsPercentage()
        {
            return ((float) life / (float) maxLife);
        }

        public boolean isExpired()
        {
            return life <= 0;
        }

        public void update(int delta)
        {
            life -= delta;
        }

        public void render()
        {
            GL11.glColor4f(color.r, color.g, color.b, getLifeAsPercentage());
            GL11.glVertex3f(position.x + (float)Math.cos(-facing - 90f) *  width,
                    position.y - (float)Math.sin(-facing - 90f) * width, 0f);
            GL11.glVertex3f(position.x + (float)Math.cos(-facing + 90f) * width,
                    position.y - (float)Math.sin(-facing + 90f) * width, 0f);
        }
    }

}
