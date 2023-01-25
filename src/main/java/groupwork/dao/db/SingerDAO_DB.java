package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.orm.manager.Manager;
import groupwork.entity.SingerEntity;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingerDAO_DB implements ISingerDao {
   /* private final String SQL_GET = "SELECT id, name FROM app.artists;";
    private final String SQL_GET_NAME = "SELECT name FROM app.artists WHERE id = ?;";
    private final String SQL_IS_CONTAIN = "SELECT id FROM app.artists WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM app.artists WHERE id = ?;";
    private final String SQL_CREATE = "INSERT INTO app.artists (name) VALUES (?);";
    private final String SQL_UPDATE = "UPDATE app.artists SET name = ? WHERE id = ?;";*/

    private final Manager manager;

    public SingerDAO_DB(Manager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getSingerList() {
        List<SingerEntity>list = new ArrayList<>();

        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();



        /*try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET);
            ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new SingerDTO(name, id));
            }

        } catch (SQLException e){
            throw new RuntimeException("Database connection error", e);
        }*/

        return list;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        /*try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_IS_CONTAIN)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                result = true;
            }

            resultSet.close();

        }catch (SQLException e){
            throw new RuntimeException("Database connection error", e);
        }*/
        return result;
    }

    @Override
    public void delete(SingerEntity singerEntity) {

        /*try (Connection connection = dataSourceWrapper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Database connection error", e);
        }*/
    }

    @Override
    public void create(SingerEntity singerEntity) {

        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(singerEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void update(int id, SingerEntity singerEntity) {
        String singer = singerEntity.getName();

       /* try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1, singer);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Database connection error", e);
        }*/
    }
    @Override
    public SingerEntity get(int id) {
        String name = null;
       /* try (Connection connection = dataSourceWrapper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_NAME)
        ) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }*/
        return null;
    }
}
