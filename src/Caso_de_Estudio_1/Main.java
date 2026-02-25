package Caso_de_Estudio_1;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expresion = "";
        int opcion = 0;


        while (opcion != 3) {
            opcion = menu(scanner, expresion);

            switch (opcion) {
                case 1: {
                    System.out.print("Ingrese una expresión aritmética: ");
                    expresion = scanner.nextLine();

                    if (expresion.isEmpty()) {
                        System.out.println("ERROR: No se aceptan expresiones vacías");
                    } else {
                        System.out.println("Expresión ingresada con éxito");
                    }
                    break;
                }

                case 2: {
                    if (expresion.isEmpty()) {
                        System.out.println("ERROR: No se puede analizar una expresión vacía");
                        System.out.println("Por favor ingrese una expresión");
                        break;
                    }

                    Token top = construirLista(expresion);
                    PilaCaracteres pila = new PilaCaracteres();
                    Token actual = top;
                    boolean error = false;

                    while (actual != null) {

                        char caracter = actual.getValor();

                        if (caracter == '(') {
                            pila.push(caracter);
                        } else if (caracter == ')') {
                            if (pila.isEmpty()) {
                                System.out.println("ERROR: Paréntesis de cierre sin apertura.");
                                System.out.println("Faltante en la expresión: (");
                                error = true;
                                break;
                            } else {
                                pila.pop();
                            }
                        }
                        actual = actual.siguiente;
                    }

                    if (!error) {
                        if (!pila.isEmpty()) {
                            System.out.println("ERROR: Hay paréntesis sin cerrar.");
                            System.out.println("Faltante en la expresión: )");
                        } else {
                            System.out.println("Expresión válida.");
                        }
                    }

                    break;
                }

                case 3: {
                    System.out.println("SALIENDO DEL PROGRAMA...");
                    break;
                }

                default: {
                    System.out.print("Opción inválida, por favor ingrese otra opción");
                }
            }
        }


    }

    public static Token construirLista(String expresion) {

        if (expresion == null || expresion.isEmpty()) return null;

        Token top = new Token(expresion.charAt(0));
        Token actual = top;

        for (int i = 1; i < expresion.length(); i++) {
            actual.siguiente = new Token(expresion.charAt(i));
            actual = actual.siguiente;
        }

        return top;
    }

    public static int menu(Scanner scanner, String expresion) {
        int opcion = 0;

        System.out.println("--- Sistema de Análisis de Expresiones Aritméticas ---");

        if (!expresion.isEmpty()) {
            System.out.print("\n");
            System.out.println("Expresión Ingresada: " + expresion);
            System.out.print("\n");
        }

        System.out.println("Seleccione una opción:");
        System.out.println("1. Ingresar Expresión");
        System.out.println("2. Analizar");
        System.out.println("3. Salir");
        System.out.print("\n");
        System.out.print("Ingrese una opción: ");
        opcion = Integer.parseInt(scanner.nextLine());

        return opcion;
    }
}