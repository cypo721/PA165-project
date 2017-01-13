package service.facade;

import dto.RevisionDTO;
import entity.Machine;
import entity.Revision;
import facade.RevisionFacade;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BeanMappingService;
import service.RevisionService;

import javax.inject.Inject;
import java.util.List;
import service.MachineService;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
@Service
@Transactional
public class RevisionFacadeImpl implements RevisionFacade {

    @Inject
    private RevisionService revisionService;
    
    @Inject
    private MachineService machineService;

    @Inject
    private BeanMappingService beanMappingService;


    @Override
    public Long createRevision(RevisionDTO revisionDTO) {
        Validate.isTrue(revisionDTO.getId() == null);

        Revision revision =  beanMappingService.mapTo(revisionDTO, Revision.class);
        Revision saved = revisionService.create(revision);
        
        return saved.getId();
    }

    @Override
    public void updateRevision(RevisionDTO revisionDTO) {
        Validate.notNull(revisionDTO.getId());
        Revision entity =  beanMappingService.mapTo(revisionDTO, Revision.class);
        
        revisionService.update(entity);
    }


    @Override
    public RevisionDTO findById(Long id) {
        Validate.notNull(id);
        return  beanMappingService.mapTo(revisionService.findById(id), RevisionDTO.class);
    }

    @Override
    public List<RevisionDTO> findAllRevisions() {

        return  beanMappingService.mapTo(revisionService.findAllRevisions(), RevisionDTO.class);
    }

    @Override
    public void deleteRevision(Long id) {
        Revision revision = new Revision(); //todo
        revision.setId(id);
        revisionService.delete(revision);  
    }


}