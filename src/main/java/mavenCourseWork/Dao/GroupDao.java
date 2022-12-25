package mavenCourseWork.Dao;

import mavenCourseWork.app.Group;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class GroupDao implements Dao<Group, Integer> {
    private EntityManager manager;

    public void add(Group group){
        manager.persist(group);
    }
    public Group getByPK(Integer id){
        return manager.find(Group.class, id);
    }
    public List<Group> getAll(){
        Query queryGroup = manager.createQuery("SELECT c FROM Group c");
        List<Group> allGroup = (List<Group>) queryGroup.getResultList();
        return allGroup;
    }
    public void update(Group group){
        manager.merge(group);
    }
    public void deleteByPK(Integer id){
        manager.remove(getByPK(id));
    }
    public List<Group> findByNameEmail(){
        TypedQuery<Group> queryGroup = manager.createQuery(
                "SELECT c FROM Group c WHERE c.start > myNow" , Group.class);
        List<Group> myGroup = queryGroup
                .setParameter("myNow", LocalDateTime.now())
                .getResultList();
        return myGroup;
    }
}
