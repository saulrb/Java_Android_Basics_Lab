package personas.lab.m1.mx.itesm.m1_srb_labo_personas;

import java.text.DecimalFormat;

/**
 * Created by saul on 18/1/2017.
 */

public class Becado extends Estudiante {

    private double porcentaje;

    public Becado(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Becado(int matricula, String nombre, String carrera, double colegiatura, double porcentaje) {
        super(matricula, nombre, carrera, colegiatura);
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String mostrar() {
        DecimalFormat df = new DecimalFormat("%");
        DecimalFormat dfc = new DecimalFormat("0.00");
        return super.mostrar().concat(" tiene un porcentaje de beca:").concat(df.format(this.porcentaje/100)
                .concat(" su colegiatura con descuento:").concat(dfc.format(this.getColegiaturaConDescuento())));
    }

    public double getColegiaturaConDescuento(){
        return this.colegiatura-(this.colegiatura*this.porcentaje/100);
    }
}
