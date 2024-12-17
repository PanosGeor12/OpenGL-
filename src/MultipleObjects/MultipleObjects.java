package MultipleObjects;

import java.util.Random;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
/*import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext; //new
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;*/
import com.jogamp.opengl.*; // Αντί των γραμμών 8 - 12
import com.jogamp.opengl.util.FPSAnimator;

public class MultipleObjects implements GLEventListener{
    private static GLWindow window;
    Random rand = new Random();

    public static void init() {
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        window = GLWindow.create(capabilities);
        window.setSize(500, 500);
        window.setResizable(false);
        window.setVisible(true);
        window.addGLEventListener(new MultipleObjects());

        window.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowDestroyNotify(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();
    }

    public static void main(String[] args) {
        init();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        String[] shapes = new String[4];
        shapes[0] = "GL2.GL_TRIANGLES";
        shapes[1] = "GL2.GL_POLYGONS";
        shapes[2] = "GL2.GL_QUADS";
        GL2 gl = GLContext.getCurrentGL().getGL2();
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        for(int j=0; j<10; j++) {
            gl.glColor3f(0.0f, 0.0f, 1.0f);
            gl.glPushMatrix();

            int shapeChoice = rand.nextInt(0,3);
            int shape = Integer.parseInt(shapes[shapeChoice]);

            float offsetX = 2.0f * rand.nextFloat(-1.0f,1.0f) - 1.0f;
            float offsetY = 2.0f * rand.nextFloat(-1.0f, 1.0f) - 1.0f;

            gl.glTranslatef(offsetX,offsetY,0.0f);

            gl.glBegin(GL2.GL_TRIANGLES);
            int numVertices = 3;
            float radius = 0.3f;

            for (int i = 0; i < numVertices; i++) {
                double angle = 2 * Math.PI * i / numVertices;
                float x = (float) (radius * Math.cos(angle));
                float y = (float) (radius * Math.sin(angle));
                gl.glVertex3f(x, y, 0.0f);
            }
            gl.glEnd();
            gl.glPopMatrix();
        }
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

    }
    @Override
    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        System.out.println("Initialized!");

    }
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO Auto-generated method stub

    }

}

