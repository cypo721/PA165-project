package service.facade;

import dto.RevisionDTO;
import entity.Revision;
import facade.RevisionFacade;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import service.RevisionService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
@Service
@Transactional
public class RevisionFacadeImpl implements RevisionFacade {

    @Inject
    private RevisionService revisionService;

    @Inject
    private Mapper dozer;


    @Override
    public RevisionDTO createRevision(RevisionDTO revisionDTO) {
        Validate.isTrue(revisionDTO.getId() == null);

        Revision revision = convert(revisionDTO);
        Revision saved = revisionService.create(revision);

        return convert(saved);
    }

    @Override
    public RevisionDTO updateRevision(RevisionDTO revisionDTO) {
        Validate.notNull(revisionDTO.getId());

        Revision entity = convert(revisionDTO);
        Revision updated = revisionService.update(entity);

        return convert(updated);
    }


    @Override
    public RevisionDTO findById(Long id) {

        Validate.notNull(id);
        return convert(revisionService.findById(id));
    }

    @Override
    public List<RevisionDTO> findAllRevisions() {

        return  convert(revisionService.findAllRevisions());
    }

    private Revision convert(RevisionDTO dto) {
        return dozer.map(dto, Revision.class);
    }

    private RevisionDTO convert(Revision entity) {
        return dozer.map(entity, RevisionDTO.class);
    }

    private List<RevisionDTO> convert(List<Revision> revisions){
        return revisions.stream().map(revision -> convert(revision)).collect(Collectors.toList());
    }
}
