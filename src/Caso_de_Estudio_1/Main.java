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

                // El usuario ingresa la expresión aritmética
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

                // El sistema analiza la expresión ingresada
                case 2: {

                    if (expresion.isEmpty()) {
                        System.out.println("ERROR: No se permiten expresiones vacías, por favor introduzca una expresión");
                        break;
                    }

                    Token top = construirLista(expresion);

                    boolean parentesisOk = analizarParentesis(top);
                    boolean operadoresOk = analizarOperadores(top);
                    boolean noTerminaEnOperador = analizarTerminaEnOperador(top);

                    if (parentesisOk && operadoresOk && noTerminaEnOperador) {
                        System.out.println("*** La expresión " + expresion + " es válida. ***");
                    }

                    break;
                }

                // Se cierra el programa
                case 3: {
                    System.out.println("SALIENDO DEL PROGRAMA...");
                    break;
                }

                // En caso de opción inválida
                default: {
                    System.out.print("Opción inválida, por favor ingrese otra opción");
                }
            }
        }


    }

    public static int menu(Scanner scanner, String expresion) {
        int opcion = 0;

        System.out.print("\n");
        System.out.print("\n");
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

    public static Token construirLista(String expresion) {

        if (expresion == null || expresion.isEmpty()) return null;

        Token top = new Token(expresion.charAt(0));
        Token actual = top;

        // Se recorre la expresión caracter por caracter para Tokenizar la expresión y construir la lista.
        for (int i = 1; i < expresion.length(); i++) {
            actual.siguiente = new Token(expresion.charAt(i));
            actual = actual.siguiente;
        }

        // Al final se retorna la cabeza de la lista, top.
        return top;
    }

    public static boolean analizarParentesis(Token top) {

        PilaCaracteres pila = new PilaCaracteres();
        Token actual = top;

        while (actual != null) {

            char caracter = actual.getValor();

            // Si encuentra un paréntesis de abertura, lo inserta en la pila
            if (caracter == '(') {
                pila.push(caracter);
            } else if (caracter == ')') {

                // Si la pila está vacía y el programa encontró un paréntesis de cierre, es porque nunca se abrió.
                if (pila.isEmpty()) {
                    System.out.println("ERROR: Paréntesis de cierre sin apertura.");
                    return false;
                }

                // De otro modo, si encuentra contenido, es porque hay un paréntesis de abertura, por ende lo saca para darlo como cerrado
                pila.pop();
            }

            actual = actual.siguiente;
        }

        if (!pila.isEmpty()) {
            System.out.println("ERROR: Hay paréntesis sin cerrar.");
            return false;
        }

        return true;
    }

    public static boolean analizarOperadores(Token top) {

        Token actual = top;

        while (actual != null && actual.siguiente != null) {

            // Declara el caracter actual y siguiente
            char actualChar = actual.getValor();
            char siguienteChar = actual.siguiente.getValor();

            // Si ambos son operadores, lanza un error y finaliza
            if (esOperador(actualChar) && esOperador(siguienteChar)) {
                System.out.println("ERROR: Operadores consecutivos.");
                return false;
            }

            // De otro modo, mueve el actual, y por ende el siguiente de posición, y continúa revisando.
            actual = actual.siguiente;
        }

        return true;
    }

    public static boolean analizarTerminaEnOperador(Token top) {

        PilaCaracteres pila = new PilaCaracteres();
        Token actual = top;

        // Inserta todos los tokens de la expresión en la pila
        while (actual != null) {
            pila.push(actual.getValor());
            actual = actual.siguiente;
        }

        // Obtiene el top de la pila, osea el ultimo valor de la expresión
        char ultimo = pila.peek();

        // Checkea si el último valor coincide con un operador
        if (ultimo == '+' || ultimo == '-' ||
                ultimo == '*' || ultimo == '/') {

            // Si es así, entonces lanza error
            System.out.println("ERROR: La expresión termina en operador");
            return false;
        }

        return true;
    }

    // Retorna TRUE o FALSE si el caracter evaluado coincide con alguna de las condiciones o caracteres operadores establecidos.
    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}