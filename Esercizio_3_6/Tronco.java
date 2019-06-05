package Esercizio_3_6;

import javax.media.j3d.*;
import javax.vecmath.Point3d;

public class Tronco extends Shape3D {
    protected QuadArray quadArray = null;
    protected PolygonAttributes polyAttrbutes = new PolygonAttributes();
    protected Appearance appearance = new Appearance();
    protected Point3d verts[] = new Point3d[24];

    public Tronco() {
        this(0.6, 0.2, 0.1);
    }

    public Tronco(double scala) {
        this(0.6 * scala, 0.2 * scala, 0.1 * scala);
    }

    public Tronco(double size, double height, double scost) {
        Point3d sx = new Point3d(-size + scost, height, size - scost); // angolo in alto a sx
        Point3d dx = new Point3d(size - scost, height, size - scost); // angolo in alto a dx
        Point3d bsx = new Point3d(-size, -height, size); // angolo in basso a sx
        Point3d bdx = new Point3d(size, -height, size); // angolo in basso a dx
        Point3d sxPostAlto = new Point3d(-size + scost, height, -size + scost); // angolo posteriore in alto a sx
        Point3d dxPostAlto = new Point3d(size - scost, height, -size + scost); // angolo posteriore in alto a dx
        Point3d sxPostBasso = new Point3d(-size, -height, -size); // angolo posteriore in basso a sx
        Point3d dxPostBasso = new Point3d(size, -height, -size); // angolo posteriore in basso a dx

        // Facciata anteriore
        verts[0] = sx;
        verts[1] = dx;
        verts[2] = bdx;
        verts[3] = bsx;
        // Facciata posteriore
        verts[4] = sxPostAlto;
        verts[5] = dxPostAlto;
        verts[6] = dxPostBasso;
        verts[7] = sxPostBasso;
        // Facciata superiore
        verts[8] = sxPostAlto;
        verts[9] = dxPostAlto;
        verts[10] = dx;
        verts[11] = sx;
        // Facciata laterale sx
        verts[12] = sx;
        verts[13] = sxPostAlto;
        verts[14] = sxPostBasso;
        verts[15] = bsx;
        // Facciata laterale dx
        verts[16] = dx;
        verts[17] = dxPostAlto;
        verts[18] = dxPostBasso;
        verts[19] = bdx;
        // Facciata inferiore
        verts[20] = bsx;
        verts[21] = bdx;
        verts[22] = dxPostBasso;
        verts[23] = sxPostBasso;

        quadArray = new QuadArray(verts.length, QuadArray.COORDINATES);
        quadArray.setCoordinates(0, verts);
        setGeometry(quadArray);

        //The PolygonAttributes object defines attributes for rendering polygon primitives.
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);

        appearance.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.40f));
        appearance.setPolygonAttributes(polyAttrbutes);

        //Colore piramide
        ColoringAttributes color = new ColoringAttributes();
        color.setColor(1.0f, 0.6f, 0.2f);
        appearance.setColoringAttributes(color);
        setAppearance(appearance);
    }
}
