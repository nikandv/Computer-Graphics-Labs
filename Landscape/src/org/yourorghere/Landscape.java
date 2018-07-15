package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Landscape.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Landscape implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Landscape");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Landscape());
        frame.add(canvas);
        frame.setSize(700, 700);
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
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glEnable(GL.GL_DEPTH_TEST);
        glu.gluPerspective(90, 1, 0.01f, 100f);
        glu.gluLookAt(1,1,1,0,0,0,0,0,1);

        float controlPoints[] = {
           -0.9f, -0.9f, 1f,   -0.54f, -0.9f, 1f,
           -0.18f, -0.9f, 0f,   0.18f, -0.9f, 1f,
           0.54f, -0.9f, 0.5f,   0.9f, -0.9f, -0.12f,
           
           -0.9f, -0.54f, -1f,   -0.54f, -0.54f, 0f,
           -0.18f, -0.54f, 1f,   0.18f, -0.54f, 0f,
           0.54f, -0.54f, 0f,   0.9f, -0.54f, 0f,
           
           -0.9f, -0.18f, -1f,   -0.54f, -0.18f, -1f,
           -0.18f, -0.18f, -1f,   0.18f, -0.18f, -1f,
           0.54f, -0.18f, -1f,   0.9f, -0.18f, -1f,
           
           -0.9f, 0.18f, 1f,   -0.54f, 0.18f, 0f,
           -0.18f, 0.18f, 0f,   0.18f, 0.18f, -1f,
           0.54f, 0.18f, -1f,   0.9f, 0.18f, -1f,
           
           -0.9f, 0.54f, 0.5f,   -0.54f, 0.54f, 0.5f,
           -0.18f, 0.54f, 1f,   0.18f, 0.54f, 0f,
           0.54f, 0.54f, 0f,   0.9f, 0.54f, 0f,
           
           -0.9f, 0.9f, 0f,   -0.54f, 0.9f, 0f,
           -0.18f, 0.9f, 0f,   0.18f, 0.9f, 0f,
           0.54f, 0.9f, 0.4f,   0.9f, 0.9f, -0.2f
        };
        
        FloatBuffer fb;
        fb=FloatBuffer.wrap(controlPoints);
        gl.glColor3f(0.2f,1,0.2f);
        gl.glMap2f(GL.GL_MAP2_VERTEX_3, 0, 1, 3, 6, 0, 1, 18, 6, fb);   
        gl.glEnable(GL.GL_MAP2_VERTEX_3);
        
        gl.glMapGrid2f(20, 0, 1, 20, 0, 1);
        gl.glEvalMesh2(GL.GL_FILL, 0, 20, 0, 20);
        gl.glTranslated(0, 0, 0.001); 
        gl.glColor3f(0.2f,0.8f,0.2f); 
        gl.glMapGrid2f(20, 0, 1, 20, 0, 1); 
        gl.glEvalMesh2(GL.GL_LINE, 0, 20, 0, 20);
        
        int i;
        gl.glPointSize(12);
        gl.glBegin(GL.GL_POINTS);
            for(i=0;i<36;i++) {
                //gl.glVertex3f(controlPoints[i*3], controlPoints[i*3+1], controlPoints[i*3+2]);
            }
        gl.glEnd();
        
        gl.glColor3f(0,0,1);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f(-1.2f,-1f,-0.2f);
            gl.glVertex3f(1.2f,-1f,-0.2f);
            gl.glVertex3f(1.2f,0.8f,-0.2f);
            gl.glVertex3f(-1.2f,0.8f,-0.2f);
        gl.glEnd();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

