package Esercizio_4_4;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import java.awt.*;

public class Fusto extends Cylinder {

    public Fusto(float radius, float height) {
        // Cylinder(float radius, float height, int primflags, int xdivision, int ydivision, Appearance ap)
        super(radius / 9, height, Primitive.GENERATE_NORMALS, 20, 1, createAppearance());
    }

    public static Appearance createAppearance() {
        Appearance app = new Appearance();
        Material material = new Material();
        PolygonAttributes polyAttrbutes = new PolygonAttributes();
        //The PolygonAttributes object defines attributes for rendering polygon primitives.
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        app.setPolygonAttributes(polyAttrbutes);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        // Caricamento Texture
        TextureLoader loader = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\basalto.jpg", "RGB", new Container());
        Texture texture = loader.getTexture();
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.COMBINE);
        TexCoordGeneration texCoordGeneration = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_3);
        app.setTexCoordGeneration(texCoordGeneration);
        app.setTexture(texture);
        app.setTextureAttributes(textureAttributes);
        app.setMaterial(material);
        return app;
    }
}
