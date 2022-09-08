import java.util.*;
import java.io.*;

public class ManejadorDeArchivos{
  static Scanner keyboard = new Scanner(System.in);
  
  
  //Metodo para buscar paciente
   public static String buscarPaciente(){
      
      System.out.println("Entre el numero de expediente:");
      String nameInputFile = keyboard.next()+".txt"; 
      return nameInputFile;
   }
   
   
   static void crearPaciente(){
        PrintWriter pacienteNuevo;
        
        // Inicializa arreglo que contendra la informacion del usuario
        String info[] = {"Nombre", "Seguro Social", "Numero de Expediente", "Fecha de Nacimiento", "Sexo", "Direccion",
        "Plan Medico", "Fecha de Visita", "Diagnostico", "Receta", "Proxima Visita"};

        for (int i = 0; i < info.length; i++) {
            System.out.println(info[i] + ": ");
            info[i] = keyboard.next(); // Asigna valor del arreglo a lo que el usuario entro.
        }
        try {
            // Escribe los valores del arreglo a el archivo.
            pacienteNuevo = new PrintWriter(info[2] + ".txt");
            for (int j = 0; j < info.length; j++) {
                pacienteNuevo.println(info[j]);
            }
            pacienteNuevo.close();
        }

        catch (Exception e){
            System.out.println("Hubo un error al crear el paciente.");
        }
  }
   
   public static void main(String [] args){
    
     //Varible para el primer menu
     int primerMenu;
     System.out.println("Seleccione una opcion. \n1.Buscar Paciente \n2.Crear un file para un paciente nuevo.");
     primerMenu= keyboard.nextInt();


     FileReader reader;
     Scanner fileInput;
     
     //Menu para seleccionar si se va crear un file para un paciente nuevo o trabajar con un file de un paciente existente.
     if(primerMenu ==1){
       //Asigana a una variable el metodo de buscarPaciente
       String buscar = buscarPaciente();
       //Variable que va guardar lo que se encuentra en la linea seleccionada del file
       String strLine;
       int opcion, menu;
       
       //Arreglo que va contener toda la informacion del paciente.
       String [] pacientInfo = new String[11];
       
       try{
         reader= new FileReader(buscar);
         fileInput = new Scanner(reader);
 
         //Loop que asigna a cada linea del file un indice del arreglo
         for(int i= 0; i < pacientInfo.length; i++){
           strLine = fileInput.nextLine();
           pacientInfo[i] = strLine;
           System.out.println(strLine);
         }                     
         fileInput.close(); 
       }
       catch (IOException e){
         System.out.println("File error" + e);
      }
      
           try{
             //Le pide al usuario que entre el numero de lo que quiere hacer,
             System.out.println("Entre el numero de lo que desea hacer \n1.Editar informacion del paciente. \n2.Borrar Informacion del paciente");
             menu = keyboard.nextInt();
             PrintWriter fileOutput;
             fileOutput = new PrintWriter(buscar);

              //Edita dato del expediente del paciente
            if(menu ==1){
               
              System.out.println("Entre el numero de lo que desa editar: \n0.Nombre \n1.Seguro Social \n2.Numero de expediente\n3.fecha de nacimiento\n4.Sexo\n5.Direccion\n6.Plan Medico\n7.Fecha de visita\n8.Diagnostico\n9.Receta\n10.Proxima visita");
              //Varible para guardar la seleccion del usuario
              opcion = keyboard.nextInt();
              //Se usa la varible opcion para indicar el indice que se va cambiar
              System.out.println("Entre a lo que lo desea cambiar");
              pacientInfo[opcion]= keyboard.next();
                            
               for(int j =0; j < pacientInfo.length; j++){ 
                 fileOutput.println(pacientInfo[j]);
               }
             }
             //Borra toda la informacion del paciente 
            else if(menu == 2){
               for (int k = 0; k < pacientInfo.length; k++){
                 pacientInfo[k] = " ";
                 fileOutput.println(pacientInfo[k]);
               }
            
             } 

            fileOutput.close();
           }
           
      catch(IOException e){}
     
   }else if(primerMenu==2){
      crearPaciente();
   } 
   
   }
   }