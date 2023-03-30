public class Planet {

    private static final double G = 6.67e-11;
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                        double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos-xxPos) * (p.xxPos-xxPos) + (p.yyPos - yyPos) * (p.yyPos-yyPos));
    }
    public double calcForceExertedBy(Planet p) {
        double dist = calcDistance(p);
        return G * mass * p.mass / dist / dist;
    }
    public double calcForceExertedByX(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * (- xxPos + p.xxPos) / dist;
    }
    public double calcForceExertedByY(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * (p.yyPos - yyPos) / dist;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double force = 0.;
        for (Planet p: allPlanets){
            if (!equals(p)) {
                force += calcForceExertedByX(p);
            }
        }
        return force;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double force = 0.;
        for (Planet p: allPlanets){
            if (!equals(p)) {
                force += calcForceExertedByY(p);
            }
        }
        return force;
    }
    public void update(double dt, double fX, double fY) {
        xxVel += fX / mass * dt;
        yyVel += fY / mass * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}