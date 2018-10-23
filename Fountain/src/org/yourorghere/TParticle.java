/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import javax.media.opengl.GL;
import java.math.*;
/**
 *
 * @author Anton
 */
public class TParticle {
    private float x, y, z, dx, dy, dz, pointSize, blue, originalX, originalY, diameter;
    
    public TParticle(float x, float y, float d) {
        originalX = x;
        originalY = y;
        diameter = d;
        Init();
    }
    
    private void Init() {
        x = originalX;
        y = originalY;
        z = -0.9f;
        
        dx = (float)(Math.random()*0.001-0.0005) * diameter;
        dy = (float)(Math.random()*0.001-0.0005) * diameter;
        dz = 0.01f;
        
        blue = (float)(Math.random());
        
        pointSize = 6;
    }
    
    public void Draw(GL gl) {
        gl.glPointSize(pointSize);
        gl.glColor3f(0, 0, blue);
        gl.glBegin(GL.GL_POINTS);
            gl.glVertex3f(x, y, z);
        gl.glEnd();
    }
    
    public void Move() {
        x = x + dx;
        y = y + dy;
        if(z>=-0.9f) {
            z = z + dz;
        }
        
        dz = dz - 0.00003f;
    }
}
