package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.math.*;



/**
 * Molecule.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Molecule implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("A molecule of methanol");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Molecule());
        frame.add(canvas);
        frame.setSize(800, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //animator.start();
    }

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
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClearColor(1,1,1,1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glRotatef(-120, 1, 0, 0);
        gl.glRotatef(35, 0, 0, 1);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        gl.glEnable(GL.GL_DEPTH_TEST);

        //Oxygen is red, Carboneum is black and Hydrogen is blue.
        gl.glTranslatef(0.25f, 0, 0);
        drawSphere(drawable, 0.13f, 24, 1.0f, 0.0f, 0.0f);
        
        gl.glPushMatrix();
            gl.glRotatef(-90,0,1,0);
            drawCylinder(drawable, 0.04f, 14, 0.5f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glRotatef(-195,0,1,0);
        gl.glRotatef(80,0,0,1);
        drawCylinder(drawable, 0.04f, 14, 0.5f);
        gl.glRotatef(-80,0,0,1);
        gl.glTranslatef(0,0,0.5f);
        gl.glRotatef(195,0,1,0);
        drawSphere(drawable, 0.13f, 24, 0f, 0f, 1.0f);
        gl.glPopMatrix();
        
        gl.glTranslatef(-0.5f, 0, 0);
        drawSphere(drawable, 0.13f, 24, 0.2f, 0.2f, 0.2f);
        
        gl.glPushMatrix();
        gl.glRotatef(-15,0,1,0);
        drawCylinder(drawable, 0.04f, 14, 0.5f);
        gl.glTranslatef(0,0,0.5f);
        gl.glRotatef(15,0,1,0);
        drawSphere(drawable, 0.13f, 24, 0f, 0f, 1.0f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glRotatef(15,0,1,0);
        gl.glRotatef(-120,1,0,0);
        gl.glRotatef(-110,0,0,1);
        drawCylinder(drawable, 0.04f, 14, 0.5f);
        gl.glRotatef(110,0,0,1);
        gl.glTranslatef(0,0,0.5f);
        gl.glRotatef(120,1,0,0);
        gl.glRotatef(-15,0,1,0);
        drawSphere(drawable, 0.13f, 24, 0f, 0f, 1.0f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glRotatef(15,0,1,0);
        gl.glRotatef(120,1,0,0);
        gl.glRotatef(-40,0,0,1);
        drawCylinder(drawable, 0.04f, 14, 0.5f);
        gl.glRotatef(40,0,0,1);
        gl.glTranslatef(0,0,0.5f);
        gl.glRotatef(-120,1,0,0);
        gl.glRotatef(-15,0,1,0);
        drawSphere(drawable, 0.13f, 24, 0f, 0f, 1.0f);
        gl.glPopMatrix();
    }
    
    private void drawSphere(GLAutoDrawable drawable, float Radius, int N, float r, float g, float b) {
        GL gl = drawable.getGL();
        
        float alpha = 0;
        float da = (float)(Math.PI*2/N);
        float beta = (float) -Math.PI/2;
        float db = (float)(Math.PI/N/2);
        float x,y,z;
        float R = Radius;
        
        while(beta < Math.PI / 2) {
            alpha = 0;
            gl.glBegin(GL.GL_QUAD_STRIP);
                while(alpha < Math.PI*2+da) {
                    float offset = 0.25f;
                    float colorAngle = Math.cos(alpha-Math.PI/3.2f) >= 0 ? 
                            (float)Math.cos(alpha-Math.PI/3.2f) : 0;
                    float red = (float)((colorAngle + offset)*r);
                    float green = (float)((colorAngle + offset)*g);
                    float blue = (float)((colorAngle + offset)*b);
                    gl.glColor3f(red,green,blue);
                    x = (float)(R*Math.cos(alpha)*Math.cos(beta));
                    y = (float)(R*Math.sin(alpha)*Math.cos(beta));
                    z = (float)(R*Math.sin(beta));
                    gl.glVertex3f(x,y,z);
                    x = (float)(R*Math.cos(alpha)*Math.cos(beta+db));
                    y = (float)(R*Math.sin(alpha)*Math.cos(beta+db));
                    z = (float)(R*Math.sin(beta+db));
                    gl.glVertex3f(x,y,z);
                    alpha += da;
                }
            gl.glEnd();
            beta += db;
        }
    }
    
    private void drawCylinder(GLAutoDrawable drawable, float Radius, int N, float length) {
        GL gl = drawable.getGL();
        
        float beta = 0;
        float db = (float)(length/N/2);
        float R = Radius;
        float alpha;
        float da = (float)(2*Math.PI/N);
        float x,y;
        
        while(beta < length) {
            alpha = 0;
            gl.glBegin(GL.GL_QUAD_STRIP);
                while(alpha < 2 * Math.PI+da) {
                    float offset = 0.35f;
                    float colorAngle = Math.cos(alpha-Math.PI/3.2f) >= 0 ? 
                            (float)Math.cos(alpha-Math.PI/3.2f) : 0;
                    float red = (float)((colorAngle + offset)*0.5f);
                    float green = (float)((colorAngle + offset)*0.5f);
                    float blue = (float)((colorAngle + offset)*0.5f);
                    gl.glColor3f(red,green,blue);
                    x = (float)(R*Math.cos(alpha));
                    y = (float)(R*Math.sin(alpha));
                    gl.glVertex3f(x,y,beta);
                    gl.glVertex3f(x,y,beta+db);
                    alpha += da;
                }
            gl.glEnd();
            beta += db;
        }
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

