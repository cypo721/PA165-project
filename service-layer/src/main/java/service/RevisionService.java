package service;

import entity.Revision;
import java.util.List;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
public interface RevisionService {

    public Revision create(Revision revision);
    public Revision update(Revision revision);
    public void delete(Revision revision);
    public Revision findById(Long id);
    public List<Revision> findAllRevisions();
}
