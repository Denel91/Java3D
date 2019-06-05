package Esercizio_4_4;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.Point3f;
import java.awt.*;

public class Timpano extends Shape3D {
    protected Geometry geometry;
    protected Appearance appearance;
    protected Material material;
    protected Point3f[] faces;
    protected PolygonAttributes polyAttrbutes;

    public Timpano(float width, float height, float depth) {
        final Point3f P0 = new Point3f(-width / 2, -height / 2, depth / 2);
        final Point3f P1 = new Point3f(0, height / 2, depth / 2);
        final Point3f P2 = new Point3f(width / 2, -height / 2, depth / 2);
        final Point3f P3 = new Point3f(width / 2, -height / 2, -depth / 2);
        final Point3f P4 = new Point3f(0, height / 2, -depth / 2);
        final Point3f P5 = new Point3f(-width / 2, -height / 2, -depth / 2);

        faces = new Point3f[]{
                P0, P1, P2,
                P2, P3, P1,
                P1, P4, P3,
                P3, P4, P5,
                P5, P4, P1,
                P1, P5, P0,
                P0, P5, P3,
                P3, P2, P0
        };

        geometry = createGeometry();
        appearance = createAppearance();
        setGeometry(geometry);
        setAppearance(appearance);
    }

    protected Geometry createGeometry() {
        int[] stripCounts = {3, 3, 3, 3, 3, 3, 3, 3};
        TriangleStripArray triangleStripArray = new TriangleStripArray(faces.length, GeometryArray.COORDINATES, stripCounts);
        triangleStripArray.setCoordinates(0, faces);
        triangleStripArray.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
        triangleStripArray.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        GeometryInfo geometryInfo = new GeometryInfo(triangleStripArray);
        geometryInfo.setCoordinates(faces);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(geometryInfo);
        setGeometry(triangleStripArray);
        setGeometry(geometryInfo.getGeometryArray());
        return geometryInfo.getGeometryArray();
    }

    protected Appearance createAppearance() {
        polyAttrbutes = new PolygonAttributes();
        appearance = new Appearance();
        material = new Material();

        //The PolygonAttributes object defines attributes for rendering polygon primitives.
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polyAttrbutes);
        material.setCapability(Material.ALLOW_COMPONENT_WRITE);
        material.setCapability(Material.ALLOW_COMPONENT_READ);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setLightingEnable(true);
        appearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
        appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

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
