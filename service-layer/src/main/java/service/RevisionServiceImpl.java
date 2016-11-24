package service;

import entity.Revision;
import java.util.List;
import javax.inject.Inject;
import dao.RevisionDao;
import org.apache.commons.lang3.Validate;


/**
 * Created by Marek Bohm on 23.11.2016.
 */
public class RevisionServiceImpl implements RevisionService {

    @Inject
    private RevisionDao revisionDao;

    @Override
    public Revision create(Revision revision) {

        Validate.isTrue(revision.getId() == null);
        revisionDao.create(revision);
        return revision;
    }

    @Override
    public Revision update(Revision revision) {
        Validate.notNull(revision.getId());
        revisionDao.create(revision);
        return revision;

    }

    @Override
    public void delete(Revision revision) {
        Validate.notNull(revision.getId());
        revisionDao.delete(revision);
    }

    @Override
    public Revision findById(Long id) {
        return revisionDao.findById(id);
    }

    @Override
    public List<Revision> findAllRevisions() {
        return revisionDao.findAllRevisions();
    }
}
