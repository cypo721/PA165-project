package facade;

import dto.RevisionDTO;

import java.util.List;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
public interface RevisionFacade {

    /**
     * Create new revision
     * @param revisionDTO
     * @return id of the newly created revision
     */
    Long createRevision(RevisionDTO revisionDTO);
    
    /**
     * Update given revision
     * @param revisionDTO 
     */
    void updateRevision(RevisionDTO revisionDTO);
    
    void deleteRevision(Long id);
    /**
     * Find revision by id
     * @param id
     * @return DTO of found revision
     */
    RevisionDTO findById(Long id);
    
    /**
     * Find all revisions
     * @return list of all revisions
     */
    List<RevisionDTO> findAllRevisions();

}