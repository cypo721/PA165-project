package facade;

import dto.RevisionDTO;

import java.util.List;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
public interface RevisionFacade {

    Long createRevision(RevisionDTO revisionDTO);
    void updateRevision(RevisionDTO revisionDTO);
    RevisionDTO findById(Long id);
    List<RevisionDTO> findAllRevisions();

}
