package Esercizio_4_3;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

public class Earth extends Applet {
    public Earth() {
        setLayout(new BorderLayout(1024, 1080));
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        createViewBranch(simpleU);
        simpleU.addBranchGraph(scene);
    }

    // Funzione che crea il sottografo
    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        TransformGroup TG = createSubGraph();

        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // mouse behaviour
        MouseRotate mouse = new MouseRotate(TG);
        mouse.setSchedulingBounds(new BoundingSphere());
        // key nav
        KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(TG); //Imposto il bound del behavior
        keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0)); //Aggiungo il behavior alla scena
        TG.addChild(keyNav);
        TG.addChild(mouse);
        node.addChild(TG);
        // Add directionalLight
        node.addChild(directionalLight());
        // Add Background
        node.addChild(createBackground());
        return node;
    }

    public TransformGroup createViewBranch(SimpleUniverse simpleU) {
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D vanishingPoints = new Transform3D();
        Point3d eye = new Point3d(2.0d, 2.0d, -6.0d); // coordinate dell’osservatore.
        Point3d center = new Point3d(0.0, 0.0, 0.0); // coordinate del punto verso cui guardare.
        Vector3d up = new Vector3d(0.0, 1.0, 0.0); // direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public Background createBackground() {
        // Creazione BACKGROUND
        TextureLoader loader = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\StarsHearth.jpg", "RGB", new Container());
        ImageComponent2D image = loader.getImage();
        // Impostazione del BACKGROUND
        Background background = new Background();
        background.setImage(image);
        // L'immagine di sfondo viene ridimensionata in modo uniforme per adattarsi
        // alla finestra in modo tale da riempire l'intera finestra.
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 1000.0);
        background.setApplicationBounds(boundingSphere);
        return background;
    }

    public DirectionalLight directionalLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 10.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        // Impostazione del colore della luce
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        lightD1.setColor(white);
        // Impostazione della direzione della luce
        lightD1.setDirection(new Vector3f(0.0f, -1.2f, 1.3f));
        // Impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        // Nodo Luna
        TransformGroup TGLuna = new TransformGroup();
        Transform3D T3DLuna = new Transform3D();
        T3DLuna.setTranslation(new Vector3d(-1.5d, 0.3d, 0.0d));
        TGLuna.setTransform(T3DLuna);
        TGLuna.addChild(new Luna());
        // Nodo Terra
        TransformGroup TGTerra = new TransformGroup();
        TGTerra.addChild(new Terra());
        transform.addChild(moonRotation(TGLuna));
        transform.addChild(earthRotation(TGTerra));
        return transform;
    }

    public TransformGroup moonRotation(TransformGroup Luna) {
        // TransformGroup generale
        TransformGroup orbit = new TransformGroup();
        // TransformGroup relativo alla posizione della Luna
        TransformGroup moonPosition = new TransformGroup();
        // TransformGroup relativo all'orbita della Luna
        TransformGroup moonOrbit = new TransformGroup();
        // Transform3D relativo all'orbita della luna
        Transform3D lunaOrbit = new Transform3D();
        lunaOrbit.rotY(-Math.PI / 4.0f);
        // Alpha(int loopCount, long increasingAlphaDuration)
        Alpha alpha = new Alpha(-1, 10000);
        //RotationInterpolator cambia l'orientamento di un oggetto (alpha, target, trasformazione, angolo partenza, angolo arrivo)
        RotationInterpolator rotationInterpolator = new RotationInterpolator(alpha, moonOrbit, lunaOrbit, 0.0f, (float) Math.PI * 2.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.d, 0.d, 0.d), 100.d);
        rotationInterpolator.setSchedulingBounds(bounds);
        moonOrbit.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        moonOrbit.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        orbit.addChild(Luna);
        moonPosition.addChild(orbit);
        moonOrbit.addChild(moonPosition);
        moonOrbit.addChild(rotationInterpolator);
        return moonOrbit;
    }

    public TransformGroup earthRotation(TransformGroup Terra) {
        TransformGroup earthOrbit = new TransformGroup();
        Transform3D t3dEarth = new Transform3D();
        t3dEarth.rotY(-Math.PI / 4.0f);
        Transform3D T3D = new Transform3D();
        // Asse terrestre attorno a cui la Terra compie il suo moto di rotazione
        // L'asse terrestre è inclinato di 23° 27' rispetto alla perpendicolare al piano dell'eclittica.
        T3D.rotZ(-Math.PI / 7.73f);
        t3dEarth.mul(T3D);
        // Alpha(int loopCount, long increasingAlphaDuration)
        Alpha alpha = new Alpha(-1, 10000);
        //RotationInterpolator cambia l'orientamento di un oggetto (alpha, target, trasformazione, angolo partenza, angolo arrivo)
        RotationInterpolator rotationInterpolator = new RotationInterpolator(alpha, earthOrbit, t3dEarth, 0.0f, (float) Math.PI * 2.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.d, 0.d, 0.d), 100.d);
        rotationInterpolator.setSchedulingBounds(bounds);
        earthOrbit.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        earthOrbit.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        earthOrbit.addChild(Terra);
        earthOrbit.addChild(rotationInterpolator);
        return earthOrbit;
    }

    public static void main(String[] args) {
        new MainFrame(new Earth(), 1024, 1080);
    }
}
