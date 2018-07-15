package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.math.*;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    public float rotateX;
    public float rotateZ;
    public boolean isWired;
    public boolean isShaded;
    public boolean isFaceted;
    public float radius;
    public float height;
    private int canvasHeight;
    private int canvasWidth;
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);
        
        rotateX = -120;
        rotateZ = 35;
        isWired = false;
        isShaded = true;
        isFaceted = false;
        radius = 0.2f;
        height = 1.2f;

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        canvasWidth = width;
        canvasHeight = height;
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glViewport(0, canvasHeight/2, canvasWidth/2, canvasHeight/2);
        gl.glScissor(0, canvasHeight/2, canvasWidth/2, canvasHeight/2);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glLoadIdentity();
        gl.glRotatef(0, 1, 0, 0);
        gl.glRotatef(0, 0, 0, 1);

        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        drawFigure(gl, radius, 32, height, false);
        
        gl.glViewport(canvasWidth/2, canvasHeight/2, canvasWidth/2, canvasHeight/2);
        gl.glScissor(canvasWidth/2, canvasHeight/2, canvasWidth/2, canvasHeight/2);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glLoadIdentity();
        gl.glRotatef(-90, 1, 0, 0);
        gl.glRotatef(0, 0, 0, 1);

        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        drawFigure(gl, radius, 32, height, false);
        
        gl.glViewport(0, 0, canvasWidth/2, canvasHeight/2);
        gl.glScissor(0, 0, canvasWidth/2, canvasHeight/2);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glLoadIdentity();
        gl.glRotatef(-90, 1, 0, 0);
        gl.glRotatef(90, 0, 0, 1);

        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        drawFigure(gl, radius, 32, height, false);
        
        gl.glViewport(canvasWidth/2, 0, canvasWidth/2, canvasHeight/2);
        gl.glScissor(canvasWidth/2, 0, canvasWidth/2, canvasHeight/2);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glLoadIdentity();
        gl.glRotatef(rotateX, 1, 0, 0);
        gl.glRotatef(rotateZ, 0, 0, 1);

        if(isWired) {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        } else {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        }
        drawFigure(gl, radius, 32, height, isShaded);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    private void drawFigure(GL gl, float Rc, int N, float height, boolean shaded) {
        if(isFaceted) {
            N /= 3;
        }
        float z = -height/2f;
        float dz = (float)(height/N);
        float x, y, R;
        
        while(z<height/2f) {
            float l = 0;
            float dl = (float)(2*Math.PI/N);
            gl.glBegin(GL.GL_QUAD_STRIP);
                while(l <= 2 * Math.PI + dl) {
                    if(shaded) {
                        gl.glColor3f((float)Math.abs(Math.cos(l)), (float)Math.abs(Math.cos(l)),
                            (float)Math.abs(Math.cos(l)));
                    } else {
                        gl.glColor3f(1, 1, 1);
                    }
                    R = Rc * (float)(1 - 0.3*Math.sin(2*z*Math.PI));
                    x = (float)(R * Math.sin(l));
                    y = (float)(R * Math.cos(l));
                    gl.glVertex3f(x,y,z);
                    R = Rc * (float)(1 - 0.3*Math.sin(2*(z+dz)*Math.PI));
                    x = (float)(R * Math.sin(l));
                    y = (float)(R * Math.cos(l));
                    gl.glVertex3f(x,y,z+dz);
                    l += dl;
                }
            gl.glEnd();
            z += dz;
        }
        
        float a = 0, da = (float)(2 * Math.PI/N);
        z = -height/2f;
        gl.glBegin(GL.GL_POLYGON);
            while(a < Math.PI * 2) {
                gl.glColor3f(0.1f, 0.1f, 0.1f);
                R = Rc * (float)(1 - 0.3*Math.sin(2*z*Math.PI));
                x = (float)(R * Math.sin(a));
                y = (float)(R * Math.cos(a));
                gl.glVertex3f(x, y, z);
                a += da;
            }
        gl.glEnd();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

