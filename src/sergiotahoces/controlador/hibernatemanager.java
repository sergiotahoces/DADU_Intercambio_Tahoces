package sergiotahoces.controlador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import sergiotahoces.modelo.Elemento;
import sergiotahoces.modelo.HibernateUtil;

public class hibernatemanager {
	private HashMap<Integer, Elemento> elementos = new HashMap<Integer, Elemento>();
	Session session;

	public hibernatemanager() {
		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	public void mostrarHibernate() {
		Query q = session.createQuery("select e from Elemento e");
        List results = q.list();
        Iterator itr = results.iterator();
        while (itr.hasNext()) {
            Elemento e = (Elemento) itr.next();
            System.out.println(e.toString());
        }
	}
	
	public void insertarHibernate(Elemento e) {
		session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
	}
	public void modificarHibernate(Elemento a) {
		
		Query q = session.createQuery("select e from Elemento e");
        List results = q.list();
        Iterator itr = results.iterator();
        while (itr.hasNext()) {
            Elemento e = (Elemento) itr.next();
            if(a.getId()==e.getId()) {
            	session.beginTransaction();
                Query j = session.createQuery("update Elemento e set e.nombre ='" + a.getNombre() + "', e.descripcion='"
                        + a.getNombre() + "', e.caracteristicas='" + a.getCaracteristica() + "' where e.id =" + a.getId());
                j.executeUpdate();
                session.getTransaction().commit();
                System.out.println("Elemento cambiado.");
            }
            else {
            	System.out.println("Id no existe");
            }
        }

	}
	public void borrarHibernate(int id) {
		session.beginTransaction();
        Query q = session.createQuery("delete from Elemento where id=" + id);
        q.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Elemento borrado");
	}
	public void borrarTodosHibernate() {
		session.beginTransaction();
        Query q = session.createQuery("delete from Elemento");
        q.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Datos borrados");
	}
	public void buscarElementoHibernate(int id) {
		Elemento e = (Elemento) session.get(Elemento.class, id);
		System.out.println(e.toString());
	}
	public void anadirhibernate(HashMap<Integer, Elemento> elementos) {
		Iterator<Elemento> itr = elementos.values().iterator();
        while (itr.hasNext()) {
            insertarHibernate(itr.next());
        }
	}

}
