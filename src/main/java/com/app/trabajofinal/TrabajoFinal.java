package com.app.trabajofinal;

import org.hibernate.Session;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TrabajoFinal {

    public static void main(String[] args) {
        TrabajoFinal es = new TrabajoFinal();
        es.ejemplo2();
    }

       public void ejemplo2() {

        String area;
        do {
            area = JOptionPane.showInputDialog("Sobre qué área desea trabajar? \n" +
                    "1 -> Cargar una orden \n" +
                    "2 -> Listar ordenes entre dos fechas\n" +
                    "3 -> Listar todas las ordenes \n" +
                    "4 -> Salir");
            switch (area) {
                case "1":
                    int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del Cliente"));
                    getClienteByDni(dni);
                    break;
                case "2":
                    listEvents();
                    break;
                case "3":
                    listartodo();
            }

        } while (!area.equals("4"));

        System.out.println("Que tengas un buen día");

        HibernateUtil.getSessionFactory().close();
    }
    private static List<Incidencia> filtrar(List<Incidencia> lista, LocalDate desde, LocalDate hasta) {
        return lista.stream()
                .filter(i -> {
                    LocalDate fecha = i.getFecha_incidencia();
                    return (fecha.isEqual(desde) || fecha.isAfter(desde)) &&
                            (fecha.isEqual(hasta) || fecha.isBefore(hasta));
                })
                .collect(Collectors.toList());
    }


//    private static List<Incidencia> filtrar(List<Incidencia> lista, LocalDate desde, LocalDate hasta) {
//        List<Incidencia> resultado = new ArrayList<>();
//
//        for (Incidencia i : lista) {
//            LocalDate fecha = i.getFecha_incidencia();
//            if ((fecha.isEqual(desde) || fecha.isAfter(desde)) &&
//                    (fecha.isEqual(hasta) || fecha.isBefore(hasta))) {
//                resultado.add(i);
//            }
//        }
//        return resultado;
//    }

    private void listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Incidencia> result = session.createQuery("FROM Incidencia", Incidencia.class).list();

        LocalDate desde = LocalDate.parse(JOptionPane.showInputDialog("Desde que fecha (YYYY-MM-DD)"));
        LocalDate hasta = LocalDate.parse(JOptionPane.showInputDialog("Hasta que fecha (YYYY-MM-DD)"));

        List<Incidencia> filtrado = filtrar(result, desde, hasta);

        session.getTransaction().commit();

        System.out.println("Las ordenes entre " + desde + " y " + hasta + " son: ");

        filtrado.forEach(e -> System.out.println(
                "/ Cliente: " + e.getCliente().getNombre() +
                        "/ Tecnico: " + e.getTecnico().getNombre_tecnico() +
                        "/ Fecha: " + e.getFecha_incidencia() +
                        "/ Categoria: " + e.getCategoria().getDesc_categoria())
        );

//        for (Incidencia e : filtrado) {
//            System.out.println(
//                    "/ Cliente: " + e.getCliente().getNombre() +
//                            "/ Tecnico: " + e.getTecnico().getNombre_tecnico() +
//                            "/ Fecha: " + e.getFecha_incidencia() +
//                            "/ Categoria: " + e.getCategoria().getDesc_categoria());
//        }
    }

    private void listartodo() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Incidencia> result = session.createQuery("FROM Incidencia", Incidencia.class).list();

        session.getTransaction().commit();

        System.out.println("Estas son todas las ordenes");

        result.forEach(e -> System.out.println(
                "/ Cliente: " + e.getCliente().getNombre() +
                        "/ Tecnico: " + e.getTecnico().getNombre_tecnico() +
                        "/ Fecha: " + e.getFecha_incidencia() +
                        "/ Categoria: " + e.getCategoria().getDesc_categoria())
        );

//        for (Incidencia e : result) {
//            System.out.println(
//                    "/ Cliente: " + e.getCliente().getNombre() +
//                            "/ Tecnico: " + e.getTecnico().getNombre_tecnico() +
//                            "/ Fecha: " + e.getFecha_incidencia() +
//                            "/ Categoria: " + e.getCategoria().getDesc_categoria());
//        }
    }

    private void getClienteByDni(int dni) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        int tecnico = Integer.parseInt(JOptionPane.showInputDialog(
                "Seleccione el Tecnico a cargo\n" +
                        "1 -> Lucia \n" +
                        "2 -> Ernesto \n" +
                        "3 -> Jose "));

        int categoria = Integer.parseInt(JOptionPane.showInputDialog(
                "Seleccione la Categoria\n" +
                        "1 -> Reparacion \n" +
                        "2 -> Mantenimiento \n" +
                        "3 -> Reposicion "));

        Cliente cliente = session.get(Cliente.class, dni);
        Categoria cat = session.get(Categoria.class, categoria);
        Tecnico tec = session.get(Tecnico.class, tecnico);

        float costo = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el costo del trabajo"));
        String desc = JOptionPane.showInputDialog("Describa el problema");

        Incidencia inc = new Incidencia();
        // inc.setEstado(Estado.ACTIVO);
        inc.setEstado(true);
        inc.setCosto(costo);
        inc.setDesc_incidencia(desc);
        inc.setFecha_incidencia(LocalDate.now());

        cat.addIncidencia(inc);
        tec.addIncidencia(inc);

        if (cliente != null) {

            System.out.println("Cliente con DNI: " + dni + " encontrado");
            cliente.addIncidencia(inc);
            session.save(inc);

            System.out.println("Orden GUARDADA");
        } else {
            System.out.println("No se encontró ninguna cliente con el DNI: " + dni);
            Cliente cli = new Cliente();

            String nombre = JOptionPane.showInputDialog("Datos del Cliente: Nombre");
            String direccion = JOptionPane.showInputDialog("Datos del Cliente: Direccion");
            String mail = JOptionPane.showInputDialog("Datos del Cliente: Mail");

            cli.setDni(dni);
            cli.setMail(mail);
            cli.setDireccion(direccion);
            cli.setNombre(nombre);

            cli.addIncidencia(inc);

            session.save(cli);
            session.save(inc);

        }
        session.getTransaction().commit();

    }
}
