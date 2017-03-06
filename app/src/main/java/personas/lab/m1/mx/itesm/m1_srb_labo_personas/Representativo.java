package personas.lab.m1.mx.itesm.m1_srb_labo_personas;

/**
 * Created by saul on 18/1/2017.
 */

public class Representativo extends Estudiante {

    private String deporte;

    public Representativo(String deporte) {
        this.deporte = deporte;
    }

    public Representativo(int matricula, String nombre, String carrera, double colegiatura, String deporte) {
        super(matricula, nombre, carrera, colegiatura);
        this.deporte = deporte;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    @Override
    public String mostrar() {
        return "El Alumno:".concat(this.nombre).concat(" tiene la matricula:").concat(String.valueOf(this.matricula)).
                concat(" Y pertenece al equipo representativo de ").concat(this.deporte);
    }
}
