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

	public void mostrarBD() {
		Query q = session.createQuery("select e from Elemento e");
        List results = q.list();
        Iterator itr = results.iterator();
        while (itr.hasNext()) {
            Elemento e = (Elemento) itr.next();
            System.out.println(e.toString());
        }
	}

	public void insertarBD() {

	}

	public void mostrarFichero() {

	}

	public void insertarFichero() {

	}

	public void moverBD() {

	}

	public void moverFichero() {

	}

}
