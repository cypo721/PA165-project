package service;

import entity.Revision;
import java.util.List;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
public interface RevisionService {

    /**
     * Cretae a revision in the system
     * @param revision
     * @return newly created revision entity
     */
    public Revision create(Revision revision);

    /**
     * Update a revision in the system
     * @param revision
     * @return modified revision entity
     */
    public Revision update(Revision revision);

    /**
     * Deletes revision given in parameter
     * @param revision
     */
    public void delete(Revision revision);

    /**
     * Deletes revision given in parameter
     * @param id
     * @return revision matching id from parameter
     */
    public Revision findById(Long id);

    /**
     * Deletes revision given in parameter
     * @return list of all revisions in the system
     */
    public List<Revision> findAllRevisions();
}
