/*
 * Fountain1.java
 *
 * Created on 30. Juli 2008, 16:18
 */

package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author cylab
 * @author mbien
 */
public class Fountain1 extends JFrame {

    private GLRenderer renderer;
    
    static {
        // When using a GLCanvas, we have to set the Popup-Menues to be HeavyWeight,
        // so they display properly on top of the GLCanvas
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    private Animator animator;

    /** Creates new form MainFrame */
    public Fountain1() {
        initComponents();
        setTitle("Simple JOGL Application");

        renderer = new GLRenderer();
        canvas.addGLEventListener(renderer);
        animator = new Animator(canvas);

        // This is a workaround for the GLCanvas not adjusting its size, when the frame is resized.
        canvas.setMinimumSize(new Dimension());         
        
        this.addWindowListener(new WindowAdapter() {

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
        animator.start();
    }

    @Override
    public void setVisible(boolean show){
        if(!show)
            animator.stop();
        super.setVisible(show);
        if(!show)
            animator.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new GLCanvas(createGLCapabilites());
        jLabel1 = new JLabel();
        jSlider1 = new JSlider();
        jLabel2 = new JLabel();
        jSlider2 = new JSlider();
        jLabel3 = new JLabel();
        jSeparator1 = new JSeparator();
        jSlider3 = new JSlider();
        jButton1 = new JButton();
        jLabel4 = new JLabel();
        jSlider4 = new JSlider();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 550));

        jLabel1.setText("���������� ������");

        jSlider1.setMaximum(6000);
        jSlider1.setMinimum(100);
        jSlider1.setToolTipText("");
        jSlider1.setValue(3000);

        jLabel2.setText("������� �����");

        jSlider2.setMaximum(50);
        jSlider2.setMinimum(1);
        jSlider2.setValue(5);
        jSlider2.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                jSlider2PropertyChange(evt);
            }
        });

        jLabel3.setText("������� ���������� �������� �������");

        jSlider3.setMaximum(70);
        jSlider3.setMinimum(10);
        jSlider3.setValue(40);
        jSlider3.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                jSlider3PropertyChange(evt);
            }
        });

        jButton1.setLabel("�������������");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("�������� �������� �������");

        jSlider4.setMaximum(90);
        jSlider4.setMinimum(10);
        jSlider4.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                jSlider4PropertyChange(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jSeparator1, Alignment.TRAILING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSlider1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(123, 123, 123))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(54, 54, 54))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(jSlider2, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(jSlider4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                        .addComponent(jSlider3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(40, 40, 40))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(106, 106, 106))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(130, 130, 130))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(89, 89, 89))))))
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(jSlider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jSlider2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jSlider3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jSlider4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jButton1))
                    .addComponent(canvas, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        renderer.start(jSlider1.getValue());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider3PropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_jSlider3PropertyChange
        // TODO add your handling code here:
        if(renderer != null) {
            renderer.updateRadius(jSlider3.getValue());
        }
    }//GEN-LAST:event_jSlider3PropertyChange

    private void jSlider2PropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_jSlider2PropertyChange
        // TODO add your handling code here:
        if(renderer != null) {
            renderer.updateFlowDiameter(jSlider2.getValue());
        }
    }//GEN-LAST:event_jSlider2PropertyChange

    private void jSlider4PropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_jSlider4PropertyChange
        // TODO add your handling code here:
        if(renderer != null) {
            renderer.updateSpeed(jSlider4.getValue());
        }
    }//GEN-LAST:event_jSlider4PropertyChange

    /**
     * Called from within initComponents().
     * hint: to customize the generated code choose 'Customize Code' in the contextmenu
     * of the selected UI Component you wish to cutomize in design mode.
     * @return Returns customized GLCapabilities.
     */
    private GLCapabilities createGLCapabilites() {
        
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);

        // try to enable 2x anti aliasing - should be supported on most hardware
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);
        
        return capabilities;
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        // Run this in the AWT event thread to prevent deadlocks and race conditions
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                // switch to system l&f for native font rendering etc.
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }

                Fountain1 frame = new Fountain1();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GLCanvas canvas;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JSeparator jSeparator1;
    private JSlider jSlider1;
    private JSlider jSlider2;
    private JSlider jSlider3;
    private JSlider jSlider4;
    // End of variables declaration//GEN-END:variables

}
