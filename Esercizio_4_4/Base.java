package Esercizio_4_4;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import java.awt.*;

public class Base extends Box {
    public Base(float xdim, float ydim, float zdim){
        // Box(float xdim, float ydim, float zdim, int primflags, Appearance ap)
        super(xdim,ydim,zdim,Primitive.GENERATE_NORMALS, createAppearance());
    }

    public static Appearance createAppearance() {
        PolygonAttributes polyAttrbutes = new PolygonAttributes();
        Appearance appearance = new Appearance();
        Material material = new Material();
        //The PolygonAttributes object defines attributes for rendering polygon primitives.
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polyAttrbutes);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        // Caricamento Texture
        TextureLoader loader = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\basalto.jpg", "RGB", new Container());
        Texture texture = loader.getTexture();
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.COMBINE);
        TexCoordGeneration texCoordGeneration = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_3);
        appearance.setTexCoordGeneration(texCoordGeneration);
        appearance.setTexture(texture);
        appearance.setTextureAttributes(textureAttributes);
        appearance.setMaterial(material);
        return appearance;
    }
}
