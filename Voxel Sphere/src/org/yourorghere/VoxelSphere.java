package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * VoxelSphere.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class VoxelSphere implements GLEventListener {

    private float voxelWidth;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new VoxelSphere());
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
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        gl.glRotatef(-120, 1, 0, 0);
        gl.glRotatef(35, 0, 0, 1);

        float R=0.7f;
        //PLEASE BE PATIENT, the greater N means slower drawing
        int N=40;
        voxelWidth = (float)(2.0f*R/N);
        float x=-R,y,z;

        //drawVoxelf(drawable,0.5f,0f,0.5f);
        while(x<R) {
            y = -R;
            while(y<R) {
                z = -R;
                while(z<R) {
                    System.out.println(x*x+y*y+z*z);
                    if(x*x+y*y+z*z<=R*R) {
                        System.out.print("1");
                        drawVoxelf(drawable,x,y,z);
                    }
                    z += voxelWidth;
                }
                y += voxelWidth;
            }
            x += voxelWidth;
        }
        
    }

    private void drawVoxelf(GLAutoDrawable drawable, float x, float y, float z) {
        GL gl = drawable.getGL();
        float _x = x + voxelWidth/2;
        float _y = y + voxelWidth/2;
        float _z = z + voxelWidth/2;
        
        gl.glColor3f(0.8f, 0.8f, 0.8f);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f( _x,  _y,  _z);
            gl.glVertex3f( _x, -_y,  _z);
            gl.glVertex3f( _x, -_y, -_z);
            gl.glVertex3f( _x,  _y, -_z);
            gl.glVertex3f(-_x,  _y,  _z);
            gl.glVertex3f(-_x, -_y,  _z);
            gl.glVertex3f(-_x, -_y, -_z);
            gl.glVertex3f(-_x,  _y, -_z);
            gl.glVertex3f( _x, -_y,  _z);
            gl.glVertex3f(-_x, -_y,  _z);
            gl.glVertex3f(-_x, -_y, -_z);
            gl.glVertex3f( _x, -_y, -_z);
            gl.glVertex3f( _x,  _y, -_z);
            gl.glVertex3f( _x, -_y, -_z);
            gl.glVertex3f(-_x, -_y, -_z);
            gl.glVertex3f(-_x,  _y, -_z);
            gl.glVertex3f( _x,  _y,  _z);
            gl.glVertex3f( _x, -_y,  _z);
            gl.glVertex3f(-_x, -_y,  _z);
            gl.glVertex3f(-_x,  _y,  _z);
        gl.glEnd();
        gl.glColor3f(1,1,1);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f( _x, _y,  _z);
            gl.glVertex3f( _x, _y, -_z);
            gl.glVertex3f(-_x, _y, -_z);
            gl.glVertex3f(-_x, _y,  _z);
        gl.glEnd();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

