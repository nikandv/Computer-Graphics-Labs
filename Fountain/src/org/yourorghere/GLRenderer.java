package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    private TParticle particles[];
    private int maxNumber, number;
    private float radius, alpha, flowDiameter, speed;
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
        start(3000);
    }
    
    public void start(int n) {
        maxNumber = n;
        number = 0;
        particles = new TParticle[maxNumber];
        radius = 0.4f;
        alpha = 0;
        flowDiameter = 1;
        speed = 1;
    }
    
    public void updateRadius(float r) {
        radius = r / 100;
    }
    
    public void updateFlowDiameter(float d) {
        flowDiameter = d / 5;
    }
    
    public void updateSpeed(float s) {
        speed = s / 50;
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        gl.glRotatef(-120, 1, 0, 0);
        gl.glRotatef(35, 0, 0, 1);

        //Перемещение фонтана
        float x = (float)(radius * Math.cos(alpha/100 * speed));
        float y = (float)(radius * Math.sin(alpha/100 * speed));
        alpha++;
        
        particles[number] = new TParticle(x, y, flowDiameter);
        if(number < maxNumber - 1) {
            number++;
        }
        
        for(int i=0; i < number; i++) {
            particles[i].Draw(gl);
            particles[i].Move();
        }

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

