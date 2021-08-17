package EjercicioEntityManager;

public class Configuration implements IConfiguration{
    private static IConfiguration configuration = null;
    private Configuration(){
    }

    public static IConfiguration getConfiguration(){
      if(configuration==null){
          configuration = new Configuration();
      }
      return configuration;
    }

    public String getUser(){return System.getenv("JAVA_SERV")+"pizzeria";}
    public String getPassWord(){return System.getenv("JAVA_USER");}
    public String getUrl(){return System.getenv("JAVA_PASSWD");}
}
