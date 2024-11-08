package org.example.modelo.example.main;

import org.example.modelo.example.modelo.GestionBD;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            menu();
            String opcion = in.nextLine();

            switch (opcion) {
                case "1":
                    conectarBase();
                    break;
                case "2":
                    desconectarBase();
                    break;
                case "3":
                    salir = salirMenu();
                    break;
            }


        }

        // Cerramos el stream de teclado
        in.close();
    } // Fin del main

    private static boolean salirMenu() {
        System.out.println("Saliendo del menu");
        return true;
    }

    private static void desconectarBase() {
        GestionBD.desconectar();
        System.out.println("Desconectado de la base " + GestionBD.nombreBase());
    }

    private static void conectarBase() {
        System.out.println("Conectando a la base");
        GestionBD.getConexion();
        System.out.println("Conectado a " + GestionBD.nombreBase());
    }

    private static void menu() {
        String texto = """
                1.- Conectar
                2.- Desconectar
                3.- Salir
                """;
        System.out.println(texto);
    }

}
