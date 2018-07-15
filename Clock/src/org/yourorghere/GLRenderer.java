package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.math.*;
import java.util.Date;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    public int hourDifference, minuteDifference, secondDifference;
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        hourDifference = 0;
        minuteDifference = 0;
        secondDifference = 0;
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //GL gl = drawable.getGL();
        //GLU glu = new GLU();

        //if (height <= 0) { // avoid a divide by zero error!
        
            //height = 1;
        //}
        //final float h = (float) width / (float) height;
        //gl.glViewport(0, 0, width, height);
        //gl.glMatrixMode(GL.GL_PROJECTION);
        //gl.glLoadIdentity();
        //glu.gluPerspective(45.0f, h, 1.0, 20.0);
        //gl.glMatrixMode(GL.GL_MODELVIEW);
        //gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        //White circle
        int N=150;
        float x,y,alpha,da,R;
        alpha = 0;
        R = 0.9f;
        da = (float) (2 * Math.PI / N);

        gl.glColor3f(1.0f,1.0f,1.0f);
        gl.glBegin(GL.GL_POLYGON);
            while(alpha < 2* Math.PI) {
                x = (float) (R * Math.cos(alpha));
                y = (float) (R * Math.sin(alpha));
                gl.glVertex2f(x,y);
                alpha = alpha + da;
            }
        gl.glEnd();
        
        //Big lines around
        int count=0;
        float linesLength;
        linesLength = 0.1f;
        alpha = 0;
        R = 0.85f;
        da = (float) (2 * Math.PI / 12);
        
        gl.glColor3f(0,0,0);
        gl.glLineWidth(5.0f);
        gl.glBegin(GL.GL_LINES);
            while(alpha < 2 * Math.PI) {
                if(count == 0) {
                    x = (float) (R * Math.cos(alpha));
                    y = (float) (R * Math.sin(alpha));
                    gl.glVertex2f(x,y);
                    x = (float) ((R-linesLength*1.4) * Math.cos(alpha));
                    y = (float) ((R-linesLength*1.4) * Math.sin(alpha));
                    gl.glVertex2f(x,y);
                } else {
                    x = (float) (R * Math.cos(alpha));
                    y = (float) (R * Math.sin(alpha));
                    gl.glVertex2f(x,y);
                    x = (float) ((R-linesLength) * Math.cos(alpha));
                    y = (float) ((R-linesLength) * Math.sin(alpha));
                    gl.glVertex2f(x,y);
                }
                //incrementing
                if(count == 2)
                    count = 0;
                else
                    count += 1;
                alpha = alpha + da;
            }
        gl.glEnd();
        
        //Small lines around
        linesLength = 0.05f;
        alpha = 0;
        da = (float) (Math.PI * 2 / 60);
        
        gl.glLineWidth(5.0f);
        gl.glBegin(GL.GL_LINES);
            while(alpha < 2 * Math.PI) {
                x = (float) (R * Math.cos(alpha));
                y = (float) (R * Math.sin(alpha));
                gl.glVertex2f(x,y);
                x = (float) ((R-linesLength) * Math.cos(alpha));
                y = (float) ((R-linesLength) * Math.sin(alpha));
                gl.glVertex2f(x,y);
                //incrementing
                alpha = alpha + da;
            }
        gl.glEnd();
        
        //Black point in the middle
        gl.glPointSize(40.0f);
        gl.glBegin(GL.GL_POINTS);
            gl.glVertex2f(0,0);
        gl.glEnd();
        gl.glPointSize(1.0f);
        
        Date date = new Date();
        updateTimeUI(gl, date.getHours()+hourDifference, 
                         date.getMinutes()+minuteDifference, date.getSeconds()+secondDifference);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void updateTimeUI(GL gl, int hours, int minutes, int seconds) {
        
        //No matter is it 1:00 or 13:00, on the clock it is the same.
        if(hours >= 12) {
            hours -= 12;
        }
        //Angles between arrows and vertical axis.
        float hoursAngle, minutesAngle, secondsAngle;
        hoursAngle = (float) ((-2 * Math.PI * (hours * 60 + minutes) / 720) + Math.PI / 2);
        minutesAngle = (float) ((-2 * Math.PI * (minutes * 60 + seconds) / 3600) + Math.PI / 2);
        secondsAngle = (float) ((-2 * Math.PI * seconds / 60) + Math.PI / 2);
        //Arrows lengths
        float hoursLength, minutesLength, secondsLength;
        hoursLength = 0.4f;
        minutesLength = 0.7f;
        secondsLength = 0.55f;
        
        float x,y;
        
        gl.glLineWidth(13.0f);
        //Drawing hour and minute arrows.
        gl.glBegin(GL.GL_LINES);
            x = (float) (hoursLength*Math.cos(hoursAngle));
            y = (float) (hoursLength*Math.sin(hoursAngle));
            gl.glVertex2f(x, y);
            gl.glVertex2f(0, 0);
            
            x = (float) (minutesLength*Math.cos(minutesAngle));
            y = (float) (minutesLength*Math.sin(minutesAngle));
            gl.glVertex2f(x, y);
            gl.glVertex2f(0, 0);
        gl.glEnd();
        
        //Drawing seconds arrow
        gl.glLineWidth(2.0f);
        gl.glColor3f(1.0f, 0, 0);
        gl.glBegin(GL.GL_LINES);
            x = (float) (secondsLength*Math.cos(secondsAngle));
            y = (float) (secondsLength*Math.sin(secondsAngle));
            gl.glVertex2f(x, y);
            gl.glVertex2f(0, 0);
        gl.glEnd();
        
        //Red point in the middle
        gl.glPointSize(7.0f);
        gl.glBegin(GL.GL_POINTS);
            gl.glVertex2f(0,0);
        gl.glEnd();
        gl.glPointSize(1.0f);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

