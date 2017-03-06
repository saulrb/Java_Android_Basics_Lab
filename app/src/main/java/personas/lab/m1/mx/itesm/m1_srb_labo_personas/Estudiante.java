package personas.lab.m1.mx.itesm.m1_srb_labo_personas;

import java.text.DecimalFormat;

/**
 * Created by saul on 18/1/2017.
 */

public class Estudiante {
    protected int matricula;
    protected String nombre;
    protected String carrera;
    protected  double colegiatura;

    public Estudiante() {
       super();
    }

    public Estudiante(int matricula, String nombre, String carrera, double colegiatura) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.carrera = carrera;
        this.colegiatura = colegiatura;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setColegiatura(double colegiatura) {
        this.colegiatura = colegiatura;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public double getColegiatura() {
        return colegiatura;
    }

    public String mostrar(){
        DecimalFormat df = new DecimalFormat("0.00");
        return "Alumno : ".concat(this.nombre).concat(" tiene la matricula:").concat(String.valueOf(this.matricula))
                .concat(" estudia:").concat(this.carrera).concat(" con la colegiatura de: $ ").concat(df.format(this.colegiatura));
    }
}
