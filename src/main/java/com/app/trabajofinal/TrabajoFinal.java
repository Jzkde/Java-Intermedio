package com.app.trabajofinal;


import org.hibernate.Session;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;


public class TrabajoFinal {

    public static void main(String[] args) {


        TrabajoFinal es = new TrabajoFinal();

        es.ejemplo2();
    }


    public void ejemplo2() {
        createAndStoreEvent("El Event");
        int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del Cliente"));
        getClienteByDni(dni);
      //  listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Categoria cat = new Categoria();
        cat.setDesc_categoria("servicio");

        Tecnico tec = new Tecnico();
        tec.setNombre_tecnico("alguien que sabe");

        Incidencia inc = new Incidencia();
        inc.setEstado(true);
        inc.setCosto(123);
        inc.setDesc_incidencia("algo paso");
        inc.setFecha_incidencia(LocalDate.now());
        inc.addCategoria(cat);
        inc.addTecnico(tec);

        Cliente cli = new Cliente();
        cli.setDni(143);
        cli.setMail("n@n.com");
        cli.setDireccion("un lugar");
        cli.setNombre("alguien");
        cli.addIncidencia(inc);

//        session.save(cli);
//        session.save(inc);
//        session.save(tec);
//        session.save(cat);

        session.getTransaction().commit();


    }


    private void listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Incidencia> result = (List<Incidencia>) session.createQuery("from Incidencia").list();
        session.getTransaction().commit();
        for (Incidencia e : result) {
            System.out.println("Descripcion: " + e.getDesc_incidencia() + "/ Fecha: " + e.getFecha_incidencia() + "/ Costo: "
                    + e.getCosto() + "/ Categoria: " + e.getCategorias() + "/ Tecnico: "
                    + e.getTecnicos() + "/ Cliente: " + e.getCliente().getNombre());
        }

    }


    private Cliente getClienteByDni(int dni) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Cliente cliente = session.get(Cliente.class, dni);

        Categoria cat = session.get(Categoria.class, 1);
        Tecnico tec = session.get(Tecnico.class, 1);


        if (cliente != null) {
            float costo = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el costo de reparacion"));
            String desc = JOptionPane.showInputDialog("Describa el problema");
            Incidencia inc = new Incidencia();
            inc.setEstado(true);
            inc.setCosto(costo);
            inc.setDesc_incidencia(desc);
            inc.setFecha_incidencia(LocalDate.now());
            inc.addCategoria(cat);
            inc.addTecnico(tec);
            cliente.addIncidencia(inc);

            session.save(inc);

            System.out.println("Incidencia GUARDADA");
        } else {
            System.out.println("No se encontró ninguna cliente con el DNI: " + dni);
            Cliente cli = new Cliente();

            String nombre = JOptionPane.showInputDialog("Ingrese el Noombre del cliente");
            String direccion = JOptionPane.showInputDialog("Ingrese el Domicilio del cliente");
            String mail = JOptionPane.showInputDialog("Ingrese el Mail del cliente");

            float costo = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el costo de reparacion"));
            String desc = JOptionPane.showInputDialog("Describa el problema");

            cli.setDni(dni);
            cli.setMail(mail);
            cli.setDireccion(direccion);
            cli.setNombre(nombre);


            Incidencia inc = new Incidencia();
            inc.setEstado(true);
            inc.setCosto(costo);
            inc.setDesc_incidencia(desc);
            inc.setFecha_incidencia(LocalDate.now());
            inc.addCategoria(cat);
            cli.addIncidencia(inc);

            session.save(cli);
            session.save(inc);


        }
        session.getTransaction().commit();
        return cliente;
    }


//    private void listEvents() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List<Incidencia> result = (List<Incidencia>) session.createQuery("from Incidencia").list();
//        session.getTransaction().commit();
////        List<Incidencia> filteredList = result.stream().filter(incidencia -> incidencia.getFecha_incidencia().after(desde) && incidencia.getFecha_incidencia().before(hasta))
////                .collect(Collectors.toList());
//        for (Incidencia e : result) {
//            System.out.println("Descripcion: " + e.getDesc_incidencia() + "/ Fecha: " + e.getFecha_incidencia() + "/ Costo: "
//                    + e.getCosto() + "/ Categoria: " + e.getCategorias() + "/ Tecnico: "
//                    + e.getTecnicos() + "/ Cliente: " + e.getCliente().getNombre());
//
//        }
//    }
}