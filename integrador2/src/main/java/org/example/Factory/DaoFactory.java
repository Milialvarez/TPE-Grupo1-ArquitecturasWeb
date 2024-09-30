package main.java.org.example.Factory;


public abstract class DaoFactory  {

    public static DaoFactory getInstance(int whichFactory) {
        switch (whichFactory) {
            case 1:
                return MySqlDaoFactory.getInstance();
            //case 2:
            //  return new DerbyJDBCDAOFactory();
            default:
                return null;
        }
    }
    //hacer metodos daos


}