package main.java.org.example.Factory;


public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance;

    public static DaoFactory getInstance() {
        if(instance==null){
            instance= new MySqlDaoFactory();
        }
        return instance;
    }


}
