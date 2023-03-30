public class NBody {
    public static double readRadius(String fpath) {
        In file = new In(fpath);
        file.readInt();
        return file.readDouble();
    }

    public static Planet[] readPlanets(String fpath) {
        In file = new In(fpath);
        int n = file.readInt();
        file.readDouble();
        Planet[] planets = new Planet[n];
        double xPos, yPos, xVel, yVel, mass;
        String imgfname;
        for (int i=0; i < n; i++) {
            xPos = file.readDouble();
            yPos = file.readDouble();
            xVel = file.readDouble();
            yVel = file.readDouble();
            mass = file.readDouble();
            imgfname = file.readString();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgfname);
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int n = planets.length;

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);

        StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
        for (Planet planet : planets) {
            planet.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

        double [] xForces = new double[n];
        double [] yForces = new double[n];
        for (double t=0; t < T; t += dt) {
            StdDraw.clear();
            for (int i = 0; i < n; i ++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i=0; i<n; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}