package mavenCourseWork.Dao;

import mavenCourseWork.app.Mountain;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MountainDao implements Dao<Mountain, Integer> {
    private EntityManager manager;

    public void add(Mountain mountain){
        manager.persist(mountain);
    }
    public Mountain getByPK(Integer id){
        return manager.find(Mountain.class, id);
    }
    public List<Mountain> getAll(){
        Query queryMountain = manager.createQuery("SELECT m FROM Mountain m");
        List<Mountain> allMountain = (List<Mountain>) queryMountain.getResultList();
        return allMountain;
    }
    public void update(Mountain mountain){
        manager.merge(mountain);
    }
    public void deleteByPK(Integer id){
        manager.remove(getByPK(id));
    }
    public List<Mountain> findHigh (int min, int max){
        TypedQuery<Mountain> queryFindHeight = manager.createQuery(
                "SELECT m FROM Mountain m WHERE m.high > :min AND m.high < :max" , Mountain.class);
        List<Mountain> myMountain = queryFindHeight
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        return myMountain;
    }
    public List<Mountain> findByName (String name){
        TypedQuery<Mountain> queryFindHeight = manager.createQuery(
                "SELECT m FROM Mountain m WHERE m.name = :name" , Mountain.class);
        List<Mountain> myMountain = queryFindHeight
                .setParameter("name", name)
                .getResultList();
        return myMountain;
    }
}
