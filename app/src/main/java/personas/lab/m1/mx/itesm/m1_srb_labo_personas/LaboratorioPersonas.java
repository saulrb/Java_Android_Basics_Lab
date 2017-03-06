package personas.lab.m1.mx.itesm.m1_srb_labo_personas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by saul on 20/1/2017.
 */

public class LaboratorioPersonas {

    private static String BECADO = "becado";
    private static String REPRESENTATIVO = "representativo";
    private static String NORMAL = "normal";

    public LaboratorioPersonas() {
    }

    public static void main(String[] args){

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        int iRespuesta = -1;
        do{
            System.out.println("Registra la opcion deseada");
            System.out.println("0) Salir");
            System.out.println("1) Alta alumno ");
            System.out.println("2) Baja alumno ");
            System.out.println("3) Modificar alumno ");
            System.out.println("4) Consulta alumno");
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            try {
                iRespuesta = Integer.parseInt(keyboard.readLine());
            }catch (IOException e ){
                System.out.println("Error: "+ e.toString());
            }
            switch(iRespuesta){
                case 0: break;
                case 1: agregar(keyboard,estudiantes);
                        break;
                case 2: borrar(keyboard,estudiantes);
                        break;
                case 3: modificar(keyboard,estudiantes);
                        break;
                case 4: consultar(keyboard,estudiantes);
                        break;
                default:
                    System.out.println("Operacion no permitida, vuelva a intentarlo...");
            }
        }while (iRespuesta != 0);


    }
    private static  void agregar(BufferedReader keyboard, ArrayList<Estudiante> estudiantes) {
        System.out.println("Captura nuevo estudiante");
        int iMat = capturaMatricula(keyboard, " agregar ");
        Estudiante founded = findStudent(iMat, estudiantes);
        if (founded == null) {
            founded = (Estudiante) leerDatos(keyboard);
            founded.setMatricula(iMat);
        }else {
            System.out.println("Estudiante ya existe con el numero de matricula".concat(String.valueOf(iMat)).concat(" !!!"));
        }
        if( founded != null) {
            estudiantes.add(founded);
        }
    }

    private static void borrar(BufferedReader keyboard, ArrayList<Estudiante> estudiantes){
        int iMat =  capturaMatricula(keyboard," borrar");
        Estudiante founded = findStudent(iMat,estudiantes);
        if(founded == null)
            System.out.println("Estudiante no se encontro !!!");
        else {
            estudiantes.remove(founded);
            System.out.println("Estuduante fue dado de baja !!!");
        }
    }

    private  static void modificar(BufferedReader keyboard, ArrayList<Estudiante> estudiantes){
        int iMat = capturaMatricula(keyboard," modificar");
        Estudiante founded = findStudent(iMat,estudiantes);
        if(founded == null)
            System.out.println("Estudiante no se encontro !!!");
        else {
            System.out.println("Estuduante encontrado: ");
            System.out.println(founded.mostrar());
            Estudiante student = (Estudiante) leerDatos(keyboard);
            if( student.getClass().getCanonicalName()  != founded.getClass().getCanonicalName() ) {
                student.setMatricula(founded.getMatricula());
                if (student.getCarrera() == null )  student.setCarrera(founded.getCarrera())  ;
                if (student.getNombre() == null ) student.setNombre(founded.getNombre());
                if (student.getColegiatura() == 0 ) student.setColegiatura(founded.getColegiatura());
                estudiantes.remove(founded);
                estudiantes.add(student);
            }else {
                if (student.getNombre() != null && student.getNombre() != founded.getNombre() )
                    founded.setNombre(student.getNombre());
                if (student.getCarrera() != null && student.getCarrera() != founded.getCarrera())
                    founded.setCarrera(student.getCarrera());
                if (student.getColegiatura() != 0 && student.getColegiatura() != founded.getColegiatura()){
                    founded.setColegiatura(student.getColegiatura());
                }
                if (student instanceof Becado) {
                    Becado becado = (Becado) student;
                    Becado foundedB = (Becado) founded;
                    if( becado.getPorcentaje() != foundedB.getPorcentaje() )
                        foundedB.setPorcentaje(becado.getPorcentaje());
                }
                if (student instanceof Representativo){
                    Representativo rep = (Representativo) student;
                    Representativo foundedRep = (Representativo) founded;
                    if (rep.getDeporte() != null && rep.getDeporte() != foundedRep.getDeporte())
                        foundedRep.setDeporte(rep.getDeporte());
                }
            }
        }

    }

    private  static void consultar(BufferedReader keyboard, ArrayList<Estudiante> estudiantes){
        int iMat =  capturaMatricula(keyboard," consultar");
        Estudiante founded = findStudent(iMat,estudiantes);
        if(founded == null)
            System.out.println("Estudiante no se encontro !!!");
        else {
            System.out.println("Estuduante encontrado: ");
            System.out.println(founded.mostrar());
        }
    }

    private static Object leerDatos(BufferedReader keyboard){
        int iMat=0;
        String sNombre = "", sCarrera="", deporte="", sTipo="";
        double dBeca=0, dColegiatura=0;
        boolean bEncontro = false;
        Object createdStudend = null;
        try{
            System.out.println("Ingresa el nombre ");
            sNombre = keyboard.readLine();
            System.out.println("Ingresa el carrera ");
            sCarrera = keyboard.readLine();
            System.out.println("Ingresa la colegiatura ");
            dColegiatura = Double.parseDouble(keyboard.readLine());
            System.out.println("Ingresa el tipo normal/becado/representativo ");
            sTipo = keyboard.readLine();
            if(BECADO.equalsIgnoreCase(sTipo)){
                System.out.println("Ingresa porcentaje de beca ");
                dBeca = Double.parseDouble(keyboard.readLine());
                createdStudend = new Becado(iMat,sNombre,sCarrera,dColegiatura,dBeca);
            }else if(REPRESENTATIVO.equalsIgnoreCase(sTipo)){
                System.out.println("Ingresa el deporte ");
                deporte = keyboard.readLine();
                createdStudend =new Representativo(iMat,sNombre,sCarrera,dColegiatura,deporte);
            }else if (NORMAL.equalsIgnoreCase(sTipo)){
                createdStudend = new Estudiante(iMat,sNombre,sCarrera,dColegiatura);
            }else
                System.out.println("Error: No es tipo de alumno valido");
        }catch( IOException e){
            System.out.println("Error: "+e.toString());
        }
        return createdStudend;
    }

    private static int capturaMatricula(BufferedReader keyboard, String announcement ){
        System.out.println("Captura la matricula del estuduante a ".concat(announcement));
        int iMat = 0;
        try{
            iMat = Integer.parseInt(keyboard.readLine());
        }catch( IOException e){
            System.out.println("Error: "+e.toString());
        }
        return iMat;
    }

    private static Estudiante findStudent(int iMat, ArrayList<Estudiante> estudiantes){
        Estudiante founded = null;
        for(Estudiante estudiante: estudiantes)
            if(estudiante.getMatricula() == iMat) {
                founded = estudiante;
                break;
            }
        return founded;
    }

}
