package interfaces;

import java.util.List;

public interface Crud {

    public abstract void create(Object object);  // recibe un objeto como parametro
    public abstract Object readByID(int id);         // leer algo con TAL id
    public abstract void update(int id, Object object);
    public abstract void delete(int id);
    //public abstract List<> readAll();
}
