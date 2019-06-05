package Esercizio_4_3;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import java.awt.*;

public class Terra extends Group {
    public Terra() {
        // Sphere(float radius, int primflags, int divisions, Appearance ap)
        Sphere earth = new Sphere(0.8f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 1500, createAppearance());
        addChild(earth);
    }

    public static Appearance createAppearance() {
        Appearance appearance = new Appearance();
        Material material = new Material();
        material.setShininess(90.0f);
        material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
        appearance.setMaterial(material);
        // Caricamento Texture
        TextureLoader loaderEarth = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\Terra3.jpg", "RGB", new Container());
        Texture texture = loaderEarth.getTexture();
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.COMBINE);
        appearance.setTexture(texture);
        appearance.setTextureAttributes(textureAttributes);
        return appearance;
    }
}
