package service;

import dao.RevisionDao;
import entity.Revision;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by Marek Bohm on 23.11.2016.
 */
@Service
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
        revisionDao.update(revision);
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
