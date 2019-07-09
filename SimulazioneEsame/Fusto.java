package SimulazioneEsame;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.Point3f;
import java.awt.*;

public class Fusto extends Shape3D {
    public static final float TOP = 2.0f;
    public static final float BOTTOM = -2.0f;
    protected Point3f[] v = null;
    protected TriangleStripArray triangleStrip;
    protected PolygonAttributes polyAttrbutes;
    protected Appearance appearance;
    protected Geometry geometry;
    protected Material material;

    public Fusto(int steps, float radius) {
        v = new Point3f[(steps + 1) * 2];
        for (int i = 0; i < steps; i++) {
            double angle = 2.0 * Math.PI * (double) i / (double) steps;
            float x = (float) Math.sin(angle) * radius;
            float y = (float) Math.cos(angle) * radius;

            v[i * 2 + 0] = new Point3f(x, y, BOTTOM);
            v[i * 2 + 1] = new Point3f(x, y, TOP);
        }

        v[steps * 2 + 0] = new Point3f(0.0f, radius, BOTTOM);
        v[steps * 2 + 1] = new Point3f(0.0f, radius, TOP);

        geometry = createGeometry(steps);
        appearance = createAppearance();
        setGeometry(geometry);
        setAppearance(appearance);
    }

    protected Geometry createGeometry(int steps) {
        int[] stripCounts = {(steps + 1) * 2};
        triangleStrip = new TriangleStripArray((steps + 1) * 2,
                GeometryArray.COORDINATES, stripCounts);
        triangleStrip.setCoordinates(0, v);
        triangleStrip.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
        triangleStrip.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        GeometryInfo geometryInfo = new GeometryInfo(triangleStrip);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(geometryInfo);
        setGeometry(triangleStrip);
        setGeometry(geometryInfo.getGeometryArray());
        return geometryInfo.getGeometryArray();
    }

    protected Appearance createAppearance() {
        polyAttrbutes = new PolygonAttributes();
        appearance = new Appearance();
        material = new Material();
        // Impostazione aspetto wireframe
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polyAttrbutes);
        material.setCapability(Material.ALLOW_COMPONENT_WRITE);
        material.setCapability(Material.ALLOW_COMPONENT_READ);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        appearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
        appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

        // Caricamento Texture
        TextureLoader loader = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\wood.jpg", "RGB", new Container());
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