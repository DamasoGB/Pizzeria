package objectsPizzeria;

import java.util.*;

//import java.lang.reflect.Field;

public abstract class Entity{
  private UUID id;

  public UUID getId(){
    return this.id;
  }
  
  /*public static Entity getEntity(){
    Entity entity = new Entity();
    entity.id = UUID.randomUUID();
    return entity;
  }*/

  public void validate(){
    try {
      if (id == null) {
          throw new Exception("ID de la entidad inválido");
      }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  @Override
  public int hashCode() {
    return this.getId().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Entity)){
      return false;
    }
    Entity temp = (Entity) obj;
    return this.getId().equals(temp.getId());
  }

  /*public void getInsert(Object clase) throws Exception{
    String name =  this.getClass().getName();
    Field[] fields = this.getClass().getDeclaredFields();
    System.out.println("Insertar: ");
    for (int i = 0; i < fields.length; i++) {

      // get value of the fields
      Object value = fields[i].get(clase);
      System.out.println(value);
    }
  }*/
  
}
 
