package Polygons;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.FPSAnimator;

public class Polygon6 implements GLEventListener {

    public static void init() {
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        GLWindow window = GLWindow.create(caps);

        window.setSize(500,500);
        window.setResizable(false);
        window.setVisible(true);
        window.addGLEventListener(new Polygon6());

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                System.exit(0);
            }
        });

        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = GLContext.getCurrentGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glBegin(GL2.GL_POLYGON);
        int numVertices = 6;
        float radius = 0.5f;

        for(int i=0; i<numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            float x = (float) (radius * Math.cos(angle));
            float y = (float) (radius * Math.sin(angle));
            gl.glVertex3f(x, y, 0.0f);
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {

    }

    public static void main(String[] args) {
        init();
    }

    // Declaring all methods of GLEventListener on the bottom of the class
    @Override
    public void init(GLAutoDrawable drawable){}
}


