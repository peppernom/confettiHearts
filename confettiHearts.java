import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class confettiHearts extends PApplet {

ArrayList psystems;

public void setup() {
  textFont(createFont("SansSerif", 25));
  noStroke();
  smooth();
  size(640, 360);
  colorMode(RGB, 255, 255, 255, 100);
  psystems = new ArrayList();
  smooth();
}

public void draw() {
  background(255, 255, 255);
  for (int i = psystems.size() - 1; i >= 0; i--) {
    ParticleSystem psys = (ParticleSystem) psystems.get(i);
    psys.run();
  }
}

public void mousePressed() {
  psystems.add(new ParticleSystem(50, new PVector(mouseX,mouseY)));
}

class Particle {
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float randomSize;
  float timer;
  
  Particle(PVector l) {
    acc = new PVector(0, 0.05f, 0);
    vel = new PVector(random(-1, 1), random(-2, 0), 0);
    loc = l.get();
    r = 10.0f;
    randomSize = random(20, 60);
    timer = 50;
  }

  public void run() {
    update();
    render();
  }

  public void update() {
    vel.add(acc);
    loc.add(vel);
    timer -= 0.35f;
  }

  public void render() {
    textSize(randomSize);
    fill(random(0, 255), random(0, 255), random(0, 255), timer);
    text("\u2665", loc.x, loc.y);
  }
}

class ParticleSystem {
  ArrayList particles;
  PVector origin;
  ParticleSystem(int num, PVector v) {
    particles = new ArrayList();
    origin = v.get();
    for (int i = 0; i < num; i++) {
        particles.add(new Particle(origin)); 
      }
    }

  public void run() {
    for (int i = particles.size() - 1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
    }
  }

  public void addParticle() {
    particles.add(new Particle(origin));
  }

  public void addParticle(Particle p) {
    particles.add(p);
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "confettiHearts" });
  }
}
